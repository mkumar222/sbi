import java.awt.*;  
import javax.swing.*;  
  
public class Layout2
{  
	JFrame fr;
  
	Layout2()
	{  
    		fr = new JFrame("Grid Layout");  
      
    		JButton b1 = new JButton("1");  
    		JButton b2 = new JButton("2");  
    		JButton b3 = new JButton("3");  
    		JButton b4 = new JButton("4");  
    		JButton b5 = new JButton("5");  
        	JButton b6 = new JButton("6");  
        	JButton b7 = new JButton("7");  
    		JButton b8 = new JButton("8");  
        	JButton b9 = new JButton("9");  
          
    		fr.add(b1);
		fr.add(b2);
		fr.add(b3);
		fr.add(b4);
		fr.add(b5);  
    		fr.add(b6);
		fr.add(b7);
		fr.add(b8);
		fr.add(b9);  
  
    		fr.setLayout(new GridLayout(3,3));  
    		
    		fr.setSize(300,300);  
    		fr.setVisible(true);  
	} 
 
	public static void main(String[] args) 
	{  
    		new Layout2();  
	}  
}  