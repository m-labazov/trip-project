package ua.home.trip.api.service;

import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.filter.Filter;

import java.util.List;

public interface ILinkService {

    void addLink(String tripId, Link link);

    void updateLink(String tripId, Link link);

    void deleteLink(String tripId, String linkName);

    void addMarkerToLink(String tripId, String linkId, Marker marker);

    List<Link> findLinks(Filter filter);

}
