// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List{

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
    // Helper function to print the list
    public void print(){
        // Time Complexity:  O(n)
        // Space Complexity: O(1)
        A1List temp = this.getFirst();
        while(temp!=null){
            System.out.print(temp.address);
            System.out.print(" <-> ");
            temp = temp.next;
        }
        System.out.println();
    }

    public A1List Insert(int address, int size, int key){ 
        // Time Complexity:  O(1)
        // Space Complexity: O(1)
        // Insert the element in the end by default
        // Calling Insert() would add a Node4 in the end and the output will be: 
        //      List:   Head Sentinel->Node1->Node2->Node3->Tail Sentinel
        //      Output: Head Sentinel->Node1->Node2->Node3->Node4->Tail Sentinel

        A1List newNode = new A1List(address,size,key);
        newNode.prev=this;
        this.next=newNode;
        return newNode;
    }
    public boolean Delete(Dictionary d){
        // Time Complexity:  O(n)
        // Space Complexity: O(1)
        try{
                A1List searchedNode = this.Find(d.key, true);
        if(searchedNode!=null && searchedNode!=this && searchedNode.prev!=null){
            searchedNode.prev.next = searchedNode.next;
            if(searchedNode.next!=null){
                searchedNode.next.prev = searchedNode.prev;
            }
            return true;
        }
        else if(searchedNode!=null && searchedNode==this){
            // Copying the elements of this.prev and deleting this.prev
            // This is because this = this.prev directly is not allowed
            this.key = this.prev.key;
            this.address = this.prev.address;
            this.size = this.prev.size;
            if(this.prev.prev!=null){
                this.prev.prev.next = this;
                this.prev = this.prev.prev;
            }
            
            return true;
        }
        }catch(Exception NullPointerException){
            return false;
        }
        
        return false;
    }

    public A1List Find(int k, boolean exact){ 
        // Time Complexity:  O(n)
        // Space Complexity: O(1)
        // If exact is true, look for the exact key value
        if(exact){
            A1List traversingPointer = this;
            while(traversingPointer!=null){  
                // Loop invariant: 
                // After i iterations, object is not present in last i entries

                if(traversingPointer.key==k){
                    return traversingPointer;
                }
                traversingPointer = traversingPointer.prev;
            }
        }
        // If exact is false, looking for smallest value greater than k
        else{
            A1List traversingPointer  = this.getFirst();
            int val=-2; // -2 signifies null value
            A1List temp2=null;
            while(traversingPointer!=null){
                // Loop invariant: 
                // After i iterations, object is not present in last i entries
                if(val==-2 || traversingPointer.key>=val){
                    val=traversingPointer.key;
                    temp2 = traversingPointer;
                }
                traversingPointer=traversingPointer.next;
            }
            return temp2;
        }
        return null;
    }

    public A1List getFirst(){
        // Time Complexity:  O(n)
        // Space Complexity: O(1)
        // Since "this" is pointing to the end we need to traverse to the beginning
        // while the previous value is not null
        if(this==null){
            return null;
        }
        A1List traversingPointer = this;
        while(traversingPointer.prev!=null){
            traversingPointer = traversingPointer.prev;
        }
        return traversingPointer;
    }
    
    public A1List getNext(){
        // Time Complexity:  O(1)
        // Space Complexity: O(1)
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


