import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private enum Commands {
         MARK, UNMARK, TODO, DEADLINE, EVENT, LIST, DELETE, BYE
    }
    private static Enum getCommand(String input){
        return Commands.valueOf(input.split(" ")[0].toUpperCase());
    }
    private static String[] getParameters(String input){
        return input.split(" ", 2)[1].split(" ");
    }
    public static void main(String[] args) {
        Enum command;
        String[] parameters;
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String input = myObj.nextLine();
            command = getCommand(input);

            if (command.equals(Commands.BYE)) { // no parameters

            } else if (command.equals(Commands.LIST)) {

            } else { //has parameters
                parameters = getParameters(input);
                if (command.equals(Commands.TODO)) {

                } else if (command.equals(Commands.DEADLINE)) {

                } else if (command.equals(Commands.EVENT)) {

                } else if (command.equals(Commands.DELETE)) {

                } else if (command.equals(Commands.UNMARK)) {

                } else if (command.equals(Commands.MARK)) {

                }
            }
        }
    }
}
