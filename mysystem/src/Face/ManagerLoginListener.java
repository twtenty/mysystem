package Face;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import Mod.*;
import System.Manager;

public class ManagerLoginListener implements ActionListener{

	private static String id = new String();
	private static String passwd = new String();
	private static Manager maa = new Manager();
	
	
	public static String getId() {
		return id;
	}


	public static void setId(String iid) {
		id = iid;
	}


	public static String getPasswd() {
		return passwd;
	}


	public static void setPasswd(String ppasswd) {
		passwd = ppasswd;
	}


	public static Manager getMaa() {
		return maa;
	}


	public static void setMaa(Manager ma) {
		maa = ma;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		passwd = new String(ManagerLogin.getpField().getPassword());
//		System.out.println(uField.getText()+"\n"+passwd);
		id=ManagerLogin.getuField().getText();
		Manager now=null;
		try {
			now = Data.getMa().search(id);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		if(now==null)
		{
//			label.setForeground(Color.red);
//			label.setFont(new Font("楷体",Font.BOLD,18));
//			label.setText("账号错误，登陆失败");
			JOptionPane.showMessageDialog(new JFrame(), "账号错误,登录失败");
				
		}
		else if(now.getPasswd().equals(passwd)==false)
		{
			ManagerLogin.getLabel().setForeground(Color.red);
			ManagerLogin.getLabel().setFont(new Font("楷体",Font.BOLD,18));
			ManagerLogin.getLabel().setText("密码错误，登陆失败");
//			JOptionPane.showMessageDialog(new JFrame(), "密码错误,登录失败");
		}
		else
		{
			try {
				maa=Data.getMa().search(id);
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
