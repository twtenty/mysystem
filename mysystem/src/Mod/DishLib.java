package Mod;
import java.sql.*;
import System.*;

public class DishLib extends Library<Dish>{
	
	public DishLib() {
		super();
	}

	
	@Override
	public Dish delete(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Dish c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				this.getObjects().remove(c);
//				this.setNum(this.getNum()-1);
//				return c;
//			}
//		}
		String sql = "select * from Dish where dishid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Dish x = new Dish();
		if(re.next()==false)
		{
			return null;
		}
		x.setMe(re.getString("me"));
		x.setId(id);
		x.setPrice(re.getDouble("price"));
		sql = "delete from Dish where dishid = '"+id+"'";
		sta.execute(sql);
		Data.digetnum();
//		sta.close();
		return x;
		
	}

	@Override
	public Dish search(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Dish c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				return c;
//			}
//		}
		String sql = "select * from Dish where dishid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();

		ResultSet re = sta.executeQuery(sql);
		Dish x = new Dish();
		if(re.next()==false)
		{
//			sta.close();
			return null;
		}
		x.setMe(re.getString("me"));
		x.setId(id);
		x.setPrice(re.getDouble("price"));
//		sta.close();
		return x;
	}

	
	@Override
	public boolean edit(Dish t1, Dish t2) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Dish c:this.getObjects())
//		{
//			if(c.equals(t1))
//			{
//				this.getObjects().remove(c);
//				this.getObjects().add(t2);
//				return true;
//			}
//		}
//		return false;
		String sql = "select * from Dish where dishid = '"+t1.getId()+"'";
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
			
		}
		if(t1.getMe().equals(t2.getMe())==false)
		{
			sql = "update dish set me ='"+t2.getMe()+"' where dishid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
		if(t1.getPrice()==t2.getPrice())
		{
			sql = "update dish set passwd ='"+t2.getPrice()+"' where dishid = '"+t2.getId()+"'";
			x = sta.execute(sql);
		}
//		sta.close();
		return x;
	}

	
	@Override
	public boolean addone(Dish t) throws SQLException {
		// TODO 自动生成的方法存根
		if(search(t.getId())!=null)
		{
			return false;
		}
		String sql = "INSERT\r\n"
				+ "into Dish\r\n"
				+ "values('"+t.getId()+"','"+t.getMe()+"','"
				+t.getPrice()+"','"+t.getorid()+"')";
//		System.out.println(sql);
		Statement sta =  Data.conn.createStatement();
		Data.digetnum();
		boolean re = sta.execute(sql);
//		sta.close();
		return re;
	}
	
//	@Override
//	public Dish search(int order) throws SQLException {
//		// TODO 自动生成的方法存根
////		for(Dish c:this.getObjects())
////		{
////			if(c.getId().equals(id))
////			{
////				return c;
////			}
////		}
//		String sql = "select * from Dish where diorder = '"+order+"'";
//		if(Data.conn==null) return null;
//		Statement sta =  Data.conn.createStatement();
//
//		ResultSet re = sta.executeQuery(sql);
//		Dish x = new Dish();
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return null;
//		}
//		x.setId(re.getString("dishid"));
//		x.setMe(re.getString("me"));
//		x.setPrice(re.getDouble("price"));
//		x.setorid(re.getString("orid"));
//		sta.close();
//		re.close();
//		return x;
//	}
//	

//	@Override
//	public boolean addAll(Vector<Dish> t, int num) {
//		// TODO 自动生成的方法存根
//		return false;
//	}
	
//	@Override
//	public boolean edit(int order1,int order2) throws SQLException {
//
//		String sql = "select * from dish where diorder = '"+order1+"'";
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
//		sql = "update dish set diorder ='"+order2+"' where diorder = '"+order1+"'";
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
//			FileOutputStream fos = new FileOutputStream("num/Dinum.txt");		
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
//		FileInputStream fis = new FileInputStream("num/Dinum.txt");
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
