
import soot.G;

public class OddEvenwithSignLattice {
	
	public interface Element{
		public Element meet(Element x);
	}
	
	public static final Element BOTTOM = new Element(){

		public Element meet(Element x) {
			// TODO Auto-generated method stub
			return BOTTOM;
		}
		
		public String toString() {
			return "US";
			
		}		
	};
	
	public static final Element TOP = new Element(){

		public Element meet(Element x) {
			// TODO Auto-generated method stub
			return x;
		}
		
		public String toString() {
			return "UNDEF";
			
		}		
	};
	
	public static final Element POSITIVE_EVEN = new Element(){

		public Element meet(Element x) {
			// TODO Auto-generated method stub
			if (x == TOP || x == POSITIVE_EVEN) {
				return POSITIVE_EVEN;
			}else {
				return BOTTOM;
			}			
		}
		
		public String toString() {
			return "Positive_Even";
			
		}		
	};
	
	public static final Element POSITIVE_ODD = new Element(){

		public Element meet(Element x) {
			// TODO Auto-generated method stub
			if (x == TOP || x == POSITIVE_ODD) {
				return POSITIVE_ODD;
			}else {
				return BOTTOM;
			}			
		}
		
		public String toString() {
			return "Positive_Odd";
			
		}		
	};
	
	public static final Element NEGATIVE_EVEN = new Element(){

		public Element meet(Element x) {
			// TODO Auto-generated method stub
			if (x == TOP || x == NEGATIVE_EVEN) {
				return NEGATIVE_EVEN;
			}else {
				return BOTTOM;
			}			
		}
		
		public String toString() {
			return "Negative_Even";
			
		}		
	};
	
	public static final Element NEGATIVE_ODD = new Element(){

		public Element meet(Element x) {
			// TODO Auto-generated method stub
			if (x == TOP || x == NEGATIVE_ODD) {
				return NEGATIVE_ODD;
			}else {
				return BOTTOM;
			}			
		}
		
		public String toString() {
			return "Negative_Odd";
			
		}		
	};
	
	public static final Element POSITIVE = new Element(){

		public Element meet(Element x) {
			// TODO Auto-generated method stub
			if (x == TOP || x == POSITIVE) {
				return POSITIVE;
			}else {
				return BOTTOM;
			}			
		}
		
		public String toString() {
			return "Positive";
			
		}		
	};
	
	public static Element OfInt(int n) {
		G.v().out.println(n);
		if (n>0) {
			if (n%2 == 1) {
				return POSITIVE_ODD;
			} else {
				return POSITIVE_EVEN;
			}
		}else if(n < 0){
			if (n%2 == 1) {
				return 	NEGATIVE_ODD;	
			} else {
				return 	NEGATIVE_EVEN;
			}
		}else {
			return POSITIVE;
		}
	}
	
	public static Element add(Element y, Element z) {
		if (y == POSITIVE_EVEN && z == POSITIVE_EVEN) {
			return POSITIVE_EVEN;
		} else if(y == POSITIVE_EVEN && z == POSITIVE_ODD){
			return POSITIVE_ODD;
		} else if (y == POSITIVE_EVEN && z == NEGATIVE_EVEN) {
			return BOTTOM;
		}else if (y == POSITIVE_EVEN && z == NEGATIVE_ODD) {
			return BOTTOM;
		}else if (y == POSITIVE_ODD && z == POSITIVE_EVEN) {
			return POSITIVE_ODD;
		}else if (y == POSITIVE_ODD && z == POSITIVE_ODD) {
			return POSITIVE_EVEN;
		}else if (y == POSITIVE_ODD && z == NEGATIVE_EVEN) {
			return BOTTOM;
		}else if (y == POSITIVE_ODD && z == NEGATIVE_ODD) {
			return BOTTOM;
		}else if (y == NEGATIVE_EVEN && z == POSITIVE_EVEN) {
			return BOTTOM;
		}else if (y == NEGATIVE_EVEN && z == POSITIVE_ODD) {
			return BOTTOM;
		}else if (y == NEGATIVE_EVEN && z == NEGATIVE_EVEN) {
			return NEGATIVE_EVEN;
		}else if (y == NEGATIVE_EVEN && z == NEGATIVE_ODD) {
			return NEGATIVE_ODD;
		}else if (y == NEGATIVE_ODD && z == POSITIVE_EVEN) {
			return BOTTOM;
		}else if (y == NEGATIVE_ODD && z == POSITIVE_ODD) {
			return BOTTOM;
		}else if (y == NEGATIVE_ODD && z == NEGATIVE_EVEN) {
			return NEGATIVE_ODD;
		}else if (y == NEGATIVE_ODD && z == NEGATIVE_ODD) {
			return NEGATIVE_EVEN;
		}else if (y == POSITIVE) {
			return z;
		}else if (z == POSITIVE) {
			return y;
		}else if (y == BOTTOM || z == BOTTOM) {
			return BOTTOM;
		}else {
			return TOP;
		}
	}
	
	public static Element multi(Element y, Element z) {
		if(y == POSITIVE_EVEN && z == POSITIVE_EVEN) {
			return POSITIVE_EVEN;		
		}else if (y == POSITIVE_EVEN && z == POSITIVE_ODD) {
			return POSITIVE_EVEN;
		}else if (y == POSITIVE_EVEN && z == NEGATIVE_EVEN) {
			return NEGATIVE_EVEN;
		}else if (y == POSITIVE_EVEN && z == NEGATIVE_ODD) {
			return NEGATIVE_ODD;
		}else if (y == POSITIVE_ODD && z == POSITIVE_EVEN) {
			return POSITIVE_EVEN;
		}else if (y == POSITIVE_ODD && z == NEGATIVE_EVEN) {
			return NEGATIVE_EVEN;
		}else if (y == POSITIVE_ODD && z == POSITIVE_ODD) {
			return POSITIVE_ODD;
		}else if (y == POSITIVE_ODD && z == NEGATIVE_ODD) {
			return NEGATIVE_ODD;
		}else if (y == NEGATIVE_ODD && z == POSITIVE_EVEN) {
			return NEGATIVE_EVEN;
		}else if (y == NEGATIVE_ODD && z == NEGATIVE_EVEN) {
			return POSITIVE_EVEN;
		}else if (y == NEGATIVE_ODD && z == POSITIVE_ODD) {
			return NEGATIVE_ODD;
		}else if (y == NEGATIVE_ODD && z == NEGATIVE_ODD) {
			return POSITIVE_ODD;
		}else if (y == NEGATIVE_EVEN && z == POSITIVE_EVEN) {
			return NEGATIVE_EVEN;
		}else if (y == NEGATIVE_EVEN && z == NEGATIVE_EVEN) {
			return POSITIVE_EVEN;
		}else if (y == NEGATIVE_EVEN && z == POSITIVE_ODD) {
			return NEGATIVE_ODD;
		}else if (y == NEGATIVE_EVEN && z == NEGATIVE_ODD) {
			return POSITIVE_EVEN;
		}else if (y == BOTTOM || z == BOTTOM) {
			return BOTTOM;
		}else if (y == POSITIVE || z == POSITIVE) {
			return POSITIVE;
		}else {
			return TOP;
		}
	}
	
	public static Element def(Element c) {
		return c;
	}
	/**
	 * 
	 */
	public OddEvenwithSignLattice() {
		// TODO Auto-generated constructor stub
		
	}
	
}
