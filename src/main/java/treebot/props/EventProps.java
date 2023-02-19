package treebot.props;



import java.time.LocalDateTime;

public class EventProps<T> extends Props {


    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventProps(String taskDescription, LocalDateTime startDateString, LocalDateTime endDateString) {
        this.taskDescription = taskDescription;
        this.startDate = startDateString;
        this.endDate = endDateString;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }


}
