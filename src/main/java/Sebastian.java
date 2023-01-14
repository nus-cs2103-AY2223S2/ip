import SebastianExceptions.IllegalInstructionException;
import SebastianExceptions.InputFormatMismacthException;
import SebastianExceptions.TaskNotExistException;

import java.util.Scanner;

public class Sebastian {
    private final TaskList tasks;

    private Sebastian() {
        this.tasks = new TaskList();
    }

    private String greet() {
        return Utilities.space() + "Greetings, I'm Sebastian" + "\n" +
                Utilities.space() + "I'm at your service";
    }

    private String exit() {
        return Utilities.space() + "Bye. It's my pleasure to serve you";
    }

    private String echo(String instruction){
        return Utilities.space() + instruction;
    }

    private String addTask(Task task){
        return Utilities.space() + "Got it. I've added this task: " + "\n" +
                Utilities.space() + Utilities.space() + task + "\n" +
                Utilities.space() + "Now you have " + this.tasks.getTotalTasks() + " tasks in the list";
    }

    private String addTodo(String instruction) throws InputFormatMismacthException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new InputFormatMismacthException("The description of a todo cannot be empty.");
        } else {
            String task = instruction.substring(5);
            return this.addTask(this.tasks.addTodo(task));
        }
    }

    private String addDeadline(String instruction) throws InputFormatMismacthException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new InputFormatMismacthException("The description of a deadline cannot be empty.");
        } else {
            String deadline = instruction.substring(9);
            String[] task = deadline.split("/by");
            if(task.length != 2) {
                throw new InputFormatMismacthException(
                        "Please specify a deadline in the following format:" + "\n" +
                                Utilities.space() + "deadline [deadline] /by [end_time]"
                );
            } else {
                return this.addTask(this.tasks.addDeadline(task[0], task[1].trim()));
            }
        }
    }

    private String addEvent(String instruction) throws InputFormatMismacthException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new InputFormatMismacthException("The description of an event cannot be empty.");
        } else {
            String event = instruction.substring(6);
            String[] task = event.split("/from|/to");
            if(task.length!=3) {
                throw new InputFormatMismacthException(
                        "Please specify a deadline in the following format: " + "\n" +
                                Utilities.space() + "event [event] /from [start_time] /to [end_time]"
                );
            } else {
                return this.addTask(this.tasks.addEvent(task[0],task[1].trim(), task[2].trim()));
            }
        }
    }

    private String markTask(String instruction) throws InputFormatMismacthException, TaskNotExistException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return  Utilities.space()+  "Well Done. I have marked this task as done: " + "\n" +
                        Utilities.space() + this.tasks.markTaskAtIndex(taskIndex);
            } catch (NumberFormatException e) {
                throw new InputFormatMismacthException("Please enter the index of the task you wish to mark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        } else {
            throw new InputFormatMismacthException(
                    "Please specify the task you wish to mark in the following format:" + "\n" +
                            Utilities.space() + "mark [task index]"
            );
        }
    }

    private String unmarkTask(String instruction) throws InputFormatMismacthException, TaskNotExistException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return Utilities.space() + "No problem, I have unmarked this task: " + "\n" +
                        Utilities.space() + this.tasks.unmarkTaskAtIndex(taskIndex);
            } catch (NumberFormatException e) {
                throw new InputFormatMismacthException("Please enter the index of the task you wish to unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        } else {
            throw new InputFormatMismacthException(
                    "Please specify the task you wish to mark in the following format:" + "\n" +
                           Utilities.space() + "unmark [task index]"
            );
        }
    }

    private String deleteTask(String instruction) throws InputFormatMismacthException, TaskNotExistException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return Utilities.space() + "Noted. I have deleted this task: " + "\n" +
                        Utilities.space() + this.tasks.deleteTaskAtIndex(taskIndex) + "\n" +
                        Utilities.space() + "Now your have " + this.tasks.getTotalTasks() + " tasks in the list";
            } catch (NumberFormatException e) {
                throw new InputFormatMismacthException("Please enter the index of the task you wish to unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        } else {
            throw new InputFormatMismacthException(
                    "Please specify the task you wish to delete in the following format:" + "\n" +
                            Utilities.space() + "delete [task index]"
            );
        }
    }

    private String showList(String instruction) {
        return Utilities.space() + "Here are the tasks in your list" + "\n" + this.tasks;
    }

    private void onDuty() throws IllegalInstructionException{
        Scanner scan = new Scanner(System.in);
        String instruction = scan.nextLine();
        String action = instruction.split(" ")[0];
        String res;
        while (!action.equals("bye")) {
            switch (action) {
                case "list":
                    res = this.showList(instruction);
                    break;
                case "mark":
                    res = this.markTask(instruction);
                    break;
                case "unmark":
                    res = this.unmarkTask(instruction);
                    break;
                case "todo":
                    res = this.addTodo(instruction);
                    break;
                case "deadline":
                    res = this.addDeadline(instruction);
                    break;
                case "event":
                    res = this.addEvent(instruction);
                    break;
                case "delete" :
                    res  = this.deleteTask(instruction);
                    break;
                default:
                    throw new IllegalInstructionException();
            }
            Utilities.printFormattedString(res);
            instruction = scan.nextLine();
            action = instruction.split(" ")[0];
        }
    }

    public static void main(String[] args) {
        Sebastian sebastian = new Sebastian();
        Utilities.printFormattedString(sebastian.greet());
        boolean flag = true;
        while(flag){
            try {
                sebastian.onDuty();
                flag = false;
            } catch (IllegalInstructionException | TaskNotExistException | InputFormatMismacthException e) {
                Utilities.printFormattedString(Utilities.space() + e.getMessage());
            }
        }
        Utilities.printFormattedString(sebastian.exit());
    }
}
