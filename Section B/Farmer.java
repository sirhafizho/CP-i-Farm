public class Farmer implements Runnable{
    private String id;
    private String name;
    private String email;
    private String pwd;
    private String phoneNumber;

    Farmer(String id, String name, String email, String pwd, String phoneNumber){
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd= pwd;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void run(){
        //implement activity class here
        String action = "Sowing";
        String date = "2022-03-30";
        String type = "Fennel Seed";
        String unit = "kg";
        String field = "Field 1";
        String row = "Row 1";

        for(int i=0; i< 1000; i++){
            System.out.println(action + " " + type + " " + field + " " + row + " " + unit + " " + date);
        }
    }

    
    
}
