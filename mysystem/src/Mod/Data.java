package Mod;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Data implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Connection conn = null;
//	final static int N=100010;
//	static Manager ma = new Manager("wcc","123","123",123456789,54321);
//	static Employee []em = new Employee[N];
//	static Customer []cu = new Customer[N];
//	static Takeout []ta = new Takeout[N];
//	static Order []or = new Order[N];
//	static Menus []me = new Menus[N];
//	static Leave []le = new Leave[N];
	private static int numem;//员工总数
	private static int numta;//外卖员总数
	private static int numcu;//顾客总数
	private static int numme;//菜品总数
	private static int numde;//辞职总数
	private static int numma;//经理总数
	private static int numor;//订单总数
	private static int numdi;//订单中菜的总数
	private static int numap;
	private static ManagerLib ma = new ManagerLib();
	private static EmployeeLib em = new EmployeeLib();
	private static TakeoutLib ta = new TakeoutLib();
	private static CustomerLib cu = new CustomerLib();
	private static DepartLib de = new DepartLib();
	private static DishLib di = new DishLib();
	private static OrdersLib or = new OrdersLib();
	private static MenusLib me = new MenusLib();
	private static ApplyLib ap = new ApplyLib();
//	public static Vector<Order> or = new Vector<Order>();
//	public static Vector<Menus> me = new Vector<Menus>();
//	public static HashMap<String,Double> cai = new HashMap<String,Double>();
//	public static HashMap<String,Order> cuor = new HashMap<String,Order>();
	public static double income;//总收入
	public static double pay;//总支出
	
	public static void init()  throws SQLException, IOException, ClassNotFoundException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mysystem";
//			String user = "root";
//			String passwd = "123456";
			Properties info = Data.getproperties("info.properties");
			conn = DriverManager.getConnection(url,info);
//			System.out.println("数据库连接成功");
			
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("驱动程序不存在");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("数据库连接失败");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("properties文件提取错误");
		}
		Data.em.setMaxsize(100);
		Data.ta.setMaxsize(100);
//		Data.exportIncome(0);
//		Data.exportPay(0);
		Data.pay=Data.importPay();
		Data.income=Data.importIncome();
		cugetnum();
		degetnum();
		digetnum();
		emgetnum();
		magetnum();
		megetnum();
		orgetnum();
		tagetnum();
		apgetnum();
