package calculator;

import calculator.operators.BinaryOperationInterface;

public class CalcModel {

    private static class MutableNum {


        private boolean isDot = false;
        private boolean isNegative = false;
        private static final String defaultWholePart = "0";
        private String wholePart = defaultWholePart;
        private String fractionalPart = "";


        public void addToInputValue(byte num) {
            //TODO:make check and throw if negative etc
            try {
                if (isDot) {
                    fractionalPart += String.valueOf(num);
                } else {
                    if (getDoubleValue() == 0) {
                        wholePart = String.valueOf(num);
                    } else {
                        wholePart += String.valueOf(num);
                    }
                }
            } catch (IllegalArgumentException e) {
                if (num < 0) {
                    throw e;
                }

            }
        }
        public void setInputValueFromDouble(double value) {
            String result = Double.toString(value);
            if (value < 0.0) {
                   isNegative = true;
                result = result.substring(1, result.length());
            }
            if (result.substring(result.length() - 2, result.length()).equals(".0")) {
                wholePart = result.substring(0, result.length() - 2);
                fractionalPart = "";
                return;
            } else {
                int count = 0;
                for (int i = 0; i <= result.length(); i++) {
                    count++;
                    if(result.substring(i,i+1).equals(".")){break;}


                }
                wholePart = result.substring(0, count - 1);
                fractionalPart = result.substring(count, result.length());
                isDot = true;

            }

        }

        public void backSpace() {
            if (isDot) {
                if (fractionalPart.length() > 0) {
                    fractionalPart = fractionalPart.substring(0, fractionalPart.length() - 1);
                } else {
                    isDot = false;
                }
            } else {
                //whole part does not! contain minus sign
                if (wholePart.length() == 1) {
                    wholePart = defaultWholePart;
                    isNegative = false;
                } else {
                    wholePart = wholePart.substring(0, wholePart.length() - 1);
                }
            }
        }

        public void toggleNegative() {
            isNegative = !isNegative;
        }


        public void putDot() {
            isDot = true;
        }


        public boolean getIsDot() {
            return isDot;
        }


        public String getStringValue() {
            String prefix = isNegative ? "-" : "";
            return isDot ? prefix + wholePart + "." + fractionalPart : prefix + wholePart;
        }

        public Double getDoubleValue() {
            String prefix = isNegative ? "-" : "";
            return isDot ? Double.valueOf(prefix + wholePart + "." + fractionalPart) : Double.valueOf(prefix + wholePart);
        }


    }

    private MutableNum inputValue;
    private Double result = 0.0;
    private BinaryOperationInterface lastOperator;
    private boolean isResultInited = false;
    private boolean isInputValueInited = false;

    public CalcModel() {
        inputValue = new MutableNum();
    }

    public void addToInputValue(byte num) {
        this.inputValue.addToInputValue(num);
        this.isInputValueInited = true;
    }
    public void setInputValueFromDouble(double value){
            this.inputValue.setInputValueFromDouble(value);
    }

    public void removeInputValue() {
        this.inputValue.wholePart = "0";
        this.inputValue.fractionalPart = "";
        this.inputValue.isDot = false;
        this.inputValue.isNegative = false;
        this.isInputValueInited = false;
    }

    public void putDot() {
        this.inputValue.putDot();
    }

    public void backSpace() {
        if (inputValue.getDoubleValue() == 0.0) {
        }
        this.inputValue.backSpace();
    }


    public String getInputValueAsString() {
        return this.inputValue.getStringValue();
    }

    public Double getInputValueAsDouble() {
        return this.inputValue.getDoubleValue();
    }


    public void setIsNegative() {
        this.inputValue.toggleNegative();
    }

    public Double getResultAsDouble() {
        return this.result;
    }

    public String getResultAsString() {
        String strResult = Double.toString(this.result);
        if (strResult.substring(strResult.length() - 2, strResult.length()).equals(".0")) {
            return strResult.substring(0, strResult.length() - 2);
        } else {
            return strResult;
        }
    }


    public void setResult(Double result) {
        this.result = result;
        this.isResultInited = true;
        this.isInputValueInited = false;
    }

    public boolean getResultInited() {
        return isResultInited;
    }

    public boolean getInputValueInited() {
        return isInputValueInited;
    }

    public BinaryOperationInterface getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(BinaryOperationInterface lastOperator) {
        this.lastOperator = lastOperator;

    }


    public boolean getIsDot() {
        return this.inputValue.getIsDot();
    }

}
