public class Personal {
    protected String name;
    protected String gender;
    protected int age;
    protected String address;
    protected String phone;
    protected String email;
    private static int ID = 0;
    private int personID;

    public Personal(String name, String gender, int age, String address, String phone, String email) {
        ID++;
        this.personID = ID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    //----------------------------------------------------------------------------setter and getter

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPersonID() {
        return personID;
    }
}

