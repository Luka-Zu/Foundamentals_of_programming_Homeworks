package pgdp.collections;
public class LinkedQueue <T> implements Queue<T>{
    private List<T> firstQueue;
    @Override
    public int size() {
        if (firstQueue==null)
            return 0;
        return firstQueue.length();
    }
    @Override
    public boolean isEmpty() {
        return size() == 0 ;
    }
    @Override
    // ენქიუთი ლისტის თავში ვამატებ ელემენტს, აქ ის უნდა გავითვალისწინოთ რომ ასე გაკეთების უფლემა იმიტომ მაქვს რომ
    // დექიუში პირველი ელემენტის წაშლის მაგივრად ვშლი ბოლო ელემენტს

    public void enqueue(T o) {
        if(firstQueue==null){
            firstQueue=new List<T>(o);
            return;
        }
        List<T> old = firstQueue;
        firstQueue=new List<T>(o, old);
    }
    // აქ ვშლი ბოლო ელემენტს, ვიხილავ რამდენიმე შემთხვევას როცა ქიუ ცარიელია ან ზომა 1 ია
    @Override
    public T dequeue() {
        if (firstQueue == null) {
            return null;
        }
        if(size()==1){
            List<T> cur=firstQueue;
            firstQueue=null;
            return cur.getInfo();
        }
        List<T> currList = firstQueue.getNext();
        List<T> oneBefore = firstQueue;
        while(currList .getNext()!= null){
            oneBefore = currList;
            currList = currList.getNext();
        }
        oneBefore.delete();
        return currList.getInfo();
    }
}