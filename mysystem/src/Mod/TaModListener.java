package Mod;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Face.TakeoutLogin;
import Tool.*;
import System.*;

public class TaModListener implements ActionListener{

	JFrame TaModListener = new JFrame("员工系统");
	
	final int WIDTH = 500;
	final int HEIGHT = 310;	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		//窗口在电脑正中间
				TaModListener.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
						(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
				
				//设置窗口内容
				BackGroundPanel bgPanel = null;
				try {
					bgPanel = new BackGroundPanel(ImageIO.read(new File("images/35.jfif")));
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
				
				//组装登录相关元素
				
				//建立垂直元素(用户名，密码，登录，注册等)
				Box vBox = Box.createVerticalBox();
				
				//用户名与输入框水平
				Box uBox = Box.createHorizontalBox();
				JLabel uLabel = new JLabel("用  户  名:");
				JTextField uField = new JTextField(10);//输入框
				uField.setText("WMY");
				uBox.add(uLabel);
				uBox.add(Box.createHorizontalStrut(20));//水平间隔
				uBox.add(uField);
				
				Box nameBox = Box.createHorizontalBox();
				JLabel nameLabel = new JLabel("姓        名:");
				JTextField nameField = new JTextField(10);//输入框
				nameBox.add(nameLabel);
				nameBox.add(Box.createHorizontalStrut(20));//水平间隔
				nameBox.add(nameField);
				
				//密码与输入框水平
				Box pBox = Box.createHorizontalBox();
				JLabel pLabel = new JLabel("密        码:");
				JPasswordField pField = new JPasswordField(10);//输入框
				pBox.add(pLabel);
				pBox.add(Box.createHorizontalStrut(20));//水平间隔
				pBox.add(pField);
				
				//再次确认密码与输入框水平
				Box cpBox = Box.createHorizontalBox();
				JLabel cpLabel = new JLabel("确认密码:");
				JPasswordField cpField = new JPasswordField(10);//输入框
				cpBox.add(cpLabel);
				cpBox.add(Box.createHorizontalStrut(20));//水平间隔
				cpBox.add(cpField);
				
				JLabel sexLabel = new JLabel("性        别:");
				ButtonGroup bg = new ButtonGroup();//单选，防止选多个
				JRadioButton male = new JRadioButton("男",true);
				JRadioButton female = new JRadioButton("女",false);
				male.setFont(new Font("楷体",Font.BOLD,15));
				female.setFont(new Font("楷体",Font.BOLD,15));
				JPanel selectPanel = new JPanel();
				bg.add(male);bg.add(female);
				selectPanel.add(Box.createHorizontalStrut(-16));
				selectPanel.add(sexLabel);
				selectPanel.add(Box.createHorizontalStrut(20));
				selectPanel.add(male);
				selectPanel.add(female);
				male.setOpaque(false);
				female.setOpaque(false);
				selectPanel.setOpaque(false);
				
				Box sBox = Box.createHorizontalBox();
				JLabel label;
				label=new JLabel(" ");
				sBox.add(label);
				
				//登录按钮与注册按钮水平
				Box btnBox = Box.createHorizontalBox();
				JButton registBtn = new JButton("修改");
				btnBox.add(Box.createHorizontalStrut(40));
				btnBox.add(registBtn);
				
//				btnBox.add(registBtn);

				
				//将水平组装的添加到垂直
				vBox.add(Box.createVerticalStrut(30));
				vBox.add(uBox);
				vBox.add(Box.createVerticalStrut(20));
				vBox.add(nameBox);
				vBox.add(Box.createVerticalStrut(20));
				vBox.add(pBox);
				vBox.add(Box.createVerticalStrut(20));
				vBox.add(cpBox);
				vBox.add(Box.createVerticalStrut(5));
				vBox.add(selectPanel);
				vBox.add(Box.createVerticalStrut(5));
				vBox.add(sBox);
				vBox.add(Box.createVerticalStrut(5));
				vBox.add(btnBox);
				
				bgPanel.add(vBox);
				
				Takeout now = new Takeout();
				now.setSex("男");
				
				male.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						now.setSex("男");
					}
					
				});
				
				//女性按钮
				female.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						now.setSex("女");
					}
					
				});
				
				//登录按钮监听
				registBtn.addActionListener(new ActionListener()
		        {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						String id=uField.getText();
						String passwd1=new String(pField.getPassword());
						String passwd2=new String(cpField.getPassword());
						try {
							if((Data.getTa().search(id)!=null||Data.getDe().search(id)!=null)&&TakeoutLogin.getId().equals(id)==false)
							{
								label.setForeground(Color.red);
								label.setFont(new Font("楷体",Font.BOLD,18));
								label.setText("用户名已存在");
							}else if(passwd1.equals(passwd2)==false){
								label.setForeground(Color.red);
								label.setFont(new Font("楷体",Font.BOLD,18));
								label.setText("两次密码不相同");
							}else if(id.equals("WMY")||passwd1.equals(""))
							{
								label.setForeground(Color.red);
								label.setFont(new Font("楷体",Font.BOLD,18));
								label.setText("账号密码不能为空");
							}else {
								
								now.setId(uField.getText());
								now.setName(nameField.getText());
								now.setWages(TakeoutLogin.getTaa().getWages());
								now.setPasswd(new String(cpField.getPassword()));
								Data.getTa().edit(TakeoutLogin.getTaa(),now);
								TakeoutLogin.setTaa(now);
								TakeoutLogin.setId(now.getId());
								TakeoutLogin.setPasswd(now.getPasswd());
								label.setFont(new Font("楷体",Font.BOLD,18));
								label.setText("修改成功");
							}
								
						} catch (SQLException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						
						
					}
		        });
				
				TaModListener.add(bgPanel);
				TaModListener.setVisible(true);
	}
}
