import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.*;


/** 
 * This class generates a slide game which rows and columns are set by the player
 * The texts on the buttons would slide to different directions depending on which button is clicked
 * After each slide, a new button with text 1 would be added to the game
 * @author   Xiaofan He
 */
public class SlideGame extends Application{
  
  /** The primary stage **/
  private static Stage primaryStage;
  /** The Grid pane that dispalies the buttons **/
  private GridPane gridPane;
  /** The array that contains all the buttons **/
  private static Button[][] buttons;
  /** The row of the game **/
  private static Integer row;
  /** The column of the game **/
  private static Integer column;
  /** The numbers corresponding to the texts on the buttons */
  private static int[][] numbers;
  
  
  /** The main method that launch the game **/
  public static void main(String[] args){
    Application.launch(args);
  }
  
  /** 
   * Overrides the start method of Application to create the game with a series of button in the center of the main window.
   * @param primaryStage the JavaFX main window
   */
  public void start(Stage primaryStage){
    this.primaryStage = primaryStage;
    GridPane gridPane = new GridPane();
    if(getParameters().getRaw().size() == 2){
      row = Integer.parseInt(getParameters().getRaw().get(0));
      column = Integer.parseInt(getParameters().getRaw().get(1));
    }
    else if(getParameters().getRaw().isEmpty() == true){
      row = 4;  // If there is no parameters entered by player, set the row and column to 4
      column = 4;
    }
    else{
      System.out.println("Invalid number");
    }
    this.generateButtons(row, column);  // Generate the buttons
    for(int i = 0, j = 0; i < row && j < column; ){
      gridPane.add(buttons[i][j], j, i);
      if(j < column - 1){
        j++;
      }
      else{
        j = 0;
        i++;
      }
    }
    for(int i = 0; i < row; i++){  // Set the buttons on action
      buttons[i][0].setOnAction(new ClickButton());
      buttons[i][column - 1].setOnAction(new ClickButton());
    }
    for(int j = 0; j < column; j++){
      buttons[0][j].setOnAction(new ClickButton());
      buttons[row - 1][j].setOnAction(new ClickButton());
    }
    BorderPane pane = new BorderPane();
    pane.setCenter(gridPane);
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Slide Game " + row + "*" + column);
    primaryStage.sizeToScene();
    primaryStage.show();
  }
  
  /**
   * Set the numbers of the int array to the buttons
   * Make the text of the button the same as the corresponding number in the int array
   */
  public static void setTextToButtons(){
    for(int i = 0, j = 0; i < row && j < column; ){
      if(numbers[i][j] == 0)
        buttons[i][j].setText("");
      else
        buttons[i][j].setText(Integer.toString(numbers[i][j]));
      if(j < column - 1)
        j++;
      else{
        i++;
        j = 0;
      }
    }
  }
  
  /**
   * The helper method that generate a series fo buttons that make up the game
   * @param row    The number of rows of the buttons
   * @param column    The number of columns of the buttons
   */
  public void generateButtons(int row, int column){
    buttons = new Button[row][column];
    numbers = new int[row][column];
    int randomRow = this.generateRandomNumber(row);
    int randomColumn = this.generateRandomNumber(column);
    for(int i = 0, j = 0; i < row && j < column;){
      buttons[i][j] = new Button();
      buttons[i][j].setMinWidth(50);
      buttons[i][j].setMinHeight(40);
      numbers[i][j] = 0;
      if(j < column - 1){
        j ++;
      }
      else{
        i ++;
        j = 0;
      }
    }
    numbers[randomRow][randomColumn] = 1; // Set a random button to be 1
    SlideGame.setTextToButtons();
  }
  
  /**
   * Generate a random number for row
   * @param number    The number of the maximum row or column
   * @return A random number
   */
  public static int generateRandomNumber(int number){
    int randomNumber = (int) (Math.random() * (number));
    return randomNumber;
  }
  
