package dojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import dojo.Order;


public class CocktailStepDefinitions{
	private Order order;
	private Menu menu;
	private Payment paypal;
	
	@Given("^(.*) who wants to buy a drink$")
	public void romeo_who_wants_to_buy_a_drink(String romeo){
		order = new Order();
		order.declareOwner(romeo);
	}
		
	@When("^an order is declared for (.*)$")
	public void an_order_is_declared_for_Juliette(String juliette){
		order.declareTarget(juliette);
	}
	
	@When("^a message saying \"([^\"]*)\" is added$")
	public void a_message_saying_something_is_added(String something){
		order.withMessage(something);
	}
	
	@When("^a mocked menu is used$")
	public void a_mocked_menu_is_used(){
		menu = mock(Menu.class);
		order.useMenu(menu);
	}
	
	
	@When("^the mock binds #(\\d+) to (.*)$")
	public void the_mock_binds_Id_to_Cocktail(int id, String cocktail){
		when(menu.getPrettyName(id)).thenReturn(cocktail);
	}
	
	@When("^the price binds #(\\d+) to (\\d+)$")
	public void the_mock_binds_Id_to_Cocktail(int id, int price){
		when(menu.getPrice(id)).thenReturn(price);
	}
	
	@When("^a cocktail #(\\d+) is added to the order$")
	public void a_cocktail_C_is_added_to_the_order(int C){
		order.addCocktail(C);
	}
	
	@When("^Romeo pays his order$")
	public void romeo_pays_his_order(){
		paypal = mock(Payment.class);
		Cashier.processOrder(paypal, order);;
	}
	
	@Then("^the payment component must be invoked (\\d+) time for (\\d+)")
	public void the_payment_component_must_be_invoked_N_times(int n, int value){
		verify(paypal, times(n)).performPayment(value);
	}
	
	@Then("^the order contains a (.*)")
	public void the_order_contains_a_given_cocktail(String givenCocktail){
		assertTrue(order.getCocktails().contains(givenCocktail));
	}
	
	@Then("^there is (\\d+) cocktails in the order$")
	public void there_is_no_cocktail_in_the_order(int n){
		List<String> cocktails = order.getCocktails();
		assertEquals(n, cocktails.size());
	}
	
	@Then("^the ticket must say \"([^\"]*)\"$")
	public void the_ticket_must_say_something_else(String somethingElse){
		String expected = String.format("From %s to %s: %s",  order.getOwner(), order.getTarget(),order.getMessage());
		assertEquals(expected, order.getTicketMessage());
	}
}