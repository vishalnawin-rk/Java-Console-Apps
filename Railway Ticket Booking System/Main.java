import java.io.*;
import java.util.*;

public class Main implements Serializable {
    static Scanner s=new Scanner(System.in);
    /*
        There are 3 coaches - AC,Non-AC,Sitting
        Size of Coaches=2;
        WL size of Each Coaches=2;

    */
    int passengerID=0;
    HashMap<Integer,Passenger> passenger_list=new HashMap<>();
    TreeMap<String,Passenger> booked_seats=new TreeMap<>();
    Queue<String> avail_seats_AC=new PriorityQueue<>(
            List.of("A1","A2")
    );
    Queue<String> avail_seats_NAC=new PriorityQueue<>(
            List.of("NA1","NA2")
    );
    Queue<String> avail_seats_S=new PriorityQueue<>(
            List.of("S1","S2")
    );
    Queue<Passenger> waiting_list_AC=new LinkedList<>();
    Queue<Passenger> waiting_list_NAC=new LinkedList<>();
    Queue<Passenger> waiting_list_S=new LinkedList<>();

    public static void main(String[] args) {
        Main m;
        try{
            FileInputStream fin=new FileInputStream("saved.txt");
            ObjectInputStream objin=new ObjectInputStream(fin);
            m=(Main)objin.readObject();
        }
        catch (Exception e) {
            m=new Main();
        }
        System.out.println("JAXTON's RAILWAYS");
        boolean close=true;
        while(close){
            System.out.println("""
                1. Booking
                2. Availability checking
                3. Cancellation
                4. Prepare chart
                5. Quit""");

            int choice=s.nextInt();
            switch(choice){
                case 1:
                    System.out.println();
                    m.booking();
                    break;
                case 2:
                    System.out.println();
                    m.avail_Status();
                    break;
                case 3:
                    System.out.println();
                    m.cancel();
                    break;
                case 4:
                    System.out.println();
                    m.chart();
                    break;
                case 5:
                    m.save(m);
                    close=false;
            }
        }
    }

