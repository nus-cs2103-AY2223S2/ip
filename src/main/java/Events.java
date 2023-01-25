public class Events extends Task {
    private String strtime;
    private String endtime;

    public Events(String name, String strtime, String endtime) {
        super(name);
        this.strtime = strtime;
        this.endtime = endtime;
    }

    public String getStrtime() {
        return this.strtime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public String toString() {
        if (this.getStatus()) {
            return "[E][X] " + this.getName() + " (from: " + this.strtime + "to: " + this.endtime + ")";
        } else {
            return "[E][ ] " + this.getName() + " (from: " + this.strtime + "to: " + this.endtime + ")";
        }
    }
}
