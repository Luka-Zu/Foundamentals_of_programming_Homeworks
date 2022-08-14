package pgdp.pinguracing;
import static pgdp.MiniJava.*; //Diesen Import nicht löschen! Do not delete this import!
public class PinguRacing {
    public static void main(String[] args) {
        int t, posA, posB, speA, speB;
        int d=0;
        speA = 0;
        speB = 0;
        posA = readInt("Alan starting position:");
        posB = readInt("Bjarne starting position:");
        t = readInt("Race duration:");
        while (t < 0) {
            t = readInt("Please do not enter a negative number:");
        }
        while( d < t/2){
            if(posA % 10 != 4 && posA % 10 != -4 ){
                speA=speA+7;
                if(speA>128){
                    speA=128;
                }
                posA=posA+speA;
            }else{
                speA=speA/2 +1;
                if(speA>128){
                    speA=128;
                }
                posA=posA+speA;
            }
            if(posB % 13 !=0){
                speB=speB+3;
                if(speB>128){
                    speB=128;
                }
                posB=posB+speB;
            }else{
                speB=speB*2 + 1;
                if(speB>128){
                    speB=128;
                }
                posB=posB+speB;
            }
            write("t = "+d);
            write("Alan position = "+posA+"; speed = "+speA);
            write("Bjarne position = "+posB+"; speed = "+speB);
            d++;
        }
        int t2=3*t;
        int t3=4*t;
        t2=t2/5;
        t3=t3/5;
        while(d<t){
            if(d==t2 || d==t3){
                speA=speA/4 ;
                posA=posA+speA;
            }else{
                speA=speA+1;
                if(speA>128){
                    speA=128;
                }
                posA=posA+speA;
            }
            if(d >=t-13){
                speB=speB/2 ;
                if(speB>128){
                    speB=128;
                }
                posB=posB+speB;
            }else{
                speB=speB-1;
                if(speB>128){
                    speB=128;
                }
                if(speB<0){
                    speB=0;
                }
                posB=posB+speB;
            }
            write("t = "+d);
            write("Alan position = "+posA+"; speed = "+speA);
            write("Bjarne position = "+posB+"; speed = "+speB);
            d++;
        }
        if(posA>posB){
            write("Alan wins!");
        }else if(posA==posB){
            write("Draw!");
        }else{
            write("Bjarne wins!");
        }
    }
}
