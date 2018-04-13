import java.util.ArrayList;

public class App {
	public static void main(String[] args){
		Box b = new Box(1);
		
		Box c = b;
		
		c.a = 3;
		
		System.out.println(c.a);
		System.out.println(b.a);
		
		
		System.out.println("The a problem.");
		
		
		E2 obj = new E2(12);
		System.out.println(obj.getA() + " , "+obj.f(34));
		
		// Casting + Reference + Subclasses
		Son p = new Son();
		Parent s = (Parent) p;
		p.print();
		s.print();
		
		System.out.println(5+6+"&");
		
		
		
	}
}

class Box{
	int a;
	public ArrayList<String> str;
	
	public Box(int a){
		this.a= a;
		str = new ArrayList<String>();
		
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public ArrayList<String> getStr() {

		return str;
	}

	public void setStr(ArrayList<String> str) {
		this.str = str;
	}
	
}

class E1{
	 int a;
	public E1(int x) {
		a = x;
		
	}
	public int getA() {return a;}
	public int f(int x) {return x+a;}
	
}
class E2 extends E1{
	 int a;
	public E2(int y) {
		super(y+100);
	}
	public int getA() {
		return a;
	
	}
	
}

class Parent{
	public Parent() {
		
	}
	void print() {
		System.out.println("I am a parent!");
	}
}
class Son extends Parent{
	public Son() {
		
	}
	
	void print() {
		System.out.println("I am a child!");
	}
}
