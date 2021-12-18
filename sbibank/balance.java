import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.util.regex.*;
import java.sql.*;

class balance implements FocusListener,ActionListener
{
	JFrame fr,fn;
	JLabel l1,l2,l3,lclose,l4;
	JTextField t1,t2,t3;	
	JButton b1,b2;
	Border border1,border2; 
	Connection con;
	PreparedStatement ps;
		
	public balance(JFrame f)
	{
		fn = f;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		fr.setTitle("STATE BANK OF INDIA");
		Image img = Toolkit.getDefaultToolkit().getImage("images//sbi.jpg");
		fr.setIconImage(img);
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setContentPane(new JLabel(new ImageIcon("images/displaybalance.png")));

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
		l2 = new JLabel("Current Balance");
		l1.setBounds(50,90,150,30);
		l2.setBounds(50,130,150,30);
		l4.setBounds(215,90,80,30);
		l1.setFont(new Font("verdana",Font.BOLD,16));
		l2.setFont(new Font("verdana",Font.BOLD,16));
		l4.setFont(new Font("verdana",Font.BOLD,16));
		l1.setForeground(Color.pink);
		l2.setForeground(Color.pink);
		l4.setForeground(Color.green);
		fr.add(l1);
		fr.add(l2);
		fr.add(l4);
			
		border1 = BorderFactory.createLineBorder(Color.gray);
			
		t1 = new JTextField();
		t2 = new JTextField();
		t2.setEditable(false);
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,45,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t1.setBounds(210,90,230,30);
		t2.setBounds(210,130,230,30);
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

		b1 = new JButton("Reset");	
		b2 = new JButton("Submit");
		b1.setBounds(225,190,90,27);	
		b2.setBounds(325,190,90,27);
		b1.setFont(new Font("verdana",Font.BOLD,14));
		b2.setFont(new Font("verdana",Font.BOLD,14));
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
		}
		if(txt==t2)
		{
			border2 = BorderFactory.createLineBorder(Color.cyan);
			t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,45,1,1)));	
			t2.setBorder(BorderFactory.createCompoundBorder(border2,BorderFactory.createEmptyBorder(1,5,1,1)));	
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
		}
		if(ae.getSource()==b2)
		{
			if(t1.getText().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Account Number can't be leave empty");
				t1.requestFocus();
			}	
			else
			{
				if(t1.getText().length()==9)
				{
					try
					{
						con = dao.createconnection();
						PreparedStatement ps1 = con.prepareStatement("select * from sbi_customer where account_number=?");
						ps1.setString(1,"SBI-"+t1.getText());	
						ResultSet rs = ps1.executeQuery();
						if(rs.next()==true)
						{	 
							t2.setText(rs.getString("opening_balance"));
						}
						else
						{
							JOptionPane.showMessageDialog(fr,"Invalid Account Number");
						}
						con.close();
					}
					catch(Exception e)
					{
					}
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"Account Number must be 9 digits");
				}
			}
		}
	}
}