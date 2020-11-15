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
        A1List allocBlk = (A1List) this.allocBlk;
        A1List freeBlk = (A1List) this.freeBlk;
        A1List space = freeBlk.Find(blockSize, false);
        if(space!=null){
            if(space.size!=blockSize){
                A1List spaceRequired = new A1List(space.address,blockSize,blockSize);
                A1List extraSpace = new A1List(space.address+blockSize, space.size- blockSize, space.size-blockSize);
                allocBlk.Insert(spaceRequired.address, spaceRequired.size, spaceRequired.key);
                freeBlk.Insert(extraSpace.address, extraSpace.size, extraSpace.key);
                freeBlk.Delete(space);
                return spaceRequired.address;
            }
            else{
                allocBlk.Insert(space.address, space.size, space.key);
                freeBlk.Delete(space);
                return space.address;
            }
            
        }
        return -1;
    } 
    
    public int Free(int startAddr) {
        A1List allocBlk = (A1List) this.allocBlk;
        A1List freeBlk = (A1List) this.freeBlk;
        A1List temp = allocBlk.getFirst();
        while(temp!=null){
            if(temp.address==startAddr){
                allocBlk.Delete(temp);
                this.freeBlk = freeBlk.Insert(temp.address,temp.size,temp.key);
                return 0;
            }
            temp = temp.getNext();
        }
        return -1;
    }
    public void print(){
        A1List allocBlk = (A1List) this.allocBlk;
        A1List freeBlk = (A1List) this.freeBlk;
        System.out.print("Allocated Block Starts: ");
        allocBlk.print();
        System.out.print("Free Block Starts: ");
        freeBlk.print();
    }
}