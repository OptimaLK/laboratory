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
