package calculator.controller;

import calculator.CalcModel;
import calculator.operators.BinaryOperationInterface;
import calculator.operators.UnaryOperationInterface;
import calculator.view.CalcViewInterface;

public class CalcController {
    private CalcViewInterface calcView;
    private CalcModel calcModel;

    public void operate(BinaryOperationInterface biOperation) {

        if (calcModel.getInputValueInited() && !calcModel.getResultInited() ) {
            calcModel.setResult(calcModel.getInputValueAsDouble());
        } else
            if (calcModel.getInputValueInited() && calcModel.getResultInited() && calcModel.getLastOperator() != null ) {
                calcModel.setResult(
                        calcModel.getLastOperator()
                                .count(calcModel.getResultAsDouble(), calcModel.getInputValueAsDouble()));
            }
            calcView.updateValue(calcModel.getResultAsString());
            calcModel.removeInputValue();
            calcModel.setLastOperator(biOperation);
        }


    public void calculate() {

        if (calcModel.getResultInited() && calcModel.getInputValueInited() && calcModel.getLastOperator() != null) {
            calcModel.setResult(calcModel.getLastOperator().count(calcModel.getResultAsDouble(), calcModel.getInputValueAsDouble()));
            calcModel.removeInputValue();

        } else if (!calcModel.getResultInited()) {
            calcView.updateValue(calcModel.getInputValueAsString());
            return;
        }
        calcView.updateValue(calcModel.getResultAsString());
    }

    public void unaryOperate(UnaryOperationInterface unaryOperate) {
        if(!calcModel.getInputValueInited() && !calcModel.getResultInited()) {
            return;
        }

        if (!calcModel.getInputValueInited() && calcModel.getResultInited() ) {
            calcModel.setResult(unaryOperate.count(calcModel.getResultAsDouble()));
            calcView.updateValue(calcModel.getResultAsString());

        }
        else{
            calcModel.setResult(unaryOperate.count(calcModel.getInputValueAsDouble()));
            calcModel.removeInputValue();
            calcView.updateValue(calcModel.getResultAsString());
        }



    }

    public CalcController(CalcModel calcModel) {
        this.calcModel = calcModel;


    }

    public void inputValue(byte value) {
        calcModel.addToInputValue(value);
        calcView.updateValue(calcModel.getInputValueAsString());
    }

    public void backspace() {
        calcModel.backSpace();
        calcView.updateValue(calcModel.getInputValueAsString());
    }

    public void cleanAll() {
        calcModel = new CalcModel();
        calcView.updateValue(calcModel.getInputValueAsString());
    }

    public void inputDot() {
        calcModel.putDot();
        calcView.updateValue(calcModel.getInputValueAsString());
    }

    public void invertSign() {

        calcModel.setIsNegative();
        calcView.updateValue(calcModel.getInputValueAsString());
    }


    public void setView(CalcViewInterface view) {
        this.calcView = view;
    }



    public double getResultFromModel(){
        return this.calcModel.getResultAsDouble();
    }

    public String getInputValueFromModel(){
        return this.calcModel.getInputValueAsString();
    }
    public Double getInputValueFromModelAsDouble(){
        return this.calcModel.getInputValueAsDouble();
    }

    public void setCalcModelResult(double value){
        this.calcModel.setResult(value);
    }
}
