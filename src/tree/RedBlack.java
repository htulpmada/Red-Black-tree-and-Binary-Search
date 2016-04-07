package tree;

import java.util.ArrayList;

public class RedBlack extends Bst {	
	public RedBlack() {}
	public RedBlack(ArrayList<String> words) {
		for(String word :words){
//			System.out.print(word);
			root=ins(word);
			//System.out.print(" Done\n");
			//fixUp(word);
		}
	}

	

	protected Node ins(String word) {
		root=super.ins(word);
		//setParents(root);
		fixUpIns(word,root);
		return root;
	}
	protected Node del(String word) {
		try{root=super.del(root,word);}
		catch(NullPointerException e){e.printStackTrace();;return root;/*item not in tree*/}
		fixUpDel(word,root);
		return root;
	}
	
	
	private void fixUpIns(String word,Node n) {
		switch(compare(word,n.getData())){
			case(-1)://less than
				fixUpIns(word,n.getLeft());
				break;
			case(1)://greater than
				fixUpIns(word,n.getRight());
				break;
			case(0)://equals
				while(true){//fix up loop
					if(isRoot(n)=="X"){break;}
					if(n.getParent().isRed()!=true){break;}
					if(n.getUncle()!=null&&n.getUncle().isRed()){//red uncle problem might be here
						n.getParent().makeBlack();
						n.getUncle().makeBlack();
						n.getParent().getParent().makeRed();
						fixUpIns(n.getParent().getParent().getData(),n.getParent().getParent());
						return;
					}
					else{//black uncle
						if(!n.linear()){//problem somewhere in here
							Node t=n.getParent();//old parent after rotate
							rotate(n,n.getParent());//rotate current node to parent node
							n=t;
						}
						n.getParent().makeBlack();
						n.getParent().getParent().makeRed();
						rotate(n.getParent(),n.getParent().getParent());//rotate parent to grandparent
					}					
				}
				root.makeBlack();
				return;
			default:
				return;
		}
	}

	private void fixUpDel(String word,Node n) {
		if(n==null||n.getFreq()>0){n.makeBlack();return;}
		switch(compare(word,n.getData())){
		case(-1)://less than
			fixUpDel(word,n.getLeft());
			break;
		case(1)://greater than
			fixUpDel(word,n.getRight());
			break;
		default://equals
			while(true){//fix up loop
				if(n.getData()==root.getData()){
					n.makeBlack();
					break;
					}
				if(n.isRed()){break;}
				if(n.getSib()!=null&&n.getSib().isRed()){
					n.getParent().makeRed();
					n.getSib().makeBlack();
					rotate(n.getSib(),n.getParent());
				}
				else if(n.getNeph()!=null&&n.getNeph().isRed()){
					if(n.getParent().isRed()){n.getSib().makeRed();}
					else{n.getSib().makeBlack();}
					n.getParent().makeBlack();
					n.getNeph().makeBlack();
					rotate(n.getSib(),n.getParent());
					n=root;
				}
				else if(n.getNeice()!=null&&n.getNeice().isRed()){
					n.getNeice().makeBlack();
					n.getSib().makeBlack();
					rotate(n.getNeice(),n.getSib());
				}
				else{
					if(n.getSib()!=null){n.getSib().makeRed();}
					n=n.getParent();
				}
			}
			n.makeBlack();
		}
	}

	private void rotate(Node n,Node p){
		if(n.getLorR()=="R"){rotateLeft(n);}//print(true);}//System.out.println("Rotate left");}
		else{rotateRight(n,p);}//print(true);}//System.out.println("Rotate right");}
	}


	private void rotateRight(Node n, Node p) {
		String lr = p.getLorR();
		Node r=root;
		Node g=n.getParent().getParent();
		Node y=n.getRight();
		if(y!=null){y.setParent(p);}
		p.setLeft(y);
		n.setRight(p);
		p.setParent(n);
		if(root.getData()==p.getData()){root=n;n.setParent(n);}
		else if(lr=="L"){n.setParent(g);g.setLeft(n);}
		else{n.setParent(g);g.setRight(n);}
	}


	private void rotateLeft(Node n) {
		Node p=n.getParent();
		root.setParent(null);
		String lr = isRoot(p);
		Node g=n.getParent().getParent();
		Node y=n.getLeft();
		p.setRight(n.getLeft());
		if(y!=null){y.setParent(p);}
		n.setParent(p.getParent());
		switch(lr){
		case("X"):
			root=n;
			break;
		case("L"):
			p.getParent().setLeft(n);
			break;
		case("R"):
			p.getParent().setRight(n);
			break;
		default:
		}
		n.setLeft(p);
		p.setParent(n);
		n.setDepth(n.getDepth()-1);
		p.setDepth(p.getDepth()+1);
		if(n.getRight()!=null){n.getRight().setDepth(n.getRight().getDepth()-1);}
		if(p.getLeft()!=null){p.getLeft().setDepth(p.getLeft().getDepth()-1);}
		root.setParent(root);
	}
	
}
