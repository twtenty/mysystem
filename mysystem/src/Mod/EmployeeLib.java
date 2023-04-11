package Mod;
import java.sql.*;
import System.*;

public class EmployeeLib extends Library<Employee>{
	
	public EmployeeLib() {
		super();
	}
	
	
	@Override
	public Employee delete(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Employee c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				this.getObjects().remove(c);
//				this.setNum(this.getNum()-1);
//				return c;
//			}
//		}
//		
//		return null;
		String sql = "select * from employee where emid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();

		ResultSet re = sta.executeQuery(sql);
		Employee x = new Employee();
		if(re.next()==false)
		{
			return null;
		}
		x.setName(re.getString("name"));
		x.setId(id);
		x.setWages(re.getDouble("wages"));
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
		sql = "delete from employee where emid = '"+id+"'";
		sta.execute(sql);
		Data.emgetnum();
//		sta.close();
		return x;
	}

	@Override
	public Employee search(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Employee c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				return c;
//			}
//		}
//		return null;
		String sql = "select * from employee where emid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
//		if(sta==null) return null;
		ResultSet re = sta.executeQuery(sql);
		Employee x = new Employee();
		if(re.next()==false)
		{
			return null;
		}
		x.setName(re.getString("name"));
		x.setId(id);
		x.setWages(re.getDouble("wages"));
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
//		sta.close();
		return x;
	}

	@Override
	public boolean edit(Employee t1, Employee t2) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Employee c:this.getObjects())
//		{
//			if(c.equals(t1))
//			{
//				this.getObjects().remove(c);
//				this.getObjects().add(t2);
//				return true;
//			}
//		}
//		return false;
		String sql = "select * from employee where emid = '"+t1.getId()+"'";
		Statement sta =  Data.conn.createStatement();
		
		ResultSet re = sta.executeQuery(sql);
		boolean x = false;
		if(re.next()==false)
		{
//			sta.close();
			return false;
		}
		if(t1.getId().equals(t2.getId())==false)
		{
			sql = "update employee set emid ='"+t2.getId()+"' where emid = '"+t1.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getName().equals(t2.getName())==false)
		{
			sql = "update employee set name ='"+t2.getName()+"' where emid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getPasswd().equals(t2.getPasswd())==false)
		{
			sql = "update employee set passwd ='"+t2.getPasswd()+"' where emid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getSex().equals(t2.getSex())==false)
		{
			sql = "update employee set sex ='"+t2.getSex()+"' where emid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getWages()!=t2.getWages())
		{
			sql = "update employee set wages ='"+t2.getWages()+"' where emid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getMaid()!=t2.getMaid())
		{
			sql = "update employee set maid ='"+t2.getMaid()+"' where emid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
//		sta.close();
		return x;
	}


	@Override
	public boolean addone(Employee t) throws SQLException {
		// TODO 自动生成的方法存根
		if(search(t.getId())!=null)
		{
			return false;
		}
		String sql = "INSERT\r\n"
				+ "into employee\r\n"
				+ "values('"+t.getId()+"','"+t.getName()+"','"
				+t.getPasswd()+"','"+t.getSex()+"','"+t.getWages()+"','"+t.getMaid()+"')";
		Statement sta =  Data.conn.createStatement();
		Data.emgetnum();
		boolean re = sta.execute(sql);
//		sta.close();
		return re;
	}
	
//	@Override
//	public Employee search(int order) throws SQLException {
//		// TODO 自动生成的方法存根
////		for(Employee c:this.getObjects())
////		{
////			if(c.getId().equals(id))
////			{
////				return c;
////			}
////		}
//		String sql = "select * from Employee where emorder = '"+order+"'";
//		if(Data.conn==null) return null;
//		Statement sta =  Data.conn.createStatement();
//		
//		ResultSet re = sta.executeQuery(sql);
//		Employee x = new Employee();
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return null;
//		}
//		x.setName(re.getString("name"));
//		x.setId(re.getString("emid"));
//		x.setWages(re.getDouble("wages"));
//		x.setPasswd(re.getString("passwd"));
//		sta.close();
//		re.close();
//		return x;
//	}
	
//	@Override
//	public boolean edit(int order1,int order2) throws SQLException {
//
//		String sql = "select * from employee where emorder = '"+order1+"'";
//		if(Data.conn==null) return false;
//		Statement sta =  Data.conn.createStatement();
//		
//		ResultSet re = sta.executeQuery(sql);
//		boolean x = true;
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return false;
//		}
//		sql = "update employee set emorder ='"+order2+"' where emorder = '"+order1+"'";
//		x = sta.execute(sql);
//		re.close();
//		sta.close();
//		return x;
//	}
//	
//	@Override
//	public boolean exportData(int t) throws IOException {
//		// TODO 自动生成的方法存根
//		try {
//			FileOutputStream fos = new FileOutputStream("num/Emnum.txt");		
//			fos.write(t);
//			fos.close();
//		} catch (FileNotFoundException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		return true;
//	}
//
//	@Override
//	public int importData() throws IOException, ClassNotFoundException {
//		// TODO 自动生成的方法存根
//		FileInputStream fis = new FileInputStream("num/Emnum.txt");
//		int x=0;
//		try{
//			x = fis.read();
////			System.out.println(x.getName()+"  "+x.getId()+"  "+x.getPasswd());
////			Data.cu.addone(x);
//		}catch(EOFException e) {
//			e.printStackTrace();
//		}
//		fis.close();
//		return x;
//	}
}
