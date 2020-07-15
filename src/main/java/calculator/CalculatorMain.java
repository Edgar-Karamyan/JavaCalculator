package calculator;

import calculator.controller.CalcController;
import calculator.view.CalcView;

public class CalculatorMain {

    public static void main(String[] arg) {
        CalcModel calcModel = new CalcModel();
        CalcController calcController = new CalcController(calcModel);
        CalcView calcView = new CalcView(calcController);
        calcController.setView(calcView);
    }
}
