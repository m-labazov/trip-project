package ua.home.trip.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ua.home.trip.api.data.IInformation;
import ua.home.trip.api.repository.IInformationRepository;
import ua.home.trip.api.service.IInformationService;

@Service
public class InformationService extends AbstractService<IInformation, IInformationRepository> implements
		IInformationService {

	@Resource
	private IInformationRepository repository;

	@Override
	protected IInformationRepository getRepository() {
		return repository;
	}

}
