package task_employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static task_employee.CreateEmployee.createEmployee;

public class ReadCsvFile {
    public static List<Employee> readCsvFile(String file) {
        List<Employee>employeeList = new ArrayList<>();
        Path path = Paths.get(file);

        try(BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line = bufferedReader.readLine();

            while (line != null){
                String[] attributes = line.split(";");
                Employee employee = createEmployee(attributes);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return employeeList;
    }
}