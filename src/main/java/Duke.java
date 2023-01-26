import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        // Read from file
        try {
            File file = new File("./data/duke.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] entry = line.split(" ");
                String c = entry[0];
                boolean isDone;
                if (entry[1].equals("true")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                StringBuilder desc = new StringBuilder();
                desc.append(" ");
                for (int i = 2; i < entry.length - 1; i++) {
                    desc.append(entry[i]);
                    desc.append(" ");
                }

                desc.append(entry[entry.length - 1]);
                if (c.equals("todo")) {
                    list.add(Todo.create(desc.toString(), isDone));
                } else if (c.equals("deadline")) {
                    list.add(Deadline.create(desc.toString(), isDone));
                } else if (c.equals("event")) {
                    list.add(Event.create(desc.toString(), isDone));
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("no file read!");
        }

        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
        String command = scanner.next();
        String content = scanner.nextLine();
        Task task;
        while (!command.equals("bye")) {
            System.out.println(line);
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
            } else if (command.equals("todo")) {
                try {
                    task = Todo.create(content);
                    list.add(task);
                    System.out.println("Ok boss. Added task:\n" + task.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if (command.equals("deadline")) {
                try {
                    task = Deadline.create(content);
                    list.add(task);
                    System.out.println("Ok boss. Added task:\n" + task.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Invalid input for deadline.");
                }
            } else if (command.equals("event")) {
                try {
                    task = Event.create(content);
                    list.add(task);
                    System.out.println("Ok boss. Added task:\n" + task.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Invalid input for event.");
                }
            } else if (command.equals("mark")) {
                if (content.length() < 2) {
                    System.out.println("☹ OOPS!!! Invalid input for mark command.");
                } else {
                    int index = Integer.parseInt(content.substring(1)) - 1;
                    if (index >= list.size() || index < 0) {
                        System.out.println("☹ OOPS!!! No such task in list.");
                    } else {
                        task = list.get(index);
                        task.markDone();
                        System.out.println("Ok boss! Marked this task as done: \n" + task.toString());
                    }
                }
            } else if (command.equals("unmark")) {
                if (content.length() < 2) {
                    System.out.println("☹ OOPS!!! Invalid input for unmark command.");
                } else {
                    int index = Integer.parseInt(content.substring(1)) - 1;
                    if (index >= list.size() || index < 0) {
                        System.out.println("☹ OOPS!!! No such task in list.");
                    } else {
                        task = list.get(index);
                        task.unmarkDone();
                        System.out.println("Ok boss! Marked this task as not done yet: \n" + task.toString());
                    }
                }
            } else if (command.equals("delete")) {
                if (content.length() < 2) {
                    System.out.println("☹ OOPS!!! Invalid input for delete command.");
                } else {
                    int index = Integer.parseInt(content.substring(1)) - 1;
                    if (index >= list.size() || index < 0) {
                        System.out.println("☹ OOPS!!! No such task in list.");
                    } else {
                        task = list.remove(index);
                        System.out.println("Ok boss! Task removed: \n" + task.toString());
                        System.out.println("Now you have " + list.size() + " in the list.");
                    }
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(line);
            command = scanner.next();
            content = scanner.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
        // write to file
        try {
            File file = new File("./data/duke.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i  = 0; i < list.size(); i++) {
                Task t = list.get(i);
                String entry = t.getType() + " " + t.isDone + " " + t.description;
                if (t instanceof Deadline) {
                    entry += " /by " + ((Deadline) t).dueDate;
                } else if (t instanceof Event) {
                    entry += " /from " + ((Event) t).startTime + " /to " + ((Event) t).endTime;
                }
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            System.out.println("Write error!");
        }
    }
}