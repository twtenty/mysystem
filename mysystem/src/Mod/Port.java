package Mod;

import java.sql.SQLException;

public interface Port<T> {
	//删除指定用户的顾客，如果成功，返回该顾客的信息，否则返回null
		public abstract T delete(String id) throws SQLException ;
		//查找指定用户的顾客，如果成功，返回该顾客的信息，否则返回null
		public abstract T search(String id) throws SQLException ;
//		public abstract T search(int order) throws SQLException ;
		//修改指定用户的信息，如果成功返回真，否则返回假
		public abstract boolean edit(T t1 , T t2) throws SQLException ;
		//查看指定用户
		
//		public abstract int importData() throws IOException, ClassNotFoundException;
		
//		public  boolean exportData(int t) throws IOException;
		 
		public abstract boolean addone(T t) throws SQLException ;
		
//		public boolean edit(int order1,int order2) throws SQLException ;
		
//		public boolean addAll(Vector<T> t,int num) ;
		
}
