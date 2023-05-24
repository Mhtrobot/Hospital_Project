import java.util.ArrayList;

public class Patient extends Personal{
    private String illness;
    private String usedDrugs;
    private boolean isInherited;
    private boolean medicalCase;
    private boolean isDrugAddict;
    private String allergyToDrugs;
    private static int ID = 0;
    private ArrayList<Doctor> doctors;
    private int patientID;

    public Patient(String name, String gender, int age, String address, String phone, String email,String illness,
                   String usedDrugs, boolean isInherited, boolean medicalCase, boolean isDrugAddict, String allergyToDrugs) {
        super(name, gender, age, address, phone, email);
        ID++;
        this.patientID = ID;
        this.illness = illness;
        this.usedDrugs = usedDrugs;
        this.isInherited = isInherited;
        this.medicalCase = medicalCase;
        this.isDrugAddict = isDrugAddict;
        this.allergyToDrugs = allergyToDrugs;
    }

    //----------------------------------------------------------------------------getter
    public String getIllness() {
        return illness;
    }

    public String getUsedDrugs() {
        return usedDrugs;
    }

    public boolean isInherited() {
        return isInherited;
    }

    public boolean isMedicalCase() {
        return medicalCase;
    }

    public boolean isDrugAddict() {
        return isDrugAddict;
    }

    public String getAllergyToDrugs() {
        return allergyToDrugs;
    }

    public int getPatientID(){
        return patientID;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    public void setDoctors(ArrayList<Doctor> doctors){
        this.doctors=doctors;
    }

    @Override
    public String toString(){
        String result = "name : " + name + "\n" +
                "age : " + age + "\n" + "illness : " + illness + "\n" +
                "used drogs : " + usedDrugs + "\n" + "isInherited : " + isInherited + "\n" +
                "medicalCase : " + medicalCase + "\n" + "isDrugAddict : " + isDrugAddict + "\n" +
                "allergyToDrugs : " + allergyToDrugs;

        return result;
    }
}

