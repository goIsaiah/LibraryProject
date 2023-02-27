package sqldb;

public interface DB {


	public <E> void addItem(E item); 
	public <E> void removeItem(E item); 
	public <E> String getItem(E item);
	public void setPassword(String psw);
 	

}
