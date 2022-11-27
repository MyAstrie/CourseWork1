import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.Clock.system;

public class Main {

    static Employee[] employee = new Employee[10];

    public static void main(String[] args) {
        var firstEmployee = new Employee("Gene Gray Flith", 1, 33);
        var secondEmployee = new Employee("Andy Panda Freek", 2, 2);
        var thirdEmployee = new Employee("Cate Forest Long", 3, 3);
        var fourthEmployee = new Employee("Don Smith Forse", 4, 211);
        var fifthEmployee = new Employee("Gene Gray Donth", 2, 332);

        employee[0] = firstEmployee;
        employee[1] = secondEmployee;
        employee[7] = thirdEmployee;
        employee[3] = fourthEmployee;

        EmployeeBook employeeBook = new EmployeeBook(firstEmployee);
        employeeBook.add(secondEmployee);
        employeeBook.add(thirdEmployee);
        employeeBook.add(fourthEmployee);
        employeeBook.add(fifthEmployee);
        employeeBook.printEmployees();

        employeeBook.removeEmployee(3);

        System.out.println("--------------");
        employeeBook.printEmployees();

        System.out.println("--------------");
        employeeBook.changeEmployeeSalary("Gene Gray Donth", 200);
        employeeBook.printEmployees();

        System.out.println("--------------");
        employeeBook.changeEmployeeDepartment(4, 100);
        employeeBook.printEmployees();

        System.out.println("--------------");
        var employeeBook2 = new EmployeeBook(employee);
        employeeBook2.printEmployees();

        employeeBook.printDepartmentAndEmployeesFullNames();
        System.out.println("--------------");
        employeeBook.indexWagesDepartment(100, 24);
        employeeBook.printEmployees();
    }
}