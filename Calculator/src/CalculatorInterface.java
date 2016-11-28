import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalculatorInterface {
    private JFrame mainFrame;
    private JPanel keyboard;
    private JTextField outputBox;

    public static void main(String[] args){
        CalculatorInterface CalculatorInterface = new CalculatorInterface();
        CalculatorInterface.prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("the best calc ever");
        mainFrame.setSize(600, 200);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        keyboard = new JPanel();
        keyboard.setSize(400, 400);
        keyboard.setLayout(new GridLayout(4, 5, 10, 10));

        JButton zero = new JButton("0");
        zero.setActionCommand("0");
        zero.addActionListener(new ActionListenerNumeric());
        keyboard.add(zero);

        JButton one = new JButton("1");
        one.setActionCommand("1");
        one.addActionListener(new ActionListenerNumeric());
        keyboard.add(one);

        JButton two = new JButton("2");
        two.setActionCommand("2");
        two.addActionListener(new ActionListenerNumeric());
        keyboard.add(two);

        JButton three = new JButton("3");
        three.setActionCommand("3");
        three.addActionListener(new ActionListenerNumeric());
        keyboard.add(three);

        JButton four = new JButton("4");
        four.setActionCommand("4");
        four.addActionListener(new ActionListenerNumeric());
        keyboard.add(four);

        JButton five = new JButton("5");
        five.setActionCommand("5");
        five.addActionListener(new ActionListenerNumeric());
        keyboard.add(five);

        JButton six = new JButton("6");
        six.setActionCommand("6");
        six.addActionListener(new ActionListenerNumeric());
        keyboard.add(six);

        JButton seven = new JButton("7");
        seven.setActionCommand("7");
        seven.addActionListener(new ActionListenerNumeric());
        keyboard.add(seven);

        JButton eight = new JButton("8");
        eight.setActionCommand("8");
        eight.addActionListener(new ActionListenerNumeric());
        keyboard.add(eight);

        JButton nine = new JButton("9");
        nine.setActionCommand("9");
        nine.addActionListener(new ActionListenerNumeric());
        keyboard.add(nine);

        JButton addition = new JButton("+");
        addition.setActionCommand("+");
        addition.addActionListener(new ActionListenerNumeric());
        keyboard.add(addition);

        JButton subtraction = new JButton("-");
        subtraction.setActionCommand("-");
        subtraction.addActionListener(new ActionListenerNumeric());
        keyboard.add(subtraction);

        JButton division = new JButton("/");
        division.setActionCommand("/");
        division.addActionListener(new ActionListenerNumeric());
        keyboard.add(division);

        JButton multiplication = new JButton("*");
        multiplication.setActionCommand("*");
        multiplication.addActionListener(new ActionListenerNumeric());
        keyboard.add(multiplication);

        JButton execution = new JButton("=");
        execution.setActionCommand("=");
        execution.addActionListener(new ActionListenerCalculate());
        keyboard.add(execution);

        JButton cleaning = new JButton("<<");
        cleaning.setActionCommand("clear");
        cleaning.addActionListener(new ActionListenerClear());
        keyboard.add(cleaning);

        outputBox = new JTextField();
        // ввод с клавиатуры отключен специально.
        outputBox.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                e.consume();
            }
        });
        outputBox.setSize(100, 200);
        outputBox.setLayout(new GridLayout());


        mainFrame.add(keyboard);
        mainFrame.add(outputBox);
        mainFrame.setVisible(true);
    }

    public class ActionListenerCalculate implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                outputBox.setText(Calculator.calculateExpression(outputBox.getText()));
            }
            catch (NotAvailableOperator | NotValidString | TooManyOperators | TooManyOperands | UnexpectedErrorInString exp) {
                outputBox.setText(exp.getMessage());
            }
        }
    }

    public class ActionListenerClear implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            outputBox.setText("");
        }
    }

    public class ActionListenerNumeric implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String oldText = new String();
            oldText = outputBox.getText();
            outputBox.setText(oldText + e.getActionCommand());
        }
    }

}
