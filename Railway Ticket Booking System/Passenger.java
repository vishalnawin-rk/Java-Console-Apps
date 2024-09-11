import java.io.Serializable;
import java.util.Scanner;

public class Passenger implements Serializable {
    static Scanner s=new Scanner(System.in);
    int id;
    String name;
    String source;
    String destination;
    String seat;

    public Passenger(){

    }
    public Passenger(int id,String name, String source, String destination){
        this.id=id;
        this.name=name;
        this.source=source;
        this.destination=destination;
    }

    public Passenger add_Passenger(int id){
        System.out.println("Enter Passenger Name: ");
        String name=s.next();
        System.out.println("Enter Passenger Source: ");
        String source=s.next();
        System.out.println("Enter Passenger Destination: ");
        String destination=s.next();

        return new Passenger(id,name,source,destination);
    }

    public void add_Seat(String seat){
        this.seat=seat;
    }

}
