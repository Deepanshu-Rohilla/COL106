public class Queue{
	Node front;
	Node rear;
	static class Node{
		int data;
		Node next;
		Node(int d){
			this.data = d;
		}
	}
	public static LinkedList insert(LinkedList l, int data){
		Node newNode = new Node(data);
		newNode.next = null;
		if(l.head==null){
			l.head = newNode;
		}
		else{
			Node temp = l.head;
			while(temp.next!=null){
				temp = temp.next;
			}
			temp.next = newNode;
		}
		return l;
	}
	public static void delete(LinkedList l, int data){
		Node temp = l.head;
		Node prev = l.head;
		if(temp.data==data){
			l.head = l.head.next;
		}
		while(temp!=null &&temp.data!=data){
			prev = temp;
			temp = temp.next;
		}
		if(temp==null){
			System.out.println("Key not found");
		}
		else{
			prev.next = temp.next;
		}
	}
	public static void printList(LinkedList l){
		Node temp = l.head;
		while(temp!=null){
			System.out.print(temp.data);
			System.out.print(" ");
			temp = temp.next;
		}
		System.out.println();
	}

}