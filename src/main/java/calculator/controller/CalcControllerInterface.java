package calculator.controller;

import calculator.operators.BiOperation;
import calculator.operators.UnaryOperationInterface;

/**
 * Created by David on 24.10.2016.
 */
public interface CalcControllerInterface {
    public String inputValue(String value);

    public String inputUnaryOperator(UnaryOperationInterface unary);
    //
    public String inputBinaryOperator(BiOperation binary);



}
