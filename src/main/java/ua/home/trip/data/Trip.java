package ua.home.trip.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Trip extends Identifier {

	private ArrayList<Link> links = new ArrayList<>();
    private List<Event> timeline = new ArrayList<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date endDate;
	private String comment;
	private String name;
    private String creator;
    private List<String> members;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}

    public List<Event> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<Event> timeline) {
        this.timeline = timeline;
    }

    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return the members
     */
    public List<String> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(List<String> members) {
        this.members = members;
    }

}
