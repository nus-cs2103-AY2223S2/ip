package duke;

import java.time.LocalDateTime;

public class Events extends TimedTask{
    LocalDateTime start;
    String startString;
    public Events() {super();}
    public Events(boolean status, String des) {
        super();
        setStatus(status);
        String[] d = new String[3];
        String[] s = des.split(" /from ");
        d[0] = s[0];
        String[] x = s[1].split(" /to ");
        d[1] = x[1];
        setDes(d);
        setStart(x[0]);
    }
    @Override
    public void configure(String[] des) {
        String[] d = new String[2];
        d[0] = des[0];
        String[] temp = des[1].split(" to ");
        d[1] = temp[1];
        setDes(d);
        setStart(temp[0]);
    }
    public void setStart(String s) {
        this.start = dateTimeParse(s);
        this.startString = start.format(super.formatter);
    }

    public String toStringStart() {
        return this.startString;
    }

    @Override
    public void printStatus() {
        String s = (status)? "X":" ";
        System.out.println("[E][" +s+ "] " + getDes() + " (from: " + toStringStart() + " to: "+ toStringEnd() +")");
    }

    @Override
    public String toString() {
        String s = (status)? "X":" ";
        return "E | " + s + " | " + getDes() + " | " + toStringStart() + " to " + toStringEnd();
    }
}
