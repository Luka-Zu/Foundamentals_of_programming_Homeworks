package pgdp.pinguart;
    
import static pgdp.MiniJava.*;

public class PinguArt {

	public static void main(String[] args) {
	int numbers[]=new int[8];

	int c=0;
	while(c<8){
		numbers[c]=readInt("Please enter a Pingu Art number:");
		c++;
	}
	// numbers[0] number[1]
	write("+---[PinguArt]---+");
	int t=0;
	int j;
	writeConsole("|");
	while(t<2) {
		j=0;
		while (j < 8) {
			if (numbers[t] % 10 == 0) {
				writeConsole(" ");
			}
			if (numbers[t] % 10 == 1) {
				writeConsole("-");
			}
			if (numbers[t] % 10 == 2) {
				writeConsole("~");
			}
			if (numbers[t] % 10 == 3) {
				writeConsole("P");
			}
			numbers[t] = numbers[t] / 10;

			j++;
		}
		t++;
	}
	writeConsole("|");
	write("");
	writeConsole("|");
	while(t<4) {
		j=0;

		while (j < 8) {
			if (numbers[t] % 10 == 0) {
				writeConsole(" ");
			}
			if (numbers[t] % 10 == 1) {
				writeConsole("-");
			}
			if (numbers[t] % 10 == 2) {
				writeConsole("~");
			}
			if (numbers[t] % 10 == 3) {
				writeConsole("P");
			}
			numbers[t] = numbers[t] / 10;
			j++;
		}
		t++;
	}
	writeConsole("|");
	write("");
	writeConsole("|");
	while(t<6) {
		j=0;
		while (j < 8) {
			if (numbers[t] % 10 == 0) {
				writeConsole(" ");
			}
			if (numbers[t] % 10 == 1) {
				writeConsole("-");
			}
			if (numbers[t] % 10 == 2) {
				writeConsole("~");
			}
			if (numbers[t] % 10 == 3) {
				writeConsole("P");
			}
			numbers[t] = numbers[t] / 10;
			j++;
		}
		t++;
	}
	writeConsole("|");
	write("");
	writeConsole("|");
	while(t<8) {
		j=0;
		while (j < 8) {
			if (numbers[t] % 10 == 0) {
				writeConsole(" ");
			}
			if (numbers[t] % 10 == 1) {
				writeConsole("-");
			}
			if (numbers[t] % 10 == 2) {
				writeConsole("~");
			}
			if (numbers[t] % 10 == 3) {
				writeConsole("P");
			}
			numbers[t] = numbers[t] / 10;
			j++;
		}
		t++;

	}
	writeConsole("|");

	write("");
	write("+----------------+");


	}
}
