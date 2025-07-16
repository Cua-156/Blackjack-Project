import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Logic{
    Display display = new Display();
    Scanner in =  new Scanner(System.in);
    Random rand = new Random();

    ArrayList pcards = new ArrayList();
    int[] dcards = new int[5];
    int card = 0;
    String userIn = new String();

    public void start(){
        System.out.println("Welcome to Blackjack please press enter to begin");
        //in.nextLine();

        System.out.println();
        userTurn();
    }

    private void userTurn(){
        //Initial Draw
        System.out.println("Your Turn: ");

        card = rand.nextInt(12) + 1;
        System.out.print("You drew " + cardCheck(card));
        if(card > 10){card = 10;}
        pcards.add(card);

        card = rand.nextInt(12) + 1;
        System.out.print(" and " + cardCheck(card));
        if(card > 10){card = 10;}
        pcards.add(card);

        System.out.println(userIn);

        while( !in.equals("Stand") ){
            System.out.print("Type Stand or Hit: ");
            if(in.equals("Hit")){
                card = rand.nextInt(12) + 1;
                System.out.print("You drew " + cardCheck(card));
                if(card > 10){card = 10;}
                pcards.add(card);
            }
        }
       
    
    }
    private void dealerTurn(){

    }
    private int aceCheck(int num){
        return 0;
    }
    private String cardCheck(int num){
        if(num == 11){return "Jack";}
        else if(num == 12){return "Queen";}
        else if(num == 13){return "King";}
        else if(num == 1){return "Ace";}
        else{return Integer.toString(num);}
    }
}
