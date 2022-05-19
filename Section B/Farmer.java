import java.util.Arrays;

public class Farmer implements Runnable {
    private String _id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String[] farms;

    private Range _idRange;
    private Activity[][] activities;

    Farmer(String _id, String name, String email, String password, String phoneNumber, String[] farms) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password= password;
        this.phoneNumber = phoneNumber;
        this.farms = farms;

        this.activities = new Activity[this.farms.length][];
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

    public Activity[][] getActivities() {
        return this.activities;
    }

    // William, this is Irfan, please take note of the _idRange object and the activties 2d array when implementing your part
    @Override
    public void run() {
        int activitiesPerFarm = _idRange.getLimitDifference() / this.farms.length;
        int activity_id = _idRange.getLowerLimit();

        // For each farm that the current farmer is employed by
        for(int i = 0; i < farms.length; i++) {
            // Generate the activities performed by the farmer for the farm
            for(int j = 0; j <= activitiesPerFarm; j++) {
                this.activities[i][j] = new Activity();
            }
        }
    }

    @Override
    public String toString() {
        return "[" + _id + ", " + name + ", " + email + ", " + password + ", " + phoneNumber + ", " + Arrays.toString(this.farms) + "]";
    }
}