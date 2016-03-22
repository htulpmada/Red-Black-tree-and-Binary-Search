package tree;

import java.util.ArrayList;

public class Bst {
	Node root;
	
	public Bst(ArrayList<String> words){
		for(String word :words){
			ins(word);
		}
	}

	
	protected void ins(String word) {
		Node found=find(word);
		if(found==null){found=new Node(word,null);root=found;}//set root
		else{}
	}
	protected void del(String word) {
		// TODO Auto-generated method stub
	}
	protected void freq(String word) {
		// TODO Auto-generated method stub
	}
	private Node find(String word) {
		if(root==null){return null;}
		Node temp=root;
		while(temp.Left!=null||temp.Right!=null){
			switch(compare(word,temp.data)){
			case(0):
				return temp;
			case(1)://less than
				return find(temp.Left);
			case(-1)://greater than
				break;
				}
		}
		
		return temp;
	}


	private int compare(String word, String data) {
		if(word.compareTo(data)>0){return 1;}
		else if(word.compareTo(data)<0){return -1;}
		return 0;
	}

}
