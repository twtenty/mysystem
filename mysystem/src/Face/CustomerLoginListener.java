package Face;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Mod.*;
import System.*;

public class CustomerLoginListener implements ActionListener{
	
	private static String id = new String();
	private static String passwd = new String();
	private static Customer cuu = new Customer();
	
	public static void setId(String iid) {
		id = iid;
		
	}

	public static String getPasswd() {
		return passwd;
	}

	public static void setPasswd(String ppasswd) {
		passwd = ppasswd;
	}

	public static Customer getCuu() {
		return cuu;
	}

	public static void setCuu(Customer cu) {
		cuu = cu;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		passwd = new String(CustomerLogin.getpField().getPassword());
		id=CustomerLogin.getuField().getText();
		try {
			cuu = Data.getCu().search(id);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
//		System.out.println(uField.getText()+"\n"+passwd);
		
		if(cuu==null)
		{
//			CustomerLogin.label.setForeground(Color.red);
//			CustomerLogin.label.setFont(new Font("楷体",Font.BOLD,18));
//			CustomerLogin.label.setText("账号错误，登陆失败");
			JOptionPane.showMessageDialog(new JFrame(), "账号错误,登录失败");	
		}
		else if(cuu.getPasswd().equals(passwd)==false)
		{
			CustomerLogin.getLabel().setForeground(Color.red);
			CustomerLogin.getLabel().setFont(new Font("楷体",Font.BOLD,18));
			CustomerLogin.getLabel().setText("密码错误，登陆失败");
//			JOptionPane.showMessageDialog(new JFrame(), "密码错误,登陆失败");	
		}
		else
		{
			CustomerLogin.getLabel().setFont(new Font("楷体",Font.BOLD,18));
			
			CuFunction.main(null);	
			CustomerLogin.getLabel().setText("登陆成功");
		}
	}

	public static String getId() {
		return id;
	}

	

}
