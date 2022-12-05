package pgdp.company;

import java.util.Scanner;

public class CompanySimulation {
    public static void main(String[] args) {
        simulation("Novak","ATP");
    }

    private static void simulation(String ceoName, String companyName) {
        Employee ceo = new Employee(ceoName,null);
        Employee employee1 = new Employee("saba", ceo);
        ceo.setID(0);
        Company company = new Company(companyName,ceo);
        Scanner scanner = new Scanner(System.in);
        String numberOfQueries = scanner.nextLine();
        company.addEmployee(employee1);

        for (int k = 0; k < Integer.parseInt(numberOfQueries); k++) {
            String query = scanner.nextLine();
            try {
                sim(company, query);
            }catch (NumberFormatException e)
            {
                e.printStackTrace();
            }

        }
        scanner.close();
    }
    public static void sim(Company company, String query){
        String[] splitString = query.split(" ");
//            System.out.println(splitString.length);
        if (splitString.length == 2){
            if (company.getEmployees().get(Integer.parseInt(splitString[1])) != null && splitString[0].equals("Fire")){
                company.fireEmployee(company.getEmployees().get(Integer.parseInt(splitString[1])).getID());
                return;
            }
            else if (company.getEmployees().get(Integer.parseInt(splitString[1])) != null && splitString[0].equals("EmployeeWithID")){
                System.out.println(company.getEmployees().get(Integer.parseInt(splitString[1])));
                return;
            }else
                System.out.println("does not exist");
        }
        if (splitString.length == 3){//Add name ID
            if (splitString[0].equals("FindCommonBoss") && company.getEmployees().get(Integer.parseInt(splitString[1]))!= null
                    && company.getEmployees().get(Integer.parseInt(splitString[2]))!= null){
                System.out.println(company.findCommonBoss(company.findByID(Integer.parseInt(splitString[1]))
                        , company.findByID(Integer.parseInt(splitString[2]))));
            }
           else if (company.getEmployees().get(Integer.parseInt(splitString[2])) != null && splitString[0].equals("Add")){
                var newEmployee = new Employee(splitString[1], company.getEmployees().get(Integer.parseInt(splitString[2])));
                company.addEmployee(newEmployee);
            }else {
                System.out.println("boss does not exist");
            }
        }
    }
}
