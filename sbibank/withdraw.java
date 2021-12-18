import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.util.regex.*;
import java.sql.*;

class withdraw implements FocusListener,ActionListener
{
	JFrame fr,fn;
	JLabel l1,l2,l3,lclose,l4;
	JTextField t1,t2,t3;	
	JButton b1,b2;
	Border border1,border2; 
	Connection con;
	PreparedStatement ps;
		
	public withdraw(JFrame f)
	{
		fn = f;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setTitle("STATE BANK OF INDIA");
		Image img = Toolkit.getDefaultToolkit().getImage("images//sbi.jpg");
		fr.setIconImage(img);
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setContentPane(new JLabel(new ImageIcon("images/withdrawform.png")));

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
				fn.setEnabled(true);
				fr.dispose();
			}
		});

		l1 = new JLabel("Account Number");
		l4 = new JLabel("SBI-");
		l2 = new JLabel("PAN Number");
		l3 = new JLabel("Amount (in Rs)");
		l1.setBounds(50,90,150,30);
		l2.setBounds(50,130,150,30);
		l3.setBounds(50,170,150,30);
		l4.setBounds(215,90,80,30);
		l1.setFont(new Font("verdana",Font.BOLD,16));
		l2.setFont(new Font("verdana",Font.BOLD,16));
		l3.setFont(new Font("verdana",Font.BOLD,16));
		l4.setFont(new Font("verdana",Font.BOLD,16));
		l1.setForeground(Color.pink);
		l2.setForeground(Color.pink);
		l3.setForeground(Color.pink);
		l4.setForeground(Color.green);
		fr.add(l1);
		fr.add(l2);
		fr.add(l3);
		fr.add(l4);
			
		border1 = BorderFactory.createLineBorder(Color.gray);
			
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,45,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t1.setBounds(210,90,230,30);
		t2.setBounds(210,130,230,30);
		t3.setBounds(210,170,230,30);
		t1.setFont(new Font("verdana",Font.BOLD,17));
		t2.setFont(new Font("verdana",Font.BOLD,17));
		t3.setFont(new Font("verdana",Font.BOLD,17));
		t1.setOpaque(false);
		t2.setOpaque(false);
		t3.setOpaque(false);
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		t3.setForeground(Color.green);
		t1.setCaretColor(Color.green);
		t2.setCaretColor(Color.green);
		t3.setCaretColor(Color.green);
		fr.add(t1);	
		fr.add(t2);
		fr.add(t3);
		t1.addFocusListener(this);	
		t2.addFocusListener(this);
		t3.addFocusListener(this);
		
		t1.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}

				if(t1.getText().length()>8)
				{
					ke.consume();
				}
			}
		});

		t2.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();

				if(ch>=97 && ch<=122)
				{
					if(t2.getText().length()<10)
					{
						ch = Character.toUpperCase(ch);
						ke.consume();
						t2.setText(t2.getText()+ch);
					}
				}

				if(!(ch>=65 && ch<=90 || ch>='0' && ch<='9'))
				{
					ke.consume();
				}

				if(t2.getText().length()>9)
				{
					ke.consume();
				}
			}
		});

		
		t3.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}

				if(t3.getText().length()>8)
				{
					ke.consume();
				}
			}
		});
		
		b1 = new JButton("Reset");	
		b2 = new JButton("Withdraw");
		b1.setBounds(215,225,100,27);	
		b2.setBounds(325,225,100,27);
		b1.setFont(new Font("verdana",Font.BOLD,12));
		b2.setFont(new Font("verdana",Font.BOLD,12));
		b1.setForeground(Color.blue);
		b2.setForeground(Color.blue);
		fr.add(b1);
		fr.add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);

		fr.setUndecorated(true);
		fr.setVisible(true);
	}

	public void focusGained(FocusEvent fe)
	{
		JTextField txt = (JTextField)fe.getComponent();
		if(txt==t1)
		{
			border2 = BorderFactory.createLineBorder(Color.cyan);
			t1.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,45,1,1)));	
			t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
			t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		}
		if(txt==t2)
		{
			border2 = BorderFactory.createLineBorder(Color.cyan);
			t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,45,1,1)));	
			t2.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,5,1,1)));	
			t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		}
		if(txt==t3)
		{
			border2 = BorderFactory.createLineBorder(Color.cyan);
			t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,45,1,1)));	
			t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
			t3.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,5,1,1)));	
		}
	}

	public void focusLost(FocusEvent fe)
	{}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			t1.setText("");
			t2.setText("");
			t3.setText("");
		}
		if(ae.getSource()==b2)
		{
			if(t1.getText().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Account Number can't be leave empty");
				t1.requestFocus();
			}	
			else if(t2.getText().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"PAN Number can't be leave empty");
				t2.requestFocus();
			}	
			else if(t3.getText().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Amount can't be leave empty");
				t3.requestFocus();
			}
			else
			{
				if(t1.getText().length()==9 && t2.getText().length()==10)
				{
				try
				{
					con = dao.createconnection();
					PreparedStatement ps1 = con.prepareStatement("select * from sbi_customer where account_number=? and pan_number=?");
					ps1.setString(1,"SBI-"+t1.getText());	
					ps1.setString(2,t2.getText());
					ResultSet rs = ps1.executeQuery();
					if(rs.next()==true)
					{	 
						Double bal = Double.parseDouble(rs.getString("current_balance"));
						if((bal-500)>=Double.parseDouble(t3.getText()))
						{
							bal = bal - Double.parseDouble(t3.getText());	
						
							long millis = System.currentTimeMillis();  
        						java.sql.Date sdate = new java.sql.Date(millis); 
 			
							java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM");
							java.util.Date jdt = new java.util.Date();	
							String month = sdf.format(jdt).toString();

							PreparedStatement ps2 = con.prepareStatement("insert into sbi_withdraw values(?,?,?,?)");
							ps2.setString(1,"SBI-"+t1.getText());
							ps2.setString(2,t3.getText());
							ps2.setString(3,month);
							ps2.setDate(4,sdate);
							int m = ps2.executeUpdate();
							if(m>0)
							{
								ps1 = con.prepareStatement("update sbi_customer set current_balance=? where account_number=?");
								ps1.setString(1,""+bal);
								ps1.setString(2,"SBI-"+t1.getText());
								ps1.executeUpdate();
								JOptionPane.showMessageDialog(fr,"Amount Withdraw Successfully");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(fr,"Insufficient Balance");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(fr,"Invalid Account / PAN number");
					}
					con.close();
				}
				catch(Exception e)
				{
				}
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"Account Number must be 9 digits and \nPAN number must be 10 digits wide");
				}
			}
		}
	}
}