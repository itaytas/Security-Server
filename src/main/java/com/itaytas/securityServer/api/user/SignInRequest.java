package com.itaytas.securityServer.api.user;

import javax.validation.constraints.NotBlank;

public class SignInRequest {

	private String usernameOrEmail;
	private String password;

	public SignInRequest() {
	}

	public SignInRequest(String usernameOrEmail, String password) {
		super();
		this.usernameOrEmail = usernameOrEmail;
		this.password = password;
	}

	@NotBlank
	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	@NotBlank
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignInRequest [usernameOrEmail=" + usernameOrEmail + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((usernameOrEmail == null) ? 0 : usernameOrEmail.hashCode());
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
		SignInRequest other = (SignInRequest) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (usernameOrEmail == null) {
			if (other.usernameOrEmail != null)
				return false;
		} else if (!usernameOrEmail.equals(other.usernameOrEmail))
			return false;
		return true;
	}

}