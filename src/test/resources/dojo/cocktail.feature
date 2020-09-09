Feature: Cocktail Ordering

As Romeo, I want to offer a drink to that naughty Juliette so that we can discuss together (and maybe more).

#Scenario: Creating an empty order
#	Given Romeo who wants to buy a drink
#	When an order is declared for Juliette
#	Then there is 0 cocktail in the order 
	
#Scenario: Sending a message with an order
#	Given Romeo who wants to buy a drink
##	When an order is declared for Juliette
#	And a message saying "Wanna chat?" is added
#	Then the ticket must say "From Roemeo to Juliette: Wanna chat?"

Background:
	Given Romeo who wants to buy a drink
	When an order is declared for Juliette

Scenario: Creating an empty order
	Then there is 0 cocktails in the order
	
Scenario Outline: Sending a message with an order
	Given <from> who wants to buy a drink
	When an order is declared for <to>
		And a message saying "<message>" is added
	Then the ticket must say "<expected>"
	
Examples:
| from | to | message | expected |
| Romeo | Juliette | wanna chat? | From Romeo to Juliette: Wanna chat? |
| Tom | Jerry | Hei! | From Tom to Jerry: Hei! |

Scenario: Offering a mojito to Juliette
When a mocked menu is used
And the mock binds #42 to mojito
And a cocktail #42 is added to the order
Then there is 1 cocktails in the order
And the order contains a mojito

Scenario: Paying the mojito offered to Juliette
When a mocked menu is used
And the mock binds #42 to mojito
And the price binds #42 to 10
And a cocktail #42 is added to the order
And Romeo pays his order
Then the payment component must be invoked 1 time for 10