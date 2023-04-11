package Face;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Mod.Data;
import System.Apply;
import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class ApplyRegister {
	JFrame ApplyRegister = new JFrame("员工应聘系统");
	final int WIDTH = 500;
	final int HEIGHT = 350;
	
	
	
	public void init(){
		//窗口相关属性
		
		//窗口在电脑正中间
		ApplyRegister.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		//设置窗口内容
		BackGroundPanel bgPanel = null;
		try {
			bgPanel = new BackGroundPanel(ImageIO.read(new File("images/1.jpg")));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		//组装登录相关元素
		
		//建立垂直元素(用户名，密码，登录，注册等)
		Box vBox = Box.createVerticalBox();
		
		//用户名与输入框水平
		Box nameBox = Box.createHorizontalBox();
		JLabel nameLabel = new JLabel("姓        名:");
		JTextField nameField = new JTextField(10);//输入框
		nameBox.add(nameLabel);
		nameBox.add(Box.createHorizontalStrut(10));//水平间隔
		nameBox.add(nameField);
		
		//性别单选按钮
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
		
		//应聘工作下拉菜单
		Box jBox = Box.createHorizontalBox();
		JLabel jLabel = new JLabel("应聘岗位:");
		JComboBox<String> jcmb=new JComboBox<String>();//下拉菜单窗口
		jBox.add(jLabel);
		jBox.add(Box.createHorizontalStrut(10));
		jBox.add(jcmb);
		jcmb.addItem("请选择");
		jcmb.addItem("服务员");
		jcmb.addItem("外卖员");
		
		//薪水
		Box wBox = Box.createHorizontalBox();
		JLabel wLabel = new JLabel("预期薪水:");
		JTextField wField = new JTextField(10);//输入框
		
		wBox.add(wLabel);
		wBox.add(Box.createHorizontalStrut(10));//水平间隔
		wBox.add(wField);
		
		//应聘原因
		Box rBox = Box.createHorizontalBox();
		JLabel rLabel = new JLabel("应聘原因:");
		JTextField rField = new JTextField();//输入框
		rBox.add(rLabel);
		rBox.add(Box.createHorizontalStrut(10));//水平间隔
		rBox.add(rField);
		
		Box sBox = Box.createHorizontalBox();
		JLabel label = new JLabel("");
		sBox.add(label);
		
		//登录按钮与注册按钮水平
		Box btnBox = Box.createHorizontalBox();
		JButton loginBtn = new JButton("  应  聘  ");
//		JButton registBtn = new JButton("注册");
		btnBox.add(Box.createHorizontalStrut(30));
		btnBox.add(loginBtn);
//		btnBox.add(Box.createHorizontalStrut(50));
//		btnBox.add(registBtn);
		
		
		
		//将水平组装的添加到垂直
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(nameBox);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(selectPanel);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(jBox);
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(wBox);
		vBox.add(Box.createVerticalStrut(30));
		vBox.add(rBox);
		vBox.add(Box.createVerticalStrut(5));
		vBox.add(sBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(btnBox);
		
		bgPanel.add(vBox);
		
		//男女按钮监听
		Apply now = new Apply();
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
		
		//下拉菜单监听
		jcmb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				 now.setJob(e.getItem().toString());
			}
			
		}); 
		
		//应聘按钮监听
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				if(now.getJob()==null||now.getJob().equals("请选择"))
				{
					label.setForeground(Color.red);
					label.setFont(new Font("楷体",Font.BOLD,18));
					label.setText("请选择招聘岗位");
				}
				else 
				{
					try {
						Data.apgetnum();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
//					System.out.println(Data.getNumap());
					now.setId(""+(int)(Data.getNumap()+1));
					now.setName(nameField.getText());
					int len=wField.getText().length();
					char[] wages = wField.getText().toCharArray();
					//字符串转变为小数a.b
					
//					System.out.print("\n");
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
					now.setReason(rField.getText());
					now.setTaid("1");
					now.setEmid("1");
//					System.out.println(now.getTaid());
					try {
						Data.getAp().addone(now);
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(new JFrame(),"应聘简历递交成功,\n请等待通知");
				}
			}
			
		});
		

		
		ApplyRegister.add(bgPanel);
		ApplyRegister.setVisible(true);

	}
	
	public static void main(String[] args) {
		new ApplyRegister().init();
	}
}
