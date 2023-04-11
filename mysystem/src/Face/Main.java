package Face;
import java.io.*;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import Mod.Data;
import Tool.*;


public class Main {
	
	JFrame MySystem = new JFrame("欢迎使用餐饮系统");
	
	final int WIDTH = 500;
	final int HEIGHT = 300;
	
	public static void main(String[] args) {
//		String one = "sb"+'\t'+"sb";
//		System.out.println(one);
		try {
			Data.init();
			new Main().init();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() throws IOException {
		//窗口相关属性
		
		//窗口在电脑正中间
		MySystem.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		//设置窗口内容
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/5.jfif")));
		
		//组装登录相关元素
		
		//建立垂直元素
		Box vBox = Box.createVerticalBox();
		
		//管理员系统
		JButton MaBtn = new JButton("管理员系统");
		
		//员工系统
		JButton EmBtn = new JButton("服务员系统");
		
		//外卖员系统
		JButton TaBtn = new JButton("外卖员系统");
		
		//顾客系统
		JButton CuBtn = new JButton("顾    客系统");
		
		//应聘系统
		JButton ApBtn = new JButton("员  工 应 聘");
		
		//将水平组装的添加到垂直
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(MaBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(EmBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(TaBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(CuBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(ApBtn);
		
		bgPanel.add(vBox);
		
		//按钮的事件
		MaBtn.addActionListener(new MainListener());
		EmBtn.addActionListener(new MainListener());
		TaBtn.addActionListener(new MainListener());
		CuBtn.addActionListener(new MainListener());
		ApBtn.addActionListener(new MainListener());
		
		MySystem.add(bgPanel);
		MySystem.setVisible(true);
		
	}
	
	
	
}




