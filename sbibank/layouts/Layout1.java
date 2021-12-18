import java.awt.*;  
import javax.swing.*;  
  
class Layout1
{  
	JFrame fr;
  
	public Layout1()
	{  
		fr = new JFrame("Border Layout");  
      
    		JButton b1 = new JButton("NORTH");;  
    		JButton b2 = new JButton("SOUTH");;  
    		JButton b3 = new JButton("EAST");;  
    		JButton b4 = new JButton("WEST");;  
    		JButton b5 = new JButton("CENTER");;  
      
    		fr.add(b1,BorderLayout.NORTH);  
    		fr.add(b2,BorderLayout.SOUTH);  
    		fr.add(b3,BorderLayout.EAST);  
    		fr.add(b4,BorderLayout.WEST);  
    		fr.add(b5,BorderLayout.CENTER);  
      
    		fr.setSize(300,300);  
    		fr.setVisible(true);  
	}  

	public static void main(String[] args)
	{  
   		new Layout1();  
	}  
}  