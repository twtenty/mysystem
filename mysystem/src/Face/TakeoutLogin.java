package Face;
import Mod.*;
import System.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import Tool.*;

public class TakeoutLogin {

	JFrame TakeoutLogin = new JFrame("外卖员系统");
	
	final int WIDTH = 500;
	final int HEIGHT = 300;
	
	private static String id = new String();
	private static String passwd = new String();
	private static Takeout  taa= new Takeout();
	
	
	public static String getId() {
		return id;
	}

	public static void setId(String iid) {
		id = iid;
	}

	public static String getPasswd() {
		return passwd;
	}

	public static void setPasswd(String ppasswd) {
		passwd = ppasswd;
	}

	public static Takeout getTaa() {
		return taa;
	}

	public static void setTaa(Takeout ta) {
		taa = ta;
	}

	public void init() throws IOException {
		//窗口相关属性
		
		//窗口在电脑正中间
		TakeoutLogin.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		//设置窗口内容
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/8.jfif")));
		
		//组装登录相关元素
		
		//建立垂直元素(用户名，密码，登录，注册等)
		Box vBox = Box.createVerticalBox();
		
		//用户名与输入框水平
		Box uBox = Box.createHorizontalBox();
		JLabel uLabel = new JLabel("用户名:");
		JTextField uField = new JTextField(10);//输入框
		uField.setText("WMY");
		uBox.add(uLabel);
		uBox.add(Box.createHorizontalStrut(20));//水平间隔
		uBox.add(uField);
		
		//密码与输入框水平
		Box pBox = Box.createHorizontalBox();
		JLabel pLabel = new JLabel("密    码:");
		JPasswordField pField = new JPasswordField(10);//输入框
		pBox.add(pLabel);
		pBox.add(Box.createHorizontalStrut(20));//水平间隔
		pBox.add(pField);
		
		Box sBox = Box.createHorizontalBox();
		JLabel label;
		label=new JLabel(" ");
		sBox.add(label);
		
		//登录按钮与注册按钮水平
		Box btnBox = Box.createHorizontalBox();
		JButton loginBtn = new JButton("登录");
//		JButton registBtn = new JButton("注册");
		btnBox.add(Box.createHorizontalStrut(40));
		btnBox.add(loginBtn);
		
//		btnBox.add(registBtn);
		
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
		loginBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				passwd = new String(pField.getPassword());
//				System.out.println(uField.getText()+"\n"+passwd);
				id=uField.getText();
				Takeout now=null;
				try {
					now = Data.getTa().search(id);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
						
				if(now==null)
				{
					label.setForeground(Color.red);
					label.setFont(new Font("楷体",Font.BOLD,18));
					label.setText("账号错误，登陆失败");
								
				}
				else if(now.getPasswd().equals(passwd)==false)
				{
					label.setForeground(Color.red);
					label.setFont(new Font("楷体",Font.BOLD,18));
					label.setText("密码错误，登陆失败");
				}
				else
				{
					label.setFont(new Font("楷体",Font.BOLD,18));
					label.setText("登陆成功");
					taa=now;
					TaFunction.main(null);
				}
			}
		 });
		
		TakeoutLogin.add(bgPanel);
		TakeoutLogin.setVisible(true);
		
		uField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO 自动生成的方法存根
				if(e.getKeyChar() == KeyEvent.VK_ENTER )
				{
					passwd = new String(pField.getPassword());
//					System.out.println(uField.getText()+"\n"+passwd);
					id=uField.getText();
					Takeout now=null;
					try {
						now = Data.getTa().search(id);
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
							
					if(now==null)
					{
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("账号错误，登陆失败");
									
					}
					else if(now.getPasswd().equals(passwd)==false)
					{
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("密码错误，登陆失败");
					}
					else
					{
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("登陆成功");
						taa=now;
						TaFunction.main(null);
					}
				}
			}
			
		});
		pField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO 自动生成的方法存根
				if(e.getKeyChar() == KeyEvent.VK_ENTER )
				{
					passwd = new String(pField.getPassword());
//					System.out.println(uField.getText()+"\n"+passwd);
					id=uField.getText();
					Takeout now=null;
					try {
						now = Data.getTa().search(id);
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
							
					if(now==null)
					{
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("账号错误，登陆失败");
									
					}
					else if(now.getPasswd().equals(passwd)==false)
					{
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("密码错误，登陆失败");
					}
					else
					{
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("登陆成功");
						taa=now;
						TaFunction.main(null);
					}
				}
			}
		});
	}
	
	public static void main(String[] args) {
		try {
			new TakeoutLogin().init();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
