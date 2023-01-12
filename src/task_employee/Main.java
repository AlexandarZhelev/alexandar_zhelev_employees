package task_employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static task_employee.CalculateDaysTogether.calculateDaysTogether;
import static task_employee.ReadCsvFile.readCsvFile;

public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = readCsvFile("C:\\Users\\Alex\\Desktop\\Employees\\Employee.csv");
        Map<Long, List<Employee>> pairOfEmployees = new HashMap<>();

        for(Employee employee : employeeList){
            if(!pairOfEmployees.containsKey(employee.getProjectID())){
                pairOfEmployees.put(employee.getProjectID(), new ArrayList<>());
            }

            pairOfEmployees.get(employee.getProjectID()).add(employee);
        }

        Map<Long, Long[]> result = new HashMap<>();

        for(Long projectID : pairOfEmployees.keySet()){
            for(int i = 0; i < pairOfEmployees.get(projectID).size(); i++){
                Employee employee1 = pairOfEmployees.get(projectID).get(i);

                for(int j = i + 1; j < pairOfEmployees.get(projectID).size(); j++){
                    Employee employee2 = pairOfEmployees.get(projectID).get(j);
                    Long workingDays;

                    if(employee1.getDateFrom().isBefore(employee2.getDateTo()) ||
                    employee1.getDateFrom().equals(employee2.getDateTo())){
                        workingDays = calculateDaysTogether(employee1, employee2);

                        if(!result.containsKey(projectID)){
                            result.put(projectID, new Long[]{0L, 0L, 0L});
                        }

                        if(result.get(projectID)[0] <= workingDays){
                            result.put(projectID, new Long[]{workingDays, employee1.getEmpID(), employee2.getEmpID()});
                        }
                    }
                }
            }
        }

        for(Long projectID : result.keySet()){
            System.out.println("Employee ID " + result.get(projectID)[1]
                    + ", Employee ID " + result.get(projectID)[2]
                    + ", Project " + projectID
                    + ", Days " + result.get(projectID)[2]);
        }
    }

    CalculateDaysTogether daysTogether = new CalculateDaysTogether();
    static ReadCsvFile readCsvFile = new ReadCsvFile();
    CreateEmployee createEmployee; {
        createEmployee = new CreateEmployee();
    }

}