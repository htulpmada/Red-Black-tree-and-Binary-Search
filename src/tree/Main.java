package tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<String> words=new ArrayList<String>();
	static boolean bst=false;
	static boolean rbTree=false;
	static int argI=0;
	static Bst BST;
	
	
	
	public static void main(String[] args) {
		processArgs(args);
		readInFiles(args);
		//tester to make sure words are stripped
		for(String w :words){
			System.out.println(w);					
		}
		BST=new Bst(words);
	}

	
	public static void readInFiles(String args[]){
		File file = new File(args[1]);
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
				if(temp!=""){words.add(temp);}
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
				System.out.println("bst");					
			}
			if(args[i].equals("-2")){
				rbTree=true;
				System.out.println("rbTree");
			}
		}
		
	}
}
