import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.io.*;
import java.sql.*;

class deleteaccount
{
	JFrame fr,mainform;
	JLabel lclose;
	JLabel p1,p2,p3,p4,p5,p6,p7,p8,p9,limg,lsearch,ldelete;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8;
	String path;
	Image original,scaled;	
	ResultSet rs;

	public deleteaccount(JFrame mf,ResultSet r)
	{
		mainform = mf;
		rs = r;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		Image img = Toolkit.getDefaultToolkit().getImage("images/sbi.jpg");
		fr.setIconImage(img);
		fr.setContentPane(new JLabel(new ImageIcon("images/deleteaccount.png")));			
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

		ldelete = new JLabel("Delete");
		ldelete.setBounds(650,15,70,28);
		ldelete.setFont(new Font("verdana",Font.BOLD,16));
		ldelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ldelete.setForeground(Color.white);
		fr.add(ldelete);
		ldelete.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				ldelete.setForeground(Color.pink);
			}
			public void mouseExited(MouseEvent me)
			{
				ldelete.setForeground(Color.white);
			}
			public void mouseClicked(MouseEvent me)
			{
				if(p9.getText().length()!=0)
				{			

				int z = JOptionPane.showConfirmDialog(fr,"Are you sure?");
				if(z==0)
				{
					try
					{
						Connection con = dao.createconnection();
						PreparedStatement ps = con.prepareStatement("delete from sbi_customer where account_number=?");
						ps.setString(1,p9.getText());
						int w = ps.executeUpdate();	
						if(w>0)
						{
							JOptionPane.showMessageDialog(fr,"Account : "+p9.getText()+" has been removed successfully");
							t1.setText("");
							t2.setText("");
							t3.setText("");
							t4.setText("");
							t5.setText("");
							t6.setText("");
							t7.setText("");
							t8.setText("");
							p9.setText("");
							limg.setIcon(new ImageIcon("images/photo.png"));
						}
						con.close();
					}
					catch(Exception e)
					{
					}
				}
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"please search the account");
				}
			}
		});

		lsearch = new JLabel("Search");
		lsearch.setBounds(800,15,70,28);
		lsearch.setFont(new Font("verdana",Font.BOLD,16));
		lsearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lsearch.setForeground(Color.white);
		fr.add(lsearch);
		lsearch.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lsearch.setForeground(Color.pink);
			}
			public void mouseExited(MouseEvent me)
			{
				lsearch.setForeground(Color.white);
			}
			public void mouseClicked(MouseEvent me)
			{
				mainform.setEnabled(true);
				new searchaccount_delete(mainform);
				fr.dispose();
			}
		});

		limg = new JLabel(new ImageIcon("images/photo.png"));
		limg.setBounds(670,115,80,70);
		fr.add(limg);

		p9 = new JLabel();
		p9.setBounds(250,14,200,30);
		p9.setFont(new Font("verdana",Font.BOLD,18));
		p9.setForeground(Color.white);
		fr.add(p9);
		
		p1 = new JLabel("Customer Name");
		p2 = new JLabel("Mobile Number");
		p3 = new JLabel("Email - ID");
		p4 = new JLabel("Adhar Number");
		p5 = new JLabel("PAN Number");
		p6 = new JLabel("Account Type");
		p7 = new JLabel("Opening Date");
		p8 = new JLabel("Current Balance");
		
		p1.setBounds(150,120,200,30);
		p2.setBounds(150,160,200,30);
		p3.setBounds(150,200,200,30);
		p4.setBounds(150,240,200,30);
		p5.setBounds(150,280,200,30);
		p6.setBounds(150,320,200,30);
		p7.setBounds(150,360,200,30);
		p8.setBounds(150,400,200,30);
		p1.setFont(new Font("verdana",Font.PLAIN,14));
		p2.setFont(new Font("verdana",Font.PLAIN,14));
		p3.setFont(new Font("verdana",Font.PLAIN,14));
		p4.setFont(new Font("verdana",Font.PLAIN,14));
		p5.setFont(new Font("verdana",Font.PLAIN,14));
		p6.setFont(new Font("verdana",Font.PLAIN,14));
		p7.setFont(new Font("verdana",Font.PLAIN,14));
		p8.setFont(new Font("verdana",Font.PLAIN,14));
		p1.setForeground(Color.cyan);
		p2.setForeground(Color.cyan);
		p3.setForeground(Color.cyan);
		p4.setForeground(Color.cyan);
		p5.setForeground(Color.cyan);
		p6.setForeground(Color.cyan);
		p7.setForeground(Color.cyan);
		p8.setForeground(Color.cyan);
		fr.add(p1);
		fr.add(p2);
		fr.add(p3);
		fr.add(p4);
		fr.add(p5);
		fr.add(p6);
		fr.add(p7);
		fr.add(p8);
					
		Border border1 = BorderFactory.createLineBorder(Color.gray);
		
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		t4 = new JTextField();
		t5 = new JTextField();
		t6 = new JTextField();
		t7 = new JTextField();
		t8 = new JTextField();
		t1.setEditable(false);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		t8.setEditable(false);
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t4.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t5.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t6.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t7.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t8.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t1.setBounds(300,120,250,25);
		t2.setBounds(300,160,250,25);
		t3.setBounds(300,200,250,25);
		t4.setBounds(300,240,250,25);
		t5.setBounds(300,280,250,25);
		t6.setBounds(300,320,250,25);
		t7.setBounds(300,360,250,25);
		t8.setBounds(300,400,250,25);
		t1.setFont(new Font("verdana",Font.PLAIN,16));
		t2.setFont(new Font("verdana",Font.PLAIN,16));
		t3.setFont(new Font("verdana",Font.PLAIN,16));
		t4.setFont(new Font("verdana",Font.PLAIN,16));
		t5.setFont(new Font("verdana",Font.PLAIN,16));
		t6.setFont(new Font("verdana",Font.PLAIN,16));
		t7.setFont(new Font("verdana",Font.PLAIN,16));
		t8.setFont(new Font("verdana",Font.PLAIN,16));
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		t3.setForeground(Color.green);
		t4.setForeground(Color.green);
		t5.setForeground(Color.green);
		t6.setForeground(Color.green);
		t7.setForeground(Color.green);
		t8.setForeground(Color.green);
		t1.setOpaque(false);
		t2.setOpaque(false);
		t3.setOpaque(false);
		t4.setOpaque(false);
		t5.setOpaque(false);
		t6.setOpaque(false);
		t7.setOpaque(false);
		t8.setOpaque(false);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
		fr.add(t4);
		fr.add(t5);
		fr.add(t6);
		fr.add(t7);
		fr.add(t8);
		
		fr.setBounds((dim.width-1000)/2,(dim.height-500)/2,1000,500);
		fr.setUndecorated(true);
		fr.setVisible(true);

		loaddata();		
	}

	private void loaddata()
	{
		try
		{
			String name = rs.getString("customer_first_name");
			if(rs.getString("customer_middle_name")!=null)
			{
				name = name + " " + rs.getString("customer_middle_name");
			}		
			if(rs.getString("customer_last_name")!=null)
			{
				name = name + " " + rs.getString("customer_last_name");
			}		

			p9.setText(rs.getString("account_number"));
			t1.setText(name.toUpperCase());
			t2.setText(rs.getString("mobile1"));
			t3.setText(rs.getString("email"));
			t4.setText(rs.getString("adhar_number"));
			t5.setText(rs.getString("pan_number"));
			t6.setText(rs.getString("account_type"));
			t7.setText(rs.getString("opening_date"));
			t8.setText(rs.getString("OPENING_BALANCE"));
			
			Blob b = rs.getBlob("photo_cus");			
			byte by[] = b.getBytes(1,(int)b.length());
			FileOutputStream fos = new FileOutputStream("photos/"+p9.getText()+"c.jpg");
			fos.write(by);
			fos.close();
			
			original = Toolkit.getDefaultToolkit().getImage("photos/"+p9.getText()+"c.jpg");
			scaled = original.getScaledInstance(80,70,Image.SCALE_DEFAULT);
			limg.setIcon(new ImageIcon(scaled));

			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}