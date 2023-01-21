package duke;
import java.util.Scanner;

public class UI {
    String inp;
    String[] inpLine;
    public UI(Functions fn) {
        this.fn = fn;
    }
    Functions fn;

    public void getInput() {
        Scanner sc = new Scanner(System.in);
        this.inp = sc.nextLine();
        this.inpLine = inp.split(" ");
    }

    public boolean action() throws DukeException{
        boolean flag = true;
        switch (inpLine[0]) {
            case "bye":
                flag = fn.bye();
                break;
            case "list":
                fn.list(inp);
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
