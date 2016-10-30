package uc.common.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description: 群
 * @author wutp 2016年10月23日
 * @version 1.0
 */
public class GroupTable implements Serializable {
	private Integer gno;//群号
	private String gname;//群名
	private Timestamp gdate;//创建时间
	public Integer getGno() {
		return gno;
	}
	public void setGno(Integer gno) {
		this.gno = gno;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public Timestamp getGdate() {
		return gdate;
	}
	public void setGdate(Timestamp gdate) {
		this.gdate = gdate;
	}
	
	
}
