package treebot.props;



import java.time.LocalDateTime;

/**
 * Represents the props needed to create an <code>Event</code> object.
 */
public class EventProps extends Props {


    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs the <code>EventProps</code> objects.
     * @param taskDescription
     * @param startDateString
     * @param endDateString
     */
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
