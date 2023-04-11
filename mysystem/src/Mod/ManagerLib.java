package Mod;
import java.sql.*;
import java.util.Date;

import System.Depart;
import System.Manager;

public class ManagerLib extends Library<Manager>{
	
	public ManagerLib() {
		super();
	}

	@Override
	public Manager delete(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Manager c:this.getObjects())
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
		String sql = "select * from manager where maid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Manager x = new Manager();
		if(re.next()==false)
		{
			return null;
		}
		x.setName(re.getString("name"));
		x.setId(id);
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
		String emsql = "select * from employee where maid = '"+id+"'";
		ResultSet emre = sta.executeQuery(emsql);
		Depart now = new Depart();
		while(emre.next())
		{
			now.setDate(new Date());
			now.setId(emre.getString("emid"));
			now.setName(emre.getString("name"));
			now.setPasswd(emre.getString("passwd"));
			now.setReason("老板辞职了");
			Data.getEm().delete(now.getId());
			Data.getDe().addone(now);
		}
//		System.out.println("1"+sql);

		Data.emgetnum();
//		System.out.println("2"+sql);
		String tasql = "select * from takeout where maid = '"+id+"'";
		ResultSet tare = sta.executeQuery(tasql);

		while(tare.next())
		{
			now.setDate(new Date());
			now.setId(tare.getString("taid"));
			now.setName(tare.getString("name"));
			now.setPasswd(tare.getString("passwd"));
			now.setReason("老板辞职了");
			Data.getTa().delete(now.getId());
			Data.getDe().addone(now);
		}
		
		Data.tagetnum();
		sql = "delete from manager where maid = '"+id+"'";
//		System.out.println("3"+sql);
		sta.execute(sql);
		Data.magetnum();
//		sta.close();
		return x;
	}

	@Override
	public Manager search(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Manager c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				return c;
//			}
//		}
//		return null;
//		System.out.println(id);
		String sql = "select * from manager where maid = '"+id+"'";
//		if(Data.conn==null) return null;
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Manager x = new Manager();
		if(re.next()==false)
		{
			return null;
		}
//		System.out.println(id);
		x.setName(re.getString("name"));
		x.setId(id);
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
//		sta.close();
		return x;
	}


	@Override
	public boolean edit(Manager t1, Manager t2) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Manager c:this.getObjects())
//		{
//			if(c.equals(t1))
//			{
//				this.getObjects().remove(c);
//				this.getObjects().add(t2);
//				return true;
//			}
//		}
//		return false;
		String sql = "select * from manager where maid = '"+t1.getId()+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		boolean x = false;
		if(re.next()==false)
		{
//			sta.close();
			return false;
		}
		if(t2.getId().equals(t1.getId())==false)
		{
			addone(t2);
			sql = "update employee set maid ='"+t2.getId()+"' where maid = '"+t1.getId()+"'";
			x = sta.execute(sql);
			sql = "update takeout set maid ='"+t2.getId()+"' where maid = '"+t1.getId()+"'";
			x = sta.execute(sql);
			delete(t1.getId());
		}
		else
		{
			if(t2.getName().equals(t1.getName())==false)
			{
				sql = "update manager set name ='"+t2.getName()+"' where maid = '"+t1.getId()+"'";
				x = sta.execute(sql);
			}
			if(t2.getPasswd().equals(t1.getPasswd())==false)
			{
				sql = "update manager set passwd ='"+t2.getPasswd()+"' where maid = '"+t1.getId()+"'";
				x = sta.execute(sql);
			}
			if(t2.getSex().equals(t1.getSex())==false)
			{
				sql = "update manager set sex ='"+t2.getSex()+"' where maid = '"+t1.getId()+"'";
				x = sta.execute(sql);
			}
		}
		
//		sta.close();
		return x;
	}

	
	@Override
	public boolean addone(Manager t) throws SQLException {
		// TODO 自动生成的方法存根
		if(search(t.getId())!=null)
		{
			return false;
		}
		String sql = "INSERT\r\n"
				+ "into manager\r\n"
				+ "values('"+t.getId()+"','"+t.getName()+"','"
				+t.getPasswd()+"','"+t.getSex()+"')";
		Statement sta =  Data.conn.createStatement();
		boolean re = sta.execute(sql);
		Data.magetnum();
//		sta.close();
		return re;
	}
	
	

//	@Override
//	public Manager search(int order) throws SQLException {
//		// TODO 自动生成的方法存根
////		for(Manager c:this.getObjects())
////		{
////			if(c.getId().equals(id))
////			{
////				return c;
////			}
////		}
//		String sql = "select * from Manager where maorder = '"+order+"'";
//		if(Data.conn==null) return null;
//		Statement sta =  Data.conn.createStatement();
//		ResultSet re = sta.executeQuery(sql);
//		Manager x = new Manager();
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return null;
//		}
//		x.setName(re.getString("name"));
//		x.setId(re.getString("maid"));
//		x.setPasswd(re.getString("passwd"));
//		sta.close();
//		re.close();
//		return x;
//	}
	
//	@Override
//	public boolean edit(int order1,int order2) throws SQLException {
//
//		String sql = "select * from manager where maorder = '"+order1+"'";
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
//		sql = "update manager set maorder ='"+order2+"' where maorder = '"+order1+"'";
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
//			FileOutputStream fos = new FileOutputStream("num/Manum.txt");		
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
//		FileInputStream fis = new FileInputStream("num/Manum.txt");
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
