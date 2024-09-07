import java.util.*;
class TicketBooking{
	static Queue<Integer> Lower=new LinkedList<>();
	static Queue<Integer> Middle=new LinkedList<>();
	static Queue<Integer> Upper=new LinkedList<>();
	static Queue<Integer> SU=new LinkedList<>();
	static Queue<Integer> RAC=new LinkedList<>();
	static Queue<Integer> WL=new LinkedList<>();
	static HashMap<Integer,String> berthType=new HashMap<>();
	

	
	public TicketBooking(){
		Lower.offer(1);
		Lower.offer(4);
		Lower.offer(9);
		Lower.offer(12);
		
		Middle.offer(2);
		Middle.offer(5);
		Middle.offer(10);
		Middle.offer(13);
		
		Upper.offer(3);
		Upper.offer(6);
		Upper.offer(11);
		Upper.offer(14);
		
		SU.offer(8);
		SU.offer(16);
		
		RAC.offer(7);
		RAC.offer(7);
		RAC.offer(15);
		RAC.offer(15);
		
		WL.offer(-1);
		WL.offer(-2);
	}
		public static void printTable(){
			for(int i=1;i<=16;i++){
				if(Lower.contains(i)) berthType.put(i,"LB");
				else if(Middle.contains(i)) berthType.put(i,"MB");
				else if(Upper.contains(i)) berthType.put(i,"UB");
				else if(SU.contains(i)) berthType.put(i,"SU");
				else berthType.put(i,"SL");
			}
			
			System.out.println("PNR\tBerth no.\tStatus\tBerth\tName\tAge");
			int k=1;
			for(int i=1;i<=16;i++){
				String temp="AVL";
				if(i==7 || i==15){
					temp="RAC"+k++;
					System.out.println("----\t"+i+"\t\t"+temp+"\t"+berthType.get(i)+"\t"+"----\t----");
					temp="RAC"+k++;
					System.out.println("----\t"+i+"\t\t"+temp+"\t"+berthType.get(i)+"\t"+"----\t----");
				}
				else System.out.println("----\t"+i+"\t\t"+temp+"\t"+berthType.get(i)+"\t"+"----\t----");
			}
		}
	
}