import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Hospital {
    static ArrayList<Employee> employees = new ArrayList<Employee>();
    static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    static ArrayList<Personal> personals = new ArrayList<Personal>();
    static ArrayList<Patient> patients = new ArrayList<Patient>();
    static ArrayList<Receipt> receipts = new ArrayList<Receipt>();


    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public static void addPersonal(Personal personal) {
        personals.add(personal);
    }

    public static void addPatient(Patient patient) {
        patients.add(patient);
    }

    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public static void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    public static void removePersonal(Personal personal) {
        personals.remove(personal);
    }

    public static void removePatient(Patient patient) {
        patients.remove(patient);
    }

    public static void dataWrite() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc",
                    "root", "M13831383mR");

            Statement statement = connection.createStatement();

            statement.execute("USE jdbc");

            String sql = "DELETE FROM doctor";
            statement.executeUpdate(sql);

            sql = "DELETE FROM patient";
            statement.executeUpdate(sql);

            sql = "DELETE FROM employee";
            statement.executeUpdate(sql);

            sql = "DELETE FROM receipt";
            statement.executeUpdate(sql);

            sql = "DELETE FROM admin";
            statement.executeUpdate(sql);

            for (Doctor i : doctors) {

                String sqlQuery = "INSERT INTO doctor VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setInt(1, i.getDoctorID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, i.getGender());
                preparedStatement.setInt(4, i.getAge());
                preparedStatement.setString(5, i.getAddress());
                preparedStatement.setString(6, i.getPhone());
                preparedStatement.setString(7, i.getEmail());
                preparedStatement.setString(8, i.getMedicalExpertise());
                preparedStatement.setString(9, i.getDaysWork());
                preparedStatement.setInt(10, i.getShiftHours());
                preparedStatement.setInt(11, i.getCareerRecord());
                preparedStatement.setBoolean(12,i.isAvailable());
                preparedStatement.setFloat(13,i.getRating());
                preparedStatement.setInt(14,i.getSalary());

                preparedStatement.executeUpdate();
            }

            for (Patient i : patients) {

                String sqlQuery = "INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setInt(1, i.getPatientID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, i.getGender());
                preparedStatement.setInt(4, i.getAge());
                preparedStatement.setString(5, i.getAddress());
                preparedStatement.setString(6, i.getPhone());
                preparedStatement.setString(7, i.getEmail());
                preparedStatement.setString(8, i.getIllness());
                preparedStatement.setString(9, i.getUsedDrugs());
                String state = "false";
                if (i.isInherited()) {
                    state = "true";
                }
                preparedStatement.setString(10, state);
                state = "false";
                if (i.isMedicalCase()) {
                    state = "true";
                }
                preparedStatement.setString(11, state);
                state = "false";
                if (i.isDrugAddict()) {
                    state = "true";
                }
                preparedStatement.setString(12, state);
                preparedStatement.setString(13, i.getAllergyToDrugs());

                preparedStatement.executeUpdate();
            }

            for (Employee i : employees) {

                String sqlQuery = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setInt(1, i.getEmployeeID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, i.getGender());
                preparedStatement.setInt(4, i.getAge());
                preparedStatement.setString(5, i.getAddress());
                preparedStatement.setString(6, i.getPhone());
                preparedStatement.setString(7, i.getEmail());
                preparedStatement.setString(8, i.getGrade());
                preparedStatement.setString(9, i.getRole());
                preparedStatement.setInt(10, i.getCareerRecord());
                preparedStatement.setString(11, i.getDaysWork());
                preparedStatement.setInt(12, i.getShiftHours());
                preparedStatement.setInt(13,i.getSalary());

                preparedStatement.executeUpdate();
            }

            for (Receipt i : receipts) {

                String sqlQuery = "INSERT INTO receipt VALUES (?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setInt(1, i.getReceiptID());
                preparedStatement.setInt(2, i.getDoctor().getDoctorID());
                preparedStatement.setInt(3, i.getPatient().getPatientID());
                preparedStatement.setInt(4, i.getCost());
                preparedStatement.setString(5, i.getDate());
                String state = "false";
                if (i.isEmergency()) {
                    state = "true";
                }
                preparedStatement.setString(6, state);


                preparedStatement.executeUpdate();
            }

            for (int i=0;i<Admin.getRequests().size();i++){
                String sqlQuery = "INSERT INTO admin VALUES (?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setString(1, Admin.getRequests().get(i));

                preparedStatement.executeUpdate();

            }

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void dataRead() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc", "root", "M13831383mR");
            Statement statement = conn.createStatement();

            ResultSet patientRS = statement.executeQuery("SELECT * FROM patient");


            while (patientRS.next()) {
                String name = patientRS.getString("name");
                String gender = patientRS.getString("gender");
                int age = patientRS.getInt("age");
                String address = patientRS.getString("address");
                String phone = patientRS.getString("phone");
                String email = patientRS.getString("email");
                String illness = patientRS.getString("illness");
                String usedDrugs = patientRS.getString("usedDrogs");
                String isInherited = patientRS.getString("isInherited");
                boolean i = false;
                if (isInherited.compareTo("true") == 0) {
                    i = true;
                }
                String medicalCase = patientRS.getString("medicalCase");
                boolean j = false;
                if (medicalCase.compareTo("true") == 0) {
                    j = true;
                }
                String isDrugAddict = patientRS.getString("isDrugAddict");
                boolean k = false;
                if (isDrugAddict.compareTo("true") == 0) {
                    k = true;
                }
                String allergyToDrugs = patientRS.getString("allergyToDrugs");


                Patient patient = new Patient(name, gender, age, address, phone, email, illness, usedDrugs, i, j, k,
                        allergyToDrugs);
                Hospital.patients.add(patient);
            }

            ResultSet doctorRS = statement.executeQuery("SELECT * FROM doctor");

            while (doctorRS.next()) {
                String name = doctorRS.getString("name");
                String gender = doctorRS.getString("gender");
                int age = doctorRS.getInt("age");
                String address = doctorRS.getString("address");
                String phone = doctorRS.getString("phone");
                String email = doctorRS.getString("email");
                String medicalExpertise = doctorRS.getString("medicalExpertise");
                String dayswork = doctorRS.getString("dayswork");
                int shiftHours = doctorRS.getInt("shiftHours");
                int careerRecord = doctorRS.getInt("careerRecord");
                boolean isAvailable = doctorRS.getBoolean("isAvailable");
                float rating = doctorRS.getFloat("Rating");
                int salary = doctorRS.getInt("Salary");

                Doctor doctor = new Doctor(name, gender, age, address, phone, email, medicalExpertise,
                        dayswork, shiftHours, careerRecord, isAvailable, rating, salary);
                Hospital.doctors.add(doctor);
            }

            ResultSet employeeRS = statement.executeQuery("SELECT * FROM employee");

            while (employeeRS.next()) {
                String name = employeeRS.getString("name");
                String gender = employeeRS.getString("gender");
                int age = employeeRS.getInt("age");
                String address = employeeRS.getString("address");
                String phone = employeeRS.getString("phone");
                String email = employeeRS.getString("email");
                String grade = employeeRS.getString("grade");
                String role = employeeRS.getString("role");
                int careerRecord = employeeRS.getInt("careerRecord");
                String dayswork = employeeRS.getString("daysWork");
                int shiftHours = employeeRS.getInt("shiftHours");
                int salary = employeeRS.getInt("salary");


                Employee employee = new Employee(name, gender, age, address, phone, email, grade, role, careerRecord,
                        dayswork, shiftHours, salary);
                Hospital.employees.add(employee);
            }

            ResultSet receiptRS = statement.executeQuery("SELECT * FROM receipt");

            while (receiptRS.next()) {
                int doctorID = receiptRS.getInt("doctorID");
                int PatientID = receiptRS.getInt("PatientID");
                int cost = receiptRS.getInt("cost");
                String date = receiptRS.getString("date");
                String isEmergency = receiptRS.getString("isEmergency");

                boolean i = false;
                if (isEmergency.compareTo("true") == 0) {
                    i = true;
                }

                Doctor doctor ;
                int x=-1;
                for (int in=0;in<doctors.size();in++) {
                    if (doctors.get(in).getDoctorID() == doctorID) {
                        x=in;
                        break;
                    }
                }
                doctor = doctors.get(x);

                Patient patient ;
                int y=-1;
                for (int j=0;j<patients.size();j++){
                    if (patients.get(j).getPatientID() == PatientID){
                        y=j;
                        break;
                    }
                }
                patient=patients.get(y);


                Receipt receipt = new Receipt(doctor,patient, cost, i,date);
                receipts.add(receipt);

            }

            ResultSet adminRS = statement.executeQuery("SELECT * FROM admin");

            while (adminRS.next()){
                String request = receiptRS.getString("Request");
                Admin.addRequest(request);
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void doctorSalaryIncrease(Doctor doctor){
        int previous = doctor.getSalary();
        int increase = (doctor.getSalary()*20)/100;
        int present = previous + increase;
        doctor.setSalary(present);
    }

    public static void doctorSalaryDecrease(Doctor doctor){
        int previous = doctor.getSalary();
        int decrease = (doctor.getSalary()*10)/100;
        int present = previous - decrease;
        doctor.setSalary(present);
    }

    public static void employeeSalaryIncrease(Employee employee){
        int previous = employee.getSalary();
        int increase = (employee.getSalary()*20)/100;
        int present = previous + increase;
        employee.setSalary(present);
    }

    public static void employeeSalaryDecrease(Employee employee){
        int previous = employee.getSalary();
        int decrease = (employee.getSalary()*10)/100;
        int present = previous - decrease;
        employee.setSalary(present);
    }

    public static ArrayList<Doctor> availableDoctors(){
        ArrayList<Doctor> available = new ArrayList<>();
        for (Doctor d : doctors){
            if (d.isAvailable()==true){
                available.add(d);
            }
        }
        return available;
    }

//    public static void main(String[] args) {
//        dataRead();
//        System.out.println(employees.get(0).getName());
//        System.out.println(availableDoctors().get(0).isAvailable());
//        System.out.println(doctors.get(0).isAvailable());
//        Doctor d3 = new Doctor("Hamzeh", "male", 23, "tehran", "0933@123456789", "nurseali@gmail.com", "Brain", "sun", 8, 5);
//        Employee d1= new Employee("Ali", "male", 23, "tehran", "0933@123456789", "nurseali@gmail.com", "Senior", "Nurse", 2, "SAT, SUN, MON, TUE, WED, THU, FRI", 4);
//        Employee d2= new Employee("Majid", "male", 50, "Karaj", "0931@987654321", "nurs@gmail.com", "Junior", "Nurse", 10, "SAT, SUN, MON", 4);
//        employees.add(d1);
//        employees.add(d2);
//        doctors.add(d3);
//        dataWrite();
//    }
}