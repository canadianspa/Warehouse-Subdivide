package Model;

public class main {

	public static void main(String[] args) {
	
		Directory canSpa = new Directory("Canadian Spa"); 
		Node ccanspa = new Node(canSpa); 
		Directory l1 = new Directory("l1");
		Directory l2 = new Directory("l2");
		Node nl1 = new Node(l1,ccanspa); 
		Node nl2 = new Node(l2,ccanspa); 
		Directory w1 = new Directory("w1"); 
		Directory w2 = new Directory("w2"); 
		Directory w3 = new Directory("w3"); 
		Node nw1 = new Node(w1,nl1); 
		Node nw2 = new Node(w2,nl2); 
		Node nw3 = new Node(w3,nl2); 



		
		System.out.println(ccanspa);


	}

}
