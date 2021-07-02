package Model_Package;

import java.util.Observable;

public class Employees extends Observable {

        private int id;
        private String name;
        private String email;
        private String birthDate;


        public Employees() {
        }


        public Employees(int id, String name, String email, String birthDate) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.birthDate = birthDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            setChanged();
            notifyObservers();
        }

        public void updateEmployee(int id, String name, String email, String birthDate) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.birthDate = birthDate;
            setChanged();
            notifyObservers();
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
            setChanged();
            notifyObservers();
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
            setChanged();
            notifyObservers();
        }
        public String getBirthDate() {
            return birthDate;
        }
        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
            setChanged();
            notifyObservers();
        }

}
