import java.lang.Math;
import java.util.Scanner;

enum GameState{
    Playing,
    Draw,
    X_Win,
    O_Win,
    Impossible
}

enum Players{
    X,
    O
}

public class TicTacToe {
    Scanner scanner = new Scanner(System.in);
    char[][] Board = new char[3][3];
    int[] InputCell = {0, 0};

    GameState CurrentState = GameState.Playing;
    Players CurrentPlayer;

    int Turns = 0;
    int xCount = 0;
    int oCount = 0;
    int xWins = 0;
    int oWins = 0;

    public void SetTurns(){
        CurrentPlayer = Turns % 2 == 0 ? Players.X : Players.O;
        Turns++;
        System.out.println("turn : " + Turns);
    }

    public void GetAndTestInput() {
        String InputString = "";
        boolean ValidInput = false;

        while (!ValidInput) {
            ValidInput = true;
            InputString = scanner.nextLine();
            try {
                String [] pieces = InputString.split(" ");
                InputCell[0] = Integer.parseInt(pieces[0]);
                InputCell[1] = Integer.parseInt(pieces[1]);
            }catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                ValidInput = false;
                continue;
            }catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Enter the numbers in the same line, separated by a space! ");
                ValidInput = false;
                continue;
            } 
        
            for (int i = 0; i < 2; i++){
                if (InputCell[i] != 1 && InputCell[i] != 2 && InputCell[i] != 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    ValidInput = false;
                    break;
                }
            }
            if(ValidInput == false) continue;
            
            if(CellIsNotEmpty()){
                System.out.println("This cell is occupied! Choose another one!");
                ValidInput = false;
            }
        }
    }

    public boolean CellIsNotEmpty(){
        int i = 2 - (InputCell[1] - 1);
        int j = InputCell[0] - 1;

        if (Board[i][j] == ' '){
            return false;
        }
        return true;
    }

    public void SetUpTheGame() {
        SetUpArray();
        DrawInitialBoard(Board);
    }

    public void SetUpArray() {
        for (int i = 0; i < 3; i++) {   
            for (int j = 2; j >= 0; j--) {
                Board[j][i] = ' ';
            }
        }
    }

    public void DrawInitialBoard(char[][] Board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {   
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(Board[j][i] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public void UpdateTheGame(){
        int i = InputCell[0];
        int j = InputCell[1];
        AddPlayerMove(i, j);
        DrawBoard();
    }

    public void AddPlayerMove(int iCoor, int jCoor) {
        int i = 2 - (InputCell[1] - 1);
        int j = InputCell[0] - 1;

        Board[i][j] = CurrentPlayer == Players.X ? 'X' : 'O';
    }

    public GameState CheckGameState(){
        int MovesPlayed = 0;

        MovesPlayed = CountMoves();
        CountWins();

        if(Math.abs(xCount - oCount) > 1) {     
            CurrentState = GameState.Impossible;
        } else {
            if(xWins >  0 && oWins >  0){ 
                CurrentState = GameState.Impossible;
            } else if(xWins == 0 && oWins >  0){ 
                CurrentState = GameState.O_Win;
            } else if(xWins >  0 && oWins == 0){ 
                CurrentState = GameState.X_Win;
            } else if(xWins == 0 && oWins == 0){
                if(MovesPlayed <  9){ 
                    CurrentState = GameState.Playing;
                } else if (MovesPlayed == 9){ 
                    CurrentState = GameState.Draw;
                } else {                      
                    CurrentState = GameState.Impossible;
                }
            }
        }
        return CurrentState;
    }

    public int CountMoves() {
        xCount = 0;
        oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                xCount += Board[i][j] == 'X' ? 1 : 0;  
                oCount += Board[i][j] == 'O' ? 1 : 0; 
            } 
        }
        return oCount + xCount;
    }

    public void CountWins(){

        for (int i = 0; i < 3; i++) { // Verticals
            xWins += Board[i][0] + Board[i][1] + Board[i][2] == 264 ? 1 : 0;
            oWins += Board[i][0] + Board[i][1] + Board[i][2] == 237 ? 1 : 0;
        }

        for (int i = 0; i < 3; i++) { // Horizontals
            xWins += Board[0][i] + Board[1][i] + Board[2][i] == 264 ? 1 : 0;
            oWins += Board[0][i] + Board[1][i] + Board[2][i] == 237 ? 1 : 0;
        }

        // Diagonals
        xWins += Board[0][0] + Board[1][1] + Board[2][2] == 264 ? 1 : 0;
        oWins += Board[0][0] + Board[1][1] + Board[2][2] == 237 ? 1 : 0;
        xWins += Board[0][2] + Board[1][1] + Board[2][0] == 264 ? 1 : 0;        
        oWins += Board[0][2] + Board[1][1] + Board[2][0]  == 237 ? 1 : 0;
    }

    public void DrawBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {   
            System.out.print("| ");

            for (int j = 0; j < 3; j++) {
                System.out.print(Board[i][j] + " ");
            }

            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public void DisplayGameState(){
        if       (CurrentState == GameState.X_Win) {    System.out.println("X Wins !");
        } else if(CurrentState == GameState.O_Win){     System.out.println("O Wins !");
        } else if(CurrentState == GameState.Impossible){System.out.println("This game is Impossible !");
        } else if(CurrentState == GameState.Draw){      System.out.println("This game is a Draw !");
        } else if(CurrentState == GameState.Playing){   System.out.println("This game is not finished yet !");
        }
    }
}