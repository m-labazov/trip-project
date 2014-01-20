package ua.home.trip.controller;

public class Locations {

	private Location[] locations;

	public Location[] getLocations() {
		return locations;
	}

	public void setLocations(Location[] locations) {
		this.locations = locations;
	}

	public static class Location {
		private String ob;
		private String nb;
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getOb() {
			return ob;
		}

		public void setOb(String ob) {
			this.ob = ob;
		}

		public String getNb() {
			return nb;
		}

		public void setNb(String nb) {
			this.nb = nb;
		}
	}
}
