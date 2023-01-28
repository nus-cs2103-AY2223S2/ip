package duke;

import java.util.Scanner;

public class UI {
    /**
     * Represents the actions that the user can input
     */
    String inp;
    String[] inpLine;
    Functions fn;

    /**
     * Constructor for an instance of a UI object
     *
     * @param fn Functions object
     */
    public UI(Functions fn) {
        this.fn = fn;
    }

    /**
     * Method to get user input from console
     */
    public void getInput() {
        Scanner sc = new Scanner(System.in);
        this.inp = sc.nextLine();
        this.inpLine = inp.split(" ");
    }

    /**
     * Method to determine what action to do from user input
     *
     * @return boolean flag to indicate program is closing
     * @throws DukeException Catch general DukeExceptions. For testing
     */
    public boolean action() throws DukeException {
        boolean flag = true;
        switch (inpLine[0]) {
        case "bye":
            flag = fn.bye();
            break;
        case "list":
            fn.list();
            break;
        case "find":
            fn.find(inp);
            break;
        case "mark":
            fn.mark(inp);
            break;
        case "unmark":
            fn.unmark(inp);
            break;
        case "delete":
            fn.delete(inp);
            break;
        case "todo":
            fn.todo(inp);
            break;
        case "deadline":
            fn.deadline(inp);
            break;
        case "event":
            fn.events(inp);
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return flag;
    }
}
