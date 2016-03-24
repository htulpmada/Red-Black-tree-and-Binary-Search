package tree;

import java.util.ArrayList;

public class RedBlack extends Bst {	
	public RedBlack(ArrayList<String> words) {
		super(words);
		fixUp();
		
	}

	
	protected Node ins(String word) {
		super.ins(word);
		fixUp();
		return root;
	}
	protected void del(String word) {
		super.del(word);
		fixUp();
	}
	
	
	private void fixUp() {
		int dir=0;
		rotate(dir);
	}
	private void rotate(int direction){
		switch(direction){
		case(0):
			break;
		case(1):
			break;
		case(2):
			break;
		case(3):
			break;
		}
	}
	
	
}
