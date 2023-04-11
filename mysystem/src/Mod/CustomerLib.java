package Mod;
import java.sql.*;
import System.*;

public class CustomerLib extends Library<Customer>{
	
	public CustomerLib() {
		super();
		
	}
	
	
	@Override
	public Customer delete(String id) throws SQLException  {
		// TODO 自动生成的方法存根
//		for(Customer c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				this.getObjects().remove(c);
//				this.setNum(this.getNum()-1);
//				return c;
//			}
//		}
		String sql = "select * from customer where cuid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Customer x = new Customer();
		if(re.next()==false)
		{
//			re.close();
//			sta.close();
			return null;
		}
		x.setName(re.getString("name"));
		x.setId(id);
		x.setFlag(re.getInt("flag"));
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
		x.setNumber(re.getString("number"));
		x.setAddress(re.getString("address"));
		sql = "select * from orders where cuid = '"+id+"'";
		re = sta.executeQuery(sql);
		while(re.next()==true)
		{
			String noworid = re.getString("orid");
			
			String sql1 = "delete from dish where orid = '"+noworid+"'";
			Statement sta1 =  Data.conn.createStatement();
			sta1.execute(sql1);
			Data.digetnum();
			sql1 = "delete from orders where orid = '"+noworid+"'";
			sta1.execute(sql1);
		}
		Data.orgetnum();
		sql = "delete from customer where cuid = '"+id+"'";
		sta.execute(sql);
		Data.cugetnum();
//		re.close();
//		sta.close();
		return x;
		
	}

	@Override
	public Customer search(String id) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Customer c:this.getObjects())
//		{
//			if(c.getId().equals(id))
//			{
//				return c;
//			}
//		}
		String sql = "select * from customer where cuid = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		Customer x = new Customer();
		if(re.next()==false)
		{
//			re.close();
//			sta.close();
			return null;
		}
		x.setName(re.getString("name"));
		x.setId(id);
		x.setFlag(re.getInt("flag"));
		x.setPasswd(re.getString("passwd"));
		x.setSex(re.getString("sex"));
		x.setNumber(re.getString("number"));
		x.setAddress(re.getString("address"));
//		sta.close();
//		re.close();
		return x;
	}
	
	@Override
	public boolean edit(Customer t1, Customer t2) throws SQLException {
		// TODO 自动生成的方法存根
//		for(Customer c:this.getObjects())
//		{
//			if(c.equals(t1))
//			{
//				this.getObjects().remove(c);
//				this.getObjects().add(t2);
//				return true;
//			}
//		}
//		return false;
		String sql = "select * from customer where cuid = '"+t1.getId()+"'";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		boolean x = true;
		if(re.next()==false)
		{
//			re.close();
//			sta.close();
			return false;
		}
		if(t2.getId().equals(t1.getId())==false)
		{
			addone(t2);
			sql = "update orders set cuid ='"+t2.getId()+"' where cuid = '"+t1.getId()+"'";
			x = sta.execute(sql);
			delete(t1.getId());
		}
		else
		{
			if(t1.getName().equals(t2.getName())==false)
			{
				sql = "update customer set name ='"+t2.getName()+"' where cuid = '"+t1.getId()+"'";
				x = sta.execute(sql);
			}
			if(t1.getPasswd().equals(t2.getPasswd())==false)
			{
				sql = "update customer set passwd ='"+t2.getPasswd()+"' where cuid = '"+t1.getId()+"'";
				x = sta.execute(sql);
			}
			if(t1.getSex().equals(t2.getSex())==false)
			{
				sql = "update customer set sex ='"+t2.getSex()+"' where cuid = '"+t1.getId()+"'";
				x = sta.execute(sql);
			}
			if(t1.getNumber().equals(t2.getNumber())==false)
			{
				sql = "update customer set number ='"+t2.getNumber()+"' where cuid = '"+t1.getId()+"'";
				x = sta.execute(sql);
			}
			if(t1.getAddress().equals(t2.getAddress())==false)
			{
				sql = "update customer set address ='"+t2.getAddress()+"' where cuid = '"+t1.getId()+"'";
				x = sta.execute(sql);
			}
			sql = "update customer set flag ='"+t2.isFlag()+"' where cuid = '"+t1.getId()+"'";
			x = sta.execute(sql);

		}
		
//		re.close();
//		sta.close();
		return x;
	}


	@Override
	public boolean addone(Customer t) throws SQLException {
		if(search(t.getId())!=null)
		{
			return false;
		}
		// TODO 自动生成的方法存根
		String sql = "INSERT\r\n"
				+ "into customer\r\n"
				+ "values('"+t.getId()+"','"+t.getName()+"','"
				+t.getPasswd()+"','"+t.getSex()+"','"+t.getNumber()
				+"','"+t.getAddress()+"','"+t.isFlag()+"')";
		Statement sta =  Data.conn.createStatement();
//		System.out.println(sql);
		boolean x = sta.execute(sql);
		Data.cugetnum();
//		sta.close();
		return x;
	}
	
//	@Override
//	public Customer search(int order) throws SQLException {
//		// TODO 自动生成的方法存根
////		for(Customer c:this.getObjects())
////		{
////			if(c.getId().equals(id))
////			{
////				return c;
////			}
////		}
//		String sql = "select * from customer where cuorder = '"+order+"'";
//		if(Data.conn==null) return null;
//		Statement sta =  Data.conn.createStatement();
//		ResultSet re = sta.executeQuery(sql);
//		Customer x = new Customer();
//		if(re.next()==false)
//		{
//			re.close();
//			sta.close();
//			return null;
//		}
//		x.setName(re.getString("name"));
//		x.setId(re.getString("cuid"));
//		x.setFlag(re.getInt("flag"));
//		x.setPasswd(re.getString("passwd"));
//		sta.close();
//		re.close();
//		return x;
//	}
	
//	@Override
//	public boolean edit(int order1,int order2) throws SQLException {
//
//		String sql = "select * from customer where cuorder = '"+order1+"'";
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
//		sql = "update customer set cuorder ='"+order2+"' where cuorder = '"+order1+"'";
//		x = sta.execute(sql);
//		re.close();
//		sta.close();
//		return x;
//	}
	
//	@Override
//	public boolean exportData(int t) throws IOException {
//		// TODO 自动生成的方法存根
//		try {
//			FileOutputStream mafos = new FileOutputStream("num/Cunum.txt");		
//			mafos.write(t);
//			mafos.close();
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
//		FileInputStream fis = new FileInputStream("num/Cunum.txt");
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
//	public boolean addAll(Vector<Customer> t, int num) {
//		// TODO 自动生成的方法存根
//		return false;
//	}
	
}
