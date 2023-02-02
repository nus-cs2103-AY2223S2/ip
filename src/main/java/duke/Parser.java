package duke;

public class Parser {

    /**
     * Converts a task from the task list format
     * to a Task object
     * @param line line to be converted to a <code>Task</code>
     * @return a <code>Task</code>> object with the relevant information
     */
    public static Task convertTaskFromLineInTaskList(String line) {
        String[] taskInfo = line.split("[|]");
        String taskType = taskInfo[0].trim();
        boolean isDone = taskInfo[1].trim().equals("1");
        String taskDescription = taskInfo[2].trim();
        switch (taskType) {
        case "T":
            return new ToDos(taskDescription, isDone);
        case "D":
            String taskTime = taskInfo[3].trim();
            return new Deadlines(taskDescription, taskTime, isDone);
        case "E":
            String startTime = taskInfo[3].trim();
            String endTime = taskInfo[4].trim();
            return new Events(taskDescription, startTime, endTime, isDone);
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }

    /**
     * Makes a <code>ToDos</code> object from the given line.
     * @param line information of the <code>ToDos</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>ToDos</code>
     */
    public static Task makeTodoFromCommand(String line) {
        String taskName = line.substring(5);
        return new ToDos(taskName);
    }

    /**
     * Makes a <code>Deadlines</code> object from the given line.
     * @param line information of the <code>Deadlines</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>Deadlines</code>
     */
    public static Task makeDeadlineFromCommand(String line) {
        String taskInfo = line.substring(9);
        String taskName = taskInfo.split(" /by")[0];
        String taskDeadline = taskInfo.split("/by ")[1];
        return new Deadlines(taskName, taskDeadline);
    }

    /**
     * Makes a <code>Events</code> object from the given line.
     * @param line information of the <code>Events</code> in a
     *             <code>String</code> format
     * @return a <code>Task</code> object of the <code>Events</code>
     */
    public static Task makeEventFromCommand(String line) {
        String taskInfo = line.substring(6);
        String taskName = taskInfo.split(" /")[0];
        String taskStart = taskInfo.split(" /")[1].substring(5);
        String taskEnd = taskInfo.split(" /")[2].substring(3);
        return new Events(taskName, taskStart, taskEnd);
    }
}
