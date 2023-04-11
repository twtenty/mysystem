package Face;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Mod.Data;
import Mod.MaFunction;
import System.Manager;

public class Key_MaListener implements KeyListener{
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		if(e.getKeyChar() == KeyEvent.VK_ENTER )
		{
			ManagerLoginListener.setPasswd(new String(ManagerLogin.getpField().getPassword()));
//			System.out.println(uField.getText()+"\n"+passwd);
			ManagerLoginListener.setId(ManagerLogin.getuField().getText());
			Manager now=null;
			try {
				now = Data.getMa().search(ManagerLoginListener.getId());
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			if(now==null)
			{
//				label.setForeground(Color.red);
//				label.setFont(new Font("楷体",Font.BOLD,18));
//				label.setText("账号错误，登陆失败");
				JOptionPane.showMessageDialog(new JFrame(), "账号错误,登录失败");
					
			}
			else if(now.getPasswd().equals(ManagerLoginListener.getPasswd())==false)
			{
				ManagerLogin.getLabel().setForeground(Color.red);
				ManagerLogin.getLabel().setFont(new Font("楷体",Font.BOLD,18));
				ManagerLogin.getLabel().setText("密码错误，登陆失败");
//				JOptionPane.showMessageDialog(new JFrame(), "密码错误,登录失败");
			}
			else
			{
				try {
					ManagerLoginListener.setMaa(Data.getMa().search(ManagerLoginListener.getId()));
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				ManagerLogin.getLabel().setFont(new Font("楷体",Font.BOLD,18));
				MaFunction.main(null);
				ManagerLogin.getLabel().setText("登陆成功");
				ManagerLogin.getpField().setText(null);
			}
		}
	}


}
