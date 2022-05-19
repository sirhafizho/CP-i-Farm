public class Activity {
    private String _id;
    private String date;
    private String action;
    private String type;
    private String unit;
    private Integer quantity;
    private Integer field;
    private Integer row;
    private Integer farmId;
    private Integer userId;

    
    public Activity(String _id, String date, String action, String type, String unit, Integer quantity, Integer field, Integer row, Integer farmId, Integer userId) {
        this._id = _id;
        this.date = date;
        this.action = action;
        this.type = type;
        this.unit = unit;
        this.quantity = quantity;
        this.field = field;
        this.row = row;
        this.farmId = farmId;
        this.userId = userId;
    }
}
