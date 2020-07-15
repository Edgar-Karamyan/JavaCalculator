package calculator;

import calculator.controller.CalcController;
import calculator.operators.BiOperation;
import calculator.operators.UnaryOperation;
import calculator.view.CalcViewInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class JUnitCalculatorTest {
    static CalcModel calcModel;
    static CalcController calcController;


    @BeforeClass
    public static void initTest() {
        calcModel = new CalcModel();
        calcController = new CalcController(calcModel);
        calcController.setView(new CalcViewInterface() {
            @Override
            public void updateValue(String val) {

            }
        });
    }
    @Before
    public void cleanupBeforeTest() {
        calcController.cleanAll();

    }

    final static double testValue = 9.0;
    final static byte inputVal = 3;
    final static byte inputVal2 = 25;


    @Test
    public void operateTest(){
        calcController.inputValue(inputVal);
        calcController.operate(BiOperation.DIVISION);
        calcController.inputValue(inputVal2);
        assertEquals(Double.valueOf(inputVal), calcController.getResultFromModel());

    }

    @Test
    public void invertSignTest(){
        calcController.inputValue(inputVal);
        calcController.invertSign();
        assertEquals("-" + Byte.toString(inputVal), calcController.getInputValueFromModel());
    }

    @Test
    public void testRoot() {
        calcController.setCalcModelResult(testValue);
        calcController.unaryOperate(UnaryOperation.ROOT);
        assertEquals(Math.sqrt(testValue), calcController.getResultFromModel());
        calcController.inputValue(inputVal2);
        calcController.unaryOperate(UnaryOperation.ROOT);
        assertEquals(Math.sqrt(inputVal2), calcController.getResultFromModel());
    }

    @Test
    public void testAdInputValue() {
        calcController.cleanAll();
        calcController.inputValue(inputVal);
        assertEquals(Double.valueOf(inputVal), calcController.getInputValueFromModelAsDouble());
    }


    @Test
    public void cleanupTest() {
        calcController.inputValue(inputVal);
        calcController.operate(BiOperation.MULTIPLY);
        calcController.inputValue(inputVal2);
        calcController.cleanAll();
        assertEquals(0.0, calcController.getResultFromModel());
        assertEquals(0.0, calcController.getInputValueFromModelAsDouble());
    }

    @After
    public void cleanup() {
        calcController.cleanAll();
    }

}


