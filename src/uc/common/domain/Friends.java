package uc.common.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description: 好友分组domain
 * @author wutp 2016年11月6日
 * @version 1.0
 */
public class Friends implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer fno;//编号
	private Integer fuc;//好友uc
	private Integer fsno;//所属分组
	private Timestamp fdate;//添加日期
	private Integer uc;//本人uc
	
	public Integer getFno() {
		return fno;
	}
	public void setFno(Integer fno) {
		this.fno = fno;
	}
	public Integer getFuc() {
		return fuc;
	}
	public void setFuc(Integer fuc) {
		this.fuc = fuc;
	}
	public Integer getFsno() {
		return fsno;
	}
	public void setFsno(Integer fsno) {
		this.fsno = fsno;
	}
	public Timestamp getFdate() {
		return fdate;
	}
	public void setFdate(Timestamp fdate) {
		this.fdate = fdate;
	}
	public Integer getUc() {
		return uc;
	}
	public void setUc(Integer uc) {
		this.uc = uc;
	}
	
	

}
