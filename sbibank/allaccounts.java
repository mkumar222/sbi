import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

class allaccounts
{
	JFrame fr,mainform;
	JLabel lclose;
	JTable tab;
	JScrollPane jsp;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public allaccounts(JFrame mf)
	{
		mainform = mf;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fr = new JFrame();
		Image img = Toolkit.getDefaultToolkit().getImage("images/sbi.jpg");
		fr.setIconImage(img);
		fr.setContentPane(new JLabel(new ImageIcon("images/allaccounts.png")));			
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

		fr.setBounds((dim.width-1000)/2,(dim.height-500)/2,1000,500);
		fr.setUndecorated(true);
		fr.setVisible(true);
		loaddata();
	}

	private void loaddata()
	{
		try
		{
			con = dao.createconnection();
			ps = con.prepareStatement("select * from sbi_customer");
			rs = ps.executeQuery();
			
			Vector<String> heading = new Vector<String>();
			heading.add(new String("Account Number"));
			heading.add(new String("Full Name"));
			heading.add(new String("Adhar Number"));
			heading.add(new String("PAN Number"));
			heading.add(new String("Mobile"));
			heading.add(new String("Address"));
			heading.add(new String("Balance"));
			heading.add(new String("Opening Date"));
		
			Vector<Vector> row = new Vector<Vector>();
			while(rs.next()==true)
			{
				Vector<Object>data = new Vector<Object>();
				data.add(rs.getObject("account_number"));
				String ln=" ";
				if(rs.getString("customer_last_name")==null)
				{
					ln = " ";
				}
				else
				{
					ln = rs.getString("customer_last_name");
				}
				data.add(rs.getObject("customer_first_name")+" "+ln);
				data.add(rs.getObject("adhar_number"));
				data.add(rs.getObject("pan_number"));
				data.add(rs.getObject("mobile1"));
				data.add(rs.getObject("address"));
				data.add(rs.getObject("current_balance"));
				data.add(rs.getObject("opening_date"));
				row.add(data);
			}

			tab = new JTable(row,heading);
			jsp = new JScrollPane(tab);
			jsp.setBounds(50,100,100*8+90,350);
			fr.add(jsp);

			con.close(); 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}