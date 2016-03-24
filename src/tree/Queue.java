package tree;

public class Queue {
	private QNode head,tail;
	private int size;
	
	public Queue(){
		size=0;
		tail=head=null;
	}
	public QNode getfront(){
		return head;
	}
	public void insert(Node n){
		QNode temp = new QNode();
		temp.index=n;
		temp.next=null;
		if(size==0)head=tail=temp;
		else {tail.next=temp;tail=temp;}
		size++;
		}
	public Node gethead(){
		Node temp =null;
		if(size>0){
			temp=head.index;
			head=head.next;
			size--;
			if(size==0){tail=head=null;}
		}
		return temp;
	}
	public int getsize(){
		return size;
	}
}
