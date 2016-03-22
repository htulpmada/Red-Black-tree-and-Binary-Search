package tree;

public class Node {
	Node Left,Right,Parent;
	String data;
	int frequency;
	
	public Node(String s,Node p){
		Left=Right=null;
		Parent=p;
		data=s;
		frequency=1;
	}
	
	
	
	
	protected void del(){
		if(this.Parent!=null){
			if(this.Parent.Left.data==this.data){
				this.Parent.Left=null;
			}
			else if(this.Parent.Right.data==this.data){
				this.Parent.Right=null;
			}
			else{
				System.out.println("error deleting, has parent");
			}
		}
		else{
			System.out.println("error, deleting root");
		}
	}
	
	
	
}