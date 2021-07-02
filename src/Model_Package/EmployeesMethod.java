package Model_Package;

import Default_Package.XmlDatabase;

import java.util.ArrayList;
import java.util.Observable;

public class EmployeesMethod extends Observable {

        private static ArrayList<Employees> employeesDatabase=new ArrayList<>();


        public EmployeesMethod() {
            if(employeesDatabase.size()==0) {
                employeesDatabase =  XmlDatabase.getEmployeesFromXml();
            }
        }

        public boolean insertInDatabase(Employees e){
            employeesDatabase.add(e);
            return true;
        }

        public boolean deleteInDatabase(Employees e){
            int index=0;
            for (Employees employees : employeesDatabase) {
                if(employees.getId()==(e.getId())){
                    employeesDatabase.remove(index);
                    return true;
                }
                index++;
            }
            return false;
        }

        public boolean updateInDatabase(Employees e){
            for (Employees employees : employeesDatabase) {
                if(employees.getId()==e.getId()){
                    updateEmployee(e, employees);
                    return true;
                }
            }
            return false;
        }

        private void updateEmployee(Employees e, Employees employees) {
            employees.setName(e.getName());
            employees.setEmail(e.getEmail());
            employees.setBirthDate(e.getBirthDate());
        }

        public ArrayList <Employees> getEmployeesDatabase() {
            return employeesDatabase;
        }

    }


