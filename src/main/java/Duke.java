import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeEngine mainEngine = new DukeEngine();
        mainEngine.greet();
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            // System.out.println(command);
            mainEngine.echo(command);
            command = sc.nextLine();
        }
        mainEngine.goodbye();
    }

}
