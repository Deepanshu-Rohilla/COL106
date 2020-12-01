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


    // A helper function to test the sanity property of BST  
    public void printByNext(){
        BSTree x = this.getFirst();
        while(x!=null){
            x.printNode();
            x = x.getNext();
        }
    }



    // A function to print inorder traversal of BST
    public void printInorder(){
        System.out.println("INORDER TRAVERSAL STARTS");
        inorder(this.getRoot());
    }

    public void inorder(BSTree t){
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

    public void preorder(BSTree t){
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

        BSTree temp = this;
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




    // Method to insert a node in the tree
    public BSTree Insert(int address, int size, int key){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 

    	BSTree temp = this.getRoot();
    	if(temp.right==null){ // Tree is empty
    		BSTree newNode = new BSTree(address,size,key);
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
    				BSTree newNode = new BSTree(address,size,key);
    				newNode.parent=temp;
    				temp.right=newNode;
    				return newNode;
    			}
    		}
    		else if(temp.key>key){ // We need to go left as our key is smaller
    			if(temp.left!=null){
    				temp = temp.left;
    			}
    			else{ // Inserting the node if left is null
    				BSTree newNode = new BSTree(address,size,key);
    				newNode.parent=temp;
    				temp.left=newNode;
    				return newNode;
    			}
    		}
    		else{ // If the key is same, we compare the addresses in similar way
    			if(temp.address<address){
    				if(temp.right!=null){
    					temp = temp.right;
    				}
	    			else{
	    				BSTree newNode = new BSTree(address,size,key);
	    				newNode.parent=temp;
	    				temp.right=newNode;
	    				return newNode;
	    			}
    			}
    			else if(temp.address>=address){
    				if(temp.left!=null){
    					temp = temp.left;
    				}
    				else{
	    				BSTree newNode = new BSTree(address,size,key);
	    				newNode.parent=temp;
	    				temp.left=newNode;
	    				return newNode;
    				}
    			}
    		}
    	}
        return null;
    }




    // Method to delete a node from the tree
    public boolean Delete(Dictionary e){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
    	BSTree temp = this.getRoot().right;
    	while(temp!=null){
    		if(temp.address==e.address && temp.key==e.key && temp.size==e.size){
    			if(temp.left==null && temp.right==null){ // If node to be deleted is a leaf
    				if(temp.parent.left==temp){
    					temp.parent.left=null;
    					temp.parent=null;
    				}
    				else{
    					temp.parent.right=null;
    					temp.parent=null;
    				}
    				return true;
    			}
    			else if (temp.left==null || temp.right==null){ // If node to be deleted has exactly one child
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
    				return true;
    			}
    			else{ // Node has 2 children. We will copy the successor detail and delete the successor instead
    				BSTree successor = temp.getNext();
    				int a = successor.address;
					int s = successor.size;
					int k = successor.key;
					if(successor.left==null && successor.right==null){
	    				if(successor.parent.left==successor){
	    					successor.parent.left=null;
	    					successor.parent=null;
	    				}
	    				else{
	    					successor.parent.right=null;
	    					successor.parent=null;
	    				}
    				}
    				else{
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




        
    public BSTree Find(int key, boolean exact){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
    	if(exact){
    		BSTree temp = this.getRoot().right;
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
    		BSTree temp = this.getFirst();
    		while(temp!=null && temp.key<key){
    			temp = temp.getNext();
    		}
    		return temp;
    	}
    }




    // Method to get the first/smallest node
    public BSTree getFirst(){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
    	BSTree temp = this.getRoot().right; // Go to right to root sentinel
        if(temp==null){
            return null;
        }
    	while(temp.left!=null){ // Keep going left
    		temp = temp.left;
    	}
        return temp;
    }


    // Method to get the successor of a node
    public BSTree getNext(){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
    	if(this==null){
    		return null;
    	}
    	else if(this.right!=null){ // If right is not null, the successor lies in the right subtree.
    		BSTree temp = this.right;
    		while(temp.left!=null){
    			temp = temp.left;
    		}
    		return temp;
    	}
    	else{ // Traveral up the tree till node is not left child.
    		BSTree temp = this;
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
    public BSTree getRoot(){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 
    	BSTree temp = this;
    	if(temp==null){
    		return null;
    	}
    	while(temp.parent!=null){
    		temp = temp.parent;
    	}
    	return temp;
    }

    public boolean sanity(){
        // Time Complexity:  O(h) 
        // Space Complexity: O(1) 

        
        // Inorder traversal should be non decreasing
        BSTree temp = this.getFirst();
        while(temp!=null){ 
            BSTree temp2 = temp.getNext();
            if(temp2==null){
                break;
            }
            if(temp2.key<temp.key){
                return false;
            }
            temp = temp2;
        } 

        // For every node, node.left.parent and node.left.parent should be the node itself.
        temp = this.getFirst();
        while(temp!=null){ 
            if(temp.left!=null){
                if(temp.left.parent!=temp){
                    return false;
                }
            }
            if(temp.right!=null){
                if(temp.right.parent!=temp){
                    return false;
                }
            }
            temp = temp.getNext();
        } 
        // Checking if search property is maintained
        temp = this.getFirst();
        while(temp!=null){ 
            if(temp.left!=null){
                if(temp.left.key>temp.key){ // Key in left should not be larger than key in the node.
                    return false;
                }
            }
            if(temp.right!=null){
                if(temp.right.key<temp.key){ // Key in left should not be less than or equal to key in the node.
                    return false;
                }
            }
            temp = temp.getNext();
        } 



        return true;
    }

}


 


