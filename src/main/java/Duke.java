import java.util.*;
import dukeexceptions.DukeExceptions;
import dukeexceptions.UnknownCommandException;

public class Duke {

    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        String intro = "  ________________________________\n"
                + "  Hello! I'm Duke\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(intro);

        Scanner sc= new Scanner(System.in);

        //start of bot
        while(true){
            String str= sc.nextLine();
            String[] splitStr = str.split(" ");
            String requestContent = str.split(" ", 2).length == 2
                    ? str.split(" ", 2)[1]
                    : "";

            //END
            if(splitStr[0].equals("bye")) {
                break;
            }

            try {

                //List Command
                if (splitStr[0].equals("list")) {
                    taskList.list();
                    continue;
                }

                //mark
                else if (splitStr[0].equals("mark")) {
                    int index = Integer.parseInt(splitStr[1]) - 1;
                    taskList.setDone(index);
                    continue;
                }

                //unmark
                else if (splitStr[0].equals("unmark")) {
                    int index = Integer.parseInt(splitStr[1]) - 1;
                    taskList.setNotDone(index);
                    continue;
                }

                else if (splitStr[0].equals("todo")) {
                    taskList.addToDo(requestContent);
                    continue;
                }

                else if (splitStr[0].equals("deadline")) {
                    taskList.addDeadline(requestContent);
                    continue;
                }

                else if (splitStr[0].equals("event")) {
                    taskList.addEvent(requestContent);
                    continue;
                }

//                else if (splitStr[0].equals("delete")) {
//                    taskList.delete(requestContent);
//                    continue;
//                }

                else {
                    taskList.unknownCommand();
                }
            } catch(DukeExceptions exceptions) {
                String reply = "  ________________________________\n"
                        + exceptions.toString() + "\n"
                        + "  ________________________________\n";
                System.out.println(reply);
            }

        }

        String bye = "  ________________________________\n"
                + "  Bye! have a great day\n"
                + "  ________________________________\n";
        System.out.println(bye);

    }
}
