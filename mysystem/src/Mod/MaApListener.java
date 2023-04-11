package Mod;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Face.ManagerLoginListener;
import System.Apply;
import System.Employee;
import System.Takeout;
import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class MaApListener implements ActionListener{

	final int WIDTH = 800;
	final int HEIGHT = 450;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame frame=new JFrame("应聘人员表");
		frame.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/19.jfif")));
			frame.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
        Container contentPane=frame.getContentPane();
        
        String[] top={"序号","姓名","性别","应聘岗位","预期薪水","应聘简介"};
        try {
			Data.apgetnum();
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
        
        //建立二维数组，存储应聘人员信息
        Object[][] td=new Object[Data.getNumap()][6];
        int len=0;
        
        //查询所有应聘人员信息
        String sql = "select * from apply ";
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
				td[len][0]=re.getString("id");
				td[len][1]=re.getString("name");
				td[len][2]=re.getString("sex");
				td[len][3]=re.getString("job");
				td[len][4]=re.getString("wages");
				td[len++][5]=re.getString("reason");
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		Box uBox = Box.createHorizontalBox();
		JLabel ulabel = new JLabel("请输入员工序号");
		JTextField uField = new JTextField(5);
		JButton yBtn = new JButton("同意");
		JButton nBtn = new JButton("删除");
		uBox.add(ulabel);
		uBox.add(Box.createHorizontalStrut(10));
		uBox.add(uField);
		uBox.add(Box.createHorizontalStrut(10));
		uBox.add(yBtn);
		uBox.add(Box.createHorizontalStrut(10));
		uBox.add(nBtn);
		
		frame.add(uBox,BorderLayout.SOUTH);
		
		//同意就加在员工表中
		yBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				
				String id=uField.getText();
				try {
					Apply ap = Data.getAp().search(id);
					if(ap==null)
					{
						JOptionPane.showMessageDialog(new JFrame(),"增加失败,请查看序号是否正确");
					}
					else 
					{
						if(ap.getJob().equals("服务员"))
						{
							Employee now = new Employee();
							int a=0;
							char []c=id.toCharArray();
							int len=c.length;
							for(int i=0;i<len;i++)
							{
								a=a*10+(c[i]-'0');
							}
							while(Data.getEm().search("YG"+a)!=null)
							{
								a++;
							}
							now.setId("YG"+a);
							now.setName(ap.getName());
							now.setSex(ap.getSex());
							now.setPasswd("123");
							now.setWages(ap.getWages());
							Data.pay+=ap.getWages();
							now.setMaid(ManagerLoginListener.getId());
							Data.getEm().addone(now);
							JOptionPane.showMessageDialog(new JFrame(),"招聘成功\nID为YG"+a+"\n密码为123");
						}
						else
						{
							Takeout now = new Takeout();
							int a=0;
							char []c=id.toCharArray();
							int len=c.length;
							for(int i=0;i<len;i++)
							{
								a=a*10+(c[i]-'0');
							}
							while(Data.getTa().search("WMY"+a)!=null)
							{
								a++;
							}
							now.setId("WMY"+a);
							now.setName(ap.getName());
							now.setSex(ap.getSex());
							now.setPasswd("123");
							now.setWages(ap.getWages());
							Data.pay+=ap.getWages();
							now.setMaid(ManagerLoginListener.getId());
							Data.getTa().addone(now);
							JOptionPane.showMessageDialog(new JFrame(),"招聘成功\nID为WMY"+a+"\n密码为123");
						}
						
						Data.getAp().delete(id);
					}
					
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
			
		});
		
		//删除监听
		nBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String id=uField.getText();
				try {
					Apply ap = Data.getAp().search(id);
					if(ap==null)
					{
						JOptionPane.showMessageDialog(new JFrame(),"删除失败,请查看序号是否正确");
					}
					else 
					{
						Data.getAp().delete(id);
						JOptionPane.showMessageDialog(new JFrame(),"删除成功");
					}
					
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		
		JTable table=new JTable(td,top);
        contentPane.add(new JScrollPane(table));
        frame.setVisible(true);
	}

}
