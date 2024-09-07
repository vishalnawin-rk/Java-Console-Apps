import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class Main extends parkingSlot{
	static Scanner s=new Scanner(System.in);
	static HashMap<String, Vehicle> VnumDetail=new HashMap<>();
	
	public String bookSlocct(String number){
		String area="";
		int arr[]=new int[2];
		if(col<=5){
			arr[0]=row;
			arr[1]=col;
			refresh.put(number,arr);
			area=(char)('A'+row)+"";
			area+=col+"";
			col++;
		}
		else if(row<=25){
			arr[0]=row;
			arr[1]=col;
			refresh.put(number,arr);
			row++;
			col=0;
			area=(char)('A'+row)+"";
			area+=col+"";
		}
		else{
			int arr1[]=avail.poll();
			refresh.put(number,arr1);
			area=(char)('A'+arr1[0])+"";
			area+=arr1[1]+"";
		}
		return area;
	}
	public void park(){
		System.out.println("*************************");
		System.out.println("1.New Vehicle\n2.Old Vehicle");
		System.out.println("*************************");
		System.out.println();
		int choice=s.nextInt();
		if(choice==1){
			System.out.println();
			System.out.println("Enter Vehicle number: ");
			String number=s.next();
			System.out.println("Enter type of Vehicle (2.Two wheeler 4.Four wheeler)");
			int type=s.nextInt();
			System.out.println("Checking if Slot is Available.....");
			if(row<=25 && col<=5 || avail.size()>0){
				System.out.println("Free Slot AVAILABLE...");
				LocalTime lt=LocalTime.now();
				String start = lt.format(DateTimeFormatter.ofPattern("HH:mm"));
				String slot=bookSlot(number);
				Vehicle v=new Vehicle(number,type,start,slot);
				VnumDetail.put(number,v);
				pLoc.put(number,slot);
				System.out.println("Vehicle Parked Successfully");
				System.out.println("Vehicle Parked Slot: "+slot);
				System.out.println("Vehicle Check-in Time: "+start);
				System.out.println();
				
			}
			else System.out.println("Slot is FULL...");
		}
		else{
			System.out.println();
			System.out.println("Enter Vehicle number: ");
			String number=s.next();
			if(VnumDetail.containsKey(number)){
				Vehicle v=VnumDetail.get(number);
				System.out.println("Checking if Slot is Available.....");
				if(row<=25 && col<=5 || avail.size()>0){
					System.out.println("Free Slot AVAILABLE...");
					LocalTime lt=LocalTime.now();
					String start = lt.format(DateTimeFormatter.ofPattern("HH:mm"));
					String slot=bookSlot(number);
					v.start=start;
					pLoc.put(number,slot);
					System.out.println("Vehicle Parked Successfully");
					System.out.println("Vehicle Parked Slot: "+slot);
					System.out.println("Vehicle Check-in Time: "+start);
					System.out.println();
				}
				else System.out.println("Slot is FULL...");
			}
			else System.out.println("Wrong vehicle Number");
		}
	}
	
	public void checkSlot(){
		System.out.println("Enter Vehicle number: ");
		String number=s.next();
		if(pLoc.containsKey(number)){
			System.out.println("Parked Slot for Vehicle: "+number+" :"+pLoc.get(number));
		}
		else System.out.println("Invalid Vehicle number");
	}
	
	public void out(){
		System.out.println("Enter Vehicle number: ");
		String number=s.next();
		if(pLoc.containsKey(number)){
			Vehicle v=VnumDetail.get(number);
			LocalTime en=LocalTime.now();
			String end = en.format(DateTimeFormatter.ofPattern("HH:mm"));
			String startData[]=v.start.split(":");
			String endData[]=end.split(":");
			double hrs=(Integer.parseInt(endData[0])-Integer.parseInt(startData[0]));
			double mins=(Integer.parseInt(endData[1])-Integer.parseInt(startData[1]));
			System.out.println(hrs+" "+mins);
			double amount=0d;
			if(v.type==2){
				double Mamount=(1*mins)/6;
				amount+=10*hrs+Mamount;
			}
			else{
				double Mamount=(5*mins)/6;
				amount+=50*hrs+Mamount;
			}
			int temp[]=refresh.get(number);
			refresh.remove(number);
			avail.offer(temp);
			pLoc.remove(number);
			System.out.println("Vehicle checked Out Successful");
			System.out.println("Vehicle Check-Out Time: "+end);
			System.out.println("Amount generated is: "+amount);
		}
		else System.out.println("Invalid Vehicle number");
	}
	public static void main(String args[]){
		System.out.println("*******PARKING LOT*******");
		boolean y=true;
		Main m=new Main();
		while(y){
			System.out.println("*************************");
			System.out.println("1.Park Vehicle\n2.Checkout Vehicle\n3.Show Parked Slot\n4.Exit");
			System.out.println("*************************");
			int choice=s.nextInt();
			switch(choice){
				case 1:
					m.park();
					break;
				case 2:
					m.out();
					break;
				case 3:
					m.checkSlot();
					break;
				case 4:
					y=false;
					break;
			}
		}
	}
}