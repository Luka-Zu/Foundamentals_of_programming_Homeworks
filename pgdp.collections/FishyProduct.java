package pgdp.collections;
public class FishyProduct{
    //პროდუქტის კლასი
    private final String name;
    private final int price;
    public FishyProduct(String name, int price) {
        if(price<=0 ){
            ExceptionUtil.illegalArgument("Price can not be negative or 0!");
        }
        if(name==null){
            ExceptionUtil.illegalArgument("Product must have name");
        }
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "FishyProduct[" + "name='" + name + ", price=" + price + ']';
    }
}
