package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import wordspew.SearchWords;

public class MainWindow {
  JFrame frame;
  JScrollPane pane;
  JTextField input;
  JTextArea textArea;

  public MainWindow() {
    initComponents();
    setListeners();
    buildWindow();
  }

  private void buildWindow() {
    frame.setSize(400, 400);
    frame.setLayout(new BorderLayout());
    
    pane.add(textArea);
    
    frame.add(textArea, BorderLayout.CENTER);
    frame.add(input, BorderLayout.NORTH);
    
    frame.setVisible(true);
    
  }

  private void setListeners() {
    input.addActionListener(e -> {
      List<String> words;
      try {
        words = new SearchWords().getWords(input.getText());
        textArea.setText(showResults(words));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });
  }

  private String showResults(List<String> list) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      sb.append(list.get(i) + "\n");
    }
    return sb.toString();
  }
  
  public void initComponents() {
    frame = new JFrame("WordSpew");
    input = new JTextField();
    textArea = new JTextArea();
    pane = new JScrollPane(textArea);
  }
  
  public static void main(String[] args) {
    new MainWindow();
  }

}
