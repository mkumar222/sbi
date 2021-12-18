import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;

class report implements ActionListener
{
	String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	JFrame fr,mainform;
	JLabel lclose,l1,l2,l3,l4,l5,l6,l7;
	JTextField t1,t2,t3;
	JButton b1;
	List ls1,ls2;
	JComboBox cm;	
	JTable tab;
	JScrollPane jsp;
	Connection con;
	PreparedStatement ps0,ps1,ps2;
	ResultSet rs0,rs1,rs2;

	public report(JFrame mf)
	{
		mainform = mf;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		Image img = Toolkit.getDefaultToolkit().getImage("images/sbi.jpg");
		fr.setIconImage(img);
		fr.setContentPane(new JLabel(new ImageIcon("images/displayreport.png")));			
		fr.setLayout(null);

		lclose = new JLabel(new ImageIcon("images/exit2.png"));
		lclose.setBounds(950,12,32,32);
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
				mainform.setEnabled(true);
				fr.dispose();
			}
		});

		l1 = new JLabel("Account Number");
		l2 = new JLabel("SBI-");
		l1.setBounds(50,80,150,27);
		l2.setBounds(215,80,80,27);
		l1.setFont(new Font("verdana",Font.BOLD,16));
		l2.setFont(new Font("verdana",Font.BOLD,16));
		l1.setForeground(Color.cyan);
		l2.setForeground(Color.green);
		fr.add(l1);
		fr.add(l2);

		Border border1 = BorderFactory.createLineBorder(Color.gray);
		t1 = new JTextField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,45,1,1)));	
		t1.setBounds(210,80,160,27);
		t1.setFont(new Font("verdana",Font.BOLD,16));
		t1.setForeground(Color.cyan);
		t1.setOpaque(false);
		t1.setCaretColor(Color.green);
		fr.add(t1);
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

		l3 = new JLabel("Select Month");
		l3.setBounds(50,125,120,27);
		l3.setFont(new Font("verdana",Font.BOLD,16));
		l3.setForeground(Color.cyan);
		fr.add(l3);
		
		cm = new JComboBox();
		for(int i=0 ; i<12 ; i++)
		{
			cm.addItem(month[i]);
		}
		cm.setBounds(210,125,90,27);
		cm.setFont(new Font("verdana",Font.BOLD,17));
		fr.add(cm);

		b1 = new JButton("GO");
		b1.setBounds(310,125,60,27);
		b1.setFont(new Font("verdana",Font.BOLD,15));
		b1.setForeground(Color.blue);
		b1.addActionListener(this);
		fr.add(b1);

		l4 = new JLabel("Deposit");
		l5 = new JLabel("Withdraw");
		l6 = new JLabel("Opening Balance");
		l7 = new JLabel("Current Balance");
		l4.setBounds(500,125,150,30);
		l5.setBounds(750,125,150,30);
		l6.setBounds(500,420,150,30);
		l7.setBounds(500,455,150,30);
		l4.setFont(new Font("verdana",Font.BOLD,16));
		l5.setFont(new Font("verdana",Font.BOLD,16));
		l6.setFont(new Font("verdana",Font.BOLD,16));
		l7.setFont(new Font("verdana",Font.BOLD,16));
		l4.setForeground(Color.cyan);
		l5.setForeground(Color.cyan);
		l6.setForeground(Color.cyan);
		l7.setForeground(Color.cyan);
		fr.add(l4);
		fr.add(l5);
		fr.add(l6);
		fr.add(l7);

		ls1 = new List();
		ls2 = new List();
		ls1.setBounds(500,155,180,250);
		ls2.setBounds(750,155,180,250);
		ls1.setFont(new Font("verdana",Font.BOLD,16));
		ls2.setFont(new Font("verdana",Font.BOLD,16));
		ls1.setForeground(Color.blue);
		ls2.setForeground(Color.blue);
		fr.add(ls1);
		fr.add(ls2);

		t2 = new JTextField();
		t2.setEditable(false);
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t2.setBounds(670,423,150,27);
		t2.setFont(new Font("verdana",Font.BOLD,16));
		t2.setForeground(Color.cyan);
		t2.setOpaque(false);
		t2.setCaretColor(Color.green);
		fr.add(t2);

		t3 = new JTextField();
		t3.setEditable(false);
		t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t3.setBounds(670,458,150,27);
		t3.setFont(new Font("verdana",Font.BOLD,16));
		t3.setForeground(Color.cyan);
		t3.setOpaque(false);
		t3.setCaretColor(Color.green);
		fr.add(t3);

		fr.setBounds((dim.width-1000)/2,(dim.height-500)/2,1000,500);
		fr.setUndecorated(true);
		fr.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			if(t1.getText().length()==0)
			{	
				JOptionPane.showMessageDialog(fr,"Please Enter Account Number");
				t1.requestFocus();
			}
			else
			{
				if(t1.getText().length()!=0 && t1.getText().length()<9)
				{
					JOptionPane.showMessageDialog(fr,"Invalid Account Number must be 9 digit wide");
					t1.requestFocus();
				}
				else
				{
					try
					{
						con = dao.createconnection();
						ps0 = con.prepareStatement("select * from sbi_customer where account_number=?");
						ps1 = con.prepareStatement("select * from sbi_deposit where account_number=? and month=?");
						ps2 = con.prepareStatement("select * from sbi_withdraw where account_number=? and month=?");
										
						ps0.setString(1,"SBI-"+t1.getText());
						rs0 = ps0.executeQuery();
				
						ps1.setString(1,"SBI-"+t1.getText());
						ps1.setString(2,cm.getSelectedItem().toString());
						rs1 = ps1.executeQuery();
				
						ps2.setString(1,"SBI-"+t1.getText());
						ps2.setString(2,cm.getSelectedItem().toString());
						rs2 = ps2.executeQuery();
						while(rs1.next())
						{
							ls1.add(rs1.getString("deposit_date").substring(0,rs1.getString("deposit_date").indexOf(" "))+"  :  "+rs1.getString("deposit"));
						}
						while(rs2.next())
						{
							ls2.add(rs2.getString("withdraw_date").substring(0,rs2.getString("withdraw_date").indexOf(" "))+"  :  "+rs2.getString("withdraw"));
						}
						
						if(rs0.next())
						{
							t2.setText(rs0.getString("opening_balance"));
							t3.setText(rs0.getString("current_balance"));
						}
						con.close(); 	
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
			}
		}
	}
}