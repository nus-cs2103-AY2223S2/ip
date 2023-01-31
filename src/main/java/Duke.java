import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static void writeToFile(String filePath, String text) throws IOException {
        File f = new File(filePath);
        f.createNewFile();
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(text);
        fw.close();
    }

    private static Task textToTask(String data) throws DukeException {
        Boolean taskIsDone = false;
        String task = "";
        String description = "";
        if (data.contains("[T]")) {
            String statusAndTask = data.replace("[T]", "");
            if (statusAndTask.contains("[X]")) {
                taskIsDone = true;
            }
            if (taskIsDone) {
                description = statusAndTask.replace("[X]", "");
            } else {
                description = statusAndTask.replace("[ ]", "");
            }
            Task todo = new Todo(description);
            if (taskIsDone) {
                todo.markAsDone();
            }
            return todo;

        } else if (data.contains("[D]")) {
            String statusAndTask = data.replace("[D]", "");
            if (statusAndTask.contains("[X]")) {
                taskIsDone = true;
            }
            if (taskIsDone) {
                task = statusAndTask.replace("[X]", "");
            } else {
                task = statusAndTask.replace("[ ]", "");
            }

            String[] arr = task.split(" \\(by: ");
            description = arr[0];
            String time = arr[1].replace(")", "");

            Task deadline = new Deadline(description, time);
            if (taskIsDone) {
                deadline.markAsDone();
            }
            return deadline;

        } else if (data.contains("[E]")) {
            String statusAndTask = data.replace("[E]", "");
            if (statusAndTask.contains("[X]")) {
                taskIsDone = true;
            }
            if (taskIsDone) {
                task = statusAndTask.replace("[X]", "");
            } else {
                task = statusAndTask.replace("[ ]", "");
            }
            String[] arr = task.split(" \\(from: ", 2);
            description = arr[0];
            String[] duration = arr[1].split(" to:", 2);
            String from = duration[0];
            String to = duration[1].replace(")", "");

            Task event = new Event(description, from, to);
            if (taskIsDone) {
                event.markAsDone();
            }
            return event;
        } else {
            throw new DukeException("File cannot be loaded");
        }
    }

    public static void main(String[] args) {

        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Somebody\n" + "What can I do for you?");

        try {
            File f = new File("data/duke.txt");
            Scanner sn = new Scanner(f);
            while (sn.hasNext()) {
                String text = sn.nextLine();
                Task task = textToTask(text);
                list.add(task);
                System.out.println(text);
            }
            sn.close();
        } catch (FileNotFoundException e) {
            System.out.println("There are no tasks as of now!");
        } catch (DukeException e) {
            System.out.println(e.toString());
        }

        Scanner scn = new Scanner(System.in);
        while (true) {

            String input = scn.nextLine();

            if (input.startsWith("mark")) {

                String index = input.split(" ", 2)[1];
                Task task = list.get(Integer.parseInt(index) - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + task.toString());

            } else if (input.startsWith("unmark")) {

                String index = input.split(" ", 2)[1];
                Task task = list.get(Integer.parseInt(index) - 1);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());

            } else if (Objects.equals(input.toLowerCase(), "bye")) {

                int len = list.size();
                String text = "";
                for (int i = 0; i < len; i++) {
                    text += list.get(i).toString();
                    text += "\n";
                }

                try {
                    writeToFile("data/duke.txt", text);
                } catch (IOException e) {
                    System.out.println("Error saving file: " + e.getMessage());
                }

                System.out.println("Bye. Hope to see you again soon!");
                scn.close();
                return;

            } else if (Objects.equals(input.toLowerCase(), "list")) {

                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    Task task = list.get(i - 1);
                    System.out.println(i + "." + task.toString());
                }

            } else if (input.startsWith("delete")) {
                String index = input.split(" ", 2)[1];
                Task task = list.get(Integer.parseInt(index) - 1);
                list.remove(Integer.parseInt(index) - 1);
                System.out.println("Noted. I've removed this task:\n" + task.toString() + "\nNow you have "
                        + list.size() + " tasks in the list.");
            } else {
                try {
                    if (input.contains("todo")) {
                        String description = input.replace("todo", "");
                        Todo todo = new Todo(description);
                        list.add(todo);
                        System.out.println("Got it. I've added this task:\n" + todo.toString() + "\nNow you have "
                                + list.size() + " tasks in the list.");
                    } else if (input.contains("deadline")) {
                        String command = input.replace("deadline", "");
                        String description = command.split("/by")[0];
                        if (description.equals("") || description.equals(" ")) {
                            throw new MissingDescriptionException();
                        } else {
                            String date = command.split("/by")[1];
                            Deadline deadline = new Deadline(description, date);
                            list.add(deadline);
                            System.out
                                    .println("Got it. I've added this task:\n" + deadline.toString() + "\nNow you have "
                                            + list.size() + " tasks in the list.");
                        }
                    } else if (input.contains("event")) {
                        String command = input.replace("event", "");
                        String description = command.split("/from")[0];
                        if (description.equals("") || description.equals(" ")) {
                            throw new MissingDescriptionException();
                        } else {
                            String remainder = command.split("/from")[1];
                            String from = remainder.split("/to")[0];
                            String to = remainder.split("/to")[1];

                            Event event = new Event(description, from, to);
                            list.add(event);
                            System.out.println("Got it. I've added this task:\n" + event.toString() + "\nNow you have "
                                    + list.size() + " tasks in the list.");
                        }
                    } else {
                        throw new UnknownCommandException();
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            }

        }
    }
}
