import java.text.DecimalFormat;
import java.util.*;

public class EmployeeBook {
    private final Employee[] employee;

    //region Базовая сложность
    public void printEmployees() {
        for (Employee employee : employee){
            if(employee != null){
                System.out.println(employee.toString());
            }
        }
    }

    public double getAmount(){
        return Arrays.stream(employee)
                .filter(Objects::nonNull)
                .mapToDouble(Employee::getSalary).sum();
    }

    public Employee getEmployeeWithMinSalary() {
        return Arrays.stream(employee)
                .filter(Objects::nonNull)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
    }

    public Employee getEmployeeWithMaxSalary() {
        return Arrays.stream(employee)
                .filter(Objects::nonNull)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
    }

    public double getAverageAmount(){
        return getAmount() / Arrays.stream(employee)
                .filter(Objects::nonNull)
                .count();
    }

    public void printEmployeesFullNames(){
        for(Employee employee : employee){
            if (employee != null)
                System.out.println("Ф.И.О сотркудника:" + employee.getFullName());
        }
    }

    public String[] getEmployeesFullNames(){
        var nonEmptyObjects = Arrays.stream(employee).filter(Objects::nonNull).count();
        String[] tempEmployees = new String[(int) nonEmptyObjects];
        int count = 0;
        for(Employee employee : this.employee){
            if(employee != null) {
                tempEmployees[count] = employee.getFullName();
                count++;
            }
        }
        return tempEmployees;
    }
    //endregion

    //region Повышенная сложность
    public void printWageIndexation(int percents) {
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .forEach(employee -> System.out.println("Employee{" +
                        "id=" + employee.getId() +
                        ", fullName='" + employee.getFullName() + '\'' +
                        ", department=" + employee.getDepartment() +
                        ", salary=" + new DecimalFormat( "#.###" )
                            .format(employee.getSalary() + employee.getSalary() * percents * 0.01) +
                        '}'));
    }

    public void indexWages (int percents) {
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .forEach(employee -> employee.setSalary(employee.getSalary() + employee.getSalary() * percents * 0.01));
    }

    public void printEmployeeWithMaxDepartmentSalary(int department){
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
    }

    public void printEmployeeWithMinDepartmentSalary(int department){
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new);
    }

    public double getDepartmentAmount(int department){
        return Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .mapToDouble(Employee::getSalary).sum();
    }

    public double getDepartmentAverageAmount(int department){
        return getDepartmentAmount(department) /  Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .count();
    }

    public void indexWagesDepartment (int department, int percents){
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> employee.setSalary(employee.getSalary() + employee.getSalary() * percents * 0.01));
    }

    public void printDepartmentWageIndexation (int department, int percents){
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> System.out.println("Employee{" +
                        "id=" + employee.getId() +
                        ", fullName='" + employee.getFullName() + '\'' +
                        ", department=" + employee.getDepartment() +
                        ", salary=" + new DecimalFormat( "#.###" )
                        .format(employee.getSalary() + employee.getSalary() * percents * 0.01) +
                        '}'));
    }

    public void printDepartmentEmployees(int department){
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> System.out.println("ID: " + employee.getId() + ", " +
                        "ФИО: " + employee.getFullName() + ", " +
                        "Зарплата: " + employee.getSalary()));
    }

    public void printEmployeesWithHigherSalary(int salary){
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getSalary() >= salary)
                .forEach(employee -> System.out.println("ID: " + employee.getId() + ", " +
                        "ФИО: " + employee.getFullName() + ", " +
                        "Зарплата: " + employee.getSalary()));
    }

    public void printEmployeesWithHigherSalary(double salary){
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getSalary() >= salary)
                .forEach(employee -> System.out.println("ID: " + employee.getId() + ", " +
                        "ФИО: " + employee.getFullName() + ", " +
                        "Зарплата: " + employee.getSalary()));
    }

    public void printEmployeesWithLowerSalary(int salary){
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getSalary() < salary)
                .forEach(employee -> System.out.println("ID: " + employee.getId() + ", " +
                        "ФИО: " + employee.getFullName() + ", " +
                        "Зарплата: " + employee.getSalary()));
    }

    public void printEmployeesWithLowerSalary(double salary) {
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getSalary() < salary)
                .forEach(employee -> System.out.println("ID: " + employee.getId() + ", " +
                        "ФИО: " + employee.getFullName() + ", " +
                        "Зарплата: " + employee.getSalary()));
    }
    //endregion

    //region Очень сложно
    private int checkFreeIndex() {
        for(int i = 0; i < employee.length; i++){
            if(employee[i] == null){
                return i;
            }
        }
        return -1;
    }

    public EmployeeBook(){
        this.employee = new Employee[10];
    }

    public EmployeeBook(Employee[] employee){
        this.employee = new Employee[employee.length];
        int i = 0;
        for(var value : employee){
            this.employee[i] = value;
            i++;
        }
    }

    public EmployeeBook(Employee employee) {
        this.employee = new Employee[10];
        this.employee[checkFreeIndex()] = employee;
    }

    public void add(Employee employee) {
        this.employee[checkFreeIndex()] = employee;
    }

    public void removeEmployee(int id) {
        for (int i = 0; i < employee.length; i++) {
            if(employee[i] != null && employee[i].getId() == id){
                this.employee[i] = null;
            }
        }
    }

    public void removeEmployee(String fullName) {
        for (int i = 0; i < employee.length; i++) {
            if(employee[i] != null && employee[i].getFullName().equals(fullName)){
                this.employee[i] = null;
            }
        }
    }

    public <V> void changeEmployeeSalary(String fullName, V newSalary) {
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getFullName().equals(fullName))
                .forEach(employee -> employee.setSalary(Double.parseDouble(newSalary.toString())));
    }

    public void changeEmployeeDepartment(String fullName, int newDepartment) {
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getFullName().equals(fullName))
                .forEach(employee -> employee.setDepartment(newDepartment));
    }

    public <V> void changeEmployeeSalary(int id, V newSalary) {
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getId() == id)
                .forEach(employee -> employee.setSalary(Double.parseDouble(newSalary.toString())));
    }

    public void changeEmployeeDepartment(int id, int newDepartment) {
        Arrays.stream(employee)
                .filter(Objects::nonNull)
                .filter(employee -> employee.getId() == id)
                .forEach(employee -> employee.setDepartment(newDepartment));
    }

    public String[] getDepartmentAndEmployeesFullNames(){
        var nonEmptyObjects = Arrays.stream(employee).filter(Objects::nonNull).count();
        String[] tempEmployees = new String[(int) nonEmptyObjects];
        int count = 0;
        for(Employee employee : this.employee){
            if(employee != null) {
                tempEmployees[count] = employee.getDepartment() + " " + employee.getFullName();
                count++;
            }
        }
        return tempEmployees;
    }

    public void printDepartmentAndEmployeesFullNames(){
        for(Employee employee : this.employee){
            if(employee != null) {
                System.out.println("Отдел : " + employee.getDepartment() + ", ФИО: " + employee.getFullName());
            }
        }
    }
    //endregion
}
