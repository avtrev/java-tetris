
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int count = 0;
		autoDown(count);
		

		System.exit(0);
	}
	public static void autoDown(int c) {

				try {
					println("sleeping "+c);
					c++;
					Thread.sleep(1000);
	    			autoDown(c);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		}
	public static void println(Object obj) {
		System.out.println(obj);
	}
}
