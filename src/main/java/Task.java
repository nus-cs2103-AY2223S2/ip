public class Task {
    protected String item;
    protected boolean status;

    Task(String x) {
        item = x;
        status = false;
    }

    public String getItem(){
        if (status == false) {
            return "[ ] " + item;
        } else {
            return "[X] " + item;
        }
    }

    public void mark(){
        status = true;
    }

    public void unmark(){
        status = false;
    }
}
