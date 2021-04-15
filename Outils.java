public class Outils{
	
	public static void pause(long temps){
		//System.out.println("Début...");
		
		long start = System.currentTimeMillis();
		
		while((System.currentTimeMillis() - start) < temps){
			// on attend 
		}
		
		//System.out.println("...fin.");
		
	}

}
