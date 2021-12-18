import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class splash implements Runnable 
{
	JFrame fr=null;
	JLabel lb;
	Thread th;
	int w=0;

	public splash()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		fr = new JFrame();
		fr.setTitle("STATE BANK OF INDIA");
		Image img = Toolkit.getDefaultToolkit().getImage("images//sbi.jpg");
		fr.setIconImage(img);

		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);

		fr.setContentPane(new JLabel(new ImageIcon("images/sbi.png")));


		lb = new JLabel(new ImageIcon("images/line.png"));
		lb.setBounds(1,249,w,6);
		fr.add(lb);

		
		fr.setUndecorated(true);

		fr.setVisible(true);

		th = new Thread(this);
		th.start();
	}

	public void run()
	{
		for(int i=1 ; i<=500 ; i++)
		{
			try
			{
				Thread.sleep(5);
				w++;
				lb.setBounds(1,249,w,6);
			}
			catch(Exception e)
			{
			}
		}
		fr.dispose();
		new login();
	}

	public static void main(String args[])
	{
		new splash();
	}
}