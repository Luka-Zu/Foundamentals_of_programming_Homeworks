package pgdp.collections;
public class PenguinCustomer {
    //ქუსთომერის კლასი
    //
    private final String  name;
    private int money;
    private final Stack<FishyProduct> products;
    //ამ მეთოდით პინგვინი მიდის ყველაზე პატარა რიგიან ჩექაუთთან

    public void goToCheckout(PenguinSupermarket market){
        Checkout penguinGoesHere = market.getCheckoutWithSmallestQueue();
        penguinGoesHere.getQueue().enqueue(this);
    }
    //კონსტრუქტორი
    public PenguinCustomer(String name, int money) {
        if(name==null){
            ExceptionUtil.illegalArgument("Penguin must have name...");
        }
        if(money<0 ){
            ExceptionUtil.illegalArgument("Penguin must have money in order to buy stuff !");
        }
        this.name = name;
        this.money = money;
        this.products=new LinkedStack<FishyProduct>();
    }
    //ნივთის აღება
    public void addProductToBasket(FishyProduct product){
        products.push(product);
    }
    //ყველა ნივთის დადება
    public void placeAllProductsOnBand(Queue<FishyProduct> basket){
        StackConnector<FishyProduct> aa= new StackConnector<FishyProduct>(products);
        QueueConnector<FishyProduct> bb= new QueueConnector<FishyProduct>(basket);
        DataStructureLink<FishyProduct> transfer = new DataStructureLink<FishyProduct>(aa,bb);
        transfer.moveAllFromAToB();
    }
    //ყველა ნივთის აღება
    public void takeAllProductsFromBand(Queue<FishyProduct> que){
        StackConnector<FishyProduct> aa= new StackConnector<FishyProduct>(products);
        QueueConnector<FishyProduct> bb= new QueueConnector<FishyProduct>(que);
        DataStructureLink<FishyProduct> transfer = new DataStructureLink<FishyProduct>(bb,aa );
        transfer.moveAllFromAToB();
    }
    public int getMoney() {
        return money;
    }
    //გადახდა აქ ვითვალისწინებთ რამდენიმე დეტალს ,რომ გადასახდელი თანხა ვერიქნება უარყოფითი
    // და ეს თანხსა არუნდა აღემატებოდეს პინგვინსი ბიუჯეტს
    public void pay(int b){
        if(money<0 || b<0 || b>money) {
            ExceptionUtil.illegalArgument("The amount must not be negative and the available money must not be negative as well ");
        }
        money=money-b;

    }
    @Override
    public String toString() {
        return "PenguinCustomer[" + "name='" + name + ", money=" + money + ", products=" + products + ']';
    }
    public String getName() {
        return name;
    }
    public Stack<FishyProduct> getProducts() {
        return products;
    }
}
