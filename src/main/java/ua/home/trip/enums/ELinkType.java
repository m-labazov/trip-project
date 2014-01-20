package ua.home.trip.enums;

/**
 * @author Maksym_Labazov
 * 
 */
public enum ELinkType {

	HOTEL("Hotel"),
	PLACE_OF_INTEREST("Place of interest"), 
	HELP_INFORMATION("Help information"), 
	AVIA("Avia"), 
	PUBLIC_TRANSPORT("Public transport");

	private String title;

	public String getTitle() {
		return title;
	}

	private ELinkType(String title) {
		this.title = title;
	}

}
