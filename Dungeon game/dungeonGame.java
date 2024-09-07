import java.util.*;
public class dungeonGame{
	static Scanner s=new Scanner(System.in);
	static int min;
	static String path="";
	static int minMonster=0;
	static int minAdv=0;
	static HashSet<Integer> dangerPits=new HashSet<>();
	
	public static void printPath(int xA, int yA){
		xA++;
		yA++;
		System.out.println();
		System.out.print("START -> ("+ xA +","+ yA +") -> ");
		for(char ch: path.toCharArray()){
			if(ch=='R'){
				System.out.print("("+ xA+ ","+ ++yA +") -> ");
			}
			else if(ch=='D'){
				System.out.print("("+ ++xA +","+ yA +") -> ");
			}
			else if(ch=='L'){
				System.out.print("("+ xA +","+ --yA +") -> ");
			}
			else{
				System.out.print("("+ --xA +","+ yA +") -> ");
			}
		}
		System.out.print("END");
		System.out.println();
	}
	public static void solve(int m, int n, int xA, int yA, int xG, int yG, int visited[][], int count, String ds, boolean flag){
		if(xA==xG && yA==yG){
			if(count<min){
				min=count;
				path=ds;
			}
			return;
		}
		
		int check=xA*10+yA;
		if(!flag && dangerPits.contains(check)) return;
		
		if(xA<0 || xA>=m || yA<0 || yA>=n || visited[xA][yA]==1) return;
			
			visited[xA][yA]=1;
			solve(m,n,xA,yA+1,xG,yG,visited,count+1,ds+"R",flag); //right
			solve(m,n,xA+1,yA,xG,yG,visited,count+1,ds+"D",flag); //down
			solve(m,n,xA-1,yA,xG,yG,visited,count+1,ds+"U",flag); // up
			solve(m,n,xA,yA-1,xG,yG,visited,count+1,ds+"L",flag); // left
			visited[xA][yA]=0;
			
	}
	
	public static boolean minSteps(int m, int n, int xA, int yA, int xM, int yM, int xG, int yG, int visited[][], boolean flag){
		min=Integer.MAX_VALUE;
		if(minMonster==0 && xM!=-1) {
			solve(m,n,xM,yM,xG,yG,visited,0,"",flag);
			minMonster=min;
			min=Integer.MAX_VALUE;
		}
		solve(m,n,xA,yA,xG,yG,visited,0,"",false);
		minAdv=min;
		
		if(minAdv<=minMonster && minMonster!=0) return true;
		return false;
	}
	public static void main(String [] args){
		System.out.println("Enter rows and columns: ");
		int m=s.nextInt();
		int n=s.nextInt();
		
		int visited[][]=new int[m][n];
		
		System.out.println("Enter Position of Adventurer (X,Y): ");
		int xA=s.nextInt()-1;
		int yA=s.nextInt()-1;
		
		System.out.println("Enter Position of Gold (X,Y): ");
		int xG=s.nextInt()-1;
		int yG=s.nextInt()-1;
		
		int xM=-1,yM=-1,xT=-1,yT=-1;
		System.out.println("Are Monsters Present..??\n1.Yes\n0.No");
		int monsters=s.nextInt();
		if(monsters==1){
			System.out.println("Enter Position of Monster (X,Y): ");
			xM=s.nextInt()-1;
			yM=s.nextInt()-1;
		}
		
		System.out.println("Are Triggers Present..??\n1.Yes\n0.No");
		int triggers=s.nextInt();
		if(triggers==1){
			System.out.println("Enter Position of Triggers (X,Y): ");
			xT=s.nextInt()-1;
			yT=s.nextInt()-1;
		}
		
		System.out.println("Are Pits Present..??\n1.Yes\n0.No");
		int pits=s.nextInt();
		if(pits==1){
			System.out.println("Enter Number of Pits: ");
			int p=s.nextInt();
			System.out.println("Enter Position of Pits (X,Y): ");
			while(p>0){
				int a=s.nextInt()-1;
				int b=s.nextInt()-1;
				
				int temp=a*10+b;
				dangerPits.add(temp);
				p--;
			}
		}
		
		if(monsters==0 && triggers==0 && pits==0){
			minSteps(m,n,xA,yA,-1,-1,xG,yG,visited,false);
			System.out.println("Minimum number of Steps: "+minAdv);
			printPath(xA,yA);
		}
		
		if(monsters==1 && triggers==0 && pits==0){
			if(minSteps(m,n,xA,yA,xM,yM,xG,yG,visited,false)){
				System.out.println("Minimum number of Steps: "+minAdv);
				printPath(xA,yA);
			}
			else System.out.println("Adventure Died...No possible solution...");
		}
		
		else if(monsters==1 && triggers==1 && pits==0){
			minSteps(m,n,xA,yA,xM,yM,xT,yT,visited,false);
			int TriggerCount=minAdv;
			String TriggerPath=path;
			minSteps(m,n,xT,yT,xM,yM,xG,yG,visited,false);
			int minTrigger=minAdv;
			path=TriggerPath+path;
			System.out.println("Minimum number of Steps: "+(TriggerCount+minTrigger));
			printPath(xA,yA);
		}
		
		
		else if(monsters==0 && triggers==0 && pits==1){
			if(minSteps(m,n,xA,yA,-1,-1,xG,yG,visited,false)){
				System.out.println("Minimum number of Steps: "+minAdv);
				printPath(xA,yA);
			}
			else System.out.println("Adventure Died...No possible solution...");
		}
		
		else if(monsters==1 && triggers==0 && pits==1){
			if(minSteps(m,n,xA,yA,xM,yM,xG,yG,visited,true)){
				System.out.println("Minimum number of Steps: "+minAdv);
				printPath(xA,yA);
			}
			else System.out.println("Adventure Died...No possible solution...");
		}
		
		else{
			minSteps(m,n,xA,yA,xM,yM,xT,yT,visited,true);
			int TriggerCount=minAdv;
			String TriggerPath=path;
			minSteps(m,n,xT,yT,xM,yM,xG,yG,visited,true);
			int minTrigger=minAdv;
			path=TriggerPath+path;
			System.out.println("Minimum number of Steps: "+(TriggerCount+minTrigger));
			printPath(xA,yA);
		}
		
	}
}