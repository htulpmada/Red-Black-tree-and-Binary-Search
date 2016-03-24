package tree;

import java.util.ArrayList;

public class Bst {
	Node root;
	
	public Bst(ArrayList<String> words){
		for(String word :words){
			root=ins(word);
			System.out.print("\ninserted\n\n");
		}
		print(root);
	}

	protected void print(Node n){
		Queue nodes=new Queue();
		nodes.insert(n);
		while(nodes.getfront()!=null){
			Node t=nodes.gethead();
			if(t==null){continue;}
			System.out.println("node: "+t.data+" Freq: "+t.frequency);
			nodes.insert(t.Left);
			nodes.insert(t.Right);
		}
	}
	
	protected Node ins(String word) {
		root=ins(root,word);
		System.out.print(""+root.data+" "+root.frequency);
		return root;
	}
	
	private Node ins(Node n,String word){
		if(n==null){return new Node(word);}
		switch(compare(word,n.data)){
			case(-1)://less than
				n.Left=ins(n.Left,word);
				break;
			case(1)://greater than
				n.Right=ins(n.Right,word);
				break;
			default:
				n.frequency++;
			}
		return n;
		}
	
	
	protected void del(String word) {
		// TODO Auto-generated method stub
	}
	protected void freq(String word) {
		// TODO Auto-generated method stub
	}


	private int compare(String word, String data) {
		if(word.compareTo(data)>0){System.out.println(word+" greaterThan "+data);return 1;}
		else if(word.compareTo(data)<0){System.out.println(word+" lessThan "+data);return -1;}
		System.out.println(word+" equals "+data);
		return 0;
	}

}
