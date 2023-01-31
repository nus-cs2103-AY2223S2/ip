package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

/** Duke is a Chat Bot program that help user track list of tasks
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Duke {
    private final Storage storage;
    private final UI ui;

    public Duke () {
        this.storage = new Storage("duke.txt");
        this.ui = new UI();
    }

    public void run(){
        ui.showWelcome();
        int i;
        String input = "";

        while (!input.equals("bye")) {
            input = ui.readCommand();
            int len = input.length();
            Parser parser = new Parser();
            String command = parser.parse(input);
            ArrayList<Task> task_list = storage.load();
            try {
                switch (command){
                    case "BYE" :
                        break;
                    case "LIST":
                        if (task_list.size() == 0){
                            String err_msg = "You have not upload any task yet";
                            throw new DukeException(err_msg);
                        }
                        System.out.println("Here are the tasks in your list:");
                        i = 1;
                        for (Task task : task_list) {
                            System.out.println(i + ". " + task.toString());
                            i++;
                        }
                        continue;
                    case "DELETE":
                        try{
                            int num = Integer.parseInt(input.substring(7));
                            Task curr_task = task_list.get(num - 1);
                            System.out.println("Noted. I've removed this task: \n  " + curr_task.toString());
                            task_list.remove(curr_task);
                            storage.update_data(task_list);
                            System.out.println("Now you have " + (task_list.size()) + " tasks in the list");
                        } catch (IndexOutOfBoundsException err1){
                            String err_msg = "☹ OOPS!!! Please the Duke.Task number that you have keyed in is invalid.";
                            System.out.println(err_msg);
                        } catch (NumberFormatException err2) {
                            String err_msg = "☹ OOPS!!! Please key in a valid Number.";
                            System.out.println(err_msg);
                        }
                        continue;
                    case "MARK":
                        try {
                            int num = Integer.parseInt(input.substring(5));
                            Task curr_task = task_list.get(num - 1);
                            System.out.println("Nice! I've marked this task as done");
                            curr_task.markAsDone();
                            task_list.set(num - 1, curr_task);
                            storage.update_data(task_list);
                            System.out.println(curr_task.getStatusIcon() + " " + curr_task.getDes());
                        } catch (IndexOutOfBoundsException err1) {
                            String err_msg = "☹ OOPS!!! Please the Duke.Task number that you have keyed in is invalid.";
                            System.out.println(err_msg);
                        } catch (NumberFormatException err2) {
                            String err_msg = "☹ OOPS!!! Please key in a valid Number.";
                            System.out.println(err_msg);
                        }
                        continue;
                    case "UNMARK":
                        try{
                            int num = Integer.parseInt(input.substring(7));
                            Task curr_task = task_list.get(num - 1);
                            System.out.println("OK, I've marked this task as not done yet");
                            curr_task.unMark();
                            task_list.set(num - 1, curr_task);
                            storage.update_data(task_list);
                            System.out.println(curr_task.getStatusIcon() + " " + curr_task.getDes());
                        } catch (IndexOutOfBoundsException err1){
                            String err_msg = "☹ OOPS!!! Please the Duke.Task number that you have keyed in is invalid.";
                            System.out.println(err_msg);
                        } catch (NumberFormatException err2) {
                            String err_msg = "☹ OOPS!!! Please key in a valid Number.";
                            System.out.println(err_msg);
                        }
                        continue;
                    case "TODO":
                        if (len <= 5) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty");
                            continue;
                        }
                        System.out.println("Got it. I've added this task:");
                        ToDos todo = new ToDos(input.substring(5), 0);
                        task_list.add(todo);
                        storage.update_data(task_list);
                        System.out.println("added: " + todo);
                        System.out.println("Now you have " + task_list.size() + " tasks in the list");
                        continue;
                    case "DEADLINE":
                        String[] ddl_str_arr = input.split(" /");
                        if (len <= 9 || ddl_str_arr.length <= 1 || ddl_str_arr[0].length() < 10) {
                            String err_msg = "☹ OOPS!!! The description or date of a deadline cannot be empty";
                            throw new DukeException(err_msg);
                        }
                        try {
                            LocalDate deadline_time = LocalDate.parse(ddl_str_arr[1]);
                            Deadline deadline = new Deadline(ddl_str_arr[0].substring(9), deadline_time, 0);
                            task_list.add(deadline);
                            storage.update_data(task_list);
                            System.out.println("added: " + deadline);
                            System.out.println("Now you have " + task_list.size() + " tasks in the list");
                        } catch (DateTimeParseException e) {
                            String err_msg = "☹ OOPS!!! The description or date of a deadline is wrong, plase key in the" +
                                    "date in the format of yyyy-mm-dd, eg. 2001-02-10\n"
                                    + "You may key in: deadline hw1 /2001-02-10, Duke.Duke will record your deadline for hw1 as" +
                                    "2001-02-10";
                            throw new DukeException(err_msg);
                        }
                        continue;
                    case "EVENT":
                        String[] event_str_arr = input.split(" /");
                        if (len <= 9 || event_str_arr.length <= 2 || event_str_arr[0].length() < 7) {
                            String err_msg = "☹ OOPS!!! The description or date of a event cannot be empty";
                            throw new DukeException(err_msg);
                        }
                        try {
                            System.out.println(Arrays.toString(event_str_arr));
                            LocalDate from = LocalDate.parse(event_str_arr[1]);
                            LocalDate to = LocalDate.parse(event_str_arr[2]);
                            if (from.isAfter(to)){
                                String err_msg = "☹ OOPS!!! Your time range is from a date to another date that is earlier" +
                                        "than the former. Please key in a valid time range";
                                throw new DukeException(err_msg);
                            }
                            Event event = new Event(event_str_arr[0].substring(6), from, to, 0);
                            task_list.add(event);
                            System.out.println("Got it. I've added this task");
                            storage.update_data(task_list);

                            System.out.println("added: " + event);
                            System.out.println("Now you have " + task_list.size() + " tasks in the list");
                        } catch (DateTimeParseException e) {
                            String err_msg = "☹ OOPS!!! The description or date for the event is wrong, plase key in the" +
                                    "date in the format of yyyy-mm-dd, eg. 2001-02-10\n"
                                    + "You may key in: event hw1 /2001-02-10 /2001-02-12, Duke.Duke will record your event hw1 as" +
                                    "from 2001-02-10 to 2001-02-12";
                            throw new DukeException(err_msg);
                        }
                        continue;
                    case "ERROR":
                        String err_msg = "☹ OOPS!!! I'm sorry, but I don't know what that means";
                        throw new DukeException(err_msg);
                    }
                }catch (DukeException e){
                    System.out.println(e);
            }
            input = ui.readCommand();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
