package Mod;

import java.awt.Container;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class MaDeListener implements ActionListener{

	final int WIDTH = 800;
	final int HEIGHT = 450;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame jf = new JFrame("离职表");
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/21.jfif")));
			jf.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		Container contentPane=jf.getContentPane();
        
        String[] top={"离职人员ID","离职人员姓名","时间","原因"};
        
        try {
			Data.degetnum();
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        
        //建立二维数组，存储应聘人员信息
        Object[][] td=new Object[Data.getNumde()][4];
        int len=0;  
	    
	    String sql = "select * from depart ";
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
			while(re.next())
			{
//				System.out.println(1);
//				System.out.println(re.getString("emid"));
				java.util.Date date = null;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				try {
					java.util.Date parse_date = sdf.parse(re.getString("date"));
					date=parse_date;
				} catch (ParseException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				td[len][0]=re.getString("deid");
				td[len][1]=re.getString("name");
				td[len][2]=""+date;
				td[len++][3]=re.getString("reason");

			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		JTable table=new JTable(td,top);
        contentPane.add(new JScrollPane(table));
		
		jf.setVisible(true);
	}

}
