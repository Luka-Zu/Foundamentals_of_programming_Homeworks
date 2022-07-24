package exam;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ListDictionary<K, V> implements Dictionary<K, V> {
    private List<K> keys =new LinkedList<>();
    private List<V> values= new LinkedList<>();

    @Override
    public Option<V> get(K key) {
        if(keys.size()==0)
            return Option.none();
        for(int i=0;i<keys.size();i++){
            if(key.equals(keys.get(i)))
                return Option.some(values.get(i));
        }
        return Option.none();
    }

    @Override
    public V get(K key, V defaultValue) {
        if(keys.size()==0)
            return defaultValue;
        for(int i=0;i<keys.size();i++){
            if(key.equals(keys.get(i)))
                return values.get(i);
        }
        return defaultValue;
    }

    @Override
    public boolean containsKey(K key) {
        for(int i=0;i<keys.size();i++){
            if(key.equals(keys.get(i)))
                return true;
        }
        return false;
    }

    @Override
    public boolean put(K key, V value) {
        if(this.containsKey(key))
            return false;
        keys.add(key);
        values.add(value);
        return true;
    }

    @Override
    public boolean update(K key, V value) {
        if(!this.containsKey(key))
            return false;
        for(int i=0;i<keys.size();i++){
            if(key.equals(keys.get(i))){
                values.add(i,value);
                values.remove(i+1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear(K key) {
        if(keys.size()==0)
            return;
        int t=-1;
        for(int i=0;i<keys.size();i++) {
            if (key.equals(keys.get(i))) {
                t=i;
                break;
            }
        }
        if(t!= -1){
            keys.remove(t);
            values.remove(t);
        }

    }

    @Override
    public Stream<K> keyStream() {
        return keys.stream();
    }

    @Override
    public Stream<V> valueStream() {
        return values.stream();
    }
}
