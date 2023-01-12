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

//    public static Long calculateDaysTogether(Employee emp1, Employee emp2){
//        LocalDate startDay;
//        LocalDate endDay;
//        int start = emp1.getDateFrom().compareTo(emp2.getDateFrom());
//
//        if(start >= 0){
//            startDay = emp2.getDateFrom();
//        }
//        else {
//            startDay = emp1.getDateFrom();
//        }
//
//        int end = emp1.getDateTo().compareTo(emp2.getDateTo());
//        if(end >=0 ){
//            endDay = emp2.getDateTo();
//        }
//        else {
//            endDay = emp1.getDateTo();
//        }
//
//        return ChronoUnit.DAYS.between(startDay, endDay);
//    }

    CalculateDaysTogether daysTogether = new CalculateDaysTogether();
    static ReadCsvFile readCsvFile = new ReadCsvFile();
    CreateEmployee createEmployee; {
        createEmployee = new CreateEmployee();
    }
//    private static List<Employee> readCsvFile(String file) {
//        List<Employee>employeeList = new ArrayList<>();
//        Path path = Paths.get(file);
//
//        try(BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
//            String line = bufferedReader.readLine();
//
//            while (line != null){
//                String[] attributes = line.split(", ");
//                Employee employee = createEmployee(attributes);
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return employeeList;
//    }

//    static Employee createEmployee(String[] attributes) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
//        Long empID = Long.parseLong(attributes[0]);
//        Long projectID = Long.parseLong(attributes[1]);
//        LocalDate dateFrom = LocalDate.parse(attributes[2], formatter);
//        LocalDate dateTo;
//
//        if (attributes[3].equalsIgnoreCase("null")){
//            dateTo = LocalDate.now();
//        }
//        else {
//            dateTo = LocalDate.parse(attributes[3], formatter);
//        }
//
//        return new Employee((long) Math.toIntExact(empID),
//                (long) Math.toIntExact(projectID), dateFrom, dateTo);
//    }
}