package Mod;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import Face.CustomerLoginListener;
import System.*;
import Tool.*;

public class OpCustomer {
	
	final static int WIDTH = 800;
	final static int HEIGHT = 450;
	
	
	public static void main(String[] args) {
		

		JFrame OpCustomer = new JFrame("选择菜品") ;
//		OpCustomer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//窗口在电脑正中间
		OpCustomer.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
						(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/29.jfif")));
			OpCustomer.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		Container contentPane=OpCustomer.getContentPane();
        
        String[] top={"菜名","价格"};
        
        try {
			Data.megetnum();
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        
        //建立二维数组，存储应聘人员信息
        Object[][] td=new Object[Data.getNumme()][2];
        int len=0;  
	   
	    
	    String sql = "select * from menus ";
//		System.out.println(sql);
		Statement sta = null;
		ResultSet re = null;
		try {
			
			sta = Data.conn.createStatement();
			re = sta.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		try {
			while(re.next())
			{
				td[len][0]=re.getString("menu");
				td[len++][1]=re.getString("price");
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		JTable table=new JTable(td,top);
		contentPane.add(new JScrollPane(table));
	    
	    Box vBox = Box.createVerticalBox();
	    
	    Box opBox = Box.createHorizontalBox();
		JLabel opLabel = new JLabel("请输入想吃的菜:");
		
		JTextField opField = new JTextField(5);
		opBox.add(opLabel);
		opBox.add(Box.createHorizontalStrut(10));//水平间隔
		opBox.add(opField);
		JLabel oplabel=new JLabel(" ");
		opBox.add(Box.createHorizontalStrut(10));//水平间隔
		opBox.add(oplabel);
		JButton opBtn = new JButton("购买");
//		opBox.add(Box.createHorizontalStrut(20));//水平间隔
		opBox.add(opBtn);
		
		
		vBox.add(opBox);
		vBox.add(Box.createVerticalStrut(5));
		
		//外卖与到店吃
		ButtonGroup bg = new ButtonGroup();//单选，防止选多个
		JRadioButton nodeliver = new JRadioButton("到店吃",true);
		JRadioButton deliver = new JRadioButton("外卖",false);
		nodeliver.setFont(new Font("楷体",Font.BOLD,18));
		deliver.setFont(new Font("楷体",Font.BOLD,18));
		JPanel selectPanel = new JPanel();
		bg.add(nodeliver);bg.add(deliver);
		selectPanel.add(nodeliver);
		selectPanel.add(deliver);
		
		vBox.add(selectPanel);
		contentPane.add(vBox,BorderLayout.SOUTH);
		
		//购买按钮监听事件
		opBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name = opField.getText();
//				System.out.println(name);
				Menus now=null;
				try {
					now = Data.getMe().search(name);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				if(now==null)
				{
					oplabel.setForeground(Color.red);
					oplabel.setFont(new Font("楷体",Font.BOLD,18));
					oplabel.setText("没有此菜");
				}
				else
				{
					oplabel.setFont(new Font("楷体",Font.BOLD,18));
					Dish ordi= new Dish();
					try {
						Data.digetnum();
					} catch (SQLException e2) {
						// TODO 自动生成的 catch 块
						e2.printStackTrace();
					}
					Data.setNumdi(Data.getNumdi() + 1);
					ordi.setId("订单菜"+ Data.getNumdi());
					ordi.setMe(name);
//					System.out.println(Data.numdi);
					ordi.setPrice(now.getPrice());
					Data.income+=now.getPrice();
					try {
						Data.exportPay(Data.pay);
						Data.exportIncome(Data.income);
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					try {
						Data.exportPay(Data.pay);
						Data.exportIncome(Data.income);
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					CuFunction.getCuor1().setPrice(CuFunction.getCuor1().getPrice()+now.getPrice());
					ordi.setorid(CuFunction.getCuor1().getOrid());
					try {
						Data.getDi().addone(ordi);
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					oplabel.setText("购买成功");
					try {
//						System.out.println("完成");
//						System.out.println(CuFunction.cuor1.isFlag());
						Data.getOr().edit(CuFunction.getCuor(), CuFunction.getCuor1());
						Customer cuu1 = CustomerLoginListener.getCuu();
						cuu1.setFlag(1);
						Data.getCu().edit(CustomerLoginListener.getCuu(), cuu1);
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}
			
		});
		
		//派送按钮监听
		nodeliver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				CuFunction.getCuor1().setOption(0);
			}
			
		});
		deliver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
//				System.out.println("外卖");
				CuFunction.getCuor1().setOption(1);
				try {
//					System.out.println("完成");
//					System.out.println(CuFunction.cuor1.isFlag());
					Data.getOr().edit(CuFunction.getCuor(), CuFunction.getCuor1());
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		
		
		
		OpCustomer.setVisible(true);
	}
	
	
}


