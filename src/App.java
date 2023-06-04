import javax.management.relation.Role;
import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Hospital.dataRead();
        Start();
    }

    //--------------------------------------------------------

    public static void cls(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Profile(Personal personal){
        cls();
        System.out.println("\t\t*--PROFILE--*\n");
        System.out.println("Name : " + personal.getName() + "\n" + "Age : " + personal.getAge() + "\n"
                + "Address : " + personal.getAddress() + "\n" + "Gender : " + personal.getGender());
    }

    public static void Start(){
        cls();
        System.out.println("WELCOME TO NiT Hospital:\n\n");
        System.out.println("Do you wish to continue as:\n");
        System.out.println("1. Management\n2. Doctor\n3. Patient\n4. Employee\n0.Exit");

        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        switch (c){
            case 2:
                Doctor();
                break;
            case 3:
                Patient();
                break;
            case 4:
                Employee();
                break;
            case 0:
                System.out.println("Provided with Suffer:::)");
                break;
        }
    }
//-------------------------------------------------------------------------------------------------------------

    public static void Doctor(){
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");//role+randomnumber+email
            String user = input.nextLine();
            boolean isMember = false;
            for (Doctor e : Hospital.doctors) {
                if (e.getEmail().equals(user)) {
                    System.out.println("Email Successfully Authorized!!");
                    while (true) {
                        System.out.print("Password: ");//phone@random number<9digits>
                        String pass = input.nextLine();
                        if (e.getPhone().compareTo(pass) == 0){
                            MenuD(e);
                            isMember = true;
                            break;
                        } else {
                            System.out.println("\t\tERROR: PASSWORD(Phone) IS INCORRECT\n");
                        }
                    }
                    break;
                }
            }
            if (isMember){
                break;
            }
            else {
                System.out.println("\t\tERROR: THIS EMAIL DOES NOT EXIST\nPLEAS TRY AGAIN");
            }
        }
    }

    public static void MenuD(Doctor doctor){
        cls();
        int doc=0;
        for (Doctor d:Hospital.doctors){
            if (doctor.getDoctorID()==d.getDoctorID())
                doc = d.getDoctorID()-1;
        }
        Scanner jin = new Scanner(System.in);
        Scanner jstr = new Scanner(System.in);

        System.out.println("\t\t--*STAFF ONLY*--\n\tWELCOME *-- "+doctor.getName()+" --*");
        System.out.println("1. Profile\n2. Request to Manager\n3. Receipts\n0. Exit\nOption:");
        int ch = jin.nextInt();

        switch (ch){
            case 1:{
                cls();
                Profile(doctor);
                System.out.print("Expert: "+doctor.getMedicalExpertise()+"\nCareer Record: "+doctor.getCareerRecord()+"\nShift Hours: "+doctor.getShiftHours()+"\nMonthly Salary: "+doctor.getSalary()+" $\n"+"\nRATING: "+doctor.getRating()+"\n");
                System.out.println("1. Edit\n2. Resign Request\n0. Back");
                int ans = jin.nextInt();

                switch (ans){
                    case 1:{
                        if (UPVerify(doctor)){
                            cls();
                            Edit(doctor);
                        }
                    }
                    break;
                    case 2:{
                        if (UPVerify(doctor)){
                            Scanner num = new Scanner(System.in);
                            System.out.println("\t\t*--RESIGN REQUEST--*\n");

                            Random random = new Random(System.currentTimeMillis());
                            int rand = random.nextInt(1000000);

                            System.out.println("For DELETING please Write down the Given Number: "+ rand);
                            int enter = num.nextInt();

                            if (enter == rand){
                                System.out.println("Please fill out the form for the further operation, Why do you want to leave us:");
                                Scanner input = new Scanner(System.in);
                                String req = input.nextLine().concat(doctor.getName()+doctor.getDoctorID());

                                System.out.println("your request: "+req+"\n\t press 1 to proceed, exit 0:");
                                Scanner scanner1 = new Scanner(System.in);
                                int ans1 = scanner1.nextInt();
                                if (ans1 == 1){
                                    /*Admin.resigns.add(req);*/
                                    System.out.println("SENT Successfully!!!");
                                    MenuD(doctor);
                                    break;
                                }else
                                    MenuD(doctor);
                            }else
                                System.out.println("INCORRECT INPUT!!!");
                        }
                    }
                    break;
                    case 0:
                        MenuD(doctor);
                        break;
                }
            }
                break;
            case 2: {
                cls();
                if (UPVerify(doctor)) {
                    System.out.println("Please write down your request in a shortest way: ");
                    Scanner scanner = new Scanner(System.in);
                    String str = scanner.nextLine().concat(doctor.getName() + doctor.getDoctorID());
                    System.out.println("your request: " + str + "\n\t press 1 to proceed, exit 0:");
                    Scanner scanner1 = new Scanner(System.in);
                    int ans = scanner1.nextInt();
                    if (ans == 1) {
                        /*Admin.requests.add(str);*/
                        System.out.println("SENT Successfully!!!");
                        MenuD(doctor);
                        break;
                    }
                } else {
                    MenuD(doctor);
                }
            }
                break;
            case 3:{
                cls();
                List<Receipt> receipts = doctor.getReceipts();
                System.out.println("\t\t--*--Receipts of Doctor "+doctor.getName()+" --*--");
                if (receipts.size()==0)
                    System.out.println("Unfortunately You have no record in this page");
                else {
                    for (Receipt r:receipts) {
                        System.out.println("ID: "+r.getReceiptID());
                        System.out.println(r);
                        System.out.println("---------------------------------");
                    }

                    System.out.println("Available Status: "+doctor.isAvailable());
                    System.out.println("Do You Wish to Change Your Status? <Y/N>");
                    String answer = jstr.nextLine();
                    if (answer.equalsIgnoreCase("y")){
                        if (doctor.isAvailable() == true)
                            Hospital.doctors.get(doc).setAvailable(false);
                        else
                            Hospital.doctors.get(doc).setAvailable(true);
                    }else
                        MenuD(doctor);
                }
            }
                break;
            case 0:
                Start();
                break;
        }
    }

    public static void MenuE(Employee employee){
        //profile & resign
        cls();
        Scanner jin = new Scanner(System.in);
        Scanner jstr = new Scanner(System.in);

        System.out.println("\t\t--*STAFF ONLY*--\n\tWELCOME *--"+employee.getName()+" --*");
        System.out.println("1. Profile\n2. Request to Manager\n0. Exit\nOption:");

        int ch = jin.nextInt();
        switch (ch){
            case 1:{
                cls();
                Profile(employee);
                System.out.print("Role: "+employee.getRole()+"\nGrade: "+employee.getGrade()+"\nShift Hours: "+employee.getShiftHours()+"\nMonthly Salary: "+employee.getSalary()+" $\n");
                System.out.println("1. Edit\n2. Resign Request\n0. Back");
                int ans = jin.nextInt();
                switch (ans){
                    case 1:{
                        if (UPVerify(employee)){
                            cls();
                            Edit(employee);
                        }
                    }
                        break;
                    case 2:{
                        if (UPVerify(employee)){
                            Scanner num = new Scanner(System.in);
                            System.out.println("\t\t*--RESIGN REQUEST--*\n");

                            Random random = new Random(System.currentTimeMillis());
                            int rand = random.nextInt(1000000);

                            System.out.println("For DELETING please Write down the Given Number: "+ rand);
                            int enter = num.nextInt();

                            if (enter == rand){
                                System.out.println("Please fill out the form for the further operation, Why do you want to leave us:");
                                Scanner input = new Scanner(System.in);
                                String req = input.nextLine().concat(employee.getName()+employee.getEmployeeID());

                                System.out.println("your request: "+req+"\n\t press 1 to proceed, exit 0:");
                                Scanner scanner1 = new Scanner(System.in);
                                int ans1 = scanner1.nextInt();
                                if (ans1 == 1){
                                    /*Admin.resigns.add(req);*/
                                    System.out.println("SENT Successfully!!!");
                                    MenuE(employee);
                                    break;
                                }else
                                    MenuE(employee);
                            }else
                                System.out.println("INCORRECT INPUT!!!");
                        }
                    }
                        break;
                    case 0:
                        MenuE(employee);
                        break;
                }
            }
                break;
            case 2: {
                cls();
                if (UPVerify(employee)) {
                    System.out.println("Please write down your request in a shortest way: ");
                    Scanner scanner = new Scanner(System.in);
                    String str = scanner.nextLine().concat(employee.getName() + employee.getEmployeeID());
                    System.out.println("your request: " + str + "\n\t press 1 to proceed, exit 0:");
                    Scanner scanner1 = new Scanner(System.in);
                    int ans = scanner1.nextInt();
                    if (ans == 1) {
                        /*Admin.requests.add(str);*/
                        System.out.println("SENT Successfully!!!");
                        MenuE(employee);
                        break;
                    }
                } else {
                    MenuE(employee);
                }
            }
                break;
            case 0:
                System.out.println("Provided with Suffer:::)");
                break;
        }
    }

    private static void Employee() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");//role+randomnumber+email
            String user = input.nextLine();
            boolean isMember = false;
            for (Employee e : Hospital.employees) {
                if (e.getEmail().equals(user)) {
                    System.out.println("Email Successfully Authorized!!");
                    while (true) {
                        System.out.print("Password: ");//phone@random number<9digits>
                        String pass = input.nextLine();
                        if (e.getPhone().compareTo(pass) == 0){
                            MenuE(e);
                            isMember = true;
                            break;
                        } else {
                            System.out.println("\t\tERROR: PASSWORD(Phone) IS INCORRECT\n");
                        }
                    }
                    break;
                }
            }
            if (isMember){
                break;
            }
            else {
                System.out.println("\t\tERROR: THIS EMAIL DOES NOT EXIST\nPLEAS TRY AGAIN");
            }
        }
    }

    public static void Patient(){//email = username || phone = password
        cls();
        System.out.println("1. Already a Member\n2. Sign Up\n3. Back\t4. Exit\nOption: ");

        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        switch (c){
            case 1:{
                System.out.println("\t\tYou Are Logging in to NiT Hospital:");
                UPChecking();
            }
                break;
            case 2: {
                UPCheckingSing();
            }
                break;
            case 3:
                Start();
                break;
            case 4:
                System.out.println("Provided with Suffer:::)");
                break;
        }
    }

    public static void MenuP(Patient patient){
        cls();
        Scanner jin = new Scanner(System.in);
        Scanner jstr = new Scanner(System.in);

        System.out.println("\tWELCOME "+ "--*"+patient.getName()+"*--");
        System.out.println("1. Profile\n2. Request Visit\n3. Back\t0. Exit\nOption: ");

        int ch = jin.nextInt();
        switch (ch){
            case 1:{
                Profile(patient);
                System.out.print("Allergies: "+patient.getAllergyToDrugs()+"\n");

                System.out.println("1. Edit\n2. Delete Account\n0. Back");

                int c = jin.nextInt();
                switch (c){
                    case 1:
                        if (UPVerify(patient)){
                            cls();
                            Edit(patient);
                        }
                        break;
                    case 2:{
                        cls();
                        Scanner num = new Scanner(System.in);
                        System.out.println("\t\t*--DELETE ACCOUNT--*\n");

                        Random random = new Random(System.currentTimeMillis());
                        int rand = random.nextInt(1000000);

                        System.out.println("For DELETING please Write down the Given Number: "+ rand);
                        int enter = num.nextInt();

                        if (enter == rand){
                            Hospital.patients.remove(patient);
                            Hospital.dataWrite();
                            System.out.println("Successfully DELETED!!!");
                            Start();
                        }else
                            System.out.println("INCORRECT INPUT!!!");
                    }
                        break;
                    case 0:
                        MenuP(patient);
                        break;
                }
            }
                break;
            case 2:
                addVisit(patient);
                break;
            case 3:
                Patient();
                break;
            case 0:
                System.out.println("Provided with Suffer:::)");
                break;
        }
    }

    public static void addVisit(Patient patient){

            try {

                System.out.println("\t\t Welcome to VISIT menu ");
                System.out.println("\t Here are our Doctors : ");

                ArrayList<Doctor> availableDoctors = Hospital.availableDoctors();
                int size = availableDoctors.size();

                if (size == 0) {
                    System.out.println("\t\t Sorry __ none of our Doctors are available ");
                    MenuP(patient);
                    return;
                }

                for (int i=0;i<size;i++){
                    System.out.printf("\t%d : %s \t\t---*  Role : %s \t---* Cost : %d $\n", i+1, availableDoctors.get(i).getName(), availableDoctors.get(i).getMedicalExpertise(), availableDoctors.get(i).getSalary()/100);
                    /*System.out.printf("\tID : %d \n", availableDoctors.get(i).getDoctorID());*/
                }

                System.out.println("-----------------------------------------------------------");

                boolean choice = false;
                while (!choice) {
                    System.out.print("Your choice <0 to exit>: ");
                    Scanner input = new Scanner(System.in);
                    int answer = input.nextInt();
                    if (answer==0) {
                        MenuP(patient);
                        break;
                    }
                    answer--;
                    if (answer >= 0 && answer < size) {
                        System.out.printf("\t\t You have choosed %s \n Are you sure ? (1:YES / 2:NO) ", availableDoctors.get(answer).getName());

                        int x= input.nextInt();


                        switch (x){
                            case 1: {
                                choice = true;
                                boolean isEmergency;
                                Scanner input_2 = new Scanner(System.in);
                                System.out.println("\t\t is the visit emergency (true/false)? ");
                                isEmergency = input_2.hasNext();


                                Receipt receipt = new Receipt(availableDoctors.get(answer), patient, isEmergency);
                                patient.getReceipts().add(receipt);
                                Hospital.receipts.add(receipt);


                                System.out.println("\t\tYou have been visited succesfully ....");
                                System.out.printf("\t-ReceiptID : %d \t -Patient Name : %s \n", receipt.getReceiptID(), receipt.getPatient().getName());
                                System.out.printf("\t-Doctor Name : %s \t -Cost : %d $ \n", receipt.getDoctor().getName(), receipt.getCost());

                                Hospital.dataWrite();

                                MenuP(patient);
                            }
                            break;
                            case 2:
                                choice = true;
                                addVisit(patient);
                                break;
                            case 3:
                                choice = true;
                                MenuP(patient);
                                break;
                            }
                    }else {
                        System.out.println("\t\t Please enter correct value :(");
                    }

                }//end of while

            }//end of try
            catch (Exception e){
                System.out.println("\t\t NO DOCTOR HAS BEEN ADDED YET...");
            }


    }

    public static void Edit(Personal p){
        cls();
        Scanner in = new Scanner(System.in);
        Scanner num = new Scanner(System.in);
        System.out.println("\t\t*--EDIT INFO--*\n\n");

        System.out.println("Select the Fields to Change:\n1. Name\n2. Address\n3. Age\n4. Gender\n5. Phone(Password)\n6. Email(Username)\n 0. Exit\t7. Back");
        int choice = num.nextInt();

        if (choice == 1){
            System.out.println("New Name: ");
            String name = in.nextLine();
            p.setName(name);
            Hospital.dataWrite();
            Edit(p);
        }

        if (choice == 2){
            System.out.println("New Address: ");
            String address = in.nextLine();
            p.setAddress(address);
            Hospital.dataWrite();
            Edit(p);
        }

        if (choice == 3){
            System.out.println("New Age: ");
            int age = num.nextInt();
            p.setAge(age);
            Hospital.dataWrite();
            Edit(p);
        }

        if (choice == 4){
            System.out.println("New Gender <male/female>: ");
            String gender = in.nextLine();
            p.setGender(gender);
            Hospital.dataWrite();
            Edit(p);
        }

        if (choice == 5){
            System.out.println("New Phone: ");
            String phone = in.nextLine();
            p.setPhone(phone);
            Hospital.dataWrite();
            Edit(p);
        }

        if (choice == 6){
            System.out.println("New Email: ");
            String email = in.nextLine();
            p.setEmail(email);
            Hospital.dataWrite();
            Edit(p);
        }

        if (choice == 7)
            Start();
        if (choice == 0)
            System.out.println("Provided with Suffer:::)");
    }

    /*Sign in patient*/private static void UPCheckingSing() {
        Scanner str = new Scanner(System.in);
        Scanner num = new Scanner(System.in);

        System.out.println("Full Name:");
        String name = str.nextLine();

        System.out.println("Gender(Male/Female):");
        String gender = str.nextLine();

        System.out.println("Age:");
        int age = num.nextInt();

        System.out.println("Address:");
        String address = str.nextLine();

        String email = null;
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Email:");
            email = str.nextLine();
            int i = 0;
            for (Patient p : Hospital.patients) {
                if (p.getEmail().compareTo(email) == 0) {
                    i++;
                    break;
                }
            }
            if (i==1) {
                System.out.println("ERROR: THIS EMAIL HAS BEEN REGISTERED BEFORE\nPLEAS TRY AGAIN");
            }else {
                System.out.println("Email Successfully Authorized!!");
                isValid = true;
            }
        }

        System.out.println("Phone Number:");
        String phone = str.nextLine();

        System.out.println("Illness:");
        String illness = str.nextLine();

        System.out.println("Used Drugs<if not type NA>:");
        String usedDrugs = str.nextLine();

        System.out.println("Is your Illness Inherited?\n1. Y \t2. N");
        boolean isInherited;
        int ans = num.nextInt();
        if(ans == 1)
            isInherited = true;
        else
            isInherited = false;

        System.out.println("Do you have a medical case in this hospital?\n1. Y \t2. N");
        boolean medicalCase;
        int ans1 = num.nextInt();
        if(ans1 == 1)
            medicalCase = true;
        else
            medicalCase = false;

        System.out.println("Are you a Drug Addict?\n1. Y \t2. N");
        boolean isDrugAddict;
        int ans2 = num.nextInt();
        if(ans == 1)
            isDrugAddict = true;
        else
            isDrugAddict = false;

        System.out.println("What drugs do you have allergy to?(divide them with \\,\\<if not type NA>):");
        String allergy = str.nextLine();

        Patient patient = new Patient(name, gender, age, address, phone, email, illness, usedDrugs, isInherited, medicalCase, isDrugAddict, allergy);
        Hospital.patients.add(patient);

        Hospital.dataWrite();
        System.out.println("Registered Successfully!!!");
        Start();
    }

    /*log in patient menu*/private static void UPChecking() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Username(Email): ");
            String user = input.nextLine();
            boolean isMember = false;
            for (Patient p : Hospital.patients) {
                if (p.getEmail().compareTo(user) == 0) {
                    System.out.println("Email Successfully Authorized!!");
                    while (true) {
                        System.out.print("Password(Phone): ");
                        String pass = input.nextLine();
                        if (p.getPhone().compareTo(pass) == 0){
                            MenuP(p);
                            isMember = true;
                            break;
                        }
                        else {
                            System.out.println("\t\tERROR: PASSWORD(Phone) IS INCORRECT\n");

                        }
                    }
                    break;
                }
            }
            if (isMember){
                break;
            }
            else {
                System.out.println("\t\tERROR: THIS EMAIL DOES NOT EXIST\nPLEAS TRY AGAIN");
            }
        }
    }

    /*for edit patient menu*/private static boolean UPVerify(Personal p) {
        Scanner input = new Scanner(System.in);
        boolean isMember = false;
        while (true) {
            System.out.print("Username(Email): ");
            String user = input.nextLine();
            if (p.getEmail().compareTo(user) == 0) {
                System.out.println("Email Successfully Authorized!!");
                while (true) {
                    System.out.print("Password(Phone): ");
                    String pass = input.nextLine();
                    if (p.getPhone().compareTo(pass) == 0) {
                        isMember = true;
                        break;
                    } else {
                        System.out.println("\t\tERROR: PASSWORD(Phone) IS INCORRECT\n");
                    }
                }
            } else {
                System.out.println("\t\tERROR: THIS EMAIL DOES NOT EXIST\nPLEAS TRY AGAIN");
            }
            if (isMember)
                break;
        }
        return isMember;
    }
}
