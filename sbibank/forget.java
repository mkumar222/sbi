import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.sql.*;
import java.util.regex.*;

class forget implements FocusListener,ActionListener
{
	JFrame fr;
	JLabel l1,l2,lclose;
	JTextField t1,t2;	
	JButton b1,b2;
	Border border1,border2; 
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public forget()
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		fr = new JFrame();
		fr.setTitle("STATE BANK OF INDIA");
		Image img = Toolkit.getDefaultToolkit().getImage("images//sbi.jpg");
		fr.setIconImage(img);
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setContentPane(new JLabel(new ImageIcon("images/forget.png")));

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
		l2 = new JLabel("Email-ID");
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
		t2 = new JTextField();
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
		b2 = new JButton("Submit");
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

		fr.setUndecorated(true);
		fr.setVisible(true);
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
			try
			{
				if(t1.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"username can't be leave blank");
					t1.requestFocus();
				}
				else if(t2.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"email can't be leave blank");
					t2.requestFocus();
				}
				else
				{
					String s1 = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
					String s2 = t2.getText();
					Pattern ptr = Pattern.compile(s1);
					Matcher mat = ptr.matcher(s2);	
					if(mat.matches())
					{
						con = dao.createconnection();
						ps = con.prepareStatement("select * from sbi_login where username=? and email=?");
						ps.setString(1,t1.getText());	
						ps.setString(2,t2.getText());
						rs = ps.executeQuery();	
						if(rs.next()==true)
						{
							JOptionPane.showMessageDialog(fr,"your password is : " + rs.getString("password"));
						}
						else
						{
							JOptionPane.showMessageDialog(fr,"invalid username or password");
						}
						con.close();
					}	
					else
					{
						JOptionPane.showMessageDialog(fr,"invalid email-id");
						t2.requestFocus();
					}
				}
			}
			catch(Exception e)
			{
			}
		}
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

	public void focusLost(FocusEvent fe)
	{
	}
}