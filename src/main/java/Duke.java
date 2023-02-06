import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;



public class Duke {

    private static void writeToFile(String filePath, String text) throws IOException{
        File f = new File(filePath);
        f.createNewFile();
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(text);
        fw.close();
    }

    private static String getUpdatedList(List<Task> lst) {
        String res = "";
        for (int i = 0; i < lst.size(); i ++) {
            res += lst.get(i).toString();
            res += '\n';
        }
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        /* task list */
        List<Task> lst = new ArrayList<>();

        /* load duke.txt into task list */
        File f = new File("./data/duke.txt");
        Scanner scannerTxtFile = new Scanner(f);
        while (scannerTxtFile.hasNext()) {
            String txt = scannerTxtFile.nextLine();
            if (txt.substring(1,2).equals("T")) {
                String details = txt.substring(7);
                Task t = new Todo(details);
                if (txt.substring(4, 5).equals("X")) {
                    t.mark();
                }
                lst.add(t);
            } else if (txt.substring(1,2).equals("D")) {
                String[] detailsAndDueDate = txt.substring(7).split(" \\(");
                String details = detailsAndDueDate[0];
                String dueDate = detailsAndDueDate[1].substring(0, detailsAndDueDate[1].length() - 1);
                Task t = new Deadline(details, dueDate);
                if (txt.substring(4, 5).equals("X")) {
                    t.mark();
                }
                lst.add(t);
            } else if (txt.substring(1,2).equals("E")) {
                String[] detailsAndDate = txt.substring(7).split(" \\(");
                String details = detailsAndDate[0];
                String[] tmp = detailsAndDate[1].split(" to: ");
                String to = tmp[1].substring(0, tmp[1].length() - 1);
                String from = tmp[0].split("from: ")[1];
                Task t = new Event(details, from, to);
                if (txt.substring(4, 5).equals("X")) {
                    t.mark();
                }
                lst.add(t);
            }
        }
        scannerTxtFile.close();

        /* take in user input */
        Scanner sc = new Scanner(System.in);


        while (true) {
            try {

                String input = sc.nextLine().toLowerCase();
                String[] inputArr = input.split(" ", 2);
                String action = inputArr[0];

                if (action.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (action.equals("list")) { // list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= lst.size(); i++) {
                        System.out.println(i + ". " + lst.get(i - 1));
                    }
                } else if (action.equals("mark")) {
                    String details = inputArr[1];
                    int taskNum = Integer.parseInt(details);
                    Task currTask = lst.get(taskNum - 1);
                    currTask.mark();
                    System.out.println("Nice! I've marked this task as done" + '\n' + currTask);
                } else if (action.equals("unmark")) {
                    String details = inputArr[1];
                    int taskNum = Integer.parseInt(details);
                    Task currTask = lst.get(taskNum - 1);
                    currTask.unMark();
                    System.out.println("Nice! I've marked this task as not done yet" + '\n' + currTask);
                } else if (action.equals("todo")) {
                    String[] actionAndDetails = input.split(" ", 2);
                    if (actionAndDetails.length == 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String details = actionAndDetails[1];
                    Todo newTodo = new Todo(details);
                    lst.add(newTodo);
                    System.out.println("Got it. I've added this task:" + '\n' + newTodo + '\n' + "Now you have " + lst.size() + " tasks in the list");
                } else if (action.equals("deadline")) {
                    String[] detailsAndDate = inputArr[1].split(" /by ");
                    String details = detailsAndDate[0];
                    String date = detailsAndDate[1];
                    Deadline newDeadline = new Deadline(details, date);
                    lst.add(newDeadline);
                    System.out.println("Got it. I've added this task:" + '\n' + newDeadline + '\n' + "Now you have " + lst.size() + " tasks in the list");
                } else if (action.equals("event")) {
                    String[] detailsAndTime = inputArr[1].split(" /from ");
                    String details = detailsAndTime[0];
                    String[] Time = detailsAndTime[1].split(" /to ");
                    String To = Time[0];
                    String From = Time[1];
                    Event newEvent = new Event(details, To, From);
                    lst.add(newEvent);
                    System.out.println("Got it. I've added this task:" + '\n' + newEvent + '\n' + "Now you have " + lst.size() + " tasks in the list");
                } else if (action.equals("delete")) {
                    String details = inputArr[1];
                    int taskNum = Integer.parseInt(details);
                    Task currTask = lst.get(taskNum - 1);
                    lst.remove(taskNum - 1);
                    System.out.println("Noted. I've removed this task:" + '\n' + currTask  + '\n' + "Now you have " + lst.size() + " tasks in the list");
                }else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                String updatedList = getUpdatedList(lst);
                try {
                    writeToFile("./data/duke.txt", updatedList);
                } catch (IOException e){
                    System.out.println("oops!");
                }
            } catch (DukeException e) {
                    System.out.println(e.getMessage());
            }

        }
    }

}

