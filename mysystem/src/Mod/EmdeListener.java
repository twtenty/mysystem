package Mod;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Face.EmployeeLogin;
import System.Depart;

public class EmdeListener implements ActionListener{

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
			now.setId(EmployeeLogin.getId());
			now.setName(EmployeeLogin.getEmm().getName());
			now.setPasswd(EmployeeLogin.getPasswd());
			now.setReason(reason);
			now.setDate(new Date());
			try {
				if(Data.getEm().delete(EmployeeLogin.getId())==null)
				{
					JOptionPane.showMessageDialog(new JFrame(),"离职失败");
				}
				else 
				{
					Data.getDe().addone(now);
					Data.pay-=EmployeeLogin.getEmm().getWages();
					try {
						Data.exportPay(Data.pay);
						Data.exportIncome(Data.income);
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(new JFrame(),"离职成功");
				}
				
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		
	}
	
}
