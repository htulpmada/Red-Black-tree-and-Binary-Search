package tree;

import java.util.ArrayList;

public class Bst {
	protected static Node root;
	
	public Bst(ArrayList<String> words){
		for(String word :words){
			root=ins(word);
			System.out.print("\ninserted\n\n");
		}
		print(root);
	}
	
	
	//////////////getter/setter f(x)
	public static Node getRoot(){return root;}
	public void setRoot(Node n){root=n;}
	
	
	/////////////level order print f(x)'s
	protected void print(){print(root);}//no args prints root of tree
	protected void print(Node n){////prints all subtrees of n
		Queue nodes=new Queue();
		nodes.insert(n);
		Node nxtLv=root;
		int level=0;
		while(nodes.getfront()!=null){
			Node t=nodes.gethead();
			if(t==null){continue;}
			if(nxtLv!=null&&nxtLv.getData()==t.getData()){
				System.out.print("\n"+ ++level+":");
				if(t.getLeft()!=null){nxtLv=t.getLeft();}
				else if(t.getRight()!=null){nxtLv=t.getRight();}
				else{
					if(nodes.getfront().get()==null){
						System.out.print(" "+t.getData()+" "+t.getFreq()+" ");
						nodes.insert(t.getLeft());
						nodes.insert(t.getRight());
						continue;
						}
					if(nodes.getfront().get().getLeft()!=null){
						nxtLv=nodes.getfront().get().getLeft();
					}
					else if(nodes.getfront().get().getRight()!=null){
						nxtLv=nodes.getfront().get().getRight();
					}
				}
			}
			
			System.out.print(" "+t.getData()+" "+t.getFreq()+" ");
			nodes.insert(t.getLeft());
			nodes.insert(t.getRight());
		}
		System.out.print("\n");
	}
	
	public static void lvOrder(Node n){////prints all subtrees of n
		Queue nodes=new Queue();
		nodes.insert(n);
		Node nxtLv=root;
		int level=0;
		while(nodes.getfront()!=null){
			Node t=nodes.gethead();
			if(t==null){continue;}
			System.out.println(" "+t.getData()+" "+t.getFreq()+" ");
			nodes.insert(t.getLeft());
			nodes.insert(t.getRight());
		}
	}
	
	/////////////insert f(x)'s
	protected Node ins(String word) {//initial call
		root=ins(root,word);
		//System.out.print("root:"+root.getData()+" f:"+root.getFreq());
		return root;
	}
	private Node ins(Node n,String word){//overloaded to recurse
		if(n==null){return new Node(word);}
		switch(compare(word,n.getData())){
			case(-1)://less than
				n.setLeft(ins(n.getLeft(),word));
				break;
			case(1)://greater than
				n.setRight(ins(n.getRight(),word));
				break;
			default:
				n.incFreq();
			}
		return n;
		}
	
	
	//////////////////////delete f(x)'s	
	protected Node del(String word) {//initial call
		root=del(root,word);
		//System.out.print("root:"+root.getData()+" f:"+root.getFreq());
		return root;
	}
	protected Node del(Node n,String word) {//overloaded to recurse
		if(n==null){return null;}
		switch(compare(word,n.getData())){
			case(-1)://less than
				n.setLeft(del(n.getLeft(),word));
				break;
			case(1)://greater than
				n.setRight(del(n.getRight(),word));
				break;
			default:
				if(n.getFreq()>1){n.decFreq();break;}//if frequency > 1
				else{//frequency = 1
					if(n.getRight()==null){return n.getLeft();}
					if(n.getLeft()==null){return n.getRight();}
					Node temp = n;
					n = min(temp.getRight());
					n.setRight(delMin(temp.getRight()));
					n.setLeft(temp.getLeft());
				}
			}
		return n;
	}
	private Node delMin(Node n){
		if(n.getLeft()==null){return n.getRight();}
		n.setLeft(delMin(n.getLeft()));
		return n;
	}
	
	
	/////////////////frequency f(x)'s
	protected int getFreq(String word) {//initial call
		return getFreq(root,word);
	}
	protected int getFreq(Node n,String word) {//overload to recurse
		if(n==null){return 0;}
		switch(compare(word,n.getData())){
			case(-1)://less than
				return getFreq(n.getLeft(),word);
			case(1)://greater than
				return getFreq(n.getRight(),word);
			default:
				return n.getFreq();
		}
	}
	
	/////////////////report f(x)'s
	public void report() {
		System.out.println("report");
	}

	//////////helper f(x)
	private Node min(Node n){
		if(n.getLeft()==null){return n;}
		else{return min(n.getLeft());}
	}
	
 	private int compare(String word, String data) {//lexographic
		if(word.compareTo(data)>0){
			System.out.println(word+" > "+data);
			return 1;
			}
		else if(word.compareTo(data)<0){
			System.out.println(word+" < "+data);
			return -1;
			}
		else {//not sure if needed didn't have originally
			System.out.println(word+" = "+data);
			return 0;
		}
	}


	

}
