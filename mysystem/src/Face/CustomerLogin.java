package Face;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import Tool.*;

public class CustomerLogin {
	
	JFrame CustomerLogin = new JFrame("顾客系统");
	
	private static JTextField uField = new JTextField(10);//输入框
	private static JPasswordField pField = new JPasswordField(10);//输入框
	private static JLabel label=new JLabel(" ");
	
	
	public static JTextField getuField() {
		return uField;
	}

	public static void setuField(JTextField uuField) {
		uField = uuField;
	}

	public static JPasswordField getpField() {
		return pField;
	}

	public static void setpField(JPasswordField ppField) {
		pField = ppField;
	}

	public static JLabel getLabel() {
		return label;
	}

	public static void setLabel(JLabel llabel) {
		label = llabel;
	}

	final int WIDTH = 500;
	final int HEIGHT = 300;
	
	public void init() {
		//窗口相关属性
		
		//窗口在电脑正中间
		CustomerLogin.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		//设置窗口内容
		BackGroundPanel bgPanel = null;
		try {
			bgPanel = new BackGroundPanel(ImageIO.read(new File("images/2.jpg")));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		//组装登录相关元素
		
		//建立垂直元素(用户名，密码，登录，注册等)
		Box vBox = Box.createVerticalBox();
		
		//用户名与输入框水平
		Box uBox = Box.createHorizontalBox();
		JLabel uLabel = new JLabel("用户名:");
		uField = new JTextField(15);//输入框
		
		uField.setText("GK");
		uBox.add(uLabel);
		uBox.add(Box.createHorizontalStrut(20));//水平间隔
		uBox.add(uField);
		
		//密码与输入框水平
		Box pBox = Box.createHorizontalBox();
		JLabel pLabel = new JLabel("密    码:");
		pField = new JPasswordField(15);
		
		pBox.add(pLabel);
		pBox.add(Box.createHorizontalStrut(20));//水平间隔
		pBox.add(pField);
		
		//登录按钮与注册按钮水平
		Box btnBox = Box.createHorizontalBox();
		JButton loginBtn = new JButton("登录");
		JButton registBtn = new JButton("注册");
		btnBox.add(loginBtn);
		btnBox.add(Box.createHorizontalStrut(40));
		btnBox.add(registBtn);
		
		//密码输入正误的提示文本
		Box sBox = Box.createHorizontalBox();
		
		
		sBox.add(label);
		
		//将水平组装的添加到垂直
		vBox.add(Box.createVerticalStrut(70));
		vBox.add(uBox);
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(pBox);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(sBox);
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(btnBox);
		
		bgPanel.add(vBox);
		
		//登录按钮监听
		loginBtn.addActionListener(new CustomerLoginListener());
		
		//注册按钮监听
		registBtn.addActionListener(new CustomerRegisterListener());
		
		CustomerLogin.add(bgPanel);
		CustomerLogin.setVisible(true);
		
		uField.addKeyListener(new Key_CuListener());
		pField.addKeyListener(new Key_CuListener());
	}
	
	public static void main(String[] args) {
		new CustomerLogin().init();
	}
}


