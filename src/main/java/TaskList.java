import java.util.Locale;

public class TaskList {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    /**
     * Adds Task to the List , and returns the success Message
     * @param input
     * @return
     */
    public String addTasks(String input,String formatSpace) {
        String[] splitCommand = input.trim().split(" ", 2);
        String command = splitCommand[0].toLowerCase();
        String message = splitCommand[1];

        if (command.equals("todo")) {
            tasks[taskCount] = new Todo(message);
        } else if (command.equals("deadline")) {
            String[] parsedMessage = this.parseDeadline(message);
            tasks[taskCount] = new Deadline(parsedMessage[0] , parsedMessage[1]);
        } else if (command.equals("event")) {
            String[] parsedMessage = this.parseEvent(message);
            tasks[taskCount] = new Event(parsedMessage[0] , parsedMessage[1], parsedMessage[2]);
        } else {
            // Throw Error
        }
        taskCount++;
        return formatSpace + tasks[taskCount - 1].getRepresentation();
    }

    /**
     *
     * @param input
     * @param formatSpace
     * @return return formatted task representation.
     */
    public String toggleTask(String input, String formatSpace) {
        String[] parseInput = input.trim().split(" ",2);
        int index =  Integer.parseInt(parseInput[1]) - 1;
         if (parseInput[0].toLowerCase().contains("unmark")) {
            tasks[index].unmark();
            return formatSpace + tasks[index].getRepresentation();
        } else if (parseInput[0].toLowerCase().contains("mark")) {
            tasks[index].mark();
            return formatSpace + tasks[index].getRepresentation();
        }else {
            // Uknown Command
            return "Command not Found.";
        }
    }

    /**
     * Retreives Tasks from the list and formats according to UI specifications.
     * @param formatSpace
     * @return formatted tasks from taskList
     */
    public String formatTasks(String formatSpace) {
        String  res = "";
        for (int i = 0; i < taskCount; i++){
                res +=  formatSpace + i + ". " + tasks[i].getRepresentation() + "\n";
        }
        return res;
    }

    private String[] parseDeadline(String mes) {
        return mes.split("/by", 2);
    }

    private String[] parseEvent(String mes) {
        String[] output = new String[3];
        String[] messageFromTo = mes.split("/from", 2);
        output[0] = messageFromTo[0];
        String[] fromTo = messageFromTo[1].split("/to", 2);
        output[1] = fromTo[0];
        output[2] = fromTo[1];
        return output;
    }


}
