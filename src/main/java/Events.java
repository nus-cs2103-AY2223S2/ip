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
}
