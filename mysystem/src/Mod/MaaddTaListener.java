package Mod;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;

import Face.ManagerLoginListener;
import System.*;
import Tool.*;

public class MaaddTaListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame MaaddTaListener = new JFrame("员工注册系统");
		
		final int WIDTH = 500;
		final int HEIGHT = 330;
		
		MaaddTaListener.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		BackGroundPanel bgPanel = null;
		try {
			bgPanel = new BackGroundPanel(ImageIO.read(new File("images/18.jfif")));
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		Box vBox = Box.createVerticalBox();
		
		//用户名与输入框水平
		Box idBox = Box.createHorizontalBox();
		JLabel idLabel = new JLabel("用  户  名:");
		JTextField idField = new JTextField(10);//输入框
		idField.setText("WMY");
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
		
		//性别按钮
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
		
		//工资与输入框水平
		Box wagesBox = Box.createHorizontalBox();
		JLabel wagesLabel = new JLabel("工        资:");
		JTextField wagesField = new JTextField(10);//输入框
		wagesBox.add(wagesLabel);
		wagesBox.add(Box.createHorizontalStrut(20));//水平间隔
		wagesBox.add(wagesField);
		
		Box sBox = Box.createHorizontalBox();
		JLabel label;
		label=new JLabel(" ");
		sBox.add(label);
				
		//登录按钮与注册按钮水平
		Box btnBox = Box.createHorizontalBox();
//		JButton loginBtn = new JButton("登录");
		JButton registBtn = new JButton("注册");
		btnBox.add(Box.createHorizontalStrut(40));
		btnBox.add(registBtn);
		
		Takeout now = new Takeout();
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
					if(Data.getTa().search(id)!=null||Data.getDe().search(id)!=null)
					{
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("用户名已存在");
					}else if(passwd1.equals(passwd2)==false){
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("两次密码不相同");
					}else if(id.equals("WMY")==true||passwd1.equals("")) {
						label.setForeground(Color.red);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("账号密码不能为空");
					}else {
						
						now.setId(idField.getText());
						now.setName(nameField.getText());
						now.setPasswd(new String(cpField.getPassword()));
						int len=wagesField.getText().length();
						char[] wages = wagesField.getText().toCharArray();
						//字符串转变为小数a.b
						
//						System.out.print("\n");
						double a=0,b=0;
						int idx=len;//记录小数点位置，先默认没有小数点
						for(int i=0;i<len;i++)
						{
							if(wages[i]=='.')
							{
								idx=i;
								break;
							}
							a=a*10+(wages[i]-'0');
						}
						for(int i=len-1;i>idx;i--)
						{
							b=b*0.1+(wages[i]-'0');
						}
						now.setWages(a+b*0.1);
						Data.pay+=a+b*0.1;
						try {
							Data.exportPay(Data.pay);
							Data.exportIncome(Data.income);
						} catch (IOException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						now.setMaid(ManagerLoginListener.getId());
						Data.getTa().addone(now);
						label.setFont(new Font("楷体",Font.BOLD,18));
						label.setText("注册成功");
					}
						
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
				
			}
			
		});
		
		//将水平组装的添加到垂直
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(idBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(nameBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(pBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(cpBox);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(selectPanel);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(wagesBox);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(sBox);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(btnBox);
		
				
		bgPanel.add(vBox);
		
		MaaddTaListener.add(bgPanel);
		MaaddTaListener.setVisible(true);
	}
	
}
