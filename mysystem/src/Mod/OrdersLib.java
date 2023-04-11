package Mod;
import java.sql.*;
import System.*;

public class OrdersLib extends Library<Orders>{
	
	public OrdersLib() {
		super();
	}
	
	@Override
	public Orders delete(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Orders c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				this.getObjects().remove(c);
//				this.setNum(this.getNum()-1);
//				return c;
//			}
//		}
		String sql = "select * from orders where orid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()==false)
		{
			return null;
		}
		Orders x = new Orders();
		x.setCuid(re.getString("cuid"));
		x.setOption(re.getInt("op"));
		x.setOrid(id);
		x.setFlag(re.getInt("flag"));
		sql = "delete from dish where orid = '"+id+"'";
		sta.execute(sql);
		Data.digetnum();
		sql = "delete from orders where cuid = '"+id+"'";
		sta.execute(sql);
		Data.orgetnum();
//		sta.close();
		Data.setNumor(Data.getNumor() - 1);
		return x;
		
	}

	@Override
	public Orders search(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Orders c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				return c;
//			}
//		}
		String sql = "select * from orders where orid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Orders x = new Orders();
		if(re.next()==false)
		{
//			sta.close();
			return null;
		}
		x.setOrid(id);
		x.setOption(re.getInt("op"));
		x.setPrice(re.getDouble("price"));
		x.setFlag(re.getInt("flag"));
		x.setCuid(re.getString("cuid"));
//		sta.close();
		return x;
	}

	
	@Override
	public boolean edit(Orders t1, Orders t2) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Orders c:this.getObjects())
//		{
//			if(c.equals(t1))
//			{
//				this.getObjects().remove(c);
//				this.getObjects().add(t2);
//				return true;
//			}
//		}
//		return false;
		String sql = "select * from orders where orid = '"+t1.getOrid()+"'";

		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		boolean x = false;
//		System.out.println(t2.getPrice());
		if(re.next()==false)
		{
//			sta.close();
			return false;
		}
		if(t1.getOrid().equals(t2.getOrid())==false)
		{
			addone(t2);
			sql = "update dish set orid ='"+t2.getOrid()+"' where orid = '"+t1.getOrid()+"'";
			x = sta.execute(sql);
			delete(t1.getOrid());
		}
		else
		{
			if(t2.getPrice()!=t1.getPrice())
			{
				sql = "update orders set price ='"+t2.getPrice()+"' where orid = '"+t1.getOrid()+"'";
				x = sta.execute(sql);
			}
			if(t2.getCuid().equals(t1.getCuid())==false )
			{
				sql = "update orders set cuid ='"+t2.getCuid()+"' where orid = '"+t1.getOrid()+"'";
				x = sta.execute(sql);
			}
			if(t1.isOption()!=t2.isOption())
			{
				sql = "update orders set op ='"+t2.isOption()+"' where orid = '"+t1.getOrid()+"'";
				x = sta.execute(sql);
			}

			sql = "update orders set flag ='"+t2.isFlag()+"' where orid = '"+t1.getOrid()+"'";
			x = sta.execute(sql);

		}
//		sta.close();
		return x;
	}

	

	@Override
	public boolean addone(Orders t) throws SQLException {
		// TODO 自动生成的方法存根
		if(search(t.getOrid())!=null)
		{
			return false;
		}
		String sql = "INSERT\r\n"
				+ "into Orders\r\n"
				+ "values('"+t.getOrid()+"','"+t.getPrice()+"','"
				+t.isOption()+"','"+t.isFlag()+"','"+t.getCuid()+"')";
//		System.out.println(sql);

		Statement sta =  Data.conn.createStatement();
		boolean re = sta.execute(sql);
//		sta.close();
		return re;
	}

	
//	@Override
//	public Orders search(int order) throws SQLException {
//		// TODO 自动生成的方法存根
////		for(Orders c:this.getObjects())
////		{
////			if(c.getId().equals(id))
////			{
////				return c;
////			}
////		}
//		String sql = "select * from Orders where ororder = '"+order+"'";
//		if(Data.conn==null) return null;
//		Statement sta =  Data.conn.createStatement();
//		ResultSet re = sta.executeQuery(sql);
//		Orders x = new Orders();
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return null;
//		}
//		x.setOrid(re.getString("orid"));
//		x.setPrice(re.getDouble("price"));
//		x.setFlag(re.getInt("flag"));
//		x.setOption(re.getInt("option"));
//		x.setCuid(re.getString("cuid"));
//		sta.close();
//		re.close();
//		return x;
//	}
//	
	
//	@Override
//	public boolean edit(int order1,int order2) throws SQLException {
//
//		String sql = "select * from orders where ororder = '"+order1+"'";
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
//		sql = "update orders set ororder ='"+order2+"' where ororder = '"+order1+"'";
//		x = sta.execute(sql);
//		String id1 = "序号"+order1;
//		String id2 = "序号"+order2;
//		sql = "update orders set orid ='"+id2+"' where orid = '"+id1+"'";
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
//			FileOutputStream fos = new FileOutputStream("num/Ornum.txt");		
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
//		FileInputStream fis = new FileInputStream("num/Ornum.txt");
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
//	public boolean addAll(Vector<Orders> t, int num) {
//		// TODO 自动生成的方法存根
//		return false;
//	}
	
}
