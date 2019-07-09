package org.activage.entities;

import java.util.List;
public class Service {
	
	private String id;
	private String type;
	private String url;
	private List<String> sources;
	private String user;
	private String password;
	private String ds;
	private String platform;
	
	public Service(String id, String type, String url, List<String> sources,
			String user, String password, String ds, String platform) {
		super();
		this.id = id;
		this.type = type;
		this.url = url;
		this.sources = sources;
		this.user = user;
		this.password = password;
		this.ds = ds;
		this.platform = platform;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDs() {
		return ds;
	}

	public void setDs(String ds) {
		this.ds = ds;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", type=" + type + ", url=" + url
				+ ", sources=" + sources + ", user=" + user + ", password="
				+ password + ", ds=" + ds + ", platform=" + platform + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Service other = (Service) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
