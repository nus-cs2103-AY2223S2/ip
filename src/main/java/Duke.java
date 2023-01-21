import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" +
                            "What can I do for you?\n" + logo);

        ArrayList<Task> list = new ArrayList<>();
        FileWriter fw = null;
        FileReader fr = null;
        try
        {
            fr = new FileReader("duke.txt");
            String task = "";
            int ch;
            while ((ch=fr.read()) != -1)
                task += (char)ch;
            fr.close();

            String[] lines = task.split("\n");
            String taskSplit[];
            for (int i = 0; i < lines.length; i++) {
                taskSplit = lines[i].split(" ");
                if(taskSplit[1].equals("T")){

                    String description = "";

                    for (int j = 5; j < taskSplit.length; j++) {
                        description += taskSplit[j] + " ";
                    }
                    list.add(new ToDo(description));

                } else if(taskSplit[1].equals("D")){

                    String deadline = "deadline";
                    for (int j = 5; j < taskSplit.length; j++) {
                        deadline += " " + taskSplit[j];
                    }
                    String split[] = deadline.split(" ");
                    addDeadline(split, list, 1);

                } else if(taskSplit[1].equals("E")){
                    String event = "event";
                    for (int j = 5; j < taskSplit.length; j++) {
                        event += " " + taskSplit[j];
                    }
                    String split[] = event.split(" ");
                    addEvent(split, list, 1);
                }
                if(taskSplit[3].equals("Y")) {
                    list.get(list.size() - 1).isDone = true;
                }
            }
            
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found...creating the file");
            fw = new FileWriter("duke.txt");
        }
        catch(ArrayIndexOutOfBoundsException a)
        {
            System.out.println("array...creating the file");
            fw = new FileWriter("duke.txt");
        }

        Scanner input = new Scanner(System.in);
        String echo = input.nextLine();
        int counter = 0;
        String echoSplit[] = echo.split(" ");

        while(true){
            if(echo.equals("bye")) {
                System.out.println("    -------------------------------------------\n    Bye. Hope to see you again soon!\n    -------------------------------------------");
                break;
            } else if(echo.equals("list")) {
                showList(list);
            } else if(echoSplit.length < 2 && !echo.equals("")) {
                throw new DukeException();
            } if(echoSplit[0].equals("mark")) {
                int index = Integer.valueOf(echoSplit[1]) - 1;
                mark(index, list);
            } else if(echoSplit[0].equals("unmark")) {
                int index = Integer.valueOf(echoSplit[1]) - 1;
                unmark(index, list);
            } else {
                if(echoSplit[0].equals("todo")) {
                    addToDo(echoSplit, list);
                    counter++;
                } else if(echoSplit[0].equals("deadline")) {
                    addDeadline(echoSplit, list, 0);
                    counter++;
                } else if(echoSplit[0].equals("event")) {
                    addEvent(echoSplit, list, 0);
                    counter++;
                } else if(echoSplit[0].equals("delete")) {
                    int index = Integer.valueOf(echoSplit[1]) - 1;
                    delete(index, list);
                    counter--;
                } else {

                }


            }

            echo = input.nextLine();
            echoSplit = echo.split(" ");
            fw = new FileWriter("duke.txt");
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).toString()+'\n');
            }
            fw.flush();

        }
        fw.close();
        fr.close();
    }
    public static void mark(int index, ArrayList<Task> list){
        try {
            list.get(index).isDone = true;
            System.out.println("    -------------------------------------------");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
            System.out.println("    -------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("caught IOOBE");
        } catch (NullPointerException n) {
            System.out.println("caught NPE");
        }
    }

    public static void unmark(int index, ArrayList<Task> list){
        list.get(index).isDone = false;
        System.out.println("    -------------------------------------------");
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println("    " + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
        System.out.println("    -------------------------------------------");
    }

    public static void showList(ArrayList<Task> list){
        System.out.println("    -------------------------------------------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + String.valueOf(i + 1) + "."  + list.get(i).toString());
        }
        System.out.println("    -------------------------------------------");
    }

    public static void addToDo(String[] echoSplit, ArrayList<Task> list){
        String task = "";
        for (int i = 1; i < echoSplit.length; i++) {
            task += echoSplit[i] + " ";
        }
        list.add(new ToDo(task));
        System.out.println("    -------------------------------------------\n    " + "added: " + task +"\n    -------------------------------------------");
    }
    public static void addEvent(String[] echoSplit, ArrayList<Task> list, int print){
        String task = "";
        int fromI = 0;
        int toI = 0;
        String from = "";
        String to = "";

        for (int i = 1; i < echoSplit.length; i++) {

            if(echoSplit[i].equals("/from") || echoSplit[i].equals("from:")) {
                fromI = i;
            }
            if(echoSplit[i].equals("/to") || echoSplit[i].equals("to:")) {
                toI = i;

                for (int j = fromI + 1; j < toI; j++) {
                    if(j == toI - 1)
                        from += echoSplit[j];
                    else
                        from += echoSplit[j]+ " ";
                }
                for (int j = toI + 1; j < echoSplit.length; j++) {
                    if(j == echoSplit.length - 1)
                        to += echoSplit[j];
                    else
                        to += echoSplit[j] + " ";
                }
                for (int j = 1; j < fromI; j++) {
                    task += echoSplit[j] + " ";
                }
                break;
            }


        }
        list.add(new Event(task, from, to));
        if(print == 0)
            System.out.println("    -------------------------------------------\n    " + "added: " + task +"\n    -------------------------------------------");
    }
    public static void addDeadline(String[] echoSplit, ArrayList<Task> list, int print){
        String task = "";
        String date = "";

        for (int i = 1; i < echoSplit.length; i++) {
            if(echoSplit[i].equals("/by") || echoSplit[i].equals("by:")){

                for (int j = 1; j < i; j++) {
                    if(j == i-1)
                        task += echoSplit[j];
                    else
                        task += echoSplit[j] + " ";
                }
                for (int j = i + 1; j < echoSplit.length; j++) {
                    if(j == echoSplit.length - 1)
                        date += echoSplit[j];
                    else
                        date += echoSplit[j] + " ";
                }

                list.add(new Deadline(task, date));
                if(print == 0) {
                    System.out.println(echoSplit[i+1]);
                    System.out.println("    -------------------------------------------\n    " + "added: " + task +"\n    -------------------------------------------");
                }

            }
        }
    }
    public static void delete(int index, ArrayList<Task> list){
        System.out.println("    -------------------------------------------\n    " + "removed: " + list.get(index) +"\n    -------------------------------------------");
        list.remove(index);
    }
}

