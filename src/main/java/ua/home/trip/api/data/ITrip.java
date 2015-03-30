package ua.home.trip.api.data;

import java.util.Date;
import java.util.List;

public interface ITrip extends IIdentifable {

	void setLocations(List<ILocation> locations);

	List<ILocation> getLocations();

	void setCreator(String id);

	List<String> getMembers();

	CharSequence getName();

	Date getStartDate();

	Date getEndDate();

}
