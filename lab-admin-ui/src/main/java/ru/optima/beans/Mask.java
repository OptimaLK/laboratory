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

    private Timestamp getTimestamp(String dateString, String hourString) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        StringBuilder stringDay = new StringBuilder(dateString);
        StringBuilder stringMonth = new StringBuilder(dateString);
        StringBuilder stringYear = new StringBuilder(dateString);
        StringBuilder stringHour = new StringBuilder(hourString);
        StringBuilder stringMin = new StringBuilder(hourString);
        stringDay.delete(2, stringDay.length());
        stringMonth.delete(0, 3).delete(2, stringMonth.length());
        stringYear.delete(0, 6);
        stringHour.delete(2, stringHour.length());
        stringMin.delete(0, 3);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(stringDay.toString()));
        calendar.set(Calendar.MONTH, Integer.parseInt(stringMonth.toString()) - 1);
        calendar.set(Calendar.YEAR, Integer.parseInt(stringYear.toString()));
        calendar.set(Calendar.HOUR, Integer.parseInt(stringHour.toString()));
        calendar.set(Calendar.MINUTE, Integer.parseInt(stringMin.toString()));

        date.setTime(calendar.getTimeInMillis());
        return date;
    }

    public Timestamp stringToDateConversionStart() {
        return getTimestamp(dateStart, hoursStart);
    }

    public Timestamp stringToDateConversionEnd() {
        return getTimestamp(dateEnd, hoursEnd);
    }
}
