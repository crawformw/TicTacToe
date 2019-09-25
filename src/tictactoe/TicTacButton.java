
package tictactoe;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/** class TicTacButton - extending Button class, called to create each of the 9 squares on the board
 *
 * @author mikec
 */
public class TicTacButton extends Button
{
    private int value;                          // value assigned based on selection
    private boolean selected;                   // boolean flag to indicate the button has something in it
    
    public TicTacButton(String label)
    {
        super(label);
        init();
    }
    
    public TicTacButton()
    {
        super();
        init();
    }
    
    /** init() - called from constructor to initialize the button, set value, set selected, set appearance
     * 
     */
    private void init()
    {
        value = 0;
        selected = false;
        setFont(Font.font("Times", FontWeight.BOLD, 32));
        setAlignment(Pos.CENTER);
        setStyle(" -fx-background-color: white; -fx-text-size: 60px; "
                + "-fx-min-width: 80px; -fx-min-height: 80px; "
                + "-fx-max-width: 80px; -fx-max-height: 80px;");
        
        // button action defined here
        setOnAction((ActionEvent ae) -> 
        {
            if (TicTacToe.isGameOn())
            {
                switch( TicTacToe.whoseTurn())
                {
                    case 1: 
                        setText("X"); 
                        value = 1;
                        break;
                    case 2: 
                        setText("O"); 
                        value = -1;  
                        break;
                }
                
                selected = true;
                TicTacToe.checkGameStatus();
                TicTacToe.nextTurn();
            }
            else { System.out.println("GAME OVER - Start Again"); }
        });         
    }
    
    /** clear() - called when a new game is played to clear the button of any value
     * 
     */
    public void clear() 
    { 
        setText("");
        value = 0;
        selected = false;
    }
    
    /** computerSelect() - called when user is playing the computer and the logic chooses a button
     * 
     */
    public void computerSelect()
    {
        if (TicTacToe.isGameOn())
        {
            setText("O");
            value = -1;
            selected = true;
            TicTacToe.checkGameStatus();
            TicTacToe.nextTurn();
        }
    }
    
    /** getValue() - return the value assigned to the button
     * @return 
     */
    public int getValue() { return value; }
    
    /** isSelected() - return an integer value relating to the selected flag
     * @return 
     */
    public int isSelected() 
    { 
        if (selected) { return 1; }
        else { return 0; }
    }
}
