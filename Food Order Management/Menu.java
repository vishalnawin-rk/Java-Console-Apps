import java.util.*;
import java.io.*;
class Food{
	int Fid=0;
	String name;
	double price;
	int quantity;
	Food(int Fid, String name, double price, int quantity){
		this.Fid=Fid;
		this.name=name;
		this.price=price;
		this.quantity=quantity;
	}
}
public class Menu{
	static Scanner s=new Scanner(System.in);
	ArrayList<Food> allFood;
	static HashMap<Integer,Integer> Quantimap= new HashMap<>();
	public Menu(){
		allFood=new ArrayList<>();
		Quantimap.put(4,5);
		Quantimap.put(5,5);
		Quantimap.put(6,5);
		allFood.add(new Food(4,"Rice",100,Quantimap.get(4)));
		allFood.add(new Food(5,"Burger",200,Quantimap.get(5)));
		allFood.add(new Food(6,"Noodles",300,Quantimap.get(6)));
	}
	
	public double getPrice(int FOid){
		for(int i=0;i<allFood.size();i++){
			if(allFood.get(i).Fid==FOid) return allFood.get(i).price;
		}
		return 0;
	}
	public int getQuantity(int FOid){
		if(Quantimap.containsKey(FOid)){
			return Quantimap.get(FOid);
		}
		return 0;
	}
	public void displayMenu(){
		for(Food f: allFood){
			System.out.println("Food ID: "+f.Fid+"\t"+"Food Name: "+f.name+"\t"+"Food Price: "+f.price+"\t"+"Quantity"+Quantimap.get(f.Fid));
		}
	}
}