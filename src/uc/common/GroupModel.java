package uc.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @Description: 
 * @author wutp 2016年10月30日
 * @version 1.0
 */
public class GroupModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1129594901962565953L;
	
	private Integer sno;
	private String groupName;
	private Timestamp sdate;//创建时间
	private Integer uc;//uc号
	private ArrayList<FriendItemModel> friends = new ArrayList<FriendItemModel>();
	
	public GroupModel(String groupName){
		this.groupName = groupName;
	}
	
	public Integer getSno() {
		return sno;
	}


	public void setSno(Integer sno) {
		this.sno = sno;
	}


	public Timestamp getSdate() {
		return sdate;
	}


	public void setSdate(Timestamp sdate) {
		this.sdate = sdate;
	}


	public Integer getUc() {
		return uc;
	}


	public void setUc(Integer uc) {
		this.uc = uc;
	}
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void trimToSize() {
		friends.trimToSize();
	}

	public void ensureCapacity(int minCapacity) {
		friends.ensureCapacity(minCapacity);
	}

	public int size() {
		return friends.size();
	}

	public boolean isEmpty() {
		return friends.isEmpty();
	}

	public boolean contains(Object o) {
		return friends.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return friends.containsAll(c);
	}

	public int indexOf(Object o) {
		return friends.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return friends.lastIndexOf(o);
	}

	public Object clone() {
		return friends.clone();
	}

	public Object[] toArray() {
		return friends.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return friends.toArray(a);
	}

	public FriendItemModel get(int index) {
		return friends.get(index);
	}

	public String toString() {
		return friends.toString();
	}

	public FriendItemModel set(int index, FriendItemModel element) {
		return friends.set(index, element);
	}

	public boolean add(FriendItemModel e) {
		return friends.add(e);
	}

	public void add(int index, FriendItemModel element) {
		friends.add(index, element);
	}

	public boolean equals(Object o) {
		return friends.equals(o);
	}

	public FriendItemModel remove(int index) {
		return friends.remove(index);
	}

	public boolean remove(Object o) {
		return friends.remove(o);
	}

	public int hashCode() {
		return friends.hashCode();
	}

	public void clear() {
		friends.clear();
	}

	public boolean addAll(Collection<? extends FriendItemModel> c) {
		return friends.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends FriendItemModel> c) {
		return friends.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return friends.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return friends.retainAll(c);
	}

	public ListIterator<FriendItemModel> listIterator(int index) {
		return friends.listIterator(index);
	}

	public ListIterator<FriendItemModel> listIterator() {
		return friends.listIterator();
	}

	public Iterator<FriendItemModel> iterator() {
		return friends.iterator();
	}

	public List<FriendItemModel> subList(int fromIndex, int toIndex) {
		return friends.subList(fromIndex, toIndex);
	}	
}
