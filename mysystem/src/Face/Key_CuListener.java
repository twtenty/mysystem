package Face;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Mod.CuFunction;
import Mod.Data;

public class Key_CuListener implements KeyListener{

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
			CustomerLoginListener.setPasswd(new String(CustomerLogin.getpField().getPassword()));
			CustomerLoginListener.setId(CustomerLogin.getuField().getText());
			try {
				CustomerLoginListener.setCuu(Data.getCu().search(CustomerLoginListener.getId()));
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
//			System.out.println(uField.getText()+"\n"+passwd);
			
			if(CustomerLoginListener.getCuu()==null)
			{
//				CustomerLogin.label.setForeground(Color.red);
//				CustomerLogin.label.setFont(new Font("楷体",Font.BOLD,18));
//				CustomerLogin.label.setText("账号错误，登陆失败");
				JOptionPane.showMessageDialog(new JFrame(), "账号错误,登录失败");	
			}
			else if(CustomerLoginListener.getCuu().getPasswd().equals(CustomerLoginListener.getPasswd())==false)
			{
				CustomerLogin.getLabel().setForeground(Color.red);
				CustomerLogin.getLabel().setFont(new Font("楷体",Font.BOLD,18));
				CustomerLogin.getLabel().setText("密码错误，登陆失败");
//				JOptionPane.showMessageDialog(new JFrame(), "密码错误,登陆失败");	
			}
			else
			{
				CustomerLogin.getLabel().setFont(new Font("楷体",Font.BOLD,18));
				
				CuFunction.main(null);	
				CustomerLogin.getLabel().setText("登陆成功");
			}
		}
	}
	
}
