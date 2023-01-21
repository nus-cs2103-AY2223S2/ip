package duke;

import java.io.IOException;

/**
 * Parser class to convert user's input into commands
 * for the duke application.
 */
public class Parser {

    private TaskList taskList;
    private int size;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.size = taskList.size();
    }

    /**
     * Function that parses the input string from ui class to determine
     * the duke operation to perform next.
     *
     * @param input input string from user read from the Ui class
     * @return boolean return false if the user indicates the end of current session
     */
    public boolean parseInput (String input) {
        if (input.equals("bye")) {
            return false;
        } else {
            if (input.contains("unmark")){
                int i = Integer.parseInt(input.substring(7,8));
                Task t = taskList.unmarkTask(i);
                try {
                    FileReadWrite.writeUnmark(i, t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Ui.printUnmark(taskList, i);
            } else if (input.contains("mark")) {
                int i = Integer.parseInt(input.substring(5,6));
                Task t = taskList.markTask(i);
                try {
                    FileReadWrite.writeMark(i, t);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Ui.printMark(taskList, i);
            } else if (input.contains("delete")) {
                int i = Integer.parseInt(input.substring(7,8));
                Ui.printDelete(taskList, i, this.size);
                taskList.delete(i);
                try{
                    FileReadWrite.writeDelete(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.size--;
            } else {
                switch (input){
                case "list":
                    Ui.printListCommand(taskList);
                    break;
                default:
                    try {
                        Task newTask = createTask(input);
                        taskList.add(newTask);
                        try{
                            FileReadWrite.writeTask(taskList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        this.size++;
                        Ui.printAddTask(newTask, size);
                        } catch (EmptyArgException e) {
                            Ui.emptyError();
                        } catch (UnknownInputException u) {
                            Ui.invalidInputError();
                        }
                }

            }
        }
        return true;
    }

    /**
     * Function that creates the Task objects from user inputs pertaining
     * to adding tasks onto the list.
     *
     * @param input from the user indicating which operation he wants duke to perform
     * @return Task return the task object created from the user input
     * @throws DukeException if the input is invalid.
     */
    public static Task createTask(String input) throws DukeException {
        Task newTask;
        if (input.contains("todo")){
            if (input.equals("todo")){
                throw new EmptyArgException("Did not provide argument");
            }
            newTask = new Todo(input.substring(5), false);
            return newTask;
        } else if (input.contains("deadline")){
            if (input.equals("deadline")){
                throw new EmptyArgException("Did not provide argument");
            }
            String[] arr = input.substring(9).split("/");
            newTask = new Deadline(arr[0],false, arr[1]);
            return newTask;
        } else if (input.contains("event")){
            if (input.equals("event")){
                throw new EmptyArgException("Did not provide argument");
            }
            String[] arr = input.substring(6).split("/");
            newTask = new Event(arr[0],false, arr[1], arr[2]);
            return newTask;
        } else {
            throw new UnknownInputException("Unknown Input!");
        }
    }
}
