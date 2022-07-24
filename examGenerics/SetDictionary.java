package exam;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class SetDictionary<K, V> implements Dictionary<K, V>  {
    Set<Pair<K, V>> entries =new HashSet<>();

    @Override
    public Option<V> get(K key) {
        if(entries.size()==0)
            return Option.none();
        for(Pair<K, V> x : entries){
            if(x.first.equals(key))
                return Option.some(x.second);
        }
        return Option.none();
    }

    @Override
    public V get(K key, V defaultValue) {
        if(entries.size()==0)
            return defaultValue;
        for(Pair<K, V> x : entries){
            if(x.first.equals(key))
                return x.second;
        }
        return defaultValue;
    }

    @Override
    public boolean containsKey(K key) {
        if(entries.size()==0)
            return false;
        for(Pair<K, V> x : entries){
            if(x.first.equals(key))
                return true;
        }
        return false;
    }

    @Override
    public boolean put(K key, V value) {
        if(this.containsKey(key))
            return false;
        Pair<K,V> adder = new Pair<>(key,value);
        entries.add(adder);
        return true;
    }

    @Override
    public boolean update(K key, V value) {
        if(!this.containsKey(key))
            return false;
        for(Pair<K, V> x : entries){
            if(x.first.equals(key)) {
                x.second = value;
                return true;
            }
        }
        return true;
    }

    @Override
    public void clear(K key) {
        Pair<K, V> y = null;
        for(Pair<K, V> x : entries){
            if(x.first.equals(key)) {
                y=x;
                break;
            }
        }
        if(y!=null){
            entries.remove(y);
        }
    }

    @Override
    public Stream<K> keyStream() {
        List<K> toReturn = new LinkedList<>();
        for(Pair<K, V> x : entries){
            toReturn.add(x.first);
        }

        return toReturn.stream();
    }

    @Override
    public Stream<V> valueStream() {
        List<V> toReturn = new LinkedList<>();
        for(Pair<K, V> x : entries){
            toReturn.add(x.second);
        }

        return toReturn.stream();
    }

}
