package pgdp.collections;

public class StackConnector<T> implements DataStructureConnector<T>{

    private final Stack<T> unifier;

    public StackConnector(Stack<T> unifier) {
        this.unifier =  unifier;
    }

    @Override
    public boolean hasNextElement() {
        return !(unifier.isEmpty());
    }

    @Override
    public void addElement(T o) {
        unifier.push( o );
    }

    @Override
    public T removeNextElement() {
        return unifier.pop();
    }
}
