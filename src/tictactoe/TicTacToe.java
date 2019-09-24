
package tictactoe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Class TicTacToe - main class, extends the base Application class, contains the main()
 *
 * @author mikec
 */
public class TicTacToe extends Application 
{
    private GridPane grid;
    private HBox gameControls;
    private static ScoreBoard ticTacBoard;
    
    private static TicTacButton[][] RC;         // matrix of buttons that represents our tic tac toe board
    
    private Button newGame;                     // button to start new games
    private Button exitGame;                    // button to exit program
   
    public static boolean gameOn;
    private static CheckBox playComputer;
    public static int player;
    public static int turnCount;
    
    @Override
    public void start(Stage primaryStage) 
    {
        grid = new GridPane();
        grid.setPadding(new Insets(4, 4, 4, 4));
        
        gameControls = new HBox(3);
        gameControls.setPadding(new Insets(4, 4, 4, 4));
        
        playComputer = new CheckBox("Play Computer");
        playComputer.setSelected(true);
        
        newGame = new Button("New Game");
        newGame.setOnAction(ae -> { clearTheBoard(); });
        
        exitGame = new Button("Exit Game");
        exitGame.setOnAction(ae -> 
        { 
            Platform.exit();
            System.exit(0); 
        });
        
        gameControls.getChildren().addAll(newGame, exitGame);
        
        ticTacBoard = new ScoreBoard();
                
        RC = new TicTacButton[3][3];
        
        for (int i=0; i < 3; i++)
        {
            for (int j=0; j < 3; j++)
            {
                RC[i][j] = new TicTacButton("X");
                grid.add( RC[i][j], j, i );
                
                switch (i)
                {
                    case 0:
                    case 2:
                        if (j == 1) 
                        { 
                            RC[i][j].setStyle(" -fx-background-color: white; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 0 4 0 4; -fx-border-color: black red black red;"); 
                        }
                        break;
                        
                    case 1:
                        switch(j)
                        {
                            case 0:
                            case 2:
                                RC[i][j].setStyle(" -fx-background-color: white; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 4 0 4 0; -fx-border-color: red black red black;");
                                break;
                                
                            case 1:
                                RC[i][j].setStyle("-fx-background-color: white; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 4 4 4 4; -fx-border-color: red red red red;");
                                break; 
                        }
                        break;
                }
            }
        }
        
        VBox root = new VBox();
        root.setPadding(new Insets(4, 4, 4, 4));
        root.getChildren().addAll(grid, gameControls, playComputer, ticTacBoard);
        
        Scene scene = new Scene(root, 250, 450);
        
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void clearTheBoard()
    {
        for (int i=0; i < 3; i++)
        {
            for (int j=0; j < 3; j++) 
            { 
                RC[i][j].clear();
                
                switch (i)
                {
                    case 0:
                    case 2:
                        switch(j)
                        {
                            case 0:
                            case 2:
                                RC[i][j].setStyle(" -fx-background-color: white; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                                break;
                                
                            case 1:
                                RC[i][j].setStyle(" -fx-background-color: white; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 0 4 0 4; -fx-border-color: black red black red;");
                                break;
                        }
                        break;
                        
                    case 1:
                        switch(j)
                        {
                            case 0:
                            case 2:
                                RC[i][j].setStyle(" -fx-background-color: white; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 4 0 4 0; -fx-border-color: red black red black;");
                                break;
                            
                            case 1:
                                RC[i][j].setStyle("-fx-background-color: white; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 4 4 4 4; -fx-border-color: red red red red;");
                                break;
                        }
                        break;
                }
            }
        }
        startNewGame();
        
    }
    
    public void startNewGame()
    {   
        gameOn = true;
        player = 1;
        turnCount = 1;
        if (playComputer.isSelected()) { ticTacBoard.setMessage("Playing Against Computer"); }
        else { ticTacBoard.setMessage("Playing Against Opponent"); }
    }
    
    public static boolean isGameOn() { return gameOn; }
    public static int whoseTurn() { return player; }
    
    public static void nextTurn() 
    {
        turnCount++;
        
        switch( whoseTurn() )
        {
            case 1:
                player = 2;
                if ( playComputer.isSelected() ) { nextComputerMove(); }
             break;
             
            case 2: player = 1; break;
        }
    }
    
    private static void nextComputerMove()
    {
        boolean moveMade = false;
        
        // check the rows and cols
        for ( int rowCol=0; rowCol < 3; rowCol++ )
        {
            // count how many squares are filled in current row
            
            if (( squaresFilledRow(rowCol) == 2 ) || (squaresFilledCol(rowCol) == 2))
            {
                // see if we can win or need to block
                if (( rowValue(rowCol) == -2 ) || (rowValue(rowCol) == 2)) { RC[rowCol][emptyRowSquare(rowCol)].computerSelect();   moveMade = true; }
                else if (( colValue(rowCol) == -2 ) || (colValue(rowCol) == 2)) { RC[emptyColSquare(rowCol)][rowCol].computerSelect();   moveMade = true; }
            }
            
            if (moveMade) { rowCol = 3; }
        }
        
        // check the two diagonals to see if we can win or block
        if (! moveMade)
        {
           if (( squaresFilledDiagonalOne() == 2) || (squaresFilledDiagonalTwo() == 2))
            {
                if (( diagValue(1) == -2) || (diagValue(1) == 2))
                {
                    fillEmptyDiagonal(1);
                    moveMade = true;
                }
                else if (( diagValue(2) == -2) || (diagValue(2) == 2))
                {
                    fillEmptyDiagonal(2);
                    moveMade = true;
                }
            } 
        }
        
        // best move to make is grab the middle square if available
        if (! moveMade)
        {
            if ( RC[1][1].isSelected() == 0 )
            {
                RC[1][1].computerSelect();
                moveMade = true;
            }
        }
        
        // find first available open square and mark
        if (! moveMade)
        {
            for (int i=0; i < 3; i++)
            {
                for (int j=0; j < 3; j++) 
                {
                    if ( RC[i][j].getValue() == 0 ) 
                    { 
                        RC[i][j].computerSelect();
                        j = 3;
                        i = 3;
                    }
                }
            }
        } 
    }
    
    private static int squaresFilledRow(int rowNum) { return ( RC[rowNum][0].isSelected() + RC[rowNum][1].isSelected() + RC[rowNum][2].isSelected() ); }
    private static int squaresFilledCol(int colNum) { return ( RC[0][colNum].isSelected() + RC[1][colNum].isSelected() + RC[2][colNum].isSelected() ); }
    private static int squaresFilledDiagonalOne() { return ( RC[0][0].isSelected() + RC[1][1].isSelected() + RC[2][2].isSelected() ); }
    private static int squaresFilledDiagonalTwo() { return ( RC[2][0].isSelected() + RC[1][1].isSelected() + RC[0][2].isSelected() ); }
    
    private static int emptyRowSquare(int row)
    {
        for (int col=0; col < 3; col++)
        {
            if ( RC[row][col].isSelected() == 0) { return col; }
        }
        return -1;
    }
    
    private static int emptyColSquare(int col)
    {
        for (int row=0; row < 3; row++)
        {
            if ( RC[row][col].isSelected() == 0) { return row; }
        }
        return -1;
    }
    
    private static void fillEmptyDiagonal(int diag)
    {
        switch(diag)
        {
            case 1:
                if ( RC[0][0].isSelected() == 0 ) { RC[0][0].computerSelect(); }
                else if ( RC[1][1].isSelected() == 0 ) { RC[1][1].computerSelect(); }
                else if ( RC[2][2].isSelected() == 0 ) { RC[2][2].computerSelect(); }
                break;
                
            case 2:
                if ( RC[0][2].isSelected() == 0 ) { RC[0][2].computerSelect(); }
                else if ( RC[1][1].isSelected() == 0 ) { RC[1][1].computerSelect(); }
                else if ( RC[2][0].isSelected() == 0 ) { RC[2][0].computerSelect(); }
                break;
        }
    }
    
    private static int rowValue(int rowNum) { return( RC[rowNum][0].getValue() + RC[rowNum][1].getValue() + RC[rowNum][2].getValue() ); }
    private static int colValue(int colNum) { return( RC[0][colNum].getValue() + RC[1][colNum].getValue() + RC[2][colNum].getValue() ); }
    private static int diagValue(int diagNum)
    {
        if (diagNum == 1) { return( RC[0][0].getValue() + RC[1][1].getValue() + RC[2][2].getValue() ); }
        else { return( RC[0][2].getValue() + RC[1][1].getValue() + RC[2][0].getValue() ); }
    }
    
    public static void checkGameStatus()
    {
        int row1 = 0;
        int row2 = 0;
        int row3 = 0;
        int col1 = 0;
        int col2 = 0;
        int col3 = 0;
        int dia1 = 0;
        int dia2 = 0;
        
        // calc row/col values
        for (int i=0; i<3; i++)
        {
            row1 += RC[0][i].getValue();
            row2 += RC[1][i].getValue();
            row3 += RC[2][i].getValue();
            
            col1 += RC[i][0].getValue();
            col2 += RC[i][1].getValue();
            col3 += RC[i][2].getValue();
        }
        
        // calc the two diag values
        dia1 = RC[0][0].getValue() + RC[1][1].getValue() + RC[2][2].getValue();
        dia2 = RC[0][2].getValue() + RC[1][1].getValue() + RC[2][0].getValue();
        
        // check if X is the winner in any case
        if (row1 == 3)
        {
            highLightRow(0);
            gameOn = false;
            ticTacBoard.xWins();
        }
        
        if (row2 == 3)
        {
            highLightRow(1);
            gameOn = false;
            ticTacBoard.xWins();
        }
        
        if (row3 == 3)
        {
            highLightRow(2);
            gameOn = false;
            ticTacBoard.xWins();
        }
        
        if (col1 == 3)
        {
            highLightCol(0);
            gameOn = false;
            ticTacBoard.xWins();
        }
        
        if (col2 == 3)
        {
            highLightCol(1);
            gameOn = false; 
            ticTacBoard.xWins();
        }
        
        if (col3 == 3)
        {
            highLightCol(2);
            gameOn = false;
            ticTacBoard.xWins();
        }
        
        if ( dia1 == 3 ) 
        { 
            highLightDiagonal(1);
            gameOn = false;
            ticTacBoard.xWins();
        }
        
        if ( dia2 == 3 ) 
        { 
            highLightDiagonal(2);
            gameOn = false;
            ticTacBoard.xWins();
        }
        
        // check if O is the winner in any case
       if (row1 == -3)
        {
            highLightRow(0);
            gameOn = false;
            ticTacBoard.oWins();
        }
        
        if (row2 == -3)
        {
            highLightRow(1);
            gameOn = false;
            ticTacBoard.oWins();
        }
        
        if (row3 == -3)
        {
            highLightRow(2);
            gameOn = false;
            ticTacBoard.oWins();
        }
        
        if (col1 == -3)
        {
            highLightCol(0);
            gameOn = false;
            ticTacBoard.oWins();
        }
        
        if (col2 == -3)
        {
            highLightCol(1);
            gameOn = false;
            ticTacBoard.oWins();
        }
        
        if (col3 == -3)
        {
            highLightCol(2);
            gameOn = false;
            ticTacBoard.oWins();
        }
        
        if ( dia1 == -3 ) 
        { 
            highLightDiagonal(1);
            gameOn = false;
            ticTacBoard.oWins();
        }
        
        if ( dia2 == -3 ) 
        { 
            highLightDiagonal(2);
            gameOn = false;
            ticTacBoard.oWins();
        }
        
        if ((turnCount > 8) && (gameOn))
        {
            highLightCol(0);
            highLightCol(1);
            highLightCol(2);
            gameOn = false;
            ticTacBoard.catWins();
        }
             
    }
    
    private static void highLightRow(int row)
    {
        switch(row)
        {
            case 0:
            case 2:
                RC[row][0].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                              + "-fx-min-width: 80px; -fx-min-height: 80px; "
                              + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                RC[row][1].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                            + "-fx-min-width: 80px; -fx-min-height: 80px; "
                            + "-fx-max-width: 80px; -fx-max-height: 80px; "
                            + "-fx-border-width: 0 4 0 4; -fx-border-color: black red black red;");
                RC[row][2].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                              + "-fx-min-width: 80px; -fx-min-height: 80px; "
                              + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                break;
                
            case 1:
                RC[row][0].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 4 0 4 0; -fx-border-color: red black red black;");
                RC[row][1].setStyle("-fx-background-color: yellow; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 4 4 4 4; -fx-border-color: red red red red;");
                RC[row][2].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                                        + "-fx-min-width: 80px; -fx-min-height: 80px; "
                                        + "-fx-max-width: 80px; -fx-max-height: 80px; "
                                        + "-fx-border-width: 4 0 4 0; -fx-border-color: red black red black;");
                break;
        }
    }
    
