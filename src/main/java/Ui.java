import java.util.Scanner;

public class Ui {

    private static final String LINE = "__________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";

        String introMsg = "Hello! I'm Duke.\n" + "What can I do for you today?";

        this.showLine();
        this.showMsg(logo);
        this.showMsg(introMsg);
        this.showLine();
    }

    public void showLine() {
        this.showMsg(Ui.LINE);
    }

    public void showMsg(String msg) {
        System.out.println(msg);
    }

    public void showError(String errorMsg) {
        this.showMsg(errorMsg);
        this.showMsg("Please try again!");
    }

    public void showAddTask(String taskString, int size) {
        String msgHeader = "I've added this task into the list:";
        String msgFooter = String.format("Now you have a total of %s tasks in the list", size);

        this.showMsg(msgHeader);
        this.showMsg(taskString);
        this.showMsg(msgFooter);
    }

    public void showDeleteTask(String taskString, int size) {
        String msgHeader = "I've deleted this task into the list:";
        String msgFooter = String.format("Now you have a total of %s tasks in the list", size);

        this.showMsg(msgHeader);
        this.showMsg(taskString);
        this.showMsg(msgFooter);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        this.scanner.close();
    }
}
