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
import Face.ManagerLoginListener;
import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class MaEmlookListener implements ActionListener{

	final int WIDTH = 800;
	final int HEIGHT = 450;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame jf = new JFrame("员工表");
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		Container contentPane=jf.getContentPane();
		
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/24.jfif")));
			contentPane.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		String[] top={"服务员ID","服务员姓名","工资","性别"};
	    
		//建立二维数组，存储应聘人员信息
		try {
			Data.emgetnum();
		} catch (SQLException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
        Object[][] td=new Object[Data.getNumem()][4];
        int len=0;
	    
	    String sql = "select * from employee where maid = '"+ManagerLoginListener.getId()+"'";
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
				td[len][0]=re.getString("emid");
				td[len][1]=re.getString("name");
				td[len][2]=""+re.getDouble("wages");
				td[len++][3]=re.getString("sex");
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		JTable table=new JTable(td,top);
        contentPane.add(new JScrollPane(table));
		
		Box opBox = Box.createHorizontalBox();
		opBox.add(Box.createHorizontalStrut(250));
		JButton addBtn = new JButton("增加服务员");
		opBox.add(addBtn);
		opBox.add(Box.createHorizontalStrut(100));//水平间隔
		JButton delBtn = new JButton("解雇服务员");
		opBox.add(delBtn);
		
		//按钮监听
		addBtn.addActionListener(new MaaddEmListener());
		
		delBtn.addActionListener(new MadelEmListener());
		
		contentPane.add(opBox,BorderLayout.SOUTH);
		
		jf.setVisible(true);
	}

}
