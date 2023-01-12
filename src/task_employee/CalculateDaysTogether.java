package task_employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculateDaysTogether {
    public static Long calculateDaysTogether(Employee emp1, Employee emp2){
        LocalDate startDay;
        LocalDate endDay;
        int start = emp1.getDateFrom().compareTo(emp2.getDateFrom());

        if(start >= 0){
            startDay = emp2.getDateFrom();
        }
        else {
            startDay = emp1.getDateFrom();
        }

        int end = emp1.getDateTo().compareTo(emp2.getDateTo());
        if(end >=0 ){
            endDay = emp2.getDateTo();
        }
        else {
            endDay = emp1.getDateTo();
        }

        return ChronoUnit.DAYS.between(startDay, endDay);
    }
}