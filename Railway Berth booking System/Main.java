import java.util.*;
public class Main{
	static Scanner s=new Scanner(System.in);
	static Queue<Passenger> currentRAC=new LinkedList<>();
	static Queue<Passenger> currentWL=new LinkedList<>();
	
	public void book(){
		System.out.println();
		List<Passenger> passengerList=new ArrayList<>();
		System.out.println("Enter no. of Tickets: ");
		int tickets=s.nextInt();
		for(int i=0;i<tickets;i++){
			System.out.println("Enter Name: ");
			String name=s.next();
			System.out.println("Enter Age: ");
			int age=s.nextInt();
			System.out.println("Enter Berth Preference (LB,MB,UB,SU): ");
			String BP=s.next();
		
			Passenger p=new Passenger(name,age,BP);
			new TicketBooking();
			//alloting berth
			
			if(!BP.equals("")){
				if(BP.equals("LB")){
					if(TicketBooking.Lower.size()>0){
						p.berth=BP;
						p.seat=TicketBooking.Lower.poll();
					}
					else{
						System.out.println("LB is FULL");
						System.out.println("1.Look for other Berths\t2.Don't look");
						if(s.nextInt()==1){
							if(TicketBooking.Middle.size()>0){
								p.berth="MB";
								p.seat=TicketBooking.Middle.poll();
							}
							else if(TicketBooking.Upper.size()>0){
								p.berth="UB";
								p.seat=TicketBooking.Upper.poll();
							}
							else if(TicketBooking.SU.size()>0){
								p.berth="SU";
								p.seat=TicketBooking.SU.poll();
							}
							else if(TicketBooking.RAC.size()>0){
								p.berth="RAC";
								p.seat=TicketBooking.RAC.poll();
								currentRAC.offer(p);
							}
							else if(TicketBooking.WL.size()>0){
								p.berth="WL";
								p.seat=TicketBooking.WL.poll();
								currentWL.offer(p);
							}
							else{
								System.out.println("+--------Waiting List is FULL---------+");
								return;
							}
						}
						else return;
					}
				}
				else if(BP.equals("MB")){
					if(TicketBooking.Middle.size()>0){
						p.berth="MB";
						p.seat=TicketBooking.Middle.poll();
					}
					else{
						System.out.println("MB is FULL");
						System.out.println("1.Look for other Berths\t2.Don't look");
						if(s.nextInt()==1){
							if(TicketBooking.Lower.size()>0){
								p.berth=BP;
								p.seat=TicketBooking.Lower.poll();
							}
							else if(TicketBooking.Upper.size()>0){
								p.berth="UB";
								p.seat=TicketBooking.Upper.poll();
							}
							else if(TicketBooking.SU.size()>0){
								p.berth="SU";
								p.seat=TicketBooking.SU.poll();
							}
							else if(TicketBooking.RAC.size()>0){
								p.berth="RAC";
								p.seat=TicketBooking.RAC.poll();
								currentRAC.offer(p);
							}
							else if(TicketBooking.WL.size()>0){
								p.berth="WL";
								p.seat=TicketBooking.WL.poll();
								currentWL.offer(p);
							}
							else{
								System.out.println("+--------Waiting List is FULL---------+");
								return;
							}
						}
						else return;
					}
				}
				else if(BP.equals("UB")){
					if(TicketBooking.Upper.size()>0){
						p.berth="UB";
						p.seat=TicketBooking.Upper.poll();
					}
					else{
						System.out.println("UB is FULL");
						System.out.println("1.Look for other Berths\t2.Don't look");
						if(s.nextInt()==1){
							if(TicketBooking.Lower.size()>0){
								p.berth=BP;
								p.seat=TicketBooking.Lower.poll();
							}
							else if(TicketBooking.Middle.size()>0){
								p.berth="MB";
								p.seat=TicketBooking.Middle.poll();
							}
							else if(TicketBooking.SU.size()>0){
								p.berth="SU";
								p.seat=TicketBooking.SU.poll();
							}
							else if(TicketBooking.RAC.size()>0){
								p.berth="RAC";
								p.seat=TicketBooking.RAC.poll();
								currentRAC.offer(p);
							}
							else if(TicketBooking.WL.size()>0){
								p.berth="WL";
								p.seat=TicketBooking.WL.poll();
								currentWL.offer(p);
							}
							else{
								System.out.println("+--------Waiting List is FULL---------+");
								return;
							}
						}
						else return;
					}
				}
				else if(BP.equals("SU")){
					if(TicketBooking.SU.size()>0){
								p.berth="SU";
								p.seat=TicketBooking.SU.poll();
							}
					else{
						System.out.println("SU is FULL");
						System.out.println("1.Look for other Berths\t2.Don't look");
						if(s.nextInt()==1){
							if(TicketBooking.Lower.size()>0){
								p.berth=BP;
								p.seat=TicketBooking.Lower.poll();
							}
							else if(TicketBooking.Middle.size()>0){
								p.berth="MB";
								p.seat=TicketBooking.Middle.poll();
							}
							else if(TicketBooking.Upper.size()>0){
								p.berth="UB";
								p.seat=TicketBooking.Upper.poll();
							}
							else if(TicketBooking.RAC.size()>0){
								p.berth="RAC";
								p.seat=TicketBooking.RAC.poll();
								currentRAC.offer(p);
							}
							else if(TicketBooking.WL.size()>0){
								p.berth="WL";
								p.seat=TicketBooking.WL.poll();
								currentWL.offer(p);
							}
							else{
								System.out.println("+--------Waiting List is FULL---------+");
								return;
							}
						}
						else return;
					}
				}
			}
			else{
				
			}
			passengerList.add(p);
		}
		
		// generating pnr
		PNR po=new PNR();
		int pnr=po.generatePNR();
		while(po.pnrs.containsKey(pnr)){
			pnr=po.generatePNR();
		}
		po.pnrs.put(pnr,passengerList);
		System.out.println("PNR - "+pnr);
		System.out.println("+--------Booking Successful---------+");
	}
	
	public void print(){
		System.out.println();
		System.out.println("Enter PNR number: ");
		int pnr=s.nextInt();
		
		PNR po=new PNR();
		if(po.pnrs.containsKey(pnr)){
			int id=1;
			System.out.println("PNR - "+pnr);
			System.out.println("S.No.\tName\t\tAge\tBooking Status\t\tCurrent Status\t\tBerth");
			for(Passenger p: po.pnrs.get(pnr)){
				String temp=(p.berth.equals("RAC") || p.berth.equals("WL"))?(p.berth):("CNF");
				System.out.println(id++ +"\t"+p.name+"\t\t"+p.age+"\t"+temp+"\t\t\t"+"CNF"+"\t\t\t"+""+p.seat+"/"+p.berth);
			}
		}
		else System.out.println("+------------Invalid PNR-------------+");
	}

	public void cancel(){
		//not yet developed
	}
	public static void main(String [] args){
		boolean y=true;
		Main m=new Main();
		while(y){
			System.out.println("+-----------------------------------+");
			System.out.println("|         JAXTON RAILWAYS           |");
			System.out.println("+-----------------------------------+");
			System.out.println("| 1.Book Ticket                     |");
			System.out.println("| 2.Print PNR details               |");
			System.out.println("| 3.Cancel Ticket                   |");
			System.out.println("| 4.Exit                            |");
			System.out.println("+-----------------------------------+");
			System.out.println();
			
			int choice=s.nextInt();
			switch(choice){
				case 1:
					m.book();
					break;
				case 2:
					m.print();
					break;
				case 3:
					//cancel();
					break;
				case 4:
					y=false;
					break;
			}
		} 
	}
}