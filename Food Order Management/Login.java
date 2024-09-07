import java.util.*;
import java.util.regex.*;
public class Login{
	static Scanner s=new Scanner(System.in);
	static int Pid;
	String name="",address="";
	long phone;
	String password="";
	boolean status=false;
	Login(){
		System.out.println("Enter name: ");
		name=s.next();
		System.out.println("Enter Address: ");
		address=s.next();
		System.out.println("Enter phone: ");
		phone=s.nextLong();
		System.out.println("Enter your Password (atleast 1 Upper,Lower,@, size 8-12 ) :");
		password=s.next();
		if(password.length()>=8 && password.length()<=12){
			if(Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*@).+$",password)){
				Pid++;
				this.status=true;
			}
		}
	}
}
