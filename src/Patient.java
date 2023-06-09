
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Patient extends Personal{
    private String illness;
    private String date;
    private String usedDrugs;
    private boolean isInherited;
    private boolean medicalCase;
    private boolean isDrugAddict;
    private String allergyToDrugs;
    private static int ID = 0;
    private ArrayList<Receipt> receipts;
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
        this.receipts = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        this.date=dtf.format(now);
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

    public ArrayList<Receipt> getReceipts() {
        ArrayList<Receipt> avaiReceipts = new ArrayList<>();
        for (Receipt r:receipts) {
            if (r.getPatient().getPatientID() == this.getPatientID())
                avaiReceipts.add(r);
        }
        return avaiReceipts;
    }

    public String getDate() {
        return date;
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

