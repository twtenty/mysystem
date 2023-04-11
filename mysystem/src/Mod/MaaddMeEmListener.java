package Mod;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import System.Menus;
import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class MaaddMeEmListener implements ActionListener{

	JFrame MaaddMeEmListener = new JFrame("增加菜品");
	
	final int WIDTH = 300;
	final int HEIGHT = 200;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		MaaddMeEmListener.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		BackGroundPanel bgPanel = null;
		try {
			bgPanel = new BackGroundPanel(ImageIO.read(new File("images/17.jfif")));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		//建立垂直元素(用户名，密码，登录，注册等)
		Box vBox = Box.createVerticalBox();
				
		//菜名与输入框水平
		Box uBox = Box.createHorizontalBox();
		uBox.add(Box.createHorizontalStrut(30));
		JLabel uLabel = new JLabel("菜名:");
		JTextField uField = new JTextField(5);//输入框
		uBox.add(uLabel);
		uBox.add(Box.createHorizontalStrut(10));//水平间隔
		uBox.add(uField);
		uBox.add(Box.createHorizontalStrut(30));
		
		Box pBox = Box.createHorizontalBox();
		pBox.add(Box.createHorizontalStrut(30));
		JLabel pLabel = new JLabel("价格:");
		JTextField pField = new JTextField(5);//输入框
		pBox.add(pLabel);
		pBox.add(Box.createHorizontalStrut(10));//水平间隔
		pBox.add(pField);
		pBox.add(Box.createHorizontalStrut(30));
		
		Box aBox = Box.createHorizontalBox();
		aBox.add(Box.createHorizontalStrut(30));
		
		JButton addBtn = new JButton("添加");
		aBox.add(addBtn);
		
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(uBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(pBox);
		vBox.add(Box.createVerticalStrut(20));
		vBox.add(aBox);
		
		bgPanel.add(vBox);
		MaaddMeEmListener.add(bgPanel);
		
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String name = uField.getText();
				char[] price = pField.getText().toCharArray();
				double a=0,b=0;
				int len=pField.getText().length();
				int idx=len;//记录小数点位置，先默认没有小数点
				for(int i=0;i<len;i++)
				{
					if(price[i]=='.')
					{
						idx=i;
						break;
					}
					a=a*10+(price[i]-'0');
				}
				for(int i=len-1;i>idx;i--)
				{
					b=b*0.1+(price[i]-'0');
				}
//				System.out.println(name+"  "+a+b*0.1);
				try {
					if(name.equals("")==false&&pField.getText().equals("")==false&&Data.getMe().addone(new Menus(name,a+b*0.1))==true)
					{
						JOptionPane.showMessageDialog(new JFrame(), "添加成功");
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame(), "添加失败");
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		
		MaaddMeEmListener.setVisible(true);
	}

}
