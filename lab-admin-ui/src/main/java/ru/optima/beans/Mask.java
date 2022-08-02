package ru.optima.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;

@Data
@Component
public class Mask {
    private String dateStart;
    private String dateEnd;
    private String hoursStart;
    private String hoursEnd;

    private Timestamp getTimestamp(String dateString) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        StringBuilder stringDay = new StringBuilder(dateString);
        StringBuilder stringMonth = new StringBuilder(dateString);
        StringBuilder stringYear = new StringBuilder(dateString);
        stringDay.delete(2, stringDay.length());
        stringMonth.delete(0, 3).delete(2, stringMonth.length());
        stringYear.delete(0, 6);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(stringDay.toString()));
        calendar.set(Calendar.MONTH, Integer.parseInt(stringMonth.toString()) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(stringYear.toString()));
        date.setTime(calendar.getTimeInMillis());
        return date;
    }

    public Timestamp stringToDateConversionStart() {
        return getTimestamp(dateStart);
    }

    public Timestamp stringToDateConversionEnd() {
        return getTimestamp(dateEnd);
    }
}