  /** 
   * The method that slide the numbers to the left
   * @param numbers    The numbers that would be slided to the left
   */
  public static void slideToLeft(int[][] numbers){
    int row = numbers.length;
    int column = numbers[0].length;
    int numberSlided = 0; //record how many numbers has been slided in this row
    for(int i = 0, j = 0; i < row && j < column; ){
      if(numbers[i][j] == 0){
        ;
      }
      else{
        if(j != numberSlided){
          numbers[i][numberSlided] = numbers[i][j]; //Slide the numbers to the left
          numbers[i][j] = 0;
          numberSlided++;
        }
        else
          numberSlided++;
        if(numberSlided > 1){
          if(numbers[i][numberSlided - 1] == numbers[i][numberSlided - 2]){ //Combine the two equal numbers
            numbers[i][numberSlided - 2] = (numbers[i][numberSlided - 1] * 2);
            numbers[i][numberSlided - 1] = 0;
            numberSlided--;
          }
        }
      }
      if(j < column - 1){
        j++;
      }
      else{
        j = 0;
        i++;
        numberSlided = 0; //reset the number that have been moved when move on for the nect row
      }
    }
  }
  
  
  /** 
     * The method that slide the numbers up
     * @param numbers    The numbers that would be slided up
     */
    public static void slideUp(int[][] numbers){
      int row = numbers.length;
      int column = numbers[0].length;
      int numberSlided = 0; //record how many numbers has been slided in this row
      for(int i = 0, j = 0; i < row && j < column; ){
        if(numbers[i][j] == 0){
          ;
        }
        else{
          if(i != numberSlided){
            numbers[numberSlided][j] = numbers[i][j]; //Slide the numbers up
            numbers[i][j] = 0;
            numberSlided++;
          }
          else
            numberSlided++;
          if(numberSlided > 1){
            if(numbers[numberSlided - 1][j] == numbers[numberSlided - 2][j]){ //Combine the two equal numbers
              numbers[numberSlided - 2][j] = (numbers[numberSlided - 1][j] * 2);
              numbers[numberSlided - 1][j] = 0;
              numberSlided--;
            }
          }
        }
        if(i < row - 1){
          i++;
        }
        else{
          i = 0;
          j++;
          numberSlided = 0; //reset the number that have been moved when move on for the nect row
        }
      }
    }
  
  
  /** 
     * The method that slide the numbers down
     * @param numbers    The numbers that would be slided down
     */
    public static void slideDown(int[][] numbers){
      int row = numbers.length;
      int column = numbers[0].length;
      int numberSlided = 0; //record how many numbers has been slided in this row
      for(int i = row - 1, j = column - 1; i >= 0 && j >= 0; ){
        if(numbers[i][j] == 0){
          ;
        }
        else{
          if(i != row - 1 - numberSlided){
            numbers[row - numberSlided - 1][j] = numbers[i][j]; //Slide the numbers to the up
            numbers[i][j] = 0;
            numberSlided++;
          }
          else
            numberSlided++;
          if(numberSlided > 1){
            if(numbers[row - numberSlided + 1][j] == numbers[row - numberSlided][j]){ //Combine the two equal numbers
              numbers[row - numberSlided + 1][j] = (numbers[row - numberSlided][j] * 2);
              numbers[row - numberSlided][j] = 0;
              numberSlided--;
            }
          }
        }
        if(i > 0){
          i--;
        }
        else{
          i = row - 1;
          j--;
          numberSlided = 0; //reset the number that have been moved when move on for the nect row
        }
      }
    }
    
    
  
  
  /** 
     * The method that slide the numbers to the right
     * @param numbers    The numbers that would be slided to the right
     */
    public static void slideToRight(int[][] numbers){
      int row = numbers.length;
      int column = numbers[0].length;
      int numberSlided = 0; //record how many numbers has been slided in this row
      for(int i = row - 1, j = column - 1; i >= 0 && j >= 0; ){
        if(numbers[i][j] == 0){
          ;
        }
        else{
          if(j != column - 1 - numberSlided){
            numbers[i][column - numberSlided - 1] = numbers[i][j]; //Slide the button down
            numbers[i][j] = 0;
            numberSlided++;
          }
          else
            numberSlided++;
          if(numberSlided > 1){
            if(numbers[i][column - numberSlided + 1] == numbers[i][column - numberSlided]){ //Combine the two equal numbers
              numbers[i][column - numberSlided + 1] = (numbers[i][column - numberSlided] * 2);
              numbers[i][column - numberSlided] = 0;
              numberSlided--;
            }
          }
        }
        if(j > 0){
          j--;
        }
        else{
          j = column - 1;
          i--;
          numberSlided = 0; //reset the number that have been moved when move on for the nect row
        }
      }
    }
  
