package uc.common.domain;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description: 用户信息
 * @author wutp 2016年10月23日
 * @version 1.0
 */
public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer uc;//uc号
	private String pwd;//密码
	private String sign;//签名
	private String photoid;//头像
	private String nickname;//昵称
	private String sex;//性别
	private Timestamp birthday;//生日
	private String constellation;//星座
	private String bloodtype;//血型
	private String diploma;//学历
	private String tellphone;//电话
	private String email;//邮箱
	private String adress;//住址
	private String status;//登录状态
	
	public Integer getUc() {
		return uc;
	}
	public void setUc(Integer uc) {
		this.uc = uc;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPhotoid() {
		return photoid;
	}
	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getBloodtype() {
		return bloodtype;
	}
	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}
	public String getDiploma() {
		return diploma;
	}
	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	public String getTellphone() {
		return tellphone;
	}
	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
