package ru.optima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.optima.beans.Mask;
import ru.optima.persist.model.Event;
import ru.optima.persist.repo.EventRepository;
import ru.optima.repr.EventRepr;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<EventRepr> findAll() {
        return eventRepository.findAll().stream()
                .map(EventRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public void save(EventRepr eventRepr, Mask mask) {
        Event event = new Event();
        event.setId(eventRepr.getId());
        event.setStartT(mask.stringToDateConversionStart());
        event.setEndT(mask.stringToDateConversionEnd());
        event.setDescription(eventRepr.getDescription());
        event.setName(eventRepr.getName());
        event.setEquipments(eventRepr.getEquipments());
        event.setUsers(eventRepr.getUsers());
        event.setWorks(eventRepr.getWorks());
        eventRepository.save(event);
    }

    @Override
    public String dateText() {
        return "";
    }

    @Override
    public Mask dateCheck(Mask mask) {
        Calendar dateEnd = Calendar.getInstance();
        Calendar dateStart = Calendar.getInstance();

        StringBuilder endDay = new StringBuilder(mask.getDateEnd());
        StringBuilder endMonth = new StringBuilder(mask.getDateEnd());
        StringBuilder endYear = new StringBuilder(mask.getDateEnd());
        StringBuilder endHour = new StringBuilder(mask.getHoursEnd());
        StringBuilder endMin = new StringBuilder(mask.getHoursEnd());

        endDay.delete(2, endDay.length());
        endMonth.delete(0, 3).delete(2, endMonth.length());
        endYear.delete(0, 6);
        endHour.delete(2, endHour.length());
        endMin.delete(0, 3);

        dateEnd.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDay.toString()));
        dateEnd.set(Calendar.MONTH, Integer.parseInt(endMonth.toString()));
        dateEnd.set(Calendar.YEAR, Integer.parseInt(endYear.toString()));
        dateEnd.set(Calendar.HOUR, Integer.parseInt(endHour.toString()));
        dateEnd.set(Calendar.MINUTE, Integer.parseInt(endMin.toString()));

        long endTime = dateEnd.getTime().getTime();

        StringBuilder startDay = new StringBuilder(mask.getDateStart());
        StringBuilder startMonth = new StringBuilder(mask.getDateStart());
        StringBuilder startYear = new StringBuilder(mask.getDateStart());
        StringBuilder startHour = new StringBuilder(mask.getHoursStart());
        StringBuilder startMin = new StringBuilder(mask.getHoursStart());

        startDay.delete(2, startDay.length());
        startMonth.delete(0, 3).delete(2, startMonth.length());
        startYear.delete(0, 6);
        startHour.delete(2, startHour.length());
        startMin.delete(0, 3);

        dateStart.set(Calendar.DAY_OF_MONTH, Integer.parseInt(startDay.toString()));
        dateStart.set(Calendar.MONTH, Integer.parseInt(startMonth.toString()));
        dateStart.set(Calendar.YEAR, Integer.parseInt(startYear.toString()));
        dateStart.set(Calendar.HOUR, Integer.parseInt(startHour.toString()));
        dateStart.set(Calendar.MINUTE, Integer.parseInt(startMin.toString()));

        long startTime = dateStart.getTime().getTime();

        if (endTime > startTime) {
            return mask;
        } else {
            Mask m = new Mask();
            m.setDateEnd(mask.getDateStart());
            m.setDateStart(mask.getDateStart());
            return m;
        }
    }

    public void save(EventRepr eventRepr) {
        Event event = new Event();
        event.setId(eventRepr.getId());
        event.setStartT(eventRepr.getStartT());
        event.setEndT(eventRepr.getEndT());
        event.setDescription(eventRepr.getDescription());
        event.setName(eventRepr.getName());
        event.setEquipments(eventRepr.getEquipments());
        event.setUsers(eventRepr.getUsers());
        event.setWorks(eventRepr.getWorks());
        eventRepository.save(event);
    }
}
