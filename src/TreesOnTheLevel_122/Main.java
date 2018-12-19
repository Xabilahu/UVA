package TreesOnTheLevel_122;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Main {
    static Scanner in;
    
    public void run() {
        while (in.hasNextLine()) {
          	String l = in.nextLine();
        	BT arbol = new BT();
        	boolean end = false;
        	while (!end){
        	   String[] a = l.split("\\s+");
        	   for (String s: a){
        		   if (s.equals("()")) end = true;
        		   else {
        			   String s2 = s.replaceAll("[()]", "");
        			   String[] a2 = s2.split(",");
        			   int val = Integer.parseInt(a2[0]);
        			   String path;
        			   if (a2.length == 1) path = ""; 
        			   else path = a2[1];
        			   arbol.insert(path, val);
        		   }
        	   }
        	   if (end != true) l = in.nextLine();
        	}
        	process(arbol);
        }
    }
    
    public void process(BT a) {
            if (!a.complete()) System.out.println("not complete"); // there cannot be -1 valued nodes
            else a.writeTreeByLevel();
    }
    

    private class BT {
    	BTN root;
    	
    	public void insert(String path, Integer val){
    		if (path.equals("")) {
    			if (root == null) root = new BTN(val);
    			else if (root.data != -1) root.data = -1; // impossible to insert the same value twice
    			else root.data = val;
    		}
    		else {
    			if (root == null) root = new BTN(-1);
    			BTN act = root;
    			for (int i = 0; i < path.length(); i++){
    				if (path.charAt(i) == 'L') {
    					if (act.left == null){
    						act.left = new BTN(-1);
    						act = act.left;
    					}
    					else act = act.left;
    				}
    				else if (path.charAt(i) == 'R') {
    					if (act.right == null){
    						act.right = new BTN(-1);
    						act = act.right;
    					}
    					else act = act.right;
    				}
    			}
    			if (act.data != -1) act.data = -1; // impossible to insert the same value twice
    			else act.data = val;
    		}
    	}
    	
        public boolean complete() {
        	return complete(root);
        }
    	
        private boolean complete(BTN a) {
            if (a == null) return true;
            else {
                if (a.data == -1) return false;
                else return this.complete(a.left) && this.complete(a.right);
            }
        }
        
        public void writeTreeByLevel(){
            
            LinkedList<Integer> list = new LinkedList<>();
            this.fillListByLevel(list, root);

        	// print elements
        	boolean prim = true;
        	for (int x: list){
        		if (prim) prim = false;
        		else System.out.print(" ");
        		System.out.print(x);
        	}
        	System.out.println();
        }

        private void fillListByLevel(LinkedList<Integer> list, BTN node) {
            if (node != null){
                Queue<BTN> nodeQueue = new LinkedList<>();
                nodeQueue.offer(node);
                while(!nodeQueue.isEmpty()) {
                    BTN actualNode = nodeQueue.remove();
                    list.add(actualNode.data);
                    if (actualNode.left != null) nodeQueue.offer(actualNode.left);
                    if (actualNode.right != null) nodeQueue.offer(actualNode.right);
                }
            }
        }
        
        public String toString() {
        	return toString(root);
        }
        
        private String toString(BTN a) {
        	if (a == null) {return "";}
        	else return "[" + a.data + toString(a.left) + toString(a.right) + "]";
        }
    }

    private class BTN {
    	Integer data;
    	BTN left;
    	BTN right;
    	
    	public BTN(int n){
    		data = n;
    		left = null;
    		right = null;
    	}
    }
    
    public static void main(String[] args) throws Exception {
        in = new Scanner(System.in);
        new Main().run();
        in.close();
    }
}