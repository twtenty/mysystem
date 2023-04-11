package Mod;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Face.TakeoutLogin;
import System.Depart;

public class TadeListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String reason=JOptionPane.showInputDialog("请输入离职原因:","");
		if(reason==null) 
		{
			JOptionPane.showMessageDialog(new JFrame(),"离职失败");
		}
		else 
		{
			Depart now = new Depart();
			now.setId(TakeoutLogin.getId());
			now.setName(TakeoutLogin.getTaa().getName());
			now.setPasswd(TakeoutLogin.getPasswd());
			now.setReason(reason);
			now.setDate(new Date());
			try {
				if(Data.getTa().delete(TakeoutLogin.getId())==null)
				{
					JOptionPane.showMessageDialog(new JFrame(),"离职失败");
				}
				else 
				{
					Data.getDe().addone(now);
					Data.pay-=TakeoutLogin.getTaa().getWages();
					
					JOptionPane.showMessageDialog(new JFrame(),"离职成功");
				}
				
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		
	}
	
}
