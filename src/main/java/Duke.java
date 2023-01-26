import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



public class Duke {

    /**
     * Update the text file whenever a task is added, removed or modified.
     * 
     * @param entireList A list in which all tasks are stored
     */
    public static void updateFile(ArrayList<Task> entireList) throws FileNotFoundException {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("/Users/kristen/Documents/NUS/CS2109S/ip/data/duke.txt"));


            for (int i = 0; i < entireList.size(); i++) {
                Task task = entireList.get(i);

                boolean isMark = task.status;
                String type = task.types;
                String name = task.item;


                if (type.equals("D")){
                    String time = task.getTime();

                    pw.println(type + "-" + isMark + "-" + name + "-" + time);
                } else if (type.equals("E")) {
                    String time = task.getTime();
                    String startEnd [] = time.split("//", 2);

                    pw.println(type + "-" + isMark + "-" + name + "-" + startEnd[0] + "-" + startEnd[1]);
                } else {
                    pw.println(type + "-" + isMark + "-" + name);
                }

            }
            pw.close();

        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Loads the text of the file into an array.
     * 
     * @param entireList A list in which all tasks are stored
     */

    public static void loadFile(ArrayList<Task> entireList) throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/kristen/Documents/NUS/CS2109S/ip/data/duke.txt"));
            String oneline;

            while ( (oneline = br.readLine() )!= null) {
                String lines [] = oneline.split("-", 3);

                Task task;

                if(lines[0].equals("T")) {
                    task = new Task(lines[2], lines[0]);

                } else if (lines[0].equals("D")) {
                    String nameTime[] = lines[2].split("-", 2);
                    task = new Deadline(nameTime[0], lines[0], "by " + nameTime[1]);

                } else {
                    String nameStartEnd[] = lines[2].split("-", 3);
                    task = new Event(nameStartEnd[0], lines[0], "from " + nameStartEnd[1], "to " + nameStartEnd[2]);

                }

                if(lines[1].equals("true")) {
                    task.mark();
                }

                entireList.add(task);
            }


        } catch (IOException i) {
            i.printStackTrace();
        }

    }


    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        toDoList toDo = new toDoList();

        loadFile(toDo.getTasks());

        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            String[] instruction = input.split(" ",2);

            try {

                if(instruction[0].equals("list")) { // printing list
                toDo.printList();

                } else if (instruction[0].equals("mark")){ //marking

                    if(instruction.length <= 1 ){
                        throw new ArgumentException("What are we marking again?");
                    } else if (! instruction[1].matches("[0-9]+")){
                        throw new ArgumentException("What are we marking again?");
                    }
                    int index = Integer.parseInt(instruction[1]);
                    if ((index -1) < 0 || (index-1) >= toDo.numberOfTask()){
                        throw new ArgumentException("Can't find the index");
                    }
                    toDo.changingStatus(0, index);

                } else if (instruction[0].equals("unmark")) { //unmarking

                    if(instruction.length <= 1 )  {
                        throw new ArgumentException("What are we unmarking again?");
                    } else if (! instruction[1].matches("[0-9]+")){
                        throw new ArgumentException("What are we unmarking again?");
                    }
                    int index = Integer.parseInt(instruction[1]);
                    if ((index -1) < 0 || (index-1) >= toDo.numberOfTask()){
                        throw new ArgumentException("Can't find the index");
                    }
                    toDo.changingStatus(1, index);

                } else if(instruction[0].equals("delete")){

                    if(instruction.length <= 1 )  {
                        throw new ArgumentException("What are we deleting again?");
                    } else if (! instruction[1].matches("[0-9]+")){
                        throw new ArgumentException("What are we deleting again?");
                    }
                    int index = Integer.parseInt(instruction[1]);

                    if ((index -1) < 0 || (index-1) >= toDo.numberOfTask()){
                        throw new ArgumentException("Can't find the index");
                    }

                    System.out.println(" Noted. I've removed this task:");
                    toDo.deleteTask(index);
                    System.out.println("Now you have " + toDo.numberOfTask() + " tasks in the list.");


                } else { // adding into list
                    String command = instruction[0];

                    if(!command.equals("todo") && !command.equals("deadline") &&
                            !command.equals("event") && !command.equals("delete")) {
                        throw new DukeException("*sigh* No such commands, please be serious.");
                    }

                    if(instruction.length == 1){
                        throw new ArgumentException("Yeah let's keep wasting each other time." +
                                " You're missing the parameters in case you don't already know.");
                    }

                    String item = instruction[1];
                    if(item.trim().isEmpty()) {
                        throw new ArgumentException("Spacing out already?");
                    }


                    if(command.equals("todo")) {
                        System.out.println("Got it. I've added this task:");
                        toDo.addItem("T", item);

                    } else if (command.equals("deadline")) {
                        String itemANDtime[] = item.split("/", 2);

                        if(itemANDtime.length== 1){
                            throw new ArgumentException("How interesting a deadline without a time limit?");
                        } else if(itemANDtime[0].trim().isEmpty()) {
                            throw new ArgumentException("Oh? Looks like your item disappeared into space.");
                        } else if(itemANDtime[1].trim().isEmpty()) {
                            throw new ArgumentException("Hiding from reality I see. Too bad time waits for no man");
                        }

                        String time[] = itemANDtime[1].split(" ", 2);

                        if (!time[0].equals("by")) {
                            throw new ArgumentException("Where did your BY go?");
                        } else if (time[1].trim().isEmpty()) {
                            throw new ArgumentException("A blank time I see");
                        }

                        System.out.println("Got it. I've added this task:");
                        toDo.addItemDeadline("D", itemANDtime[0], itemANDtime[1]);

                    } else {
                        String itemANDtime[] = item.split("/", 3);
                        if(itemANDtime.length <= 2){
                            throw new ArgumentException("How interesting an event without proper timing?");
                        } else if(itemANDtime[0].trim().isEmpty()) {
                            throw new ArgumentException("Oh? Looks like your item disappeared into space.");
                        } else if(itemANDtime[1].trim().isEmpty() || itemANDtime[2].trim().isEmpty()) {
                            throw new ArgumentException("Hiding from reality I see. Too bad time waits for no man");
                        }

                        String start[] = itemANDtime[1].split(" ", 2);
                        String end[] = itemANDtime[2].split(" ", 2);

                        if (!start[0].equals("from")) {
                            throw new ArgumentException("You know, eveything starts FROM somewhere. Where did your FROM go?");
                        } else if (start[1].trim().isEmpty()) {
                            throw new ArgumentException("A blank time I see");
                        } else if (!end[0].equals("to")){
                            throw new ArgumentException("Where did your TO go?");
                        } else if (end[1].trim().isEmpty()) {
                            throw new ArgumentException("Cool event! Too bad I don't know what time it ends");
                        }

                        System.out.println("Got it. I've added this task:");
                        toDo.addItemEvent("E", itemANDtime[0], itemANDtime[1], itemANDtime[2]);
                    }

                    System.out.println("Now you have " + toDo.numberOfTask() + " tasks in the list.");

                }

               updateFile(toDo.getTasks());

            } catch (DukeException ex) {
            System.out.println(ex.getMessage());
            } catch (ArgumentException ex2) {
                System.out.println(ex2.getMessage());
            }

            input = sc.nextLine();
        }

        sc.close();
    }


}
