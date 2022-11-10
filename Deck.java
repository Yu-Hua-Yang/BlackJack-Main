//contains all the methods that the deck command is gonna use throughout the main method
import java.util.Random;
public class Deck {
    public DynamicCardArray fullDECK;
    public Random random;

    Random rand = new Random();

    //creation of the deck with nested loop looping over every enum and making all the possibilities 
    public Deck(){
        this.fullDECK = new DynamicCardArray();

        for (Suit suits : Suit.values()) {
            for (Value values : Value.values()) {
                Card card = new Card(values,suits);
                fullDECK.add(card);
            }
        }
    }

    //made to print out a string that shows all of the cards in the deck
    public String toString(){
        String Dprint = "The deck holds the cards in this order: ";

        for(int i = 0; i<this.fullDECK.length(); i++){
            if(i == this.fullDECK.length()){
                Dprint += fullDECK.get(i);
            }
            Dprint += fullDECK.get(i) + ", ";
        }

        return Dprint;
    }

    //using a random value chosen by the util from java it gives us a number between 0 and the value next from the DynamicCardArray
    //it has 2 variables one holding the number of index from random and other that holds the card at the index with the get method
    //then with the created DynamicCardArray we insted the card we held with the variable and add it to the array
    //then it swaps then with a for loop taking the cards from Replace array and adding it back to the deck array
    public void Shuffle(){
        DynamicCardArray Replace = new DynamicCardArray();
        for(int i = 0; i<this.fullDECK.length(); i++){
            int randomCard = rand.nextInt(this.fullDECK.length());
            Card tempHold = this.fullDECK.get(randomCard);
            this.fullDECK.remove(randomCard);
            Replace.add(tempHold);
        }  
        for(int i = 0; i< Replace.length();i++){
        Card inserts = Replace.get(i);
        this.fullDECK.add(inserts);
        }
    }
    
    //it returns the deck 
    public DynamicCardArray getDeck(){
        return fullDECK;
    }
    
    //it uses the method from the DynamicCardArray so that I can use it on the deck object in the main method
    public Card getCardFromTop(){
       return this.fullDECK.getCardFromTop();
    }
}