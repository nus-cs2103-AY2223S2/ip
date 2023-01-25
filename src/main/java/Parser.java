import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Parser {

    public static String understandInput(String input, String[] input_arr, TaskList tasks, Storage storage) throws Missing, WrongKeyWord {
        String reply;

        if (input_arr[0].equals("bye")) {
            reply = "bye";
        }
        else if (input_arr[0].equals("list")) {
            reply = tasks.printTasks();
        } else if (input_arr[0].equals("mark")) {
            int index = Integer.valueOf(input_arr[1]) - 1;
            Task task = tasks.getTask(index);
            reply =  task.mark();
        } else if (input_arr[0].equals("unmark")){
            int index = Integer.valueOf(input_arr[1]) - 1;
            Task task = tasks.getTask(index);
            reply = task.unmark();
        }
        else if (input_arr[0].equals("delete")) {
            int index = Integer.valueOf(input_arr[1]) - 1;
            Task task = tasks.deleteTask(index);
            reply = task.removeTask();

        } else if (input_arr[0].equals("todo") || input_arr[0].equals("deadline") || input_arr[0].equals("event")) {
            Task newTask = null;
            String type = input_arr[0];

            switch (type) {
            case "todo" :
                input = input.replaceFirst("todo", "");
                if (input.equals("")) {
                    throw new Missing("");
                }
                newTask = new ToDo(input);
                type = "T";
                break;
            case "deadline" :
                input = input.replaceFirst("deadline", "");
                if (input.equals("")) {
                    throw new Missing("");
                }
                String[] taskDate = input.split("/by ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(taskDate[1], formatter);
                String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma"));
                input = taskDate[0] + "(by:" + dateTimeString  + ")";
                newTask = new Deadline(input);
                type = "D";
                break;
            case "event" :
                input = input.replaceFirst("event", "");
                if (input.equals("")) {
                    throw new Missing("");
                }
                String[] taskDate2 = input.split("/from ");
                String[] fromTo = taskDate2[1].split(" /to ");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
                LocalDateTime fromDateTime = LocalDateTime.parse(fromTo[0], formatter2);
                LocalDateTime toDateTime = LocalDateTime.parse(fromTo[1], formatter2);

                String fromString = fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma"));
                String toString = toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmma"));
                input = taskDate2[0] + "(from: " + fromString + " ";
                input = input + "to: " + toString  + ")";

                newTask = new Event(input);
                type = "E";
                break;
            }
            tasks.addTask(newTask);
            reply = newTask.toString();
            storage.writeToFile(input, type);
        } else {
            throw new WrongKeyWord("");
        }
        return reply;
    }
}
