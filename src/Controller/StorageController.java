package Controller;

import Model.BoardModel;
import Model.PlayerModel;

public class StorageController {

    public void StoreInputInCell(BoardModel gameBoardModel, PlayerModel currentPlayerModel, int i, int j){
        char Move = currentPlayerModel == PlayerModel.X ? 'X' : 'O';
        currentPlayerModel.AddMoves(currentPlayerModel);

        gameBoardModel.AddPlayerMove(Move, i, j);
    }
}
