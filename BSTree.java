// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        return null;
    }

    public boolean Delete(Dictionary e)
    { 
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        return null;
    }

    public BSTree getFirst()
    { 
        return null;
    }

    public BSTree getNext()
    { 
        return null;
    }

    public boolean sanity()
    { 
        return false;
    }

}


 


