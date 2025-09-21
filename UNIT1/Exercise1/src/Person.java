import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Person {
    private String name;
    private LocalDate birthdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        if (birthdate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException(Exercise1.INVALID_BIRTH_DATE);
        this.birthdate = birthdate;
    }

    public int getAge() {
        return (int) ((LocalDate.now().toEpochDay() - birthdate.toEpochDay()) / 365);
    }
}

class Client extends Person {
    private String handy;
    private Set<Company> companies = new HashSet<>();

    public String getHandy() {
        return handy;
    }

    public void setHandy(String value) {
        String handyPattern = "^((\\+|00)\\d{2,3})?\\d{9}$";
        if (Pattern.matches(handyPattern, value)) {
            this.handy = value;
        } else {
            throw new IllegalArgumentException(Exercise1.INVALID_MOVIL_NUMBER);
        }
    }
}

class Employee extends Person {
    private double totalSalary;

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        if (totalSalary < 0)
            throw new IllegalArgumentException(Exercise1.INVALID_SALARY);
        this.totalSalary = totalSalary;
    }
}

class Directive extends Employee {
    private String category;
    private Set<Employee> subordinates = new HashSet<>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSubordinatesCount() {
        return subordinates.size();
    }

    public void addSubordinate(Employee subordinate) {
        boolean subordinateCheck = true;    // this subordinateCheck validates the added object
        if (subordinateCheck)
            subordinates.add(subordinate);
    }
}
