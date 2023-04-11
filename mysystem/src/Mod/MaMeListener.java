package Mod;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class MaMeListener implements ActionListener{

	final int WIDTH = 800;
	final int HEIGHT = 450;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame jf = new JFrame("菜品修改系统");
		jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/26.jfif")));
			jf.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}

		Container contentPane=jf.getContentPane();
        
        String[] top={"菜名","价格"};
        
        try {
			Data.megetnum();
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        
        //建立二维数组，存储应聘人员信息
        Object[][] td=new Object[Data.getNumme()][2];
        int len=0;  
	   
	    
	    String sql = "select * from menus ";
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
				td[len][0]=re.getString("menu");
				td[len++][1]=re.getString("price");
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		JTable table=new JTable(td,top);
        contentPane.add(new JScrollPane(table));
		
		Box opBox = Box.createHorizontalBox();
		opBox.add(Box.createHorizontalStrut(250));
		JButton addBtn = new JButton("增加菜品");
		opBox.add(addBtn);
		opBox.add(Box.createHorizontalStrut(100));//水平间隔
		JButton delBtn = new JButton("删除菜品");
		opBox.add(delBtn);
		
		//设置增加菜品按钮监听
		addBtn.addActionListener(new MaaddMeEmListener());
		
		delBtn.addActionListener(new MaDelMeEmListener());
		
		
		contentPane.add(opBox,BorderLayout.SOUTH);
		jf.setVisible(true);
	}

}
