package Mod;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Face.CustomerLoginListener;
import Tool.BackGroundPanel;

public class CulookListener implements ActionListener{

	final int WIDTH = 600;
	final int HEIGHT = 400;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame jf = new JFrame("订单表");
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/10.jfif")));
			jf.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		jf.setBounds(100,100,WIDTH,HEIGHT);
		JPanel contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0,0));
		jf.setContentPane(contentPane);
		
		JScrollPane scrollPane=new JScrollPane();
	    contentPane.add(scrollPane,BorderLayout.NORTH);
		
	    Box ListBox = Box.createHorizontalBox();
	    
		JList<String> list1=new JList<String>();
		JList<String> list2=new JList<String>();
		
	    scrollPane.setViewportView(ListBox);
	    
	    String[] listData1=new String[100];
	    String[] listData2=new String[100];
	    int len1=0,len2=0;
	    listData1[len1++]="菜名";
	    listData2[len2++]="价格";
	    
	    listData1[len1++]=" ";
	    listData2[len2++]=" ";
	    
	    String sql = "select * from orders where cuid = '"+CustomerLoginListener.getId()+"'";
//		System.out.println(sql);
		Statement sta = null;
		ResultSet re = null;
		try {
			
			sta = Data.conn.createStatement();
			re = sta.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		try {
			Statement orsta = null;
			ResultSet orre = null;
			while(re.next())
			{
				listData1[len1++]=re.getString("orid");
			    listData2[len2++]=" ";
				
				String orid = re.getString("orid");
				int flag = re.getInt("flag");
//				System.out.println("orid="+orid);
				String orsql = "select * from dish where orid = '"+orid+"'";
				orsta =  Data.conn.createStatement();
				orre = orsta.executeQuery(orsql);
				while(orre.next())
				{
					listData1[len1++]=orre.getString("me");
					listData2[len2++]=""+orre.getDouble("price");
					
				}
				if(flag==1)listData1[len1++]="总消费:"+re.getDouble("price")+",未结算";
				else listData1[len1++]="总消费:"+re.getDouble("price")+",已结算";
				listData2[len2++]=" ";
				if(re.getInt("op")==1)
				{
					listData1[len1++]="需要外送";
					listData2[len2++]=" ";
					listData1[len1++]=" ";
					listData2[len2++]=" ";
				}
				else
				{
					listData1[len1++]="不需要外送";
					listData2[len2++]=" ";
					listData1[len1++]=" ";
					listData2[len2++]=" ";
				}
//				orsta.close();
//				orre.close();
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		list1.setListData(listData1);
		list2.setListData(listData2);
		
		ListBox.add(list1, BorderLayout.NORTH);
		ListBox.add(Box.createHorizontalStrut(20));
		ListBox.add(list2, BorderLayout.NORTH);
		
		jf.setVisible(true);
	}

}
