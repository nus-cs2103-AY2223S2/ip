import java.util.*;

public class Duke {

    public static void main(String[] args) {
//        Task[] list = new Task[100];
//        int listLen = 0;
//
        TaskList taskList = new TaskList();

        String intro = "  ________________________________\n"
                + "  Hello! I'm Mark\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(intro);

        Scanner sc= new Scanner(System.in);

        //start of bot
        while(true){
            String str= sc.nextLine();
            String[] splitStr = str.split(" ");

            //END
            if(splitStr[0].equals("bye")) {
                break;
            }

            //List Command
            if(splitStr[0].equals("list")) {
                taskList.list();
                continue;
            }

            //mark
            if(splitStr[0].equals("mark")) {
                int index = Integer.parseInt(splitStr[1]) - 1;
                taskList.setDone(index);
                continue;
            }

            //unmark
            if(splitStr[0].equals("unmark")) {
                int index = Integer.parseInt(splitStr[1]) - 1;
               taskList.setNotDone(index);
                continue;
            }

            String requestContent = str.split(" ", 2).length == 2
                    ? str.split(" ", 2)[1]
                    : "";

            if (splitStr[0].equals("todo")) {
                taskList.addToDo(requestContent);
                continue;
            }

            if (splitStr[0].equals("deadline")) {
                String[] requestInfo = requestContent.split("/by");
                String description = requestInfo[0];
                String by = requestInfo[1];
                taskList.addDeadline(description, by);
                continue;
            }

            if (splitStr[0].equals("event")) {
                String[] requestInfo = requestContent.split("/from");
                String description = requestInfo[0];
                String[] fromTo = requestInfo[1].split("/to");

                String from = fromTo[0];
                String to = fromTo[1];
                taskList.addEvent(description, from, to);
                continue;
            }



        }

        String bye = "  ________________________________\n"
                + "  Bye! have a great day\n"
                + "  ________________________________\n";
        System.out.println(bye);

    }
}
