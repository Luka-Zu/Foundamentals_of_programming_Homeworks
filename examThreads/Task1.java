package threads;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    private static final int NUM_THREADS = 10;

    public static List<String> generate(final int from, final int to, final int count) {
        if (from < 0 || to < 0 || !isInRange(count, 0, to - from + 1)) throw new IllegalArgumentException();
        List<String> generated = new ArrayList<>(count);
        // TODO 
        for(int i= 0 ; i <10;i++){
            Thread tt = new Thread(){
                @Override
                public void run() {
                    while(true){
                        int x = from + (int)(Math.random() * ((to - from) + 1));
                        String xx = KanjiLib.convert(x);
                        String ans =x+", "+xx;
                        synchronized (generated){
                            if(generated.size()>=count)
                                return;
                            boolean cont = true;
                            for(int i = 0 ; i<generated.size();i++){
                                if(generated.get(i).equals(ans)) {
                                    cont = false;
                                    break;
                                }
                            }
                            if(cont){
                                generated.add( ans);
                            }
                        }

                    }
                }
            };
            tt.start();
            try {
                tt.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        return generated;
    }

    private static boolean isInRange(int count, int from, int to) {
        return from <= count && count <= to;
    }

    public static void main(String[] args) {
        List<String > a = generate(1,100,100);
        System.out.println(a.size());
        //zomaze sworad gamoaq
        // System.out.println(generate(1,100,100));
        // listic
    }
}