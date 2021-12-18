import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.io.*;
import java.sql.*;
import java.util.regex.*;

class modifyaccount
{
	JFrame fr,mainform;
	JLabel lclose;
	JLabel n1,n2,n3,n4,p1,p2,p3,p4,p5,p6,p7,p8,p9,limg,limgn,lsearch,lupdate;
	JTextField t1,t2,t3,t5,t6,t7,t11,t22,t33,t44;
	JTextArea t4;
	String path1=null,path2=null;
	Image original,scaled;	
	ResultSet rs;
	int cp=0,np=0;

	public modifyaccount(JFrame mf,ResultSet r)
	{
		mainform = mf;
		rs = r;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		Image img = Toolkit.getDefaultToolkit().getImage("images/sbi.jpg");
		fr.setIconImage(img);
		fr.setContentPane(new JLabel(new ImageIcon("images/modifyaccount.png")));			
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

		lupdate = new JLabel("Update");
		lupdate.setBounds(650,15,70,28);
		lupdate.setFont(new Font("verdana",Font.BOLD,16));
		lupdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lupdate.setForeground(Color.white);
		fr.add(lupdate);
		lupdate.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent me)
			{
				lupdate.setForeground(Color.pink);
			}
			public void mouseExited(MouseEvent me)
			{
				lupdate.setForeground(Color.white);
			}
			public void mouseClicked(MouseEvent me)
			{
				if(p9.getText().length()!=0)
				{			
					int z = JOptionPane.showConfirmDialog(fr,"Are you sure?");
					if(z==0)
					{
						if(t1.getText().length()==0)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Customer's First Name");
							t1.requestFocus();
						}
						else if(t4.getText().length()==0)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Customer's Full Address");
							t4.requestFocus();
						}
						else if(t11.getText().length()==0)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Nominee's Name");
							t11.requestFocus();
						}
						else if(t22.getText().length()==0)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Nominee's Relation");
							t22.requestFocus();
						}
						else if(t33.getText().length()==0)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Nominee's Age");
							t33.requestFocus();
						}
						else if(t44.getText().length()==0)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Nominee's Adhar");
							t44.requestFocus();
						}
						else if(t5.getText().length()!=0 && t5.getText().length()<10)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Valid Mobile Number 1");
							t5.requestFocus();
						}
						else if(t6.getText().length()!=0 && t6.getText().length()<10)
						{
							JOptionPane.showMessageDialog(fr,"Please Enter Valid Mobile Number 2");
							t6.requestFocus();
						}
						else
						{
							boolean bool = true;
							if(t7.getText().length()!=0)
							{
								String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
								String email = t7.getText();

								Pattern ptr = Pattern.compile(regex);
								Matcher mth = ptr.matcher(email);
								if(mth.matches())
								{
									bool = true;
								}
								else
								{	
									bool = false;
								}	
							}
							if(bool == true)
							{	
								try	
								{
									Connection con = dao.createconnection();
									PreparedStatement ps = con.prepareStatement("update sbi_customer set customer_first_name=?,customer_middle_name=?,customer_last_name=?,address=?,mobile1=?,mobile2=?,email=?,nominee_name=?,nominee_relation=?,nominee_age=?,nominee_adhar=?,photo_cus=?,photo_nom=? where account_number=?");
									ps.setString(1,t1.getText());
									ps.setString(2,t2.getText());	
									ps.setString(3,t3.getText());
									ps.setString(4,t4.getText());
									ps.setString(5,t5.getText());
									ps.setString(6,t6.getText());
									ps.setString(7,t7.getText());
									ps.setString(8,t11.getText());
									ps.setString(9,t22.getText());
									ps.setString(10,t33.getText());
									ps.setString(11,t44.getText());
									
									FileInputStream fis1 = new FileInputStream(path1);
									FileInputStream fis2 = new FileInputStream(path2);
									ps.setBinaryStream(12,fis1,fis1.available());
									ps.setBinaryStream(13,fis2,fis2.available());
									ps.setString(14,p9.getText());
									int w = ps.executeUpdate();	
									if(w>0)
									{
										JOptionPane.showMessageDialog(fr,"Account : "+p9.getText()+" has been updated successfully");
										t1.setText("");
										t2.setText("");
										t3.setText("");
										t4.setText("");
										t5.setText("");
										t6.setText("");
										t7.setText("");
										t11.setText("");
										t22.setText("");
										t33.setText("");
										t44.setText("");
										p9.setText("");
										limg.setIcon(new ImageIcon("images/photo.png"));
										limgn.setIcon(new ImageIcon("images/photo.png"));
									}
									con.close();
								}
								catch(Exception e)
								{
								}
							}
							else
							{
								JOptionPane.showMessageDialog(fr,"Please Enter Valid Email-ID");
								t7.requestFocus();
							}
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
				new searchaccount_modify(mainform);
				fr.dispose();
			}
		});

		limg = new JLabel(new ImageIcon("images/photo.png"));
		limg.setBounds(375,115,80,70);
		fr.add(limg);
		limg.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					FileDialog fd = new FileDialog(fr,"Select Photo",FileDialog.LOAD);
					fd.setVisible(true);	
					path1 = fd.getDirectory()+fd.getFile();
					if(path1.equals("nullnull"))
					{
						path1 = "photos/"+p9.getText()+"c.jpg";
						original = Toolkit.getDefaultToolkit().getImage("photos/"+p9.getText()+"c.jpg");
						scaled = original.getScaledInstance(80,70,Image.SCALE_DEFAULT);
						limg.setIcon(new ImageIcon(scaled));
					}
					else
					{
						original = Toolkit.getDefaultToolkit().getImage(path1);
						scaled = original.getScaledInstance(80,70,Image.SCALE_DEFAULT);
						limg.setIcon(new ImageIcon(scaled));
					}
				}
				catch(Exception e)
				{
				}	
			}
		});

		limgn = new JLabel(new ImageIcon("images/photo.png"));
		limgn.setBounds(670,300,80,70);
		fr.add(limgn);
		limgn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					FileDialog fd = new FileDialog(fr,"Select Photo",FileDialog.LOAD);
					fd.setVisible(true);	
					path2 = fd.getDirectory()+fd.getFile();
					if(path2.equals("nullnull"))
					{
						path2 = "photos/"+p9.getText()+"n.jpg";
						original = Toolkit.getDefaultToolkit().getImage("photos/"+p9.getText()+"n.jpg");
						scaled = original.getScaledInstance(80,70,Image.SCALE_DEFAULT);
						limgn.setIcon(new ImageIcon(scaled));
					}
					else
					{
						original = Toolkit.getDefaultToolkit().getImage(path2);
						scaled = original.getScaledInstance(80,70,Image.SCALE_DEFAULT);
						limgn.setIcon(new ImageIcon(scaled));
					}
				}
				catch(Exception e)
				{
				}	
			}
		});
								
		p9 = new JLabel();
		p9.setBounds(250,14,200,30);
		p9.setFont(new Font("verdana",Font.BOLD,18));
		p9.setForeground(Color.white);
		fr.add(p9);
		
		p1 = new JLabel("First Name");
		p2 = new JLabel("Middle Name");
		p3 = new JLabel("Last Name");
		p4 = new JLabel("Full Address");
		p5 = new JLabel("Mobile No. 1");
		p6 = new JLabel("Mobile No. 2");
		p7 = new JLabel("Email - ID");
		p1.setBounds(90,117,200,30);
		p2.setBounds(90,157,200,30);
		p3.setBounds(90,197,200,30);
		p4.setBounds(90,237,200,30);
		p5.setBounds(90,327,200,30);
		p6.setBounds(90,367,200,30);
		p7.setBounds(90,407,200,30);
		p1.setFont(new Font("verdana",Font.PLAIN,14));
		p2.setFont(new Font("verdana",Font.PLAIN,14));
		p3.setFont(new Font("verdana",Font.PLAIN,14));
		p4.setFont(new Font("verdana",Font.PLAIN,14));
		p5.setFont(new Font("verdana",Font.PLAIN,14));
		p6.setFont(new Font("verdana",Font.PLAIN,14));
		p7.setFont(new Font("verdana",Font.PLAIN,14));
		p1.setForeground(Color.cyan);
		p2.setForeground(Color.cyan);
		p3.setForeground(Color.cyan);
		p4.setForeground(Color.cyan);
		p5.setForeground(Color.cyan);
		p6.setForeground(Color.cyan);
		p7.setForeground(Color.cyan);
		fr.add(p1);
		fr.add(p2);
		fr.add(p3);
		fr.add(p4);
		fr.add(p5);
		fr.add(p6);
		fr.add(p7);
					
		Border border1 = BorderFactory.createLineBorder(Color.gray);
		
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		t4 = new JTextArea();
		t5 = new JTextField();
		t6 = new JTextField();
		t7 = new JTextField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t4.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t5.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t6.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t7.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t1.setBounds(200,120,150,25);
		t2.setBounds(200,160,150,25);
		t3.setBounds(200,200,255,25);
		t4.setBounds(200,240,255,75);
		t5.setBounds(200,330,255,25);
		t6.setBounds(200,370,255,25);
		t7.setBounds(200,410,255,25);
		t1.setFont(new Font("verdana",Font.PLAIN,16));
		t2.setFont(new Font("verdana",Font.PLAIN,16));
		t3.setFont(new Font("verdana",Font.PLAIN,16));
		t4.setFont(new Font("verdana",Font.PLAIN,12));
		t5.setFont(new Font("verdana",Font.PLAIN,16));
		t6.setFont(new Font("verdana",Font.PLAIN,16));
		t7.setFont(new Font("verdana",Font.PLAIN,16));
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		t3.setForeground(Color.green);
		t4.setForeground(Color.green);
		t5.setForeground(Color.green);
		t6.setForeground(Color.green);
		t7.setForeground(Color.green);
		t1.setOpaque(false);
		t2.setOpaque(false);
		t3.setOpaque(false);
		t4.setOpaque(false);
		t5.setOpaque(false);
		t6.setOpaque(false);
		t7.setOpaque(false);
		t1.setCaretColor(Color.green);
		t2.setCaretColor(Color.green);
		t3.setCaretColor(Color.green);
		t4.setCaretColor(Color.green);
		t5.setCaretColor(Color.green);
		t6.setCaretColor(Color.green);
		t7.setCaretColor(Color.green);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
		fr.add(t4);
		fr.add(t5);
		fr.add(t6);
		fr.add(t7);
		t5.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}
				if(t5.getText().length()>9)
				{
					ke.consume();
				}
			}	
		});
		
		t6.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}
				if(t6.getText().length()>9)
				{
					ke.consume();
				}
			}	
		});

		n1 = new JLabel("Nominee Name");
		n2 = new JLabel("Relation");
		n3 = new JLabel("Age");
		n4 = new JLabel("Adhar Number");
		n1.setBounds(520,117,200,30);
		n2.setBounds(520,157,200,30);
		n3.setBounds(520,197,200,30);
		n4.setBounds(520,237,200,30);
		n1.setFont(new Font("verdana",Font.PLAIN,14));
		n2.setFont(new Font("verdana",Font.PLAIN,14));
		n3.setFont(new Font("verdana",Font.PLAIN,14));
		n4.setFont(new Font("verdana",Font.PLAIN,14));
		n1.setForeground(Color.cyan);
		n2.setForeground(Color.cyan);
		n3.setForeground(Color.cyan);
		n4.setForeground(Color.cyan);
		fr.add(n1);
		fr.add(n2);
		fr.add(n3);
		fr.add(n4);
	
		t11 = new JTextField();
		t22 = new JTextField();
		t33 = new JTextField();
		t44 = new JTextField();
		t11.setBounds(670,120,210,25);
		t22.setBounds(670,160,210,25);
		t33.setBounds(670,200,210,25);
		t44.setBounds(670,240,210,25);		
		t11.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t22.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t33.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t44.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t11.setForeground(Color.green);
		t22.setForeground(Color.green);
		t33.setForeground(Color.green);
		t44.setForeground(Color.green);
		t11.setFont(new Font("verdana",Font.PLAIN,16));
		t22.setFont(new Font("verdana",Font.PLAIN,16));
		t33.setFont(new Font("verdana",Font.PLAIN,16));
		t44.setFont(new Font("verdana",Font.PLAIN,16));
		t11.setOpaque(false);
		t22.setOpaque(false);
		t33.setOpaque(false);
		t44.setOpaque(false);
		t11.setCaretColor(Color.green);
		t22.setCaretColor(Color.green);
		t33.setCaretColor(Color.green);
		t44.setCaretColor(Color.green);
		fr.add(t11);
		fr.add(t22);
		fr.add(t33);
		fr.add(t44);
		
		fr.setBounds((dim.width-1000)/2,(dim.height-500)/2,1000,500);
		fr.setUndecorated(true);
		fr.setVisible(true);

		loaddata();		
	}

	private void loaddata()
	{
		try
		{
			p9.setText(rs.getString("account_number"));
			t1.setText(rs.getString("CUSTOMER_FIRST_NAME"));
			t2.setText(rs.getString("CUSTOMER_MIDDLE_NAME"));
			t3.setText(rs.getString("CUSTOMER_LAST_NAME"));
			t4.setText(rs.getString("ADDRESS"));
			t5.setText(rs.getString("MOBILE1"));
			t6.setText(rs.getString("MOBILE2"));
			t7.setText(rs.getString("EMAIL"));
			t11.setText(rs.getString("NOMINEE_NAME"));
			t22.setText(rs.getString("NOMINEE_RELATION"));
			t33.setText(rs.getString("NOMINEE_AGE"));
			t44.setText(rs.getString("NOMINEE_ADHAR"));
			
			Blob bc = rs.getBlob("photo_cus");			
			byte byc[] = bc.getBytes(1,(int)bc.length());
			FileOutputStream fosc = new FileOutputStream("photos/"+p9.getText()+"c.jpg");
			fosc.write(byc);
			fosc.close();

			Blob bn = rs.getBlob("photo_nom");			
			byte byn[] = bn.getBytes(1,(int)bn.length());
			FileOutputStream fosn = new FileOutputStream("photos/"+p9.getText()+"n.jpg");
			fosn.write(byn);
			fosn.close();

			path1 = "photos/"+p9.getText()+"c.jpg";
			path2 = "photos/"+p9.getText()+"n.jpg";			
			
			original = Toolkit.getDefaultToolkit().getImage("photos/"+p9.getText()+"c.jpg");
			scaled = original.getScaledInstance(80,70,Image.SCALE_DEFAULT);
			limg.setIcon(new ImageIcon(scaled));

			original = Toolkit.getDefaultToolkit().getImage("photos/"+p9.getText()+"n.jpg");
			scaled = original.getScaledInstance(80,70,Image.SCALE_DEFAULT);
			limgn.setIcon(new ImageIcon(scaled));

			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}