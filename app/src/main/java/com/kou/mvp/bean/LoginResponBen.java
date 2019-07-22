package com.kou.mvp.bean;

/**
 * Created by sq on 2018/6/23
 */
public class LoginResponBen {

	/**
	 * access_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2VfaWQiXSwidXNlcl9uYW1lIjoi5byg5LiJMSIsInNjb3BlIjpbInNlbGVjdCJdLCJleHAiOjE1MjYzNzI4MDgsImF1dGhvcml0aWVzIjpbIkFkbWluIl0sImp0aSI6ImNhYzM5NTk5LTc5ZTItNDI3OC05YTY2LTVlNTU3NTIzNDBhMSIsImNsaWVudF9pZCI6ImNsaWVudF8yIn0.aUcBsdbNPmJwreTBjT-ZgQNh5uUjv8bxXFNiTFtyknY
	 * token_type : bearer
	 * refresh_token : eyJhbGciOi
	 * JIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicmVzb3VyY2VfaWQiXSwidXNlcl9uYW1lIjoi5byg5LiJMSIsInNjb3BlIjpbInNlbGVjdCJdLCJhdGkiOiJjYWMzOTU5OS03OWUyLTQyNzgtOWE2Ni01ZTU1NzUyMzQwYTEiLCJleHAiOjE1MjYzNzI4MDgsImF1dGhvcml0aWVzIjpbIkFkbWluIl0sImp0aSI6Ijg4MTY3YzM2LTU4NzUtNDgxZi1iNjJlLThjMGU0NzgxMDg3NSIsImNsaWVudF9pZCI6ImNsaWVudF8yIn0.UmklEUW6LIiJZeVjP_RXcstKHJa_AFI3mKOpQGRTr6Q
	 * expires_in : 9999
	 * scope : select
	 * jti : cac39599-79e2-4278-9a66-5e55752340a1
	 */

	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private String scope;
	private String jti;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}


	@Override
	public String toString() {
		return "Response{" +
				"access_token='" + access_token + '\'' +
				", token_type='" + token_type + '\'' +
				", refresh_token='" + refresh_token + '\'' +
				", expires_in=" + expires_in +
				", scope='" + scope + '\'' +
				", jti='" + jti + '\'' +
				'}';
	}
}
