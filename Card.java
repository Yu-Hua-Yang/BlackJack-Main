//contains all method for the card and everything to make a card
public class Card {
    private Value values; 
    private Suit suits;

    //gets the integer value of the card
    public int getValue(){
        return this.values.getValues();
    }

    //gets the suit value of the card
    public Suit getSuit(){
        return this.suits;
    }

    //prints out the card and is synergized with the other toString methods when it prints
    public String toString(){
        String card = this.values + " of " + this.suits;
        return card;
    }

    //constructor to create a card
    public Card(Value values, Suit suits){
        this.values = values;
        this.suits = suits;
    }
}