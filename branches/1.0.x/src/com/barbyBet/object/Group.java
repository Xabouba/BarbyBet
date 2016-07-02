package com.barbyBet.object;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.barbyBet.components.SQLUsersComponent;
import com.barbyBet.tools.DateUtil;
import com.barbyBet.tools.ServletUtil;

public class Group {

	private Long id;
	private String name;
	private int status;
	private String img;
	private String description;
	private Long groupCreator;
	private Date creationDate;
	private List<User> members;

	public Group(Long id, String name, int status, String description, Long groupCreator, Date creationDate, List<User> members) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.description = description;
		this.groupCreator = groupCreator;
		this.creationDate = creationDate;
		this.members = members;
	}

	public Group() {
		
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public Long getGroupCreator() {
		return groupCreator;
	}

	public void setGroupCreator(Long groupCreator) {
		this.groupCreator = groupCreator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public HashMap<String, String> toHashMap() {
		/** Group Information */
	    HashMap<String, String> groupInfo = new HashMap<String, String>();
	    groupInfo.put("id", String.valueOf(this.getId()));
	    groupInfo.put("name", this.getName());
	    groupInfo.put("img", this.getImg());
	    groupInfo.put("description", this.getDescription());
	    groupInfo.put("status", String.valueOf(this.getStatus()));
	    
	    if(this.getMembers().size() == 1) {
	    	groupInfo.put("numberOfMembersFullStr", String.valueOf(this.getMembers().size()) + " membre");
	    } else {
	    	groupInfo.put("numberOfMembersFullStr", String.valueOf(this.getMembers().size()) + " membres");
	    }
	    
	    groupInfo.put("numberOfMembers", String.valueOf(this.getMembers().size()));
	    
	    // Retrieve the user who created the group
	    SQLUsersComponent userComponent = new SQLUsersComponent();
	    User u = userComponent.getUser(this.getGroupCreator());
	    
	    groupInfo.put("groupCreator", u.getUsername());
	    groupInfo.put("groupCreatorId",String.valueOf(u.getId()));
	    groupInfo.put("creationDate", DateUtil.SHORT_DATE_FORMAT_FRANCE.format(this.getCreationDate()));
	    groupInfo.put("statusStr", ServletUtil.getGroupStatusStringFromInt(this.getStatus()));
	    
	    return groupInfo;
	}
}
