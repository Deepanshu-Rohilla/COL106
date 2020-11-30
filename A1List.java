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


    // Supporting method to print entire list (mainly for debugging purpose)
    public void print(){
        // Time Complexity:  O(n) 
        // Space Complexity: O(1) 
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


    // A helper function to print contents of a node
    public void printNode(){
        // Time Complexity:  O(n) 
        // Space Complexity: O(1) 

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



    // Function to insert the value next to the node that calls it
    public A1List Insert(int address, int size, int key){
        // Time Complexity:  O(1) 
        // Space Complexity: O(1) 

        A1List newNode = new A1List(address,size,key);
        newNode.next = this.next;
        newNode.prev = this;
        this.next.prev = newNode;
        this.next= newNode;
        return newNode;
    }


    // Function to delete the node with exact address, size and key as that of parameter d
    public boolean Delete(Dictionary d){
        // Time Complexity:  O(n) 
        // Space Complexity: O(1) 
        A1List temp = this.getFirst();
        while(temp!=null){
            if(temp.address==d.address && temp.size==d.size && temp.key==d.key){
        // Case1: The node to be deleted is not the node from where it is called
                if(temp!=this){
                    temp.prev.next= temp.next;
                    temp.next.prev = temp.prev;
                    return true; 
                }
        // Case2: We have to remove the node which calls the function
        // Here, we will copy the elemets to the previous or next node
        // and delete that node.
        // Case2a: The node to be deleted (call it node0) has 2 nodes 
        //         after it (node1 and node2).

        // Head...sublist...node0...node1...node2...sublist...

        // Here, we will copy the elements of node1 in node0 and remove node1
        // by establishing links between node0 and node2
                if(this.next.next!=null){
                    this.address = this.next.address;
                    this.size = this.next.size;
                    this.key = this.next.key;
                    this.next = this.next.next;
                    this.next.prev = this;
                    return true;
                }
        //Cas2b: Similar to Case2a but in reverse direction
                if(this.prev.prev!=null){
                    this.address = this.prev.address;
                    this.size = this.prev.size;
                    this.key = this.prev.key;
                    this.prev.prev.next = this;
                    this.prev = this.prev.prev;
                    return true;
                }
        // Case2c:  List has exactly one element and that is to be deleted
        // Make the node as tail node and remove the old tail node
                this.address = this.prev.address;
                this.size = this.prev.size;
                this.key = this.prev.key;
                this.prev=null;
                return true;
                
            }
        // If element not found, increment the temp pointer
            temp = temp.next;
        }
        return false;
    }


    public A1List Find(int k, boolean exact){ 
        // Time Complexity:  O(n) 
        // Space Complexity: O(1) 

        A1List temp = this.getFirst();
        if(exact){
            // Loop Invariant: 
            // After i iterations, k ≠ key value of first i entries.
            while(temp!=null){
                if(temp.key==k){
                    return temp;
                }
                temp = temp.next;
            }
        }
        else{
            while(temp!=null){
            // Loop Invariant: 
            // After i iterations, key value of first i entries < k. 
                if(temp.key>=k){
                    return temp;
                }
                temp = temp.next;
            }
        }
        return null;
    }



    // This is a helper function to get the head sentinel
    public A1List getHead(){
        // Time Complexity:  O(n) 
        // Space Complexity: O(1) 

        if(this==null){
            return null;
        }
        A1List traversingPointer = this;
        while(traversingPointer.prev!=null){
            traversingPointer = traversingPointer.prev;
        }
        return traversingPointer;
    }

    // This is a helper function to get the tail sentinel
    public A1List getTail(){
        // Time Complexity:  O(n) 
        // Space Complexity: O(1) 

        if(this==null){
            return null;
        }
        A1List traversingPointer = this;
        while(traversingPointer.next!=null){
            traversingPointer = traversingPointer.next;
        }
        return traversingPointer;
    }



    // To extract the first node of the list
    public A1List getFirst(){
        if(getHead().next.next==null){
            return null;
        }
        return getHead().next;
    }
    

    // To get the nextNode of any node. It is the getter function which is required
    // since next is a private attribute and cannot be accessed directly.
    public A1List getNext(){
        if(this==null){
            return null;
        }
        return this.next;
    }


    public boolean sanity(){
        // Time Complexity:  O(n) 
        // Space Complexity: O(1) 

        // Code to check cycle in a list using fast-slow pointer
        A1List slow = this.getFirst();
        A1List fast = this.getFirst();
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                return false;
            }
        }

        // Prev of head sentinel should be null
        if(this.getHead().prev!=null){ 
            return false;
        }

        // Next of tail sentinel should be null
        if(this.getTail().next!=null){ 
            return false;
        }

        // For every loop, temp.prev.next!=temp should hold to maintain correctness of pointers
        A1List temp = this.getFirst();
        while(temp!=null){
            if(temp.prev.next!=temp){
                return false;
            }
            temp = temp.next;
        }
        return true;
    }

}


