import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private static int reserveListID = 0;
    private Doctor doctor;
    private Patient patient;
    private int cost;
    private String date;
    private boolean isEmergency;
    private int recieptID;

    public Receipt(Doctor doctor, Patient patient , int cost , boolean isEmergency){
        reserveListID++;
        recieptID = reserveListID;
        this.doctor=doctor;
        this.patient=patient;
        this.cost=cost;
        this.isEmergency=isEmergency;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        date=dtf.format(now);

    }

    public Doctor getDoctor() {
        return doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public int getCost() {
        return cost;
    }
    public String getDate() {
        return date;
    }
    public boolean isEmergency() {
        return isEmergency;
    }
    public int getRecieptID() {
        return recieptID;
    }


    public void setRecieptID(int recieptID) {
        this.recieptID = recieptID;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                ", cost=" + cost +
                ", date='" + date + '\'' +
                ", isEmergency=" + isEmergency +
                ", recieptID=" + recieptID +
                '}';
    }

}
