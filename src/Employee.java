public class Employee extends Personal{
    private String grade;
    private String role;
    private int careerRecord;
    private String daysWork;
    private int shiftHours;
    private static int ID = 0;
    private int employeeID;

    public Employee(String name, String gender, int age, String address, String phone, String email, String grade,
                    String role, int careerRecord, String daysWork, int shiftHours) {
        super(name, gender, age, address, phone, email);
        ID++;
        this.employeeID = ID;
        this.grade = grade;
        this.role = role;
        this.careerRecord = careerRecord;
        this.daysWork = daysWork;
        this.shiftHours = shiftHours;
    }

    //----------------------------------------------------------------------------getter & setter

    public String getGrade() {
        return grade;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCareerRecord() {
        return careerRecord;
    }

    public void setCareerRecord(int careerRecord) {
        this.careerRecord = careerRecord;
    }

    public String getDaysWork() {
        return daysWork;
    }

    public void setDaysWork(String daysWork) {
        this.daysWork = daysWork;
    }

    public int getShiftHours() {
        return shiftHours;
    }

    public void setShiftHours(int shiftHours) {
        this.shiftHours = shiftHours;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", grade='" + grade + '\'' +
                ", role='" + role + '\'' +
                ", careerRecord=" + careerRecord +
                ", daysWork='" + daysWork + '\'' +
                ", shiftHours=" + shiftHours +
                '}';
    }
}

