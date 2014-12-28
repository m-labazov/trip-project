package ua.home.trip.data;

import org.springframework.data.annotation.Id;

import ua.home.trip.api.data.IIdentifable;


public class Identifier implements IIdentifable {

    @Id
	private String id;

    @Override
	public String getId() {
		return id;
	}

    @Override
	public void setId(String id) {
		this.id = id;
	}

}
