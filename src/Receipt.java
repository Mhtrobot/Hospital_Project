import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Receipt {       //receipt is added after visit
    private static int ID = 0;
    private Doctor doctor;
    private Patient patient;
    private int cost;
    private String date;
    private boolean isEmergency;
    private int receiptID;

    public Receipt(Doctor doctor, Patient patient , int cost , boolean isEmergency , String date){
        //constructor for reading from database
        ID++;
        receiptID=ID;
        this.doctor=doctor;
        this.patient=patient;
        this.cost=cost;
        this.isEmergency=isEmergency;
        this.date=date;
    }

    public Receipt(Doctor doctor, Patient patient , boolean isEmergency){
        //constructor for new receipts
        ID++;
        receiptID = ID;
        this.doctor=doctor;
        this.patient=patient;
        this.isEmergency=isEmergency;
        //every doctor's reception cost is 1% of his/her salary, the better rating-> more salary-> more reception cost
        this.cost = doctor.getSalary() / 100;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        date=dtf.format(now);

        doctor.setAvailable(false);


        System.out.println("\t\t\t --------Please enter your Doctor's Rating : ");
        System.out.printf("\t\tDoctor %s : \n",doctor.getName());
        System.out.print("\tRating : ");
        Scanner input = new Scanner(System.in);
        float rating = input.nextFloat();
        doctor.setRating(rating);

        if (rating > 7.0){
            Hospital.doctorSalaryIncrease(doctor);}
        else if (rating < 5.0){
            Hospital.doctorSalaryDecrease(doctor);}

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
    public int getReceiptID() {
        return receiptID;
    }


    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                ", cost=" + cost +
                ", date='" + date + '\'' +
                ", isEmergency=" + isEmergency +
                ", recieptID=" + receiptID +
                '}';
    }

}