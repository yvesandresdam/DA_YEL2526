import java.util.HashSet;
import java.util.Set;

public class Company {
    private String name;
    private Set<Employee> employees = new HashSet<>();
    private Set<Client> clients = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int clientsCount(){
        return clients.size();
    }
    
    public int employeesCount() {
        return employees.size();
    }
}
