public class Test{
	public static void main(String[] args) {
		A1List l = new A1List();
		l = l.Insert(1,1,1);
		l = l.Insert(2,2,2);
		l = l.Insert(3,3,3);
		l.Delete(new A1List(3,3,3));
		l = l.Insert(4,4,4);
		l.print();
		
	}
}