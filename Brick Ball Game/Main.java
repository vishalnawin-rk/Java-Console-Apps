import java.util.*;
public class Main{
	static Scanner s=new Scanner(System.in);
	static char grid[][];
	static int n;
	static int life;
	static int currX,currY;
	static int prevX,prevY;
	static int count;
	
	public void init(){
		System.out.println("Enter size");
		n=s.nextInt();
		grid=new char[n][n];
		currX=(n-1);
		currY=(n-1)/2;
		prevX=currX;
		prevY=currY;
		System.out.println("Enter Number of Bricks");
		int b=s.nextInt();
		count=b;
		int bX[]=new int[b];
		int bY[]=new int[b];
		// int bX[]={2,2,2,3,3,3};
		// int bY[]={2,3,4,2,3,4};
		// count=6;
		System.out.println("Enter positions of Bricks (X,Y)");
		for(int i=0;i<b;i++){
			bX[i]=s.nextInt();
			bY[i]=s.nextInt();
		}
		System.out.println("Enter Life");
		life=s.nextInt();
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i==0 || j==n-1|| j==0) grid[i][j]='w';
				else if(i==n-1){
					if(j==0 || j==n-1) grid[i][j]='w';
					else if(j==(n-1)/2) grid[i][j]='o';
					else grid[i][j]='g';
				}
			}
		}
		
		for(int i=0;i<bX.length;i++){
			grid[bX[i]][bY[i]]='1';
		}
		print();
	}
	public void print(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i==n-1){
					if(i==currX && j==currY) grid[i][j]='o';
					else if(j!=0 && j!=n-1) grid[i][j]='g';
				}
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void play(){
		boolean b=true;
		while(b){
			System.out.println("1.Play\n2.Exit");
			if(s.nextInt()==1){
				System.out.println("Enter direction: (S,L,R)");
				char ip=s.next().charAt(0);
				if(ip=='S'){
					for(int i=currX-1;i>=0;i--){
						if(grid[i][currY]=='1'){
							grid[i][currY]=' ';
							count--;
							break;
						}
					}
				}
				else if(ip=='L'){
					while(currX>0 && currY>0){
						if(grid[currX][currY]=='1'){
							grid[currX][currY]=' ';
							count--;
							break;
						}
						currX--;
						currY--;
					}
					System.out.println(currX +" "+currY);
					
					if(grid[currX][currY]=='w'){
						currY++;
						for(int j=currY;j<=n-1;j++){
							if(grid[currX][j]=='1'){
								grid[currX][j]=' ';
								count--;
								currY=j;
								break;
							}
							if(grid[currX][j]=='w'){
								currY=n-2;
								break;
							}
						}
					}
					//currX--;
					
					for(int i=currX+1;i<n-1;i++){
						if(grid[i][currY]=='1'){
							grid[i][currY]=' ';
							count--;
						}
					}
					currX=n-1;
					if(prevX!=currX || prevY!=currY){
						life--;
						prevX=currX;
						prevY=currY;
					} 
				}
				else{
					//currX--;
					//currY++;
					while(currX>0 && currY<=n-1){
						if(grid[currX][currY]=='1'){
							grid[currX][currY]=' ';
							count--;
							break;
						}
						if(grid[currX][currY]=='w') break;
						currX--;
						currY++;
					}
					System.out.println(currX +" "+currY);
					if(grid[currX][currY]=='w'){
						currY--;
						for(int j=currY;j>=0;j--){
							if(grid[currX][j]=='1'){
								grid[currX][j]=' ';
								count--;
								currY=j;
								break;
							}
							if(grid[currX][j]=='w'){
								currY=++j;
								break;
							}
						}
					}
					System.out.println(currX +" "+currY);
					for(int i=currX+1;i<n-1;i++){
						if(grid[i][currY]=='1'){
							grid[i][currY]=' ';
							count--;
						}
					}
					currX=n-1;
					if(prevX!=currX || prevY!=currY){
						life--;
						prevX=currX;
						prevY=currY;
					}
				}
				print();
				System.out.println();
				System.out.println("Life - "+life);
				if(count==0 || life==0) {
					b=false;
					System.out.println("Game Ended..");
				}
			}
			else b=false;
		}
		
	}
	public static void main(String [] args){
		Main m=new Main();
		m.init();
		m.play();
	}
}