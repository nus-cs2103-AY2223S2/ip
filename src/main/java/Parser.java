import java.io.IOException;
import java.time.LocalDateTime;
import java.lang.String;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
public class Parser {
    private final static DateTimeFormatter  timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final static DateTimeFormatter  HrFormat = DateTimeFormatter.ofPattern("HHmm");
    private static void addTodo(String description, TaskList list) {
        ToDo tdItem = new ToDo(description);
        list.addTask(tdItem);
        System.out.println("\tGot it. I have added this task:\n" + tdItem.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", list.size());
    }

    private static void addEvents(String description,TaskList list, LocalDateTime start, LocalTime end) {
        Events evItem = new Events(description, start, end);
        list.addTask(evItem);
        System.out.println("\tGot it. I have added this task:\n " + evItem.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", list.size());
    }

    private static void addDeadline(String description,TaskList list, LocalDateTime doneBy) {
        Deadline dlItem = new Deadline(description, doneBy);
        list.addTask(dlItem);
        System.out.println("\tGot it. I have added this task:\n " + dlItem.toString());
        System.out.printf("\tYou have %d tasks in the list.\n\n", list.size());
    }

    public static void delete(String number, TaskList list) {
        Task removed = list.get(Integer.parseInt(number.split(" ")[1]) );
        int nummbering = Integer.parseInt(number.split(" ")[1]) - 1;
        list.removeTask(nummbering);
        Ui.Underline();
        Ui.showDeleteMessage();
        System.out.println(String.format("\t%s removed", removed.toString()));
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }
    public static int parse(String instct, TaskList list) throws  IOException  {
        String cmd = instct.split(" ")[0];
        try {
            if (instct.split(" ")[0].equals("list") ) {
                Ui.Underline();
                Ui.listMsg();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + list.get(i+1).toString());
                }
                Ui.Underline();

            }
            else if (instct.split(" ")[0].equals("bye")) {
                Ui.sayBye();
                return -1;
            }
            else if ((instct.split(" ").length) > 1 && !instct.split(" ")[0].equals("list")) {
                if (instct.split(" ")[0].equals("mark")) {
                    int numbering = Integer.parseInt(instct.split(" ")[1]) ;
                    Ui.Underline();
                    Ui.markedMessage();
                    list.markDone(numbering);
                    System.out.println("\t" + list.get(numbering).toString());
                    Ui.Underline();

                } else if (instct.split(" ")[0].equals("unmark")) {
                    int numbering = Integer.parseInt(instct.split(" ")[1]);
                    Ui.Underline();
                    Ui.unMarkedMessage();
                    list.markNotDone(numbering);
                    System.out.println("\t" + list.get(numbering).toString());
                    Ui.Underline();

                } else if (instct.split(" ")[0].equals("todo")) {
                    Ui.Underline();
                    String description = instct.split(" ")[1];
                    addTodo(description, list);
                    Ui.Underline();


                } else if (instct.split(" ")[0].equals("deadline")) {
                    Ui.Underline();
                    String description = instct.split(" ")[1];
                    String temp = instct.split(" /by ")[1];
                    LocalDateTime doneBy = LocalDateTime.parse(temp, timeFormat);
                    addDeadline(description, list, doneBy);
                    Ui.Underline();


                } else if (instct.split(" ")[0].equals("event")) {
                    Ui.Underline();
                    String description = instct.split(" ")[1];
                    String[] temp = instct.split("/from | /to ");
                    LocalDateTime from = LocalDateTime.parse(temp[1], timeFormat);
                    LocalTime to = LocalTime.parse(temp[2], HrFormat);
                    addEvents(description, list, from, to);
                    Ui.Underline();


                } else if (instct.split(" ")[0].equals("delete")) {

                    delete(instct, list);
                    Ui.Underline();


                }  else {
                    throw new unknownCommandException();
                }


            } else {
                throw new emptyDescriptionException(instct.split(" ")[0]);


            }


        } catch (DukeException ex) {
            System.out.printf("%s\n", ex);
            Ui.Underline();
        }
        return 1;
    }
}
