package pgdp.strings;
public class MyString {
    private char [] data;
    private MyString next;
    // კონსტრუქტორი
    public MyString (char [] data){
        this.data=data;
    }
    //იგებს სიგრძეს
    public int length(){
        int counter=this.data.length;
        MyString current=this.next;
        while(current!=null){
            counter=counter+current.data.length;
            current=current.next;
        }
        return counter;
    }
    //ეს ბოლოს უწერს ახალ ელემენტს
    public void concat (char [] data){
        if(next==null){
            next=new MyString(data);
        }
        else{
            next.concat(data);
        }
    }
    // ამას გამოაქ string ად მთლიანი ლისთი
    public String toString (){
        char[] temp=this.auxiliary();
        return new String(temp);
    }
    // დამხმარე ფუნქცია ,რომელსაც ვიყენებ ბოლო 3 მეთოდში
    public char[] auxiliary(){
        MyString curr=this;
        char[] str= new char[this.length()];
        int p= 0;
        int i=0 ;
        while(curr!=null) {
            while(i<curr.data.length){
                str[p]= curr.data[i];
                p++;
                i++;
            }
            i=0;
            curr=curr.next;
        }
        return str;
    }
    //აქ ვქმნი ფუნქციას ,რომელიც მიხვდება ორი ლისთი ერთი და იგივე არის თუ არა
    public boolean equals (MyString other){
        if(other==null){
            return false;
        }
        if(this.length()!=other.length()){
            return false;
        }
        char[] str1=this.auxiliary();
        char[] str2=other.auxiliary();
        for (int i=0;i<this.length();i++){
            if(str1[i]!=str2[i]){
                return false;
            }
        }
        return true;
    }
    //ეს ფუქნცია გამოიტანს რომელ პოზიცია რომელიღაც ქერექთერი. თუ არ არის გამოაქვს -1
    public int indexOf (char c){
        char[] str=this.auxiliary();
        for(int i =0;i<this.length();i++){
            if(str[i]==c){
                return i;
            }
        }
        return -1;
    }
    //ეს ფუქნცია გამოიტანს რომელ პოზიცია რომელიღაც ქერექთერი ბოლოდან. თუ არ არის გამოაქვს -1
    public int lastIndexOf (char c){
        char[] str=this.auxiliary();
        for (int i=this.length()-1; i>=0;i--){
            if(str[i]==c){
                return i;
            }
        }
        return -1;
    }
}
