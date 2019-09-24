
package tictactoe;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/** class ScoreBoard() - gives us a way to track wins/losses and ties, extends GridPane 
 *
 * @author mikec
 */
public class ScoreBoard extends GridPane
{
    private Label xHeading;
    private Label oHeading;
    private Label catHeading;
    private Label scoreboardMessage;
    
    private TextField xScore;
    private TextField oScore;
    private TextField catScore;
    
    private int xTotal;
    private int oTotal;
    private int catTotal;
    
    
    
    public ScoreBoard()
    {
        super();
        init();
    }
    
    private void init()
    {
        setPadding(new Insets(4, 4, 4, 4));
        this.setHgap(4);
        
        xHeading = new Label("X Score");
        oHeading = new Label("O Score");
        catHeading = new Label("Cat");
        scoreboardMessage = new Label("");
        
        xScore = new TextField();
        oScore = new TextField();
        catScore = new TextField();
        
        xTotal = 0;
        oTotal = 0;
        catTotal = 0;
        
        add(xHeading, 0, 0);
        add(xScore,1, 0);
        
        add(oHeading, 0, 1);
        add(oScore,1, 1);
        
        add(catHeading, 0, 2);
        add(catScore,1, 2);
        
        add(new Label(""), 0, 3);
        add(scoreboardMessage, 1, 3);
        
        xScore.setText(String.valueOf(xTotal));
        oScore.setText(String.valueOf(oTotal));
        catScore.setText(String.valueOf(catTotal));
    }
    
    public void setMessage(String msg) { scoreboardMessage.setText(msg); }
    
    public void xWins() 
    { 
        xScore.setText(String.valueOf(++xTotal));
        setMessage("  X WINS !! ");
    }
    
    public void oWins() 
    { 
        oScore.setText(String.valueOf(++oTotal));
        setMessage("  O WINS !! ");
    }
    
    public void catWins() 
    { 
        catScore.setText(String.valueOf(++catTotal));
        setMessage("  CAT Game :( ");
    }
}
