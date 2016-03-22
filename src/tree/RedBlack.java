package tree;

import java.util.ArrayList;

public class RedBlack extends Bst {	
	public RedBlack(ArrayList<String> words) {
		super(words);
		fixUp();
		
	}

	
	protected void ins(String word) {
		super.ins(word);
		fixUp();
	}
	protected void del(String word) {
		super.del(word);
		fixUp();
	}
	
	
	private void fixUp() {
		
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
