import java.util.*;
import java.io.*;
public class Person{
	static Scanner s=new Scanner(System.in);
	static int Pid;
	String name="",address="";
	long phone;
	Person(){
		System.out.println("Enter name: ");
		name=s.next();
		System.out.println("Enter Address: ");
		address=s.next();
		System.out.println("Enter phone: ");
		phone=s.nextLong();
		Pid++;
	}
}