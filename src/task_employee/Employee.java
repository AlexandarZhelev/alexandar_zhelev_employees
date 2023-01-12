package task_employee;

import java.time.LocalDate;
import java.util.Objects;

public class Employee implements Comparable<Employee>{
    private Long EmpID;
    private Long ProjectID;
    private LocalDate DateFrom;
    private LocalDate DateTo;

    public Employee(Long empID, Long projectID, LocalDate dateFrom, LocalDate dateTo){
        this.EmpID = empID;
        this.ProjectID = projectID;
        this.DateFrom = dateFrom;
        this.DateTo = dateTo;
    }

    public Long getEmpID() {
        return EmpID;
    }

    public void setEmpID(Long empID) {
        if(ValidateID.validateEmpID(Math.toIntExact(EmpID))){
            this.EmpID = empID;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public Long getProjectID() {
        return ProjectID;
    }

    public void setProjectID(Long projectID) {
        if(ValidateID.validateProjectID(Math.toIntExact(projectID))){
            this.ProjectID = projectID;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public LocalDate getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        if(dateFrom.equals("NULL")){
            this.DateFrom = LocalDate.now();
        }
        else if (!dateFrom.equals("NULL")) {
            this.DateFrom = LocalDate.parse(dateFrom);
        }
    }

    public LocalDate getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        if(dateTo.equals("NULL")) {
            this.DateTo = LocalDate.now();
        }
        else if (!dateTo.equals("NULL")) {
            this.DateTo = LocalDate.parse(dateTo);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmpID: " + EmpID +
                ", ProjectID: " + ProjectID +
                ", DateFrom: " + DateFrom +
                ", DateTo: " + DateTo +
                '}';
    }

    public int compareTo(Employee e){
        if(Objects.equals(ProjectID, e.ProjectID)){
            return 1;
        }

        else {
            return -1;
        }
    }
}