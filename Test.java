public class Test{
	public static void main(String[] args) {
		A1DynamicMem mem = new A1DynamicMem();
		mem.print();
		mem.Allocate(10);
		mem.print();
		mem.Allocate(100);
		mem.print();
		// mem.Allocate(69);
		// mem.print();
		// mem.Allocate(200);
		// mem.print();
		// mem.Allocate(50);
		// mem.print();
		// mem.Allocate(150);
		// mem.print();
		// mem.Free(100);
		// mem.print();
		// mem.Free(69);
		// mem.print();
	}
}