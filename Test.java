public class Test{
	public static void main(String[] args) { 
    AVLTree t  = new AVLTree();
    t.Insert(1,1,10);
    t.Insert(1,1,20);
    t.Insert(1,1,15);
    t.Insert(1,1,25);
    t.Insert(1,1,30);
    t.Insert(1,1,16);
    t.Insert(1,1,18);
    t.Insert(1,1,19);
    t.printInorder();
    t.printPreorder();
    t.Delete(new AVLTree(1,1,30));
 }
}