import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        toDoList toDo = new toDoList();

        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            String[] instruction = input.split(" ",2);

            if(instruction[0].equals("list")) { // printing list
                toDo.printList();
            } else if (instruction[0].equals("mark")){ //marking

                try {
                    if(instruction.length <= 1 ){
                        throw new ArgumentException("What are we marking again?");
                    } else if (! instruction[1].matches("[0-9]+")){
                        throw new ArgumentException("What are we marking again?");
                    }
                    int index = Integer.parseInt(instruction[1]);
                    if ((index -1) < 0 || (index-1) >= toDo.numberOfTask()){
                        throw new ArgumentException("Can't find the index");
                    }
                    toDo.changingStatus(0, index);

                } catch (ArgumentException ex) {
                    System.out.println(ex.getMessage());
                }

            } else if (instruction[0].equals("unmark")) { //unmarking
                try {
                    if(instruction.length <= 1 )  {
                        throw new ArgumentException("What are we unmarking again?");
                    } else if (! instruction[1].matches("[0-9]+")){
                        throw new ArgumentException("What are we unmarking again?");
                    }
                    int index = Integer.parseInt(instruction[1]);
                    if ((index -1) < 0 || (index-1) >= toDo.numberOfTask()){
                        throw new ArgumentException("Can't find the index");
                    }
                    toDo.changingStatus(1, index);

                } catch (ArgumentException ex) {
                    System.out.println(ex.getMessage());
                }

            } else if(instruction[0].equals("delete")){
                try {
                    if(instruction.length <= 1 )  {
                        throw new ArgumentException("What are we deleting again?");
                    } else if (! instruction[1].matches("[0-9]+")){
                        throw new ArgumentException("What are we deleting again?");
                    }
                    int index = Integer.parseInt(instruction[1]);
                    if ((index -1) < 0 || (index-1) >= toDo.numberOfTask()){
                        throw new ArgumentException("Can't find the index");
                    }

                    System.out.println(" Noted. I've removed this task:");
                    toDo.deleteTask(index);
                    System.out.println("Now you have " + toDo.numberOfTask() + " tasks in the list.");

                } catch (ArgumentException ex) {
                    System.out.println(ex.getMessage());
                }

            } else { // adding into list

                String command = instruction[0];
                try {

                    if(!command.equals("todo") && !command.equals("deadline") &&
                            !command.equals("event") && !command.equals("delete")) {
                        throw new DukeException("*sigh* No such commands, please be serious.");
                    }

                    if(instruction.length == 1){
                        throw new ArgumentException("Yeah let's keep wasting each other time." +
                                " You're missing the parameters in case you don't already know.");
                    }

                    String item = instruction[1];
                    if(item.trim().isEmpty()) {
                        throw new ArgumentException("Spacing out already?");
                    }


                    if(command.equals("todo")) {
                        System.out.println("Got it. I've added this task:");
                        toDo.addItem("T", item);

                    } else if (command.equals("deadline")) {
                        String itemANDtime[] = item.split("/", 2);

                        if(itemANDtime.length== 1){
                            throw new ArgumentException("How interesting a deadline without a time limit?");
                        } else if(itemANDtime[0].trim().isEmpty()) {
                            throw new ArgumentException("Oh? Looks like your item disappeared into space.");
                        } else if(itemANDtime[1].trim().isEmpty()) {
                            throw new ArgumentException("Hiding from reality I see. Too bad time waits for no man");
                        }

                        String time[] = itemANDtime[1].split(" ", 2);

                        if (!time[0].equals("by")) {
                            throw new ArgumentException("Where did your BY go?");
                        } else if (time[1].trim().isEmpty()) {
                            throw new ArgumentException("A blank time I see");
                        }
                        String deadlineTime = itemANDtime[1].split(" ", 2)[1];

                        SimpleDateFormat converterDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        Date date = converterDate.parse(deadlineTime);

                        System.out.println("Got it. I've added this task:");
                        toDo.addItemDeadline("D", itemANDtime[0], date);

                    } else {

                        if(!item.contains("/from") || !item.contains("/to")) {
                            throw new ArgumentException("For event please enter the format: event nameOfEvent /from dd/MM/yyyy HH:mm" +
                                    " /to dd/MM/yyyy HH:mm");
                        }

                        String itemANDtime[] = item.split(" /from ", 2);

                        if (itemANDtime.length < 2) {
                            throw new ArgumentException("You forget the event name!");
                        } else if(itemANDtime[0].trim().isEmpty()) {
                            throw new ArgumentException("Oh? Looks like your item disappeared into space.");
                        }

                        String nameItem = itemANDtime[0];

                        String startEnd [] = itemANDtime[1].split(" /to ");

                        if(startEnd.length <2) {
                            throw new ArgumentException("Please enter the format: event nameOfEvent /from xxx /to xxx");
                        }

                        if(startEnd[0].trim().isEmpty() || startEnd[1].trim().isEmpty()) {
                            throw new ArgumentException("Please do not leave the timing blank!");
                        }

                        SimpleDateFormat converterStartDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        Date startDate = converterStartDate.parse(startEnd[0]);
                        Date endDate =  converterStartDate.parse(startEnd[1]);


//                        if(itemANDtime.length <= 2){
//                            throw new ArgumentException("How interesting an event without proper timing?");
//                        } else if(itemANDtime[0].trim().isEmpty()) {
//                            throw new ArgumentException("Oh? Looks like your item disappeared into space.");
//                        } else if(itemANDtime[1].trim().isEmpty() || itemANDtime[2].trim().isEmpty()) {
//                            throw new ArgumentException("Hiding from reality I see. Too bad time waits for no man");
//                        }

//                        String start[] = itemANDtime[1].split(" ", 2);
//                        String end[] = itemANDtime[2].split(" ", 2);
//
//                        if (!start[0].equals("from")) {
//                            throw new ArgumentException("You know, eveything starts FROM somewhere. Where did your FROM go?");
//                        } else if (start[1].trim().isEmpty()) {
//                            throw new ArgumentException("A blank time I see");
//                        } else if (!end[0].equals("to")){
//                            throw new ArgumentException("Where did your TO go?");
//                        } else if (end[1].trim().isEmpty()) {
//                            throw new ArgumentException("Cool event! Too bad I don't know what time it ends");
//                        }

                        System.out.println("Got it. I've added this task:");
                        toDo.addItemEvent("E", nameItem, startDate, endDate);
//                        toDo.addItemEvent("E", itemANDtime[0], itemANDtime[1], itemANDtime[2]);
                    }

                    System.out.println("Now you have " + toDo.numberOfTask() + " tasks in the list.");

                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                } catch (ArgumentException ex2) {
                    System.out.println(ex2.getMessage());
                } catch (ParseException e) {
                    System.out.println("Please enter the time of the format dd/MM/yyyy HH:mm");
                }
            }

            input = sc.nextLine();
        }

        sc.close();
    }
}
