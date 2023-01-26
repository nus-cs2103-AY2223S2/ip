
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.ArrayList;

public class James {
    ArrayList<Task> inputs = new ArrayList<>();

    public void welcome() {
        System.out.println("Hello! I'm James.");
        System.out.println("How can I help you today?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you soon!");
    }

    public void list() throws JamesException {
        if (inputs.isEmpty()) {
            throw new JamesException("There are currently no tasks!");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputs.size(); i++) {
            System.out.println((i + 1) + "." + inputs.get(i));
        }
    }
    public void delete(int index) {
        Task deletedinput = inputs.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedinput);
        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
    }

    public void addToDo(String input) throws JamesException {
        ToDo task = new ToDo(input);
        inputs.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + inputs.get(inputs.size() - 1));
        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
    }

    public void addDeadline(String description, String by) throws JamesException {
        Deadline task = new Deadline(description, by);
        inputs.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + inputs.get(inputs.size() - 1));
        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
    }

    public void addEvent(String description, String start, String end) {
        Event task = new Event(description, start, end);
        inputs.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + inputs.get(inputs.size() - 1));
        System.out.println("Now you have " + inputs.size() + " tasks in the list.");
    }

    public void setTask(int index, boolean isDone) {
        Task task = inputs.get(index - 1);
        task.setStatus(isDone);
        System.out.println("Nice! I have marked this task as " + (isDone ? "done" : "not done")  +  ":");
        System.out.println(task);

    }
    public void loadFile(File f) throws JamesException {
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                String[] task = scan.nextLine().split(" \\|");
                switch (task[0]) {
                    case "T":
                        addToDo(task[2]);
                        break;
                    case "D":
                        addDeadline(task[2], task[3]);
                        break;
                    case "E":
                        addEvent(task[2], task[3], task[4]);
                        break;
                }
                if (task[1].equals("1")) {
                    setTask(inputs.size(), true);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            throw new JamesException("File does not exist");
        }
    }

    public void writeToFile() throws JamesException {
        try {
            FileWriter file = new FileWriter("data/james.txt");
            StringBuilder strings = new StringBuilder();
            for (Task task : inputs) {
                strings.append(task.toStoreString());
            }
            file.write(String.valueOf(strings));
            file.close();
        } catch (IOException e) {
            throw new JamesException("Path could not be found");
        }
    }


    public static void main(String[] args) throws JamesException {
        James inputList = new James();
        inputList.welcome();

        File file = new File("data/james.txt");
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException error) {
            throw new JamesException("Unable to create file.");
        }

        inputList.loadFile(file);

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                String[] parts = input.split(" ", 2);
                boolean isOutOfRange = parts.length == 1;

                if (input.equals("bye")) {
                    inputList.exit();
                    break;
                } else if (input.equals("list")) {
                    inputList.list();
                } else if (input.startsWith("mark")) {
                    if (isOutOfRange) {
                        throw new JamesException("OOPS! Task index is empty");
                    } else {
                        int index = Integer.parseInt(parts[1]);
                        inputList.setTask(index, true);

                    }
                } else if (input.startsWith("unmark")) {
                    if (isOutOfRange) {
                        throw new JamesException("OOPS! Task index is empty");
                    } else {
                        int index = Integer.parseInt(parts[1]);
                        inputList.setTask(index, false);
                    }
                } else if (input.startsWith("todo")) {
                    if (isOutOfRange) {
                        throw new JamesException("OOPS! Description of todo task cannot be empty!");
                    } else {
                        input = input.replaceAll("todo", "");
                        inputList.addToDo(input);
                    }
                } else if (input.startsWith("deadline")) {
                    if (isOutOfRange) {
                        throw new JamesException("OOPS! Description of deadline task cannot be empty!");
                    } else {
                        try {
                            input = input.replaceAll("deadline", "");
                            String[] arrDeadline = input.split("/", 2);
                            inputList.addDeadline(arrDeadline[0], arrDeadline[1]);
                        } catch (JamesException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (input.startsWith("event")) {
                    if (isOutOfRange) {
                        throw new JamesException("OOPS! Description of event task cannot be empty!");
                    } else {
                        input = input.replaceAll("event", "");
                        String[] arrEvent = input.split("/", 3);
                        inputList.addEvent(arrEvent[0], arrEvent[1], arrEvent[2]);
                    }
                } else if (input.startsWith("delete")) {

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println((i + 1) + "." + inputs.get(i));
                }
            }
            else if(input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markAsDone();
                    System.out.println(inputs.get(index).toString());
                }
            }
            else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < inputs.size()) {
                    inputs.get(index).markAsUnDone();
                    System.out.println(inputs.get(index).toString());
                }
            }
            else if (input.startsWith("todo")) {
                try {
                    input = input.replaceAll("todo", "");
                    inputs.add(new ToDo(input));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + inputs.get(inputs.size() - 1));
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                }   catch(JamesException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (input.startsWith("deadline")) {
                try {
                input = input.replaceAll("deadline", "");
                String[] parts = input.split("/by ", 2);
                if (parts.length != 2) {
                    throw new JamesException("OOPS!!! The description of a deadline task cannot be empty.");
                }
                Deadline task = new Deadline(parts[0], parts[1]);
                inputs.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + inputs.get(inputs.size() - 1));
                System.out.println("Now you have " + inputs.size() + " tasks in the list.");
            }   catch(JamesException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if(input.startsWith("event")) {
                try {
                    if (input.split(" ").length == 1) {
                        throw new JamesException("The description of an event cannot be empty.");
                    }
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new JamesException("Please include: /from <time> /to <time>");
                    }
                    String[] arrEvent = input.split(" ", 2)[1].split(" /from ");
                    String description = arrEvent[0];
                    String[] timeRange = arrEvent[1].split(" /to ");
                    String from = timeRange[0];
                    String to = timeRange[1];
                    Event event = new Event(description, from, to);
                    inputs.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + inputs.get(inputs.size() - 1));
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
               } catch(JamesException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("delete")) {

                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        inputList.delete(index);
                    } catch (NumberFormatException e) {
                        System.out.println("OOPS!!! Invalid number input. Please enter a valid number for the task index.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Invalid task index. Please enter a valid number for the task index.");
                    }
                } else {
                    throw new JamesException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (JamesException e) {
                System.out.println(e);
            }
            inputList.writeToFile();
        }
    }
}


