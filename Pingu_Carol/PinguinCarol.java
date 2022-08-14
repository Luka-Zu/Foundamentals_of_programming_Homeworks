package pgdp.carol;

import static pgdp.MiniJava.*;

public class PinguinCarol {

	public static void main(String[] args) {
		char miss;
		int x,y,view,ice;
		int width,height;
		width=readInt("Enter the playing field width:");
		height=readInt("Enter pitch height:");
		if(width<1 || height<1){
			write("The width and height of the playing field must be greater than zero.");
		}else{
			int[][] field= new int[width][height];
			for(int i=0;i<width;i++){
				for(int j=0; j<height;j++){
					field[i][j]=randomInt(-1,9);
				}

			}
			printPlayground (field);
			x= readInt("Starting position x:");
			y= readInt("Starting position y:");
			view=readInt("Direction of view at the beginning:");
			ice=readInt("Ice blocks at the beginning:");

			if(x<0 || x>width-1 || y<0 || y>height-1 || ice<0 || ice>10 || view<0||view>3){
				write("Invalid start values.");
			}else{
				miss ='&';
				while(miss!='e') {
					miss = readChar("Enter instruction:");
					if(miss != 'a' && miss !='l' &&  miss !='s' &&miss !='n' &&miss !='p' &&miss!='e' && miss!='r') {
						write("Unknown instruction!");
					}
					if(miss=='a'){
						printPlayground(field,x,y,view,ice);
					}
					if(miss=='r'){
						view--;
						if(view==-1){
							view=3;
						}
					}
					if(miss=='l'){
						view++;
						if(view==4){
							view=0;
						}
					}
					if(miss=='s'){
						if(view==0){
							if(x==width-1) {
								write("Carol cannot leave the field.");
							}else{
								if(field[x+1][y]-field[x][y]==-1 || field[x+1][y]-field[x][y]==0 || field[x+1][y]-field[x][y]==1){
									x++;
								}else{
									write("Carol cannot go to the next field because the difference in height is too great.");
								}
							}
						}

						if(view==1){
							if(y==height-1) {
								write("Carol cannot leave the field.");
							}else{
								if(field[x][y+1]-field[x][y]==-1 || field[x][y+1]-field[x][y]==0 || field[x][y+1]-field[x][y]==1){
									y++;
								}else{
									write("Carol cannot go to the next field because the difference in height is too great.");
								}
							}
						}

						if(view==2){
							if(x==0) {
								write("Carol cannot leave the field.");
							}else{
								if(field[x-1][y]-field[x][y]==-1 || field[x-1][y]-field[x][y]==0 || field[x-1][y]-field[x][y]==1){
									x--;
								}else{
									write("Carol cannot go to the next field because the difference in height is too great.");
								}
							}


						}
						if(view==3){
							if(y==0) {
								write("Carol cannot leave the field.");
							}else{
								if(field[x][y-1]-field[x][y]==-1 || field[x][y-1]-field[x][y]==0 || field[x][y-1]-field[x][y]==1){
									y--;
								}else{
									write("Carol cannot go to the next field because the difference in height is too great.");
								}
							}
						}




					}
					if(miss=='n'){
						if(ice==10){
							write("Carol can't take a block of ice, she's already carrying ten.");
						}
						if(field[x][y]==-1){
							write("Carol cannot take blocks of ice in the water.");
						}
						if(x==width-1 && view==0){
							write("Carol cannot take blocks of ice off the field.");
						}else{
							if(view==0 && field[x+1][y]==-1){
								write("Carol can't take a block of ice, there aren't any left.");
							}
							if(view==0 && field[x+1][y]!=-1 &&ice<10&&field[x][y]!=-1){
								field[x+1][y]--;
								ice++;
							}

						}
						if(y==height-1 && view==1){
							write("Carol cannot take blocks of ice off the field.");
						}else{
							if(view==1 && field[x][y+1]==-1){
								write("Carol can't take a block of ice, there aren't any left.");
							}
							if(view==1 && field[x][y+1]!=-1 &&ice<10&&field[x][y]!=-1){
								field[x][y+1]--;
								ice++;
							}
						}
						if(x==0 && view==2){
							write("Carol cannot take blocks of ice off the field.");
						}else{
							if(view==2 && field[x-1][y]==-1){
								write("Carol can't take a block of ice, there aren't any left.");
							}
							if(view==2 && field[x-1][y]!=-1 &&ice<10&&field[x][y]!=-1){
								field[x-1][y]--;
								ice++;
							}
						}
						if(y==0 && view==3){
							write("Carol cannot take blocks of ice off the field.");
						}else{
							if(view==3 && field[x][y-1]==-1){
								write("Carol can't take a block of ice, there aren't any left.");
							}
							if(view==3 && field[x][y-1]!=-1 &&ice<10&&field[x][y]!=-1){
								field[x][y-1]--;
								ice++;
							}
						}


					}
					if(miss=='p'){
						if(ice==0){
							write("Carol can't lay a block of ice because she isn't carrying one.");
						}
						if(field[x][y]==-1){
							write("Carol cannot lay blocks of ice in the water.");
						}
						if(x==width-1 && view==0){
							write("Carol cannot put blocks of ice off the field of play.");
						}
						if(x>=0 && x<width-1 && view==0 && field[x+1][y]==9){
							write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
						}
						if(x>0 && x<=width-1 && view==2 && field[x-1][y]==9){
							write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
						}
						if(y>=0 && y<height-1 && view==1 && field[x][y+1]==9){
							write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
						}
						if(y>0 && y<=height-1 && view==3 && field[x][y-1]==9){
							write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
						}
						if(x>=0 && x<width-1 && view==0 && field[x+1][y]!=9 &&ice>=1 &&field[x][y]!=-1){
							ice--;
							field[x+1][y]++;
						}
						if(x>0 && x<=width-1 && view==2 && field[x-1][y]!=9&&ice>=1&&field[x][y]!=-1){
							ice--;
							field[x-1][y]++;
						}
						if(y>=0 && y<height-1 && view==1 && field[x][y+1]!=9&&ice>=1&&field[x][y]!=-1){
							ice--;
							field[x][y+1]++;
						}
						if(y>0 && y<=height-1 && view==3 && field[x][y-1]!=9&&ice>=1&&field[x][y]!=-1){
							ice--;
							field[x][y-1]++;
						}



						if(x==0 && view==2){
							write("Carol cannot put blocks of ice off the field of play.");
						}
						if(y==height-1 && view==1){
							write("Carol cannot put blocks of ice off the field of play.");
						}
						if(y==0 && view==3){
							write("Carol cannot put blocks of ice off the field of play.");
						}






					}



				}
			}
		}



	}




}
