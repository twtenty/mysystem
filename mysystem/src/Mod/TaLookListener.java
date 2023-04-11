package Mod;

import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class TaLookListener implements ActionListener{

	final int WIDTH = 800;
	final int HEIGHT = 450;
	
	private static String cuid = null;	
	
	public static String getCuid() {
		return cuid;
	}

	public static void setCuid(String cuid) {
		TaLookListener.cuid = cuid;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame jf = new JFrame("顾客表");
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/32.jfif")));
			jf.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		Container contentPane=jf.getContentPane();
		 String[] top={"顾客ID","顾客姓名","是否在消费","性别"};
	        try {
				Data.cugetnum();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
	        
	        //建立二维数组，存储应聘人员信息
	        Object[][] td=new Object[Data.getNumcu()][4];
	        int len=0;
	    
	    JPanel panel=new JPanel();
		JLabel uLabel = new JLabel("请选择顾客:");
		JComboBox<String> cmb=new JComboBox<String>();//下拉菜单窗口
		cmb.addItem("请选择");
	    
	    String sql = "select * from customer ";
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
//				System.out.println(1);
//				System.out.println(re.getString("emid"));
				
				String cuuid = re.getString("cuid");
				
				td[len][0]=cuuid;
				td[len][1]=re.getString("name");
				td[len][3]=re.getString("sex");
				
				if(re.getInt("flag")==1)
				{
					td[len++][2]="正在消费";
					cmb.addItem(cuuid);
				}
				else
				{
					td[len++][2]=" ";
				}

			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		JTable table=new JTable(td,top);
		contentPane.add(new JScrollPane(table));
		
		JButton DeBtn = new JButton("查询订单");
		
		panel.add(uLabel);
		panel.add(Box.createHorizontalStrut(30));
		panel.add(cmb,BorderLayout.CENTER);
		panel.add(Box.createHorizontalStrut(30));
		panel.add(DeBtn);
		
		//按钮监听
		cmb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				 cuid=e.getItem().toString();
			}
			
		}); 
		
		DeBtn.addActionListener(new TaLoCuorListener());
		
		contentPane.add(panel,BorderLayout.SOUTH);
		
		jf.setVisible(true);
	}

}