//		System.out.println(Data.numme);	
		
	}
	
	private static Properties getproperties(String filepath) throws IOException {
		FileInputStream fis = new FileInputStream(filepath);
		Properties info = new Properties();
		info.load(fis);
		return info;
	}
	
	public static void cugetnum() throws SQLException {
		
		String sql = "select count(*) from customer ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numcu=re.getInt(1);
		
	}
	
	public static void degetnum() throws SQLException{

		String sql = "select count(*) from depart ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numde=re.getInt(1);
	}
	
	public static void digetnum() throws SQLException{

		String sql = "select count(*) from dish ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numdi=re.getInt(1);
	}
	
	public static void emgetnum() throws SQLException{

		String sql = "select count(*) from employee ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numem=re.getInt(1);
	}
	
	public static void magetnum() throws SQLException{

		String sql = "select count(*) from manager ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numma=re.getInt(1);
	}
	
	public static void megetnum() throws SQLException{

		String sql = "select count(*) from menus ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numme=re.getInt(1);
	}
	
	public static void orgetnum() throws SQLException{

		String sql = "select count(*) from orders ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numor=re.getInt(1);
	}
	
	public static void tagetnum() throws SQLException{

		String sql = "select count(*) from takeout ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numta=re.getInt(1);
	}
	
	public static void apgetnum() throws SQLException{

		String sql = "select count(*) from apply ";
		Statement sta =  Data.conn.createStatement();
		ResultSet re = sta.executeQuery(sql);
		if(re.next()) Data.numap=re.getInt(1);
	}
	
	public static boolean exportIncome(double t) throws IOException {
		// TODO 自动生成的方法存根
		try {
			FileOutputStream mafos = new FileOutputStream("Incom.txt");
			ObjectOutputStream maoos = new ObjectOutputStream(mafos);
//			Manager pos = new Manager("wcc","123","123");
//			oos.writeObject(pos);
//			for(Menus mapos:Data.cu.getObjects())
//			{
//				maoos.writeObject(mapos);
//			}
			maoos.writeDouble(t);;
			maoos.close();
			mafos.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return true;
	}

	public static double importIncome() throws IOException, ClassNotFoundException {
		// TODO 自动生成的方法存根
		FileInputStream fis = new FileInputStream("Incom.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		double x=0;
		try{
			x = ois.readDouble();
//			System.out.println(x.getName()+"  "+x.getId()+"  "+x.getPasswd());
//			Data.cu.addone(x);
		}catch(EOFException e) {
			e.printStackTrace();
		}
		
		ois.close();
		fis.close();
		return x;
	}
	
	public static boolean exportPay(double t) throws IOException {
		// TODO 自动生成的方法存根
		try {
			FileOutputStream mafos = new FileOutputStream("Pay.txt");
			ObjectOutputStream maoos = new ObjectOutputStream(mafos);
//			Manager pos = new Manager("wcc","123","123");
//			oos.writeObject(pos);
//			for(Menus mapos:Data.cu.getObjects())
//			{
//				maoos.writeObject(mapos);
//			}
			maoos.writeDouble(t);
			maoos.close();
			mafos.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return true;
	}

	public static double importPay() throws IOException, ClassNotFoundException {
		// TODO 自动生成的方法存根
		FileInputStream fis = new FileInputStream("Pay.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		double x=0;
		try{
			x = ois.readDouble();
//			System.out.println(x.getName()+"  "+x.getId()+"  "+x.getPasswd());
//			Data.cu.addone(x);
		}catch(EOFException e) {
			e.printStackTrace();
		}
		
		ois.close();
		fis.close();
		return x;
	}

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		Data.conn = conn;
	}

	public static int getNumem() {
		return numem;
	}

	public static void setNumem(int numem) {
		Data.numem = numem;
	}

	public static int getNumta() {
		return numta;
	}

	public static void setNumta(int numta) {
		Data.numta = numta;
	}

	public static int getNumcu() {
		return numcu;
	}

	public static void setNumcu(int numcu) {
		Data.numcu = numcu;
	}

	public static int getNumme() {
		return numme;
	}

	public static void setNumme(int numme) {
		Data.numme = numme;
	}

	public static int getNumde() {
		return numde;
	}

	public static void setNumde(int numde) {
		Data.numde = numde;
	}

	public static int getNumma() {
		return numma;
	}

	public static void setNumma(int numma) {
		Data.numma = numma;
	}

	public static int getNumor() {
		return numor;
	}

	public static void setNumor(int numor) {
		Data.numor = numor;
	}

	public static int getNumdi() {
		return numdi;
	}

	public static void setNumdi(int numdi) {
		Data.numdi = numdi;
	}

	public static ManagerLib getMa() {
		return ma;
	}

	public static void setMa(ManagerLib ma) {
		Data.ma = ma;
	}

	public static EmployeeLib getEm() {
		return em;
	}

	public static void setEm(EmployeeLib em) {
		Data.em = em;
	}

	public static TakeoutLib getTa() {
		return ta;
	}

	public static void setTa(TakeoutLib ta) {
		Data.ta = ta;
	}

	public static CustomerLib getCu() {
		return cu;
	}

	public static void setCu(CustomerLib cu) {
		Data.cu = cu;
	}

	public static DepartLib getDe() {
		return de;
	}

	public static void setDe(DepartLib de) {
		Data.de = de;
	}

	public static DishLib getDi() {
		return di;
	}

	public static void setDi(DishLib di) {
		Data.di = di;
	}

	public static OrdersLib getOr() {
		return or;
	}

	public static void setOr(OrdersLib or) {
		Data.or = or;
	}

	public static MenusLib getMe() {
		return me;
	}

	public static void setMe(MenusLib me) {
		Data.me = me;
	}

	public static double getIncome() {
		return income;
	}

	public static void setIncome(double income) {
		Data.income = income;
	}

	public static double getPay() {
		return pay;
	}

	public static void setPay(double pay) {
		Data.pay = pay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getNumap() {
		return numap;
	}

	public static void setNumap(int numap) {
		Data.numap = numap;
	}

	public static ApplyLib getAp() {
		return ap;
	}

	public static void setAp(ApplyLib ap) {
		Data.ap = ap;
	}
	
	
//	public static Menus search(String name) {
//		// TODO 自动生成的方法存根
//		for(Menus c:Data.me)
//		{
//			if(c.getMenu().equals(name))
//			{
//				return c;
//			}
//		}
//		return null;
//	}
	
}
