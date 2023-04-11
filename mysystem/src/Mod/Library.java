package Mod;

public abstract class Library<T> implements Port<T>{

	private int Maxsize;
	
	public Library() {
		// TODO Auto-generated method stub
		
		this.Maxsize=100;
	}

	
	
	public Library(int maxsize) {
		super();
		
		Maxsize = maxsize;
	}

	public int getMaxsize() {
		return Maxsize;
	}

	public void setMaxsize(int maxsize) {
		Maxsize = maxsize;
	}


	
}
