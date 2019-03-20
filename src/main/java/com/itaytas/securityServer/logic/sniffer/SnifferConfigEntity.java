package com.itaytas.securityServer.logic.sniffer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "SnifferConfigs")
public class SnifferConfigEntity {

	private String snifferConfigId;
	private String userId;
	private List<String> userApps;

	public SnifferConfigEntity() {
	}

	public SnifferConfigEntity(String userId, List<String> userApps) {
		super();
		this.userId = userId;
		this.userApps = userApps;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getSnifferConfigId() {
		return snifferConfigId;
	}

	@NotBlank
	public String getUserId() {
		return userId;
	}

	@NotEmpty
	public List<String> getUserApps() {
		return userApps;
	}

	public void setSnifferConfigId(String snifferConfigId) {
		this.snifferConfigId = snifferConfigId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserApps(List<String> userApps) {
		this.userApps = userApps;
	}

	@Override
	public String toString() {
		return "SnifferConfigEntity [snifferConfigId=" + snifferConfigId + ", userId=" + userId + ", userApps="
				+ userApps + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((snifferConfigId == null) ? 0 : snifferConfigId.hashCode());
		result = prime * result + ((userApps == null) ? 0 : userApps.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		SnifferConfigEntity other = (SnifferConfigEntity) obj;
		if (snifferConfigId == null) {
			if (other.snifferConfigId != null)
				return false;
		} else if (!snifferConfigId.equals(other.snifferConfigId))
			return false;
		if (userApps == null) {
			if (other.userApps != null)
				return false;
		} else if (!userApps.equals(other.userApps))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
