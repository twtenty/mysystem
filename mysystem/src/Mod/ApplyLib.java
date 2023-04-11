package Mod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import System.Apply;

public class ApplyLib extends Library<Apply>{

	@Override
	public Apply delete(String id) throws SQLException {
		// TODO 自动生成的方法存根

		String sql = "select * from apply where id = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
	
		ResultSet re = sta.executeQuery(sql);
		Apply x = new Apply();
		if(re.next()==false)
		{
			return null;
		}
		x.setName(re.getString("name"));
		x.setSex(re.getString("sex"));
		x.setJob(re.getString("job"));
		x.setWages(re.getDouble("wages"));
		x.setReason(re.getString("reason"));
		x.setEmid(null);
		x.setTaid(null);
		sql = "delete from apply where id = '"+id+"'";
		sta.execute(sql);
		Data.apgetnum();
		return x;
//		sta.close();
		
	}

	@Override
	public Apply search(String id) throws SQLException {
		// TODO 自动生成的方法存根
		String sql = "select * from apply where id = '"+id+"'";
		Statement sta =  Data.conn.createStatement();
	
		ResultSet re = sta.executeQuery(sql);
		Apply x = new Apply();
		if(re.next()==false)
		{
			return null;
		}
		x.setName(re.getString("name"));
		x.setSex(re.getString("sex"));
		x.setJob(re.getString("job"));
		x.setWages(re.getDouble("wages"));
		x.setReason(re.getString("reason"));
		x.setEmid(null);
		x.setTaid(null);
		sta.execute(sql);
		return x;
	}

	@Override
	public boolean edit(Apply t1, Apply t2) throws SQLException {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	public boolean addone(Apply t) throws SQLException {
		// TODO 自动生成的方法存根
		if(search(t.getId())!=null)
		{
			return false;
		}
		String sql = "INSERT\r\n"
				+ "into apply\r\n"
				+ "values('"+t.getId()+"','"+t.getName()+"','"
				+t.getSex()+"','"+t.getJob()+"','"+t.getWages()+"','"+t.getReason()+
				"',null,null)";
		Statement sta =  Data.conn.createStatement();
		Data.emgetnum();
		boolean re = sta.execute(sql);
		return re;
	}
	
}
