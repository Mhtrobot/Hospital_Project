import java.util.ArrayList;

public class Doctor extends Personal{
    private String medicalExpertise;
    private String daysWork;
    private int shiftHours;
    private int careerRecord;
    private static int ID = 0;
    private int doctorID;
    private float rating;
    private boolean isAvailable;
    private int salary;
    private ArrayList<Receipt> receipts;
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public Doctor(String name, String gender, int age, String address, String phone, String email,
                  String medicalExpertise, String daysWork, int shiftHours, int careerRecord) {
        super(name, gender, age, address, phone, email);
        ID++;
        this.doctorID = ID;
        this.medicalExpertise = medicalExpertise;
        this.daysWork = daysWork;
        this.shiftHours = shiftHours;
        this.careerRecord = careerRecord;
        isAvailable=true;
        salary=1000;
        rating = 0.0f;
        this.receipts = new ArrayList<>();
    }//constructor for new obj

    public Doctor(String name, String gender, int age, String address, String phone, String email,
                  String medicalExpertise, String daysWork, int shiftHours, int careerRecord, boolean isAvailable,
                  float rating ,int salary) {
        super(name, gender, age, address, phone, email);
        ID++;
        this.doctorID = ID;
        this.medicalExpertise = medicalExpertise;
        this.daysWork = daysWork;
        this.shiftHours = shiftHours;
        this.careerRecord = careerRecord;
        this.isAvailable=isAvailable;
        this.rating=rating;
        this.salary=salary;
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

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public float getRating() {
        return rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public ArrayList<Receipt> getReceipts() {
        ArrayList<Receipt> avaiReceipts = new ArrayList<>();
        for (Receipt r:receipts) {
            if (r.getPatient().getPatientID() == this.getDoctorID())
                avaiReceipts.add(r);
        }
        return avaiReceipts;
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
                ", rating=" + rating +
                '}';
    }
}

