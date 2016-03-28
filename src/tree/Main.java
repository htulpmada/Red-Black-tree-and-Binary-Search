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
	static boolean haveTree = false;
	/**
	 * 
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
		processArgs(args);
		readInFiles(args[1]);
		BST=new Bst(words);
		Bst.setParents(Bst.getRoot());
		haveTree=true;
		readInFiles(args[2]);
		//System.out.println();
		Bst.findMinMax(Bst.getRoot());
		BST=processCommands(BST,commands);
		//Bst.setParents(Bst.getRoot());
		//Bst.print();
		
	}

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
	
	public static void processArgs(String[] args){
		for(int i=0;i<args.length;i++){
			if(args[i].equals("-1")){
				bst=true;
				System.out.println("making a bst:");					
			}
			if(args[i].equals("-2")){
				rbTree=true;
				System.out.println("making rbTree:");
			}
		}
	}

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
				System.out.println("\tshow tree:");
				b.print();
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

}
