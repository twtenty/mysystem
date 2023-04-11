package Face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String command = e.getActionCommand();	
		
		try {
			switch(command)
			{
				case "管理员系统":
					new ManagerLogin().init();
					break;
				case "服务员系统":
					new EmployeeLogin().init();
					break;
				case "外卖员系统":
					new TakeoutLogin().init();
					break;
				case "顾    客系统":
					new CustomerLogin().init();
					break;
				case "员  工 应 聘":
					new ApplyRegister().init();
				default : break;
			}
			
		} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
		}
		
	}
}
