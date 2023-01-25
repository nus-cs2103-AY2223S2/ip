import java.time.LocalDateTime;

public class Deadline extends Task{
    protected LocalDateTime deadline;

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = parseDateTime(deadline);
    }

    @Override
    public String toString(){
        return "D" + "|" + super.toString() + "(by: " + this.deadline.toString() + ")";
    }

    private LocalDateTime parseDateTime(String deadline){
        String[] dateAndTime = deadline.split(" ");
        String[] dayMonthYear = dateAndTime[0].split("/");
        int hour = Integer.valueOf(dateAndTime[1])/100;
        int minute = Integer.valueOf(dateAndTime[1])%100;
        int[] ddMMYY = new int[3];
        for (int i = 0; i < dayMonthYear.length;i++){
            ddMMYY[i] = Integer.valueOf(dayMonthYear[i]);
        }

        return LocalDateTime.of(ddMMYY[2], ddMMYY[1], ddMMYY[0], hour, minute);
    }
}
