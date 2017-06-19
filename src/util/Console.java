package util;

import java.util.Scanner;
/**
 * @author lhries
 * 
 * - O console foi levemente modificado por mim (Kelvin). -
 */

public class Console {
	
	public static String scanString()
	{
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextLine());
	}
	
	public static int scanInt()
	{
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextInt());		
	}

	public static double scanDouble()
	{
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextDouble());		
	}

	public static float scanFloat()
	{
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextFloat());		
	}

	public static boolean scanBoolean()
	{
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextBoolean());		
	}
	
	public static char scanChar()
	{
		Scanner scanner = new Scanner (System.in);
		return(scanner.next().charAt(0));				
	}
        
        public static long scanLong()
        {
            Scanner scanner = new Scanner (System.in);
            return (scanner.nextLong());
        }
	
}