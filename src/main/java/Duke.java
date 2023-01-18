
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {

        //Introductory Responses

        String name = "C4PO-Storage";
        String line = "-----------------------------------------------";
        String quote = "Hello. I don’t believe we have been introduced. A pleasure to meet you. I am " + name + " Human-Computer Relations.";
        String job = "I collect words which you say.";
        System.out.println(quote);
        System.out.println(job);
        System.out.println(name + ": Master, please type your response below.");
        System.out.println(line);
        System.out.println(line);

        //Init scanner
        Scanner newScan = new Scanner(System.in);

        //Main Loop

        while (true) {
            String receive = newScan.nextLine(); //reads user input

            try {
                if (receive.length() > 2 && "bye".equalsIgnoreCase(receive.strip())) {
                    System.out.println(name + ": Goodbye! I'll miss all of you, especially you R3-D3.");
                    break;
                } else if (receive.length() > 3 && "list".equalsIgnoreCase(receive.strip())) {
                    Task.printList();
                } else if (receive.length() > 3 && "mark".equalsIgnoreCase(receive.substring(0,4))) {
                    try {
                        Integer index = Integer.valueOf(receive.substring(4).strip());

                        String out = Task.mark(index, "mark");
                        if (!out.equals("No such item exists in list")) {
                            System.out.println("Great work sir! I've marked this task as done. Task:");
                        }
                        System.out.println(out);

                    } catch (Exception e) {
                        String markErr = "Sir! Index for toggling mark cannot be empty";
                        throw new DukeException(markErr);
                    }


                } else if (receive.length() > 5 && "unmark".equalsIgnoreCase(receive.substring(0,6))) {
                    try {
                        Integer index = Integer.valueOf(receive.substring(6).strip());
                        String out = Task.mark(index, "unmark");
                        if (!out.equals("No such item exists in list")) {
                            System.out.println("Ahhh I see ...  I shall unmark that task then. *beep* Done. Task:");
                        }

                        System.out.println(out);
                    } catch (Exception e) {
                        String markErr = "Sir! Index for toggling mark cannot be empty";
                        throw new DukeException(markErr);
                    }

                } else if (receive.length() > 3 && "todo".equalsIgnoreCase(receive.substring(0,4))) {
                    String desc = receive.substring(4);

                    String errToDo = "Sir!!! The description of a todo cannot be empty.";
                    if (desc.isBlank()) {
                        throw new DukeException(errToDo);
                    }
                    ToDo newTodo = new ToDo(desc);
                    System.out.println("Excellent sir, I've added the task: ");
                    System.out.println(newTodo.toString());
                    System.out.println(Task.getTaskCount());

                } else if (receive.length() > 7 && "deadline".equalsIgnoreCase(receive.substring(0,8))) {
                    String desc = receive.substring(8).strip();
                    String errDeadline = "Sir!!! The description of a deadline cannot be empty.";
                    if (desc.isBlank()) {
                        throw new DukeException(errDeadline);
                    }
                    try {
                        String[] stringarr = desc.split(" /by ");
                        Deadline newDeadline = new Deadline(stringarr[0], stringarr[1]);
                        System.out.println("Excellent sir, I've added the task: ");
                        System.out.println(newDeadline.toString());
                        System.out.println(Task.getTaskCount());
                    } catch (Exception e) {
                        String eventErr = "Sir, there seems to be an error in your deadline details input.";
                        throw new DukeException(eventErr);
                    }


                } else if (receive.length() > 4 && "event".equalsIgnoreCase(receive.substring(0,5))) {
                    String desc = receive.substring(5).strip();
                    String errEvent = "Sir!!! The description of a event cannot be empty.";
                    if (desc.isBlank()) {
                        throw new DukeException(errEvent);
                    }
                    try {
                        String[] stringarr = desc.split(" /from ");
                        String[] strarr = stringarr[1].split(" /to ");
                        Event newEvent = new Event(stringarr[0], strarr[0], strarr[1]);
                        System.out.println("Excellent sir, I've added the task: ");
                        System.out.println(newEvent.toString());
                        System.out.println(Task.getTaskCount());
                    } catch (Exception e) {
                        String eventErr = "Sir, there seems to be an error in your event details input.";
                        throw new DukeException(eventErr);
                    }

                } else if (receive.length() > 5 && "delete".equalsIgnoreCase(receive.substring(0,6))) {
                    try {
                        Integer index = Integer.valueOf(receive.substring(6).strip());
                        String out = Task.delete(index);
                        System.out.println("Ahhh I see ...  I shall delete that task then. *beep* Done. Task deleted:");
                        System.out.println(out);
                        System.out.println(Task.getTaskCount());
                    } catch (Exception e) {
                        String markErr = "Sir! Index for deletion cannot be empty";
                        throw new DukeException(markErr);
                    }
                } else {
                    String takFaham = "My apologies sir, my program forbids me from translating anything other than command words.";
                    throw new DukeException(takFaham);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            System.out.println(line);
        }
    }
}
