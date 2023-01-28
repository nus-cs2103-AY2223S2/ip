import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    private static void loadData() throws IOException {
        File data = new File("duke.txt");
        data.createNewFile();
        List<String> tmp = Files.readAllLines(Paths.get("duke.txt"), StandardCharsets.UTF_8);
        Iterator<String> itr = tmp.iterator();
        Task newTask;
        while (itr.hasNext()) {
            String curr = itr.next();
            String[] currLine = curr.split(" \\| ");
            String type = currLine[0];
            String description = currLine[2];
            switch (type) {
                case "D": {
                    String deadline = currLine[3];
                    newTask = new Deadline(description, deadline);
                    break;
                }
                case "E": {
                    newTask = new Event(description, currLine[3], currLine[4]);
                    break;
                }
                default: {
                    newTask = new ToDo(description);
                }
            }
            if (currLine[1].equals("X")) {
                newTask.mark();
            }
            list.add(newTask);
        }
    }

    public static void saveData() throws IOException {
        FileWriter dukeWriter = new FileWriter("duke.txt", false);
        for (Task i : list) {
            if (i instanceof ToDo) {
                dukeWriter.write("T | " + i.getStatusIcon() + " | " + i.getDescription() + "\n");
            } else if (i instanceof Deadline) {
                String[] nameAndDeadline = i.toString().split("\\(by: ");
                String deadline = nameAndDeadline[1];
                dukeWriter.write("D | " + i.getStatusIcon() + " | " + i.getDescription() + " | " + deadline.replace(")", "") + "\n");
            } else {
                String[] nameAndStart = i.toString().split(" \\(from: ");
                String[] startAndEnd = nameAndStart[1].split(" to: ");
                String start = startAndEnd[0];
                String end = startAndEnd[1];
                dukeWriter.write("E | " + i.getStatusIcon() + " | " + i.getDescription() + " | " + start + " | " + end.replace(")", "") + "\n");
            }
        }
        dukeWriter.close();
    }

    public static void main(String[] args) throws DukeException, IOException {
        System.out.println("Hello!\n" + "\nWhat can I do for you?\n");
        loadData();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: \n");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ": " + list.get(i - 1));
                }
            } else if (input.split(" ")[0].equals("mark")) {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    list.get(num - 1).mark();
                    System.out.println("Nice! I've marked this task as done: \n" + list.get(num-1));

            } else if (input.split(" ")[0].equals("unmark")) {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    list.get(num - 1).unmark();
                    System.out.println("OK, I've marked this task as not done yet: \n" + list.get(num-1));

           } else if (input.split(" ")[0].equals("delete")) {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    Task removed = list.remove(num - 1);
                    System.out.println("Noted. I've removed this task: \n" + removed + "\nNow you have " + list.size() + " tasks in the list.");
            } else {
                Task newTask;
                String[] inputs = input.split(" ");
                String type = inputs[0];
                switch(type) {
                    case "todo": {
                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        String name = input.split(" ", 2)[1];
                        newTask = new ToDo(name);
                        break;
                    }
                    case "deadline": {
                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] nameAndDeadline = input.split(" ", 2)[1].split(" /by ");
                        if (nameAndDeadline.length < 2) {
                            throw new DukeException("☹ OOPS!!! The deadline of a deadline cannot be empty.");
                        }
                        String name = nameAndDeadline[0];
                        String deadline = nameAndDeadline[1];
                        newTask = new Deadline(name, deadline);
                        break;
                    }
                    case "event": {
                        if (inputs.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        String[] nameAndStart = input.split(" ", 2)[1].split(" /from ");
                        if (nameAndStart.length < 2) {
                            throw new DukeException("☹ OOPS!!! The start of a event cannot be empty.");
                        }
                        String name = nameAndStart[0];
                        String[] startAndEnd = nameAndStart[1].split(" /to ");
                        if (startAndEnd.length < 2) {
                            throw new DukeException("☹ OOPS!!! The end of a event cannot be empty.");
                        }
                        String start = startAndEnd[0];
                        String end = startAndEnd[1];
                        newTask = new Event(name, start, end);
                        break;
                    }
                    default: {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
                list.add(newTask);
                System.out.println("Got it. I've added this task:\n" + newTask + "\nNow you have " + list.size() + " tasks in the list.");
            }
            input = sc.nextLine();
        }
        if (input.equals("bye")) {
            saveData();
            System.out.println("Bye. Hope to see you again soon!");
        }

    }



}
