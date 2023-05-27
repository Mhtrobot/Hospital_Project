import java.sql.*;
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

            for (Doctor i : doctors) {

                String sqlQuery = "INSERT INTO doctor VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

                String sqlQuery = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";

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

                preparedStatement.executeUpdate();
            }

            for (Receipt i : receipts) {

                String sqlQuery = "INSERT INTO receipt VALUES (?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setInt(1, i.getRecieptID());
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

            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void dataRead() {
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Hospital", "root", "M13831383mR");

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
                String usedDrugs = patientRS.getString("usedDrugs");
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


                Patient patient = new Patient(name, gender, age, address, phone, email, illness, usedDrugs, i, j, k, allergyToDrugs);
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

                Doctor doctor = new Doctor(name, gender, age, address, phone, email, medicalExpertise, dayswork, shiftHours, careerRecord);
                doctors.add(doctor);
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


                Employee employee = new Employee(name, gender, age, address, phone, email, grade, role, careerRecord, dayswork, shiftHours);
                employees.add(employee);
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

                Receipt receipt = new Receipt(doctor,patient,cost,i);
                receipts.add(receipt);

            }

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {
        Doctor x =new Doctor("Mahdi Rahimi", "male",19,"tehran","0912","@gmail","expert","sa",15,2);
        Hospital.addDoctor(x);
        Patient y =new Patient("Mahdi Rahimi", "male",19,"tehran","0912","@gmail","blood","",false,true,false,"");
        patients.add(y);
        Employee z=new Employee("Mahdi Rahimi", "male",19,"tehran","0912","@gmail","license","",5,"a",8);
        employees.add(z);
        Doctor x2 =new Doctor("Matin Aliakbari", "male",19,"tehran","0912","@gmail","expert","sa",15,2);
        Hospital.addDoctor(x2);
        Patient y2 =new Patient("Ali Ahmadi", "male",19,"tehran","0912","@gmail","blood","",false,true,false,"");
        patients.add(y2);
        Receipt r = new Receipt(x,y2,90000,true);
        receipts.add(r);
        Receipt r2 = new Receipt(x2, y, 78000, false);
        receipts.add(r2);
        Hospital.dataWrite();
        Hospital.dataRead();
        System.out.println(patients.get(0));
        System.out.println(employees.get(0));
        System.out.println(doctors.get(0));
        System.out.println(receipts.get(0));
    }
}