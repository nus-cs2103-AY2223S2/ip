package duke;

import java.time.LocalDateTime;

public class Events extends TimedTask{
    LocalDateTime start;
    String consoleStartString;
    String fileStartString;
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
        d[1] = dateTimeFileInParse(temp[1]).format(isoFormat);
        setDes(d);
        setStart(dateTimeFileInParse(temp[0]).format(isoFormat));
    }
    public void setStart(String s) {
        this.start = dateTimeConsoleInParse(s);
        this.consoleStartString = start.format(super.consoleFormat);
        this.fileStartString = start.format(super.fileFormat);
    }

    public String toStringConsoleStart() {
        return this.consoleStartString;
    }
    public String toStringFileStart() {
        return this.fileStartString;
    }

    @Override
    public void printStatus() {
        String s = (status)? "X":" ";
        System.out.println("[E][" +s+ "] " + getDes() + " (from: " + toStringConsoleStart() + " to: "+ toStringConsoleEnd() +")");
    }

    @Override
    public String toString() {
        String s = (status)? "X":" ";
        return "E | " + s + " | " + getDes() + " | " + toStringFileStart() + " to " + toStringFileEnd();
    }
}
