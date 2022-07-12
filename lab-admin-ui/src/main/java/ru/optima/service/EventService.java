package ru.optima.service;

import org.springframework.stereotype.Service;
import ru.optima.beans.Mask;
import ru.optima.repr.EventRepr;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public interface EventService {

    List<EventRepr> findAll();

    void save(EventRepr eventRepr, Mask mask);

    String dateText();
}
