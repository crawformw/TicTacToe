
package tictactoe;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author mikec
 */
public class TicTacButton extends Button
{
    private int value;
    private boolean selected;
    
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
    
    private void init()
    {
        value = 0;
        selected = false;
        setFont(Font.font("Times", FontWeight.BOLD, 32));
        setAlignment(Pos.CENTER);
        setStyle(" -fx-background-color: white; -fx-text-size: 60px; "
                + "-fx-min-width: 80px; -fx-min-height: 80px; "
                + "-fx-max-width: 80px; -fx-max-height: 80px;");
        
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
    
    public void clear() 
    { 
        setText("");
        value = 0;
        selected = false;
    }
    
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
        else { System.out.println("GAME OVER - Start Again"); }
    }
    
    public int getValue() { return value; }
    public int isSelected() 
    { 
        if (selected) { return 1; }
        else { return 0; }
    }
}
