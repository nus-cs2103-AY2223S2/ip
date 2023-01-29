package duke;

import duke.Tasks.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/** List containing all Tasks*/
public class TaskList {
    /** ArrayList containing all Tasks*/
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Processes all input commands
     * @param input is user's input
     * @throws DukeExceptions if input format is wrong
     * @throws Exception if input is invalid
     */
    public void handleInput(String input) {
        if (input.equals("list")) {
            try { //check if list is empty
                if(tasks.isEmpty()) {
                    throw new DukeExceptions("List is empty!");
                }
            } catch(DukeExceptions de) {
                System.out.println("List is empty!");
            }
            tasks.forEach(x -> {
                System.out.println(x);
            });
        }
        if (input.equals("bye")) {
            System.out.println("See you again, thanks for visiting!");
            Storage.upload(this.tasks);
        }
        if(input.startsWith("mark ")) {
            String[] inp = input.split(" ");
            int id = Integer.parseInt(inp[1]);
            try {
                Task marked = tasks.get(id - 1);
                marked.setIsDone(true);
                System.out.println("Good job! This task is now marked done!\n" + marked);
            } catch(Exception e) {
                System.out.println("No such task found!");
            }
        }
        if(input.startsWith("unmark ")) {
            String[] inp = input.split(" ");
            int id = Integer.parseInt(inp[1]);
            try {
                Task unmarked = tasks.get(id - 1);
                unmarked.setIsDone(false);
                System.out.println("What a bummer! This task is now unmarked\n" + unmarked);
            } catch(Exception e) {
                System.out.println("No such task found!");
            }
        }
        if(input.startsWith("todo ")) {
            ToDo processed = Parser.parseToDo(tasks.size(), input);
            tasks.add(processed);
        }
        if(input.startsWith("deadline ")) {
            Deadline dl = Parser.parseDeadline(tasks.size(), input);
            try { //catching no desc and no deadline
                if(input.substring(9).equals("")) {
                    throw new DukeExceptions("Input cannot be empty!");
                }
            } catch(DukeExceptions e) {
                System.out.println("Input cannot be empty!");
            }
            String[] inp = input.split("/");
            try { //catching no deadline
                String deadline = inp[1].substring(3);
                LocalDate date = LocalDate.parse(deadline);
                try { //catching no description
                    String undesc = inp[0];
                    String desc = undesc.substring(9);
                    if(desc.equals("")) {
                        throw new DukeExceptions("Input cannot be empty!");
                    }
                    tasks.add(new Deadline(tasks.size() + 1, desc, date));
                } catch(DukeExceptions e){
                    System.out.println("Description cannot be empty!");
                }
            } catch(Exception e) {
                System.out.println(e);
                System.out.println("Please input a deadline!");
            }
        }
        if(input.startsWith("event ")) {
            try { //catching no desc and no deadline
                if(input.substring(6).equals("")) {
                    throw new DukeExceptions("Input cannot be empty!");
                }
            } catch(DukeExceptions e) {
                System.out.println("Input cannot be empty!");
            }
            String[] inp = input.split("/");
            try { //Catching for input format error
                if(inp.length != 3) {
                    throw new DukeExceptions("Format is task, start, end!");
                }
            } catch(DukeExceptions de) {
                System.out.println("Format is task, /start, /end!");
            }
            String start = inp[1].substring(5);
            String[] DnT = start.split(" "); //[2000-10-23,10:15]
            String startDate = DnT[0];
            String startTime = DnT[1];
            LocalDateTime startDnT = LocalDateTime.parse(String.join("T", startDate, startTime));
            String end = inp[2].substring(3);
            String[] DateAndTime = end.split(" ");
            String endDate = DateAndTime[0];
            String endTime = DateAndTime[1];
            LocalDateTime endDnT = LocalDateTime.parse(String.join("T", endDate, endTime));
            String undesc = inp[0];
            String desc = undesc.substring(6);
            try { //catching for empty description
                if(desc.equals("")) {
                    throw new DukeExceptions("Description cannot be empty!");
                }
            } catch(DukeExceptions e) {
                System.out.println("Description cannot be empty!");
            }
            Event ev = new Event(tasks.size() + 1, desc, startDnT, endDnT);
            tasks.add(ev);
        }
        if (input.startsWith("delete ")) {
            String[] inp = input.split(" ");
            int id = Integer.parseInt(inp[1]);
            try {
                tasks.remove(id - 1);
                tasks.forEach(x -> x.setId(tasks.indexOf(x) + 1)); //update id of all items after removing
            } catch(Exception e) {
                System.out.println("No such task found!");
            }
        }
    }
}
