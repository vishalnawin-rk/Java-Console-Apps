import java.util.*;
class parkingSlot{
	public static int row=0,col=0;
	public static int grid[][]=new int[25][5];
	public static Queue<int []> avail=new LinkedList<>();
	public static HashMap<String,String> pLoc=new HashMap<>();
	public static HashMap<String, int[]> refresh=new HashMap<>();
}