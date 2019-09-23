
package tictactoe;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author mikec
 */
public class ScoreBoard extends GridPane
{
    private Label xHeading;
    private Label oHeading;
    private Label catHeading;
    
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
        
        xScore.setText(String.valueOf(xTotal));
        oScore.setText(String.valueOf(oTotal));
        catScore.setText(String.valueOf(catTotal));
    }
    
    public void xWins() { xScore.setText(String.valueOf(++xTotal)); }
    public void oWins() { oScore.setText(String.valueOf(++oTotal)); }
    public void catWins() { catScore.setText(String.valueOf(++catTotal)); }
}
