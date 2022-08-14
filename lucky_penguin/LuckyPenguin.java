package pgdp.luckypenguin;

import static pgdp.MiniJava.*;

public class LuckyPenguin {
	public static void main(String[] args) {

		int n=readInt("Number of penguins:");
		while(n<2){
			n=readInt("Number of penguins should be >1:");
		}
		int m=readInt("Starting fish per penguin:");
		while(m<=0){
			m=readInt("Starting fish should be >0:");
		}
		int[] gasulebi = new int[n];
		int[] penguin = new int[n];
		int gagrdzeleba;
		for(int i= 0; i<n;i++){
			penguin[i]=m;
			gasulebi[i]=0;
		}
		int[] table = new int[9];
		for(int j= 0; j<9;j++){
			table[j]=0;//j+3 is the number on table
		}
		writeBoard(table[0],table[1],table[2],table[3],table[4],table[5],table[6],table[7],table[8]);
		int x;
		int motamasheebi=n;
		while(motamasheebi>1){
			for (int i = 0; i < n; i++) {
				if(motamasheebi!=1){
				if (penguin[i] != 0) {
					write("It's penguin " + i + " turn:");
					int dice1 = dice();
					int dice2 = dice();

					x = dice1 + dice2;
					write(dice1 + " + " + dice2 + " = " + x + " was rolled.");
					if (x - 3 == 9) {
						write("King Penguin! You win all the fish on the board!");
						penguin[i] = penguin[i] + table[0] + table[1] + table[2] + table[3] + table[4] + table[5] + table[6] + table[7] + table[8];
						write("You now have " + penguin[i] + " fishes!");
						for (int k = 0; k < 9; k++) {
							table[k] = 0;
						}

					}
					if (x - 3 == -1) {
						write("Lucky penguin! You win all fish on the board except F7!");
						penguin[i] = penguin[i] + table[0] + table[1] + table[2] + table[3] + table[5] + table[6] + table[7] + table[8];

						write("You now have " + penguin[i] + " fishes!");
						for (int c = 0; c < 4; c++) {
							table[c] = 0;
						}
						for (int t = 5; t < 9; t++) {
							table[t] = 0;
						}

					}
					if (x - 3 == 4) {
						write("Wedding! You give a fish and place it on F7.");
						table[4] = table[4] + 1;
						penguin[i] = penguin[i] - 1;
						if(penguin[i]==1){
							write("You now have " + penguin[i] + " fish!");
						}else {
							write("You now have " + penguin[i] + " fishes!");
						}

						if(penguin[i]==0){
							motamasheebi--;
							write("You have lost all fish, so you can no longer play!");
						}

					}
					if (x - 3 != 4 && x - 3 != -1 && x - 3 != 9) {
						if (table[x - 3] != 0) {
							write("They take the fish from F" + x+".");
							table[x - 3]--;
							penguin[i]++;
							write("You now have " + penguin[i] + " fishes!");



						}else {
							write("You put a fish on F" + x+".");
							table[x - 3]++;
							penguin[i]--;
							if(penguin[i]==1){
								write("You now have " + penguin[i] + " fish!");
							}else {
								write("You now have " + penguin[i] + " fishes!");
							}
							if(penguin[i]==0) {
								motamasheebi--;
								write("You have lost all fish, so you can no longer play!");
							}

						}
					}
					if(penguin[i]!=0) {
						gagrdzeleba = readInt("Enter 1 to exit now:");
						if (gagrdzeleba == 1) {
							motamasheebi--;
							gasulebi[i] = penguin[i];
							penguin[i] = 0;
						}
					}



					writeBoard(table[0], table[1], table[2], table[3], table[4], table[5], table[6], table[7], table[8]);
				}
			}
			}
		}







		int demiwinner=0;
		for(int y=0;y<n;y++){
			if(penguin[y]!=0)
				demiwinner=y;
		}

		if(motamasheebi==1){
			write("It's penguin "+demiwinner+" turn:");
			write("You are the last penguin to play! You win all the fish on the board!");

			penguin[demiwinner]=penguin[demiwinner] + table[0] + table[1] + table[2] + table[3] + table[5] + table[6] + table[7] + table[8]+table[4];




		}

		int demimax=gasulebi[0];
		for(int z=1;z<n;z++){
			if(demimax<gasulebi[z])
				demimax=gasulebi[z];
		}
		int winner;
		if(penguin[demiwinner]<demimax) {
			winner = demimax;
			write("The winning penguins with " + winner + " fishes:");
			for(int u=0;u<n;u++){
				if(gasulebi[u]==demimax){
					write("Penguin "+u);
				}
			}
		}
		if(penguin[demiwinner]>demimax) {
			winner = penguin[demiwinner];
			write("The winning penguins with " + winner + " fishes:");
			write("Penguin "+demiwinner);
		}
		if(penguin[demiwinner]==demimax){
			winner = demimax;
			write("The winning penguins with " + winner + " fishes:");
			for(int u=0;u<n;u++){
				if(gasulebi[u]==demimax){
					write("Penguin "+u);
				}
			}

			write("Penguin "+demiwinner);
		}

	}
}
