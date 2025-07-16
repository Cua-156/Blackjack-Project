import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Logic{
    Display display = new Display();
    Scanner in =  new Scanner(System.in);
    Random rand = new Random();
    String userIn = new String();

    ArrayList pcards = new ArrayList();
    ArrayList dcards = new ArrayList();
    int card = 0;
    int pTotal = 0;
    int dTotal = 0;

    boolean lost = false;
    boolean playing = true;

    public void start(){
        System.out.println("Welcome to Blackjack please press enter to begin");
        userIn = in.nextLine();

        //One round consists of a User's Turn and then the Dealer's turn
        while(playing){
            System.out.println("__________________________________");
            userTurn();
            dealerTurn();
            again();
        }
    }

    private void userTurn(){
        //Initial Draw
        System.out.println("Your Turn: ");
        
        draw("Player");
        draw("Player");

        // for(int i = 0; i < pcards.size(); i++){
        //     pTotal += (int) pcards.get(i);
        // }

        System.out.println("Your current total is " + pTotal);
        if(pTotal == 21){
            System.out.println("You've already hit 21, You Win!");
        }
        else{
            while(!userIn.equals("Stand") || !userIn.equals("s") || lost != true){
                System.out.print("Type Stand/s or Hit/h: ");
                userIn = in.nextLine();
                if(userIn.equals("Hit") || userIn.equals("h")){
                    draw("Player");
                    if(pTotal == 21){System.out.println("You've hit 21, You Win!");}
                    else if(pTotal > 21){
                        System.out.println("You've Busted");
                        lost = true;
                    }
                    else{System.out.println("Your new total is " + pTotal);}
                }
                else if(userIn.equals("Stand") || userIn.equals("s")){}
                else{
                    System.out.println("Please enter a valid input");
                }
                System.out.println("Debug: userIn = " + userIn);
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
                if(dTotal > 21){
                    System.out.println("The Dealer has busted. You Win!");
                }
            }
            if(pTotal > dTotal){System.out.println("The Dealer got " + dTotal + ", which is smaller than your " + pTotal + " so you've Won!");}
            else if(pTotal == dTotal){System.out.println("Both you and the Dealer got " + dTotal + " so you've tied");}
            else{System.out.println("The Dealer got " + dTotal + ", which is larger than your " + pTotal + " so you've lost.");}
        }
    }

    private void draw(String who){
        if(who.equals("Player")){
            card = rand.nextInt(12) + 1;
            System.out.println("You drew " + cardCheck(card));
            if(card > 10){card = 10;}
            pcards.add(card);
            pTotal += card;
        }
        else if(who.equals("Dealer")){
            card = rand.nextInt(12) + 1;
            System.out.println("The Dealer drew " + cardCheck(card));
            if(card > 10){card = 10;}
            dcards.add(card);
            dTotal += card;
        }

    }
    private String cardCheck(int num){
        if(num == 11){return "a Jack";}
        else if(num == 12){return "a Queen";}
        else if(num == 13){return "a King";}
        else if(num == 1){return "an Ace";}
        else{return Integer.toString(num);}
    }
    private void again(){
        System.out.println("Would you like to play again? Y/N");
        while(!userIn.equals("Y") || !userIn.equals("N")){
            System.out.println("Would you like to play again? Y/N");
            userIn = in.nextLine();
            if(userIn.equals("Y")){playing = true;}
            else if(userIn.equals("N")){playing = false;}
            else{System.out.println("Please give a valid answer");}
        }
    }
}
