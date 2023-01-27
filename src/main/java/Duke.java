import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static void saveTasks(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : tasks) {
            textToAdd.append(task).append("\n");
        }
        fw.write(String.valueOf(textToAdd));
        fw.close();
    }

    private static ArrayList<Task> retrieveTasks(String filePath) throws IOException, DukeException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] inputs = line.split("]", 3);
            String taskType = inputs[0];
            String status = inputs[1];
            boolean isDone = status.equals("[X");
            String rest = inputs[2];

            if (taskType.equals("[T")) {
                // then it is a Todo
                Task t = new Todo(rest.trim());
                if (isDone) {
                    t.mark();
                }
                tasks.add(t);
            } else if (taskType.equals("[D")) {
                // then it is a Deadline
                String[] restStrings = rest.split("by:", 2);
                String description = restStrings[0].replaceAll("\\(", "").trim();
                String by = restStrings[1].replaceAll("\\)", "").trim();
                Task t = new Deadline(description, by);
                if (isDone) {
                    t.mark();
                }
                tasks.add(t);
            } else {
                // then it is an Event
                String[] restStrings = rest.split("from:", 2);
                String description = restStrings[0].replaceAll("\\(", "").trim();
                String[] duration = restStrings[1].split("to:", 2);
                String from = duration[0].trim();
                String to = duration[1].replaceAll("\\)", "").trim();
                Task t = new Event(description, from, to);
                if (isDone) {
                    t.mark();
                }
                tasks.add(t);
            }
        }
        return tasks;
    }

    private static Task parse(String[] inputs) throws DukeException {
        String taskType = inputs[0];
        Task task;
        if (inputs.length <= 1) {
            throw new DukeException("Oops!! The description of a " + taskType + " cannot be empty!");
        } else {
            String rest = inputs[1];

            if (taskType.equals("todo")) {
                task = new Todo(rest);
            } else if (taskType.equals("deadline")) {
                String[] addon = rest.split(" /", 2);
                String description = addon[0];
                if (addon.length <= 1) {
                    throw new DukeException("Oops!! Please indicate when the deadline is by!");
                } else {
                    String byString = addon[1];
                    String[] byPart = byString.split(" ", 2);
                    String by = byPart[1];
                    task = new Deadline(description, by);
                }
            } else {
                // event
                String[] addon = rest.split(" /", 3);
                String description = addon[0];
                if (addon.length <= 1) {
                    throw new DukeException("Oops!! Please indicate when the event will be at using /from and /to!");
                } else {
                    String fromString = addon[1];
                    String[] fromPart = fromString.split(" ", 2);
                    String from = fromPart[1];
                    String toString = addon[2];
                    String[] toPart = toString.split(" ", 2);
                    String to = toPart[1];
                    task = new Event(description, from, to);
                }
            }
        }
        return task;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Chiwa, your personal chatbot (◔◡◔✿)");
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(System.in);
        String path = "data/duke.txt";
        try {
            ArrayList<Task> tasks = retrieveTasks(path);

            label:
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] inputs = input.split(" ", 2);
                String command = inputs[0];

            try {
                switch (command) {
                case "bye":
                    System.out.println("Bye~ Hope to see you again soon!");
                    break label;
                case "list":
                    StringBuilder reply = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        reply.append(String.format("%d. " + tasks.get(i) + "\n", i + 1));
                    }
                    System.out.print(reply);
                    break;
                case "mark":
                case "unmark":
                case "delete":
                    if (inputs.length <= 1) {
                        throw new DukeException("Please input the numbering of the task you want to" + command + "as well!");
                    } else {
                        String number = inputs[1]; // might have Number Format Exception here
                        int num = Integer.parseInt(number);
                        if (num > tasks.size()) {
                            String errorMessage = String.format("Task %d does not exist!", num);
                            throw new DukeException(errorMessage);
                        } else {
                            Task t = tasks.get(num - 1);
                            if (command.equals("mark")) {
                                t.mark();
                                System.out.println("Congratulations for completing the task ^^ I've marked it as done:");
                                System.out.println(t);
                            } else if (command.equals("unmark")) {
                                t.unmark();
                                System.out.println("Ok, I've unmarked the task for you:");
                                System.out.println(t);
                            } else {
                                // deleting a task
                                System.out.println("Ok, I've deleted the following task for you:");
                                System.out.println(t);
                                tasks.remove(num - 1);
                                System.out.printf("You now have %d task(s) in your list!\n", tasks.size());
                            }
                        }
                        saveTasks(path, tasks);
                    }
                    break;
                case "todo":
                case "deadline":
                case "event": // adding new task
                    // Have 3 types of tasks: todo, deadline and event
                    Task t = Duke.parse(inputs);
                    tasks.add(t);
                    System.out.println("Ok, I've added this task:");
                    System.out.println(t);
                    System.out.printf("You now have %d task(s) in your list!\n", tasks.size());
                    saveTasks(path, tasks);
                    break;
                default:
                    throw new DukeException("Sorry I don't understand this command! :(");
                }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
