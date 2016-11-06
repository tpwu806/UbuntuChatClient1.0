package uc.common.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description: 分组信息domain
 * @author wutp 2016年11月6日
 * @version 1.0
 */
public class SubGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer sno;//组号
	private String sname;//组名
	private Timestamp sdate;//创建时间
	private Integer uc;//uc号
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
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
	
	

}
