
public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		int b = 20;
		if (a>b) {
			a = a-1;
			if (a>b) {
				a=b+(-1);
			} else {
				a=b+(-2);
			}
			b=b*2;
		}else if (a<b) {
			a = a+1;
			if (a<b) {
				a=b;
			} else {
				a=a+10;
			}
			b=b*10;
			a=a*10;
		}else {
			a = a*1;
			if (a==b) {
				a=a+1;
			} 
			if(a>10){
				b=b+1;
			}
		}
		
	}

}
