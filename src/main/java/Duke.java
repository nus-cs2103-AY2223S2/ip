import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    private static ArrayList<Task> actions = new ArrayList<Task>();

    public static void main(String[] args) {
        try {
            File f = new File("./src/main/data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String taskDetails = s.nextLine();
                String[] details = taskDetails.split(" |", 2);
                String taskType = details[0];
                String taskName = details[1];
                System.out.println(taskName);
                taskName = taskName.replace("|", "");
                switch (taskType) {
                    case "T" :
                        actions.add(new ToDo(taskName));
                        break;
                    case "E" :
                        actions.add(new Event(taskName));
                        break;
                    case "D" :
                        actions.add(new Deadline(taskName));
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Hello from! I'm a Cookie Monster\n" + "What can I do for you?\n");
        Scanner reader = new Scanner(System.in);

        while (true) {
            try {
                String input = reader.nextLine();
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
                            input = input.replaceFirst( "/by", "(by:");
                            input = input + ")";
                            newTask = new Deadline(input);
                            type = "D";
                            break;
                        case "event" :
                            input = input.replaceFirst("event", "");
                            if (input.equals("")) {
                                throw new Missing("");
                            }
                            input = input.replaceFirst( "/from", "(from:");
                            input = input.replaceFirst( "/to ", "to: ");
                            input = input + ")";
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
