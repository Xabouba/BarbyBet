package com.barbyBet.object;

import java.sql.Timestamp;
import java.util.List;

public class Group {

	private String name;
	private String img;
	private String status;
	private String description;
	private Timestamp creationDate;
	private List<User> members;

	public Group(String name2, String desc, String status2) {
		name=name2;
		status=status2;
		setDescription(desc);
	}

	public Group() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