  /**
   * Slide the numbers diagonally to up right direction
   * @param numbers    The numbers that would be slided
   */
  public static void slideUpRight(int[][] numbers){
    int row = numbers.length;
      int column = numbers[0].length;
      for(int i = 0, j = 0; i < row && j < column;){
        if(numbers[i][j] == 0)
          ;
        else{
          if(i >= 1 && j <= column - 2){
            for(int a = i, b = j; a >= 1 && b <= column - 2;){
              if(numbers[a - 1][b + 1] == 0){
                numbers[a - 1][b + 1] = numbers[a][b]; //Slide
                numbers[a][b] = 0;
                a--;
                b++;
              }
              else{
                if(numbers[a][b] == numbers[a - 1][b + 1]){
                  numbers[a - 1][b + 1] = (numbers[a][b] * 2); //Combine the two equal numbers
                  numbers[a][b] = 0;  // Make the previous one blank
                }
                
                a = 0;
                b = column;
              }
            }
          }
        }
        if(j < column - 1)
          j++;
        else{
          i++;
          j = 0;
        }
      }
    }
  
  
  /**
   * Slide the numbers diagonally to up left direction
   * @param numbers    The numbers that would be slided
   */
  public static void slideUpLeft(int[][] numbers){
    int row = numbers.length;
      int column = numbers[0].length;
      for(int i = 0, j = 0; i < row && j < column;){
        if(numbers[i][j] == 0)
          ;
        else{
          if(i >= 1 && j >= 1){
            for(int a = i, b = j; a >= 1 && b >= 1;){
              if(numbers[a - 1][b - 1] == 0){
                numbers[a - 1][b - 1] = numbers[a][b];  // Slide
                numbers[a][b] = 0;
                a--;
                b--;
              }
              else{
                if(numbers[a][b] == numbers[a - 1][b - 1]){
                  numbers[a - 1][b - 1] = (numbers[a][b] * 2); // Combine the two equal numbers
                  numbers[a][b] = 0;
                }
                a = 0;
                b = 0;
              }
            }
          }
        }
        if(j < column - 1)
          j++;
        else{
          i++;
          j = 0;
        }
      }
    }
  
  
  /**
   * Slide the numbers diagonally to down left direction
   * @param numbers    The numbers that would be slided
   */
  public static void slideDownLeft(int[][] numbers){
    int row = numbers.length;
      int column = numbers[0].length;
      for(int i = 0, j = 0; i < row && j < column;){
        if(numbers[i][j] == 0)
          ;
        else{
          if(i <= row - 2 && j >= 1){
            for(int a = i, b = j; a <= row - 2 && b >= 1;){
              if(numbers[a + 1][b - 1] == 0){
                numbers[a + 1][b - 1] = numbers[a][b]; // Slide
                numbers[a][b] = 0;
                a++;
                b--;
              }
              else{
                if(numbers[a][b] == numbers[a + 1][b - 1]){
                  numbers[a + 1][b - 1] = (numbers[a][b] * 2); //Combine two equal numbers
                  numbers[a][b] = 0;
                }
                a = row;
                b = 0;
              }
            }
          }
        }
        if(j < column - 1)
          j++;
        else{
          i++;
          j = 0;
        }
      }
    }
  
