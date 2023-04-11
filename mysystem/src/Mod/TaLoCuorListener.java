package Mod;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import System.Customer;
import System.Orders;
import Tool.BackGroundPanel;

public class TaLoCuorListener implements ActionListener{

	final int WIDTH = 600;
	final int HEIGHT = 400;
	private static String orid = new String();
	
	
	public static String getOrid() {
		return orid;
	}

	public static void setOrid(String orid) {
		TaLoCuorListener.orid = orid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame jf = new JFrame(TaLookListener.getCuid()+"的订单表");
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/31.jfif")));
			jf.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		jf.setBounds(100,100,WIDTH,HEIGHT);
		JPanel contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0,0));
		jf.setContentPane(contentPane);
		
		JScrollPane scrollPane=new JScrollPane();
	    contentPane.add(scrollPane,BorderLayout.NORTH);
		
	    Box ListBox = Box.createHorizontalBox();
	    
	    Box opBox = Box.createHorizontalBox();
	    //最后的选择订单送菜
	    JLabel uLabel = new JLabel("请选择订单:");
		
		JComboBox<String> cmb=new JComboBox<String>();//下拉菜单窗口
		cmb.addItem("请选择");
	    
		JList<String> list1=new JList<String>();
		JList<String> list2=new JList<String>();
		
	    scrollPane.setViewportView(ListBox);
	    
	    String[] listData1=new String[30];
	    String[] listData2=new String[30];
	    int len1=0,len2=0;
	    listData1[len1++]="菜名";
	    listData2[len2++]="价格";
	    
	    listData1[len1++]=" ";
	    listData2[len2++]=" ";
	    
	    
	    String sql = "select * from orders where cuid = '"+TaLookListener.getCuid()+"'";
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
			Statement orsta = null;
			ResultSet orre = null;
			while(re.next())
			{
				String orid = re.getString("orid");
				
				
				
				int orop = re.getInt("op");
				int orfl = re.getInt("flag");
				if(orfl==0||orop==0) continue;
				listData1[len1++]=orid;
			    listData2[len2++]=" ";
			    cmb.addItem(orid);
//				System.out.println("orid="+orid);
				String orsql = "select * from dish where orid = '"+orid+"'";
				orsta =  Data.conn.createStatement();
				orre = orsta.executeQuery(orsql);
				while(orre.next())
				{
					listData1[len1++]=orre.getString("me");
					listData2[len2++]=""+orre.getDouble("price");
				}
				listData1[len1++]="总消费:"+re.getDouble("price");
				listData2[len2++]=" ";
				
				listData1[len1++]=" ";
				listData2[len2++]=" ";
				listData1[len1++]=" ";
				listData2[len2++]=" ";

//				orsta.close();
//				orre.close();
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		list1.setListData(listData1);
		list2.setListData(listData2);
		
		ListBox.add(list1, BorderLayout.NORTH);
		ListBox.add(Box.createHorizontalStrut(20));
		ListBox.add(list2, BorderLayout.NORTH);
		
		JButton DeBtn = new JButton("送餐");
		
		opBox.add(uLabel);
		opBox.add(Box.createHorizontalStrut(10));
		opBox.add(cmb,BorderLayout.CENTER);
		opBox.add(Box.createHorizontalStrut(10));
		opBox.add(DeBtn);
		
		contentPane.add(opBox,BorderLayout.SOUTH);
		
		
		//下拉菜单监听
		cmb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				// TODO 自动生成的方法存根
				 orid=e.getItem().toString();				 
			}
			
		});
		
		//监听
		DeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				try {
					Orders now = Data.getOr().search(orid);
//					System.out.println(now.isFlag());
					if(now.isFlag()==0)
					{
						JOptionPane.showMessageDialog(new JFrame(),"该订单已经完成,送餐失败");
					}
					else
					{

						Orders now1 = now;
						now1.setFlag(0);
						Data.getOr().edit(now, now1);
						Statement sta = null;
						ResultSet re = null;
						String sql = "select * from orders where cuid = '"+TaLookListener.getCuid()+"'";
						sta = Data.conn.createStatement();
						re = sta.executeQuery(sql);
						boolean flag = false;
						while(re.next())
						{
							if(re.getInt("flag")==1)
							{
								flag=true;
							}
						}
						if(flag==false)
						{
							Customer cu = Data.getCu().search(TaLookListener.getCuid());
							Customer cu1= cu;
							cu.setFlag(0);
							Data.getCu().edit(cu1, cu);
							
						}
						JOptionPane.showMessageDialog(new JFrame(),
								"该顾客的手机号为:"+Data.getCu().search(TaLookListener.getCuid()).getNumber()
								+"\n该顾客的收货地址为:"+Data.getCu().search(TaLookListener.getCuid()).getAddress());
					}
					
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		
		jf.setVisible(true);
	}

}
