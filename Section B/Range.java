public class Range {
    private int lowerLimit;
    private int upperLimit;

    Range(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    Range(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public void setLowerLimit(int lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public int getLowerLimit() {
        return this.lowerLimit;
    }

    public int getUpperLimit() {
        return this.upperLimit;
    }

    public int getLimitDifference() {
        return (this.upperLimit - this.lowerLimit) + 1;
    }

    @Override
    public String toString() {
        return "The Lower Limit: " + this.lowerLimit + ", The Upper Limit: " + this.upperLimit + ", The Limit Difference: " + this.getLimitDifference();
    }
}
