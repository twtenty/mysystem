package Mod;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Face.CustomerLoginListener;

public class CudeListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		try {
			Data.getCu().delete(CustomerLoginListener.getId());
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(new JFrame(), "注销成功");
	}

}
