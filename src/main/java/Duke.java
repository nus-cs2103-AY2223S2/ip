import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;



public class Duke {
    private static ArrayList<Task> actions = new ArrayList<Task>();

    public static void main(String[] args) {

        /*Loading task from the hard disk*/
        Storage storage =  new Storage("./src/main/data/duke.txt");
        actions = storage.load();

        Ui appInterface = new Ui();
        System.out.println(appInterface.greetUser());
        String input = appInterface.readUserInput();

        while (true) {
            try {
                String[] input_arr = input.split(" ");
                if (input.equals("bye")) {
                    System.out.print("Bye I'm gonna go eat cookies. Hope to see you again soon!");
                    break;
                }
                else if (input.equals("list")) {
                    int len = actions.size();
                    for (int i = 0; i < len; i++) {
                        System.out.println(i + 1 + ". " + actions.get(i).status());
                    }

                } else if (input_arr[0].equals("mark") || input_arr[0].equals("unmark")) {
                    int index = Integer.valueOf(input_arr[1]) - 1;
                    Task task = actions.get(index);
                    if (input_arr[0].equals("mark")) {
                        System.out.println(task.mark());
                    } else {
                        System.out.println(task.unmark());
                    }
                } else if (input_arr[0].equals("delete")) {
                    int index = Integer.valueOf(input_arr[1]) - 1;
                    Task action = actions.remove(index);
                    System.out.println(action.removeTask());
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
                    actions.add(newTask);
                    System.out.println(newTask.toString());
                    try {
                        FileWriter f = new FileWriter("./src/main/data/duke.txt", true);
                        String text = type + " |" + input;
                        f.write(System.lineSeparator());
                        f.write(text);
                        f.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    throw new WrongKeyWord("");
                }
            } catch (WrongKeyWord e) {
                System.out.println("☹ OOPS!!! I'M sorry, but I don't know what that means");
            } catch (Missing e) {
                /*Does not handle other key words yet*/
                System.out.println("☹ OOPS!!! I'M sorry, but the description of a todo cannot be empty");
            }
        }


    }
}
