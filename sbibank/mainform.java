import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

class mainform implements ActionListener,Runnable
{
	JFrame fr;
	String user;
	JMenuBar mb;
	JMenu mnu,mnu1,mnu2;
	JMenuItem m1,m2,m3,m4,m6,m7,m8,m9,m10,m11,m12,m13;
	JMenuItem p1,p2,p3,p4,p5,p6,p7,p8,p9;
	JPopupMenu pop;
	JToolBar tb;
	JButton b1,b2,b3,b4,b5,b6,b7,b8,b9; 
	Thread th;
	JLabel ltime;	
	boolean bn=true;	

	public mainform(String un)
	{
		user = un;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame("State Bank of India : Login By " + user);
		Image img = Toolkit.getDefaultToolkit().getImage("images/sbi.jpg");
		fr.setIconImage(img);
		fr.setContentPane(new JLabel(new ImageIcon("images/mainform.png")));			
		fr.setLayout(null);

		mb = new JMenuBar();
		mnu = new JMenu("Operations");
		mnu.setFont(new Font("verdana",Font.BOLD,18));
		mnu.setForeground(Color.red);
		m1 = new JMenuItem("  New Account");		
		m2 = new JMenuItem("  Search Account");		
		m3 = new JMenuItem("  Remove Account");		
		m4 = new JMenuItem("  Modify Account");		
		
		mnu1 = new JMenu("  Transaction");		
		m9 = new JMenuItem("  Deposit   ");		
		m10 = new JMenuItem("  Withdraw   ");		
		mnu1.add(m9);		
		mnu1.addSeparator();
		mnu1.add(m10);		

		mnu2 = new JMenu("  Account Info");		
		m11 = new JMenuItem("  Balance    ");		
		m12 = new JMenuItem("  Report    ");		
		m13 = new JMenuItem("  All Accounts    ");		
		mnu2.add(m11);		
		mnu2.addSeparator();
		mnu2.add(m12);		
		mnu2.addSeparator();
		mnu2.add(m13);		
		
		m6 = new JMenuItem("  Quit Application");
		m1.setIcon(new ImageIcon("images/new.png"));	
		m2.setIcon(new ImageIcon("images/srch.png"));	
		m3.setIcon(new ImageIcon("images/del.png"));	
		m4.setIcon(new ImageIcon("images/edit.png"));	
		m9.setIcon(new ImageIcon("images/deposit.png"));	
		m10.setIcon(new ImageIcon("images/withdraw.png"));	
		m11.setIcon(new ImageIcon("images/balance.png"));	
		m12.setIcon(new ImageIcon("images/report.png"));	
		m13.setIcon(new ImageIcon("images/allcustomer.png"));	
		m6.setIcon(new ImageIcon("images/exit2.png"));
		m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,Event.CTRL_MASK));
		m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		m3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,Event.CTRL_MASK));
		m4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,Event.CTRL_MASK));
		m6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Event.CTRL_MASK));
		m9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,Event.CTRL_MASK));
		m10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,Event.CTRL_MASK));
		m11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,Event.CTRL_MASK));
		m12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,Event.CTRL_MASK));
		m13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,Event.CTRL_MASK));
		m1.setFont(new Font("verdana",Font.BOLD,14));
		m2.setFont(new Font("verdana",Font.BOLD,14));
		m3.setFont(new Font("verdana",Font.BOLD,14));
		m4.setFont(new Font("verdana",Font.BOLD,14));
		m6.setFont(new Font("verdana",Font.BOLD,14));
		m9.setFont(new Font("verdana",Font.BOLD,14));
		m10.setFont(new Font("verdana",Font.BOLD,14));
		m11.setFont(new Font("verdana",Font.BOLD,14));
		m12.setFont(new Font("verdana",Font.BOLD,14));
		m13.setFont(new Font("verdana",Font.BOLD,14));
		mnu1.setFont(new Font("verdana",Font.BOLD,14));
		mnu2.setFont(new Font("verdana",Font.BOLD,14));
		m1.setForeground(Color.blue);
		m2.setForeground(Color.blue);
		m3.setForeground(Color.blue);
		m4.setForeground(Color.blue);
		m6.setForeground(Color.blue);
		m9.setForeground(Color.blue);
		m10.setForeground(Color.blue);
		m11.setForeground(Color.blue);
		m12.setForeground(Color.blue);
		m13.setForeground(Color.blue);
		mnu1.setForeground(Color.blue);
		mnu2.setForeground(Color.blue);
		mnu.add(m1);
		mnu.addSeparator();
		mnu.add(m2);
		mnu.addSeparator();
		mnu.add(m3);
		mnu.addSeparator();
		mnu.add(m4);
		mnu.addSeparator();
		mnu.add(mnu1);
		mnu.addSeparator();
		mnu.add(mnu2);
		mnu.addSeparator();
		mnu.add(m6);
		mb.add(mnu);
		mb.setBounds(1,1,dim.width,30);
		fr.add(mb);
		m1.addActionListener(this);
		m2.addActionListener(this);
		m3.addActionListener(this);
		m4.addActionListener(this);
		m6.addActionListener(this);
		m9.addActionListener(this);
		m10.addActionListener(this);
		m11.addActionListener(this);
		m12.addActionListener(this);
		m13.addActionListener(this);

		tb = new JToolBar();
		b1 = new JButton(new ImageIcon("images/new.png"));
		b2 = new JButton(new ImageIcon("images/srch.png"));
		b3 = new JButton(new ImageIcon("images/del.png"));
		b4 = new JButton(new ImageIcon("images/edit.png"));
		b5 = new JButton(new ImageIcon("images/deposit.png"));
		b6 = new JButton(new ImageIcon("images/withdraw.png"));
		b7 = new JButton(new ImageIcon("images/balance.png"));
		b8 = new JButton(new ImageIcon("images/report.png"));
		b9 = new JButton(new ImageIcon("images/allcustomer.png"));
		ltime = new JLabel("time");
		ltime.setFont(new Font("verdana",Font.BOLD,16));
		ltime.setForeground(Color.blue);
		b1.setToolTipText("Create New Account");
		b2.setToolTipText("Search Account");
		b3.setToolTipText("Remove Account");
		b4.setToolTipText("Edit Account");
		b5.setToolTipText("Deposit");
		b6.setToolTipText("Withdraw");
		b7.setToolTipText("Balance");
		b8.setToolTipText("Report");
		b9.setToolTipText("All Customer Info");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		tb.add(b1);		
		tb.add(b2);		
		tb.add(b3);		
		tb.add(b4);		
		tb.add(b5);
		tb.add(b6);
		tb.add(b7);
		tb.add(b8);
		tb.add(b9);
		tb.add(ltime);		
		tb.setBounds(1,30,dim.width,40);
		fr.add(tb);

		pop = new JPopupMenu();
		p1 = new JMenuItem("  New Account");
		p2 = new JMenuItem("  Search Account");
		p3 = new JMenuItem("  Remove Account");
		p4 = new JMenuItem("  Edit Account");
		p5 = new JMenuItem("  Deposit");
		p6 = new JMenuItem("  Withdraw");
		p7 = new JMenuItem("  Balance");
		p8 = new JMenuItem("  Report");
		p9 = new JMenuItem("  All Customer Info");
		p1.addActionListener(this);
		p2.addActionListener(this);
		p3.addActionListener(this);
		p4.addActionListener(this);
		p5.addActionListener(this);
		p6.addActionListener(this);
		p7.addActionListener(this);
		p8.addActionListener(this);
		p9.addActionListener(this);
		p1.setIcon(new ImageIcon("images/new.png"));	
		p2.setIcon(new ImageIcon("images/srch.png"));	
		p3.setIcon(new ImageIcon("images/del.png"));	
		p4.setIcon(new ImageIcon("images/edit.png"));	
		p5.setIcon(new ImageIcon("images/deposit.png"));	
		p6.setIcon(new ImageIcon("images/withdraw.png"));	
		p7.setIcon(new ImageIcon("images/balance.png"));	
		p8.setIcon(new ImageIcon("images/report.png"));	
		p9.setIcon(new ImageIcon("images/allcustomer.png"));	
		pop.add(p1);
		pop.addSeparator();
		pop.add(p2);		
		pop.addSeparator();
		pop.add(p3);		
		pop.addSeparator();
		pop.add(p4);		
		pop.addSeparator();
		pop.add(p5);
		pop.addSeparator();
		pop.add(p6);
		pop.addSeparator();
		pop.add(p7);
		pop.addSeparator();
		pop.add(p8);
		pop.addSeparator();
		pop.add(p9);
		
		fr.addMouseListener(new MouseAdapter()
		{
			public void cat(MouseEvent me)
			{
				if(me.isPopupTrigger())
				{
					pop.show(fr,me.getX(),me.getY());
				}
			}

			public void mousePressed(MouseEvent me)
			{
				cat(me);	
			}

			public void mouseReleased(MouseEvent me)
			{	
				cat(me);
			}
		});		

		fr.setSize(dim.width,dim.height);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		th = new Thread(this);
		th.start();
	}

	public void run()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  hh:mm:ss a");
		while(bn)
		{
			Date dt = new Date();
			String str = sdf.format(dt).toString();
			ltime.setText("         "+str);	
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
			}
		}		
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==m1 || ae.getSource()==b1 || ae.getSource()==p1)
		{
			fr.setEnabled(false);
			new createaccount(fr);
		}
		if(ae.getSource()==m2 || ae.getSource()==b2 || ae.getSource()==p2)
		{
			fr.setEnabled(false);
			new searchaccount(fr);
		}
		if(ae.getSource()==m3 || ae.getSource()==b3 || ae.getSource()==p3)
		{
			fr.setEnabled(false);
			new searchaccount_delete(fr);
		}
		if(ae.getSource()==m4 || ae.getSource()==b4 || ae.getSource()==p4)
		{
			fr.setEnabled(false);
			new searchaccount_modify(fr);
		}
		if(ae.getSource()==m9 || ae.getSource()==b5 || ae.getSource()==p5)
		{
			fr.setEnabled(false);
			new deposit(fr);
		}
		if(ae.getSource()==m10 || ae.getSource()==b6 || ae.getSource()==p6)
		{
			fr.setEnabled(false);
			new withdraw(fr);
		}
		if(ae.getSource()==m11 || ae.getSource()==b7 || ae.getSource()==p7)
		{
			fr.setEnabled(false);
			new balance(fr);
		}
		if(ae.getSource()==m12 || ae.getSource()==b8 || ae.getSource()==p8)
		{
			fr.setEnabled(false);
			new report(fr);
		}
		if(ae.getSource()==m13 || ae.getSource()==b9 || ae.getSource()==p9)
		{
			fr.setEnabled(false);
			new allaccounts(fr);
		}
		if(ae.getSource()==m6)
		{
			int z = JOptionPane.showConfirmDialog(fr,"would you like to QUIT Application");
			if(z==0)
			{
				bn=false;
				th = null;
				System.exit(0);
			}
		}
	}
	
	public static void main(String args[])
	{
		new mainform("sharad");	
	}
}