package exam;

public class Option<T> {
    private T obj;
    private Option(T obj) {
        this.obj = obj;
    }
    private Option(){
        obj=null;
    }
    public static <T> Option<T> some(T some){
        return new Option<>(some);
    }
    public static <T> Option<T> none(){
        return new Option<>();
    }
    public T get() throws EmptyOptionException {
        if(obj==null) {
            throw new EmptyOptionException();
        }
        return obj;
    }
    public T getOrDefault(T opt){
        if(obj==null)
            return opt;
        return obj;
    }
    public boolean isNone(){
        return obj==null;
    }

}
