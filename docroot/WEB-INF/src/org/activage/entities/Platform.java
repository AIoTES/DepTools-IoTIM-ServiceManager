package org.activage.entities;

public class Platform {
	
	private String id;
	private String platformType;
	private String baseEndpoint;
	private String location;
	private String name;
	private String downstreamInputAlignmentName;
	private String downstreamInputAlignmentVersion;
	private String downstreamOutputAlignmentName;
	private String downstreamOutputAlignmentVersion;
	private String upstreamInputAlignmentName;
	private String upstreamInputAlignmentVersion;
	private String upstreamOutputAlignmentName;
	private String upstreamOutputAlignmentVersion;
	
	public Platform(String id, String platformType, String baseEndpoint,
			String location, String name, String downstreamInputAlignmentName,
			String downstreamInputAlignmentVersion,
			String downstreamOutputAlignmentName,
			String downstreamOutputAlignmentVersion,
			String upstreamInputAlignmentName,
			String upstreamInputAlignmentVersion,
			String upstreamOutputAlignmentName,
			String upstreamOutputAlignmentVersion) {
		super();
		this.id = id;
		this.platformType = platformType;
		this.baseEndpoint = baseEndpoint;
		this.location = location;
		this.name = name;
		this.downstreamInputAlignmentName = downstreamInputAlignmentName;
		this.downstreamInputAlignmentVersion = downstreamInputAlignmentVersion;
		this.downstreamOutputAlignmentName = downstreamOutputAlignmentName;
		this.downstreamOutputAlignmentVersion = downstreamOutputAlignmentVersion;
		this.upstreamInputAlignmentName = upstreamInputAlignmentName;
		this.upstreamInputAlignmentVersion = upstreamInputAlignmentVersion;
		this.upstreamOutputAlignmentName = upstreamOutputAlignmentName;
		this.upstreamOutputAlignmentVersion = upstreamOutputAlignmentVersion;
	}
	
	public Platform(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public String getBaseEndpoint() {
		return baseEndpoint;
	}

	public void setBaseEndpoint(String baseEndpoint) {
		this.baseEndpoint = baseEndpoint;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDownstreamInputAlignmentName() {
		return downstreamInputAlignmentName;
	}

	public void setDownstreamInputAlignmentName(String downstreamInputAlignmentName) {
		this.downstreamInputAlignmentName = downstreamInputAlignmentName;
	}

	public String getDownstreamInputAlignmentVersion() {
		return downstreamInputAlignmentVersion;
	}

	public void setDownstreamInputAlignmentVersion(
			String downstreamInputAlignmentVersion) {
		this.downstreamInputAlignmentVersion = downstreamInputAlignmentVersion;
	}

	public String getDownstreamOutputAlignmentName() {
		return downstreamOutputAlignmentName;
	}

	public void setDownstreamOutputAlignmentName(
			String downstreamOutputAlignmentName) {
		this.downstreamOutputAlignmentName = downstreamOutputAlignmentName;
	}

	public String getDownstreamOutputAlignmentVersion() {
		return downstreamOutputAlignmentVersion;
	}

	public void setDownstreamOutputAlignmentVersion(
			String downstreamOutputAlignmentVersion) {
		this.downstreamOutputAlignmentVersion = downstreamOutputAlignmentVersion;
	}

	public String getUpstreamInputAlignmentName() {
		return upstreamInputAlignmentName;
	}

	public void setUpstreamInputAlignmentName(String upstreamInputAlignmentName) {
		this.upstreamInputAlignmentName = upstreamInputAlignmentName;
	}

	public String getUpstreamInputAlignmentVersion() {
		return upstreamInputAlignmentVersion;
	}

	public void setUpstreamInputAlignmentVersion(
			String upstreamInputAlignmentVersion) {
		this.upstreamInputAlignmentVersion = upstreamInputAlignmentVersion;
	}

	public String getUpstreamOutputAlignmentName() {
		return upstreamOutputAlignmentName;
	}

	public void setUpstreamOutputAlignmentName(String upstreamOutputAlignmentName) {
		this.upstreamOutputAlignmentName = upstreamOutputAlignmentName;
	}

	public String getUpstreamOutputAlignmentVersion() {
		return upstreamOutputAlignmentVersion;
	}

	public void setUpstreamOutputAlignmentVersion(
			String upstreamOutputAlignmentVersion) {
		this.upstreamOutputAlignmentVersion = upstreamOutputAlignmentVersion;
	}

	@Override
	public String toString() {
		return "Platform [id=" + id + ", platformType=" + platformType
				+ ", baseEndpoint=" + baseEndpoint + ", location=" + location
				+ ", name=" + name + ", downstreamInputAlignmentName="
				+ downstreamInputAlignmentName
				+ ", downstreamInputAlignmentVersion="
				+ downstreamInputAlignmentVersion
				+ ", downstreamOutputAlignmentName="
				+ downstreamOutputAlignmentName
				+ ", downstreamOutputAlignmentVersion="
				+ downstreamOutputAlignmentVersion
				+ ", upstreamInputAlignmentName=" + upstreamInputAlignmentName
				+ ", upstreamInputAlignmentVersion="
				+ upstreamInputAlignmentVersion
				+ ", upstreamOutputAlignmentName="
				+ upstreamOutputAlignmentName
				+ ", upstreamOutputAlignmentVersion="
				+ upstreamOutputAlignmentVersion + "]";
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
		Platform other = (Platform) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
