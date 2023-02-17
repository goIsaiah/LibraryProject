package Databases;

public interface Database<T> {
	public boolean addData(T t) throws Exception;
	public boolean removeData(T t);
	public T getData(String s);
}
