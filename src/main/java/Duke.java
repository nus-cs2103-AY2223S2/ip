import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

public class Duke {
    private final static String UNDERLINE = "________________________________________________________________";
    private static ArrayList<Task> list = new ArrayList<>();

    private final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private static void greet() {

        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println((UNDERLINE));
    }

    private static void addTodo(String description) {
        ToDo tdItem = new ToDo(description);
        list.add(tdItem);
        System.out.println("\tGot it. I have added this task:\n" + tdItem.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", list.size());
    }

    private static void addEvents(String description, String start, String end) {
        Events evItem = new Events(description, start, end);
        list.add(evItem);
        System.out.println("\tGot it. I have added this task:\n " + evItem.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", list.size());
    }

    private static void addDeadline(String description, String doneBy) {
        Deadline dlItem = new Deadline(description, doneBy);
        list.add(dlItem);
        System.out.println("\tGot it. I have added this task:\n " + dlItem.toString());
        System.out.printf("\tYou have %d tasks in the list.\n\n", list.size());
    }

    public static void delete(String number) {

        Task removed = list.get(Integer.parseInt(number.split(" ")[1]) - 1);
        int nummbering = Integer.parseInt(number.split(" ")[1]) - 1;
        list.remove(nummbering);
        System.out.println(UNDERLINE);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println(String.format("\t%s removed", removed.toString()));
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");


    }

    public static int execute(String instct) throws  IOException  {
        String cmd = instct.split(" ")[0];
        try {
            if (instct.split(" ")[0].equals("list") ) {
                System.out.println(UNDERLINE);
                System.out.println("\t" + "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
                }
                System.out.println(UNDERLINE);

            }
            else if (instct.split(" ")[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                return 0;
            }
            else if ((instct.split(" ").length) > 1 && !instct.split(" ")[0].equals("list")) {
                  if (instct.split(" ")[0].equals("mark")) {
                    int numbering = Integer.parseInt(instct.split(" ")[1]) - 1;
                    System.out.println(UNDERLINE);
                    System.out.println("\t" + "Nice! I've marked this task as done:");
                    list.get(numbering).markDone();
                    System.out.println("\t" + list.get(numbering).toString());
                    System.out.println(UNDERLINE);

                } else if (instct.split(" ")[0].equals("unmark")) {
                    int numbering = Integer.parseInt(instct.split(" ")[1]) - 1;
                    System.out.println(UNDERLINE);
                    System.out.println("\t" + "OK, I've marked this task as not done yet:");
                    list.get(numbering).markNotDone();
                    System.out.println("\t" + list.get(numbering).toString());
                    System.out.println(UNDERLINE);

                } else if (instct.split(" ")[0].equals("todo")) {
                    System.out.println(UNDERLINE);
                    String description = instct.split(" ")[1];
                    addTodo(description);
                    System.out.println(UNDERLINE);


                } else if (instct.split(" ")[0].equals("deadline")) {
                    System.out.println(UNDERLINE);
                    String description = instct.split(" ")[1];
                    String doneBy = instct.split(" /by ")[1];
                    addDeadline(description, doneBy);
                    System.out.println(UNDERLINE);


                } else if (instct.split(" ")[0].equals("event")) {
                    System.out.println(UNDERLINE);
                    String description = instct.split(" ")[1];
                    String[] temp = instct.split("/from | /to ");
                    String from = temp[1];
                    String to = temp[2];
                    addEvents(description, from, to);
                    System.out.println(UNDERLINE);


                } else if (instct.split(" ")[0].equals("delete")) {

                    delete(instct);
                    System.out.println(UNDERLINE);


                }  else {
                    throw new unknownCommandException();
                }
                saveData();

            } else {
                throw new emptyDescriptionException(instct.split(" ")[0]);


            }


        } catch (DukeException ex) {
            System.out.printf("%s\n", ex);
            System.out.println((UNDERLINE));
        }
        return 1;
    }

    private static void createDataFile() {
        File f = new File("database/data.txt");
        new File("database").mkdir();
        try {
            f.createNewFile();
        }
        catch (IOException e){
            System.out.println("File does not exist creating now");
        }
    }
    private static void readnWriteData(){
       try{
            File data = new File("database/data.txt");
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String txt = sc.nextLine();
                String[] segments = txt.split(" | ");
                String task = segments[0];
                String indicator = segments[1];
                String description = segments[2];
                Task addedtask;
                if (task.equals("T")) {
                    addedtask = new ToDo(description);
                    list.add(addedtask);
                    if (indicator.equals("1")) {
                        addedtask.markDone();
                    }
                }
                else if (task.equals("D")){
                    String Doneby = segments[3];
                    addedtask = new Deadline(description, Doneby);
                    list.add(addedtask);
                    if (indicator.equals("1")) {
                        addedtask.markDone();
                    }
                }
                else if (task.equals("E")){
                    String from = segments[3];
                    String to = segments[4];
                    addedtask = new Events(description, from, to);
                    list.add(addedtask);
                    if (indicator.equals("1")) {
                        addedtask.markDone();
                    }
                } else {
                }

            }



        }
       catch (IOException e){
           createDataFile();
       }
    }
    private static void saveData() throws  IOException{
        FileWriter fwrite = new FileWriter("database/data.txt", false);
        for (Task task : list) {
            fwrite.write(task.changeFormat() + "\n");
        }
        fwrite.close();


    }


    public static void main(String[] args) {
        Scanner text = new Scanner(System.in);

        System.out.println(logo);
        Duke.greet();
        readnWriteData();

        // parse the command
        while (true) {
            try {
                String instct = text.nextLine();
                String cmd = instct.split(" ")[0];
                int temp = execute(instct);
                if (temp == 0) {
                    break;
                }
            }
            catch (IOException e){
                 e.printStackTrace();
            }


        }

    }
}

