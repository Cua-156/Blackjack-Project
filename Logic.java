import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Logic{
    //Display display = new Display(); //Unused Display
    Scanner in =  new Scanner(System.in); //Used to get the user's imput
    Random rand = new Random(); //Used to generate the cards
    String userIn = new String(); //The user's input

    ArrayList pcards = new ArrayList(); //Was going to be used in the case of Aces being either 1 or 11
    ArrayList dcards = new ArrayList(); //Was going to be used in the case of Aces being either 1 or 11
    int card = 0; //The current card being looked at
    int pTotal = 0; //Total amount for the player
    int dTotal = 0; //Total amount for the Dealer

    boolean lost = false; //Tells us if the player has lost
    boolean playing = true; //Tells us if the player still wants to play

    public void start(){
        System.out.println("Welcome to Blackjack please press enter to begin");
        userIn = in.nextLine();

        //One round consists of a User's Turn and then the Dealer's turn
        while(playing){
            System.out.println("__________________________________");
            userTurn();
            dealerTurn();
            again(); //used to ask the player if they want to play again
        }
    }

    private void userTurn(){
        //Initial Draw
        System.out.println("Your Turn: ");
        
        //At the start of the player's round, draw 2 cards
        draw("Player");
        draw("Player");

        //Was going to be used with the Arraylist
        // for(int i = 0; i < pcards.size(); i++){
        //     pTotal += (int) pcards.get(i);
        // }

        System.out.println("Your current total is " + pTotal);
        if(pTotal == 21){
            System.out.println("You've already hit 21, You Win!");
        }
        else{
            while(!userIn.equals("Stand") && !userIn.equals("s") && !lost){
                System.out.print("Type Stand/s or Hit/h: ");
                userIn = in.nextLine();
                if(userIn.equals("Hit") || userIn.equals("h")){
                    //Player Hits, draw new card
                    draw("Player");
                    //Player has hit 21 and automatically wins
                    if(pTotal == 21){System.out.println("You've hit 21, You Win!");}
                    //Player has over 21 automatically losing
                    else if(pTotal > 21){
                        System.out.println("You've Busted");
                        lost = true;   
                    }
                    else{System.out.println("Your new total is " + pTotal);}
                }
                //Player chooses not to draw a card, nothing happens
                else if(userIn.equals("Stand") || userIn.equals("s")){}
                else{
                    System.out.println("Please enter a valid input");
                }
            }
        }
    }
    private void dealerTurn(){
        if(!lost){
            System.out.println("Dealer's Turn: ");
            draw("Dealer");
            draw("Dealer");
            while(dTotal < 17){
                draw("Dealer");
            }
            //Calculates win/lose
            if(dTotal > 21){System.out.println("The Dealer has busted. You Win!");}
            else if(pTotal > dTotal){System.out.println("The Dealer got " + dTotal + ", which is smaller than your " + pTotal + " so you've Won!");}
            else if(pTotal == dTotal){System.out.println("Both you and the Dealer got " + dTotal + " so you've tied");}
            else{System.out.println("The Dealer got " + dTotal + ", which is larger than your " + pTotal + " so you've lost.");}
        }
    }

    private void draw(String who){
        if(who.equals("Player")){
            card = rand.nextInt(12) + 1; //Card draw
            System.out.println("You drew " + cardCheck(card));
            if(card > 10){card = 10;} //All cards above 10 are valued at 10
            //pcards.add(card); //Was going to be used with the Arraylist
            pTotal += card;
        }
        else if(who.equals("Dealer")){
            card = rand.nextInt(12) + 1; //Card draw
            System.out.println("The Dealer drew " + cardCheck(card));
            if(card > 10){card = 10;} //All cards above 10 are valued at 10
            //dcards.add(card); //Was going to be used with the Arraylist
            dTotal += card;
        }

    }
    //Used to see if they drew above a ten and what it would be
    private String cardCheck(int num){
        if(num == 11){return "a Jack";}
        else if(num == 12){return "a Queen";}
        else if(num == 13){return "a King";}
        else if(num == 1){return "an Ace";}
        else{return Integer.toString(num);}
    }
    private void again(){
        while(!userIn.equals("Y") && !userIn.equals("N")){
            System.out.println("Would you like to play again? Y/N");
            userIn = in.nextLine();
            if(userIn.equals("Y")){playing = true;}
            else if(userIn.equals("N")){playing = false;}
            else{System.out.println("Please give a valid answer");}
        }
    }
}
