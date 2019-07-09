package org.activage.entities;

public class SyntacticTranslator {
	
	private String id;
	private String url;
	private String platformType;
	
	public SyntacticTranslator(String id, String url, String platformType) {
		super();
		this.id = id;
		this.url = url;
		this.platformType = platformType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	@Override
	public String toString() {
		return "SyntacticTranslator [id=" + id + ", url=" + url
				+ ", platformType=" + platformType + "]";
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
		SyntacticTranslator other = (SyntacticTranslator) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
