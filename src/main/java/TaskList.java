import Exceptions.CommandNotFoundException;
import Exceptions.NonExistentTask;

public class TaskList {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    /**
     * Adds Task to the List , and returns the success Message
     * @param input
     * @return
     */
    public String addTasks(String input,String formatSpace) throws CommandNotFoundException{
        String[] splitCommand = input.trim().split(" ", 2);
        String command = splitCommand[0].toLowerCase();
        if (splitCommand.length == 1) {
            throw new CommandNotFoundException(input);
        }
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
            throw new CommandNotFoundException(input);
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
    public String toggleTask(String input, String formatSpace) throws NonExistentTask, CommandNotFoundException{
        String[] parseInput = input.trim().split(" ",2);
        if (parseInput.length == 1 || Integer.parseInt(parseInput[1].trim()) - 1 >= this.taskCount) {
            throw new NonExistentTask(input);
        }
        int index =  Integer.parseInt(parseInput[1].trim()) - 1;
         if (parseInput[0].toLowerCase().equals("unmark")) {
            tasks[index].unmark();
            return formatSpace + tasks[index].getRepresentation();
        } else if (parseInput[0].toLowerCase().equals("mark")) {
            tasks[index].mark();
            return formatSpace + tasks[index].getRepresentation();
        }else {
            // Uknown Command
            throw new CommandNotFoundException(input);
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
