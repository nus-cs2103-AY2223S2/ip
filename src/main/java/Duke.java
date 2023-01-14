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
                String[] splited = command.split(" ");
                // Here later should catch another issue of empty command
                if (splited[0].equals("mark")) {
                    int index = Integer.parseInt(splited[1]);
                    mainEngine.markDone(index);
                } else if (splited[0].equals("unmark")) {
                    int index = Integer.parseInt(splited[1]);
                    mainEngine.markUnDone(index);
                } else if (splited[0].equals("todo")) {
                    mainEngine.handleToDo(command);
                } else if (splited[0].equals("deadline")) {
                    mainEngine.handleDeadLine(command);
                } else if (splited[0].equals("event")) {
                    mainEngine.handleEvent(command);
                } else { // otherwise = commonplace task
                    mainEngine.addTask(command);
                }
            }
            // mainEngine.echo(command);
            mainEngine.printLine();
            command = sc.nextLine();
        }
        mainEngine.goodbye();
    }

}
