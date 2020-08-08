import java.util.Calendar;

/**
 * Esta classe trabalha com numeros aleatorios
 */
public class Random {
	
	 private long p = 2147483648L;
	 private long m = 843314861;
	 private long a = 453816693;
	 
	 private long xi = 1023;
	 
	 public Random(){
		 setSementeCalendario();
	 }
	 
	 public Random (int semente){
		 xi = semente;
	 }
	 
	 public void setSemente(int semente){
		 xi = semente;
	 }
	 
	 public void setSementeCalendario(){
		 xi = Calendar.getInstance().getTimeInMillis();
	 }
	 
	 public double getRand(){
		 xi =  (a + m * xi) % p;
		 double d = xi;
		 return d / p;
	  
	 }
	 
	 public int getIntRand(int max){
		 double d = getRand() * max;
		 return (int) d;
	 }
	 
	 public int getIntRand(int min, int max){ 
		 double d = getRand();
		 d = (d * (max - min)) + min;
		 return (int) d;
	 }
}