// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List
public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }


    public void print(){
        A1List temp = this.getHead();
        while(temp!=null){
            System.out.print('(');
            System.out.print(temp.address);
            System.out.print(',');
            System.out.print(temp.size);
            System.out.print(',');
            System.out.print(temp.key);
            System.out.print(')');
            System.out.print(" ");
            temp = temp.next;
        }
        System.out.println();
    }



    public void printNode(){
        A1List temp = this;
        System.out.print('(');
        System.out.print(temp.address);
        System.out.print(',');
        System.out.print(temp.size);
        System.out.print(',');
        System.out.print(temp.key);
        System.out.print(')');
        System.out.print(" ");
        System.out.println();
    }



    public A1List getHead(){
        if(this==null){
            return null;
        }
        A1List traversingPointer = this;
        while(traversingPointer.prev!=null){
            traversingPointer = traversingPointer.prev;
        }
        return traversingPointer;
    }




    public A1List Insert(int address, int size, int key)
    {
        A1List newNode = new A1List(address,size,key);
        newNode.next = this.next;
        newNode.prev = this;
        this.next.prev = newNode;
        this.next= newNode;
        return newNode;
    }

    public boolean Delete(Dictionary d) 
    {   
        A1List temp = this.getFirst();
        while(temp!=null){
            if(temp.address==d.address && temp.size==d.size && temp.key==d.key){
                if(temp!=this){
                    temp.prev.next= temp.next;
                    temp.next.prev = temp.prev;
                    return true; 
                }
                if(this.next.next!=null){
                    this.address = this.next.address;
                    this.size = this.next.size;
                    this.key = this.next.key;
                    this.next = this.next.next;
                    this.next.prev = this;
                    return true;
                }
                if(this.prev.prev!=null){
                    this.address = this.prev.address;
                    this.size = this.prev.size;
                    this.key = this.prev.key;
                    this.prev.prev.next = this;
                    this.prev = this.prev.prev;
                    return true;
                }
                this.address = this.prev.address;
                this.size = this.prev.size;
                this.key = this.prev.key;
                this.next=null;
                
            }
            temp = temp.next;
        }
        return false;
    }




    public A1List Find(int k, boolean exact){ 
        A1List temp = this.getFirst();
        if(exact){
            while(temp!=null){
                if(temp.key==k){
                    return temp;
                }
                temp = temp.next;
            }
        }
        else{
            while(temp!=null){
                if(temp.key>=k){
                    return temp;
                }
                temp = temp.next;
            }
        }
        return null;
    }




    public A1List getFirst(){
        if(getHead().next.next==null){
            return null;
        }
        return getHead().next;
    }
    



    public A1List getNext() 
    {
        if(this==null){
            return null;
        }
        return this.next;
    }



    public boolean sanity()
    {
        return true;
    }

}


