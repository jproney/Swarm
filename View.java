
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View{
	
	JFrame frame;
	JPanel panel;
	JLabel welcome;
	
	public View(){
		frame = new JFrame();
		panel = new JPanel();
		welcome = new JLabel("Welcome");	
	}
	
	public void startGUI(){
		panel.add(welcome);
		frame.add(panel);
		frame.setSize(400,400);
        frame.setLayout(new GridLayout(3, 1));
        frame.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent windowEvent){
            	System.exit(0);
         	}        
        });    
		frame.setVisible(true);
	}
	
} 