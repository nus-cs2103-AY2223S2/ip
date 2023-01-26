

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C4PO {

    public static String name = "C4PO-Storage";
    public static String line = "-----------------------------------------------";
    public static String quote = "Hello. I don’t believe we have been introduced. A pleasure to meet you. I am " + name + " Human-Computer Relations.";
    public static String job = "I collect words which you say.";

    public static String callToAction = ": Master, please type your response below.";

    protected static String fileP = "./data/tasks.txt";
    protected static String dirP = "./data";
    public static String forgetQuote = "Oh! It seems I do not remember your past tasks... silly me! Let me organise my circuits.";
    public static String rememberQuote = "Oh! It seems I do remember your past tasks... thank goodness! Let me see, the tasks are:";
    public static String initMemory = "Thank you for waiting sir. Successfully initiated memory";
    public static String farewellQuote = ": Goodbye! I'll miss all of you, especially you R3-D3.";

    public static String taskAddedQuote = "Excellent sir, I've added the task: ";
    public static String unableToUnderstandQuote = "My apologies sir, my program forbids me from translating anything other than command words.";
    public static String markedAsDoneQuote = "Great work sir! I've marked this task as done. Task:";
    public static String markErr = "Sir! Index for deletion cannot be empty";

    public static void main(String[] args) throws IOException {

        //Introductory Responses
        System.out.println(quote);
        System.out.println(job);
        System.out.println(name + callToAction);
        System.out.println(line);
        System.out.println(line);

        //Init scanner
        Scanner newScan = new Scanner(System.in);



        //Init Database retrieval

        //Create directories if not exist
        Files.createDirectories(Paths.get(dirP));
        File f = new File(fileP);
        if (!f.exists()) {
            System.out.println(forgetQuote);
            boolean success = f.createNewFile();
            if (success) {
                System.out.println(initMemory);
            }
        } else {
            System.out.println(rememberQuote);
            //Else, if exists then load it all into Tasks.list
            Task.loadFromFile(fileP);
            Task.printList();
        }


        //Main Loop
        //From here, append newly added Tasks to the file
        boolean taskFileAppend = true;
        while (true) {
            String receive = newScan.nextLine(); //reads user input

            try {
                if (receive.length() > 2 && "bye".equalsIgnoreCase(receive.strip())) {
                    System.out.println(name + farewellQuote);
                    break;
                } else if (receive.length() > 3 && "list".equalsIgnoreCase(receive.strip())) {
                    Task.printList();
                } else if (receive.length() > 3 && "mark".equalsIgnoreCase(receive.substring(0,4))) {
                    try {
                        Integer index = Integer.valueOf(receive.substring(4).strip());

                        String out = Task.mark(index, "mark");
                        if (!out.equals("No such item exists in list")) {
                            System.out.println(markedAsDoneQuote);
                            Task.writeToFile();
                        }
                        System.out.println(out);

                    } catch (Exception e) {
                        String markErr = "Sir! Index for toggling mark cannot be empty";
                        throw new BotException(markErr);
                    }


                } else if (receive.length() > 5 && "unmark".equalsIgnoreCase(receive.substring(0,6))) {
                    try {
                        Integer index = Integer.valueOf(receive.substring(6).strip());
                        String out = Task.mark(index, "unmark");
                        if (!out.equals("No such item exists in list")) {
                            System.out.println("Ahhh I see ...  I shall unmark that task then. *beep* Done. Task:");
                            Task.writeToFile();
                        }

                        System.out.println(out);
                    } catch (Exception e) {
                        String markErr = "Sir! Index for toggling mark cannot be empty";
                        throw new BotException(markErr);
                    }

                } else if (receive.length() > 3 && "todo".equalsIgnoreCase(receive.substring(0,4))) {
                    String desc = receive.substring(4).strip();

                    String errToDo = "Sir!!! The description of a todo cannot be empty.";
                    if (desc.isBlank()) {
                        throw new BotException(errToDo);
                    }
                    ToDo newTodo = new ToDo(desc);
                    if (taskFileAppend) {
                        Task.writeToFile(newTodo.getTaskFileFormat());
                    }
                    System.out.println("Excellent sir, I've added the task: ");
                    System.out.println(newTodo);
                    System.out.println(Task.getTaskCount());

                } else if (receive.length() > 7 && "deadline".equalsIgnoreCase(receive.substring(0,8))) {
                    String desc = receive.substring(8).strip();
                    String errDeadline = "Sir!!! The description of a deadline cannot be empty.";
                    if (desc.isBlank()) {
                        throw new BotException(errDeadline);
                    }
                    try {
                        String[] stringArr = desc.split(" /by ");

                        ArrayList<String> arr = new ArrayList<String>(List.of(stringArr));
                        String description = arr.get(0);
                        arr.remove(0);

                        String dateTimeString = String.join(" ", arr);
                        Deadline newDeadline = new Deadline(description, dateTimeString);
                        if (taskFileAppend) {
                            Task.writeToFile(newDeadline.getTaskFileFormat());
                        }
                        System.out.println(taskAddedQuote);
                        System.out.println(newDeadline);
                        System.out.println(Task.getTaskCount());
                    } catch (Exception e) {
                        System.out.println(e);
                        String eventErr = "Sir, there seems to be an error in your deadline details input.";
                        throw new BotException(eventErr);
                    }


                } else if (receive.length() > 4 && "event".equalsIgnoreCase(receive.substring(0,5))) {
                    String desc = receive.substring(5).strip();
                    String errEvent = "Sir!!! The description of a event cannot be empty.";
                    if (desc.isBlank()) {
                        throw new BotException(errEvent);
                    }
                    try {
                        String[] stringarr = desc.split(" /from ");
                        String[] strarr = stringarr[1].split(" /to ");
                        Event newEvent = new Event(stringarr[0], strarr[0], strarr[1]);
                        //If we want to append the task to the file
                        if (taskFileAppend) {
                            Task.writeToFile(newEvent.getTaskFileFormat());
                        }
                        System.out.println("Excellent sir, I've added the task: ");
                        System.out.println(newEvent);
                        System.out.println(Task.getTaskCount());
                    } catch (Exception e) {
                        System.out.println(e);
                        String eventErr = "Sir, there seems to be an error in your event details input.";
                        throw new BotException(eventErr);
                    }

                } else if (receive.length() > 5 && "delete".equalsIgnoreCase(receive.substring(0,6))) {
                    try {
                        Integer index = Integer.valueOf(receive.substring(6).strip());
                        String out = Task.delete(index);
                        if (!out.equals("No such item exists in list")) {
                            System.out.println("Ahhh I see ...  I shall delete that task then. *beep* Done. Task deleted:");
                        }
                        System.out.println(out);

                        System.out.println(Task.getTaskCount());
                    } catch (Exception e) {

                        throw new BotException(markErr);
                    }
                } else {
                    String takFaham = unableToUnderstandQuote;
                    throw new BotException(takFaham);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println(line);
        }
    }
}
