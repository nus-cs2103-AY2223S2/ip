import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showLine() {
        System.out.println("    ______________________________________________________________");

    }

    public String readCommand() {
        String command = this.sc.nextLine();
        return command;
    }

    public void showError(DukeException error) {
        System.out.println(error);
    }

    public void showLoadingError() {
        System.out.println("    The file does not exist.");
    }

    public void showGuide() {
        System.out.println("    list");
        System.out.println("    todo (content)");
        System.out.println("    deadline (content) /by (dd/MM/yyyy HH:mm)");
        System.out.println("    event (content) /from (dd/MM/yyyy HH:mm) /to (dd/MM/yyyy HH:mm)");
        System.out.println("    mark (index)");
        System.out.println("    unmark (index)");
        System.out.println("    delete (index)");
        System.out.println("    bye");
    }

    public void end() {
        System.out.println("    Bye. Hope to see you again soon!");
        this.sc.close();
    }
}
