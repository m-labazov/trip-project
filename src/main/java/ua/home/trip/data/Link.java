package ua.home.trip.data;

import java.util.Date;

import ua.home.trip.enums.ELinkType;

public class Link {

	private String linkId;

	private ELinkType type;
	private String url;
	private String name;
    private String description;
	private Marker marker;
    private String location;
	private Date createDate;
	private String createUser;
	private boolean planned;

	public boolean isPlanned() {
		return planned;
	}

	public void setPlanned(boolean planned) {
		this.planned = planned;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public ELinkType getType() {
		return type;
	}

	public void setType(ELinkType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

}
