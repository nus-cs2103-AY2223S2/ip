import java.time.LocalTime;
import java.util.Arrays;

public class Event extends Task{

    //default constructor
    protected LocalTime startTime;
    protected LocalTime endTime;
    protected String startDay;
    protected String endDay;

    public Event(String description,String startTime,String endTime) throws Exception {
        super(description,TypeOfTask.event);

        // to check if the user specifies the day also
        String[] startTimeArray = startTime.split(" ");
        String[] endTimeArray = endTime.split(" ");
        // Example: /by Monday /to Tuesday
        if(startTimeArray.length == 1 && endTimeArray.length == 1) {
            this.startDay = startTimeArray[0];
            this.startTime = null;
            this.endDay = endTimeArray[0]; 
            this.endTime = null;
        }
        // Example: /by Monday 2 pm /to Sunday 4 pm
        else if (startTimeArray.length > 2 && endTimeArray.length > 2) {
            this.startDay = startTimeArray[0];
            String slicedStartTime = String.join(" ",Arrays.copyOfRange(startTimeArray, 1, startTimeArray.length));
            String slicedEndTime = String.join(" ",Arrays.copyOfRange(endTimeArray, 1, endTimeArray.length));
            this.startTime = convertToLocalTime(slicedStartTime);
            this.endDay = endTimeArray[0];
            this.endTime = convertToLocalTime(slicedEndTime);
        }
        // Example: /by Mon 2 pm /to 4 pm
        else if (startTimeArray.length > 2 && endTimeArray.length == 2) {
            this.startDay = startTimeArray[0];
            String slicedStartTime = String.join(" ",Arrays.copyOfRange(startTimeArray, 1, startTimeArray.length));
            String slicedEndTime = String.join(" ",endTimeArray);
            this.startTime = convertToLocalTime(slicedStartTime);
            this.endDay = null;
            this.endTime = convertToLocalTime(slicedEndTime);
        } else {
            throw new Exception("Please check your inputs again for the days and time given");
        }
    }


    @Override
    public String toString() {
        if (startTime == null) {
            return String.format("[E][%s] %s (from: %s to: %s)",super.getStatusIcon(),super.getDescription(),this.startDay,this.endDay);
        } else if (endDay == null) {
            String startTime = this.startTime.toString();
            String endTime = this.endTime.toString();
            return String.format("[E][%s] %s (from: %s %s to: %s)",super.getStatusIcon(),super.getDescription(),this.startDay,startTime,endTime);
        } else {
            String startTime = this.startTime.toString();
            String endTime = this.endTime.toString();
            return String.format("[E][%s] %s (from: %s %s to: %s %s)",super.getStatusIcon(),super.getDescription(),this.startDay,startTime,this.endDay,endTime);
        }  
    }
}
