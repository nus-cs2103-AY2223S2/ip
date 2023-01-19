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

    ArrayList<String> tasks = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    while(sc.hasNext()) {
      String instr = sc.nextLine();
      switch(instr) {                                 //Single-word instructions
        case "list":  //List down
          System.out.println(divider);
          for(int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1));
          }
          System.out.println(divider);
          break;

        case "bye":   //Exit
          System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
          break;

        //Other single word commands go here.
        
        default:                                      //Instructions with arguments
          StringTokenizer tokens = new StringTokenizer(instr, " ");
          String action = tokens.nextToken(); //Splitting the action and args.

          String keyword = "";  //keyword = argument(s) after the action.
          while(tokens.hasMoreTokens()) {
            keyword = keyword + " " + tokens.nextToken();
          }

          switch(action) {                            //For instructions with argument(s).
            case "add":
              tasks.add(keyword);
              break;
          }
      }
    }
  }
}
