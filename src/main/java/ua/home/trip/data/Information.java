package ua.home.trip.data;

import java.util.Date;

import ua.home.trip.api.data.IInformation;
import ua.home.trip.enums.ELinkType;

public class Information extends Identifier implements IInformation {

	private String tripId;
	private String locationId;
	private ELinkType type;
	private String url;
	private String name;
	private String description;
	private Marker marker;
	private Date createDate;
	private String createUser;

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	@Override
	public String getLocationId() {
		return locationId;
	}

	@Override
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Override
	public ELinkType getType() {
		return type;
	}

	@Override
	public void setType(ELinkType type) {
		this.type = type;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Marker getMarker() {
		return marker;
	}

	@Override
	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String getCreateUser() {
		return createUser;
	}

	@Override
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
}
