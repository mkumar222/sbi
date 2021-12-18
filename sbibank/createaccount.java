import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.util.Random;
import java.io.*;
import java.util.regex.*;
import java.sql.*;

class createaccount implements FocusListener
{
	String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	JFrame fr,mainform;
	JLabel lclose,n1,n2,n3,n4,at2,at3,at5,lsave,lreset,ltime;
	JLabel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,acn,limg,limg2;
	JLabel a1,a2; 
	JTextField t1,t2,t3,t4,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16;
	JTextArea t5;
	JComboBox c1,c2,c3,at1,at4;
	JRadioButton r1,r2,r3;
	ButtonGroup bg;
	Border border1,border2;
	String path,path2;
	Image original,scaled;	
	boolean bn1,bn2;	
	
	public createaccount(JFrame mf)
	{
		mainform = mf;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		Image img = Toolkit.getDefaultToolkit().getImage("images/sbi.jpg");
		fr.setIconImage(img);
		fr.setContentPane(new JLabel(new ImageIcon("images/newform.png")));			
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

		limg = new JLabel(new ImageIcon("images/photo.png"));
		limg.setBounds(845,115,80,70);
		limg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(limg);
		limg.addMouseListener(new MouseAdapter()
		{	
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					FileDialog fd = new FileDialog(fr,"Select Photo",FileDialog.LOAD);
					fd.setVisible(true);
					path = fd.getDirectory()+fd.getFile();
					if(path.equals("nullnull"))
					{
						limg.setIcon(new ImageIcon("images/default.png"));
						bn1= false;
					}
					else
					{
						bn1 = true;
						original = Toolkit.getDefaultToolkit().getImage(path);		
						scaled = original.getScaledInstance(80,70,Image.SCALE_DEFAULT);
						limg.setIcon(new ImageIcon(scaled));
					}
				}	
				catch(Exception e)
				{
				}
			}
		});

		limg2 = new JLabel(new ImageIcon("images/photo2.png"));
		limg2.setBounds(867,217,60,50);
		limg2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(limg2);
		limg2.addMouseListener(new MouseAdapter()
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
						limg2.setIcon(new ImageIcon("images/default.png"));
						bn2 = false;
					}
					else
					{
						bn2 = true;
						original = Toolkit.getDefaultToolkit().getImage(path2);		
						scaled = original.getScaledInstance(60,50,Image.SCALE_DEFAULT);
						limg2.setIcon(new ImageIcon(scaled));
					}
				}	
				catch(Exception e)
				{
				}
			}
		});

		Random rand = new Random();
		int id = rand.nextInt(900000000) + 100000000;
		acn = new JLabel("SBI-"+id);
		acn.setBounds(220,13,250,30);
		acn.setFont(new Font("verdana",Font.PLAIN,20));
		acn.setForeground(Color.white);
		fr.add(acn);
		
		p1 = new JLabel("First Name *");
		p2 = new JLabel("Middel Name");
		p3 = new JLabel("Last Name");
		p4 = new JLabel("Date of Birth");
		p5 = new JLabel("Gender *");
		p6 = new JLabel("Father's Name * ");
		p7 = new JLabel("Address,City,Pin,Country *");
		p8 = new JLabel("Mobile");
		p9 = new JLabel("Alternate Mobile");
		p10 = new JLabel("Email-ID");
		p1.setBounds(70,120,100,30);
		p2.setBounds(210,120,100,30);
		p3.setBounds(350,120,100,30);
		p4.setBounds(70,185,100,30);
		p5.setBounds(300,185,100,30);
		p6.setBounds(70,255,150,30);
		p7.setBounds(265,255,200,30);
		p8.setBounds(70,320,200,30);
		p9.setBounds(70,385,200,30);
		p10.setBounds(265,385,200,30);
		p1.setFont(new Font("verdana",Font.PLAIN,14));
		p2.setFont(new Font("verdana",Font.PLAIN,14));
		p3.setFont(new Font("verdana",Font.PLAIN,14));
		p4.setFont(new Font("verdana",Font.PLAIN,14));
		p5.setFont(new Font("verdana",Font.PLAIN,14));
		p6.setFont(new Font("verdana",Font.PLAIN,14));
		p7.setFont(new Font("verdana",Font.PLAIN,14));
		p8.setFont(new Font("verdana",Font.PLAIN,14));
		p9.setFont(new Font("verdana",Font.PLAIN,14));
		p10.setFont(new Font("verdana",Font.PLAIN,14));
		p1.setForeground(Color.cyan);
		p2.setForeground(Color.cyan);
		p3.setForeground(Color.cyan);
		p4.setForeground(Color.cyan);
		p5.setForeground(Color.cyan);
		p6.setForeground(Color.cyan);
		p7.setForeground(Color.cyan);
		p8.setForeground(Color.cyan);
		p9.setForeground(Color.cyan);
		p10.setForeground(Color.cyan);
		fr.add(p1);
		fr.add(p2);
		fr.add(p3);
		fr.add(p4);
		fr.add(p5);
		fr.add(p6);
		fr.add(p7);
		fr.add(p8);
		fr.add(p9);
		fr.add(p10);

					
		border1 = BorderFactory.createLineBorder(Color.gray);
		
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		t4 = new JTextField();
		t6 = new JTextField();
		t7 = new JTextField();
		t8 = new JTextField();
		t1.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t2.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t3.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t4.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t6.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t7.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t8.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t1.setBounds(70,150,130,25);
		t2.setBounds(210,150,130,25);
		t3.setBounds(350,150,138,25);
		t4.setBounds(70,285,170,25);
		t6.setBounds(70,350,170,25);
		t7.setBounds(70,415,170,25);
		t8.setBounds(265,415,220,25);
		t1.setFont(new Font("verdana",Font.PLAIN,16));
		t2.setFont(new Font("verdana",Font.PLAIN,16));
		t3.setFont(new Font("verdana",Font.PLAIN,16));
		t4.setFont(new Font("verdana",Font.PLAIN,16));
		t6.setFont(new Font("verdana",Font.PLAIN,16));
		t7.setFont(new Font("verdana",Font.PLAIN,16));
		t8.setFont(new Font("verdana",Font.PLAIN,16));
		t1.setForeground(Color.green);
		t2.setForeground(Color.green);
		t3.setForeground(Color.green);
		t4.setForeground(Color.green);
		t6.setForeground(Color.green);
		t7.setForeground(Color.green);
		t8.setForeground(Color.green);
		t1.setCaretColor(Color.green);
		t2.setCaretColor(Color.green);
		t3.setCaretColor(Color.green);
		t4.setCaretColor(Color.green);
		t6.setCaretColor(Color.green);
		t7.setCaretColor(Color.green);
		t8.setCaretColor(Color.green);
		t1.setOpaque(false);
		t2.setOpaque(false);
		t3.setOpaque(false);
		t4.setOpaque(false);
		t6.setOpaque(false);
		t7.setOpaque(false);
		t8.setOpaque(false);
		fr.add(t1);
		fr.add(t2);
		fr.add(t3);
		fr.add(t4);
		fr.add(t6);
		fr.add(t7);
		fr.add(t8);

		t1.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(!(ch>=65 && ch<=90 || ch>=97 && ch<=122))
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
				if(!(ch>=65 && ch<=90 || ch>=97 && ch<=122))
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
				if(!(ch>=65 && ch<=90 || ch>=97 && ch<=122))
				{
					ke.consume();
				}
			}
		});

		t4.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(!(ch>=65 && ch<=90 || ch>=97 && ch<=122 || ch==32 || ch=='.'))
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

		t7.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}

				if(t7.getText().length()>9)
				{
					ke.consume();
				}
			}
		});

		t5 = new JTextArea();
		t5.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t5.setForeground(Color.green);
		t5.setCaretColor(Color.green);
		t5.setOpaque(false);
		t5.setBounds(265,285,220,90);
		fr.add(t5);
		
		c1 = new JComboBox();
		for(int i=1 ; i<=31 ; i++)
		{
			c1.addItem(""+i);			
		}
		c1.setFont(new Font("verdana",Font.PLAIN,15));
		c1.setBounds(70,215,50,25);	
		c1.setForeground(Color.green);
		c1.setBackground(Color.black);
		fr.add(c1);

		c2 = new JComboBox();
		for(int i=0 ; i<12 ; i++)
		{
			c2.addItem(month[i]);			
		}
		c2.setFont(new Font("verdana",Font.PLAIN,15));
		c2.setBounds(130,215,62,25);	
		c2.setForeground(Color.green);
		c2.setBackground(Color.black);
		fr.add(c2);

		c3 = new JComboBox();
		for(int i=1900 ; i<2019 ; i++)
		{
			c3.addItem(""+i);			
		}
		c3.setFont(new Font("verdana",Font.PLAIN,15));
		c3.setBounds(200,215,70,25);	
		c3.setForeground(Color.green);
		c3.setBackground(Color.black);
		fr.add(c3);

		bg = new ButtonGroup();
		r1 = new JRadioButton("Male");
		r2 = new JRadioButton("Female");
		r3 = new JRadioButton("Other");
		r1.setFont(new Font("verdana",Font.PLAIN,14));
		r2.setFont(new Font("verdana",Font.PLAIN,14));
		r3.setFont(new Font("verdana",Font.PLAIN,14));
		r1.setBounds(290,215,55,25);	
		r2.setBounds(347,215,75,25);	
		r3.setBounds(425,215,75,25);	
		r1.setForeground(Color.green);
		r2.setForeground(Color.green);
		r3.setForeground(Color.green);
		r1.setOpaque(false);
		r2.setOpaque(false);
		r3.setOpaque(false);
		bg.add(r1);
		bg.add(r2);
		bg.add(r3);
		fr.add(r1);
		fr.add(r2);
		fr.add(r3);
		
		a1 = new JLabel("Adhar Number *");
		a2 = new JLabel("P A N Number *");
		a1.setBounds(530,120,170,30);
		a2.setBounds(530,155,170,30);
		a1.setFont(new Font("verdana",Font.PLAIN,14));
		a2.setFont(new Font("verdana",Font.PLAIN,14));
		a1.setForeground(Color.cyan);
		a2.setForeground(Color.cyan);
		fr.add(a1);
		fr.add(a2);

		t9 = new JTextField();
		t10 = new JTextField();
		t9.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t10.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t9.setBounds(660,120,160,25);
		t10.setBounds(660,155,160,25);
		t9.setFont(new Font("verdana",Font.PLAIN,16));
		t10.setFont(new Font("verdana",Font.PLAIN,16));
		t9.setForeground(Color.green);
		t10.setForeground(Color.green);
		t9.setCaretColor(Color.green);
		t10.setCaretColor(Color.green);
		t9.setOpaque(false);
		t10.setOpaque(false);
		fr.add(t9);
		fr.add(t10);

		t9.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}

				if(t9.getText().length()>11)
				{
					ke.consume();
				}
			}
		});

		
		t10.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();

				if(ch>=97 && ch<=122)
				{
					if(t10.getText().length()<10)
					{
						ch = Character.toUpperCase(ch);
						ke.consume();
						t10.setText(t10.getText()+ch);
					}
				}

				if(!(ch>=65 && ch<=90 || ch>='0' && ch<='9'))
				{
					ke.consume();
				}

				if(t10.getText().length()>9)
				{
					ke.consume();
				}
			}
		});

		n1 = new JLabel("Name *");
		n2 = new JLabel("Age *");
		n3 = new JLabel("Relation *");
		n4 = new JLabel("Adhar *");
		n1.setFont(new Font("verdana",Font.PLAIN,14));
		n2.setFont(new Font("verdana",Font.PLAIN,14));
		n3.setFont(new Font("verdana",Font.PLAIN,14));
		n4.setFont(new Font("verdana",Font.PLAIN,14));
		n1.setForeground(Color.cyan);
		n2.setForeground(Color.cyan);
		n3.setForeground(Color.cyan);
		n4.setForeground(Color.cyan);
		n1.setBounds(530,230,100,30);
		n2.setBounds(730,230,50,30);
		n3.setBounds(530,277,100,30);
		n4.setBounds(730,277,100,30);
		fr.add(n1);
		fr.add(n2);
		fr.add(n3);
		fr.add(n4);

		t11 = new JTextField();
		t12 = new JTextField();
		t13 = new JTextField();
		t14 = new JTextField();
		t11.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t12.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t13.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t14.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t11.setFont(new Font("verdana",Font.PLAIN,16));
		t12.setFont(new Font("verdana",Font.PLAIN,16));
		t13.setFont(new Font("verdana",Font.PLAIN,16));
		t14.setFont(new Font("verdana",Font.PLAIN,16));
		t11.setCaretColor(Color.green);
		t12.setCaretColor(Color.green);
		t13.setCaretColor(Color.green);
		t14.setCaretColor(Color.green);
		t11.setOpaque(false);
		t12.setOpaque(false);
		t13.setOpaque(false);
		t14.setOpaque(false);
		t11.setBounds(600,232,120,25);
		t12.setBounds(795,232,50,25);
		t13.setBounds(600,279,120,25);
		t14.setBounds(795,279,135,25);
		t11.setForeground(Color.green);
		t12.setForeground(Color.green);
		t13.setForeground(Color.green);
		t14.setForeground(Color.green);
		fr.add(t11);
		fr.add(t12);
		fr.add(t13);
		fr.add(t14);
		t13.addFocusListener(this);
		t11.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(!(ch>=65 && ch<=90 || ch>=97 && ch<=122))
				{
					ke.consume();
				}
			}
		});

		t12.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}

				if(t12.getText().length()>2)
				{
					ke.consume();
				}
			}
		});

		t13.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(!(ch>=65 && ch<=90 || ch>=97 && ch<=122))
				{
					ke.consume();
				}
			}
		});

		t14.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}

				if(t14.getText().length()>11)
				{
					ke.consume();
				}
			}
		});

		at1 = new JComboBox();
		at1.addItem("Personal A/C");
		at1.addItem("Current A/C");
		at1.addItem("Fixed Deposit A/C");
		at2 = new JLabel("Opening Balance");
		at3 = new JLabel("Date");
		at4 = new JComboBox();
		at4.addItem("Yes");
		at4.addItem("No");
		at5 = new JLabel("Cheque Book");
		at1.setFont(new Font("verdana",Font.PLAIN,12));
		at2.setFont(new Font("verdana",Font.PLAIN,14));
		at3.setFont(new Font("verdana",Font.PLAIN,14));
		at4.setFont(new Font("verdana",Font.PLAIN,14));
		at5.setFont(new Font("verdana",Font.PLAIN,14));
		at1.setForeground(Color.green);
		at2.setForeground(Color.cyan);
		at3.setForeground(Color.cyan);
		at4.setForeground(Color.green);
		at5.setForeground(Color.cyan);
		at1.setBounds(530,363,150,25);
		at2.setBounds(700,360,130,30);
		at3.setBounds(530,407,100,30);
		at4.setBounds(840,407,88,25);
		at5.setBounds(700,407,110,25);
		at1.setBackground(Color.black);
		at4.setBackground(Color.black);
		fr.add(at1);
		fr.add(at2);
		fr.add(at3);
		fr.add(at4);
		fr.add(at5);

		t15 = new JTextField("0");
		t16 = new JTextField();
		t16.setEditable(false);
		t15.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t16.setBorder(BorderFactory.createCompoundBorder(border1,BorderFactory.createEmptyBorder(1,5,1,1)));	
		t15.setFont(new Font("verdana",Font.PLAIN,15));
		t16.setFont(new Font("verdana",Font.PLAIN,14));
		t15.setCaretColor(Color.green);
		t16.setCaretColor(Color.green);
		t15.setOpaque(false);
		t16.setOpaque(false);
		t15.setBounds(840,365,90,25);
		t16.setBounds(575,410,105,25);
		t15.setForeground(Color.green);
		t16.setForeground(Color.green);
		fr.add(t15);
		fr.add(t16);
		t15.addFocusListener(this);
		t15.addKeyListener(new KeyAdapter()
		{
			public void keyTyped(KeyEvent ke)
			{
				char ch = ke.getKeyChar();
				if(ch<'0' || ch>'9')
				{
					ke.consume();
				}
			}
		});

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMM-yyyy");
		java.util.Date dt = new java.util.Date();
		t16.setText(sdf.format(dt));

		lsave = new JLabel("<html><u>Save</u></html>");
		lreset = new JLabel("<html><u>Reset</u></html>");
		lsave.setFont(new Font("verdana",Font.PLAIN,20));
		lreset.setFont(new Font("verdana",Font.PLAIN,20));
		lsave.setForeground(Color.white);
		lreset.setForeground(Color.white);
		lsave.setBounds(700,12,65,32);
		lreset.setBounds(820,12,70,32);
		lsave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lreset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lsave);
		fr.add(lreset);
		lreset.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				t1.setText("");		
				t2.setText("");		
				t3.setText("");		
				t4.setText("");		
				t5.setText("");		
				t6.setText("");		
				t7.setText("");		
				t8.setText("");		
				t9.setText("");		
				t10.setText("");		
				t11.setText("");		
				t12.setText("");		
				t13.setText("");		
				t14.setText("");		
				t15.setText("0");		
				t1.requestFocus();						
				c1.setSelectedIndex(0);
				c2.setSelectedIndex(0);
				c3.setSelectedIndex(0);
				at1.setSelectedIndex(0);
				at4.setSelectedIndex(0);
				bg.clearSelection();
				limg.setIcon(new ImageIcon("images/photo.png"));
			}
		});

		lsave.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if(t1.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Customer's First Name");
					t1.requestFocus();
				}
				else if(r1.isSelected()==false && r2.isSelected()==false && r3.isSelected()==false)
				{
					JOptionPane.showMessageDialog(fr,"Please Select Gender");
				}
				else if(t4.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Father's Name");
					t4.requestFocus();
				}
				else if(t5.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Customer's Full Address");
					t5.requestFocus();
				}
				else if(t9.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Adhar Number");
					t9.requestFocus();
				}
				else if(t10.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter PAN Number");
					t10.requestFocus();
				}
				else if(t11.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Nominee's Name");
					t11.requestFocus();
				}
				else if(t12.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Nominee's Age");
					t12.requestFocus();
				}
				else if(t13.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Nominee's Relation");
					t13.requestFocus();
				}
				else if(t14.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Nominee's Adhar");
					t14.requestFocus();
				}
				else if(t6.getText().length()!=0 && t6.getText().length()<10)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Valid Customer's Mobile Number");
					t6.requestFocus();
				}
				else if(t7.getText().length()!=0 && t7.getText().length()<10)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Valid Alternate Mobile Number");
					t7.requestFocus();
				}
				else if(t9.getText().length()<12)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Valid Customer's Adhar Number");
					t9.requestFocus();
				}
				else if(t10.getText().length()<10)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Valid Customer's PAN Number");
					t10.requestFocus();
				}
				else if(t14.getText().length()!=0 && t14.getText().length()<12)
				{
					JOptionPane.showMessageDialog(fr,"Please Enter Valid Nominee's Adhar Number");
					t14.requestFocus();
				}
				else if(bn1==false)
				{
					JOptionPane.showMessageDialog(fr,"Please Browse Customer's Photo");
				}	
				else if(bn2==false)
				{
					JOptionPane.showMessageDialog(fr,"Please Browse Nominee's Photo");
				}	
				else
				{
					boolean bool = true;
					try
					{
						if(t8.getText().length()!=0)
						{
							String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
							String email = t8.getText();

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
						Connection con = dao.createconnection();
						PreparedStatement ps = con.prepareStatement("insert into sbi_customer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						ps.setString(1,acn.getText());
						ps.setString(2,t1.getText());
						ps.setString(3,t2.getText());
						ps.setString(4,t3.getText());
											
						String strdate = c1.getSelectedItem().toString()+"/"+c2.getSelectedItem().toString()+"/"+c3.getSelectedItem().toString();
      						java.util.Date javadate = new java.text.SimpleDateFormat("dd/MMM/yyyy").parse(strdate);  
   						java.sql.Date sqldate = new java.sql.Date(javadate.getTime()); 
						ps.setDate(5,sqldate);

						String gen="";
						if(r1.isSelected()==true)
						{
							gen = "Male";
						}
						else if(r2.isSelected()==true)
						{
							gen = "Female";
						}
						else if(r3.isSelected()==true)
						{
							gen = "Other";
						}
						ps.setString(6,gen);

						ps.setString(7,t4.getText());
						ps.setString(8,t5.getText());
						ps.setString(9,t6.getText());
						ps.setString(10,t7.getText());
						ps.setString(11,t8.getText());
						ps.setString(12,t9.getText());
						ps.setString(13,t10.getText());
						ps.setString(14,t11.getText());
						ps.setString(15,t12.getText());
						ps.setString(16,t13.getText());
						ps.setString(17,t14.getText());
						ps.setString(18,at1.getSelectedItem().toString());
						ps.setString(19,t15.getText());
						ps.setString(20,t16.getText());
						ps.setString(21,at4.getSelectedItem().toString());
						FileInputStream fis1 = new FileInputStream(path);
						FileInputStream fis2 = new FileInputStream(path2);
						ps.setBinaryStream(22,fis1,fis1.available());
						ps.setBinaryStream(23,fis2,fis2.available());
						ps.setString(24,t15.getText());
						int r = ps.executeUpdate();
						if(r>0)
						{
							JOptionPane.showMessageDialog(fr,"Account Created Successfully,\nPlease Note Down A/C No. " + acn.getText());
						}
						con.close();
						}
						else
						{
							JOptionPane.showMessageDialog(fr,"Invalid Email-ID");
							t8.requestFocus();
						}
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
			}
		});
		
		fr.setBounds((dim.width-1000)/2,(dim.height-500)/2,1000,500);
		fr.setUndecorated(true);
		fr.setVisible(true);

		t1.requestFocus();
	}

	
	public void focusGained(FocusEvent fe)
	{
		if(t15.getText().equals("0"))
		{
			t15.setText("");
		}
	} 

	public void focusLost(FocusEvent fe)
	{
		if(t15.getText().length()==0)
		{
			t15.setText("0");
		}
		if(t13.getText().equalsIgnoreCase("father"))
		{
			t11.setText(t4.getText());
		}
	} 
}