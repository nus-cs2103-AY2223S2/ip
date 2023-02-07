import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    private Scanner input = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();

    public void start() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?\n");
    }

    public void display() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public void mark(String command) throws DukeException{
        if (command.trim().equals("mark")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a mark cannot be empty.\n");
        }
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            if (index + 1 > tasks.size() || index < 0) {
                throw new DukeException("\t ☹ OOPS!!! Please input a valid number.\n");
            }
            tasks.get(index).mark();
            editData(index, "1");
        } catch (NumberFormatException | IOException ex) {
            System.out.println("\t ☹ OOPS!!! Please input a valid number.\n");
        }
    }

    public void unmark(String command) throws DukeException{
        if (command.trim().equals("mark")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a unmark cannot be empty.\n");
        }
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            if (index + 1 > tasks.size() || index < 0) {
                throw new DukeException("\t ☹ OOPS!!! Please input a valid number.\n");
            } else if (tasks.get(index).getStatusIcon().equals(" ")) {
                throw new DukeException("\t ☹ OOPS!!! This task has not been marked yet.\n");
            }
            tasks.get(index).unmark();
            editData(index, "0");
        } catch (NumberFormatException | IOException ex) {
            System.out.println("\t ☹ OOPS!!! Please input a valid number.\n");
        }
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("\t " + (i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        System.out.print("\n");
    }

    public void addTask(Task task) {
        tasks.add(task);
        save(task);
        System.out.println("\t Got it. I've added this task:\n"
                + "\t\t "+ tasks.get(tasks.size() - 1).toString()
                + "\n\t Now you have " + tasks.size() + " tasks in the list.\n");
    }
    public void todo(String command) throws DukeException {
        if (command.trim().equals("todo")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        String description = command.split(" ", 2)[1];
        Todo todo = new Todo(description);
        addTask(todo);
    }

    public void deadline(String command) throws DukeException {
        if (command.trim().equals("deadline")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a deadline cannot be empty.\n");
        } else if (command.trim().contains(" /by ") == false) {
            throw new DukeException("\t ☹ OOPS!!! There is no deadline date or the wrong format\n");
        }

        command = command.split(" ", 2)[1];
        if (command.startsWith("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        } else if (command.endsWith("/by")) {
            throw new DukeException(("☹ OOPS!!! The due date of a deadline cannot be empty.\n"));
        }

        String info[] = command.split(" /by ");
        String description = info[0];
        String by = info[1];
        Deadline deadline = new Deadline(description, by);
        addTask(deadline);
    }

    public void event(String command) throws DukeException{
        command = command.trim();
        if (command.equals("event")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a event cannot be empty.\n");
        } else if (command.contains(" /from ") == false || command.contains(" /to ") == false) {
            throw new DukeException("\t ☹ OOPS!!! The from date or due date of a deadline cannot be empty.\n"
                    + "\t Or the format is wrong.\n");
        }

        command = command.split(" ", 2)[1];
        if (command.startsWith("/from") || command.endsWith("/to") || command.contains("/from /to")) {
            throw new DukeException("\t ☹ OOPS!!! The description, from date or due date of a event cannot be empty.\n");
        }

        String info[] = command.split(" /from ");
        String description = info[0];
        String dates[] = info[1].split(" /to ");
        String from = dates[0];
        String to = dates[1];

        Event event = new Event(description, from, to);
        addTask(event);
    }

    public void delete(String command) throws DukeException, IOException {
        command = command.trim();
        if (command.equals("delete")) {
            throw new DukeException("\t ☹ OOPS!!! The description of a delete cannot be empty.\n");
        }
        int index;
        try {
            index = Integer.parseInt(command.split(" ")[1]);
        } catch (NumberFormatException ex) {
            throw new DukeException("\t ☹ OOPS!!! Please input a valid number.\n");
        }
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("\t ☹ OOPS!!! The input is out of range.\n");
        }
        System.out.println("\t Noted. I've removed this task:\n"
                + "\t\t " + tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.\n");
        deleteData(index);
    }

    public void editData(int taskIndex, String data) throws IOException {
        Path file = Paths.get(".", ".\\data\\duke.txt");
        List<String> records = Files.readAllLines(file);
        String[] str = records.get(taskIndex).split(" \\| ");
        str[1] = data;
        String record = String.join(" | ", str);
        records.set(taskIndex, record);
        Files.write(file, records);
    }

    public void deleteData(int taskIndex) throws IOException {
        Path file = Paths.get(".", ".\\data\\duke.txt");

        try {
            List<String> datas = Files.readAllLines(file);
            datas.remove(taskIndex - 1);
            Files.write(file, datas);
        } catch (IOException e) {
            System.out.println("\t Invalid Path.");
        }
    }

    public void save(Task task) {
        Path file = Paths.get(".", ".\\data\\duke.txt");
        String data = "";
        if (task instanceof Todo) {
            data = ("T | ");
        } else if (task instanceof Deadline) {
            data = "D | ";
        } else {
            data = "E | ";
        }
        if (task.getStatusIcon().equals("X")) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        data += task.getDescription();
        if (task instanceof Deadline) {
            data += " | " + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            data += " | " + ((Event) task).getFrom() + "-" + ((Event) task).getTo();
        }
        data += "\n";

        try {
            Files.writeString(file, data, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("\t Invalid Path");
        }
    }

    public void loadData() {
        Path file = Paths.get(".", ".\\data\\duke.txt");
        try {
            List<String> datas = Files.readAllLines(file);
            for (int i = 0; i < datas.size(); i++) {
                String[] content = datas.get(i).split(" \\| ");
                String isDone = content[1];
                String description = content[2];
                if (content[0].equals("T")) {
                    Todo task = new Todo(description);
                    tasks.add(task);
                } else if (content[0].equals("D")) {
                    String endDate = content[3];
                    Deadline task = new Deadline(description, endDate);
                    tasks.add(task);
                } else {
                    String times = content[3];
                    String date[] = times.split("-", 2);
                    String startTime = date[0];
                    String endTime = date[1];
                    Event task = new Event(description, startTime, endTime);
                    tasks.add(task);
                }
                if (isDone.equals("1")) {
                    tasks.get(i).setDone(true);
                }
            }
        } catch (IOException e) {
            System.out.println("\t Invalid Path.");
        }
    }

    public void checkFileExit() {
        try {
            Path file = Paths.get(".", ".\\data\\duke.txt");

            if (Files.exists(file)) {
                loadData();
            } else {
                Files.createDirectories(file.getParent());
                Files.createFile(file);
            }
        } catch (IOException e) {
            System.out.println("Invalid Path");
            e.printStackTrace();
        }
    }

    public void run() {
        start();
        display();
        checkFileExit();
        while (true) {
            String command = input.nextLine();
            try {
                if (command.equals("bye")) {
                    display();
                    System.out.println("\t Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    display();
                    list();
                } else if (command.startsWith("mark")) {
                    display();
                    mark(command);
                } else if (command.startsWith("unmark")) {
                    display();
                    unmark(command);
                } else if (command.startsWith("todo")) {
                    display();
                    todo(command);
                } else if (command.startsWith("deadline")) {
                    display();
                    deadline(command);
                } else if (command.startsWith("event")) {
                    display();
                    event(command);
                } else if (command.startsWith("delete")) {
                    display();
                    delete(command);
                } else {
                    display();
                    System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means.\n");
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

