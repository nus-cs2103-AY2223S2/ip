/**
 * The Ui class implements the user interactions of the user.
 *
 * @author Chia Jeremy
 */

public class Ui {

    private static final String INDENTATION = "    ";
    private static final String LINE = INDENTATION + "____________________________________________________________";

    public void display(String message) {
        System.out.println(LINE);
        String[] lines = message.split("\n");
        for (String s : lines) {
            System.out.println(INDENTATION + s);
        }
        System.out.println(LINE + "\n");
    }

    public void greet(String name) {
        display("Hello I'm\n" + name + "What can I do for you?\n\n" + showCommands());
    }

    public String showCommands() {
        return "MY COMMANDS ARE:\n"
                + "ADD TODO TASK:     todo [description]\n"
                + "ADD DEADLINE TASK: deadline [description] /by [YYYY-MM-DD HH:MM]\n"
                + "ADD EVENT TASK:    event [description] /from [YYYY-MM-DD HH:MM] /to [YYYY-MM-DD HH:MM]\n"
                + "MARK A TASK:       mark [index]\n"
                + "UNMARK A TASK:     unmark [index]\n"
                + "DELETE A TASK:     delete [index]\n"
                + "LIST ALL TASKS:    list\n"
                + "DISPLAY COMMANDS:  help\n"
                + "EXIT PROGRAM:      bye";
    }
}
