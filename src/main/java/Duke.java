import java.util.*;

public class Duke {
  public static void main(String[] args) {
    //        String logo = " ____        _        \n"
    //                + "|  _ \\ _   _| | _____ \n"
    //                + "| | | | | | | |/ / _ \\\n"
    //                + "| |_| | |_| |   <  __/\n"
    //                + "|____/ \\__,_|_|\\_\\___|\n";
    //        System.out.println("Hello from\n" + logo);

    String divider = "____________________________________________________________\n";
    System.out.println(divider + "Hello! I'm Duke");
    System.out.println("What can I do for you?\n" + divider);

    ArrayList<Task> tasks = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    while(sc.hasNext()) {
      String instr = sc.nextLine();
      switch(instr) {                                 //Single-word instructions
        case "list":  //List down
          System.out.println(divider + "Here are the tasks in your list:\n");
          for(int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1));
          }
          System.out.println(divider);
          break;

        case "bye":   //Exit
          System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
          return;

          //Other single word commands go here.

        default:                                      //Instructions with arguments
          StringTokenizer tokens = new StringTokenizer(instr, " ");
          String action = tokens.nextToken(); //Splitting the action and args.

          String keyword = "";  //keyword = argument(s) after the action.
          while(tokens.hasMoreTokens()) {
            keyword = keyword + " " + tokens.nextToken();
          }
          keyword = keyword.strip();
          if(keyword.equals("")) {
            System.out.println(divider + "OOPS!!! The description of a " + action + " cannot be empty.\n" + divider);
          }

          switch(action) {                            //For instructions with argument(s).
            case "add":
              tasks.add(new Task(keyword));
              break;

            case "mark":
              try {
                Task t1 = tasks.get(Integer.parseInt(keyword) - 1);
                t1.mark();
                System.out.println(divider + "Nice! I've marked this task as done:\n" + t1 + "\n" + divider);
              } catch(IndexOutOfBoundsException ioobe) {
                System.out.println("Sorry, the index number you've entered does not exist.");
                continue;
              } catch(NumberFormatException nfe) {
                System.out.println("Please enter the index number you wish to mark/unmark.");
              }
              break;

            case "unmark":
              try {
                Task t2 = tasks.get(Integer.parseInt(keyword) - 1);
                t2.unmark();
                System.out.println(divider + "OK! I've marked this task as not done yet:\n" + t2 + "\n" + divider);
              } catch(IndexOutOfBoundsException ioobe) {
                System.out.println("Sorry, the index number you've entered does not exist.");
                continue;
              } catch(NumberFormatException nfe) {
                System.out.println("Please enter the index number you wish to mark/unmark.");
              }
              break;

            case "todo":
              tasks.add(new Todo(keyword));
              break;

            case "deadline":
              String[] deadlineFinder = keyword.split(" /by "); //Split keyword into description and date, separated by "/by".
              keyword = deadlineFinder[0];
              try {
                String deadline = deadlineFinder[1];
                tasks.add(new Deadline(keyword, deadline));
              } catch(IndexOutOfBoundsException ioobe) {
                System.out.println("Please define a deadline following the keyword '/by'.");
              }
              break;

            case "event":
              String[] startFinder = keyword.split(" /from ");  //Split keyword into description and start,end.
              try{
                String[] endFinder = startFinder[1].split(" /to "); //Split start,end into start and end.
                tasks.add(new Event(startFinder[0], endFinder[0], endFinder[1]));
              } catch(IndexOutOfBoundsException ioobe) {
                System.out.println("Please define a start time following the keyword '/from'\nand then an end time following the keyword '/to'.");
              }
              break;

            case "delete":
              try {
                int deleteIdx = Integer.parseInt(keyword);
                tasks.remove(deleteIdx - 1);
              } catch(NumberFormatException nfe) {
                System.out.println("Please enter the index number you wish to delete.");
              } catch(IndexOutOfBoundsException ioobe) {
                System.out.println("Sorry, the index number you've entered does not exist.");
                continue;
              }
              break;
            default:
              System.out.println(divider + "OOPS! I'm sorry, but I don't know what that means :-(\n" + divider);  //For unknown action.
          }
      }
    }
  }
}