    private void save(Main m){
        try{
            FileOutputStream fout=new FileOutputStream("saved.txt");
            ObjectOutputStream objout=new ObjectOutputStream(fout);
            objout.writeObject(m);
            System.out.println("Saved...");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    private void chart() {
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n","S.No.","P ID","Name","Source","Des","Seat");
        int count=1;
        for(Map.Entry<String,Passenger> e: booked_seats.entrySet()){
            String seat=e.getKey();
            Passenger p=e.getValue();
            System.out.printf("%-10d %-10d %-10s %-10s %-10s %-10s%n",count++,p.id,p.name,p.source,p.destination,p.seat);
        }
        System.out.println();
    }


    public void avail_Status(){
        System.out.println("AC");
        if(avail_Status(1)){
            System.out.print("Seats: AVAILABLE - ");
            System.out.println(avail_seats_AC.size()+2-waiting_list_AC.size());
        }
        else{
            System.out.println("Seats: FULL !!!");
        }
        System.out.println("Non-AC");
        if(avail_Status(2)){
            System.out.print("Seats: AVAILABLE - ");
            System.out.println(avail_seats_NAC.size()+2-waiting_list_NAC.size());
        }
        else{
            System.out.println("Seats: FULL !!!");
        }
        System.out.println("Sitting");
        if(avail_Status(3)){
            System.out.print("Seats: AVAILABLE - ");
            System.out.println(avail_seats_S.size()+2-waiting_list_S.size());
        }
        else{
            System.out.println("Seats: FULL !!!");
        }
        System.out.println();
    }

    private void cancel() {
        System.out.println("Enter Passenger ID: ");
        int pid=s.nextInt();
        if(!passenger_list.containsKey(pid)){
            System.out.println("Invalid Passenger ID !!!");
            return;
        }
        Passenger p=passenger_list.get(pid);
        String seat=p.seat;
        booked_seats.remove(seat);
        passenger_list.remove(pid);

        System.out.println("Cancellation Successful...");
        System.out.println();
        waiting_List_conformation(seat);

    }

    private void waiting_List_conformation(String seat) {
        if(seat.charAt(0)=='A' &&  !waiting_list_AC.isEmpty()){
            Passenger p=waiting_list_AC.poll();
            booked_seats.put(seat,p);
            p.seat=seat;
            System.out.println("Passenger ID: "+p.id);
            System.out.println("STATUS: CNF");
            System.out.println("Seat: "+seat);
        }
        else if(seat.charAt(0)=='N' &&  !waiting_list_NAC.isEmpty()){
            Passenger p=waiting_list_NAC.poll();
            booked_seats.put(seat,p);
            System.out.println("Passenger ID: "+p.id);
            System.out.println("STATUS: CNF");
            System.out.println("Seat: "+seat);
        }
        else if(seat.charAt(0)=='S'&&  !waiting_list_AC.isEmpty()){
            Passenger p=waiting_list_AC.poll();
            booked_seats.put(seat,p);
            System.out.println("Passenger ID: "+p.id);
            System.out.println("STATUS: CNF");
            System.out.println("Seat: "+seat);
        }
        else if(seat.charAt(0)=='A'){
            avail_seats_AC.offer(seat);
        }
        else if(seat.charAt(0)=='N'){
            avail_seats_NAC.offer(seat);
        }
        else if(seat.charAt(0)=='S'){
            avail_seats_S.offer(seat);
        }
        System.out.println();
    }

    public boolean avail_Status(int choice){
        switch(choice){
            case 1:
                return !avail_seats_AC.isEmpty() || waiting_list_AC.size()<2;
            case 2:
                return !avail_seats_NAC.isEmpty() || waiting_list_NAC.size()<2;
            case 3:
                return !avail_seats_S.isEmpty() || waiting_list_S.size()<2;
            default:
                return false;
        }
    }
    private void booking() {
        System.out.println("""
                1. AC
                2. Non-AC
                3. Sitting
                4. Quit""");

        int choice=s.nextInt();
        System.out.println();
        if(!avail_Status(choice)){
            System.out.println("Seats are Full !!!");
            System.out.println();
            return;
        }
        Passenger p=new Passenger().add_Passenger(++passengerID);
        switch(choice){
            case 1:
                if(!avail_seats_AC.isEmpty()){
                    String seat=avail_seats_AC.poll();
                    p.add_Seat(seat);
                    passenger_list.put(p.id,p);
                    booked_seats.put(seat,p);
                }
                else if(waiting_list_AC.size()<2){
                    p.add_Seat("AC-WL");
                    passenger_list.put(p.id,p);
                    waiting_list_AC.offer(p);
                }
                else{
                    System.out.println("AC Seats is Full !!!");
                }
                break;
            case 2:
                if(!avail_seats_NAC.isEmpty()){
                    String seat=avail_seats_NAC.poll();
                    p.add_Seat(seat);
                    passenger_list.put(p.id,p);
                    booked_seats.put(seat,p);
                }
                else if(waiting_list_NAC.size()<2){
                    p.add_Seat("Non-AC-WL");
                    passenger_list.put(p.id,p);
                    waiting_list_NAC.offer(p);
                }
                else{
                    System.out.println("Non-AC Seats is Full !!!");
                }
                break;
            case 3:
                if(!avail_seats_S.isEmpty()){
                    String seat=avail_seats_S.poll();
                    p.add_Seat(seat);
                    passenger_list.put(p.id,p);
                    booked_seats.put(seat,p);
                }
                else if(waiting_list_S.size()<2){
                    p.add_Seat("S-WL");
                    passenger_list.put(p.id,p);
                    waiting_list_S.offer(p);
                }
                else{
                    System.out.println("Sitting Seats is Full !!!");
                }
                break;
        }
        System.out.println();
        System.out.println("Booked Successfully...");
        print_passenger_details(p);

    }

    private void print_passenger_details(Passenger p) {
        System.out.println();
        System.out.printf("%-10s %-10s %-10s %-10s %-10s%n","P ID","Name","Source","Des","Seat");
        System.out.printf("%-10d %-10s %-10s %-10s %-10s%n",p.id,p.name,p.source,p.destination,p.seat);
        System.out.println();
    }
}
