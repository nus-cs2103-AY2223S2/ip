//addapted from partial solution provided in lecture notes
//https://nus-cs2103-ay2223s2.github.io/website/admin/ip-w2.html
public class Task {
    String desc;
    String type;
    boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
        this.type = null;
    }

    public Task(String desc,String type) {
        this.desc = desc;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDesc() {
        return desc;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.type,this.getStatusIcon(),this.getDesc());
    }
}
