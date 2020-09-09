package dojo;

import java.util.ArrayList;
import java.util.List;

public class Order{
	private String from;
	private String to;
	private String message;
	private Menu menu;
	private List<String> contents = new ArrayList<String>();
	private int amount;
	
	public void declareOwner(String romeo){
		this.from = romeo;
	}
	
	public void declareTarget(String juliette){
		this.to = juliette;
	}
	
	public List<String> getCocktails(){
		return contents;
	}
	
	public String getOwner(){return from;}
	public String getTarget(){return to;}
	public String getMessage(){return message;}
	public String getTicketMessage(){return "From " + from + " to " + to + ": " + message;}

	public void withMessage(String something) {
		// TODO Auto-generated method stub
		this.message = something;
	}

	public void useMenu(Menu menu) {
		// TODO Auto-generated method stub
		this.menu = menu;
	}

	public void addCocktail(int c) {
		// TODO Auto-generated method stub
		this.contents.add(menu.getPrettyName(c));
		this.amount += menu.getPrice(c);
	}
	
	public int getBillAmount(){return amount;}
}