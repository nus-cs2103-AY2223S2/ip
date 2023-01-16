public class TaskInfoParser {
    private String input;

    /**
     * This is a parser to extract information from the task to decide which object it is referring
     * to in the command input and returns it accordingly.
     * @param input command input from stdin
     * @return Task object specific to the command input string
     */
    public static Task obtainTask(String input) {
        String[] commands = input.split(" ");
        switch(commands[0]) {
            case "todo" :
                String toDoDescription = buildDescription(commands, 1, commands.length - 1);
                return new ToDo(toDoDescription);
            case "deadline" :
                int byIndex = input.indexOf('/');
                String deadline = input.substring(byIndex + 1);
                String deadlineDescription = input.substring(9, byIndex - 1);
                return new Deadline(deadlineDescription, deadline);
            case "event" :
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                String eventDescription = input.substring(6, fromIndex - 2);
                String startTime = input.substring(fromIndex + 6, toIndex - 1);
                String endTime = input.substring(toIndex + 4);
                return new Event(eventDescription, startTime, endTime);
            default:
                break;
        }
        return null;
    }

    /**
     * Concatenates the strings from [start, end] indices of commands array to form
     * required description of the task.
     * @param commands array of commands split by regex patterns like " "
     * @param start start index of commands array (inclusive)
     * @param end end index of commands array (inclusive)
     * @return a string description of a task
     */
    public static String buildDescription(String[] commands, int start, int end) {
        String description = "";
        for (int i = start; i < end; i++) {
            description += commands[i] + " ";
        }
        return description + commands[end];
    }
}
