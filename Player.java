//contains all methods that I will need to call back on when using player object for the hand
public class Player {

    private DynamicCardArray hand;

    public Player(){
        this.hand = new DynamicCardArray();
    }

    //prints out your hand 
    public String toString(){
        String phand = "Your hand contains: ";
        
        for(int i = 0; i<this.hand.length(); i++){
        phand += this.hand.get(i) + ", ";   
        }

        return phand;
    }

    //takes a input so you can choose your input for a switch either one or eleven and calculates the value of your sum 
    //by taking the hard coded final values of the card and adds them together
    public int CalculateValue(String Pchoice){
        int sum = 0;

        for(int i = 0; i<this.hand.length(); i++){
            int CardValue = this.hand.get(i).getValue();
            if(CardValue == 1){
                switch(Pchoice){
                    case "One": 
                        sum++;
                        break;
                    case "Eleven":
                        sum += 11;
                        break;
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

    //checks if your hand has a number above 21 so it sends back a boolean to break the while loop it is in
    //takes a int input to print the amount of money alongside a Pchoice to run the CalculateValute method
    public Boolean isPHandBust(String Pchoice, int amountMoney){
        Boolean Result = false;
        if(CalculateValue(Pchoice) > 21){
            System.out.println(toString());
            System.out.println("Your hand is a bust you lost the gamble.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" [|*|]          Better Luck Next Time Please Come Again And Play Again          [|*|] ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("You have lost your bet and now your new total amount of money is: " + amountMoney);
            Result = true;
        }
        else{
            System.out.println("Your Total Sum is: " + CalculateValue(Pchoice));
        }
        return Result;
        
    }
}