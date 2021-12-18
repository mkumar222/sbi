import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.sql.*;

class searchaccount implements FocusListener,ActionListener
{
	JFrame fr,fn;
	JLabel l1,l2,lclose;
	JTextField t1;
	JButton b1;
	Border border1,border2; 
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public searchaccount(JFrame f)
	{
		fn = f;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		fr = new JFrame();
		fr.setTitle("STATE BANK OF INDIA");
		Image img = Toolkit.getDefaultToolkit().getImage("images//sbi.jpg");
		fr.setIconImage(img);
		fr.setBounds((dim.width-500)/2,(dim.height-300)/2,500,300);
		fr.setContentPane(new JLabel(new ImageIcon("images/searchcustomer.png")));

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
		
		l1 = new JLabel("please enter any one of them");
		l1.setBounds(100,55,450,50);
		l1.setFont(new Font("verdana",Font.BOLD,18));
		l1.setForeground(Color.green);
		fr.add(l1);

		l2 = new JLabel("Account / Mobile / Adhar / PAN Number");
		l2.setBounds(46,90,450,50);
		l2.setFont(new Font("verdana",Font.BOLD,18));
		l2.setForeground(Color.pink);
		fr.add(l2);
			
		border1 = BorderFactory.createLineBorder(Color.gray);
			
		t1 = new JTextField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t1.setBounds(140,140,230,30);
		t1.setFont(new Font("verdana",Font.BOLD,17));
		t1.setOpaque(false);
		t1.setHorizontalAlignment(JTextField.CENTER);
		t1.setForeground(Color.green);
		t1.setCaretColor(Color.green);
		fr.add(t1);	
		t1.addFocusListener(this);	
		
		b1 = new JButton("Search");	
		b1.setBounds(210,190,90,27);	
		b1.setFont(new Font("verdana",Font.BOLD,14));
		b1.setForeground(Color.blue);
		fr.add(b1);
		b1.addActionListener(this);
		
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
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			if(t1.getText().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Please Enter A/C Number or Mobile Number or Adhar Number or PAN Number");	
				t1.requestFocus();
			}
			else 
			{
				try
				{
					con = dao.createconnection();
					ps = con.prepareStatement("select * from sbi_customer where account_number=? or adhar_number=? or pan_number=? or mobile1=? or mobile2=?");
					ps.setString(1,t1.getText());
					ps.setString(2,t1.getText());
					ps.setString(3,t1.getText());
					ps.setString(4,t1.getText());
					ps.setString(5,t1.getText());
					rs = ps.executeQuery();
					if(rs.next()==true)
					{
						new displayaccount(fn,rs);
						fr.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(fr,"sorry! account does not exists");
					}
					//con.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(fr,"There is no ACCOUNT");
				}
			}
		}
	}

	public void focusLost(FocusEvent fe)
	{
	}
}