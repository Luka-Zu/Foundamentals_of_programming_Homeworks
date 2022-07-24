package pgdp.collections;
public class Checkout {
    private  final Queue<PenguinCustomer> queue;
    private  final Queue<FishyProduct> bandBeforeCashier;
    private  final Queue<FishyProduct> bandAfterCashier;
    public Checkout() {
        this.queue = new LinkedQueue<PenguinCustomer>();
        this.bandBeforeCashier = new LinkedQueue<FishyProduct>();
        this.bandAfterCashier = new LinkedQueue<FishyProduct>();
    }
    public int queueLength(){
        return queue.size();
    }
    public void serveNextCustomer(){
        if(queue.isEmpty()){
            return;
        }
        PenguinCustomer firstInRow = queue.dequeue();
        if(firstInRow==null){
            return;
        }
        int priceToPay = 0;
        Stack<FishyProduct> thingsPenguinNeedsToPayFor= firstInRow.getProducts();
        while (!thingsPenguinNeedsToPayFor.isEmpty()){
            FishyProduct prod = thingsPenguinNeedsToPayFor.pop();
            priceToPay += prod.getPrice();
            bandBeforeCashier.enqueue(prod);
        }
        while (!bandBeforeCashier.isEmpty()){
            bandAfterCashier.enqueue(bandBeforeCashier.dequeue());
        }
        firstInRow.takeAllProductsFromBand(bandAfterCashier);
        firstInRow.pay(priceToPay);
    }
    @Override
    public String toString() {
        return "Checkout[" + "queue=" + queue + ", bandBeforeCashier=" + bandBeforeCashier + ", bandAfterCashier=" + bandAfterCashier + ']';
    }
    public Queue<PenguinCustomer> getQueue() {
        return queue;
    }
    public Queue<FishyProduct> getBandBeforeCashier() {
        return bandBeforeCashier;
    }
    public Queue<FishyProduct> getBandAfterCashier() {
        return bandAfterCashier;
    }
}
