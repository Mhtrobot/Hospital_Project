import java.util.ArrayList;

public class Doctor extends Personal{
    private String medicalExpertise;
    private String daysWork;
    private int shiftHours;
    private int careerRecord;
    private static int ID = 0;
    private int doctorID;
    private ArrayList<Patient> patients = new ArrayList<Patient>();

    public Doctor(String name, String gender, int age, String address, String phone, String email,
                  String medicalExpertise, String daysWork, int shiftHours, int careerRecord) {
        super(name, gender, age, address, phone, email);
        ID++;
        this.doctorID = ID;
        this.medicalExpertise = medicalExpertise;
        this.daysWork = daysWork;
        this.shiftHours = shiftHours;
        this.careerRecord = careerRecord;
    }

    //----------------------------------------------------------------------------getter & setter

    public String getMedicalExpertise() {
        return medicalExpertise;
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

    public int getCareerRecord() {
        return careerRecord;
    }


    public int getDoctorID() {
        return doctorID;
    }
    public ArrayList<Patient> getPatients(){
        return patients;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", medicalExpertise='" + medicalExpertise + '\'' +
                ", daysWork='" + daysWork + '\'' +
                ", shiftHours=" + shiftHours +
                ", careerRecord=" + careerRecord +
                '}';
    }
}

