// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two.

    public int Allocate(int blockSize) {
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
        if(blockSize<=0){
            return -1;
        }
        Dictionary space = freeBlk.Find(blockSize, false); // Looking for free space
        if(space!=null){
            int a = space.address;
            int s = space.size;
            int k = space.key;
            if(s!=blockSize){ // Here, the split happens as described in assignment
                allocBlk.Insert(a, blockSize, a);
                freeBlk.Delete(space); // Removing memory from freeBlk
                freeBlk.Insert(a+blockSize, s-blockSize, s-blockSize);
                return a;
            }
            else{ // No need to split as block available is of exact size as requirement
                allocBlk.Insert(a, s, a);
                freeBlk.Delete(space);
                return a;
            }
            
        }
        return -1; // Free space of required size not found
    } 
    
    public int Free(int startAddr){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
        if(startAddr<0){
            return -1;
        }
        Dictionary temp = allocBlk.Find(startAddr, true);
        if(temp!=null){
            int a = temp.address;
            int s = temp.size;
            int k  = temp.key;
            freeBlk.Insert(a,s,s);
            allocBlk.Delete(temp); // Removing memory from allocated Block
            return 0;
        }
        return -1; // Address not found in allocBlk
    }

    // Helper function to print values of allocBlk and FreeBlk
    public void print(){
            BSTree allocBlk = (BSTree) this.allocBlk;// typecasting Dictionary => BSTree
            BSTree freeBlk = (BSTree) this.freeBlk; // typecasting Dictionary => BSTree
            System.out.print("Allocated Block Starts: ");
            allocBlk.printPreorder();
            allocBlk.printInorder();
            System.out.println();
            System.out.print("Free Block Starts: ");
            freeBlk.printPreorder();
            freeBlk.printInorder();
            System.out.println();
            System.out.println();
        
    } 
    public void Defragment() {
        // Time Complexity:  O(n*h) 
        // Space Complexity: O(1) 
        // If type is of BSTree
        if(type==2){
            BSTree defrag = new BSTree();
            BSTree freeBlk = (BSTree) this.freeBlk; // typecasting Dictionary => BSTree
            BSTree temp = freeBlk.getFirst();
            // Develop the tree indexed by address.
            while(temp!=null){
                defrag.Insert(temp.address,temp.size,temp.address);
                temp = temp.getNext();
            }

            temp = defrag.getFirst();
            while(temp!=null){
                BSTree temp2 = temp.getNext();
                if(temp2==null){ // If next is null, exit the method
                    return;
                }
                else if(temp.address + temp.size==temp2.address){ // If the two blocks are contiguous, merge them
                    BSTree d1 = new BSTree(temp.address,temp.size,temp.size);
                    BSTree d2 = new BSTree(temp2.address,temp2.size,temp2.size);
                    this.freeBlk.Delete(d1);
                    this.freeBlk.Delete(d2);
                    this.freeBlk.Insert(temp.address,temp.size+temp2.size,temp.size+temp2.size);
                    temp.size = temp.size + temp2.size;
                    BSTree d3 = new BSTree(temp2.address,temp2.size,temp2.address);
                    defrag.Delete(d3);
                }
                else{ // else go to next value
                    temp = temp2;
                }
            }
        }
        else if(type==3){
            AVLTree defrag = new AVLTree();
            AVLTree freeBlk = (AVLTree) this.freeBlk; // typecasting Dictionary => BSTree
            AVLTree temp = freeBlk.getFirst();
            // Develop the tree indexed by address.
            while(temp!=null){
                defrag.Insert(temp.address,temp.size,temp.address);
                temp = temp.getNext();
            }

            temp = defrag.getFirst();
            while(temp!=null){
                AVLTree temp2 = temp.getNext();
                if(temp2==null){ // If next is null, exit the method
                    return;
                }
                else if(temp.address + temp.size==temp2.address){ // If the two blocks are contiguous, merge them
                    AVLTree d1 = new AVLTree(temp.address,temp.size,temp.size);
                    AVLTree d2 = new AVLTree(temp2.address,temp2.size,temp2.size);
                    this.freeBlk.Delete(d1);
                    this.freeBlk.Delete(d2);
                    this.freeBlk.Insert(temp.address,temp.size+temp2.size,temp.size+temp2.size);
                    temp.size = temp.size + temp2.size;
                    AVLTree d3 = new AVLTree(temp2.address,temp2.size,temp2.address);
                    defrag.Delete(d3);
                }
                else{ // else go to next value
                    temp = temp2;
                }
            }
        }
        return;
    }
    
}