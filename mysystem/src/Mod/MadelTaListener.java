package Mod;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import java.sql.SQLException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import System.Depart;
import Tool.BackGroundPanel;
import Tool.ScreenUtils;

public class MadelTaListener implements ActionListener{

	
	JFrame MadelTaListener = new JFrame("解雇外卖员");
	
	final int WIDTH = 300;
	final int HEIGHT = 200;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		MadelTaListener.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,
				(ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
		
		BackGroundPanel bgPanel = null;
		try {
			bgPanel = new BackGroundPanel(ImageIO.read(new File("images/23.jfif")));
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		//建立垂直元素(用户名，密码，登录，注册等)
		Box vBox = Box.createVerticalBox();
				
		//菜名与输入框水平
		Box uBox = Box.createHorizontalBox();
		uBox.add(Box.createHorizontalStrut(20));
		JLabel uLabel = new JLabel("外卖员ID:");
		JTextField uField = new JTextField(10);//输入框
		uField.setText("WMY");
		uBox.add(uLabel);
		uBox.add(Box.createHorizontalStrut(10));//水平间隔
		uBox.add(uField);
		uBox.add(Box.createHorizontalStrut(10));
		
		Box pBox = Box.createHorizontalBox();
		pBox.add(Box.createHorizontalStrut(20));
		JLabel pLabel = new JLabel("解雇原因:");
		JTextField pField = new JTextField(10);//输入框
		pBox.add(pLabel);
		pBox.add(Box.createHorizontalStrut(10));//水平间隔
		pBox.add(pField);
		pBox.add(Box.createHorizontalStrut(10));
		
		Box sBox = Box.createHorizontalBox();
		sBox.add(Box.createHorizontalStrut(20));
		JLabel label = new JLabel("");
		sBox.add(label);
		
		Box dBox = Box.createHorizontalBox();
		dBox.add(Box.createHorizontalStrut(30));
		
		JButton dBtn = new JButton("解雇");
		dBox.add(dBtn);
		
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(uBox);
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(pBox);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(sBox);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(dBox);
		
		dBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String id = uField.getText();
				String reason = pField.getText();
				try {
					if(Data.getTa().search(id)==null)
					{
						label.setText("账号错误");
					}
					else
					{
						Depart now = new Depart();
						now.setId(id);
						now.setName(Data.getTa().search(id).getName());
						now.setPasswd(Data.getTa().search(id).getPasswd());
						Data.pay-=Data.getTa().search(id).getWages();
						try {
							Data.exportPay(Data.pay);
							Data.exportIncome(Data.income);
						} catch (IOException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
						now.setReason(reason);
						Date date = new Date();
						now.setDate(date);
						Data.getTa().delete(id);
						Data.getDe().addone(now);
						JOptionPane.showMessageDialog(new JFrame(),"解雇成功");
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			
		});
		
		bgPanel.add(vBox);
		MadelTaListener.add(bgPanel);
		MadelTaListener.setVisible(true);
	}
	
}
