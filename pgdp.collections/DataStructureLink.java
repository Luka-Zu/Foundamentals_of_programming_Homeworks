package pgdp.collections;

public class DataStructureLink<T> {
    private final DataStructureConnector<T> dataOne;
    private final DataStructureConnector<T> dataTwo;
    public DataStructureLink(DataStructureConnector<T> dataOne, DataStructureConnector<T> dataTwo) {
        this.dataOne = dataOne;
        this.dataTwo = dataTwo;
    }
    public boolean moveNextFromAToB(){
        if(!dataOne.hasNextElement()){
            return false;
        }else{
            dataTwo.addElement(dataOne.removeNextElement());
            return true;
        }
    }
    public void moveAllFromAToB(){
        while(dataOne.hasNextElement()){
            dataTwo.addElement(dataOne.removeNextElement());
        }
    }
}
