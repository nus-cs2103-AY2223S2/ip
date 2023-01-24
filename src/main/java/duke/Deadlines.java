package duke;
public class Deadlines extends TimedTask{
    public Deadlines(){super();}
    public Deadlines(boolean status, String des) {
        super();
        setStatus(status);
        String[] s = des.split(" /by ");
        setDes(s);
    }

    @Override
    public void printStatus() {
        String s = (status)? "X":" ";
        System.out.println("[D][" +s+ "] " + getDes() + " (by: " + toStringConsoleEnd() + ")");
    }

    @Override
    public String toString() {
        String s = (status)? "X":" ";
        return "D | " + s + " | " + getDes() + " | " + super.toStringFileEnd();
    }
}