  /**
   * Slide the numbers diagonally to down right direction
   * @param numbers    The numbers that would be slided
   */
  public static void slideDownRight(int[][] numbers){
    int row = numbers.length;
      int column = numbers[0].length;
      for(int i = 0, j = 0; i < row && j < column;){
        if(numbers[i][j] == 0)
          ;
        else{
          if(i <= row - 2 && j <= column - 2){
            for(int a = i, b = j; a <= row - 2 && b <= column - 2;){
              if(numbers[a + 1][b + 1] == 0){
                numbers[a + 1][b + 1] = numbers[a][b]; // Slide
                numbers[a][b] = 0;
                a++;
                b++;
              }
              else{
                if(numbers[a][b] == numbers[a + 1][b + 1]){
                  numbers[a + 1][b + 1] = (numbers[a][b] * 2);  //Combine two equal numbers
                  numbers[a][b] = 0;
                }
                a = row;
                b = column;
              }
            }
          }
        }
        if(j < column - 1)
          j++;
        else{
          i++;
          j = 0;
        }
      }
    }
  
  /**
     * Generate a new number 1 on a grid which is 0
     */
  public static void newAfterClicked(){
      int randomRow;
      int randomColumn;
      for(randomRow = SlideGame.generateRandomNumber(row), randomColumn = SlideGame.generateRandomNumber(column); randomRow >= 0 && randomColumn >= 0;){
        if(numbers[randomRow][randomColumn] == 0){
          numbers[randomRow][randomColumn] = 1;
          randomRow = -1;
          randomColumn = -1;
        }
        else{
          randomRow = SlideGame.generateRandomNumber(row);
          randomColumn = SlideGame.generateRandomNumber(column);
        }
      }
      SlideGame.setTextToButtons();
    }
  
  /**
   * The class that react to the buttom clicks
   * Response to clicks on different buttons
   */
  public class ClickButton implements EventHandler<ActionEvent>{
    
    /**
     * Override the handle method
     * Set actions respond to different button clicks
     * @param e    The ActionEvent that happens when different buttons are clicked
     */
    public void handle(ActionEvent e){
      boolean left = false;
      boolean right = false;
      boolean up = false;
      boolean down = false;
      for(int i = 1; i < row - 1;){ // Distinguish what direction the numbers should be slided to when different buttons are clicked
        if(e.getSource().equals(buttons[i][0])){
          left = true;
          i = row;
        }
        else if(e.getSource().equals(buttons[i][column - 1])){
          right = true;
          i = row;
        }
        else
          i++;
      }
      for(int j = 1; j < column - 1;){
        if(e.getSource().equals(buttons[0][j])){
          up = true;
          j = column;
        }
        else if(e.getSource().equals(buttons[row - 1][j])){
          down = true;
          j = column;
        }
        else
          j++;
      }
      if(e.getSource().equals(buttons[0][0]))   // Set actions to different buttons
        SlideGame.slideUpLeft(SlideGame.numbers);
      else if(e.getSource().equals(buttons[0][column - 1]))
        SlideGame.slideUpRight(SlideGame.numbers);
      else if(e.getSource().equals(buttons[row - 1][column - 1]))
        SlideGame.slideDownRight(SlideGame.numbers);
      else if(e.getSource().equals(buttons[row - 1][0]))
        SlideGame.slideDownLeft(SlideGame.numbers);
      else if(left == true)
        SlideGame.slideToLeft(SlideGame.numbers);
      else if(right == true)
        SlideGame.slideToRight(SlideGame.numbers);
      else if(up == true)
        SlideGame.slideUp(SlideGame.numbers);
      else if(down == true)
        SlideGame.slideDown(SlideGame.numbers);
      SlideGame.newAfterClicked(); // Generate a new 1 after clicking the button
      SlideGame.setTextToButtons();  // Set the texts to the numbers after the numbers have been slided
      primaryStage.sizeToScene();
    }
  }
  
}