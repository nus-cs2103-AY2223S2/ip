package duke.parsing;

/**
 * Helper class to hold parsed information of a task loaded from a data file.
 */
public class ParsedTask {
    private char taskType;
    private String taskName;
    private boolean isDone;
    private String dueDate;
    private String fromDate;
    private String toDate;

    public ParsedTask(char taskType, String taskName, boolean isDone,
            String dueDate, String fromDate, String toDate) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.isDone = isDone;
        this.dueDate = dueDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Used by 'Storage' class to parse string task loaded from data file.
     * @param strTask String representation of task from data file
     * @return Task information wrapped in ParsedLoadedTask Object
     */
    public static ParsedTask parseLoadTask(String strTask) {
        char taskType;
        String taskName;
        boolean isDone;
        String dueDate = "";
        String fromDate = "";
        String toDate = "";

        String[] info1 = strTask.split("\\[");
        taskType = info1[1].charAt(0);
        isDone = info1[2].charAt(0) == 'x';
        String[] info2 = info1[2].split(" \\(");
        taskName = info2[0].substring(3);

        switch (taskType) {
        case('T'):
            break;
        case('D'):
            dueDate = info2[1].substring(4, info2[1].indexOf(")"));
            break;
        case('E'):
            String[] info3 = info2[1].split(" to: ");
            fromDate = info3[0].substring(6);
            toDate = info3[1].substring(0, info3[1].indexOf(")"));
            break;
        default:
        }
        return new ParsedTask(taskType, taskName, isDone, dueDate, fromDate, toDate);
    }

    public char getTaskType() {
        return this.taskType;
    }
    public String getTaskName() {
        return this.taskName;
    }
    public boolean getIsDone() {
        return this.isDone;
    }
    public String getDueDate() {
        return this.dueDate;
    }
    public String getFromDate() {
        return this.fromDate;
    }
    public String getToDate() {
        return this.toDate;
    }
}
