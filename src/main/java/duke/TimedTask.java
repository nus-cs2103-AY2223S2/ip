package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public abstract class TimedTask extends Task{
    DateTimeFormatter isoFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter consoleFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    DateTimeFormatter fileFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime end;
    String consoleEndString;
    String fileEndString;

    public TimedTask(boolean status, String des) {
        super(status, des);
    }

    public TimedTask() {
        super();
    }

    public LocalDateTime dateTimeConsoleInParse(String s) {
        return LocalDateTime.parse(s,isoFormat);
    }
    public LocalDateTime dateTimeFileInParse(String s) {
        return LocalDateTime.parse(s,fileFormat);
    }
    @Override
    public void setDes(String[] des) {
        super.des = des[0];
        setEnd(des[1]);
    }
    @Override
    public void configure(String[] des) {
        des[1] = dateTimeFileInParse(des[1]).format(isoFormat);
        setDes(des);
    }

    public void setEnd(String s) {
        this.end = dateTimeConsoleInParse(s);
        this.consoleEndString = end.format(consoleFormat);
        this.fileEndString = end.format(fileFormat);
    }
    public String toStringConsoleEnd() {
       return this.consoleEndString;
    }

    public String toStringFileEnd() {
        return this.fileEndString;
    }
}
