package Mod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;

import Face.CustomerLoginListener;
import System.Orders;
import Tool.*;


public class CuFunction {
	
	JFrame CuFunction = new JFrame("亲爱的"+CustomerLoginListener.getCuu().getName()+"用户,欢迎登陆！") ;
	private static Orders cuor = new Orders();
	private static Orders cuor1 = new Orders();
	final int WIDTH = 500;
	final int HEIGHT = 300;
	
	
	
	public static Orders getCuor() {
		return cuor;
	}

	public static void setCuor(Orders cuuor) {
		cuor = cuuor;
	}

	public static Orders getCuor1() {
		return cuor1;
	}

	public static void setCuor1(Orders cuuor1) {
		cuor1 = cuuor1;
	}

	public static void main(String[] args) {
		try {
			new CuFunction().init();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	public void init() throws IOException {
		//窗口相关属性
		
		//窗口在电脑正中间
		CuFunction.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		//设置窗口内容
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/9.jfif")));
		
		//组装登录相关元素
		
		//建立垂直元素
		Box vBox = Box.createVerticalBox();
		
		//消费
		JButton CoBtn = new JButton("消        费");
		
		//员工系统
		JButton LoBtn = new JButton("查看订单");
		
		//外卖员系统
		JButton MoBtn = new JButton("修改信息");
		
		//顾客系统
		JButton DeBtn = new JButton("注销账号");
		
		//将水平组装的添加到垂直
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(CoBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(LoBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(MoBtn);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(DeBtn);
		
		bgPanel.add(vBox);
		
		//按钮的事件
		CoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
				
				//首先创建订单
				cuor.setCuid(CustomerLoginListener.getId());
				try {
					Data.orgetnum();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				cuor.setOrid("订单"+ Data.getNumor());
				cuor.setOption(0);
				cuor.setPrice(0);
				cuor.setFlag(1);
				cuor1.setCuid(CustomerLoginListener.getId());
				cuor1.setOrid("订单"+ Data.getNumor());
				cuor1.setOption(0);
				cuor1.setPrice(0);
				cuor1.setFlag(1);
				try {
					Data.getOr().addone(cuor);
				} catch (SQLException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
				OpCustomer.main(null);
				
			}
			
		});
		LoBtn.addActionListener(new CulookListener());
		MoBtn.addActionListener(new CuModListener());
		DeBtn.addActionListener(new CudeListener());
//		CuFunction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CuFunction.add(bgPanel);
		CuFunction.setVisible(true);
	}
	
	
		
}




