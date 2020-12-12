public class Main {
    public static void main(String[] args) {   
        TicTacToe ticTacToe = new TicTacToe(); 
        GameState CurrentState = GameState.Playing;

        clearScreen();

        ticTacToe.SetUpTheGame();
        while(CurrentState == GameState.Playing) {

            ticTacToe.SetTurns();
            ticTacToe.GetAndTestInput();
            ticTacToe.UpdateTheGame();
            CurrentState = ticTacToe.CheckGameState();
        }
        ticTacToe.DisplayGameState();
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}