package calculator.operators;


public enum UnaryOperation implements UnaryOperationInterface {

    ROOT(new UnaryOperationInterface() {
        @Override
        public double count(double a) {
            return Math.sqrt(a);
        }
    });


    private UnaryOperationInterface action;

    UnaryOperation(UnaryOperationInterface action) {
        this.action = action;
    }

    @Override
    public double count(double a) {
        return this.action.count(a);
    }
}







