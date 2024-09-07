import java.util.*;
import java.io.*;
public class Main implements Serializable{
	static Scanner s=new Scanner(System.in);
	static ArrayList<int[]> freeSpaces=new ArrayList<>();
	int[][]grid=new int[4][4];
	
	public void random2or4(int[] selectedCell){
		Random rand=new Random();
		int temp[]=new int []{2,4};
		int twoOrfour=rand.nextInt(2);
		grid[selectedCell[0]][selectedCell[1]]=temp[twoOrfour];
	}
	
	public int[] randomFreeSpace(){
		Random rand=new Random();
		int randomIndex=rand.nextInt(freeSpaces.size());
		int[] selectedCell=freeSpaces.get(randomIndex);
		freeSpaces.remove(randomIndex);
		return selectedCell;
	}
	
	public boolean getFreeSpace(){
		freeSpaces.clear();
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(grid[i][j]==2048) return false;
				if(grid[i][j]==0) freeSpaces.add(new int[]{i,j});
			}
		}
		return true;
	}
	
	public void print(){
		System.out.println("+-------+");
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(j==0) System.out.print("|"+grid[i][j]+" ");
				else if(j==3) System.out.print(grid[i][j]+"|");
				else System.out.print(grid[i][j]+" ");
			}
			System.out.println("");
		}
		System.out.print("+-------+");
	}
	
	public void left(){
		for(int i=0;i<4;i++){
			List<Integer>temp=new ArrayList<>();
			
			for(int j=0;j<4;j++){
				if(grid[i][j]!=0) temp.add(grid[i][j]);
			}
			while(temp.size()<4){
				temp.add(0);
			}
			
			for(int j=0;j<3;j++){
				if(temp.get(j)==0 && temp.get(j+1)!=0){
					temp.set(j,temp.get(j+1));
					temp.set(j+1,0);
				}
				else if(temp.get(j)==0 && temp.get(j+1)==0){
					int p=j+2;
					while(p<=3){
						if(temp.get(p)!=0){
							temp.set(j,temp.get(p));
							temp.set(p,0);
							break;
						}
						p++;
					}
					if(p==4) break;
				}
				else if(temp.get(j)==temp.get(j+1)){
					temp.set(j,2*temp.get(j));
					temp.set(j+1,0);
				}
			}
			
			for(int j=0;j<4;j++){
				grid[i][j]=temp.get(j);
			}
		}
	}
	
	public void right(){
		for(int i=0;i<4;i++){
			List<Integer>temp=new ArrayList<>();
			
			for(int j=3;j>=0;j--){
				if(grid[i][j]!=0) temp.add(grid[i][j]);
			}
			while(temp.size()<4){
				temp.add(0);
			}
			
			for(int j=0;j<3;j++){
				if(temp.get(j)==0 && temp.get(j+1)!=0){
					temp.set(j,temp.get(j+1));
					temp.set(j+1,0);
				}
				else if(temp.get(j)==0 && temp.get(j+1)==0){
					int p=j+2;
					while(p<=3){
						if(temp.get(p)!=0){
							temp.set(j,temp.get(p));
							temp.set(p,0);
							break;
						}
						p++;
					}
					if(p==4) break;
				}
				else if(temp.get(j)==temp.get(j+1)){
					temp.set(j,2*temp.get(j));
					temp.set(j+1,0);
				}
			}
			int k=0;
			for(int j=3;j>=0;j--){
				grid[i][j]=temp.get(k++);
			}
		}
	}
	public void up(){
		for(int j=0;j<4;j++){
			List<Integer>temp=new ArrayList<>();
			
			for(int i=0;i<4;i++){
				if(grid[i][j]!=0) temp.add(grid[i][j]);
			}
			while(temp.size()<4){
				temp.add(0);
			}
			
			for(int i=0;i<3;i++){
				if(temp.get(i)==0 && temp.get(i+1)!=0){
					temp.set(i,temp.get(i+1));
					temp.set(i+1,0);
				}
				else if(temp.get(i)==0 && temp.get(i+1)==0){
					int p=i+2;
					while(p<=3){
						if(temp.get(p)!=0){
							temp.set(i,temp.get(p));
							temp.set(p,0);
							break;
						}
						p++;
					}
					if(p==4) break;
				}
				else if(temp.get(i)==temp.get(i+1)){
					temp.set(i,2*temp.get(i));
					temp.set(i+1,0);
				}
			}
			
			for(int i=0;i<4;i++){
				grid[i][j]=temp.get(i);
			}
		}
	}
	
	public void down(){
		for(int j=0;j<4;j++){
			List<Integer>temp=new ArrayList<>();
			
			for(int i=3;i>=0;i--){
				if(grid[i][j]!=0) temp.add(grid[i][j]);
			}
			while(temp.size()<4){
				temp.add(0);
			}
			
			for(int i=0;i<3;i++){
				if(temp.get(i)==0 && temp.get(i+1)!=0){
					temp.set(i,temp.get(i+1));
					temp.set(i+1,0);
				}
				else if(temp.get(i)==0 && temp.get(i+1)==0){
					int p=i+2;
					while(p<=3){
						if(temp.get(p)!=0){
							temp.set(i,temp.get(p));
							temp.set(p,0);
							break;
						}
						p++;
					}
					if(p==4) break;
				}
				else if(temp.get(i)==temp.get(i+1)){
					temp.set(i,2*temp.get(i));
					temp.set(i+1,0);
				}
			}
			int k=0;
			for(int i=3;i>=0;i--){
				grid[i][j]=temp.get(k++);
			}
		}
	}
	public void start(){
		boolean b=true;
		while(b){
			b=getFreeSpace();
			if(!b) continue;
			random2or4(randomFreeSpace());
			print();
			System.out.println("\nLeft(l)"+" "+"Right(r)"+" "+"Up(u)"+" "+"Down(d)"+" "+"Quit(q)");
			char ch=s.next().charAt(0);
			switch(ch){
				case 'l':
					left();
					break;
				case 'r':
					right();
					break;
				case 'u':
					up();
					break;
				case 'd':
					down();
					break;
				case 'q':
					b=false;
					break;
			}
		}
	}
	public static void main(String [] args){
		System.out.println("WELCOME TO 2048 GAME");
		boolean b=true;
		Main m=new Main();
		while(b){
			System.out.println("1.Play\n2.Resume\n3.Exit");
			int choice=s.nextInt();
			if(choice==3){
				try{
					b=false;
					FileOutputStream fout=new FileOutputStream("log.txt");
					ObjectOutputStream out=new ObjectOutputStream(fout);
					out.writeObject(m);
					out.flush();
					out.close();
					System.out.println("Log saved...");
				}
				catch(Exception e){ 
					System.out.println(e);
				}
			}
			else if(choice==2){
				try{
					FileInputStream fin=new FileInputStream("log.txt");
					ObjectInputStream in=new ObjectInputStream(fin);
					m=(Main)in.readObject();
					m.start();
				}
				catch(Exception e){ 
					System.out.println("No Game saved");
				}
			}
			else{
				m.start();
			}
		}
	}
}