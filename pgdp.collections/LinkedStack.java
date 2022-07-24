package pgdp.collections;
public class LinkedStack <T> implements Stack<T>{
    private List<T> firstElement;
    // აქ ახალ ელემენტს ვაგდებ სტაქში პირველ ელემენტად

    @Override
    public void push(T t) {
        if(firstElement==null){
            firstElement=new List<T>(t);
            return;
        }
        List<T> old = firstElement;
        firstElement=new List<T>(t, old);
    }
    // ბოლოს დამატებულ ელემენტს ვიღებ სტაქიდან
    @Override
    public T pop(){
        if (firstElement == null) {
            return null;
        }
        T result = firstElement.getInfo();
        firstElement = firstElement.getNext();
        return result;
    }
    @Override
    public int size() {
        if (firstElement==null)
            return 0;
        return firstElement.length();
    }
    @Override
    public boolean isEmpty() {
        return size() == 0 ;
    }
}
