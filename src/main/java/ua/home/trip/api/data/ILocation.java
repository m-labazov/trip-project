package ua.home.trip.api.data;

import java.util.Date;
import java.util.List;

public interface ILocation extends IIdentifable {

	void setInformation(List<IInformation> information);

	List<IInformation> getInformation();

	/**
	 * @return the name
	 */
	String getName();

	/**
	 * @param name
	 *            the name to set
	 */
	void setName(String name);

	/**
	 * @return the description
	 */
	String getDescription();

	/**
	 * @param description
	 *            the description to set
	 */
	void setDescription(String description);

	/**
	 * @return the startDate
	 */
	Date getStartDate();

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	void setStartDate(Date date);

	/**
	 * @return the endDate
	 */
	Date getEndDate();

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	void setEndDate(Date date);

	/**
	 * @return the tripId
	 */
	String getTripId();

	/**
	 * @param tripId
	 *            the tripId to set
	 */
	void setTripId(String tripId);

}
