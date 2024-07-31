import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.*;

public class Calchatgpt extends JFrame implements ActionListener {
    JButton[] numButton;
    JButton[] functiButtons = new JButton[7];
    JButton clearButton;
    JButton addition;
    JButton subtract;
    JButton multiply;
    JButton divides;
    JButton point;
    JButton equalsButton;
    JTextField textField;
    Double num1 = 0.0, num2 = 0.0, result = 0.0;
    String operator;
    private static final String MODE_KEY = "mode";
    private static final String DARK_MODE = "dark";
    private static final String LIGHT_MODE = "light";
    private Preferences preferences;

    Calchatgpt() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setLayout(new FlowLayout());
        this.setSize(300, 500);
        preferences = Preferences.userRoot().node(this.getClass().getName());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(280, 70));
        textField.setBorder(null);
        textField.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(280, 330));
        panel.setBackground(Color.GRAY);
        panel.setLayout(new GridLayout(4, 3, 1, 1));

        clearButton = new JButton("C");
        addition = new JButton("+");
        subtract = new JButton("-");
        multiply = new JButton("x");
        divides = new JButton("/");
        equalsButton = new JButton("=");
        point = new JButton(".");

        functiButtons[0] = clearButton;
        functiButtons[1] = addition;
        functiButtons[2] = subtract;
        functiButtons[3] = multiply;
        functiButtons[4] = divides;
        functiButtons[5] = equalsButton;
        functiButtons[6] = point;

        for (int i = 0; i <=6 ; i++) {
            functiButtons[i].addActionListener(this);
            functiButtons[i].setFocusable(false);
            functiButtons[i].setBackground(Color.WHITE);
        }

        numButton = new JButton[10];

        for (int i = 0; i < 10; i++) {
            numButton[i] = new JButton(String.valueOf(i));
            numButton[i].addActionListener(this);
            numButton[i].setFocusable(false);
            numButton[i].setBackground(Color.WHITE);
            panel.add(numButton[i]);
            if (i == 2) {
                panel.add(clearButton);
            } else if (i == 5) {
                panel.add(addition);
            } else if (i == 8) {
                panel.add(subtract);
            }
        }

        panel.add(multiply);
        panel.add(divides);
        panel.add(point);
        panel.add(equalsButton);

        this.add(textField);
        this.add(panel);
        this.setResizable(false);
        this.setVisible(true);
        applyStoredMode();
    }

    private void applyStoredMode() {
        String storedMode = preferences.get(MODE_KEY, LIGHT_MODE); 
        if (storedMode.equals(DARK_MODE)) {
            darkMode();
        } else {
            lightMode();
        }
    }

    private void toggleMode() {
        String currentMode = preferences.get(MODE_KEY, LIGHT_MODE);
        String newMode = currentMode.equals(DARK_MODE) ? LIGHT_MODE : DARK_MODE;
        preferences.put(MODE_KEY, newMode);

        if (newMode.equals(DARK_MODE)) {
            darkMode();
        } else {
            lightMode();
        }
    }

    public void darkMode() {
        this.getContentPane().setBackground(new Color(105, 105, 105));
        for (int i = 0; i <= 5; i++) {
            functiButtons[i].setBackground(Color.BLACK);
            functiButtons[i].setForeground(Color.WHITE);
        }
        for (int i = 0; i < 10; i++) {
            numButton[i].setBackground(Color.BLACK);
            numButton[i].setForeground(Color.WHITE);
        }
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
    }

    public void lightMode() {
        this.getContentPane().setBackground(null);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        for (int i = 0; i <= 5; i++) {
            functiButtons[i].setBackground(Color.WHITE);
            functiButtons[i].setForeground(Color.BLACK);
        }
        for (int i = 0; i < 10; i++) {
            numButton[i].setBackground(Color.WHITE);
            numButton[i].setForeground(Color.BLACK);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            textField.setText("");
            num1 = 0.0;
            num2 = 0.0;
            result = 0.0;
        }
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButton[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == addition || e.getSource() == subtract || e.getSource() == multiply
                || e.getSource() == divides) {
            operator = ((JButton) e.getSource()).getText();
            if (!textField.getText().equals("")) {
                num1 = Double.parseDouble(textField.getText());
            }
            textField.setText("");
        }
        if (e.getSource() == equalsButton) {
            if (!textField.getText().equals("")) {
                num2 = Double.parseDouble(textField.getText());
            }
            result = calculateResult(num1, num2, operator);
            textField.setText(String.valueOf(result));
            num1 = 0.0;
        }
    }

    private Double calculateResult(Double num1, Double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                try {
                    return num1 / num2;
                } catch (ArithmeticException ex) {
                    textField.setText("Error: Division by zero");
                    return null;
                }
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        new CalcFrame();
    }
}

