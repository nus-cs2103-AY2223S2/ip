import java.util.Scanner;

public class Duke {
    private static Scanner scanner;
    private static DukeList taskList;
    public static void initialiseIO() {
        scanner = new Scanner(System.in);
        System.out.println(DukeIO.LOGO);
        System.out.println(DukeIO.wrapContent(DukeIO.GREET));
        taskList = new DukeList();
    }
    public static void waitForInput() {
        while(true) {
            String input = scanner.nextLine();
            if (input.equals(DukeIO.terminateString)) {
                System.out.println(DukeIO.wrapContent(DukeIO.FAREWELL));
                return;
            }
            if (input.equals(DukeIO.listString)) {
                System.out.println(DukeIO.wrapContent(taskList.toString()));
                continue;
            }
            taskList.addTask(input);
        }
    }
    public static void main(String[] args) {
        initialiseIO();
        waitForInput();
    }
}
