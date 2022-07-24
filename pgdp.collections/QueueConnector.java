package pgdp.collections;

public class QueueConnector<T> implements DataStructureConnector<T>{
    private final Queue<T> unifier;
    public QueueConnector(Queue<T> unifier) {
        this.unifier = (LinkedQueue<T>) unifier;
    }
    @Override
    public boolean hasNextElement() {
        return !(unifier.isEmpty());
    }
    @Override
    public void addElement(T t) {
        unifier.enqueue(t);
    }
    @Override
    public T removeNextElement() {
        return unifier.dequeue();
    }
}
