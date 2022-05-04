package ru.optima.beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
@Component
public class Dairy {

    private long countMonth;
    private long countYear;
    private LocalDate date = LocalDate.now();
    private int month = date.getMonthValue();
    private int today = date.getDayOfMonth();
    private ArrayList<Integer> listDay;

    private Calendar calendar = Calendar.getInstance();
    String[] monthNames = { "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь" };

    public ArrayList<Integer> sevenDay(){
        listDay = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            listDay.add(i + 1);
        }
        return listDay;
    }

    public int firstDayOfMonth() {
        int count = -1;
        Calendar cal = calendar;
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:
                count = 1;
                break;
            case Calendar.TUESDAY:
                count = 2;
                break;
            case Calendar.WEDNESDAY:
                count = 3;
                break;
            case Calendar.THURSDAY:
                count = 4;
                break;
            case Calendar.FRIDAY:
                count = 5;
                break;
            case Calendar.SATURDAY:
                count = 6;
                break;
            case Calendar.SUNDAY:
                count = 7;
                break;
        }
        calendar = cal;
        return count;
    }

    public int lastDayOfMonth() {
        return calendar.getActualMaximum(Calendar.DATE);
    }

    public List<Integer> countWeekOfMonth(){
        List<Integer> arrayList = new ArrayList<>();
        Calendar cal = calendar;
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        if (calendar.get(Calendar.WEEK_OF_MONTH) == 0){
            for (int i = 1; i < calendar.getActualMaximum(Calendar.WEEK_OF_MONTH) + 2; i++) {
                arrayList.add(i);
            }
        } else {
            for (int i = 1; i < calendar.getActualMaximum(Calendar.WEEK_OF_MONTH) + 1; i++) {
                arrayList.add(i);
            }
        }
        calendar = cal;
        return arrayList;
    }

    public void today() {
        calendar = Calendar.getInstance();
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

    public int currentMonth(){
        return (int) (calendar.get(Calendar.MONTH));
    }

    public int currentYear(){
        return (int) (calendar.get(Calendar.YEAR));
    }

    public void plusMonth(){
        calendar.add(Calendar.MONTH, 1);
    }

    public void minusMonth(){
        calendar.add(Calendar.MONTH, -1);
    }

    public long plusYear(){
        return countYear++;
    }

    public long minusYear(){
        return countYear--;
    }

    public String monthAndYear() {
        return monthNames[currentMonth()] + " " + currentYear();
    }


}
