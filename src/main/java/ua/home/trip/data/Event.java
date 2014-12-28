package ua.home.trip.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import ua.home.trip.enums.ELinkType;

import java.util.Date;
import java.util.List;


public class Event extends Identifier {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private Date startTime;
    private Date endTime;
    private String location;
    private String url;
    private List<Link> links;
    private String description;
    private String name;
    private ELinkType type;
    private EventDetails details;
    private String tripId;

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public ELinkType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ELinkType type) {
        this.type = type;
    }

    /**
     * @return the details
     */
    public EventDetails getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(EventDetails details) {
        this.details = details;
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

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return the tripId
     */
    public String getTripId() {
        return tripId;
    }

    /**
     * @param tripId the tripId to set
     */
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

}
