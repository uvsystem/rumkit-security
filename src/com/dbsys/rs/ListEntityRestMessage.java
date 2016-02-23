package com.dbsys.rs;

import java.util.List;

/**
 * Kelas untuk men-generate daftar entity menjadi objek JSON.
 * 
 * @author Deddy Christoper Kakunsi
 *
 * @param <T>
 */
public class ListEntityRestMessage<T> extends RestMessage {
	private List<T> list;
	
	public ListEntityRestMessage() {
		super();
	}
	
	protected ListEntityRestMessage(Exception ex) {
		super(ex);
	}

	public ListEntityRestMessage(List<T> list) {
		super("Berhasil", Type.LIST);
		setList(list);
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * Buat objek JSON utuk pesan error.
	 * 
	 * @param cause
	 * 
	 * @return
	 */
	public static <T> ListEntityRestMessage<T> listEntityError(Exception cause) {
		return new ListEntityRestMessage<T>(cause);
	}
}
