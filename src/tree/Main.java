package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Adam Pluth
 *
 */
public class Main {
	static ArrayList<String> words=new ArrayList<String>();
	static ArrayList<String> commands=new ArrayList<String>();
	static boolean bst=false;
	static boolean rbTree=false;
	static int argI=0;
	static Bst BST;
	static RedBlack RBTree;
	static boolean haveTree = false;
	/**
	 * 
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
		BST=new Bst();
		RBTree=new RedBlack();
		int i;
		i=processArgs(args);
		readInFiles(args[i]);
		if(bst){BST=new Bst(words);BST.setParents(BST.getRoot());}
		
		if(rbTree){RBTree=new RedBlack(words);RBTree.setParents(RBTree.getRoot());}
		haveTree=true;
		System.out.println("\n\t\tdone making tree\n");
		readInFiles(args[++i]);
		if(bst){
			BST.findMinMax(Bst.getRoot());
			BST=processCommands(BST,commands);		
			}
		if(rbTree){
			RBTree.findMinMax(RBTree.getRoot());
			RBTree=processCommands(RBTree,commands);
		}
	}
	/**
	 * 
	 * @param args reads in String of a file name strips punctuation lowers all capital letters and stores in global String ArrayList
	 */
	public static void readInFiles(String args){
		File file = new File(args);
		try {
			Scanner s = new Scanner(file);
			while(s.hasNext()){
				String word=s.next();
				word=word.toLowerCase();
				String temp="";
				char[] letters=word.toCharArray();
				for(int i=0;i<letters.length;i++){//strips non alpha chars
					if(Character.isLetter(letters[i])==true){
						temp+=letters[i];
					}
				}
				if(temp!=""){
					if(!haveTree){//while reading the text file
						words.add(temp);
					}
					else{//while reading command list
						commands.add(temp);
					}
				}
			}
			s.close();	
		}
		catch (FileNotFoundException e) {
			System.out.println("File not Found");
			e.printStackTrace();
			System.exit(0);
		}	
	}
	
	public static int processArgs(String[] args){
		int index=0;
		for(int i=0;i<args.length;i++){
			if(args[i].equals("-1")){
				bst=true;
				System.out.println("making a bst:");
				continue;
			}
			else if(args[i].equals("-2")){
				rbTree=true;
				System.out.println("making rbTree:");
				continue;
			}
			else{
				index=i;
				break;
			}
		}
		return index;
	}
	
	@SuppressWarnings("static-access")
	private static Bst processCommands(Bst b,ArrayList<String> c) {
		for(int i=0;i<c.size();i+=2){
			switch(c.get(i)){
			case("i"):
				b.ins(c.get(i+1));
				System.out.println(c.get(i+1)+" inserted");
				b.setParents(b.getRoot());
				break;
			case("d"):
				b.del(c.get(i+1));
				System.out.println(c.get(i+1)+" deleted");
				b.setParents(b.getRoot());
				break;
			case("f"):
				System.out.println("frequency of "+c.get(i+1)+": "+b.getFreq(c.get(i+1)));
				break;
			case("s"):
				System.out.println("\tshow bstree:");
				b.print(false);
				i--;
				break;
			case("r"):
				b.report();
				i--;
				break;
			}
		}
		return b;		
	}
	private static RedBlack processCommands(RedBlack r,ArrayList<String> c) {
		for(int i=0;i<c.size();i+=2){
			switch(c.get(i)){
			case("i"):
				System.out.println(c.get(i+1)+" inserted");
				r.ins(c.get(i+1));
				r.setParents(r.getRoot());
				break;
			case("d"):
				System.out.println(c.get(i+1)+" deleted");
				r.del(c.get(i+1));
				r.setParents(r.getRoot());
				break;
			case("f"):
				System.out.println("frequency of "+c.get(i+1)+": "+r.getFreq(c.get(i+1)));
				break;
			case("s"):
				System.out.println("\tshow rbtree:");
				if(r!=null){r.print(true);}
				else{System.out.println("!Empty tree!");}
				i--;
				break;
			case("r"):
				r.report();
				i--;
				break;
			default: 
				System.out.println("not a valid argument");
				i--;
			}
		}
		return r;		
	}

}