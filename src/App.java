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
        System.out.println("WELCOME TO NiT Hospital:\n\n");
        System.out.println("Do you wish to continue as:\n");
        System.out.println("1. Management\n2. Doctor\n3. Patient\n4. Employee\n0.Exit");

        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        switch (c){
            case 1:
                ADChecking();
                break;
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
                                String req = input.nextLine().concat("-->" + doctor.getName()+ ":" +doctor.getDoctorID());

                                System.out.println("your request: "+req+"\n\t press 1 to proceed, exit 0:");
                                Scanner scanner1 = new Scanner(System.in);
                                int ans1 = scanner1.nextInt();
                                if (ans1 == 1){
                                    Admin.addRequest(req);                      //done
                                    System.out.println("SENT Successfully!!!");
                                    Hospital.dataWrite();
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
                    String str = scanner.nextLine().concat("-->" + doctor.getName() + ":" + doctor.getDoctorID());
                    System.out.println("your request: " + str + "\n\t press 1 to proceed, exit 0:");
                    Scanner scanner1 = new Scanner(System.in);
                    int ans = scanner1.nextInt();
                    while (true) {
                        if (ans == 1) {
                            Admin.addRequest(str);
                            System.out.println("SENT Successfully!!!");
                            Hospital.dataWrite();
                            MenuD(doctor);
                            break;
                        } else {
                            MenuD(doctor);
                            break;
                            }
                        }

                    } else{
                        MenuD(doctor);
                        break;
                    }

            }
                break;

            case 3:{
                cls();
                List<Receipt> receipts = doctor.getReceipts();
                System.out.println("\t\t--*--Receipts of Doctor "+doctor.getName()+" --*--");
                if (receipts.size()==0){
                    System.out.println("Unfortunately You have no record in this page");
                    MenuD(doctor);
                }
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
                        if (doctor.isAvailable() == true){
                            Hospital.doctors.get(doc).setAvailable(false);
                            Hospital.dataWrite();
                            MenuD(doctor);
                        }
                        else{
                            Hospital.doctors.get(doc).setAvailable(true);
                            Hospital.dataWrite();
                            MenuD(doctor);
                        }
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
    //---------------------------------------------------------------------------------------------

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
                                String req = input.nextLine().concat("-->" + employee.getName()+ ":" +employee.getEmployeeID());

                                System.out.println("your request: "+req+"\n\t press 1 to proceed, exit 0:");
                                Scanner scanner1 = new Scanner(System.in);
                                int ans1 = scanner1.nextInt();
                                if (ans1 == 1){
                                    Admin.addRequest(req);                       //done
                                    System.out.println("SENT Successfully!!!");
                                    Hospital.dataWrite();
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
                while (true){
                    if (UPVerify(employee)) {
                        System.out.println("Please write down your request in a shortest way: ");
                        Scanner scanner = new Scanner(System.in);
                        String str = scanner.nextLine().concat("-->" + employee.getName() + ":" + employee.getEmployeeID());
                        System.out.println("your request: " + str + "\n\t press 1 to proceed, exit 0:");
                        Scanner scanner1 = new Scanner(System.in);
                        int ans = scanner1.nextInt();
                        if (ans == 1) {
                            Admin.addRequest(str);                         //done
                            System.out.println("SENT Successfully!!!");
                            Hospital.dataWrite();
                            MenuE(employee);
                            break;
                        }
                        else{
                            MenuE(employee);
                            break;
                        }
                    } else {
                        System.out.println("Wrong username or password");
                    }
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
    //---------------------------------------------------------------------------------------------

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
    //---------------------------------------------------------------------------------------------

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

    public static void admin(){
        Scanner input = new Scanner(System.in);

        System.out.println("\tWELCOME DEAR"+ "--*"+"Admin"+"*--");
        System.out.println("1. Add Staff\n2. Remove Staff\n3. Increase Payment\n4. Decrease Payment\n5. Requests checkbox\n" +
                "6. Back\t0. Exit\nOption: ");

        int awnser = input.nextInt();
        switch (awnser) {
            case 1:    //add staff
                System.out.println("Which one Do you want to ADD ? \n1. Doctor\n2. Employee\n3. exit");
                Scanner in = new Scanner(System.in);
                int w = in.nextInt();
                switch (w) {
                    case 1: //Doctor
                        Scanner strings = new Scanner(System.in);
                        System.out.println("\t\tPlease fill fields below : ");
                        System.out.println("\t Name : ");
                        String name = strings.nextLine();
                        System.out.println("\t Gender : ");
                        String gender = strings.nextLine();
                        System.out.println("\t age : ");
                        int age = in.nextInt();
                        System.out.println("\t address : ");
                        String address = strings.nextLine();
                        //----
                        Random random = new Random(System.currentTimeMillis());
                        String email = null;
                        String newEmail = null;
                        boolean isValid = false;
                        while (!isValid) {
                            System.out.println("Email:");
                            email = strings.nextLine();
                            int i = 0;
                            for (Doctor p : Hospital.doctors) {
                                if (p.getEmail().compareTo(email) == 0) {
                                    i++;
                                    break;
                                }
                            }
                            if (i == 1) {
                                System.out.println("ERROR: THIS EMAIL HAS BEEN REGISTERED BEFORE\nPLEAS TRY AGAIN");
                            } else {
                                int ranNum = random.nextInt(1000);
                                newEmail = email.concat("doc" + ranNum);
                                System.out.println("Email Successfully Authorized!!");
                                isValid = true;
                            }
                        }
                        /*System.out.println("\t Email : ");
                        String email = strings.nextLine();*/
                        System.out.println("\t phone : ");
                        String tempPhone = strings.nextLine();
                        int ran = random.nextInt(1000);
                        String phone = tempPhone.concat("@" + ran);
                        //---
                        System.out.println("\t MedicalExpertise : ");
                        String medicalExpertise = strings.nextLine();
                        System.out.println("\t daysWork : ");
                        String daysWork = strings.nextLine();
                        System.out.println("\t ShiftHours : ");
                        int shiftHours = in.nextInt();
                        System.out.println("\t CareerRecord : ");
                        int careerRecord = in.nextInt();

                        Doctor doctor = new Doctor(name, gender, age, address, phone, newEmail, medicalExpertise, daysWork, shiftHours, careerRecord, true, 0.0f, careerRecord * 100);
                        Hospital.doctors.add(doctor);

                        System.out.println("\t Your Doctor Successfully Added To Hospital");
                        Hospital.dataWrite();
                        admin();

                        break;
                    case 2: //Employee
                        Scanner strings2 = new Scanner(System.in);
                        System.out.println("\t\tPlease fill fields below : ");
                        System.out.println("\t Name : ");
                        String Ename = strings2.nextLine();
                        System.out.println("\t Gender : ");
                        String Egender = strings2.nextLine();
                        System.out.println("\t age : ");
                        int Eage = in.nextInt();
                        System.out.println("\t address : ");
                        String Eaddress = strings2.nextLine();
                        //----
                        Random random1 = new Random(System.currentTimeMillis());
                        String emailE = null;
                        String newEmailE = null;
                        boolean isValidE = false;
                        while (!isValidE) {
                            System.out.println("Email:");
                            emailE = strings2.nextLine();
                            int i = 0;
                            for (Employee p : Hospital.employees) {
                                if (p.getEmail().compareTo(emailE) == 0) {
                                    i++;
                                    break;
                                }
                            }
                            if (i == 1) {
                                System.out.println("ERROR: THIS EMAIL HAS BEEN REGISTERED BEFORE\nPLEAS TRY AGAIN");
                            } else {
                                int ranNum = random1.nextInt(1000);
                                newEmailE = emailE.concat("staff" + ranNum);
                                System.out.println("Email Successfully Authorized!!");
                                isValidE = true;
                            }
                        }
                        System.out.println("\t phone : ");
                        String tempPhoneE = strings2.nextLine();
                        int ranE = random1.nextInt(1000);
                        String phoneE = tempPhoneE.concat("@" + ranE);
                        //---
                        System.out.println("\t Grade : ");
                        String grade = strings2.nextLine();
                        System.out.println("\t Role : ");
                        String role = strings2.nextLine();
                        System.out.println("\t careerRecord : ");
                        int EcareerRecord = in.nextInt();
                        System.out.println("\t DaysWork : ");
                        String EdaysWork = strings2.nextLine();
                        System.out.println("\t ShiftHours : ");
                        int shiftHoursE = in.nextInt();

                        Employee employee = new Employee(Ename, Egender, Eage, Eaddress, phoneE, newEmailE, grade, role, EcareerRecord, EdaysWork, shiftHoursE, EcareerRecord * 100);
                        Hospital.employees.add(employee);

                        System.out.println("\t Your Employee Successfully Added To Hospital");
                        Hospital.dataWrite();
                        admin();
                        break;

                    case 3:
                        admin();
                        break;
                }
                break;
            case 2: {   // remove staff
                System.out.println("Which one Do you want to REMOVE ? \n1. Doctor\n2. Employee\n3. exit");
                Scanner integers = new Scanner(System.in);
                Scanner strings = new Scanner(System.in);
                int newawnser = integers.nextInt();

                switch (newawnser) {
                    case 1:              //Doctor
                        System.out.println("\t Choose a doctor : ");
                        for (int i = 0; i < Hospital.doctors.size(); i++) {
                            System.out.printf("%d.  %s \n", i + 1, Hospital.doctors.get(i).getName());
                        }


                        while (true) {
                            System.out.println("your choice : ");
                            int choose = integers.nextInt();
                            if (choose > Hospital.doctors.size() || choose < 1) {
                                System.out.println("Choose correct number !!");
                            } else {
                                choose--;
                                Doctor doctor = Hospital.doctors.get(choose);
                                System.out.printf("you want to delete <<%s>>\n", doctor.getName());
                                System.out.println("Are you sure : (1:YES/2:NO)");
                                while (true) {
                                    int x = integers.nextInt();
                                    if (x == 1) {
                                        System.out.println("Print text below to confirm : \"Confirm@1234\"  (\"exit\" : to exit)");
                                        while (true) {
                                            String s = strings.nextLine();
                                            if (s.compareTo("Confirm@1234") == 0) {
                                                Hospital.doctors.remove(doctor);
                                                System.out.printf("\t Doctor %s Has Been Deleted Successfully\n", doctor.getName());
                                                Hospital.dataWrite();
                                                admin();
                                                break;
                                            } else if (s.compareTo("exit") == 0) {
                                                admin();
                                                break;
                                            } else {
                                                System.out.println("Please enter correct value !!");
                                            }
                                        }
                                        break;
                                    } else if (x == 2) {
                                        admin();
                                        break;
                                    } else {
                                        System.out.println("Choose correct number !!");
                                    }
                                }
                                break;
                            }
                        }
                        break;

                    case 2:   //Employee

                        System.out.println("\t Choose an employee : ");
                        for (int i = 0; i < Hospital.employees.size(); i++) {
                            System.out.printf("%d.  %s \n", i + 1, Hospital.employees.get(i).getName());
                        }


                        while (true) {
                            System.out.println("your choice : ");
                            int choose = integers.nextInt();
                            if (choose > Hospital.employees.size() || choose < 1) {
                                System.out.println("Choose correct number !!");
                            } else {
                                choose--;
                                Employee employee = Hospital.employees.get(choose);
                                System.out.printf("you want to delete <<%s>>\n", employee.getName());
                                System.out.println("Are you sure : (1:YES/2:NO)");
                                while (true) {
                                    int x = integers.nextInt();
                                    if (x == 1) {
                                        System.out.println("Print text below to confirm : \"Confirm@1234\"  (\"exit\" : to exit)");
                                        while (true) {
                                            String s = strings.nextLine();
                                            if (s.compareTo("Confirm@1234") == 0) {
                                                Hospital.employees.remove(employee);
                                                System.out.printf("\t Employee %s Has Been Deleted Successfully\n", employee.getName());
                                                Hospital.dataWrite();
                                                admin();
                                                break;
                                            } else if (s.compareTo("exit") == 0) {
                                                admin();
                                                break;
                                            } else {
                                                System.out.println("Please enter correct value !!");
                                            }
                                        }
                                        break;
                                    } else if (x == 2) {
                                        admin();
                                        break;
                                    } else {
                                        System.out.println("Choose correct number !!");
                                    }
                                }
                                break;
                            }
                        }
                        break;


                    case 3:
                        admin();
                        break;
                }
            }
            break;
            case 3:{    //increase salary
                Scanner integers2 = new Scanner(System.in);

                System.out.println("1. Doctor  -  2. Employee  - 3. exit\t choice:");
                int awnser2 = integers2.nextInt();

                switch (awnser2) {
                    case 1: // Doctor
                        System.out.println("Choose a doctor : ");
                        for (int i = 0; i < Hospital.doctors.size(); i++) {
                            System.out.printf("%d.  %s\n", i + 1, Hospital.doctors.get(i).getName());
                        }

                        while (true) {
                            System.out.println("Your choice : ");
                            int ans = integers2.nextInt();

                            if (ans > Hospital.doctors.size() || ans < 1) {
                                System.out.println("Choose correct value !!");
                            } else {
                                ans--;
                                Doctor doctor = Hospital.doctors.get(ans);
                                Hospital.doctorSalaryIncrease(doctor);
                                System.out.printf("Salary of Doctor %s Has Been Increased\n", doctor.getName());
                                Hospital.dataWrite();
                                admin();
                                break;
                            }
                        }

                        break;
                    case 2:    //Employee

                        System.out.println("Choose an Employee : ");
                        for (int i = 0; i < Hospital.employees.size(); i++) {
                            System.out.printf("%d.  %s\n", i + 1, Hospital.employees.get(i).getName());
                        }

                        while (true) {
                            System.out.println("Your choice : ");
                            int ans = integers2.nextInt();

                            if (ans > Hospital.employees.size() || ans < 1) {
                                System.out.println("Choose correct value !!");
                            } else {
                                ans--;
                                Employee employee = Hospital.employees.get(ans);
                                Hospital.employeeSalaryIncrease(employee);
                                System.out.printf("Salary of Employee %s Has Been Increased\n", employee.getName());
                                Hospital.dataWrite();
                                admin();
                                break;
                            }
                        }

                        break;

                    case 3:
                        admin();
                        break;
                }

            }
                break;
            case 4: {    //decrease salary

                Scanner integers3 = new Scanner(System.in);
                Scanner strings3 = new Scanner(System.in);

                System.out.println("1. Doctor  -  2. Employee  - 3. exit\t choice:");
                int awnser3 = integers3.nextInt();

                switch (awnser3) {
                    case 1: // Doctor
                        System.out.println("Choose a doctor : ");
                        for (int i = 0; i < Hospital.doctors.size(); i++) {
                            System.out.printf("%d.  %s\n", i + 1, Hospital.doctors.get(i).getName());
                        }

                        while (true) {
                            System.out.println("Your choice : ");
                            int ans = integers3.nextInt();

                            if (ans > Hospital.doctors.size() || ans < 1) {
                                System.out.println("Choose correct value !!");
                            } else {
                                ans--;
                                Doctor doctor = Hospital.doctors.get(ans);
                                Hospital.doctorSalaryDecrease(doctor);
                                System.out.printf("Salary of Doctor %s Has Been Decreased\n", doctor.getName());
                                Hospital.dataWrite();
                                admin();
                                break;
                            }
                        }

                        break;
                    case 2:    //Employee

                        System.out.println("Choose an Employee : ");
                        for (int i = 0; i < Hospital.employees.size(); i++) {
                            System.out.printf("%d.  %s\n", i + 1, Hospital.employees.get(i).getName());
                        }

                        while (true) {
                            System.out.println("Your choice : ");
                            int ans = integers3.nextInt();

                            if (ans > Hospital.employees.size() || ans < 1) {
                                System.out.println("Choose correct value !!");
                            } else {
                                ans--;
                                Employee employee = Hospital.employees.get(ans);
                                System.out.println(employee.getSalary());
                                Hospital.employeeSalaryDecrease(employee);
                                System.out.println(employee.getSalary());
                                System.out.printf("Salary of Employee %s Has Been Decreased\n", employee.getName());
                                Hospital.dataWrite();
                                admin();
                                break;
                            }
                        }

                        break;

                    case 3:
                        admin();
                        break;
                }
            }
                break;
            case 5: {          //Request checkbox
                Scanner se = new Scanner(System.in);
                System.out.printf("\t\t----- You have %d new messages \n", Admin.getRequests().size());
                for (int i = 0; i < Admin.getRequests().size(); i++) {
                    System.out.printf("\t%d_   *\" %s \"* \n", i + 1, Admin.getRequests().get(i));
                    System.out.println("Press :  1 to Delete message  -  2 to get next message  -  3 to exit ");
                    int aw = se.nextInt();
                    switch (aw) {
                        case 1:
                            System.out.println("\tAre you sure  (1:YES/2:NO) ?");
                            int x = se.nextInt();
                            switch (x) {
                                case 1:
                                    Admin.removeRequest(Admin.getRequests().get(i));
                                    System.out.println("\t\t Message deleted successfully ");
                                    break;
                                case 2:
                                    break;
                            }
                            break;
                        case 2:
                            if (i == Admin.getRequests().size() - 1)
                                System.out.println("end of messages");
                            break;
                        case 3:
                            admin();
                            break;
                    }
                }
                admin();
            }
                break;
            case 6:
                App.Start();
                break;
            case 0:
                System.out.println("Provided with Suffer:::)");
                break;
        }
    }
    //---------------------------------------------------------------------------------------------
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

    /*sign in admin*/private static void ADChecking(){
        //to enter as an admin : Username=admin@verify , Password=admin1234
        System.out.println("\t\t Welcome To Login Page (in every place enter \"exit\" to exit");
        Scanner admin = new Scanner(System.in);
        while (true){
            System.out.println("\t\t Enter your username : ");
            String username = admin.nextLine();

            if (username.compareTo("admin@verify") == 0){
                System.out.println("\t\t username successfully autherized");

                while (true){
                    System.out.println("\t Enter your password : ");
                    String pass = admin.nextLine();

                    if (pass.compareTo("admin1234") == 0){
                        System.out.println("\t\t Password successfully autherized");
                        admin();
                        break;
                    } else if (pass.compareTo("exit") == 0) {
                        Start();
                        break;
                    } else {
                        System.out.println("\t\t Wrong password");
                    }
                }

                break;
            }
            else if (username.compareTo("exit") == 0){
                Start();
                break;
            }
            else {
                System.out.println("\t\t Wrong username");
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