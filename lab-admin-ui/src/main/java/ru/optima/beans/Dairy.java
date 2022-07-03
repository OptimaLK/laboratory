package ru.optima.beans;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.optima.persist.model.equipments.Bag;
import ru.optima.repr.BagRepr;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private ArrayList<Integer> listHour;
    private Bag bag;
    private Timestamp dateCalendar = new Timestamp(System.currentTimeMillis());

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

    public Long todayCalendar() {
        return new Date().getTime();
    }

    public Long todayPlusMonthCalendar() {
        return new Date().getTime() + 2800000000l;
    }

    public int todayDayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public String todayMonth() {
        return monthNames[calendar.get(Calendar.MONTH)];
    }
    public int todayDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setData(int year, int month, int day) {
       calendar.set(year, month, day );
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

    public String DayMonthAndYear() {
        return todayDay() + " " + monthNames[currentMonth()] + " " + currentYear();
    }

    public void minusDay() {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
    }
    public void plusDay() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
    }

    public void listHour() {
        listHour = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            listHour.add(i);
        }
    }
    public ArrayList<Integer> getListHour(){
        return listHour;
    }

    public boolean bagThatDay(int day, int month, int year, Timestamp birthTime, Timestamp lifeTime) {
        final long SEVEN_HOUR = 7 * 60 * 60 * 1000;
        Calendar cBT = Calendar.getInstance();
        Calendar cLT = Calendar.getInstance();
        cBT.setTimeInMillis(birthTime.getTime() - SEVEN_HOUR);
        cLT.setTimeInMillis(lifeTime.getTime() - SEVEN_HOUR);

        cBT.set(Calendar.HOUR_OF_DAY, 0);
        cBT.set(Calendar.MINUTE, 0);
        cLT.set(Calendar.HOUR_OF_DAY, 23);
        cLT.set(Calendar.MINUTE, 59);

        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, day);
        today.set(Calendar.MONTH, month);
        today.set(Calendar.YEAR, year);

        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts.setTime(today.getTimeInMillis());
        long thisTime = ts.getTime();
        ts.setTime(cBT.getTimeInMillis());
        long birthTimeBag = ts.getTime();
        ts.setTime(cLT.getTimeInMillis());
        long lifeTimeBag = ts.getTime();
        return thisTime >= birthTimeBag && thisTime <= lifeTimeBag;
    }

    public boolean bagThatHour(int hour, int day, int month, int year, Timestamp birthTime, Timestamp lifeTime) {
        final long SEVEN_HOUR = 7 * 60 * 60 * 1000;
        Calendar cBT = Calendar.getInstance();
        Calendar cLT = Calendar.getInstance();
        cBT.setTimeInMillis(birthTime.getTime() - SEVEN_HOUR);
        cLT.setTimeInMillis(lifeTime.getTime() - SEVEN_HOUR);

        Calendar today = Calendar.getInstance();
        today.set(Calendar.DAY_OF_MONTH, day);
        today.set(Calendar.MONTH, month);
        today.set(Calendar.YEAR, year);
        today.set(Calendar.HOUR_OF_DAY, hour);

        if (cLT.get(Calendar.DAY_OF_MONTH) == day) {
            if (cLT.get(Calendar.HOUR_OF_DAY) < hour) {
                return false;
            }
        }
        if (cBT.get(Calendar.DAY_OF_MONTH) == day) {
            if (cBT.get(Calendar.HOUR_OF_DAY) > hour) {
                return false;
            }
        }
        return true;
    }
}
