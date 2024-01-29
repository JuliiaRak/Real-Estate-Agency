package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.Meeting;
import com.solvd.domain.RealEstate;
import com.solvd.domain.exceptions.EntityNotFoundException;
import com.solvd.domain.exceptions.FieldValidationException;
import com.solvd.persistence.MeetingRepository;
import com.solvd.persistence.impl.MeetingRepositoryMybatisImpl;
import com.solvd.service.ClientService;
import com.solvd.service.EmployeeService;
import com.solvd.service.MeetingService;
import com.solvd.service.RealEstateService;
import com.solvd.service.validators.Validator;
import com.solvd.service.validators.date.FutureDateValidator;
import com.solvd.service.validators.date.NotNullDateValidator;
import com.solvd.service.validators.date.PastDateValidator;
import com.solvd.service.validators.object.NotNullObjectValidator;
import com.solvd.service.validators.string.NotEmptyStringValidator;
import com.solvd.service.validators.string.NotNullStringValidator;
import com.solvd.service.validators.string.SizeStringValidator;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final RealEstateService realEstateService;
    private final ClientService clientService;
    private final EmployeeService employeeService;

    public MeetingServiceImpl() {
        this.meetingRepository = new MeetingRepositoryMybatisImpl();
        this.realEstateService = new RealEstateServiceImpl();
        this.clientService = new ClientServiceImpl();
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void create(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) throws EntityNotFoundException, FieldValidationException {
        validate(meeting);
        checkRealEstate(realEstateId);
        checkBuyer(buyerId);
        checkEmployee(employeeId);

        if(meeting.getBuyer().equals(realEstateService.getById(realEstateId).getSeller())){
            throw new FieldValidationException("Error creating a meeting");
        }
        else {
            meetingRepository.create(meeting, realEstateId, buyerId, employeeId);
        }
    }

    private void checkRealEstate(Long realEstateId) throws EntityNotFoundException {
        if (!realEstateService.existsById(realEstateId)) {
            throw new EntityNotFoundException("Real estate", realEstateId);
        }
    }

    private void checkBuyer(Long buyerId) throws EntityNotFoundException {
        if (!clientService.existsById(buyerId)) {
            throw new EntityNotFoundException("Buyer", buyerId);
        }
    }

    private void checkEmployee(Long employeeId) throws EntityNotFoundException {
        if (!employeeService.existsById(employeeId)) {
            throw new EntityNotFoundException("Employee", employeeId);
        }
    }

    @Override
    public void deleteById(long id) {
        meetingRepository.deleteById(id);
    }

    @Override
    public void update(Meeting meeting, Long realEstateId, Long buyerId, Long employeeId) throws EntityNotFoundException, FieldValidationException {
        if (meetingRepository.findById(meeting.getId()).isEmpty()) {
            throw new EntityNotFoundException("Meeting", meeting.getId());
        }
        validate(meeting);
        meetingRepository.update(meeting, realEstateId, buyerId, employeeId);
    }

    @Override
    public Meeting getById(long id) throws EntityNotFoundException {
        return meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meeting", id));
    }

    @Override
    public List<Meeting> getByClient(Client client) {
        return meetingRepository.findAll().stream()
                .filter(meeting -> meeting.getBuyer().getId() == client.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meeting> getByRealEstate(RealEstate realEstate) {
        return meetingRepository.findAll().stream()
                .filter(meeting -> meeting.getRealEstate().getId() == realEstate.getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meeting> getAll() {
        return meetingRepository.findAll();
    }

    private void validate(Meeting meeting) throws FieldValidationException {
        Validator<Object> objectValidator = new NotNullObjectValidator();
        objectValidator.validate("meeting", meeting);

        Validator<Date> notNullDateValidator = new NotNullDateValidator();
        Validator<Date> futureDateValidator = new FutureDateValidator(notNullDateValidator);
        futureDateValidator.validate("meeting date", meeting.getMeetingDateTime());

        Validator<Date> pastDateValidator = new PastDateValidator(notNullDateValidator);
        pastDateValidator.validate("inquiry date", meeting.getInquiryDate());

        Validator<String> stringValidator = new SizeStringValidator(new NotEmptyStringValidator(new NotNullStringValidator()));
        stringValidator.validate("meeting status", meeting.getMeetingStatus());
    }
}
