package complexmatrix;

public class testcomp{
	public static void main(String[] args) {
		Complex g=new Complex(2,-18);
		Complex h=new Complex(-20,5);
		Complex l=new Complex(0,5);
		Complex j=new Complex();
		Complex x=new Complex();
		Complex y=new Complex();
		Complex z=new Complex();
		j=g.add(h);
		x=g.subtract(h);
		y=g.multiply(h);
		z=g.divide(h);
		System.out.println(g.toString());
		System.out.println(h.toString());
		System.out.println(j.toString());
		System.out.println(x.toString());
		System.out.println(y.toString());
		System.out.println(h.getMagnitude());
		System.out.println(h.compareTo(l));
	}
}