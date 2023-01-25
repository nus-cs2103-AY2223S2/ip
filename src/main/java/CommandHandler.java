import java.util.List;

public class CommandHandler {
    public static String endDuke() {
        return "Bye! Hope to see you again soon!\n";
    }
    public static String showTasks(List<Task> tasks) {
        String response = "";
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                response += String.format("%d.%s", i, tasks.get(i - 1).printTask()) + "\n";
            }
        } else {
            response = "You currently have no items in your to-do list!\n";
        }
        return response;
    }
    public static String markTask(String command, List<Task> tasks) {
        String response = "";
        if (! command.matches("mark \\d+")) {
            response = "Please enter one task which you would like to mark as done.\n";
        } else {
            try {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.get(taskIndex).setDone(true);
                response += "Okay! I've marked this task as done!\n";
                response += tasks.get(taskIndex).printTask() + "\n";
            } catch (IndexOutOfBoundsException e) {
                response = "You don't have that many tasks!\n";
            }
        }
        return response;
    }
    public static String unmarkTask(String command, List<Task> tasks) {
        String response = "";
        if (! command.matches("unmark \\d+")) {
            response = "Please enter one task which you would like to mark as undone.\n";
        } else {
            try {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                tasks.get(taskIndex).setDone(false);
                response += "Okay! I've marked this task as not done yet!\n";
                response += tasks.get(taskIndex).printTask() + "\n";
            } catch (IndexOutOfBoundsException e) {
                response = "You don't have that many tasks!\n";
            }
        }
        return response;
    }
    public static String addTodo(String command, List<Task> tasks) {
        String response = "";
        if (! command.matches("todo .+")) {
            response = "Please enter the task you would like to do in the format \n>> todo [task]\n";
        } else {
            command = command.split(" ", 2)[1];
            Task newTask = new Todo(command);
            tasks.add(newTask);
            response = String.format("Added: %s\n", newTask.printTask());
        }
        return response;
    }
    public static String addDeadline(String command, List<Task> tasks) {
        String response = "";
        if (! command.matches("deadline .+ /by .+")) {
            response = "Sorry, that command is invalid. Specify a deadline task with \n >> deadline [description] /by [time]\n";
        } else {
            String delimiter = "/by ";
            String task = command.substring("deadline ".length(), command.indexOf(delimiter) - 1);
            String deadline = command.substring(command.indexOf(delimiter) + delimiter.length());
            Task newTask = new Deadline(task, deadline);
            tasks.add(newTask);
            response = String.format("Added: %s\n", newTask.printTask());
        }
        return response;

    }
    public static String addEvent(String command, List<Task> tasks) {
        String response = "";
        if (! command.matches("event .+ /from .+ /to .+")) {
            response = "Sorry, that command is invalid. Specify an event task with \n >> event [description] /from [start time] /to [end time]\n";
        } else {
            String startDelimiter = "/from ";
            String endDelimiter = "/to ";
            String task = command.substring("event ".length(), command.indexOf(startDelimiter) - 1);
            String startTime = command.substring(
                    command.indexOf(startDelimiter) + startDelimiter.length(),
                    command.indexOf(endDelimiter) - 1);
            String endTime = command.substring(
                    command.indexOf(endDelimiter) + endDelimiter.length());
            Task newTask = new Event(task, startTime, endTime);
            tasks.add(newTask);
            response = String.format("Added: %s\n", newTask.printTask());
        }
        return response;
    }
    public static String noMatch() {
        return "Sorry, I didn't understand that, please ask again.\n";
    }

    public static String deleteEvent(String command, List<Task> tasks) {
        String response = "";
        if (! command.matches("delete \\d+")) {
            response = "Tell me the index of the event you want to delete! Type >>list to view your events again.\n";
        } else {
            try {
                int taskIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                response = String.format("Okay! I deleted task %s\n", tasks.get(taskIndex).printTask());
                tasks.remove(taskIndex);
            } catch (IndexOutOfBoundsException e) {
                response = "You don't have that many tasks!\n";
            }
        }
        return response;
    }
}
