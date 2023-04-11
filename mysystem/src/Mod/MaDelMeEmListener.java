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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class MaDelMeEmListener implements ActionListener{

	final int WIDTH = 300;
	final int HEIGHT = 100;
	
	private static String name = null;
	
	
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		MaDelMeEmListener.name = name;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		JFrame jf = new JFrame("删除菜品");
		jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		try {
			BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images/22.jfif")));
			jf.add(bgPanel);
		} catch (IOException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		
		JPanel panel=new JPanel();
		
		JLabel uLabel = new JLabel("请选择菜名:");
		
		JComboBox<String> cmb=new JComboBox<String>();//下拉菜单窗口
		cmb.addItem("请选择");
		
		Statement sta = null;
		ResultSet re = null;
		String sql = "select * from menus ";
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
				cmb.addItem(re.getString("menu"));
			}
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		JButton DeBtn = new JButton("删除");
		
		panel.add(uLabel);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(cmb,BorderLayout.CENTER);
		panel.add(Box.createHorizontalStrut(10));
		panel.add(DeBtn);

		
		jf.add(panel);
		
		jf.setVisible(true);
		
		cmb.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				 name=e.getItem().toString();
			}
			
		}); 
		
		DeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				try {
					if(Data.getMe().delete(name)==null)
					{
						JOptionPane.showMessageDialog(new JFrame(), "删除失败,请检查菜名");
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame(), "删除成功");
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
	}

}
