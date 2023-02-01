package duke;

public class Parser {

    /** Converts a task from a format of:
     * TASK_TYPE | TASK_IS_DONE | TASK_NAME | TASK_START | TASK_END
     * to a Task object
     * @return a Task object
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

    public static Task makeTodoFromCommand(String line) {
        String taskName = line.substring(5);
        return new ToDos(taskName);
    }

    public static Task makeDeadlineFromCommand(String line) {
        String taskInfo = line.substring(9);
        String taskName = taskInfo.split(" /by")[0];
        String taskDeadline = taskInfo.split("/by ")[1];
        return new Deadlines(taskName, taskDeadline);
    }

    public static Task makeEventFromCommand(String line) {
        String taskInfo = line.substring(6);
        String taskName = taskInfo.split(" /")[0];
        String taskStart = taskInfo.split(" /")[1].substring(5);
        String taskEnd = taskInfo.split(" /")[2].substring(3);
        return new Events(taskName, taskStart, taskEnd);
    }
}
