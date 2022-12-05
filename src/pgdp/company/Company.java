package pgdp.company;
import pgdp.tree.Tree;

import java.util.*;

public class Company {

    private Employee CEO;
    private Tree<Integer> employeesTree;
    private Map<Integer,Employee> employees;
    private Queue<Integer> availableIDs;
    private static int availableID = 1;
    private String name;

    public Company(String name, Employee CEO) {
        this.name = name;
        this.CEO = CEO;
        employeesTree = new Tree<>(0);
        availableIDs = new PriorityQueue<>();
        employees = new HashMap<>();
        employees.put(0,CEO);
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee newEmployee) {
        if (employees.get(newEmployee.getBoss().getID()) != null){
            newEmployee.setID(availableIDs.stream().sorted().findFirst().orElse(availableID));
            if(newEmployee.getID() == availableID)
                availableID++;
            employeesTree.insert(newEmployee.getID(), newEmployee.getBoss().getID());
            employees.put(newEmployee.getID(), newEmployee);
            availableIDs.remove(newEmployee.getID());
        }
    }

    public void fireEmployee(int ID)
    {
        Employee emp = employees.get(ID);
        if (emp != null && ID != CEO.getID()){
            Set<Employee> set = employeeSet(emp);
            Employee empBoss = emp.getBoss();
            while (set.iterator().hasNext())
                set.iterator().next().setBoss(empBoss);
            employeesTree.remove(ID);
            employees.remove(ID);
            availableIDs.add(ID);
        }
    }
    public Set<Employee> employeeSet(Employee employee)
    {
        Set<Employee> set = new HashSet<>();
        for (var i : employees.values())
        {
            if (i != null && i.getBoss().getID() == employee.getID())
                set.add(i);
        }
        return set;
    }
    public Employee findCommonBoss(Employee e1, Employee e2) {
        int x = employeesTree.LCA(e1.getID(),e2.getID());
        for (var i:employees.values()) {
            if (i.getID() == x)
                return i;
        }
        return null;
    }

    public Employee findByID(int ID) {
        for (var i: employees.values()) {
            if (i.getID() == ID){
                return i;
            }
        }
        return null;
    }

}
