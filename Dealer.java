//contains all the methods created for my dealer object to use in the main method
public class Dealer {
    
    private DynamicCardArray hand;

    public Dealer(){
        this.hand = new DynamicCardArray();
    }

    //prints out the hand of the dealer
    public String toString(){
        String phand = "The Dealer's hand contained: ";
        
        for(int i = 0; i<this.hand.length(); i++){
        phand += this.hand.get(i) + ", ";   
        }

        return phand;
    }

    //same as the player it takes the dealers hand an the hard coded values assosiated and automatically chooses if aces should be a one or eleven
    public int CalculateValue(){
        int sum = 0;

        for(int i = 0; i<this.hand.length(); i++){
            int CardValue = this.hand.get(i).getValue();
            if(CardValue == 1){
                if(sum <= 10){
                    sum += 11;
                }
                else{
                    sum += 1;
                }
            }
            else{
                sum += CardValue;
            }
        }
        return sum;
    }

    //draws a card using the add command and the getcardfromtop method in the main method as a input
    public void Draw(Card c){
        this.hand.add(c);
    }

    //takes as input totalbet and int money to calculate and print out the amount of money left
    //and it makes it so that if the dealer has over 21 it will bust and return false causing while loop to break
    public boolean isDHandBust(int playerBet, int totalMoney){
        boolean Result = false;
        if(CalculateValue() > 21){
            System.out.println("You won the gamble Dealer hand is a bust.");
            playerBet = playerBet*2;
            totalMoney += playerBet;
            System.out.println("You have doubled your profits and your new total amount of money is: " + totalMoney);
            Result = true;
        }
        return Result;
    }

    //couldn't fix first one to break the loop that it is nested in so I made second method to break the second loop
    public boolean tobreakSecondLoop(){
        boolean Result = false;
        if(CalculateValue() > 21){
            Result = true;
        }
        return Result;
    }
}
