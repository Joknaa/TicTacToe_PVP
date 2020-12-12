package Controller;

import Model.BoardModel;
import Model.GameStateModel;
import Model.PlayerModel;
import Model.TurnCounterModel;
import View.OutputView;

public class GameFlowController {
    InputController     InputCTRL = new InputController();
    StorageController StorageCTRL = new StorageController();
    ScoreController     ScoreCTRL = new ScoreController();
    BoardModel gameBoardModel = new BoardModel();
    PlayerModel currentPlayerModel;
    GameStateModel currentGameStateModel;

    TurnCounterModel Counter = new TurnCounterModel();
    OutputView Display = new OutputView();
    int[] CellCoords = new int[2];

    public void StartGame(){
        gameBoardModel.SetUpBoard();
        DrawBoard();
    }

    public GameStateModel UpdateGame(GameStateModel currentGameStateModel){
        this.currentGameStateModel = currentGameStateModel;

        CellCoords = InputCTRL.ValidateInput(gameBoardModel);
        StorageCTRL.StoreInputInCell(gameBoardModel, GetCurrentPlayer(), CellCoords[0], CellCoords[1]);
        DrawBoard();
        currentGameStateModel = ScoreCTRL.CheckCurrentGameState(currentGameStateModel, currentPlayerModel, gameBoardModel, Counter);
        Counter.NextTurn();
        return currentGameStateModel;
    }

    public PlayerModel GetCurrentPlayer(){
        currentPlayerModel = Counter.GetTurn() % 2 == 0 ? PlayerModel.X : PlayerModel.O;
        return currentPlayerModel;
    }

    public void DrawBoard(){
        char[][] Board;
        Board = gameBoardModel.GetFullBoard();
        Display.DrawBoard(Board);
    }

    public void EndTheGame(GameStateModel finalGameStateModel){
        char FinalGameStateSymbol = ' ';

        if      (finalGameStateModel == GameStateModel.X_Win) { FinalGameStateSymbol = 'X';}
        else if (finalGameStateModel == GameStateModel.O_Win) { FinalGameStateSymbol = 'O';}
        else if (finalGameStateModel == GameStateModel.Draw)  { FinalGameStateSymbol = 'D';}
        Display.DisplayGameState(FinalGameStateSymbol);
    }
}
