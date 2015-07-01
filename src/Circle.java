

import java.awt.*;
import javax.swing.*;


	public class Circle extends JPanel {

		public void paint(Graphics g)
		{
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setColor(Color.GREEN);
			g2d.fillOval(130, 30, 30, 30);
			
			g2d.setColor(Color.RED);
			g2d.fillOval(205, 30, 30, 30);
			
		} 
		
}
