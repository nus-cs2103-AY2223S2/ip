import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.List;
public class Duke {
    private Ui ui;

    private Storage storage;
    private TaskList list;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList temp;
        try {
            temp = this.storage.loadFile();
        } catch (DukeException e) {
            this.ui.display(e.getMessage());
            temp = new TaskList();
        }
        this.list = temp;

    }
    public static void main(String[] args) {
        String txtDir = System.getProperty("user.dir") + "/data/tasks.txt";

        Duke instance = new Duke(txtDir);
        instance.run();
    }
    public void run() {

        this.ui.showLogo();
        this.ui.showWelcome();
        Parser parser = new Parser();

        /*
        String[] readLine = parser.readLine();
        ArrayList<String> kw = new ArrayList<>();
        kw.add("from");
        kw.add("to");

        try {
            ArrayList<String> queries = parser.queries(readLine, kw);
            System.out.println(queries);
        } catch (DukeException dk) {
            System.out.println(dk.getMessage());
        }
        */

        String command = "";
        Scanner sc = new Scanner(System.in);


        while (!command.equals("bye")) {
            try {
                String[] line = parser.readLine();
                command = parser.readCommand(line);
                if (command.equals("list")) {
                    String items = list.getTaskStrings();
                    ui.display(items);

                } else if (command.equals("mark")) {
                    int num = parser.singleQueryInteger(line);
                    list.markTask(num);
                    ui.display(String.format(
                            "Nice! I've marked this task as done:\n %s", list.get(num)));
                } else {
                    Task taskNew;
                    if (command.equals("todo")) {
                        if (line.length == 1) {
                            throw new NoArgsException("todo");
                        } else {
                            String description = parser.queries(line, List.<String>of()).get(0);
                            System.out.println(description);
                            addTask(new Todos(description), list, ui);
                        }
                    } else if (command.equals("deadline")) {
                        if (line.length == 1) {
                            throw new NoArgsException("deadline");
                        } else {
                            addTask(
                                    new Deadlines(parser.queries(line, List.<String>of("by"))), list, ui);

                        }
                    } else if (command.equals("event")) {
                        if (line.length == 1) {
                            throw new NoArgsException("event");
                        } else {
                            addTask(
                                    new Events(
                                            parser.queries(line, List.<String>of("from", "to"))), list, ui);

                        }
                    } else if (command.equals("delete")) {
                        if (line.length == 1) {
                            throw new NoArgsException("delete command");
                        } else if (line.length > 1 && !line[1].matches("\\d")) {
                            throw new DukeException("☹☹☹☹☹☹ OOPS!!! Provide a number!");
                        } else if (list.size() == 0) {
                            throw new StorerEmptyException();
                        } else {
                            int index = Integer.valueOf(line[1]);
                            Task E = list.remove(index);
                            String speech = "Noted. I've removed this task:\n" +
                                    E + "\n Now you have " + list.size() + " tasks in the list.";
                            ui.display(speech);
                        }
                    } else if (command.equals("bye")) {
                        break;
                    } else {
                        throw new EmptyException();
                    }

                    this.storage.dumpFile(list);
                }
            } catch (Exception err) {
                ui.display(err.getMessage());
            }

        }
        ui.display("Bye. Hope to see you again soon!");
    }

    static void addTask(Task taskNew, TaskList list, Ui ui) {
        list.add(taskNew);
        ui.display("Got it. I've added this task:\n" + taskNew +
                String.format("\nNow you have %s tasks in the list.", list.size()));

    }




}

