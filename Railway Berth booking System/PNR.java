import java.util.*;
class PNR{
	HashMap<Integer, List<Passenger>> pnrs=new HashMap<>();
	int number=0;
	public int generatePNR(){
		Random rand=new Random();
		int max=99999, min=10000;
		return rand.nextInt(max-min+1)+min;
	}
}