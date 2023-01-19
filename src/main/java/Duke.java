import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String start_message = "Hello! I'm Duke\n" +  "What can I do for you?";
        System.out.println(start_message);
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<Task>();
        String s = scanner.nextLine();
        int i = 1;

        while (!s.equals("bye")) {
            Integer len = s.length();
            try {
                if (s.equals("list")) {
                    if (list.size() == 0){
                        String err_msg = "You have not upload any task yet";
                        throw new DukeException(err_msg);
                    }
                    System.out.println("Here are the tasks in your list:");
                    i = 1;
                    for (Task task : list) {
                        System.out.println(i + ". " + task.toString());
                        i++;
                    }
                } else if (len >= 8 && s.substring(0, 6).equals("delete")) {
                    try{
                        Integer num = Integer.parseInt(s.substring(7));
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
                } else if (len >= 6 && s.substring(0, 4).equals("mark")) {
                    try {
                        Integer num = Integer.parseInt(s.substring(5));
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
                }else if (len >= 8 && s.substring(0, 6).equals("unmark")) {
                    try{
                        Integer num = Integer.parseInt(s.substring(7));
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
                } else if (len >= 6 && s.substring(0, 4).equals("todo")) {
                    if (len <= 5) {
                        String err_msg = "☹ OOPS!!! The description of a todo cannot be empty";
                        s = scanner.nextLine();
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    ToDos todo = new ToDos(s.substring(5));
                    list.add(todo);
                    System.out.println("added: " + todo);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                } else if (len >= 10 && s.substring(0, 8).equals("deadline")) {
                    String[] ddl_str_arr = s.split("/");
                    if (len <= 9 || ddl_str_arr.length <= 1) {
                        String err_msg = "☹ OOPS!!! The description or date of a deadline cannot be empty";
                        throw new DukeException(err_msg);
                    }
                    System.out.println("Got it. I've added this task");
                    Deadline deadline = new Deadline(ddl_str_arr[0].substring(9), ddl_str_arr[1]);
                    list.add(deadline);
                    System.out.println("added: " + deadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                } else if (len >= 7 && s.substring(0, 5).equals("event")) {
                    String[] event_str_arr = s.split("/");
                    if (len <= 9 || event_str_arr.length <= 2) {
                        String err_msg = "☹ OOPS!!! The description or date of a event cannot be empty";
                        throw new DukeException(err_msg);
                    }
                    System.out.println("Got it. I've added this task");
                    Event event = new Event(event_str_arr[0].substring(6), event_str_arr[1] + event_str_arr[2]);
                    list.add(event);
                    System.out.println("added: " + event);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                } else {
                    String err_msg = "☹ OOPS!!! I'm sorry, but I don't know what that means";
                    throw new DukeException(err_msg);
                }
            } catch (DukeException e){
                System.out.println(e);
            }
            s = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}