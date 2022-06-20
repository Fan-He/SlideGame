import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


/**
 * The class that test the SlideGame class
 * @author   Xiaofan He
 */
public class SlideGameTester{
  
  
  /**
   * Test the slideToLeft method
   */
  @Test
  public void testSlideToLeft(){
    int[][] numbers = new int[4][5];
    numbers[1][2] = 1;
    numbers[2][2] = 1;
    numbers[2][4] = 1;
    SlideGame.slideToLeft(numbers);
    assertEquals("The number on the left after slided to the left", 1, numbers[1][0]);
    assertEquals("The number on the left after slided to the left", 2, numbers[2][0]);
  }
  
  /**
   * Test the slideToRight method
   */
  @Test
  public void testSlideToRight(){
    int[][] numbers = new int[4][5];
    numbers[1][2] = 1;
    numbers[2][2] = 1;
    numbers[2][4] = 1;
    SlideGame.slideToRight(numbers);
    assertEquals("The numbers are slided to the right", 1, numbers[1][4]);
    assertEquals("The numbers are slided to the right", 2, numbers[2][4]);
  }
  
  
  /**
   * Test the slideUp method
   */
  @Test
  public void testSlideUp(){
    int[][] numbers = new int[4][5];
    numbers[1][2] = 1;
    numbers[2][2] = 1;
    numbers[2][4] = 1;
    SlideGame.slideUp(numbers);
    assertEquals("The numbers are slided up", 2, numbers[0][2]);
    assertEquals("The numbers are slided up", 1, numbers[0][4]);
  }
  
  /**
   * Test the slideDown method
   */
  @Test
  public void testSlideDown(){
    int[][] numbers = new int[4][5];
    numbers[1][2] = 1;
    numbers[2][2] = 1;
    numbers[2][4] = 1;
    SlideGame.slideDown(numbers);
    assertEquals("The numbers are slided down", 2, numbers[3][2]);
    assertEquals("The numbers are slided down", 1, numbers[3][4]);
  }
  
  /**
   * Test the slideUpLeft method
   */
  @Test
  public void testSlideUpLeft(){
    int[][] numbers = new int[4][5];
    numbers[1][1] = 1;
    numbers[3][3] = 1;
    numbers[2][4] = 1;
    SlideGame.slideUpLeft(numbers);
    assertEquals("The numbers are slided up left", 2, numbers[0][0]);
    assertEquals("The numbers are slided up left", 1, numbers[0][2]);
  }
  
  /**
   * Test the slideUpRight method
   */
  @Test
  public void testSlideUpRight(){
    int[][] numbers = new int[4][5];
    numbers[1][1] = 1;
    numbers[3][3] = 1;
    numbers[2][4] = 1;
    SlideGame.slideUpRight(numbers);
    assertEquals("The numbers are slided up right", 2, numbers[2][4]);
    assertEquals("The numbers are slided up right", 1, numbers[0][2]);
  }
  
  /**
   * Test the slideDownLeft method
   */
  @Test
  public void testSlideDownLeft(){
    int[][] numbers = new int[4][5];
    numbers[1][1] = 1;
    numbers[3][3] = 1;
    numbers[2][4] = 1;
    SlideGame.slideDownLeft(numbers);
    assertEquals("The numbers are slided down left", 2, numbers[3][3]);
    assertEquals("The numbers are slided down left", 1, numbers[2][0]);
  }
  
  /**
   * Test the slideDownRight method
   */
  @Test
  public void testSlideDownRight(){
    int[][] numbers = new int[4][5];
    numbers[1][1] = 1;
    numbers[3][3] = 1;
    numbers[2][4] = 1;
    numbers[1][0] = 1;
    SlideGame.slideDownRight(numbers);
    assertEquals("The numbers are slided down right and combined", 2, numbers[3][3]);
    assertEquals("The numbers are slided down right", 1, numbers[2][4]);
    assertEquals("The numbers are slided down right", 1, numbers[3][2]);
  }
}