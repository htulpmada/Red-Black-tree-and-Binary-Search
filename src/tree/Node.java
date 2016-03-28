package tree;


public class Node {
	private Node Left,Right,Parent;
	private String data;
	private boolean red;
	private int frequency;
	private int depth;
	
	public Node(String s,int i){
		Left=Right=null;
		data=s;
		red=true;
		frequency=1;
		depth=i;
	}

	public Node getLeft(){return this.Left;}
	
	public Node getRight(){return this.Right;}
	
	public Node getParent(){return this.Parent;}
	
	public String getData(){return this.data;}
	
	public int getFreq(){return this.frequency;}
	
	public int getDepth(){return this.depth;}
	
	public boolean isRed(){return this.red==true;}
	
	public void setLeft(Node n){this.Left=n;}
	
	public void setRight(Node n){this.Right=n;}
	
	public void setParent(Node n){this.Parent=n;}
	
	public void setData(String s){this.data=s;}
	
	public void makeRed(){this.red=true;}
	
	public void makeBlack(){this.red=false;}
	
	public void incFreq(){this.frequency++;}
	
	public void decFreq(){this.frequency--;}

	public void setDepth(int d){this.depth=d;}
	
	public String getLorR() {
		if(this.Parent.Left!=null&&this.data==this.Parent.Left.data){return "L";}
		else {return "R";}
	}
}
