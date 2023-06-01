import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Start();
        /*Doctor doctor = new Doctor("Mmd", "Male", 18, "NA", "090123456789", "mmd@gmail.com", "Teeth", "SAT/TUE", 4,10);
        Doctor doctor2 = new Doctor("Adel", "Male", 22, "NA", "010123456789", "adel@gmail.com", "poop", "SUN/WED", 8,10);
        Doctor doctor3 = new Doctor("Ali", "Male", 32, "NA", "090123136789", "ali@gmail.com", "heart", "THU/TUE", 4,10);
        Doctor doctor4 = new Doctor("Amir", "Male", 40, "NA", "090123455789", "amir@gmail.com", "nerve", "MON/FRI", 8,10);

        Patient p1 = new Patient("a", "Female", 18, "NA", "12354987412", "a@gmail.com", "depression", "NONE", false, false, false, "NONE");
        Patient p2 = new Patient("b", "Male", 50, "NA", "12354984412", "b@gmail.com", "depression", "NONE", false, false, false, "NONE");
        Patient p3 = new Patient("c", "Female", 18, "NA", "12341287412", "c@gmail.com", "depression", "NONE", false, false, false, "NONE");*/
    }

    //--------------------------------------------------------
    public static void Start(){
        System.out.println("WELCOME TO NiT Hospital:\n\n");
        System.out.println("Do you wish to continue as:\n");
        System.out.println("1. Management\n2. Doctor\n3. Patient\n");

        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        switch (c){
            /*case 1:
                break;*/
            case 2:
        }
    }

    public static void Patient(){
        Hospital.dataRead();
        System.out.println("1. Already a Member\n2. Sign Up\noption: ");

        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        switch (c){
            /*case 1:
                break;*/
            case 2: {
                Scanner str = new Scanner(System.in);
                Scanner num = new Scanner(System.in);

                System.out.println("Full Name:");
                String name = str.next();

                System.out.println("Gender(Male/Female):");
                String gender = str.next();

                System.out.println("Age:");
                int age = num.nextInt();

                System.out.println("Address:");
                String address = str.next();

                boolean isValid = false;
                while (isValid == false) {
                    System.out.println("Email:");
                    String email = num.next();
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
                String phone = str.next();

                System.out.println("Illness:");
                String illness = str.next();

                System.out.println("Used Drugs:");
                String usedDrugs = str.next();

                //boolean isInherited, boolean medicalCase, boolean isDrugAddict, String allergyToDrugs
                System.out.println("Is your Illness Inherited?\n1. Y \t2. N");
                int ans = num.nextInt();
                if
                /*break;*/
            }
        }
    }
}
