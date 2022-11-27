import java.util.Objects;

public class Employee {

    //region Variables
    private String fullName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer department;
    private Double salary;
    private static Integer idCounter = 0;
    private final Integer id;
    //endregion

    //region Getters
    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
    //endregion

    //region Setters
    public void setFullName(String fullName) {
        if(fullName.split(" ").length > 3 || fullName.matches(".*\\d.*"))
            throw new IllegalArgumentException("Имя, Фамилия и Отчество не должны содаержать чисел");
        this.fullName = fullName;
        this.firstName = fullName.split(" ")[0];
        this.middleName = fullName.split(" ")[1];
        this.lastName = fullName.split(" ")[2];
    }

    public void setFirstName(String firstName) {
        if(firstName.matches(".*\\d.*"))
            throw new IllegalArgumentException("Имя не должно содаержать чисел");
        this.firstName = firstName;
        this.fullName.split(" ")[0] = firstName;
    }

    public void setMiddleName(String middleName) {
        if(middleName.matches(".*\\d.*"))
            throw new IllegalArgumentException("Фамилия не должно содаержать чисел");
        this.middleName = middleName;
        this.fullName.split(" ")[1] = middleName;
    }

    public void setLastName(String lastName) {
        if(lastName.matches(".*\\d.*"))
            throw new IllegalArgumentException("Отчество не должно содаержать чисел");
        this.lastName = lastName;
        this.fullName.split(" ")[2] = lastName;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public <N> void setSalary(N salary) {
        if (Double.parseDouble(salary.toString()) < 0)
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");
        this.salary = Double.parseDouble(salary.toString());
    }
    //endregion

    //region Constructors
    public <V> Employee(String fullName, Integer department, V salary ) {
        if(fullName.split(" ").length > 3 || fullName.matches(".*\\d.*"))
            throw new IllegalArgumentException("Имя, Фамилия и Отчество не должны содаержать чисел");
        else if (Double.parseDouble(salary.toString()) < 0)
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");

        this.fullName = fullName;
        this.firstName = fullName.split(" ")[0];
        this.middleName = fullName.split(" ")[1];
        this.lastName = fullName.split(" ")[2];
        this.department = department;
        this.id = idCounter++;
        this.salary = Double.parseDouble(salary.toString());
    }

    public <V> Employee(String firstName, String middleName, String lastName, Integer department, V salary) {
        if(firstName.matches(".*\\d.*") || middleName.matches(".*\\d.*")
                || lastName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Имя, Фамилия и Отчество  не должны содаержать чисел");
        }
        else if (Double.parseDouble(salary.toString()) < 0)
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fullName = firstName + " " + middleName + " " + lastName;
        this.department = department;
        this.id = idCounter++;
        this.salary = Double.parseDouble(salary.toString());
    }
    //endregion

    //region Override toString(), equals and hashCode
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(fullName, employee.fullName)
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(middleName, employee.middleName)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(department, employee.department)
                && Objects.equals(salary, employee.salary)
                && Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, firstName, middleName, lastName, department, salary, id);
    }
    //endregion
}