    private static void highLightCol(int col)
    {
        switch(col)
        {
            case 0:
            case 2:
                RC[0][col].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                              + "-fx-min-width: 80px; -fx-min-height: 80px; "
                              + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                RC[1][col].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                              + "-fx-min-width: 80px; -fx-min-height: 80px; "
                              + "-fx-max-width: 80px; -fx-max-height: 80px; "
                              + "-fx-border-width: 4 0 4 0; -fx-border-color: red black red black;");
                RC[2][col].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                              + "-fx-min-width: 80px; -fx-min-height: 80px; "
                              + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                break;
                
            case 1:
                RC[0][col].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                            + "-fx-min-width: 80px; -fx-min-height: 80px; "
                            + "-fx-max-width: 80px; -fx-max-height: 80px; "
                            + "-fx-border-width: 0 4 0 4; -fx-border-color: black red black red;");
                RC[1][col].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                            + "-fx-min-width: 80px; -fx-min-height: 80px; "
                            + "-fx-max-width: 80px; -fx-max-height: 80px; "
                            + "-fx-border-width: 4 4 4 4; -fx-border-color: red red red red;");
                RC[2][col].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                            + "-fx-min-width: 80px; -fx-min-height: 80px; "
                            + "-fx-max-width: 80px; -fx-max-height: 80px; "
                            + "-fx-border-width: 0 4 0 4; -fx-border-color: black red black red;");
                break;
        }
    }
    
    private static void highLightDiagonal(int diag)
    {
        switch(diag)
        {
            case 1:
                RC[0][0].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                              + "-fx-min-width: 80px; -fx-min-height: 80px; "
                              + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                RC[1][1].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                              + "-fx-min-width: 80px; -fx-min-height: 80px; "
                              + "-fx-max-width: 80px; -fx-max-height: 80px; "
                              + "-fx-border-width: 4 4 4 4; -fx-border-color: red red red red;");
                RC[2][2].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                              + "-fx-min-width: 80px; -fx-min-height: 80px; "
                              + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                break;
                
            case 2:
                RC[2][0].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                            + "-fx-min-width: 80px; -fx-min-height: 80px; "
                            + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                RC[1][1].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                            + "-fx-min-width: 80px; -fx-min-height: 80px; "
                            + "-fx-max-width: 80px; -fx-max-height: 80px; "
                            + "-fx-border-width: 4 4 4 4; -fx-border-color: red red red red;");
                RC[0][2].setStyle(" -fx-background-color: yellow; -fx-text-size: 60px; "
                            + "-fx-min-width: 80px; -fx-min-height: 80px; "
                            + "-fx-max-width: 80px; -fx-max-height: 80px; ");
                break;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { launch(args); }
    
}
