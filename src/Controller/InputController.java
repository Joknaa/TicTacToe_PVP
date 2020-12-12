package Controller;

import Model.BoardModel;
import View.InputView;
import View.OutputView;

public class InputController {
    OutputView outputView   = new OutputView();
    int[] InputCoords = new int[2];

    public int[] ValidateInput(BoardModel gameBoardModel) {
        boolean ValidInput = true;
        do {
            ValidInput = CheckInput(gameBoardModel, InputView.GetInput());
        } while (!ValidInput);

        return InputCoords;
    }

    public boolean CheckInput(BoardModel gameBoardModel, int Input){
        try {
            InputCoords = ConvertInputIntoCoords(Input);
        }catch (NumberFormatException e) {
            outputView.OutputNotNumberError();
            return false;
        }catch (ArrayIndexOutOfBoundsException e) {
            outputView.OutputNotInLineError();
            return false;
        }
        for (int i = 0; i < 2; i++){
            if (InputCoords[i] != 0 && InputCoords[i] != 1 && InputCoords[i] != 2) {
                outputView.OutputNotExistError();
                return false;
            }
        }

        if(CellIsFull(gameBoardModel, InputCoords[0], InputCoords[1])){
            outputView.OutputNotEmptyError();
            return false;
        }
        return true;
    }

    public int[] ConvertInputIntoCoords(int Input){
        int[] Coords = new int[2];
        int[] ConversionArray = {00, 01, 02, 10, 11, 12, 20, 21, 22};

        Coords[0] = ConversionArray[Input] / 10;
        Coords[1] = ConversionArray[Input] % 10;
        System.out.println("Courds[0] : " + Coords[0]);
        System.out.println("Courds[1] : " + Coords[1]);

        return Coords;
    }

    public boolean CellIsFull(BoardModel gameBoardModel, int i, int j){
        return gameBoardModel.GetCellContent(i, j) != '_';
    }
}