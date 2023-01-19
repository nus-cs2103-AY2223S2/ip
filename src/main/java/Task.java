public class Task {
    protected String item;
    protected boolean status;
    protected String types; // toDo, deadline, events

    protected String deadLineTime;
    protected String eventTime1;
    protected String eventTime2;

    Task(String x, String y) {
        item = x;
        types = y;
        status = false;
    }

    public void setDeadLine(String time) {
        String x[] = time.split(" ", 2);
        deadLineTime = x[0] + ": " + x[1];
    }

    public void setEventTime(String time1, String time2){
        String x1[] = time1.split(" ", 2);
        String x2[] = time2.split(" ", 2);
        eventTime1 = x1[0] + ": " + x1[1];
        eventTime2 = x2[0] + ": " + x2[1];
    }

    public String getItem(){
        String getter;
        if (status == false) {
            getter = "[" + types +"][ ] " + item;
        } else {
            getter = "[" + types +"][X] " + item;
        }

        if (types.equals("T")) {
            return getter;
        } else if (types.equals("D")){
            return getter + "(" + deadLineTime + ")";
        }

        return getter + "(" + eventTime1 + " " + eventTime2 + ")";
    }

    public void mark(){
        status = true;
    }

    public void unmark(){
        status = false;
    }
}
