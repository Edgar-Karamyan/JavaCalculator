package calculator.view;

import calculator.controller.CalcController;
import calculator.operators.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

//where is the problem?

public class CalcView implements CalcViewInterface {
    private CalcController controller;



    enum InputOperation {
        BACKSPACE( a -> a.backspace()
                /*new Consumer<CalcController>(){

            @Override
            public void accept(CalcController calcController) {
                calcController.//invoke BACKSPACE
            }
            }*/),
        DOT(a -> a.inputDot()),
        CLEAN(a -> a.cleanAll()),
        CALCULATE(a -> a.calculate()),
//        ROOT(a -> a.root()),
        INVERTSIGHT(a -> a.invertSign());



        InputOperation(Consumer<CalcController> s) {
            this.s = s;
        }

        Consumer<CalcController> s;

        public void invoke(CalcController controller) {
            s.accept(controller);
        }

    }

    private JButton[][] buttonArr = new JButton[][]{
            {new OperatorButton("С", InputOperation.CLEAN), new OperatorButton("⇦", InputOperation.BACKSPACE), new OperatorButton("%", BiOperation.PERCENT), new OperatorButton("✖", BiOperation.MULTIPLY)},
            {new OperatorButton("7", (byte) 7), new OperatorButton("8", (byte) 8), new OperatorButton("9", (byte) 9), new OperatorButton("÷", BiOperation.DIVISION)},
            {new OperatorButton("4", (byte) 4), new OperatorButton("5", (byte) 5), new OperatorButton("6", (byte) 6), new OperatorButton("-", BiOperation.MINUS)},
            {new OperatorButton("1", (byte) 1), new OperatorButton("2", (byte) 2), new OperatorButton("3", (byte) 3), new OperatorButton("+", BiOperation.PLUS)},
            {new OperatorButton("±", InputOperation.INVERTSIGHT ), new OperatorButton("0", (byte) 0), new OperatorButton(".", InputOperation.DOT), new OperatorButton("═", InputOperation.CALCULATE)},
            {new OperatorButton("√", UnaryOperation.ROOT ), new OperatorButton("√", UnaryOperation.ROOT ),  new OperatorButton("√", UnaryOperation.ROOT ), new OperatorButton("√", UnaryOperation.ROOT )}
    };
    private final String title = "Super-Puper Calculator!";
    private JTextField text;

    public CalcView(CalcController controller) {
        this.controller = controller;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                init();
            }

        });

    }

    @Override
    public void updateValue(String val) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                text.setText(val);
            }
        });
    }


    private class OperatorButton extends JButton {

        public OperatorButton(String symbol, OperationInterface operation) {
            this.setText(symbol);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (operation instanceof BinaryOperationInterface) {
                        controller.operate((BinaryOperationInterface) operation);
                    }
                    //TODO: make the same for unary operation
                    if (operation instanceof UnaryOperationInterface) {
                        controller.unaryOperate((UnaryOperationInterface) operation);
                    }
                }
            });
        }

        public OperatorButton(String symbol, byte value) {
            this.setText(symbol);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.inputValue(value);
                }
            });
        }

        public OperatorButton(String symbol, InputOperation operation) {
            this.setText(symbol);
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    operation.invoke(controller);

                }
            });
        }


    }


    private void init() {
// Text Field settings
        text = new JTextField();

        text.setHorizontalAlignment(JTextField.RIGHT);
        text.setEditable(false);
        text.setText("0");
        text.setFont(text.getFont().deriveFont(25));
        text.setPreferredSize(new Dimension(300, 30));
//finished Text`s Field settings

//Creating Setting and filling a JPanel
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(15, 8, 6, 6);

        JPanel inputFieldPanel = new JPanel(new GridBagLayout());

        inputFieldPanel.add(text, c);
        JPanel keyboardInputPanel = new JPanel(new GridLayout(6, 4));


        for (int i = 0; i < 6; i++) {

            for (int a = 0; a < 4; a++) {
                keyboardInputPanel.add(buttonArr[i][a]);

            }

        }
        // Finished the JPanel`s Creating Setting and filling

        // Creating and setting JFrame and add the Panel on it.
        BorderLayout bl = new BorderLayout();
        bl.setVgap(10);
        JFrame frame = new JFrame(title);
        frame.setLayout(bl);
        frame.add(inputFieldPanel, bl.PAGE_START);
        frame.add(keyboardInputPanel, BorderLayout.CENTER);
        frame.setSize(320, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}

