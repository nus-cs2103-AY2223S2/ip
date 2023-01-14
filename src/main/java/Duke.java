import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke\n" +
                            "What can I do for you?\n" + logo);
        Scanner input = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String echo = input.nextLine();
        int counter = 0;
        String echoSplit[] = echo.split(" ");

        while(true){
            if(echo.equals("bye")) {
                System.out.println("    -------------------------------------------\n    Bye. Hope to see you again soon!\n    -------------------------------------------");
                break;
            } else if(echo.equals("list")) {
                System.out.println("    -------------------------------------------");
                for (int i = 0; i < counter; i++) {
                    System.out.println("    " + String.valueOf(i + 1) + "."  + list.get(i).toString());
                }
                System.out.println("    -------------------------------------------");
            } else if(echoSplit.length < 2 && !echo.equals("")) {
                throw new DukeException();
            } if(echoSplit[0].equals("mark")) {

                int index = Integer.valueOf(echoSplit[1]) - 1;
                try {
                    list.get(index).isDone = true;
                    System.out.println("    -------------------------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("    " + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
                    System.out.println("    -------------------------------------------");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("caught IOOBE");
                } catch (NullPointerException n) {
                    System.out.println("caught NPE");
                }



            } else if(echoSplit[0].equals("unmark")) {
                int index = Integer.valueOf(echoSplit[1]) - 1;
                list.get(index).isDone = false;

                System.out.println("    -------------------------------------------");
                System.out.println("Nice! I've marked this task as not done yet:");
                System.out.println("    " + "[" + list.get(index).getStatusIcon() + "] " + list.get(index).description);
                System.out.println("    -------------------------------------------");
            } else {
                if(echoSplit[0].equals("todo")) {
                    String task = "";
                    for (int i = 1; i < echoSplit.length; i++) {
                        task += echoSplit[i] + " ";
                    }
                    list.add(new ToDo(task));
                    System.out.println("    -------------------------------------------\n    " + "added: " + task +"\n    -------------------------------------------");
                    counter++;
                } else if(echoSplit[0].equals("deadline")) {
                    String task = "";

                    for (int i = 1; i < echoSplit.length; i++) {
                        if(echoSplit[i].equals("/by")){

                            for (int j = 1; j < i; j++) {
                                task += echoSplit[j] + " ";
                            }

                            list.add(new Deadline(task, echoSplit[i + 1]));
                            System.out.println("    -------------------------------------------\n    " + "added: " + task +"\n    -------------------------------------------");
                            counter++;
                        }
                    }

                } else if(echoSplit[0].equals("event")) {
                    String task = "";
                    int fromI = 0;
                    int toI = 0;
                    String from = "";
                    String to = "";

                    for (int i = 1; i < echoSplit.length; i++) {

                        if(echoSplit[i].equals("/from")) {
                            fromI = i;
                        }
                        if(echoSplit[i].equals("/to")) {
                            toI = i;

                            for (int j = fromI + 1; j < toI; j++) {
                                from = from + echoSplit[j];
                            }
                            for (int j = toI + 1; j < echoSplit.length; j++) {
                                to = to + echoSplit[j];
                            }
                            for (int j = 1; j < fromI; j++) {
                                task += echoSplit[j] + " ";
                            }
                            break;
                        }


                    }
                    list.add(new Event(task, from, to));
                    System.out.println("    -------------------------------------------\n    " + "added: " + task +"\n    -------------------------------------------");
                    counter++;
                } else if(echoSplit[0].equals("delete")) {

                    System.out.println("    -------------------------------------------\n    " + "removed: " + list.get(Integer.valueOf(echoSplit[1]) - 1) +"\n    -------------------------------------------");
                    list.remove(Integer.valueOf(echoSplit[1]) - 1);
                    counter--;
                } else {

                }


            }

            echo = input.nextLine();
            echoSplit = echo.split(" ");
        }
    }
}
