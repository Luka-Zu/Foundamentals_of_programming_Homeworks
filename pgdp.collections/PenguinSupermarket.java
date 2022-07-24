package pgdp.collections;

public class PenguinSupermarket {
    private Checkout[] checkouts;

    public Checkout[] getCheckouts() {
        return checkouts;
    }
    //კონკსტრუქტორი რომლითაც ვქმნი checkout ების ერრეის.
    public PenguinSupermarket(int n) {
        if(n<1){
            ExceptionUtil.illegalArgument("registers must be greater than 0!!");
        }
        checkouts=new Checkout[n];
        for(int i= 0 ; i<n;i++){
            checkouts[i]=new Checkout();
        }
    }
    //ამ მეთოდით ერთ-ერთ ჩექაუთს ვკეტავ, ვითვალისწინებ შემთხვევებს როცა მსგავსი ჩექაუთი არ არ სებობს
    //ამ ინდექსის წასაშლელად ვქმნი ახალ ერრეის და ძველს ვაკოპირებ ამაში, წაშლილი ელემენტის არ ჩათვლით
    public void closeCheckout(int index){
        if(index<0 || checkouts.length==1|| index>=checkouts.length){
            ExceptionUtil.illegalArgument("Checkout with this index does not exist");
        }
        Checkout closedCheckout=this.checkouts[index];
        Checkout[] temp = this.checkouts;
        this.checkouts= new Checkout[temp.length-1];
        for(int i=0; i<temp.length;i++){
            if(i<index){
                checkouts[i] = temp[i];
            }
            else if(i>index){
                checkouts[i-1] = temp[i];
            }
        }
        LinkedStack<PenguinCustomer> newLine =new LinkedStack<>();
        int t = closedCheckout.queueLength();
        for(int i = 0;i<t ; i++){
            newLine.push(closedCheckout.getQueue().dequeue());
        }
        for(int i = 0;i<t ; i++){
            newLine.pop().goToCheckout(this);
        }
    }
    //ამ ორი ლუპით ვიგებ ჯერ ყველაზე პატარა ქიუში რამდენი პინგვინი დგას
    // შემდეგ მეორე ლუპიტ ვიგებ ეგ ქიუ რომელ ჩექაუთზეა
    public Checkout getCheckoutWithSmallestQueue(){
        int min=checkouts[0].queueLength();
        for (int i=0;i<checkouts.length;i++) {
            if (checkouts[i].queueLength() < min) {
                min = checkouts[i].queueLength();
            }
        }
        for (int i=0;i<checkouts.length;i++) {
            if (checkouts[i].queueLength() == min) {
                return checkouts[i];
            }
        }
        return null;
    }
    //ლუბს ვატრიალებ ყველა ჩექაუთისთვის და ყველაში 1 ქასთომერს ვუშვებ ქიუდან
    public void serveCustomers(){
        for (int i=0;i< checkouts.length;i++){
            checkouts[i].serveNextCustomer();
        }
    }
//    @Override
//    public String toString() {
//        return "PenguinSupermarket{" + "checkouts=" + Arrays.toString(checkouts) + '}';
//    }
}
