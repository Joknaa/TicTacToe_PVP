package Model;

public class TurnCounterModel {
    int Turn = 0;

    public void NextTurn(){
        this.Turn++;
    }

    public int GetTurn(){
        return this.Turn;
    }
}
