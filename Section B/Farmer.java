public class Farmer implements Runnable {
    private String _id;
    private String name;
    private String email;
    // TODO: Determine what to do with the password property
    private String password;
    private String phoneNumber;
    private String[] farms;

    Farmer(String _id, String name, String email, String password, String phoneNumber, String[] farms) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password= password;
        this.phoneNumber = phoneNumber;
        this.farms = farms;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
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

    public String[] getFarms() {
        return farms;
    }

    public void setFarms(String[] farms) {
        this.farms = farms;
    }

    @Override
    public void run() {
        // TODO: Implement activity class here
        String action = "Sowing";
        String date = "2022-03-30";
        String type = "Fennel Seed";
        String unit = "kg";
        String field = "Field 1";
        String row = "Row 1";

        for(int i=0; i< 1000; i++) {
            System.out.println(action + " " + type + " " + field + " " + row + " " + unit + " " + date);
        }
    }

    @Override
    public String toString() {
        String arrayString = "[";
        for(int i = 0; i < farms.length - 1; i++) {
            arrayString += farms[i] + ", ";
        }
        arrayString += farms[farms.length - 1] + "]";

        return "[" + _id + ", " + name + ", " + email + ", " + password + ", " + phoneNumber + ", " + arrayString + "]";
    }
}
