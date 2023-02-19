package treebot.props;


import java.time.LocalDateTime;

public class DeadlineProps extends Props {
    private LocalDateTime deadline;
    public DeadlineProps(String taskDescription, LocalDateTime deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;

    }
    public LocalDateTime getDeadline() {
        return this.deadline;
    }


}
