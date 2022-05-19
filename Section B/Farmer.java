import java.util.Arrays;

public class Farmer implements Runnable {
    private String _id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String[] farms;

    private Range _idRange;

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

    public void setRange(Range range) {
        this._idRange = range;
    }

    @Override
    public void run() {
        int activitiesPerFarm = _idRange.getLimitDifference() / this.farms.length;
        int activity_id = _idRange.getLowerLimit();
        Activity[][] activities = new Activity[this.farms.length][activitiesPerFarm];

        // For each farm that the current farmer is employed by
        for(int i = 0; i < farms.length; i++) {
            // Generate the activities performed by the farmer for the farm
            for(int j = 0; j <= activitiesPerFarm; j++) {
                activities[i][j] = new Activity();
            }
        }
    }

    @Override
    public String toString() {
        return "[" + _id + ", " + name + ", " + email + ", " + password + ", " + phoneNumber + ", " + Arrays.toString(this.farms) + "]";
    }
}