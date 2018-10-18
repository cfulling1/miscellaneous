import java.util.*;

public class Bingo {

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int[][] card = {{0,0,0,0,0}, 
                      {0,0,0,0,0},
                      {0,0,0,0,0},
                      {0,0,0,0,0},
                      {0,0,0,0,0}};
      printRules();
      System.out.println("Enter 1 to begin the game!");
      int command = sc.nextInt();
      if (command == 1) {
         fillCard(card);
         System.out.println("Your bingo card is:");
         printBoard(card);
         System.out.println();
         while (bingoCheck(card) == false)
            playRound(card);
      }
      else System.out.println("Please follow the prompt");
      
      printBoard(card); 
      System.out.println("BINGO!");
    }
    
    public static void fillCard(int[][] card) { 
      int num;
      boolean[] used = new boolean[75]; //boolean array for checking if number has been chosen already
      for (int row = 0; row < card.length; row++) { //goes through the entire column
         if ((num = randomFill(1,15,used)) > 0) //making sure randomFill returned an actual card value
            card[row][0] = num;   //if it did, mark the card with that value
         else 
            row--;  //if it didn't, decrement row by one so it can be redone (and this will happen until an actual value is chosen)
      }
      
      for (int row = 0; row < card.length; row++) { //exact same as the first for loop, just with "I" column instead
         if ((num = randomFill(16,30,used)) > 0)
            card[row][1] = num;  
         else 
            row--;
      }
      for (int row = 0; row < card.length; row++) { //exact same as the first for loop, just with "N" column instead
         if ((num = randomFill(31,45,used)) > 0)
            card[row][2] = num;  
         else 
            row--;
      }
      for (int row = 0; row < card.length; row++) { //exact same as the first for loop, just with "G" column instead
         if ((num = randomFill(46,60,used)) > 0)
            card[row][3] = num;  
         else 
            row--;
      }
      for (int row = 0; row < card.length; row++) { //exact same as the first for loop, just with "O" column instead
         if ((num = randomFill(61,75,used)) > 0)
            card[row][4] = num;  
         else 
            row--;
      }
      
      card[2][2] = 0;  //marking free spot
    }
    
    public static int randomFill(int min, int max, boolean[] used) {
      Random rng = new Random();
     	int num = rng.nextInt(max - min + 1) + min; //random number generator, range values depend on which column
      if (used[num-1] == false) { //checking if number is false (not chosen yet) in boolean array
         used[num-1] = true; //if it is false, mark it true
         return num; //and then return the number
      }
      else      
         return 0; //if number has been chosen, return a 0
    }
    
    public static void printBoard(int[][] card) { 
        System.out.println("  B  I  N  G  O");
        for (int row = 0; row < card.length; row++) // card.length gives us the number of rows
        {
            for (int col = 0; col < card[row].length; col++) // card[row].length gives us the number of columns at row: row
                System.out.printf("%3d", card[row][col]);
            System.out.println();
        }

    }

    public static int playRound(int[][] card) { 
      int num;
      Scanner sc = new Scanner(System.in);
      printBoard(card);
      if ((num = randomDraw()) > 0) {
         if (ballCheck(card,num) == 1) {
            System.out.println();
            System.out.println("Number found! Enter 0 to mark it on your card.");
            int command = sc.nextInt();
            System.out.println();
            if (command == 0)
               markCard(card,num);
         }
        
         else {
            System.out.println();
            System.out.println("Number not found on card, enter 1 to keep going.");
            int command = sc.nextInt();
            if (command == 1)
               return 1;
         }
       }
       else 
         playRound(card);
       return 1;
     }
 
    public static int randomDraw() { 
      Random rng = new Random();
      boolean[] used = new boolean[75];
      
      int num = rng.nextInt(75) + 1;
      if (used[num-1] == false) {
         System.out.println();
         used[num-1] = true;
         if (num >= 1 && num <= 15)
            System.out.println("Ball drawn: B-"+num);
         if (num >= 16 && num <= 30)
            System.out.println("Ball drawn: I-"+num);
         if (num >= 31 && num <= 45)
            System.out.println("Ball drawn: N-"+num);
         if (num >= 46 && num <= 60)
            System.out.println("Ball drawn: G-"+num);
         if (num >= 61 && num <= 75)
            System.out.println("Ball drawn: O-"+num);
         return num;
       }
       else return 0;
    }
    
    public static int ballCheck(int[][] card, int num) { 
      for (int row = 0; row < card.length; row++) { // card.length gives us the number of rows
       //checking whole card for the number
         for (int col = 0; col < card[row].length; col++) // card[row].length gives us the number of columns at row: row
            if (card[row][col] == num) return 1; //number was found on player's card
      }
      return 0; //number was not found
    }
    
    public static void markCard(int[][] card, int num) { 
      for (int row = 0; row < card.length; row++) { // card.length gives us the number of rows
         for (int col = 0; col < card[row].length; col++)
            if (card[row][col] == num) 
               card[row][col] = 0;
      }
    }
    
    public static boolean bingoCheck(int[][]card) {
      
        // checking for bingos in rows
        if  ((card[0][0]==card[0][1] && card[0][1]==card[0][2] && card[0][2]==card[0][3] && card[0][3]==card[0][4])
          || (card[1][0]==card[1][1] && card[1][1]==card[1][2] && card[1][2]==card[1][3] && card[1][3]==card[1][4])
          || (card[2][0]==card[2][1] && card[2][1]==card[2][2] && card[2][2]==card[2][3] && card[2][3]==card[2][4])
          || (card[3][0]==card[3][1] && card[3][1]==card[3][2] && card[3][2]==card[3][3] && card[3][3]==card[3][4])
          || (card[4][0]==card[4][1] && card[4][1]==card[4][2] && card[4][2]==card[4][3] && card[4][3]==card[4][4]))
          return true; //bingo was found
          
        // checking for bingos in columns
        if  ((card[0][0]==card[1][0] && card[1][0]==card[2][0] && card[2][0]==card[3][0] && card[3][0]==card[4][0])
          || (card[0][1]==card[1][1] && card[1][1]==card[2][1] && card[2][1]==card[3][1] && card[3][1]==card[4][1])
          || (card[0][2]==card[1][2] && card[1][2]==card[2][2] && card[2][2]==card[3][2] && card[3][2]==card[4][2])
          || (card[0][3]==card[1][3] && card[1][3]==card[2][3] && card[2][3]==card[3][3] && card[3][3]==card[4][3])
          || (card[0][4]==card[1][4] && card[1][4]==card[2][4] && card[2][4]==card[3][4] && card[3][4]==card[4][4]))
          return true; //bingo was found
          
        // check for bingos in diagonals
        if  ((card[0][0]==card[1][1] && card[1][1]==card[2][2] && card[2][2]==card[3][3] && card[3][3]==card[4][4])
          || (card[4][0]==card[3][1] && card[3][1]==card[2][2] && card[2][2]==card[1][3] && card[1][3]==card[0][4]))
          return true; //bingo was found
            
        return false; //bingo was not found
      }
            
      public static void printRules() {  //prints the rules
         System.out.println("You will be given a 5x5 bingo card filled with random numbers.");
         System.out.println("The five columns are labeled B, I, N, G, and O.");
         System.out.println("All of the twenty-five squares are filled with random numbers,"); 
         System.out.println("you will be given the middle spot for free.");
         System.out.println("A random number will be drawn, and your card will be checked for that value.");
         System.out.println("If your card has that value, you will mark that spot with a 0."); 
         System.out.println("Your goal is to get five 0's in a row (horizontally, vertically, or diagonally).");
         System.out.println("This is known as BINGO!, and at this point you win. Good luck!");
         System.out.println();
      }
}    
      
      
