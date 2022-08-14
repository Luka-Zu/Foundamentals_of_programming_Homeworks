package fop.w9colony;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class PenguinColony {

    private HashSet<Penguin> penguins;

    public PenguinColony(HashSet<Penguin> penguins) {
        this.penguins = penguins;
    }

    public HashSet<Penguin> getPenguins() {
        return penguins;
    }

    public void setPenguins(HashSet<Penguin> penguins) {
        this.penguins = penguins;
    }

    public void uniteColonies(PenguinColony otherColony) {
        Iterator<Penguin> pengu = otherColony.getPenguins().iterator() ;
        for (Iterator<Penguin> it = pengu; it.hasNext(); ) {
            Penguin e = it.next();
            penguins.add(e);


        }
        otherColony.getPenguins().clear();
    }

    public PenguinColony splitColony(Predicate<? super Penguin> pred) {
        HashSet<Penguin> others = new HashSet<>();
        for(Penguin e : penguins){
            if(pred.test(e)){
                others.add(e);
            }

        }
        for(Penguin e: others){
            penguins.remove(e);
        }

        return new PenguinColony(others);
    }

    public Penguin findFirstFriend(LinkedList<Penguin> penguinFriends) {
        for(Penguin e : penguinFriends){
            for (Penguin t : penguins){
                if(e.equals(t))
                    return e;
            }
        }
        return null;
    }

    public boolean canFeedPenguinsWithProperty(Predicate<? super Penguin> pred, Set<Fish> fishes) {
        for(Penguin e: penguins){
            if(pred.test(e)){
                boolean isThereFishForThatPenguin = false;
                for(Fish x: fishes){
                    if(e.getFavoriteFish()==x)
                        isThereFishForThatPenguin=true;
                }
                if(!isThereFishForThatPenguin)
                    return false;
            }
        }
        return true;
    }

    public int computeSum(Function<? super Penguin, Integer> fun) {
        int x= 0;
        for( Penguin e : penguins){
            x= x+ fun.apply(e);
        }
        return x;
    }

}
