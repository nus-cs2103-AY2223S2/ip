package treebot.props;

import treebot.utils.LocalDateTimeFormatter;

import java.time.LocalDateTime;

public class DeadlineProps extends Props {
    LocalDateTimeFormatter dateTimeFormatter;
    private LocalDateTime deadline;
    public DeadlineProps(String taskDescription, LocalDateTime deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;

    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }


}
