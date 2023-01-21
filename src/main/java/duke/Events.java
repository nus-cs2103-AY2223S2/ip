package duke;
public class Events extends TimedTask{
    String des;
    public Events(boolean status, String des) {
        super(status, des);
        String[] s = des.split(" /from ");
        this.des = s[0];
        String[] x = s[1].split(" /to ");
        super.setStart(x[0]);
        super.setEnd(x[1]);
    }

    @Override
    public void printStatus() {
        String s = (status)? "X":" ";
        System.out.println("[E][" +s+ "] " + this.des + " (from: " + toStringStart() + " to: "+ toStringEnd() +")");
    }

    @Override
    public String toString() {
        String s = (status)? "X":" ";
        return "E | " + s + " | " + this.des + " | " + super.toStringStart() + " to " + super.toStringEnd();
    }
}
