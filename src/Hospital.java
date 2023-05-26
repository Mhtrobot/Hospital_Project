import java.sql.*;
import java.util.ArrayList;

public class Hospital {
    static ArrayList<Employee> employees = new ArrayList<Employee>();
    static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
    static ArrayList<Personal> personals = new ArrayList<Personal>();
    static ArrayList<Patient> patients = new ArrayList<Patient>();
    static ArrayList<Receipt> receipts = new ArrayList<Receipt>();

    public static void addEmployee(Employee employee){
        employees.add(employee);
    }
    public static void addDoctor(Doctor doctor){
        doctors.add(doctor);
    }
    public static void addPersonal(Personal personal){
        personals.add(personal);
    }
    public static void addPatient(Patient patient){
        patients.add(patient);
    }

    public static void removeEmployee(Employee employee){
        employees.remove(employee);
    }
    public static void removeDoctor(Doctor doctor){
        doctors.remove(doctor);
    }
    public static void removePersonal(Personal personal){
        personals.remove(personal);
    }
    public static void removePatient(Patient patient){
        patients.remove(patient);
    }


    public static void dataWrite(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc",
                    "root", "admin");

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

            for (Doctor i : doctors){

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

            for (Patient i : patients){

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
                if (i.isInherited()){
                    state = "true";
                }
                preparedStatement.setString(10, state);
                state = "false";
                if (i.isMedicalCase()){
                    state = "true";
                }
                preparedStatement.setString(11, state);
                state = "false";
                if (i.isDrugAddict()){
                    state = "true";
                }
                preparedStatement.setString(12,state);
                preparedStatement.setString(13,i.getAllergyToDrugs());

                preparedStatement.executeUpdate();
            }

            for (Employee i : employees){

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
                preparedStatement.setInt(10,i.getCareerRecord());
                preparedStatement.setString(11,i.getDaysWork());
                preparedStatement.setInt(12,i.getShiftHours());

                preparedStatement.executeUpdate();
            }

            for (Receipt i : receipts){

                String sqlQuery = "INSERT INTO receipt VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setInt(1, i.getRecieptID());
                preparedStatement.setInt(2, i.getDoctor().getDoctorID());
                preparedStatement.setInt(3, i.getPatient().getPatientID());
                preparedStatement.setInt(4, i.getCost());
                preparedStatement.setString(5, i.getDate());
                preparedStatement.setString(6, i.getIllness());
                String state = "false";
                if (i.isEmergency()){
                    state = "true";
                }
                preparedStatement.setString(7, state);


                preparedStatement.executeUpdate();
            }



//            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt("customer_id") + ", " +
//                        resultSet.getString("first_name"));
//            }
//
//            String sql = "INSERT INTO doctor (idDoctor, name) SELECT MAX(idDoctor) + 1, '2220-01-01' FROM doctor";
//            PreparedStatement statement2 = connection.prepareStatement(sql);
//            statement2.executeUpdate();
//
//            resultSet = statement.executeQuery("SELECT * FROM doctor");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt("idDoctor"));
//            }

        }
        catch (Exception e){
            System.out.println(e);
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
    }
}