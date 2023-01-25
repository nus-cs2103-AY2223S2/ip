package duke;

public class Task {
    boolean status;
    String des;

    public Task(boolean status, String des) {
        this.status = status;
        this.des = des;
    }

    public Task() {

    }

    public void configure(String[] des) {
        setDes(des);
    }

    public String getDes() {
        return this.des;
    }

    public void setDes(String[] des) {
        this.des = des[0];
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void printStatus() {
        String s = (status) ? "X" : " ";
        System.out.println("[" + s + "] " + this.des);
    }

    public String toString() {
        String s = (status) ? "X" : " ";
        return "  | " + s + this.des;
    }
}
