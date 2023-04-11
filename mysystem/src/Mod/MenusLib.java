package Mod;
import java.sql.*;
import System.*;

public class MenusLib extends Library<Menus>{
	
	public MenusLib() {
		super();
	}

	@Override
	public Menus delete(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Menus c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				this.getObjects().remove(c);
//				this.setNum(this.getNum()-1);
//				return c;
//			}
//		}
		String sql = "select * from Menus where menu = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Menus x = new Menus();
		if(re.next()==false)
		{
			return null;
		}
		x.setMenu(re.getString("menu"));
		x.setPrice(re.getDouble("price"));
		sql = "delete from Menus where menu = '"+id+"'";
		sta.execute(sql);
		Data.megetnum();
//		sta.close();
		return x;
		
	}

	@Override
	public Menus search(String name) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Menus c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				return c;
//			}
//		}
		String sql = "select * from Menus where menu = '"+name+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Menus x = new Menus();
		if(re.next()==false)
		{
//			sta.close();
			return null;
		}
		x.setMenu(re.getString("menu"));
		x.setPrice(re.getDouble("price"));
//		sta.close();
		return x;
	}

	
	@Override
	public boolean edit(Menus t1, Menus t2) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Menus c:this.getObjects())
//		{
//			if(c.equals(t1))
//			{
//				this.getObjects().remove(c);
//				this.getObjects().add(t2);
//				return true;
//			}
//		}
//		return false;
		String sql = "select * from Menus where menu = '"+t1.getMenu()+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		boolean x = false;
		if(re.next()==false)
		{
//			sta.close();
			return false;
		}
		if(t1.getMenu().equals(t2.getMenu())==false)
		{
			sql = "update Menus set name ='"+t2.getMenu()+"' where meid = '"+t1.getMenu()+"'";
			x = sta.execute(sql);
		}
		if(t1.getPrice()==t2.getPrice())
		{
			sql = "update Menus set passwd ='"+t2.getPrice()+"' where meid = '"+t2.getMenu()+"'";
			x = sta.execute(sql);
		}
//		sta.close();
		return x;
	}


	@Override
	public boolean addone(Menus t) throws SQLException {
		// TODO 自动生成的方法存根
		if(search(t.getMenu())!=null)
		{
			return false;
		}
		String sql = "INSERT\r\n"
				+ "into Menus\r\n"
				+ "values('"+t.getMenu()+"','"
				+t.getPrice()+"')";
		Statement sta =  Data.conn.createStatement();
		sta.execute(sql);
		Data.megetnum();
//		sta.close();
		return true;
	}

//	@Override
//	public Menus search(int order) throws SQLException {
//		// TODO 自动生成的方法存根
////		for(Menus c:this.getObjects())
////		{
////			if(c.getId().equals(id))
////			{
////				return c;
////			}
////		}
//		String sql = "select * from Menus where meorder = '"+order+"'";
//		if(Data.conn==null) return null;
//		Statement sta =  Data.conn.createStatement();
//		ResultSet re = sta.executeQuery(sql);
//		Menus x = new Menus();
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return null;
//		}
//		x.setMenu(re.getString("menu"));
//		x.setId(re.getString("meid"));
//		x.setPrice(re.getDouble("price"));
//		sta.close();
//		re.close();
//		return x;
//	}
//	
	
//	@Override
//	public boolean edit(int order1,int order2) throws SQLException {
//
//		String sql = "select * from menus where meorder = '"+order1+"'";
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
//		sql = "update menus set meorder ='"+order2+"' where meorder = '"+order1+"'";
//		x = sta.execute(sql);
//		String id1 = "菜品"+order1;
//		String id2 = "菜品"+order2;
//		sql = "update menus set meid ='"+id2+"' where meid = '"+id1+"'";
//		x = sta.execute(sql);
//		re.close();
//		sta.close();
//		return x;
//	}
//	
//	
//	@Override
//	public boolean exportData(int t) throws IOException {
//		// TODO 自动生成的方法存根
//		try {
//			FileOutputStream fos = new FileOutputStream("num/Menum.txt");		
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
//		FileInputStream fis = new FileInputStream("num/Menum.txt");
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
//	@Override
//	public boolean addAll(Vector<Menus> t, int num) {
//		// TODO 自动生成的方法存根
//		return false;
//	}
	
}
