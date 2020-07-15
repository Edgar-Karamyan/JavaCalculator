package calculator.operators;


public enum BiOperation implements BinaryOperationInterface {

    MULTIPLY(new BinaryOperationInterface() {
        @Override
        public double count(double a, double b) {
            return a * b;
        }
    }),
    DIVISION(new BinaryOperationInterface() {
        @Override
        public double count(double a, double b) {
            return a / b;
        }
    }),
    MINUS(new BinaryOperationInterface() {
        @Override
        public double count(double a, double b) {
            return a - b;
        }
    }),
    PERCENT(new BinaryOperationInterface() {
        @Override
        public double count(double a, double b) {
            return (a / 100) * b;
        }
    }),
    PLUS(new BinaryOperationInterface() {
        @Override
        public double count(double a, double b) {
            return a + b;
        }
    });
    private BinaryOperationInterface action;

    BiOperation(BinaryOperationInterface action) {
        this.action = action;
    }

    @Override
    public double count(double a, double b) {
        return this.action.count(a, b);
    }


}






