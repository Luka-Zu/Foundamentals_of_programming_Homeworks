package fop.w12space;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Spaceuin extends Thread {

	private Beacon start;
	private Beacon to;
	private Set<Beacon> visited;

	// For stopping all Threads at the end
	private Spaceuin parentThread;
	private List<Spaceuin> childThreads;

	// For telling the way afterward
	private FlightRecorder flightRecorder;

	// Standard constructor for first Pingu
	public Spaceuin(Beacon start, Beacon to, FlightRecorder flightRecorder) {
		this(start, to, flightRecorder, null);
	}

	// Constructor for every other Pingu
	public Spaceuin(Beacon start, Beacon to, FlightRecorder flightRecorder, Spaceuin parentThread) {
		this.start = start;
		this.to = to;
		this.flightRecorder = flightRecorder;
		visited = new HashSet<>();

		this.parentThread = parentThread;
		childThreads = new ArrayList<>();

	}

	@Override
	public void run() {
		visit(start);
	}

	private void visit(Beacon current) {
		// Way was already found
		if (this.isInterrupted())
			return;

		// Needs to be declared before synchronized
		Beacon next = null;
		Iterator<BeaconConnection> nextVisits = null;
		// Lock current location
		synchronized (current) {
			// Check here again to avoid printing the story two times
			if (this.isInterrupted())
				return;
			flightRecorder.recordArrival(current);
			// Found location of starfleet
			if (current == to) {
				stopSearching(this);
				flightRecorder.tellStory();
				return;
			}
			visited.add(current);

			/*
			 * New Pingus through wormholes
			 */
			for (BeaconConnection con : current.connections()) {
				if (con.type() == ConnectionType.WORMHOLE && !visited.contains(con.beacon())) {
					synchronized (this) {
						if (this.isInterrupted())
							return;
						Spaceuin p = new Spaceuin(con.beacon(), to, flightRecorder.createCopy(), this);
						childThreads.add(p);
						p.start();
					}
				}
			}
			// Visit next beacons
			nextVisits = current.connections().iterator();
			next = nextVisit(nextVisits);
			flightRecorder.recordDeparture(current);
		}

		while (next != null) {
			visit(next);
			// We're back from a dead end
			synchronized (current) {
				flightRecorder.recordArrival(current);
				// Determine if and where to go next
				next = nextVisit(nextVisits);
				flightRecorder.recordDeparture(current);
				// Possibly going back one step, unlock current beacon
			}
		}
	}

	private Beacon nextVisit(Iterator<BeaconConnection> outgoingConnections) {
		while (outgoingConnections.hasNext()) {
			BeaconConnection next = outgoingConnections.next();
			if (next.type() == ConnectionType.NORMAL && !visited.contains(next.beacon()))
				return next.beacon();
		}
		return null;
	}

	private synchronized void stopSearching(Spaceuin stopping) {
		// Stop parent if it's not the one stopping
		if (parentThread != null && parentThread != stopping) {
			parentThread.stopSearching(this);
		}
		// Stop every child that is not the one stopping
		for (Spaceuin p : childThreads) {
			if (p != stopping) {
				p.stopSearching(this);
			}
		}
		// Interrupt this Pingu's search
		this.interrupt();
	}
}
