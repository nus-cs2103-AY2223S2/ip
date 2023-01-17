import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Duke {
    private static final String LINE = "__________________________________________________________\n";
    private boolean hasEnded = false;
    private final ArrayList<Task> taskList = new ArrayList<>(100);
    private final String path;
    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public Duke(String path) {
        this.path = path;
        try {
            loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("Task file does not exist");
        }
    }

    public void loadTasks() throws FileNotFoundException {
        File toRead = new File(this.path);
        Scanner sc = new Scanner(toRead);

        while (sc.hasNext()) {
            String[] task = sc.nextLine().split(" \\| ");

            switch(task[0]) {
                case "T": {
                    Todos toAdd = new Todos(task[2]);
                    if (task[1].equals("1")) {
                        toAdd.mark();
                    }
                    this.taskList.add(toAdd);
                }
                break;

                case "D": {
                    Deadlines toAdd = new Deadlines(task[2], task[3]);
                    if (task[1].equals("1")) {
                        toAdd.mark();
                    }
                    this.taskList.add(toAdd);
                }
                break;

                case "E": {
                    Events toAdd = new Events(task[2], task[3], task[4]);
                    if (task[1].equals("1")) {
                        toAdd.mark();
                    }
                    this.taskList.add(toAdd);
                }
                break;
            }
        }
        sc.close();
    }

    public void writeTasks() {
        try {
            new File(this.path).getParentFile().mkdirs();
            FileWriter writer = new FileWriter(this.path);
            for (Task toSave : this.taskList) {
                writer.write(toSave.toWrite());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file to write into");
        } catch (IOException e) {
            System.out.println("Unable to save task. Try again");
        }
    }

    public void greet() {
        System.out.println(Duke.LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + Duke.LINE);
    }

    public void exit() {
        System.out.println(Duke.LINE
                + "Bye. Hope to see you again soon!\n"
                + Duke.LINE);
    }

    public void list() {
        System.out.println(Duke.LINE + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
        System.out.println(Duke.LINE);
    }

    public void mark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = taskList.get(position);
        toChange.mark();
        System.out.println(Duke.LINE + "Nice! I've marked this task as done:\n"
                + toChange + "\n" + Duke.LINE);
    }

    public void unmark(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toChange = taskList.get(position);
        toChange.unmark();
        System.out.println(Duke.LINE + "Okay, I've marked this task as not done yet:\n"
                + toChange + "\n" + Duke.LINE);
    }

    public void todo(String input) {
        Task toAdd = new Todos(input);
        taskList.add(toAdd);
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void deadline(String input) {
        String[] split = input.split(" /by ");
        Task toAdd = new Deadlines(split[0], split[1]);
        taskList.add(toAdd);
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void event(String input) {
        String[] split = input.split(" /from | /to " );
        Task toAdd = new Events(split[0], split[1], split[2]);
        taskList.add(toAdd);
        System.out.println(Duke.LINE + "Got it. I've added this task:\n" + toAdd);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void delete(String input) {
        int position = Integer.parseInt(input) - 1;
        Task toRemove = taskList.remove(position);
        System.out.println(Duke.LINE + "Noted. I've removed this task:\n" + toRemove);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(Duke.LINE);
    }

    public void start(Scanner sc) {
        this.greet();

        while(sc.hasNext()) {
            try {
                String[] input = sc.nextLine().split(" ", 2);
                Command c = Command.valueOf(input[0].toUpperCase());

                switch (c) {
                    case BYE:
                        this.exit();
                        this.writeTasks();
                        this.hasEnded = true;
                        break;

                    case LIST:
                        this.list();
                        break;

                    case MARK:
                        if (input.length < 2) {
                            throw new NumberMissingException();
                        }
                        this.mark(input[1]);
                        break;

                    case UNMARK:
                        if (input.length < 2) {
                            throw new NumberMissingException();
                        }
                        this.unmark(input[1]);
                        break;

                    case TODO:
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.todo(input[1]);
                        break;

                    case DEADLINE:
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.deadline(input[1]);
                        break;

                    case EVENT:
                        if (input.length < 2) {
                            throw new EmptyDescriptionException();
                        }
                        this.event(input[1]);
                        break;

                    case DELETE:
                        if (input.length < 2) {
                            throw new NumberMissingException();
                        }
                        this.delete(input[1]);
                        break;

                }
            }
            catch (IllegalArgumentException e) {
                System.out.println(Duke.LINE + "Command not recognised. Please input a valid command");
                System.out.println(Duke.LINE);
            }
            catch (Exception e) {
                System.out.println(Duke.LINE + e.getMessage());
                System.out.println(Duke.LINE);
            } if (this.hasEnded) {
                break;
            }

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = System.getProperty("user.home") + "/data/duke.txt";
        Duke duke = new Duke(path);
        duke.start(sc);
    }
}
