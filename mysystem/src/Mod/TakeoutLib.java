package Mod;
import java.sql.*;
import System.*;

public class TakeoutLib extends Library<Takeout>{
	
	public TakeoutLib() {
		super();
	}
	
	@Override
	public Takeout delete(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Takeout c:this.getObjects())
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
		String sql = "select * from takeout where taid = '"+id+"'";

		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Takeout x = new Takeout();
		if(re.next()==false)
		{
			return null;
		}
		x.setName(re.getString("name"));
		x.setId(id);
		x.setWages(re.getDouble("wages"));
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
		sql = "delete from takeout where taid = '"+id+"'";
		sta.execute(sql);
		Data.tagetnum();
//		sta.close();
		return x;
	}

	@Override
	public Takeout search(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Takeout c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				return c;
//			}
//		}
//		return null;
		String sql = "select * from takeout where taid = '"+id+"'";
		
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Takeout x = new Takeout();
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
	public boolean edit(Takeout t1, Takeout t2) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Takeout c:this.getObjects())
//		{
//			if(c.equals(t1))
//			{
//				this.getObjects().remove(c);
//				this.getObjects().add(t2);
//				return true;
//			}
//		}
//		return false;
		String sql = "select * from takeout where taid = '"+t1.getId()+"'";

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
			sql = "update takeout set taid ='"+t2.getId()+"' where taid = '"+t1.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getName().equals(t2.getName())==false)
		{
			sql = "update takeout set name ='"+t2.getName()+"' where taid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getPasswd().equals(t2.getPasswd())==false)
		{
			sql = "update takeout set passwd ='"+t2.getPasswd()+"' where taid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getSex().equals(t2.getSex())==false)
		{
			sql = "update takeout set sex ='"+t2.getSex()+"' where taid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getWages()!=t2.getWages())
		{
			sql = "update takeout set wages ='"+t2.getWages()+"' where taid = '"+t2.getId()+"'";
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

//	@Override
//	public Takeout look(int x) {
//		// TODO 自动生成的方法存根
//		return this.getObjects(x);
//	}

	
	@Override
	public boolean addone(Takeout t) throws SQLException {
		// TODO 自动生成的方法存根
		if(search(t.getId())!=null)
		{
			return false;
		}
		String sql = "INSERT\r\n"
				+ "into takeout\r\n"
				+ "values('"+t.getId()+"','"+t.getName()+"','"
				+t.getPasswd()+"','"+t.getSex()+"','"+t.getWages()+"','"+t.getMaid()+"')";

		Statement sta =  Data.conn.createStatement();
		boolean re = sta.execute(sql);
//		sta.close();
		return re;
	}
	
	
//	@Override
//	public Takeout search(int order) throws SQLException {
//		// TODO 自动生成的方法存根
////		for(Takeout c:this.getObjects())
////		{
////			if(c.getId().equals(id))
////			{
////				return c;
////			}
////		}
//		String sql = "select * from Takeout where taorder = '"+order+"'";
//		if(Data.conn==null) return null;
//		Statement sta =  Data.conn.createStatement();
//		ResultSet re = sta.executeQuery(sql);
//		Takeout x = new Takeout();
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return null;
//		}
//		x.setName(re.getString("name"));
//		x.setId(re.getString("taid"));
//		x.setWages(re.getDouble("wages"));
//		x.setPasswd(re.getString("passwd"));
//		sta.close();
//		re.close();
//		return x;
//	}
//	
	
//	@Override
//	public boolean edit(int order1,int order2) throws SQLException {
//
//		String sql = "select * from takeout where taorder = '"+order1+"'";
//		if(Data.conn==null) return false;
//		Statement sta =  Data.conn.createStatement();
//		ResultSet re = sta.executeQuery(sql);
//		boolean x = true;
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return false;
//		}
//		sql = "update takeout set taorder ='"+order2+"' where taorder = '"+order1+"'";
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
//			FileOutputStream fos = new FileOutputStream("num/Tanum.txt");		
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
//		FileInputStream fis = new FileInputStream("num/Tanum.txt");
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
//	
}
