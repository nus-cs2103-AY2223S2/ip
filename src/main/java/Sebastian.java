import sebastianExceptions.*;
import formatters.Formatter;

import java.util.Scanner;

public class Sebastian {
    private final TaskList tasks;

    private Sebastian() {
        this.tasks = new TaskList();
    }

    /**
     * Start a session by greeting the user
     * @return a greet
     */
    private String greet() {
        return Formatter.space() + "Greetings, I'm Sebastian" + "\n" +
                Formatter.space() + "I'm at your service";
    }

    /**
     * End a session
     * @return final greet
     */
    private String exit() {
        return Formatter.space() + "Bye. It's my pleasure to serve you";
    }

    /**
     * Echo whatever the user has typed in
     * @param instruction the user input
     * @return the same user input
     */
    private String echo(String instruction){
        return Formatter.space() + instruction;
    }

    /**
     * Response after a task is added to the task list
     * @param task the task being added
     * @return notice that the task has been added
     */
    private String addTask(Task task){
        return Formatter.space() + "Noted. I've added this task: " + "\n" +
                Formatter.space() + Formatter.space() + task + "\n" +
                Formatter.space() + "Now you have " + this.tasks.getTotalTasks() + " tasks in the list";
    }


    /**
     * Add a to-do to the task list
     * @param instruction user input
     * @return notice that the to-do has been added
     * @throws LackOfArgumentException when the user did not specify a to-do to add
     */
    private String addTodo(String instruction) throws LackOfArgumentException {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        } else {
            String task = instruction.substring(5);
            return this.addTask(this.tasks.addTodo(task));
        }
    }

    /**
     * Add a deadline to the task list
     * @param instruction user input
     * @return notice that the deadline has been added
     * @throws LackOfArgumentException when the user did not specify a deadline to add
     * @throws DeadlineFormatMismatchException when the format for adding a deadline is not followed
     */
    private String addDeadline(String instruction) throws LackOfArgumentException, DeadlineFormatMismatchException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        } else {
            String deadline = instruction.substring(9);
            String[] task = deadline.split("/by");
            if(task.length != 2) {
                throw new DeadlineFormatMismatchException();
            } else {
                return this.addTask(this.tasks.addDeadline(task[0], task[1].trim()));
            }
        }
    }

    /**
     * Add an event to the task list
     * @param instruction user input
     * @return notice that the event has been added
     * @throws LackOfArgumentException when the user did not specify an event to add
     * @throws EventFormatMismatchException when the format for adding an event is not followed
     */
    private String addEvent(String instruction) throws LackOfArgumentException, EventFormatMismatchException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        } else {
            String event = instruction.substring(6);
            String[] task = event.split("/from|/to");
            if(task.length!=3) {
                throw new EventFormatMismatchException();
            } else {
                return this.addTask(this.tasks.addEvent(task[0],task[1].trim(), task[2].trim()));
            }
        }
    }

    /**
     * Mark a task as done
     * @param instruction user input
     * @return notice that the task has been marked
     * @throws LackOfArgumentException when the user did not specify a task to mark as done
     * @throws InstructionFormatMismatchException when the user did not follow the format to mark a task
     * @throws TaskNotExistException when the task specify by the user does not exist
     */
    private String markTask(String instruction) throws LackOfArgumentException, InstructionFormatMismatchException, TaskNotExistException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        }
        else if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return  Formatter.space()+  "Well Done. I have marked this task as done: " + "\n" +
                         Formatter.space() + Formatter.space() + this.tasks.markTaskAtIndex(taskIndex);
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("mark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        }
        else {
            throw new InstructionFormatMismatchException("mark");
        }
    }

    /**
     * Mark a task as not done
     * @param instruction user input
     * @return notice that the task has been marked as not done
     * @throws LackOfArgumentException when the user did not specify a task to mark as not done
     * @throws InstructionFormatMismatchException when the user did not follow the format to unmark a task
     * @throws TaskNotExistException when the task specify by the user does not exist
     */
    private String unmarkTask(String instruction) throws LackOfArgumentException, InstructionFormatMismatchException, TaskNotExistException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        }
        else if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return Formatter.space() + "No problem, I have unmarked this task: " + "\n" +
                        Formatter.space() + Formatter.space() + this.tasks.unmarkTaskAtIndex(taskIndex);
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("unmark");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        }
        else {
            throw new InstructionFormatMismatchException("unmark");
        }
    }

    /**
     * Delete a task from the task list
     * @param instruction user input
     * @return notice that the task has been removed
     * @throws LackOfArgumentException when the user did not specify a task to delete
     * @throws InstructionFormatMismatchException when the user did not follow the format to delete a task
     * @throws TaskNotExistException when the task specify by the user does not exist
     */
    private String deleteTask(String instruction) throws LackOfArgumentException, InstructionFormatMismatchException, TaskNotExistException{
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            throw new LackOfArgumentException();
        }
        else if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return Formatter.space() + "Noted. I have deleted this task: " + "\n" +
                        Formatter.space() + Formatter.space() + this.tasks.deleteTaskAtIndex(taskIndex) + "\n" +
                        Formatter.space() + "Now your have " + this.tasks.getTotalTasks() + " tasks in the list";
            } catch (NumberFormatException e) {
                throw new InstructionFormatMismatchException("delete");
            } catch (IndexOutOfBoundsException e) {
                throw new TaskNotExistException();
            }
        }
        else {
            throw new InstructionFormatMismatchException("delete");
        }
    }

    /**
     * Show the current task list
     * @param instruction user input
     * @return the current task list
     */
    private String showList(String instruction) {
        return Formatter.space() + "Here are the tasks in your list" + "\n" + this.tasks;
    }

    /**
     * Start a prompt-response cycle
     * @throws IllegalInputException when user input does not constitute a valid instruction
     */
    private void onDuty() throws IllegalInputException{
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
                    throw new IllegalInputException();
            }
            Formatter.printFormattedString(res);
            instruction = scan.nextLine();
            action = instruction.split(" ")[0];
        }
    }

    public static void main(String[] args) {
        Sebastian sebastian = new Sebastian();
        Formatter.printFormattedString(sebastian.greet());
        boolean flag = true;
        while(flag){
            try {
                sebastian.onDuty();
                flag = false;
            } catch (IllegalInputException | TaskNotExistException | InputFormatMismatchException e) {
                Formatter.printFormattedString(Formatter.space() + e.getMessage());
            }
        }
        Formatter.printFormattedString(sebastian.exit());
    }
}
