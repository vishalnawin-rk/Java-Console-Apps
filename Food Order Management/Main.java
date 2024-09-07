import java.util.*;
import java.io.*;
public class Main{
	static Scanner s=new Scanner(System.in);
	static HashMap<Integer, String> Lmap=new HashMap<>();
	static LinkedHashMap<Integer, Integer> FOmap=new LinkedHashMap<>();
	static HashMap<Integer, String> PCmap=new HashMap<>();
	static HashMap<Integer, String> OrderDetailsmap=new HashMap<>();
	static int orderId=0;

	public synchronized static int payment(){
		PCmap.put(1,"COD");
		PCmap.put(2,"UPI");
		PCmap.put(3,"Cards");
		System.out.println("Enter Payment Methods");
		System.out.println("1.COD\n2.UPI\n3.Cards");
		int Paymentchoice=s.nextInt();
		return Paymentchoice;
	}

	public static String generateFile(){
		String allformat="abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb=new StringBuilder();
		Random r=new Random();
		for(int i=0;i<5;i++){
			char ch=allformat.charAt(r.nextInt(36));
			sb.append(ch);
		}
		return sb.toString();
	}
	public static void order(){
		double totalPrice=0;
		Menu m=new Menu();
		boolean y=true;
		do{
			m.displayMenu();
			System.out.println("Enter the ID of Food to order: ");
			int FOid=s.nextInt();
			System.out.println("Enter quantity: ");
			int quantity=s.nextInt();
			if(quantity<=m.getQuantity(FOid)){
				FOmap.put(FOid,quantity);
				System.out.println((Menu.Quantimap.get(FOid)-quantity));
				Menu.Quantimap.put(FOid,(Menu.Quantimap.get(FOid)-quantity));
				totalPrice+=quantity*m.getPrice(FOid);
				System.out.println("Total Price: "+totalPrice);
			}
			else{
				System.out.println("Quantity Not Available");
			}
			System.out.println("1.Order More\n2.Nah Iam done");
			int choice=s.nextInt();
			if(choice==2) y=false;
		} while(y);
		int Paymentchoice=payment();
		orderId++;
		String fileLoc="./Orders/"+generateFile()+".txt";
		try{
			FileWriter fw=new FileWriter(fileLoc);
			fw.write("Order ID: "+orderId+"\n");
			for(Map.Entry<Integer,Integer> e: FOmap.entrySet()){
				int id=e.getKey();
				for(int i=0;i<m.allFood.size();i++){
					if(m.allFood.get(i).Fid==id){
						fw.write("Order Food ID: "+m.allFood.get(i).Fid+"\n");
						fw.write("Order Food: "+m.allFood.get(i).name+"\n");
						fw.write("Order Quantity: "+FOmap.get(id)+"\n");
						fw.write("Price: "+m.allFood.get(i).price*FOmap.get(id)+"\n");
					} 
				}
			}
			fw.write("Total Price: "+totalPrice+"\n");
			fw.write("Payment Method: "+PCmap.get(Paymentchoice)+"\n");
			fw.close();
		}
	    catch(Exception e){
			System.out.println(e);
		}
		OrderDetailsmap.put(orderId,fileLoc);
		try{
			FileWriter fw=new FileWriter("ListOfOrders.txt",true);
			fw.write(orderId+","+fileLoc+"\n");
			fw.close();
		} 
		catch(Exception e){
			System.out.println(e);
		}
		System.out.println("Successfully Order Placed");
		System.out.println("Order ID: "+orderId);
	}
	public static String LocFile(String id){
		try{
			FileReader fr=new FileReader("ListOfOrders.txt");
			BufferedReader br=new BufferedReader(fr);
			String curr;
			String data[];
			while((curr=br.readLine())!=null){
				data=curr.split(",");
				if(data[0].equals(id)){
					return data[1];
				}
			}
			br.close();
			fr.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return "error";
	}
	public static void details(){
		System.out.println("Enter Order ID");
		String id=s.next();
		String loc=LocFile(id);
		if(loc.equals("error")){
			System.out.println("Invalid Order ID");
		}
		else{
			try{
				FileReader fw=new FileReader(loc);
				BufferedReader br=new BufferedReader(fw);
				String curr;
				while((curr=br.readLine())!=null){
					System.out.println(curr);
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		
	}
	public static void updateQuantity(String loc){
		try{
			FileReader fr=new FileReader(loc);
			BufferedReader br=new BufferedReader(fr);
			String curr,FoodID="";
			while((curr=br.readLine())!=null){
				System.out.println(curr);
				if(curr.contains("Order Food ID: ")){
					FoodID=curr;
					FoodID=FoodID.replace("Order Food ID: ","");
					System.out.println(FoodID);
					int FID=Integer.valueOf(FoodID);
					System.out.println(FID);
					br.readLine();
					curr=br.readLine();
					String quantity=curr;
					quantity=quantity.replace("Order Quantity: ","");
					int quan=Integer.valueOf(quantity);
					Menu.Quantimap.put(FID,Menu.Quantimap.get(FID)+quan);
				}
			}
			br.close();
			fr.close();
		}
		catch(Exception e){
			System.out.println("string");
			System.out.println(e);
		}
	}
	public static void removeFile(String loc){
		try{
			FileReader fr=new FileReader("ListOfOrders.txt");
			BufferedReader br=new BufferedReader(fr);
			FileWriter fw=new FileWriter("ListOfOrders.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			String current;
			while((current=br.readLine())!=null){
				if(!current.contains(loc)){
					bw.write(current+"\n");
				}
			}
			bw.close();
			fw.close();
			br.close();
			fr.close();
			// File oldFile=new File(loc);
			// oldFile.delete();
			System.out.println("Cancelled Successfully");
		}
		catch(Exception e){
			System.out.println("1");
			System.out.println(e);
		}
	}	
	public static void cancel(){
		System.out.println("Enter Order ID");
		String id=s.next();
		String loc=LocFile(id);
		if(loc.equals("error")){
			System.out.println("Invalid Order ID");
		}
		else{
			updateQuantity(loc);
			removeFile(loc);	
		}
	}
	public static void main(String [] args){
		boolean y=true;
		while(y){
			System.out.println("\n**********Food Order**********");
			System.out.println("1.New User\n2.Existing User");
			int choice=s.nextInt();
			if(choice==1){
				Login l=new Login();
				if(l.status){
					Lmap.put(l.Pid,l.password);
					try{
						FileWriter fw=new FileWriter("./User Credentials/"+"userPass.txt",true);
						fw.write(l.Pid+","+l.password+"\n");
						fw.close();
					}
					catch(Exception e){
						System.out.println(e);
					}
					System.out.println("Created Successfully");
					System.out.println("User ID: "+l.Pid);
					y=false;
				}
				else{
					System.out.println("Password Error: (Specify Password in required format)");
				}
			}
			else{
				System.out.println("Enter User ID: ");
				String Uid=s.next();
				System.out.println("Enter Password: ");
				String pass=s.next();
				try{
					FileReader fr=new FileReader("./User Credentials/"+"userPass.txt");
					BufferedReader br=new BufferedReader(fr);
					String curr;
					String [] data;
					while((curr=br.readLine())!=null){
						data=curr.split(",");
						if(data[0].equals(Uid) && data[1].equals(pass)){
							System.out.println("Login Successful");
							y=false;
						}
					}
					br.close();
					fr.close();
				}
				catch(Exception e){
					System.out.println(e);
				}
				if(y)System.out.println("Invalid Credentials");
			}
		}
		y=true;
		while(y){
			System.out.println("1.Order Food\n2.Cancel Order\n3.Order Details\n4.Exit");
			int choice=s.nextInt();
			switch(choice){
				case 1:
					order();
					break;
				case 2:
					cancel();
					break;
				case 3:
					details();
					break;
				case 4:
					y=false;
					break;
			}
		}
	}
}