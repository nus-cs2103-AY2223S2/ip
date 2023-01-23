import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import exceptions.*;

public class Duke{

    private static List<Task> list = new ArrayList<>();

    /**
     * Prints Welcome message
     */
    private static void greet(){
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Prints Exit message
     */
    private static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args)  {
        String directoryName = System.getProperty("user.dir") + File.separator + "data";
        String fileName = "duke.txt";
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(directoryName + File.separator + fileName);
        try {
            file.createNewFile();
            readFile(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.startsWith("bye")) {
            String[] c = input.split(" ");
            try {
                if (c[0].equals("list")) {
                    listItOut();
                } else if (c[0].equals("mark")) {
                    mark(input);
                    writeFile(file);
                } else if (c[0].equals("unmark")) {
                    unmark(input);
                    writeFile(file);
                } else if (c[0].equals("todo")) {
                    todo(input);
                    writeFile(file);
                } else if (c[0].equals("deadline")) {
                    deadline(input);
                    writeFile(file);
                } else if (c[0].equals("event")) {
                    event(input);
                    writeFile(file);
                }  else if (c[0].equals("delete")) {
                    delete(input);
                    writeFile(file);
                }else {
                    throw new InvalidInstructionException();
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }
        exit();
        sc.close();
    }

    /**
     * List the items in the list
     */

    public static void listItOut(){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    /**
     * Executes the marking of task as done.
     * @param input in the form of a string
     */

    public static void mark(String input) throws NumberFormatException{
        try {
            String number = input.substring(5);
            int taskNum = Integer.parseInt(number);
            int taskNumMinusOne = taskNum - 1;
            if(taskNumMinusOne < 0 || taskNumMinusOne > list.size() - 1)
                throw new InvalidNumberException();
            list.get(taskNumMinusOne).setAsMarked();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskNum + "." + list.get(taskNumMinusOne));
        }catch(InvalidNumberException e){
            System.out.println(e.getMessage());
        }catch(NumberFormatException e){
            System.out.println("Please input a valid number.");
        }
    }

    /**
     * Deletes a task from the list
     * @param input in the form of a string
     */

    public static void delete(String input){
        String[] c = input.split(" ");
        int taskNum = Integer.parseInt(c[1]);
        int taskNumMinusOne = taskNum - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskNum + "." + list.get(taskNumMinusOne));
        list.remove(taskNumMinusOne);
        System.out.println("Now you have " + list.size()  +" tasks in the list.");
    }

    /**
     * Executes the marking of task as not complete.
     * @param input in the form of a string
     */

    public static void unmark(String input) throws NumberFormatException{
        try {
            String number = input.substring(7);
            int taskNum = Integer.parseInt(number);
            int taskNumMinusOne = taskNum - 1;
            if(taskNumMinusOne < 0 || taskNumMinusOne > list.size() - 1)
                throw new InvalidNumberException();
            list.get(taskNumMinusOne).setAsUnmarked();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskNum + "." + list.get(taskNumMinusOne));
        }catch(InvalidNumberException e){
            System.out.println(e.getMessage());
        }catch(NumberFormatException e){
            System.out.println("Please input a valid number.");
        }
    }

    /**
     * Produce a new todo object and places inside list
     * @param input in the form of a string
     * @throws BlankException if there's nothing written todo.
     */

    public static void todo(String input) throws BlankException {
        if(!(input.substring(4).trim().isBlank())){
            Task task= new Todo(input);
            list.add(task);
            System.out.println("Got it. I've added this task: \n" + task);
            System.out.format("Now you have %s tasks in the list.\n", list.size());
        }else{
            throw new BlankException();
        }
    }

    /**
     * Produce a new deadline object and places inside list
     * @param input in the form of a string
     */

    public static void deadline(String input) throws BlankException{
        if(!(input.substring(8).trim().isBlank())){
            input = input.substring(9);
            String[] split = input.split("/by");
            String description = split[0].trim();
            String time = split[1].trim();
            Task task = new Deadline(description, time);
            list.add(task);
            System.out.println("Got it. I've added this task: \n" + task);
            System.out.format("Now you have %s tasks in the list.\n", list.size());
        }else{
            throw new BlankException();
        }
    }

    /**
     * Produce a new event object and places inside list
     * @param input in the form of a string
     */

    public static void event(String input) throws BlankException{
        if(!(input.substring(5).trim().isBlank())){
            String[] s = input.split("/from");
            String[] timing = s[1].split("/to");
            String from = timing[0].trim();
            String to = timing[1].trim();
            String description = s[0].substring(5).trim();
            Task task = new Event(description, from, to );
            list.add(task);
            System.out.println("Got it. I've added this task: \n" + task);
            System.out.format("Now you have %s tasks in the list.\n", list.size());
        }else{
            throw new BlankException();
        }
    }

    public static void writeFile(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            for(int i = 0; i < list.size(); ++i) {
                Task task = list.get(i);
                String done = task.getStatusIcon().equals("X") ? "1" : "0";
                String write = "|" + done + "|" + task.getDescription();
                if (task instanceof Todo) {
                    write = "T" + write;
                } else if (task instanceof Deadline) {
                    write = "D" + write + "|" + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    write = "E" + write + "|" + ((Event) task).getTime();
                }
                fw.write(write);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e ) {
            System.out.println(e.getMessage());
        }
    }

    public static void readFile(File file) {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|");
                String task = split[0];
                String mark = split[1];
                switch (task) {
                    case "T":
                        Todo todo = new Todo(split[2]);
                        if (mark.equals("1")) {
                            todo.setAsMarked();
                        }
                        list.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(split[2], split[3]);
                        if (mark.equals("1")) {
                            deadline.setAsMarked();
                        }
                        list.add(deadline);
                        break;
                    case "E":
                        String[] s = split[3].split("-");
                        String from = s[0].trim();
                        String to = s[1].trim();
                        Event event = new Event(split[2], from, to);
                        if (mark.equals("1")) {
                            event.setAsMarked();
                        }
                        list.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
