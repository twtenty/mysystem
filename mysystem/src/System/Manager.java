package System;
import java.io.Serializable;

public class Manager extends Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Manager(String name,String id,String passwd,String sex) {
		super(name,id,passwd,sex);
	}

	public Manager() {
		super();
		// TODO 自动生成的构造函数存根
	}

	@Override
	public boolean equals(Object obj) {
		// TODO 自动生成的方法存根
		if(this == obj) return true;
		if(obj instanceof Manager)
		{
			Manager b=(Manager) obj;
			if(this.getId().equals(b.getId()))
				return true;
			else return false;
		}
		else return false;
	}

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return this.getId()+" "+this.getName();
	}

}
