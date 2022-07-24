package threads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Task3 {
    private static final int NUM_THREADS = 10;
    private static final int CHANNEL_CAPACITY = 100;
    private static final int POISON_PILL = -1;
    Thread a;
    List<Thread> b;
    List<String> generated;

    public List<String> get() throws InterruptedException {
        // TODO 
        return generated;
    }

    public List<Thread> getThreads() {
        // TODO 
        return b;
    }

    public void interrupt() {
        // TODO
       
        a.interrupt();
    }

    public Task3(final int from, final int to, final int count) {
        if (from < 0 || to < 0 || !isInRange(count, 0, to - from + 1)) throw new IllegalArgumentException();
        b = new ArrayList<>();
        this.generated = new ArrayList<>(count);
        Channel locker =new Channel();
        Set<Integer> setOfIntegers= new HashSet<>();
        this.a = new Thread(){
            @Override
            public void run() {
                int j = 0;
                while (j<count){
                    if(isInterrupted()){
                        break;
                    }
                    int x = from + (int) (Math.random() * ((to - from) + 1));
                    try {
                        synchronized (setOfIntegers) {
                            if(!setOfIntegers.contains(x)) {
                                locker.write(x);
                                setOfIntegers.add(x);
                                j++;
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                locker.setDone();
            }
        };
        a.start();
        for(int i =0 ;i<10;i++ ) {
            Thread bb = new Thread(){
                @Override
                public void run() {
                    while (true){
                        if(isInterrupted()){
                            a.interrupt();
                            break;
                        }
                        if(locker.getIsDone())
                            break;
                        try {
                            int x = locker.read();
                            String ss = KanjiLib.convert(x);
                            String ans =x+", "+ss;
                            synchronized (generated){
                                generated.add(ans);
                            }
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                            break;
                        }


                    }
                }
            };
            b.add(bb);
            bb.start();

        }

    }

    private static boolean isInRange(int count, int from, int to) {
        return from <= count && count <= to;
    }
    private static class Channel {
        final Lock key = new ReentrantLock();
        final Condition isNotEmpty = key.newCondition();
        final Condition isNotFull = key.newCondition();
        boolean done=false;
        public void setDone(){
            this.done = true;
        }
        public boolean getIsDone(){
            return done&&count==0;
        }

        final int[] integers = new int[100];
        int putObj, takeObj, count;

        public void write(int x) throws InterruptedException {
            key.lock();
            try {
                while (count == integers.length)
                    isNotFull.await();
                integers[putObj] = x;
                if (++putObj == integers.length) {
                    putObj = 0;

                }

                count++;
                isNotEmpty.signal();
            } finally {
                key.unlock();
            }
        }

        public int read() throws InterruptedException {
            key.lock();
            int e = 0;
            try {
                while (count == 0)
                    isNotEmpty.await();
                int x = integers[takeObj];
                if (++takeObj == integers.length) takeObj = 0;
                count--;
                isNotFull.signal();
                return x;
            } finally {
                key.unlock();
            }
        }

        public static void main(String[] args) {
            Task3 test = new Task3(1,100,100);

            try {
                sleep(100);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            test.interrupt();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(test.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}