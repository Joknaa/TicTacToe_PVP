package View;

public class OutputView {
    public void OutputNotNumberError(){
        System.out.println("You should enter numbers!");
    }

    public void OutputNotInLineError(){
        System.out.println("Enter the numbers in the same line, separated by a space! ");
    }

    public void OutputNotExistError(){
        System.out.println("Coordinates should be from 1 to 3!");
    }

    public void OutputNotEmptyError(){
        System.out.println("This cell is occupied! Choose another one!");
    }

    public void DrawBoard(char[][] Board) {
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

    public void DisplayGameState(char CurrentGameState){
        if     (CurrentGameState == 'X'){     System.out.println("X Wins !");}
        else if(CurrentGameState == 'O'){     System.out.println("O Wins !");}
        else if(CurrentGameState == 'D'){     System.out.println("This game is a Draw !");
        }
    }
}
