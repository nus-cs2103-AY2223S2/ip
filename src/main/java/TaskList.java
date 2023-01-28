import Exceptions.AvaException;
import Exceptions.CommandNotFoundException;
import Exceptions.DateTimeNotParsed;
import Exceptions.NonExistentTask;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;


public class TaskList {
    private  ArrayList<Task> tasks = new ArrayList<>();
    private int taskCount = 0;
    private String FILE_NAME = "tasks.txt";

    private Storage storage = new Storage();

    public TaskList() throws AvaException {
        this.addFromStorage();
    }

    /**
     * Adds Task to the List , and returns the success Message
     * @param input
     * @return
     */

    public String addTasks(String input,String formatSpace) throws AvaException {
        try {
            String[] splitCommand = input.trim().split(" ", 2);
            String command = splitCommand[0].toLowerCase();
            String message = splitCommand[1];
            Task newTask = null;
            if (command.equals("todo")) {
                newTask = new Todo(message);
            } else if (command.equals("deadline")) {
                String[] parsedMessage = this.parseDeadline(message);
                newTask = new Deadline(parsedMessage[0], parsedMessage[1]);
            } else if (command.equals("event")) {
                String[] parsedMessage = this.parseEvent(message);
                newTask = new Event(parsedMessage[0], parsedMessage[1], parsedMessage[2]);
            } else {
                // Throw Error
                throw new CommandNotFoundException(input);
            }
            tasks.add(newTask);
            taskCount++;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandNotFoundException(input);
        }

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
    public void addToStorage() {
        boolean canAppend = true;
        storage.deleteFile(FILE_NAME);
        for (Task t : tasks) {
            storage.writeToStorage(FILE_NAME, t.getStorageFormat(), canAppend);
        }
    }


    private void addFromStorage() throws AvaException {
        ArrayList<String> taskStrings = storage.readStorage(FILE_NAME);
        for (String task : taskStrings){
            String[] parsed = task.split(",");

            Task newTask = null;
            if (parsed[0].contains(Todo.TASK_SIGN)) {
                newTask = new Todo(parsed[2]);
            } else if (parsed[0].contains(Deadline.TASK_SIGN) ) {
                newTask = new Deadline(parsed[2] , parsed[3]);
            } else if (parsed[0].contains(Event.TASK_SIGN) ) {
                newTask = new Event(parsed[2] , parsed[3], parsed[4]);
            } else {
                //Uknown ouput
            }
            if (Boolean.valueOf(parsed[1])) {
                newTask.mark();
            }
            tasks.add(newTask);
            taskCount++;
        }

    }


}
