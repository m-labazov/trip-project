package ua.home.trip.api.service;

import java.util.List;

import ua.home.trip.api.data.IInformation;

public interface IInformationService extends IAbstractService<IInformation> {

	List<? extends IInformation> findList(String tripId);

}
