package Mod;
import java.sql.*;
import java.text.*;
import System.*;

public class DepartLib extends Library<Depart>{
	
	public DepartLib() {
		super();
	}

	@Override
	public Depart delete(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Depart c:this.getObjects())
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
		String sql = "select * from depart where cuid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Depart x = new Depart();
		if(re.next()==false)
		{
			return null;
		}
		x.setName(re.getString("name"));
		x.setId(id);
		x.setReason(re.getString("reason"));
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			java.util.Date parse_date = sdf.parse(re.getString("date"));
			x.setDate(parse_date);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		sql = "delete from depart where deid = '"+id+"'";
		sta.execute(sql);
		Data.degetnum();
//		sta.close();
		return x;
	}

	@Override
	public Depart search(String id) throws SQLException {
//		// TODO 自动生成的方法存根
//		for(Depart c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				return c;
//			}
//		}
//		return null;
		String sql = "select * from depart where deid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Depart x = new Depart();
		if(re.next()==false)
		{
			return null;
		}
		x.setName(re.getString("name"));
		x.setId(id);
		x.setReason(re.getString("reason"));
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			java.util.Date parse_date = sdf.parse(re.getString("date"));
			x.setDate(parse_date);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		re=sta.executeQuery(sql);
//		sta.close();
		return x;
	}
	

	@Override
	public boolean edit(Depart t1, Depart t2) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "select * from depart where deid = '"+t1.getId()+"'";
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
			sql = "update depart set deid ='"+t2.getId()+"' where deid = '"+t1.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getName().equals(t2.getName())==false)
		{
			sql = "update depart set name ='"+t2.getName()+"' where deid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getPasswd().equals(t2.getPasswd())==false)
		{
			sql = "update depart set passwd ='"+t2.getPasswd()+"' where deid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getReason().equals(t2.getReason())==false)
		{
			sql = "update depart set flag ='"+t2.getReason()+"' where deid = '"+t2.getReason()+"'";
			x = sta.execute(sql);
		}
		if(t1.getSex().equals(t2.getSex())==false)
		{
			sql = "update depart set sex ='"+t2.getSex()+"' where deid = '"+t2.getReason()+"'";
			x = sta.execute(sql);
		}
		if(t1.getDate().compareTo(t2.getDate())==1)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String birthday = df.format(t2.getDate());//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
			sql = "update depart set date ='"+birthday+"' where deid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
//		sta.close();
		return x;
	}

//	@Override
//	public Depart look(int x) {
//		// TODO 自动生成的方法存根
//		return this.getObjects(x);
//	}

	

	@Override
	public boolean addone(Depart t) throws SQLException {
		// TODO 自动生成的方法存根
		if(search(t.getId())!=null)
		{
			return false;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birthday = df.format(t.getDate());
//		System.out.println("addone "+birthday);
		String sql = "INSERT\r\n"
				+ "into depart\r\n"
				+ "values('"+t.getId()+"','"+t.getName()+"','"
				+t.getPasswd()+"','"+t.getSex()+"','"+t.getReason()+"','"+birthday+"')";
		Statement sta =  Data.conn.createStatement();
		boolean re = sta.execute(sql);
		Data.degetnum();
//		sta.close();
		return re;
	}
	
	
//	@Override
//	public Depart search(int order) throws SQLException {
//		// TODO 自动生成的方法存根
////		for(Depart c:this.getObjects())
////		{
////			if(c.getId().equals(id))
////			{
////				return c;
////			}
////		}
//		String sql = "select * from Depart where deorder = '"+order+"'";
//		if(Data.conn==null) return null;
//		Statement sta =  Data.conn.createStatement();
//		ResultSet re = sta.executeQuery(sql);
//		Depart x = new Depart();
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return null;
//		}
//		x.setName(re.getString("name"));
//		x.setId(re.getString("deid"));
//		x.setReason(re.getString("reason"));;
//		x.setPasswd(re.getString("passwd"));
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try {
//			java.util.Date parse_date = sdf.parse(re.getString("date"));
//			x.setDate(parse_date);
//		} catch (ParseException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		sta.close();
//		re.close();
//		return x;
//	}
	
//	@Override
//	public boolean edit(int order1,int order2) throws SQLException {
//
//		String sql = "select * from depart where deorder = '"+order1+"'";
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
//		sql = "update depart set deorder ='"+order2+"' where deorder = '"+order1+"'";
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
//			FileOutputStream fos = new FileOutputStream("num/Denum.txt");		
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
//		FileInputStream fis = new FileInputStream("num/Denum.txt");
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
