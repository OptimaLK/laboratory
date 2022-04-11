package ru.optima.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
@Component
public class Dairy {

    LocalDate date = LocalDate.now();
    int month = date.getMonthValue();
    int today = date.getDayOfMonth();
    ArrayList<Integer> listDay = new ArrayList<>();

    Calendar calendar = Calendar.getInstance();
    String[] monthNames = { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" };


    public ArrayList<Integer> addsevenDay() {
        for (int i = 0; i < 7; i++) {
            listDay.add(i + 1);
        }
        return listDay;
    }

    public int firstDayOfMonth() {
        DayOfWeek dayOfWeek = date.minusDays(today - 1).getDayOfWeek();
        return dayOfWeek.getValue();
    }

    public int lastDayOfMonth() {
        return calendar.getActualMaximum(Calendar.DATE);
    }

    public List<Integer> countWeekOfMonth(){
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < calendar.getActualMaximum(Calendar.WEEK_OF_MONTH) +1; i++) {
            arrayList.add(i + 1);
        }
        return arrayList;
    }

    public Calendar today() {
        return calendar;
    }

    public int todayDayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public String todayMonth() {
        return monthNames[calendar.get(Calendar.MONTH)];
    }

    public int todayYear() {
        return calendar.get(Calendar.YEAR);
    }

    public String monthAndYear() {
        return monthNames[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);
    }

}
