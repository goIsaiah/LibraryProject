package Databases;

public interface Database<T> {
	public boolean addItem(T t);
	public boolean removeItem(T t);
	public T getData(E e);
}
