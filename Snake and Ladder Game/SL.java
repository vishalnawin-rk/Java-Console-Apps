import java.util.*;
import java.io.*;
public class SL implements Serializable{
	static Scanner s=new Scanner(System.in);
	Random r=new Random();
	int arr[][]=new int[10][10];
	HashMap<String, Integer> NP=new HashMap<>();
	HashMap<Integer, String> PN=new HashMap<>();
	Queue<String> q=new LinkedList<>();
	HashMap<Integer, Integer> snake=new HashMap<>();
	HashMap<Integer, String> SPN=new HashMap<>();
	HashMap<Integer, String> LPN=new HashMap<>();
	HashMap<Integer, Integer> ladder=new HashMap<>();
	
	public void initLadder(){
		ladder.put(4,25);
		ladder.put(13,46);
		ladder.put(33,49);
		ladder.put(50,69);
		ladder.put(42,63);
		ladder.put(62,81);
		ladder.put(74,92);
		
		LPN.put(4,"L1");
		LPN.put(13,"L2");
		LPN.put(33,"L3");
		LPN.put(50,"L4");
		LPN.put(42,"L5");
		LPN.put(62,"L6");
		LPN.put(74,"L7");
		
		LPN.put(25,"L1");
		LPN.put(46,"L2");
		LPN.put(49,"L3");
		LPN.put(69,"L4");
		LPN.put(63,"L5");
		LPN.put(81,"L6");
		LPN.put(92,"L7");

	}
	
	public void initSnake(){
		snake.put(40,3);
		snake.put(43,18);
		snake.put(27,5);
		snake.put(54,31);
		snake.put(89,53);
		snake.put(66,45);
		snake.put(76,58);
		snake.put(99,41);
		
		SPN.put(40,"S1");
		SPN.put(43,"S2");
		SPN.put(27,"S3");
		SPN.put(54,"S4");
		SPN.put(89,"S5");
		SPN.put(66,"S6");
		SPN.put(76,"S7");
		SPN.put(99,"S8");
		
		SPN.put(3,"S1");
		SPN.put(18,"S2");
		SPN.put(5,"S3");
		SPN.put(31,"S4");
		SPN.put(53,"S5");
		SPN.put(45,"S6");
		SPN.put(58,"S7");
		SPN.put(41,"S8");
	}
	
	public void initGame(){
		int num=100;	
		for(int i=0;i<10;i++){
			if(i%2==0){
				for(int j=0;j<10;j++){
					arr[i][j]=num--;
				}
			}
			else{
				for(int j=9;j>=0;j--){
					arr[i][j]=num--;
				}
			}
		}
		
		initSnake();
		initLadder();
	}
	public void print(){
		boolean flag=false;
		System.out.println("");
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				for(Map.Entry<String, Integer> e: NP.entrySet()){
					if(e.getValue()==arr[i][j]){
						System.out.print(e.getKey());
						flag=true;
					}
				}
				if(flag){
					System.out.print("\t");
					flag=false;
					continue;
				}
				if(SPN.containsKey(arr[i][j])){
					System.out.print(SPN.get(arr[i][j])+"\t");
					continue;
				}
				else if(LPN.containsKey(arr[i][j])){
					System.out.print(LPN.get(arr[i][j])+"\t");
					continue;
				}
				else System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public void getUser(){
		System.out.println("Enter Number of Users: ");
		int n=s.nextInt();
		for(int i=1;i<=n;i++){
			System.out.println("Enter the name of User-"+i);
			String name=s.next();
			NP.put(name,1);
			PN.put(1,name);
			q.offer(name);
		}
		print();
	}
	
	public void play(){
		while(q.size()>0){
			String name=q.poll();
			q.offer(name);
			System.out.println("Roll for User-"+name);
			System.out.println("1.ROll\n2.Exit");
			int choice=s.nextInt();
			if(choice==1){
				int pos=NP.get(name);
				PN.remove(pos);
				int dice=r.nextInt(6)+1;
				System.out.println("Dice:- "+dice);
				int temp=pos;
				pos+=dice;
				if(pos==100){
					System.out.println("User-"+name+" WON");
					break;
				}
				else if(pos>100){
					pos=temp;
					print();
					continue;
				}
				else if(ladder.containsKey(pos)) pos=ladder.get(pos);
				else if(snake.containsKey(pos)) pos=snake.get(pos);
				NP.put(name,pos);
				PN.put(pos,name);
				print();
			}
			else return;
			
		}
		
		
	}
	public static void main(String [] args){
		boolean y=true;
		while(y){
			System.out.println("********SNL********");
			System.out.println("1.play\n2.Resume\n3.Exit");
			int choice=s.nextInt();
			SL sl=null;
			switch(choice){
				case 1:
					sl=new SL(); 
					sl.initGame();
					sl.getUser();
					sl.play();
					break;
				case 2:
					try{
						FileInputStream fin=new FileInputStream("saved.txt");
						ObjectInputStream in=new ObjectInputStream(fin); 
						SL old=(SL)in.readObject();
						old.play();
					}
					catch(Exception e){
						System.out.println("No Game saved");
					}
					break;
				case 3:
					y=false;
					
					try{
						FileOutputStream fout=new FileOutputStream("saved.txt");
						ObjectOutputStream out=new ObjectOutputStream(fout);
						out.writeObject(sl);
						out.close();
						System.out.println("Successfully Saved");
					}
					catch(Exception e){
						System.out.println(e);
					}
					break;
			}
		}
		
		
		
	}
}