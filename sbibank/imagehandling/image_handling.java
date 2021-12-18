import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.util.UUID;

class image_handling implements ActionListener
{
	JFrame fr;
	JLabel l1,l2,l3,l4,lid,limg;
	JLabel t1;	
	JButton b1,b2,b3,b4,b5,b6; 
	Image original,scaled;
	String path;
	boolean bn = false;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;	

	public image_handling()
	{
		fr = new JFrame("Image Demo");
		fr.setSize(1360,768);
		fr.setLayout(null);

		l1 = new JLabel("Storing Image in Database");
		l1.setFont(new Font("verdana",Font.BOLD,35));
		l1.setBounds(50,30,555,55);
		l1.setForeground(Color.blue);
		fr.add(l1);

		limg = new JLabel(new ImageIcon("user.png"));
		limg.setBounds(350,120,128,128);
		fr.add(limg);

		t1 = new JLabel();
		t1.setFont(new Font("verdana",Font.BOLD,20));
		t1.setBounds(370,290,128,30);
		t1.setForeground(Color.red);
		fr.add(t1);

		lid = new JLabel("<html><u>Generate ID</u></html>");
		lid.setFont(new Font("verdana",Font.BOLD,18));
		lid.setBounds(350,320,128,30);
		lid.setForeground(Color.blue);
		lid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fr.add(lid);
		lid.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)		
			{
				String id = UUID.randomUUID().toString();	
				id = id.substring(0,6);
				t1.setText(id);	
			}
		});

		b1 = new JButton("Browse Image");
		b2 = new JButton("Save Image");
		b3 = new JButton("Reset Image");
		b6 = new JButton("Show Navigator");
		b4 = new JButton(new ImageIcon("next.png"));
		b5 = new JButton(new ImageIcon("prev.png"));
		b4.setVisible(false);
		b5.setVisible(false);
		b1.setBounds(50,120,195,30);
		b2.setBounds(50,170,195,30);
		b3.setBounds(50,220,195,30);
		b6.setBounds(50,270,195,30);
		b4.setBounds(350,370,50,35);
		b5.setBounds(420,370,50,35);
		b1.setFont(new Font("verdana",Font.BOLD,17));
		b2.setFont(new Font("verdana",Font.BOLD,17));
		b3.setFont(new Font("verdana",Font.BOLD,17));
		b6.setFont(new Font("verdana",Font.BOLD,17));
		b4.setFont(new Font("arial",Font.BOLD,12));
		b5.setFont(new Font("arial",Font.BOLD,12));
		b1.setForeground(Color.red);
		b2.setForeground(Color.red);
		b3.setForeground(Color.red);
		b6.setForeground(Color.red);
		b4.setForeground(Color.red);
		b5.setForeground(Color.red);
		fr.add(b1);
		fr.add(b2);
		fr.add(b3);
		fr.add(b6);
		fr.add(b4);
		fr.add(b5);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b6.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);

		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createconnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","career");
		}
		catch(Exception e)
		{
		}
	}

	public void navigator()
	{
		try
		{
			createconnection();
			ps = con.prepareStatement("select * from image_table",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
		}
		catch(Exception e)
		{
		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			try
			{
				FileDialog fd = new FileDialog(fr,"Select Image",FileDialog.LOAD);
				fd.setVisible(true);
				path = fd.getDirectory() + fd.getFile(); 
				if(path.equals("nullnull"))
				{
					JOptionPane.showMessageDialog(fr,"canceled by user");
					bn = false;
				}	
				else
				{
					bn = true;
					original = Toolkit.getDefaultToolkit().getImage(path);
					scaled = original.getScaledInstance(128,128,Image.SCALE_DEFAULT);
					limg.setIcon(new ImageIcon(scaled));
				}
			}
			catch(Exception e)
			{
			}
		}

		if(ae.getSource()==b2)
		{
			try
			{
				if(bn==false)
				{
					JOptionPane.showMessageDialog(fr,"Please Select An Image ");
				}
				else if(t1.getText().length()==0)
				{
					JOptionPane.showMessageDialog(fr,"Please Generate An UID ");
				}
				else
				{
					//reading image from HDD
					FileInputStream fis = new FileInputStream(path);
					createconnection();
					ps = con.prepareStatement("insert into image_table values(?,?)");
					ps.setString(1,t1.getText());
					ps.setBinaryStream(2,fis,fis.available());
					int z = ps.executeUpdate();	
					if(z>0)
					{
						JOptionPane.showMessageDialog(fr,"Image Uploaded Successfully.....");
					}
					con.close();
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(fr,"UID already exists....");
			}
		}
		if(ae.getSource()==b3)
		{
			t1.setText("");
			limg.setIcon(new ImageIcon("user.png"));
			bn = false;
		}

		if(ae.getSource()==b4)
		{
			try
			{
				rs.next();
				t1.setText(rs.getString("id"));
				Blob bb = rs.getBlob("photo"); 
				byte barr[] = bb.getBytes(1,(int)bb.length()); 
              			FileOutputStream fout = new FileOutputStream("photo/"+t1.getText()+".jpg");  
				fout.write(barr);  
				fout.close();  
				original = Toolkit.getDefaultToolkit().getImage("photo/"+t1.getText()+".jpg");
				scaled = original.getScaledInstance(128,128,Image.SCALE_DEFAULT);
				limg.setIcon(new ImageIcon(scaled));
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(fr,"you are at last record");
			}
		}

		if(ae.getSource()==b5)
		{
			try
			{
				rs.previous();
				t1.setText(rs.getString("id"));
				Blob bb = rs.getBlob("photo"); 
				byte barr[] = bb.getBytes(1,(int)bb.length()); 
              			FileOutputStream fout = new FileOutputStream("photo/"+t1.getText()+".jpg");  
				fout.write(barr);  
				fout.close();  
				original = Toolkit.getDefaultToolkit().getImage("photo/"+t1.getText()+".jpg");
				scaled = original.getScaledInstance(128,128,Image.SCALE_DEFAULT);
				limg.setIcon(new ImageIcon(scaled));
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(fr,"you are at first record");
			}
		}

		if(ae.getSource()==b6)
		{
			if(ae.getActionCommand().equals("Show Navigator"))
			{
				try
				{
					navigator();
					b4.setVisible(true);
					b5.setVisible(true);
					b6.setLabel("Hide Navigator");
				}
				catch(Exception e)
				{}
			}
			else if(ae.getActionCommand().equals("Hide Navigator"))
			{
				try
				{
					con.close();
					b4.setVisible(false);
					b5.setVisible(false);
					b6.setLabel("Show Navigator");
				}
				catch(Exception e)
				{}
			}
		}
	}

	public static void main(String args[])
	{
		new image_handling();
	}
}