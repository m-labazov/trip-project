package ua.home.trip.api.data;

import java.util.Date;

import ua.home.trip.data.Marker;
import ua.home.trip.enums.ELinkType;

public interface IInformation extends IIdentifable {

	String getLocationId();

	void setLocationId(String locationId);

	ELinkType getType();

	void setType(ELinkType type);

	String getUrl();

	void setUrl(String url);

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	Marker getMarker();

	void setMarker(Marker marker);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	String getCreateUser();

	void setCreateUser(String createUser);

}
