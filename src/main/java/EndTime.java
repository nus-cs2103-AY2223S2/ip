public class EndTime {
    private String endTime;

    public EndTime(String endtime) {
        this.endTime = endtime;
    }

    @Override
    public String toString() {
        return "(by: " + this.endTime + ")";
    }
}
