import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();
    private static String[] spStg;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        try {
            loadFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Scanner reader = new Scanner(System.in);  // inside vs outside?

        while(true) {
            String s = reader.nextLine();
            spStg = s.split(" ", 2);
            if(spStg[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else if(spStg[0].equals("list")) {
                list();
            }

            else if(spStg[0].equals("delete")) {
               delete();
            }

            else if(spStg[0].equals("mark")) {
                mark();
            }

            else if(spStg[0].equals("unmark")) {
               unmark();
            }

            else {
                String firstWord = spStg[0];
                if (firstWord.equals("todo")) {
                   addTodo();
                }
                else if (firstWord.equals("deadline")) {
                    addDeadline();
                }

                else if (firstWord.equals("event")) {
                    addEvent();
                }
                else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    continue;
                }
            }

            try {
                saveToFile();
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        }
    }

    public static void loadFile() throws IOException {
        String home = System.getProperty("user.dir");
        File f = new File(home +"/data/Duke.txt");
        File directory = new File(home + "/data");
        if (! directory.exists()) {
            directory.mkdir();
        }
        f.createNewFile();

        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] curr = s.nextLine().split(" \\| ");
            if (curr[0].equals("T")) {
                list.add(new Todo(curr[2], Boolean.valueOf(curr[1])));
            }
            else if (curr[0].equals("D")) {
                list.add(new Deadline(curr[2], curr[3], Boolean.valueOf(curr[1])));
            }
            else if (curr[0].equals("E")) {
                list.add(new Event(curr[2], curr[3], curr[4], Boolean.valueOf(curr[1])));
            }
        }
    }
    
    public static void addEvent() {
        if (spStg.length == 1) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return;
        }
        String[] sppStg = spStg[1].split("/from | /to ");
        try {
            list.add(new Event(sppStg[0], sppStg[1], sppStg[2], false));
        } catch (Exception e){
            System.out.println("Please write your deadline in this format: YYYY-MM-DD HH:mm ");
            return;
        }
        System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
    }

    public static void delete() {
        int i =  Integer.parseInt(spStg[1]) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(i));
        list.remove(i);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void mark() {
        int i =  Integer.parseInt(spStg[1]) - 1;
        list.get(i).mark();
        System.out.println("Nice! I've marked this task as done:\n" + list.get(i));
    }

    public static void unmark() {
        int i =  Integer.parseInt(spStg[1]) - 1;
        list.get(i).unmark();
        System.out.println("OK, I've marked this task as not done yet:\n" + list.get(i));
    }

    public static void addTodo() {
        if (spStg.length == 1) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }
        list.add(new Todo(spStg[1],false));
        System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void addDeadline() {
        if (spStg.length == 1) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        }
        String[] sppStg = spStg[1].split("/by ");
        try {
            list.add(new Deadline(sppStg[0], sppStg[1], false));
        } catch (Exception e){
            System.out.println("Please write your deadline in this format: YYYY-MM-DD HH:mm ");
            return;
        }
        System.out.println("Got it. I've added this task:\n" + list.get(list.size() - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void saveToFile() throws IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/Duke.txt");
        for (Task t: list) {
            fw.write(t.parse() +  System.lineSeparator());
        }
        fw.close();
    }


}
