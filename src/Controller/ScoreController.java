package Controller;

import Model.BoardModel;
import Model.GameStateModel;
import Model.PlayerModel;
import Model.TurnCounterModel;

public class ScoreController {
    GameStateModel currentGameStateModel;
    BoardModel gameBoardModel;
    PlayerModel currentPlayerModel;
    TurnCounterModel Counter;
    char[][] Board;

    int XMoves;
    int OMoves;
    int XWins;
    int OWins;

    public GameStateModel CheckCurrentGameState(GameStateModel currentGameStateModel, PlayerModel PlayerModel, BoardModel gameBoardModel, TurnCounterModel Counter){
        this.currentGameStateModel = currentGameStateModel;
        this.currentPlayerModel = PlayerModel;
        this.gameBoardModel = gameBoardModel;
        this.Counter = Counter;
        Board = gameBoardModel.GetFullBoard();
        int PlayersTotalMoves;

        CountWins();

        XMoves = PlayerModel.GetMoves(PlayerModel.X);
        OMoves = PlayerModel.GetMoves(PlayerModel.O);
        XWins  = PlayerModel.GetWins(PlayerModel.X);
        OWins  = PlayerModel.GetWins(PlayerModel.O);
        PlayersTotalMoves = XMoves + OMoves;

        if (XWins == 0 && OWins > 0){           currentGameStateModel = GameStateModel.O_Win;
        } else if(XWins >  0 && OWins == 0){    currentGameStateModel = GameStateModel.X_Win;
        } else if(XWins == 0 && OWins == 0){
            if(PlayersTotalMoves < 9){          currentGameStateModel = GameStateModel.Playing;
            } else if (PlayersTotalMoves == 9){ currentGameStateModel = GameStateModel.Draw;
            }
        }
        return currentGameStateModel;
    }

    public void CountWins(){
        for (int i = 0; i < 3; i++) { // Verticals
            if (Board[i][0] + Board[i][1] + Board[i][2] == 264) currentPlayerModel.AddWins(PlayerModel.X);
            if (Board[i][0] + Board[i][1] + Board[i][2] == 237) currentPlayerModel.AddWins(PlayerModel.O);
        }

        for (int i = 0; i < 3; i++) { // Horizontals
            if (Board[0][i] + Board[1][i] + Board[2][i] == 264) currentPlayerModel.AddWins(PlayerModel.X);
            if (Board[0][i] + Board[1][i] + Board[2][i] == 237) currentPlayerModel.AddWins(PlayerModel.O);
        }

        // Diagonals
        if (Board[0][0] + Board[1][1] + Board[2][2] == 264) currentPlayerModel.AddWins(PlayerModel.X);
        if (Board[0][0] + Board[1][1] + Board[2][2] == 237) currentPlayerModel.AddWins(PlayerModel.O);
        if (Board[0][2] + Board[1][1] + Board[2][0] == 264) currentPlayerModel.AddWins(PlayerModel.X);
        if (Board[0][2] + Board[1][1] + Board[2][0] == 237) currentPlayerModel.AddWins(PlayerModel.O);
    }
}