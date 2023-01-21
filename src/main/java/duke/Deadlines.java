package duke;
public class Deadlines extends TimedTask{
    String des;
    public Deadlines(boolean status, String des) {
        super(status, des);
        String[] s = des.split(" /by ");
        this.des = s[0];
        super.setEnd(s[1]);
        super.setStart(null);
    }

    @Override
    public void printStatus() {
        String s = (status)? "X":" ";
        System.out.println("[D][" +s+ "] " + this.des + " (by: " + toStringEnd() + ")");
    }

    @Override
    public String toString() {
        String s = (status)? "X":" ";
        return "D | " + s + " | " + this.des + " | " + super.toStringEnd();
    }
}
