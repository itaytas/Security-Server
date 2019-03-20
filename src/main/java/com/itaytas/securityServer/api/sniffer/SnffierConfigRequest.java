package com.itaytas.securityServer.api.sniffer;

import java.util.List;

import com.itaytas.securityServer.logic.sniffer.SnifferConfigEntity;

public class SnffierConfigRequest {

	private String snifferConfigId;
	private String userId;
	private List<String> userApps;
	
	public SnffierConfigRequest() {
	}

	public SnffierConfigRequest(String userId, List<String> userApps) {
		this.userId = userId;
		this.userApps = userApps;
	}
	
	public SnffierConfigRequest(SnifferConfigEntity entity) {
		this(entity.getUserId(), entity.getUserApps());
		this.snifferConfigId = entity.getSnifferConfigId();
	}
	
	public SnifferConfigEntity toEntity() {
		SnifferConfigEntity rv = new SnifferConfigEntity();
		 if (this.snifferConfigId != null || !this.snifferConfigId.isEmpty()) {
			rv.setSnifferConfigId(this.snifferConfigId);
		}
		rv.setUserId(this.userId);
		rv.setUserApps(this.userApps);
		return rv;
	}

	public String getSnifferConfigId() {
		return snifferConfigId;
	}

	public String getUserId() {
		return userId;
	}

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
		return "SnffierConfigRequest [snifferConfigId=" + snifferConfigId + ", userId=" + userId + ", userApps="
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
		SnffierConfigRequest other = (SnffierConfigRequest) obj;
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
