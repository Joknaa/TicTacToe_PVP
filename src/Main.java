import Controller.GameFlowController;
import Model.GameStateModel;

public class Main {
    public static void main(String[] args) {
        GameFlowController GameMaster = new GameFlowController();
        GameStateModel currentGameStateModel = GameStateModel.Playing;

        GameMaster.StartGame();

        while(currentGameStateModel == GameStateModel.Playing) {
            currentGameStateModel = GameMaster.UpdateGame(currentGameStateModel);
        }

        GameMaster.EndTheGame(currentGameStateModel);
    }
}