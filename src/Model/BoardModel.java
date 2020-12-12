package Model;

public class BoardModel {
    private char[][] Board = new char[3][3];

    public void SetUpBoard(){
        for (int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.Board[i][j] = '_';
            }
        }
    }

    public void AddPlayerMove(char Move, int i, int j) {
        this.Board[i][j] = Move;
    }

    public char GetCellContent(int i, int j){
        return this.Board[i][j];
    }

    public char[][] GetFullBoard(){
        return this.Board;
    }

}
