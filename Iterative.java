import java.util.*;
import java.io.*;
class Node{
	int data;
	int count;
	Node left, right;
	Node(int data){
		this.data  = data;
		this.left = null;
		this.right=null;
		this.count=0;
	}
}
class Tree{
	Node root;
	Iterative(){
		this.root = null;
	}
	public void preOrder(Node node){
		Stack<IterNode> s1 = new Stack<IterNode>();
		Stack<Integer> s2 = new Stack<Integer>();
		if(node==null){
			return;
		}
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public void postOrder(Node node){
		if(node==null){
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + " ");
	}
	public void inOrder(Node node){
		if(node==null){
			return;
		}
		inOrder(node.left);
		System.out.print(node.data + " ");
		

	public static void main(String[] args) {
		Iterative tree = new Iterative();
		tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
  
        System.out.println("Preorder traversal of binary tree is "); 
        tree.preOrder(tree.root); 
  
        System.out.println("\nInorder traversal of binary tree is "); 
        tree.inOrder(tree.root); 
  
        System.out.println("\nPostorder traversal of binary tree is "); 
        tree.postOrder(tree.root); 
	}
}