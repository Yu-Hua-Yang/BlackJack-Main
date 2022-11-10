//main game and program for blackjack
import java.util.Scanner;
public class BlackJack {
    public static void main(String[] args) {

        //initializing all the variables that doesn't get reset when game restarts
        Scanner Scan = new Scanner(System.in);
        Scanner intScan = new Scanner(System.in);
        String stringInput = "";
        int playerBet = 0;
        int totalMoney = 100;
        boolean notPlaying = false;
       
        //printing out the introduction and reading if you would like to see or not prints with helper method
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" [|*|]                      Welcome to the game of BLACKJACK                    [|*|] ");
        System.out.println(" [|*|]   If you would like to read the Rules and the Values of Cards enter Y    [|*|] ");
		System.out.println(" [|*|]          If you no enter any character to continue to the game           [|*|] ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        stringInput = Scan.nextLine();
        if(stringInput.equals("y") || stringInput.equals("Y")){
            System.out.println(rulesAndScoringSystem() + "\n");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(" [|*|]                           Please Enjoy The Game                          [|*|] ");
        System.out.println(" [|*|]                        MADE BY: YU HUA YANG 2133677                      [|*|] ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //The Game actual functioning game that is looped over and over again with a continue if you play
        while (!notPlaying) {
            
            //creation of deck, player, and dealer object and shuffling the deck
            Deck deck = new Deck();
        
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();
            deck.Shuffle();

            Player player = new Player();
            Dealer dealer = new Dealer();

            //creation of boolean for the while loop of the player's turn
            boolean isPlayOver = false;

            //boolean for the validation of the inputed money
            boolean betCheck = false;
            while(!betCheck){
                try{
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]   How much would you like to bet out of the " + totalMoney + "$ you have input number   [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    //Validation and taking input of amount for bet and subtracting from total Money
                    playerBet = intScan.nextInt();
                    isbetSmaller(playerBet, totalMoney);
                    betCheck = true;
                }
                catch(IllegalArgumentException e){
                    System.out.println(e + "\nPlease Re-Enter");
                }
            }
                totalMoney -= playerBet;
            

            //Informing of player money they bet and how much they have left to bet
            System.out.println("You have " + totalMoney + "$ left to bet");
            System.out.println("You have placed a " + playerBet + "$ bet");

            //Dealing Cards to both Player and Dealer before turn
            player.Draw(deck.getCardFromTop());
            player.Draw(deck.getCardFromTop());
            dealer.Draw(deck.getCardFromTop());
            dealer.Draw(deck.getCardFromTop());

            //initializing variables to check how much Dealer and Player has so can calculate who wins
            int howMuchD = 0;
            int howMuchP = 0;
            String ifAce = "";

            //Player turns followed by the dealers turn
            //data validation so that they only input H or S
            while (!isPlayOver) {              
				boolean HSCheck = false;
				
				while(!HSCheck){
				try{
					System.out.println(player.toString());
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println(" [|*|]       Do you want to HIT or STAND enter H for HIT and S for STAND        [|*|] ");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					
					stringInput = Scan.nextLine();
					checkSorH(stringInput);
					HSCheck = true;
				}
				catch(IllegalArgumentException e){
					System.out.println(e + "\nPlease Re-Enter");
				}
				}
                //if you hit then it is gonna run program where it asks you if you would like to have your aces to be equal one or eleven
                if(stringInput.equals("h") || stringInput.equals("H")){
                    player.Draw(deck.getCardFromTop());
					
					boolean exceptionCatch = false;
					
                    //data validation for your input of One or Eleven
					while(!exceptionCatch){
						try{
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							System.out.println(" [|*|]      If you have ACES enter One or Eleven for it to be either value      [|*|] ");
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

							ifAce = Scan.nextLine();
                            checkOneorEleven(ifAce);
							exceptionCatch = true;
						}
						catch(IllegalArgumentException e){
							System.out.println(e + "\nPlease Re-Enter");
						}
					}

                    //runs method isPHandBust to calculate if your hand is above 21 and if not it will loop back again
                    isPlayOver = player.isPHandBust(ifAce, totalMoney);
                    howMuchP = player.CalculateValue(ifAce);
                    continue;
                }
                //if you stand it will run the dealers turn
                else if (stringInput.equals("s") || stringInput.equals("S")){
                    howMuchP = player.CalculateValue(ifAce);

                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]                Now it is the dealer's turn to hit or stand               [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    boolean isdealerTurn = false;

                    //dealer is fully automated but is running in it's own while loop to calcualte the value if hand is under 17 must hit
                    //the dealer's aces are also automatically identidied and chosen depending on the sum of the hand
                    while (!isdealerTurn){
                            howMuchD = dealer.CalculateValue();

                        if(!(howMuchD >= 17)){
                            dealer.Draw(deck.getCardFromTop());
							howMuchD = dealer.CalculateValue();
                            isdealerTurn = dealer.isDHandBust(playerBet, totalMoney);
                            isPlayOver = dealer.tobreakSecondLoop();
                            continue;
                        }
                        else{
                            break;
                        }
                    }
                }
                
                //Three different conclusion to game besides the busts that will print dependant on the sum of both the player and dealer
                //The dealer bigger hand then player dealer won prints you lost and lose all of bet
                if(howMuchD > howMuchP && !(howMuchD > 21) && !(howMuchP > 21)){
                    System.out.println("You lost the Dealer had a bigger number then you, they had " + howMuchD + " you have " + howMuchP);
                    String lost = "Lost";
                    totalMoney = outcomeOfBet(lost, playerBet, totalMoney);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]          Better Luck Next Time Please Come Again And Play Again          [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    break;
                }
                //the player has a bigger hand then the dealer prints out player won and doubles the bet and adds it to totalMoney
                else if(howMuchD < howMuchP && !(howMuchD > 21) && !(howMuchP > 21)){
                    System.out.println("You won the Dealer had a smaller number then you, you have " + howMuchP + " they had " + howMuchD);
                    String win = "Win";
                    totalMoney = outcomeOfBet(win, playerBet, totalMoney);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]       Congradualtions On Winning Please Come Again And Play Again        [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    break;
                }
                //if draw the player wins because of casino rules printing out that the player won and doubles the bet and adds it to totalMoney
                else if(howMuchD == howMuchP && !(howMuchD > 21) && !(howMuchP > 21)){
                    System.out.println("You won because the Casino's rules if the Dealer and the player have ");
                    System.out.println("the same number you win, you had " + howMuchP + " they had " + howMuchD);
                    String win = "Win";
                    totalMoney = outcomeOfBet(win, playerBet, totalMoney);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(" [|*|]       Congradualtions On Winning Please Come Again And Play Again        [|*|] ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    break;
                }
            }

            //checks if game is over because player has no more money if yes it breaks is not then continues the loop
            notPlaying = isGameOver(totalMoney);
			if(notPlaying){
				break;
			}
			
            //intialize variable for the try and catch
			boolean PLCheck = false;

            //data validation to only accept P and L
			while(!PLCheck){
			try{
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println(" [|*|]     Now That The Game Is Over You May Choose To Play Again or Leave      [|*|] ");
				System.out.println(" [|*|]                     Enter P for Play or L for Leave                      [|*|] ");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

				stringInput = Scan.nextLine();
				checkPorL(stringInput);
				PLCheck = true;
			}
			catch(IllegalArgumentException e){
				System.out.println(e + "\nPlease Re-Enter");
			}
			}
			
            //if P runs code again with a continue line but if L then it will quit game with a little message
            if(stringInput.equals("p") || stringInput.equals("P")){
                System.out.println("You have chosen to play another one best of luck to you");
                continue;
            }
            else if(stringInput.equals("l") || stringInput.equals("L")){
                System.out.println("You have chosen to leave have a good day");
                break;
			}
            
        }
    }

    //helper method using Stringbuilder ot print out rules and scoring system
    public static String rulesAndScoringSystem() {
        StringBuilder builder = new StringBuilder();

        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        builder.append("[|*|]                    Rules and Scoring System of BlackJack                   [|*|]\n");
        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        builder.append("[[|]]                    Rules: You start with 100 dollars                       [[|]]\n");
        builder.append(" | |                     and you lose when you have no money                      | | \n");
        builder.append(" | |                     left or when you feel like leaving                       | | \n");
        builder.append(" | |                     the game as a whole. You will recieve                    | | \n");
        builder.append(" | |                     2 cards alongside the Dealer's 2 cards                   | | \n");
        builder.append(" | |                     and you have to have a sum of 21 or under.               | | \n");
        builder.append(" | |                     any higher will result in a bust and                     | | \n");
        builder.append(" | |                     if your number is less then the dealer                   | | \n");
        builder.append(" | |                     you will automatically lose your bet                     | | \n");
        builder.append(" | |                     because the dealer won. Though you                       | | \n");
        builder.append(" | |                     will get the option to either hit or stand               | | \n");
        builder.append("[[|]]                    or for the ace to be a 1 or a 11.                       [[|]]\n");
        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        builder.append("[[|]]                    HIT: you will be dealt another card                     [[|]]\n");
        builder.append(" | |                     STAND: you hold position and let the                     | | \n");
        builder.append("[[|]]                    dealer choose if he hits or stands.                     [[|]]\n");
        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        builder.append("[[|]]                               VALUES OF CARDS                              [[|]]\n");
        builder.append(" | |                     [ACE]-----------------------[1 or 11]                    | | \n");
        builder.append(" | |                     [TWO]-----------------------------[2]                    | | \n");
        builder.append(" | |                     [THREE]---------------------------[3]                    | | \n");
        builder.append(" | |                     [FOUR]----------------------------[4]                    | | \n");
        builder.append(" | |                     [FIFTH]---------------------------[5]                    | | \n");
        builder.append(" | |                     [SIX]-----------------------------[6]                    | | \n");
        builder.append(" | |                     [SEVEN]---------------------------[7]                    | | \n");
        builder.append(" | |                     [EIGHT]---------------------------[8]                    | | \n");
        builder.append(" | |                     [NINE]----------------------------[9]                    | | \n");
        builder.append(" | |                     [TEN]----------------------------[10]                    | | \n");
        builder.append(" | |                     [JACK]---------------------------[10]                    | | \n");
        builder.append(" | |                     [QUEEN]--------------------------[10]                    | | \n");
        builder.append("[[|]]                    [KING]---------------------------[10]                   [[|]]\n");
        builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        return builder.toString();
    }

    //helper method that calculates the bets and how much it goes up by and includes a message that is printed
    public static int outcomeOfBet(String outcome, int playerBet, int totalMoney){
        if(outcome.equals("Win")){
            playerBet = playerBet*2;
            totalMoney += playerBet;
            System.out.println("You have doubled your profits and your new total amount of money is: " + totalMoney);
        }
        else if(outcome.equals("Lost")){
            System.out.println("You have lost your bet and now your new total amount of money is: " + totalMoney);
        }
        return totalMoney; 
    }

    //checks if you have no more money to bet causing it to return true and break the loop
    public static boolean isGameOver(int totalMoney){
        boolean isOver = false;
        
        if(totalMoney == 0){
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(" [|*|]         You no longer have money to bet please leave the table           [|*|] ");
            System.out.println(" [|*|]                    Come back when you have more money                    [|*|] ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            isOver = true;
        }
        return isOver;
    }
	
    //data validation helper method for the try and catch
	public static boolean checkSorH(String input){
		boolean result = false;
		
		if(input.equals("H") || input.equals("h") || input.equals("S") || input.equals("s")){
			result = true;
		}
		else{
			throw new IllegalArgumentException("Input must be S or H");
		}
		
		return result;
	}
	
    //data validation helper method for the try and catch
	public static boolean checkPorL(String input){
		boolean result = false;
		
		if(input.equals("P") || input.equals("p") || input.equals("L") || input.equals("l")){
			result = true;
		}
		else{
			throw new IllegalArgumentException("Input must be P or L");
		}
		
		return result;
	}

    //data validation helper method for the try and catch
    public static boolean checkOneorEleven(String input){
        boolean result = false;

        if(input.equals("One") || input.equals("one") || input.equals("Eleven") || input.equals("eleven")){
            result = true;
        }
        else{
            throw new IllegalArgumentException("Input must me One or Eleven");
        }

        return result;
    }

    //data validation helper method for the try and catch
    public static boolean isbetSmaller(int bet, int total){
        boolean result = false;

        if(bet <= total){
            result = true;
        }
        else{
            throw new IllegalArgumentException("Input must be a valid amount you have");
        }

        return result;
    }
}
