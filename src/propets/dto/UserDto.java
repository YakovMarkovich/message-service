package propets.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;

import propets.entities.User;

public class UserDto {
	private String userLogin;
	private String userName;
	private String avatar;
	private String role;
	private Map<String, List<Long>> activities;
	
	{
		activities = new HashMap<>();
		activities.put("messages", new ArrayList<Long>());
		activities.put("lostFound", new ArrayList<Long>());
		activities.put("hotels", new ArrayList<Long>());
	}
	

	public Map<String, List<Long>> getActivities() {
		return activities;
	}

	public void setActivities(String key, long value) {
		activities.get(key).add(value);
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserDto(String userName, String userLogin, String password) {
		super();
		this.userLogin = userLogin;
		this.userName = userName;
		this.password = password;
	}

	private String phone;

	public String getRole() {
		return role;
	}

	public String getPhone() {
		return phone;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	private String password;

	public UserDto() {
	}


	public UserDto(String userLogin, String userName, String avatar, String phone, String role) {
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = avatar;
		this.phone = phone;
		this.role = role;
	}
	
	public UserDto(String userLogin, String userName, String avatar, String phone, String role, String password) {
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = avatar;
		this.phone = phone;
		this.role = role;
		this.password = password;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userLogin=" + userLogin + ", userName=" + userName + ", avatar=" + avatar
				+ ", role=" + role + ", password=" + password + "]";
	}

}
