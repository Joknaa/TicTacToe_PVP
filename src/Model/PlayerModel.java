package Model;

public enum PlayerModel {
    X(0,0),
    O(0,0);

    int Moves;
    int Wins;

    PlayerModel(int Moves, int Wins){
        this.Moves = Moves;
        this.Wins = Wins;
    }

    public int GetMoves(PlayerModel thisPlayerModel){
        return thisPlayerModel.Moves;
    }
    public int GetWins(PlayerModel thisPlayerModel){
        return thisPlayerModel.Wins;
    }

    public void AddMoves(PlayerModel thisPlayerModel){
        thisPlayerModel.Moves++;
    }
    public void AddWins(PlayerModel thisPlayerModel){
        thisPlayerModel.Wins++;
    }

}
