package task_employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateEmployee {
    public static Employee createEmployee(String[] attributes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        long empID = Long.parseLong(attributes[0]);
        long projectID = Long.parseLong(attributes[1]);
        LocalDate dateFrom = LocalDate.parse(attributes[2], formatter);
        LocalDate dateTo;

        if (attributes[3].equalsIgnoreCase("null")){
            dateTo = LocalDate.now();
        }
        else {
            dateTo = LocalDate.parse(attributes[3], formatter);
        }

        return new Employee((long) Math.toIntExact(empID),
                (long) Math.toIntExact(projectID), dateFrom, dateTo);
    }
}
