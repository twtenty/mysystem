package Mod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import Face.TakeoutLogin;
import Tool.*;


public class TaFunction {
	
	JFrame TaFunction = new JFrame("亲爱的"+TakeoutLogin.getTaa().getName()+"用户,欢迎登陆！") ;
	
	final int WIDTH = 500;
	final int HEIGHT = 300;
	
	public static void main(String[] args) {
		try {
			new TaFunction().init();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public void init() throws IOException {
		//窗口相关属性
		
		//窗口在电脑正中间
		TaFunction.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		//设置窗口内容
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/30.jfif")));
		
		//组装登录相关元素
		
		//建立垂直元素
		Box vBox = Box.createVerticalBox();
		
		//消费
		JButton LowagesBtn = new JButton("查看工资");
		
		//员工系统
		JButton LoCuBtn = new JButton("查看顾客");
		
		//外卖员系统
		JButton MoBtn = new JButton("修改信息");
		
		//顾客系统
		JButton DeBtn = new JButton("辞        职");
		
		//将水平组装的添加到垂直
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(LowagesBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(LoCuBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(MoBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(DeBtn);
		
		bgPanel.add(vBox);
		
		//按钮的事件
		LowagesBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				JOptionPane.showMessageDialog(new JFrame(),"您的工资为:"+TakeoutLogin.getTaa().getWages());
			}
			
		});
		
		LoCuBtn.addActionListener(new TaLookListener());
		MoBtn.addActionListener(new TaModListener());
		DeBtn.addActionListener(new TadeListener());
//		TaFunction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TaFunction.add(bgPanel);
		TaFunction.setVisible(true);
	}
	
	
		
}




