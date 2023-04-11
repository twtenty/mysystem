package Mod;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Face.CustomerLoginListener;
import System.Customer;
import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class CuModListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame CuModListener = new JFrame("修改个人信息");
		
		final int WIDTH = 500;
		final int HEIGHT = 400;
		
		CuModListener.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		BackGroundPanel bgPanel = null;
		try {
			bgPanel = new BackGroundPanel(ImageIO.read(new File("images/26.jfif")));
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		Box vBox = Box.createVerticalBox();
		
		//用户名与输入框水平
		Box idBox = Box.createHorizontalBox();
		JLabel idLabel = new JLabel("用  户  名:");
		JTextField idField = new JTextField(10);//输入框
		idField.setText("GK");
		idBox.add(idLabel);
		idBox.add(Box.createHorizontalStrut(20));//水平间隔
		idBox.add(idField);
				
		
				
		//姓名与输入框水平
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
				
		//确认密码与输入框水平
		Box cpBox = Box.createHorizontalBox();
		JLabel cpLabel = new JLabel("确认密码:");
		JPasswordField cpField = new JPasswordField(10);//输入框
		cpBox.add(cpLabel);
		cpBox.add(Box.createHorizontalStrut(20));//水平间隔
		cpBox.add(cpField);
		
		
		
		//手机号码
		Box phoneBox = Box.createHorizontalBox();
		JLabel phoneLabel = new JLabel("手机号码:");
		JTextField phoneField = new JTextField(10);//输入框
		phoneBox.add(phoneLabel);
		phoneBox.add(Box.createHorizontalStrut(20));//水平间隔
		phoneBox.add(phoneField);
		
		//地址
		Box addBox = Box.createHorizontalBox();
		JLabel addLabel = new JLabel("收货地址:");
		JTextField addField = new JTextField(10);//输入框
		addBox.add(addLabel);
		addBox.add(Box.createHorizontalStrut(20));//水平间隔
		addBox.add(addField);
		
		//男女单选按钮
		JLabel sexLabel = new JLabel("性        别:");
		ButtonGroup bg = new ButtonGroup();//单选，防止选多个
		JRadioButton male = new JRadioButton("男",true);
		JRadioButton female = new JRadioButton("女",false);
		male.setFont(new Font("楷体",Font.BOLD,15));
		female.setFont(new Font("楷体",Font.BOLD,15));
		JPanel selectPanel = new JPanel();
		bg.add(male);bg.add(female);
		selectPanel.add(Box.createHorizontalStrut(-13));
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
//		JButton loginBtn = new JButton("登录");
		JButton registBtn = new JButton("修改");
		btnBox.add(Box.createHorizontalStrut(10));
		btnBox.add(registBtn);
		
		Customer now = new Customer();
		now.setSex("男");
		
		//男性按钮
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
		
		
		registBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String id=idField.getText();
				String passwd1=new String(pField.getPassword());
				String passwd2=new String(cpField.getPassword());
				try {
					if(Data.getCu().search(id)!=null&&id.equals(CustomerLoginListener.getId())==false)
					{
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("用户名已存在");
	//					JOptionPane.showMessageDialog(CuModListener, "用户已存在,请重新注册");
					}else if(passwd1.equals(passwd2)==false){
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("两次密码不相同");
	//					JOptionPane.showMessageDialog(CuModListener, "两次密码不同");
					}else if(id.equals("GK")||passwd1.equals("")) {
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("账号密码不能为空");
					}else if(phoneField.getText().length()!=11) {
						label.setText("手机号格式错误");
					}else {
//						System.out.println(phoneField.getText());
						now.setId(idField.getText());
						now.setName(nameField.getText());
						now.setPasswd(new String(cpField.getPassword()));
						now.setFlag(0);
						now.setNumber(phoneField.getText());
//						System.out.println(now.getNumber());
						now.setAddress(addField.getText());
						Data.getCu().addone(now);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("修改成功");
						Data.getCu().edit(CustomerLoginListener.getCuu(), now);
						CustomerLoginListener.setCuu(now);
						CustomerLoginListener.setId(now.getId());
						CustomerLoginListener.setPasswd(now.getPasswd());
	//					JOptionPane.showMessageDialog(CuModListener, "注册成功");
					}	
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}	
			}
		});
		
		
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(idBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(nameBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(pBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(cpBox);
		
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(phoneBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(addBox);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(selectPanel);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(sBox);
		vBox.add(btnBox);
				
		bgPanel.add(vBox);
		
		CuModListener.add(bgPanel);
		CuModListener.setVisible(true);
	}
}
