// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }



    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.


    public void printInorder(){
        System.out.println("INORDER TRAVERSAL STARTS");
        inorder(this.getRoot());
    }

    public void inorder(AVLTree t){
        if(t==null){
            return;
        }
        inorder(t.left);
        t.printNode();
        inorder(t.right);
    }



    // A function to print preorder traversal of BST
    public void printPreorder(){
        System.out.println("PREORDER TRAVERSAL STARTS");
        preorder(this.getRoot());
    }

    public void preorder(AVLTree t){
        if(t==null){
            return;
        }
        t.printNode();
        preorder(t.left);
        preorder(t.right);
    }



    // A helper function to print contents of a node
    public void printNode(){
        // Time Complexity:  O(n) 
        // Space Complexity: O(1) 

        AVLTree temp = this;
        if(temp==null){
            System.out.println("Value to null nikli");
            return;
        }
        System.out.print('(');
        System.out.print(temp.address);
        System.out.print(',');
        System.out.print(temp.size);
        System.out.print(',');
        System.out.print(temp.key);
        System.out.print(')');
        System.out.print(" with height ");
        System.out.println(height(temp));
    }
    
    public AVLTree Insert(int address, int size, int key){ 
        AVLTree temp = this.getRoot();
        AVLTree newNode = new AVLTree(address,size,key);
        if(temp.right==null){ // Tree is empty
            newNode.parent=temp;
            temp.right=newNode;
            return newNode;
        }
        temp = temp.right; // Else go to right of root sentinel at the actual Tree
        while(temp!=null){
            if(temp.key<key){ // We need to go right as our key is larger
                if(temp.right!=null){ 
                    temp = temp.right;
                }
                else{ // Inserting the node if right is null
                    newNode.parent=temp;
                    temp.right=newNode;
                    break;
                    // return newNode;
                }
            }
            else if(temp.key>key){ // We need to go left as our key is smaller
                if(temp.left!=null){
                    temp = temp.left;
                }
                else{ // Inserting the node if left is null
                    newNode.parent=temp;
                    temp.left=newNode;
                    break;
                    // return newNode;
                }
            }
            else{ // If the key is same, we compare the addresses in similar way
                if(temp.address<address){
                    if(temp.right!=null){
                        temp = temp.right;
                    }
                    else{
                        newNode.parent=temp;
                        temp.right=newNode;
                        break;
                        // return newNode;
                    }
                }
                else if(temp.address>=address){
                    if(temp.left!=null){
                        temp = temp.left;
                    }
                    else{
                        newNode.parent=temp;
                        temp.left=newNode;
                        break;
                        // return newNode;
                    }
                }
            }
        }
        newNode.height = 1;
        temp.height = 1 + max(height(temp.left),height(temp.right));
        checkBalance(temp);
        return newNode;
    }

    public void incrementHeight(AVLTree node){
        if(node.parent==null){
            return;
        }
        node.height = 1 + max(height(node.left),height(node.right));
        incrementHeight(node.parent);
    }

    public void checkBalance(AVLTree node){
        if(node.parent==null){
            return;
        }
        if((height(node.left) - height(node.right)>1) ||(height(node.left) - height(node.right)<-1)){
            rebalance(node);
        }
        node.height = 1 + max(height(node.left),height(node.right));
        checkBalance(node.parent);
    }

    public void rebalance(AVLTree node){
        // System.out.println("Entered in rebalancing");
        // node.printNode();
        if(height(node.left) - height(node.right)>1){
            if(height(node.left.left)>height(node.left.right)){
                node = rightRotate(node);
            }
            else{
                node = leftRightRotate(node);
            }
        }
        else{
            if(height(node.right.right)>height(node.right.left)){
                node = leftRotate(node);
            }
            else{
                node = rightLeftRotate(node);
            }
        }
    }

    public AVLTree rightRotate(AVLTree temp) { 
        // System.out.print("Right rotating");
        // temp.printNode();
        AVLTree temp2 = temp.left; 
        AVLTree temp2R = temp2.right; 
  
        // Perform rotation 

        temp2.parent = temp.parent;
        if(temp.parent.right==temp){
            temp.parent.right=temp2;
        }
        else{
            temp.parent.left=temp2;
        }

        temp.parent = temp2;

        temp2.right = temp; 

        temp.left = temp2R; 
        if(temp2R!=null){
            temp2R.parent = temp;
        }
  
        // Update heights 
        temp.height = max(height(temp.left), height(temp.right)) + 1; 
        temp2.height = max(height(temp2.left), height(temp2.right)) + 1; 
  
        // Return new root 
        return temp2; 
    } 
  
    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
    public AVLTree leftRotate(AVLTree temp) { 
        // System.out.print("Left rotating");
        // temp.printNode();
        AVLTree temp2 = temp.right; 
        AVLTree temp2L = temp2.left; 
  
        // Perform rotation 
        temp2.parent = temp.parent; 
        if(temp.parent.right==temp){
            temp.parent.right=temp2;
        }
        else{
            temp.parent.left=temp2;
        }
        temp.parent = temp2;
        temp2.left=temp;
        temp.right = temp2L;
        if(temp2L!=null){
            temp2L.parent = temp; 
        }
        //  Update heights 
        temp.height = max(height(temp.left), height(temp.right)) + 1; 
        temp2.height = max(height(temp2.left), height(temp2.right)) + 1; 
  
        // Return new root 
        return temp2; 
    } 

    public AVLTree leftRightRotate(AVLTree x){
        // System.out.print("Left Right rotating");
        // x.printNode();
        x.left = leftRotate(x.left); 
        return rightRotate(x);

    }
    public AVLTree rightLeftRotate(AVLTree x){
        // System.out.print("Right Left rotating");
        // x.printNode();
        x.right = rightRotate(x.right); 
        return leftRotate(x); 
    }
    int height(AVLTree node) { 
        if (node == null) 
            return 0; 
  
        return node.height; 
    } 
  
    // A utility function to get maximum of two integers 
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 

    public boolean Delete(Dictionary e){
        AVLTree temp = this.getRoot().right;
        while(temp!=null){
            if(temp.address==e.address && temp.key==e.key && temp.size==e.size){
                if(temp.left==null && temp.right==null){ // If node to be deleted is a leaf
                    AVLTree t1 = temp.parent;
                    if(temp.parent.left==temp){
                        temp.parent.left=null;
                        temp.parent=null;
                    }
                    else{
                        temp.parent.right=null;
                        temp.parent=null;
                    }
                    checkBalance(t1);
                    return true;
                }
                else if (temp.left==null || temp.right==null){ // If node to be deleted has exactly one child
                    AVLTree t1 = temp.parent;
                    if(temp.left==null){
                        if(temp.parent.left==temp){
                            temp.parent.left = temp.right;
                        }
                        else{
                            temp.parent.right = temp.right;
                        }
                        temp.right.parent=temp.parent;
                    }
                    else{
                        if(temp.parent.left==temp){
                            temp.parent.left = temp.left;
                        }
                        else{
                            temp.parent.right = temp.left;
                        }
                        temp.left.parent = temp.parent;
                    }
                    checkBalance(t1);
                    return true;
                }
                else{ // Node has 2 children. We will copy the successor detail and delete the successor instead
                    AVLTree successor = temp.getNext();
                    AVLTree temp1 = successor.parent;
                    int a = successor.address;
                    int s = successor.size;
                    int k = successor.key;
                    if(successor.left==null && successor.right==null){
                        AVLTree t1 = successor.parent;
                        if(successor.parent.left==successor){
                            successor.parent.left=null;
                            successor.parent=null;
                        }
                        else{
                            successor.parent.right=null;
                            successor.parent=null;
                        }
                        checkBalance(t1);
                    }
                    else{
                        AVLTree t1 = successor.parent;
                        if(successor.left==null){
                            if(successor.parent.left==successor){
                                successor.parent.left = successor.right;
                            }
                            else{
                                successor.parent.right = successor.right;
                            }
                        successor.right.parent=successor.parent;
                        }
                        else{
                            if(successor.parent.left==successor){
                                successor.parent.left = successor.left;
                            }
                            else{
                                successor.parent.right = successor.left;
                            }
                            successor.left.parent = successor.parent;
                        }
                        checkBalance(t1);
                    }
                    temp.address=a;
                    temp.size=s;
                    temp.key=k;
                    return true;
                }
            }
            else{ // If key does not match, traverse to left or right accordingly
                if(temp.key<e.key){
                    temp = temp.right;
                }
                else if(temp.key>e.key){
                    temp = temp.left;
                }
                else{
                    if(temp.address<e.address){
                        temp = temp.right;
                    }
                    else{
                        temp = temp.left;
                    }
                }
            }
        }
        return false; // If element not found, return false
    }
        
    public AVLTree Find(int key, boolean exact){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
        if(exact){
            AVLTree temp = this.getRoot().right;
            while(temp!=null){
                if(temp.key<key){ // Go right if key is large
                    temp = temp.right;
                }
                else if(temp.key>key){// Go left if key is small
                    temp = temp.left;
                }
                else{
                    while(temp.left!=null && temp.left.key==key){ // Returning the one with smalles address if key is same
                        temp = temp.left;
                    }
                    return temp;
                }
            }
            return null;
        }
        else{
            // Starting with the smallest and traversing till we find one will key greater than
            // or equal to the key we are searching. This ensures that best fit condition is satsfied.
            AVLTree temp = this.getFirst();
            while(temp!=null && temp.key<key){
                temp = temp.getNext();
            }
            return temp;
        }
    }

    
    // Method to get the first/smallest node
    public AVLTree getFirst(){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
        AVLTree temp = this.getRoot().right; // Go to right to root sentinel
        if(temp==null){
            return null;
        }
        while(temp.left!=null){ // Keep going left
            temp = temp.left;
        }
        return temp;
    }


    // Method to get the successor of a node
    public AVLTree getNext(){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
        if(this==null){
            return null;
        }
        else if(this.right!=null){ // If right is not null, the successor lies in the right subtree.
            AVLTree temp = this.right;
            while(temp.left!=null){
                temp = temp.left;
            }
            return temp;
        }
        else{ // Traveral up the tree till node is not left child.
            AVLTree temp = this;
            while(temp.parent!=null){
                if(temp.parent.left==temp){
                    return temp.parent;
                }
                temp = temp.parent;
            }
        }
        return null;
    }

    // A helper function to get the root sentinel of a tree.q
    public AVLTree getRoot(){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
        AVLTree temp = this;
        if(temp==null){
            return null;
        }
        while(temp.parent!=null){
            temp = temp.parent;
        }
        return temp;
    }

    public boolean sanity()
    { 
        return false;
    }
}


