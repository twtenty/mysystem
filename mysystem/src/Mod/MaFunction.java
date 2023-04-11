package Mod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

import Face.ManagerLoginListener;
import System.Depart;
import Tool.*;


public class MaFunction {
	
	JFrame CuFunction = new JFrame("亲爱的"+ManagerLoginListener.getMaa().getName()+"用户,欢迎登陆！") ;
	final int WIDTH = 500;
	final int HEIGHT = 300;
	
	public static void main(String[] args) {
		try {
			new MaFunction().init();
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
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/25.jfif")));
		
		//组装登录相关元素
		
		//建立垂直元素
		Box vBox = Box.createVerticalBox();
		
		//管理员系统
		JButton IPBtn = new JButton("查看收入和支出");
		
		//员工系统
		JButton EmBtn = new JButton("查看服务员信息");
		
		//外卖员系统
		JButton TaBtn = new JButton("查看外卖员信息");
		
		//顾客系统
		JButton MoBtn = new JButton("修改个人信息    ");
		
		//解雇人员按钮
		JButton DeBtn = new JButton("查看解雇人员    ");
		
		//增删菜品
		JButton MeBtn = new JButton("增加和删除菜品");
		
		//查看应聘人员
		JButton ApBtn = new JButton("查看应聘人员    ");
		
		//离职
		JButton ReBtn = new JButton("     离          职     ");
		
		
		//将水平组装的添加到垂直
		Box uBox1 = Box.createHorizontalBox();
		Box uBox2 = Box.createHorizontalBox();
		Box uBox3 = Box.createHorizontalBox();
		Box uBox4 = Box.createHorizontalBox();
		
		vBox.add(Box.createVerticalStrut(30));
		
		uBox1.add(IPBtn);
		uBox1.add(Box.createHorizontalStrut(30));
		uBox1.add(EmBtn);
		vBox.add(uBox1);
		
		vBox.add(Box.createVerticalStrut(20));
		
		uBox2.add(TaBtn);
		uBox2.add(Box.createHorizontalStrut(30));
		uBox2.add(MoBtn);
		vBox.add(uBox2);
		
		vBox.add(Box.createVerticalStrut(20));
		
		uBox3.add(DeBtn);
		uBox3.add(Box.createHorizontalStrut(30));
		uBox3.add(MeBtn);
		vBox.add(uBox3);
		
		vBox.add(Box.createVerticalStrut(20));
		
		uBox4.add(ApBtn);
		uBox4.add(Box.createHorizontalStrut(30));
		uBox4.add(ReBtn);
		
		vBox.add(uBox4);
		
		bgPanel.add(vBox);
		
		//按钮的事件
		IPBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				JOptionPane.showMessageDialog(new JFrame(), "收入为:"+Data.income+"\n支出为:"+Data.pay);	
			}
			
		});
		
		EmBtn.addActionListener(new MaEmlookListener());
		TaBtn.addActionListener(new MaTalookListener());
		MoBtn.addActionListener(new MaModListener());
		DeBtn.addActionListener(new MaDeListener());
		MeBtn.addActionListener(new MaMeListener());
		ApBtn.addActionListener(new MaApListener());
		ReBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String reason=JOptionPane.showInputDialog("请输入离职原因:","");
				if(reason==null)
				{
					JOptionPane.showMessageDialog(new JFrame(),"离职失败");
				}
				else
				{
					Depart now = new Depart();
					now.setId(ManagerLoginListener.getId());
					now.setName(ManagerLoginListener.getMaa().getName());
					now.setPasswd(ManagerLoginListener.getPasswd());
					now.setReason(reason);
					now.setDate(new Date());
					try {
						if(Data.getMa().delete(ManagerLoginListener.getId())==null)
						{
							JOptionPane.showMessageDialog(new JFrame(),"离职失败");
						}
						else 
						{
							Data.getDe().addone(now);
							JOptionPane.showMessageDialog(new JFrame(),"离职成功");
						}
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
				
				
			}
			
		});
//		CuFunction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CuFunction.add(bgPanel);
		CuFunction.setVisible(true);
	}
	
	
		
}




