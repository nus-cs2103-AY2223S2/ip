import Exceptions.CommandNotFoundException;
import Exceptions.NonExistentTask;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;


public class TaskList {
    private  ArrayList<Task> tasks = new ArrayList<>();
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
        Task newTask = null;
        if (command.equals("todo")) {
            newTask = new Todo(message);
        } else if (command.equals("deadline")) {
            String[] parsedMessage = this.parseDeadline(message);
            newTask = new Deadline(parsedMessage[0] , parsedMessage[1]);
        } else if (command.equals("event")) {
            String[] parsedMessage = this.parseEvent(message);
            newTask = new Event(parsedMessage[0] , parsedMessage[1], parsedMessage[2]);
        } else {
            // Throw Error
            throw new CommandNotFoundException(input);
        }
        tasks.add(newTask);
        taskCount++;
        return formatSpace + tasks.get(taskCount - 1).getRepresentation();
    }

    public String deleteTask(String input,String formatSpace) throws NonExistentTask,CommandNotFoundException {
        String[] parseInput = input.trim().split(" ",2);
        Task temp = null;
        if (parseInput.length == 1 || Integer.parseInt(parseInput[1].trim()) - 1 >= this.taskCount) {
            throw new NonExistentTask(input);
        }
        int index = Integer.parseInt(parseInput[1].trim()) - 1;
        if (parseInput[0].toLowerCase().equals("delete")) {
            temp = tasks.get(index);
            tasks.remove(index);
            taskCount--;
        } else {
            throw new CommandNotFoundException(input);
        }
        return formatSpace + temp.getRepresentation();
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
            tasks.get(index).unmark();
        } else if (parseInput[0].toLowerCase().equals("mark")) {
            tasks.get(index).mark();
        }else {
            // Uknown Command
            throw new CommandNotFoundException(input);
        }
        return formatSpace + tasks.get(index).getRepresentation();
    }

    /**
     * Retreives Tasks from the list and formats according to UI specifications.
     * @param formatSpace
     * @return formatted tasks from taskList
     */
    public String formatTasks(String formatSpace) {
        String  res = "";
        for (int i = 0; i < taskCount; i++){
                res +=  formatSpace + i + ". " + tasks.get(i).getRepresentation() + "\n";
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
