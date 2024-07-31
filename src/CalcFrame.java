
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.*;

import swing.*;

public class CalcFrame extends JFrame implements ActionListener{
  JButton[] numButton;
  JButton[] functiButtons = new JButton[6];
  JButton clearButton;
  JButton addition;
  JButton subtract;
  JButton multiply;
  JButton divides;
  JButton equalsButton;
  JTextField textField;
  Double num1=0.0,num2=0.0, result=0.0;
  String operator;
  JToggleButton toggleButton;
  SwitchButton switchbutton;
  private static final String MODE_KEY = "mode";
  private static final String DARK_MODE = "dark";
  private static final String LIGHT_MODE = "light";
  private Preferences preferences;

  CalcFrame(){
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
  this.setLayout(new FlowLayout());
  this .setSize(300,500);
  preferences = Preferences.userRoot().node(this.getClass().getName());
  //this.getContentPane().setBackground(Color.WHITE);
  //this.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));

    // button = new JButton("Submit");
    // button.addActionListener(this);

    textField = new JTextField();
    textField.setPreferredSize(new Dimension(280,70));
    textField.setBorder(null);
    textField.setFont(new Font("Times New Roman",Font.PLAIN,35));
    textField.setEditable(false);
    
    textField.setBackground(Color.WHITE);
    
    
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(280, 330));
    panel.setBackground(Color.GRAY);
    panel.setLayout(new GridLayout(4,3,1,1));

    clearButton = new JButton("C");
    addition = new JButton("+");
    subtract = new JButton("-");
    multiply = new JButton("x");
    divides = new JButton("/");
    equalsButton = new JButton("=");

    functiButtons[0] = clearButton;
    functiButtons[1] = addition;
    functiButtons[2] = subtract;
    functiButtons[3] = multiply;
    functiButtons[4] = divides;
    functiButtons[5] = equalsButton;

    for(int i = 0 ; i <= 5 ;i++){
      functiButtons[i].addActionListener(this);
      functiButtons[i].setFocusable(false);
       functiButtons[i].setBackground(Color.WHITE);
    }
    
    numButton = new JButton[10];
    
    for(int i =0 ; i < 10 ; i++){
      numButton[i] = new JButton(String.valueOf(i));
      numButton[i].addActionListener(this);
      numButton[i].setFocusable(false);
      numButton[i].setBackground(Color.WHITE);
      //numButton[i].setForeground(Color.WHITE);
      
      panel.add(numButton[i]);
      if(i==2){
        panel.add(clearButton);
      }
      else if(i==5){
        panel.add(addition);
      }
      else if(i==8){
        panel.add(subtract);
      }
    }
    // toggleButton = new JToggleButton("Mode");
    // toggleButton.addActionListener(this);
    // toggleButton.setFocusable(false);
    

    
    panel.add(multiply);
    panel.add(divides);
    panel.add(equalsButton);

    switchbutton = new SwitchButton();
     switchbutton.addEventSelected(new EventSwitchSelected() {
      @Override
      public void onSelected(boolean selected) {
        toggleMode();
          // if (selected) {
          //    System.out.println("Selected");
          //    darkMode();

             
          // } else {
          //     System.out.println("UnSelected"); 
          //     lightMode();
          // }
      }
  });
    //this.add(button);
    this.add(textField);
    this.add(panel);
    //this.add(toggleButton);
    this.add(switchbutton);
    //this.pack();
    this.setResizable(false);
    this.setVisible(true);
    applyStoredMode();
  
}
private void applyStoredMode() {
  String storedMode = preferences.get(MODE_KEY, LIGHT_MODE); // Default to light mode if mode not found
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

  // Apply the new mode
  if (newMode.equals(DARK_MODE)) {
      darkMode();
  } else {
      lightMode();
  }
}

public void darkMode(){
 this.getContentPane().setBackground(new Color(105,105,105));
       for(int i = 0 ; i <= 5 ;i++){
      
      functiButtons[i].setBackground(Color.BLACK);
      functiButtons[i].setForeground(Color.WHITE);
    }
    for(int i =0 ; i < 10 ; i++){
      
      numButton[i].setBackground(Color.BLACK);
      numButton[i].setForeground(Color.WHITE);
    }
    textField.setBackground(Color.BLACK);
    textField.setForeground(Color.WHITE);
}

public void lightMode(){
this.getContentPane().setBackground(null);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        for(int i = 0 ; i <= 5 ;i++){
      
      functiButtons[i].setBackground(Color.WHITE);
      functiButtons[i].setForeground(Color.BLACK);
    }
    for(int i =0 ; i < 10 ; i++){
      
      numButton[i].setBackground(Color.WHITE);
      numButton[i].setForeground(Color.BLACK);
    }  
}

  @Override
  public void actionPerformed(ActionEvent e) {
    // if(e.getSource()==toggleButton){
    //   boolean selected=toggleButton.getModel().isSelected();
    //   if(selected){
    //     System.out.println("Selected");
    //      darkMode();
    
    //   }
    //   else{
    //     System.out.println("No");
    //    lightMode();
    //   }
    // }
    
    if(e.getSource()==clearButton){
        textField.setText("");
        num1=0.0;
        num2=0.0;
        result=0.0;
    }
    for(int i = 0 ; i < 10 ; i++){
    if(e.getSource()==numButton[i]){
      textField.setText(textField.getText().concat(String.valueOf(i)));
    }}


    if(e.getSource()==addition){
    if(!textField.getText().equals("")){
     num2= Double.parseDouble(textField.getText());}
     textField.setText("");
     operator = "+";
     results(num2, operator);
    }
        
    
    if(e.getSource()== subtract){
       if(!textField.getText().equals("")){
     num2= Double.parseDouble(textField.getText());}
        operator = "-";
        results(num2,operator);
        textField.setText("");
    }
    if(e.getSource()== multiply){
      
        if(!textField.getText().equals("")){
     num2= Double.parseDouble(textField.getText());}
        operator = "*";
        results(num2,operator);
        textField.setText("");
    }
    if(e.getSource()== divides){
        if(!textField.getText().equals("")){
     num1= Double.parseDouble(textField.getText());}
        operator = "/";
       // results(num2,operator);
        textField.setText("");
    }
    if(e.getSource()== equalsButton){
      if(!textField.getText().equals("")){
     num2= Double.parseDouble(textField.getText());}
      
      results(num2, operator);
      textField.setText(String.valueOf(result));
      
       num1 = 0.0 ;
    }
   }

  public Double results(Double num2, String operator){
    try{
      switch (operator) {
        case "+":
          //num1 = Integer.parseInt(textField.getText());
          result = num1 + num2;
          
          System.out.println("result:"+result);
          break;
          case "-":
          if(num1!=0){
          result = num1 - num2;
          System.out.println("result:"+result);}
          else{
            result = num2 - num1;
            System.out.println("result:"+result);
          }
          break;
          case "*":
          if(num1!=0){
          result = num1 * num2;
          }
          else{
            result = num2;
          }
          System.out.println("result:"+result);
          break;
          case "/":
          try{
          if(num1!=0){
          result = (double)Math.round((num1/num2) * 10000000000d) / 10000000000d;
        }
       
          else{
            result = (double)0;
          }
          
          System.out.println("result:"+result);
        }
        catch(ArithmeticException ex){
          textField.setText("NA");
          System.out.println("Division by zero");
        }
          break;
      
        default :
            
          break;
      }
      num1 = result;
      
      }
      catch(Exception n){
        result = num2;
      }
      
      return result;
  }
}





