package com.acbenny.HouseExpenses.model.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@SequenceGenerator(name = "ID_SEQ", sequenceName = "USERS_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
	private Integer id;
	
	@Column(unique = true, nullable = false)
	private String userName;
	
	private String name;
	
	@Basic (fetch=FetchType.LAZY)
	private String pwd;
	
	@Column(unique = true, nullable = false)
	@Basic (fetch=FetchType.LAZY)
	private String email;
	
	public User() {
	}

	@OneToMany(mappedBy = "user", targetEntity = Share.class, fetch = FetchType.LAZY)
	private List<Share> shareList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Share> getShareList() {
		return shareList;
	}

	public void setShareList(List<Share> shareList) {
		this.shareList = shareList;
	}
	public static String getConstraintName(String column) {
		if ("username".equalsIgnoreCase(column)) {
			return "USERS_UK_USERNAME";
		}else if ("email".equalsIgnoreCase(column)) {
			return "USERS_UK_EMAIL";
		}else {
			return "";
		}
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", name=" + name
				+ ", email=" + email + "]";
	}
}
