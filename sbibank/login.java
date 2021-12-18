import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.sql.*;

class login implements FocusListener,ActionListener
{
	JFrame fr;
	JLabel l1,l2,lclose,lforget,lsignup;
	JTextField t1;	
	JPasswordField t2;
	JButton b1,b2;
	Border border1,border2; 
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public login()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		fr = new JFrame();
		fr.setTitle("STATE BANK OF INDIA");
		Image img = Toolkit.getDefaultToolkit().getImage("images//sbi.jpg");
		fr.setIconImage(img);
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setContentPane(new JLabel(new ImageIcon("images/login.png")));

		lclose = new JLabel(new ImageIcon("images/exit2.png"));
		lclose.setBounds(455,10,32,32);
		lclose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lclose);
		lclose.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lclose.setIcon(new ImageIcon("images/exit3.png"));
			}
			public void mouseExited(MouseEvent me)
			{
				lclose.setIcon(new ImageIcon("images/exit2.png"));
			}
			public void mouseClicked(MouseEvent me)
			{
				fr.dispose();
			}
		});

		l1 = new JLabel("UserName");
		l2 = new JLabel("Password");
		l1.setBounds(70,90,120,30);
		l2.setBounds(70,130,120,30);
		l1.setFont(new Font("verdana",Font.BOLD,18));
		l2.setFont(new Font("verdana",Font.BOLD,18));
		l1.setForeground(Color.pink);
		l2.setForeground(Color.pink);
		fr.add(l1);
		fr.add(l2);
			
		border1 = BorderFactory.createLineBorder(Color.gray);
			
		t1 = new JTextField();
		t2 = new JPasswordField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		
		t1.setBounds(190,90,230,30);
		t2.setBounds(190,130,230,30);
		t1.setFont(new Font("verdana",Font.BOLD,17));
		t2.setFont(new Font("verdana",Font.BOLD,17));
		t1.setOpaque(false);
		t2.setOpaque(false);
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		t1.setCaretColor(Color.green);
		t2.setCaretColor(Color.green);
		fr.add(t1);	
		fr.add(t2);
		t1.addFocusListener(this);	
		t2.addFocusListener(this);

		b1 = new JButton("Reset");	
		b2 = new JButton("Login");
		b1.setBounds(205,175,90,27);	
		b2.setBounds(305,175,90,27);
		b1.setFont(new Font("verdana",Font.BOLD,14));
		b2.setFont(new Font("verdana",Font.BOLD,14));
		b1.setForeground(Color.blue);
		b2.setForeground(Color.blue);
		fr.add(b1);
		fr.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);

		lsignup = new JLabel("<html><u>New Signup</u></html>");
		lsignup.setBounds(70,210,80,20);
		lsignup.setFont(new Font("verdana",Font.PLAIN,13));
		lsignup.setForeground(Color.cyan);
		lsignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lsignup);
		lsignup.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				new signup();
			}
		});

		lforget = new JLabel("<html><u>Forget Password?</u></html>");
		lforget.setBounds(70,235,120,20);
		lforget.setFont(new Font("verdana",Font.PLAIN,13));
		lforget.setForeground(Color.cyan);
		lforget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lforget);
		lforget.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				new forget();
			}
		});

		fr.setUndecorated(true);
		fr.setVisible(true);
	}

	public void focusGained(FocusEvent fe)
	{
		JTextField txt = (JTextField)fe.getComponent();
		if(txt==t1)
		{
			border2 = BorderFactory.createLineBorder(Color.cyan);
			t1.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,5,1,1)));	
			t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		}
		if(txt==t2)
		{
			border2 = BorderFactory.createLineBorder(Color.cyan);
			t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
			t2.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,5,1,1)));	
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			t1.setText("");
			t2.setText("");
		}

		if(ae.getSource()==b2)
		{
			if(t1.getText().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"username can't be leave empty");	
				t1.requestFocus();
			}
			else if(t2.getText().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"password can't be leave empty");	
				t2.requestFocus();
			}
			else 
			{
				try
				{
					con = dao.createconnection();
					ps = con.prepareStatement("select * from sbi_login where username=? and password=?");
					ps.setString(1,t1.getText());
					ps.setString(2,t2.getText());
					rs = ps.executeQuery();
					if(rs.next()==true)
					{
						String un = t1.getText();
						JOptionPane.showMessageDialog(fr,"Welcome, " + t1.getText().toUpperCase());
						fr.dispose();
						new mainform(un);	
					}
					else
					{
						JOptionPane.showMessageDialog(fr,"sorry! invalid username or password");
					}
					con.close();
				}
				catch(Exception e)
				{
				}
			}
		}
	}

	public void focusLost(FocusEvent fe)
	{
	}

	public static void main(String args[])
	{
		new login();
	}
}