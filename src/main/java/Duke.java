import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private enum Commands {
         BYE, LIST, MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE
    }
    private static Enum getCommand(String input){ //get the command
        return Commands.valueOf(input.split(" ")[0].toUpperCase());
    }
    private static String[] getParameters(String input){ //get all tokens after the command
        return input.split(" ", 2)[1].split(" ");
    }
    private static String[] parseParameters(Enum command, String[] parameters){ //get only the useful tokens
        if(command.equals(Commands.MARK) || command.equals(Commands.UNMARK) || command.equals(Commands.DELETE)){

        }
        else if(command.equals(Commands.TODO)){

        }
        else if(command.equals(Commands.EVENT)){

        }
        else if(command.equals(Commands.DEADLINE)){

        }
    }
    public static void main(String[] args) {
        Enum command;
        String[] parameters;
        TaskList taskList = new TaskList();
        Scanner myObj = new Scanner(System.in);

        while (true) {
            String input = myObj.nextLine();
            command = getCommand(input);
            if (command.equals(Commands.BYE)) { // no parameters
                System.out.println("bai");
            } else if (command.equals(Commands.LIST)) {
                System.out.println(taskList.listTasks());
            } else { //has parameters
                parameters = parseParameters(command,getParameters(input));
                if (command.equals(Commands.MARK)) {
                    System.out.println(taskList.markTask(Integer.parseInt(parameters[0])));
                } else if (command.equals(Commands.UNMARK)) {
                    System.out.println(taskList.unmarkTask(Integer.parseInt(parameters[0])));
                } else if (command.equals(Commands.TODO)) {
                    System.out.println(taskList.addTask(parameters[0]));
                } else if (command.equals(Commands.DELETE)) {
                    System.out.println(taskList.deleteTask(Integer.parseInt(parameters[0])));
                } else if (command.equals(Commands.EVENT)) {
                    System.out.println(taskList.addTask(parameters[0], parameters[1]));
                } else if (command.equals(Commands.DEADLINE)) {
                    System.out.println(taskList.addTask(parameters[0], parameters[1], parameters[2]));
                }
            }
        }
    }
}
