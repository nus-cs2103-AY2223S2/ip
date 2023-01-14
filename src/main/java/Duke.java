import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeEngine mainEngine = new DukeEngine();
        mainEngine.greet();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            mainEngine.printLine();
            if (command.equals("list")) {
                mainEngine.listTask();
            } else {
                mainEngine.addTask(command);
            }
            // mainEngine.echo(command);
            mainEngine.printLine();
            command = sc.nextLine();
        }
        mainEngine.goodbye();
    }

}
