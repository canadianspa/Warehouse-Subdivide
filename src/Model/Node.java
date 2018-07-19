package Model;

import java.util.ArrayList;
import java.util.List;



public class Node {
    private List<Node> children = new ArrayList<Node>();
    private Node parent = null;
    private Directory data = null;
    private int depth;
    public static Node root;

    public Node(Directory data) {
        this.data = data;
        depth = 0;
        root = this;
    }

    public Node(Directory data, Node parent) {
        this.data = data;
        this.parent = parent;
        parent.children.add(this);
        depth = parent.depth + 1;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Directory getData() {
        return this.data;
    }

    public void setData(Directory data) {
        this.data = data;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeFromParent() {
        this.parent.children.remove(this);
    }
   
 

	public Node getParent() {
		return parent;
	}

	@Override
	public String toString() {
		String output = "";
		for(int i = 0; i < depth; i ++)
		{
			output += "  ";
		}
		output += data.name + System.lineSeparator();
		for(Node n: children)
		{
			output += n.toString();
		}
		return output;
	}
    
    
}