<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
=======
import java.io.Serializable;

public class Task implements Serializable {
>>>>>>> branch-Level-7
    protected String description;
    protected boolean isDone;
    protected TypeOfTask type;

    public Task(String description,TypeOfTask type){
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    public String getDescription(){
        return this.description;
    }

    public String getTypeOfTask(){
        switch(this.type){
            case deadline:
                return "D";
            case event:
                return "E";
            case todo:
                return "T";
            default:
                return null;
        }
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void unmarkAsDone(){
        this.isDone = false;
    }

    public static LocalDate covertToLocalDate(String date) throws Exception {
        String[] dateFormats = {"dd/MM/yyyy", "yyyy-MM-dd", "MM-dd-yyyy","d/MM/yyyy","d/M/yyyy"};
        LocalDate localDate;
        for (String dateFormat : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                localDate = LocalDate.parse(date, formatter);
                return localDate;
            } catch (DateTimeParseException e) {
                // continue trying other formats
                continue;
            }
        }
        throw new Exception("format of date given is not recognized");
    }

    public static LocalTime convertToLocalTime(String time) throws Exception{
        String[] timeFormats = {"h:mm a", "HH:mm", "hh:mm a", "HH:mm:ss","HHmm","h:mma","hh:mma","h:a"};
        LocalTime localTime;
        for (String timeFormat : timeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
                localTime = LocalTime.parse(time.toUpperCase(), formatter);
                return localTime;
            } catch (DateTimeParseException e) {
                // continue trying other formats
                continue;
            }
        }
        throw new Exception("format of time given is not recognized");
    }
}
