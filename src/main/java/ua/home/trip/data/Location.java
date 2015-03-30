package ua.home.trip.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.home.trip.api.data.IInformation;
import ua.home.trip.api.data.ILocation;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Location extends Identifier implements ILocation {

	private List<IInformation> information = new ArrayList<>();
	private String name;
	private String description;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date endDate;
	private String tripId;

	@Override
	public List<IInformation> getInformation() {
		return information;
	}

	@Override
	public void setInformation(List<IInformation> information) {
		this.information = information;
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
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String getTripId() {
		return tripId;
	}

	@Override
	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

}
