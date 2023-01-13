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

    private String addTodo(String instruction) {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            return Utilities.space() + "Pleas specify a todo";
        } else {
            String task = instruction.substring(5);
            return this.addTask(this.tasks.addTodo(task));
        }
    }

    private String addDeadline(String instruction) {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            return Utilities.space() + "Pleas specify a deadline";
        } else {
            String deadline = instruction.substring(9);
            String[] task = deadline.split("/by");
            if(task.length != 2) {
                return Utilities.space() + "Please specify a deadline in the following format: " + "\n" +
                        Utilities.space() + "deadline [deadline] /by [end_time]";
            } else {
                return this.addTask(this.tasks.addDeadline(task[0], task[1].trim()));
            }
        }
    }

    private String addEvent(String instruction) {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            return Utilities.space() + "Pleas specify a event";
        } else {
            String event = instruction.substring(6);
            String[] task = event.split("/from|/to");
            if(task.length!=3) {
                return Utilities.space() + "Please specify a deadline in the following format: " + "\n" +
                        Utilities.space() + "event [event] /from [start_time] /to [end_time]";
            } else {
                return this.addTask(this.tasks.addEvent(task[0],task[1].trim(), task[2].trim()));
            }
        }
    }

    public String showList(String instruction) {
        return Utilities.space() + "Here are the tasks in your list" + "\n" + this.tasks;
    }

    public String markTask(String instruction) {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return  Utilities.space()+  "Well Done. I have marked this task as done: " + "\n" +
                        Utilities.space() + this.tasks.markTaskAtIndex(taskIndex);
            } catch (NumberFormatException e) {
                return "Please specify an integer";
            } catch (IndexOutOfBoundsException e) {
                return Utilities.space() + "Task does not exist";
            }
        } else {
            return "Plead specify a task to mark";
        }
    }

    public String unmarkTask(String instruction) {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return Utilities.space() + "No problem, I have unmarked this task: " + "\n" +
                        Utilities.space() + this.tasks.unmarkTaskAtIndex(taskIndex);
            } catch (NumberFormatException e) {
                return "Please specify an integer";
            } catch (IndexOutOfBoundsException e) {
                return Utilities.space() + "Task does not exist";
            }
        } else {
            return "Plead specify a task to mark";
        }
    }

    public static void main(String[] args) {
        Sebastian sebastian = new Sebastian();
        Utilities.printFormattedString(sebastian.greet());

        // Read user input
        Scanner scan = new Scanner(System.in);
        String instruction = scan.nextLine();
        String action = instruction.split(" ")[0];

        // respond to different inputs
        String res;
        while(!action.equals("bye")) {
            switch (action){
                case "list":
                    res = sebastian.showList(instruction);
                    break;
                case "mark":
                    res = sebastian.markTask(instruction);
                    break;
                case "unmark":
                    res = sebastian.unmarkTask(instruction);
                    break;
                case "todo":
                    res = sebastian.addTodo(instruction);
                    break;
                case "deadline":
                    res = sebastian.addDeadline(instruction);
                    break;
                case "event":
                    res = sebastian.addEvent(instruction);
                    break;
                default:
                    res = "Plead specify your instruction";
            }
            Utilities.printFormattedString(res);

            // read in the next input
            instruction = scan.nextLine();
            action = instruction.split(" ")[0];
        }

        Utilities.printFormattedString(sebastian.exit());
    }
}
