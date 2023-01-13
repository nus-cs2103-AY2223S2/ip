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

    private String addTask(String instruction) {
        if(this.tasks.addTask(instruction)) {
            return Utilities.space() + "added: " + instruction;
        } else {
            return Utilities.space() + "Failed to add a task";
        }
    }

    public String showList(String instruction) {
        String[] insArr = instruction.split(" ");
        if(insArr.length > 1) {
            return this.addTask(instruction);
        } else {
            return Utilities.space() + "Here are the tasks in your list" + "\n" + this.tasks;
        }
    }

    public String markTask(String instruction) {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            return Utilities.space() + "Plead specify a task to mark";
        } else if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return  Utilities.space()+  "Well Done. I have marked this task as done: " + "\n" +
                        Utilities.space() + this.tasks.markTaskAtIndex(taskIndex);
            } catch (NumberFormatException e) {
                return addTask(instruction);
            } catch (IndexOutOfBoundsException e) {
                return Utilities.space() + "Task does not exist";
            }
        } else {
            return this.addTask(instruction);
        }
    }

    public String unmarkTask(String instruction) {
        String[] insArr = instruction.split(" ");
        if(insArr.length == 1) {
            return Utilities.space() + "Plead specify a task to unmark";
        } else if(insArr.length == 2) {
            try {
                int taskIndex = Integer.parseInt(insArr[1]);
                return Utilities.space() + "No problem, I have unmarked this task: " + "\n" +
                        Utilities.space() + this.tasks.unmarkTaskAtIndex(taskIndex);
            } catch (NumberFormatException e) {
                return addTask(instruction);
            } catch (IndexOutOfBoundsException e) {
                return Utilities.space() + "Task does not exist";
            }
        } else {
            return this.addTask(instruction);
        }
    }

    public static void main(String[] args) {
        Sebastian sebastian = new Sebastian();
        // Start a session with greeting the user
        Utilities.printFormattedString(sebastian.greet());
        // Read user input
        Scanner scan = new Scanner(System.in);
        String instruction = scan.nextLine();
        String action = instruction.split(" ")[0];

        // respond to different inputs
        String res = "";
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
                default:
                    res = sebastian.addTask(instruction);
            }
            Utilities.printFormattedString(res);

            // read in the next input
            instruction = scan.nextLine();
            action = instruction.split(" ")[0];
        }

        Utilities.printFormattedString(sebastian.exit());
    }
}
