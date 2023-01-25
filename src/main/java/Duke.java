<<<<<<< HEAD
import java.io.*;
import java.util.Scanner;
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
>>>>>>> branch-Level-8

import command.*;
import exception.DukeException;

import task.*;


public class Duke {
    private static TaskList taskList;

    private static String line = "    ----------------------------------------";
    private static String logo =  "____    ____  __    __   __    __  \n"
                                + "\\   \\  /   / |  |  |  | |  |  |  |\n"
                                + " \\   \\/   /  |  |  |  | |  |  |  | \n"
                                + "  \\_    _/   |  |  |  | |  |  |  | \n"
                                + "    |  |     |  `--'  | |  `--'  | \n"
                                + "    |__|      \\______/   \\______/  \n";

    private enum commandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args)  {
        boolean terminate = false;
        taskList = new TaskList();
        String filePath = "./data/duke.txt";

        System.out.println("           Hello! I am\n" + logo);
        
        try {
            taskList = load(filePath);
            System.out.println(line);
        } catch (FileNotFoundException e) {
            System.out.println("    No existing files found.");
        }

        System.out.println("    What can I do for you?");

        

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!terminate) {
            try {
                System.out.println(line);
                String input = br.readLine().trim();

                if (input.isEmpty()) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but input cannot be empty");

                } else {
                    String[] inputs = input.split(" ");
                    commandType command = commandType.valueOf(inputs[0].toUpperCase());
                    switch(command) {
                        case BYE:
                            terminate = true;
                            save(taskList, filePath);
                            new ByeCommand().execute();
                            
                            break;

                        case LIST:
                            new ListCommand(taskList).execute();
                            break;

                        case MARK:
                            new MarkCommand(input, taskList).execute();
                            break;

                        case UNMARK:
                            new UnmarkCommand(input, taskList).execute();
                            break;

                        case DELETE:
                            new DeleteCommand(input, taskList).execute();
                            break;

                        case TODO:
                            new TodoCommand(input, taskList).execute();
                            break;
                            
                        case DEADLINE:
                            new DeadlineCommand(input, taskList).execute();
                            break;

                        case EVENT:
                            new EventCommand(input, taskList).execute();
                            break;

                        default :
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                
                }
                

            } catch (IOException e) {
                System.out.println("    " + e.getMessage());
            } catch (DukeException e) {
                System.out.println("    " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DateTimeParseException e) {
                System.out.println("    ☹ OOPS!!! Invalid datetime. Syntax: YYYY-MM-DD");
            }
        }
    }

   

    public static void save(TaskList taskList, String filePath) throws IOException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);

        for (Task task : taskList) {
            
            // T | 1 | read book
            // D | 0 | return book | June 6th
            // E | 0 | project meeting | Aug 6th 2-4pm
            // T | 1 | join sports club
            
            String str = task.encode();
            fw.write(str);
            fw.write(System.lineSeparator());
        }
        System.out.println("    Tasks saved successfully. ");
        fw.close();
    }   

    public static TaskList load(String filePath) throws FileNotFoundException {
        File file = new File(filePath); // create a File for the given file path
        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(file); // create a Scanner using the File as the source
        
        System.out.println("    Here are your existing tasks: ");
        while (sc.hasNext()) {

            String[] inputs = sc.nextLine().trim().split(" \\| ");

            commandType taskType = commandType.valueOf(inputs[0].toUpperCase());

            String task = inputs[2];
            boolean status = Boolean. parseBoolean(inputs[1]);

            switch(taskType) {
                case TODO:
                    Task todo = new ToDo(task, status);
                    System.out.println("    " + todo);
                    taskList.add(todo);
                    break;
                case DEADLINE:
                    Task deadline = new Deadline(task, status, inputs[3]);
                    System.out.println("    " + deadline);
                    taskList.add(deadline);
                    break;
                case EVENT:
                    Task event = new Event(task, status, inputs[3], inputs[4]);
                    System.out.println("    " + event);
                    taskList.add(event);
                    break;
                default:
                    // Will not reach here

            }

        }

        sc.close();
        return taskList;
    }

}
