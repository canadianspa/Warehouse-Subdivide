package Model;
import java.util.ArrayList;

public class Zone extends Directory {
	
	ArrayList<Item> listOfItems;
	public Zone(String name) {
		super(name);
		listOfItems =  new ArrayList<Item>();
	}
	@Override
	public String toString() {
		String output = "";
		output += name + System.lineSeparator();
		for(Item i: listOfItems)
		{
			output += i + System.lineSeparator();
		}
		return output;
	}
	
	public void addItem(Item i) 
	{
		listOfItems.add(i);
	}
	
	public void removeItem(Item i) 
	{
		listOfItems.remove(i);
	}
	
	public boolean containsItem(Item i)
	{
		return listOfItems.contains(i);
	}
	
	
	
	
	

}
