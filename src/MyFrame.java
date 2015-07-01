

import java.awt.*;
import javax.swing.*;

class MyFrame 
{
	public static void main(String[] args) {
		 JFrame f = new JFrame();
	 
		 f.setTitle("My Frame");
		 f.setSize(400,200);
		 f.setLocation(700,300);
		 f.setLayout((new BorderLayout()));
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		  JButton btn = new JButton("Start");
		  btn.setBounds(70, 100, 100, 50);
	      f.add(btn);
	      
	      JButton btn2 = new JButton("Stop");
		  btn2.setBounds(200, 100, 100, 50);
	      f.add(btn2);
		
		 f.add(new Circle());
		  f.show();  
	}
}