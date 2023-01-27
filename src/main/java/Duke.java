import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
        String start_message = "Hello! I'm Duke\n" +  "What can I do for you?";
        System.out.println(start_message);
        Scanner scanner = new Scanner(System.in);
        Database db = new Database("duke.txt");
        List<Task> list = new ArrayList<Task>();
        String input = scanner.nextLine();
        int i = 1;


        while (!input.equals("bye")) {
            Integer len = input.length();
            try {
                if (input.equals("list")) {
                    ArrayList<Task> arrayList = db.get_data();
                    if (arrayList.size() == 0){
                        String err_msg = "You have not upload any task yet";
                        throw new DukeException(err_msg);
                    }
                    System.out.println("Here are the tasks in your list:");
                    i = 1;
                    for (Task task : arrayList) {
                        System.out.println(i + ". " + task.toString());
                        i++;
                    }
                } else if (len >= 8 && input.substring(0, 6).equals("delete")) {
                    try{
                        Integer num = Integer.parseInt(input.substring(7));
                        Task curr_task = list.get(num - 1);
                        System.out.println("Noted. I've removed this task: \n  " + curr_task.toString());
                        list.remove(curr_task);
                        System.out.println("Now you have " + (list.size()) + " tasks in the list");
                    } catch (IndexOutOfBoundsException err1){
                        String err_msg = "☹ OOPS!!! Please the Task number that you have keyed in is invalid.";
                        System.out.println(err_msg);
                    } catch (NumberFormatException err2) {
                        String err_msg = "☹ OOPS!!! Please key in a valid Number.";
                        System.out.println(err_msg);
                    }
                } else if (len >= 6 && input.substring(0, 4).equals("mark")) {
                    try {
                        Integer num = Integer.parseInt(input.substring(5));
                        Task curr_task = list.get(num - 1);
                        System.out.println("Nice! I've marked this task as done");
                        curr_task.markAsDone();
                        System.out.println(curr_task.getStatusIcon() + " " + curr_task.getDes());
                    } catch (IndexOutOfBoundsException err1) {
                        String err_msg = "☹ OOPS!!! Please the Task number that you have keyed in is invalid.";
                        System.out.println(err_msg);
                    } catch (NumberFormatException err2) {
                        String err_msg = "☹ OOPS!!! Please key in a valid Number.";
                        System.out.println(err_msg);
                    }
                }else if (len >= 8 && input.substring(0, 6).equals("unmark")) {
                    try{
                        Integer num = Integer.parseInt(input.substring(7));
                        Task curr_task = list.get(num - 1);
                        System.out.println("OK, I've marked this task as not done yet");
                        curr_task.unMark();
                        System.out.println(curr_task.getStatusIcon() + " " + curr_task.getDes());
                    } catch (IndexOutOfBoundsException err1){
                        String err_msg = "☹ OOPS!!! Please the Task number that you have keyed in is invalid.";
                        System.out.println(err_msg);
                    } catch (NumberFormatException err2) {
                        String err_msg = "☹ OOPS!!! Please key in a valid Number.";
                        System.out.println(err_msg);
                    }
                } else if (len >= 6 && input.substring(0, 4).equals("todo")) {
                    if (len <= 5) {
                        String err_msg = "☹ OOPS!!! The description of a todo cannot be empty";
                        input = scanner.nextLine();
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    ToDos todo = new ToDos(input.substring(5));
                    ArrayList arraylist = db.get_data();
                    arraylist.add(todo);
                    db.update_data(arraylist);
                    System.out.println("added: " + todo);
                    System.out.println("Now you have " + arraylist.size() + " tasks in the list");
                } else if (len >= 10 && input.substring(0, 8).equals("deadline")) {
                    String[] ddl_str_arr = input.split(" /");
                    if (len <= 9 || ddl_str_arr.length <= 1) {
                        String err_msg = "☹ OOPS!!! The description or date of a deadline cannot be empty";
                        throw new DukeException(err_msg);
                    }
                    System.out.println("Got it. I've added this task");
                    System.out.println(Arrays.toString(ddl_str_arr));
                    Deadline deadline = new Deadline(ddl_str_arr[0].substring(9), ddl_str_arr[1]);

                    ArrayList arraylist = db.get_data();
                    arraylist.add(deadline);
                    db.update_data(arraylist);

//                    list.add(deadline);
                    System.out.println("added: " + deadline);
                    System.out.println("Now you have " + arraylist.size() + " tasks in the list");
                } else if (len >= 7 && input.substring(0, 5).equals("event")) {
                    String[] event_str_arr = input.split(" /");
                    if (len <= 9 || event_str_arr.length <= 2) {
                        String err_msg = "☹ OOPS!!! The description or date of a event cannot be empty";
                        throw new DukeException(err_msg);
                    }
                    System.out.println("Got it. I've added this task");
                    Event event = new Event(event_str_arr[0].substring(6), event_str_arr[1] + event_str_arr[2]);
//                    list.add(event);
                    ArrayList arraylist = db.get_data();
                    arraylist.add(event);
                    db.update_data(arraylist);

                    System.out.println("added: " + event);
                    System.out.println("Now you have " + arraylist.size() + " tasks in the list");
                } else {
                    String err_msg = "☹ OOPS!!! I'm sorry, but I don't know what that means";
                    throw new DukeException(err_msg);
                }
            } catch (DukeException e){
                System.out.println(e);
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}