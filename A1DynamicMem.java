// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if(blockSize<=0){
            return -1;
        }
        A1List allocBlk = (A1List) this.allocBlk; // typecasting Dictionary => A1List
        A1List freeBlk = (A1List) this.freeBlk; // typecasting Dictionary => A1List
        A1List space = freeBlk.Find(blockSize, false); // Looking for free space
        if(space!=null){
            if(space.size!=blockSize){ // Here, the split happens as described in assignment
                A1List spaceRequired = new A1List(space.address,blockSize,space.address);
                A1List extraSpace = new A1List(space.address+blockSize, space.size- blockSize, space.size-blockSize);
                allocBlk.Insert(spaceRequired.address, spaceRequired.size, spaceRequired.key);
                freeBlk.Insert(extraSpace.address, extraSpace.size, extraSpace.size);
                freeBlk.Delete(space); // Removing memory from freeBlk
                return spaceRequired.address;
            }
            else{ // No need to split as block available is of exact size as requirement
                allocBlk.Insert(space.address, space.size, space.address);
                freeBlk.Delete(space);
                return space.address;
            }
            
        }
        return -1; // Free space of required size not found
    } 
    
    public int Free(int startAddr){
        if(startAddr<0){
            return -1;
        }
        A1List allocBlk = (A1List) this.allocBlk; // typecasting Dictionary => A1List
        A1List freeBlk = (A1List) this.freeBlk; // typecasting Dictionary => A1List
        A1List temp = allocBlk.Find(startAddr, true);
        if(temp!=null){
            allocBlk.Delete(temp); // Removing memory from allocated Block
            freeBlk.Insert(temp.address,temp.size,temp.size);
            return 0;
        }
        return -1; // Address not found in allocBlk
    }
    public void print(){
        if(this.type==1){
            A1List allocBlk = (A1List) this.allocBlk; // typecasting Dictionary => A1List
            A1List freeBlk = (A1List) this.freeBlk; // typecasting Dictionary => A1List
            System.out.print("Allocated Block Starts: ");
            allocBlk.print();
            System.out.print("Free Block Starts: ");
            freeBlk.print();
            System.out.println();
        }
        else if(this.type==2){
            BSTree allocBlk = (BSTree) this.allocBlk;
            BSTree freeBlk = (BSTree) this.freeBlk;
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
        
    } 
}