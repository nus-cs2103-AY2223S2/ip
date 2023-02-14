package DukeHelpfulCode.Utilities;

import java.util.Arrays;
import java.util.NoSuchElementException;

import DukeHelpfulCode.Commands.*;
import DukeHelpfulCode.Exceptions.*;
import DukeHelpfulCode.Tasks.*;

public class Parser {
    /**
     * parses the commands kekw
     *
     */

    public static Command parse(String fullCommand) throws NoTaskTypeException, NoTaskNameException, NoDueTimeException, NoStartTimeException, NoEndTimeException, NoSuchTaskException {
        /**
         * Converts the user input into a Command.
         *
         * @param   fullCommand         The user input
         * @return  command             The Command that the user wants
         * @throws  NoTaskTypeException     Thrown if parser does not find the task type if adding
         * @throws  NoTaskNameException     Thrown if parser does not find the task name if adding
         * @throws  NoDueTimeException      Thrown if parser does not find the due dateTime if adding deadline
         * @throws  NoStartTimeException    Thrown if parser does not find the start dateTime if adding event
         * @throws  NoEndTimeException      Thrown if parser does not find the end dateTime if adding event
         * @throws  NoSuchTaskException     Thrown if deleting/marking task that isnt there on the list
         */
        Command cmd = new HelpCommand();
        String[] cmdArr = fullCommand.split(" ");

        if (cmdArr.length == 1 && cmdArr[0].equals("bye")){
            cmd = new ExitCommand();
            return cmd;

        } else if (cmdArr.length == 1 && cmdArr[0].equals("help")){
            cmd = new HelpCommand();
            return cmd;
        } else if (cmdArr.length == 1 && cmdArr[0].equals("list")){
            cmd = new ListCommand();
            return cmd;
        }

        // check for task type, task name, start end due datetime
        // input will be in the form of:
        // >bye
        // >help
        // >add event name /from sdt /to edt
        // >mark 1 -> first item so 0th index
        // >unmark 1 -> first item so 0th index
        // >delete 1 -> first item to 0th index

        if (cmdArr[0].equals("add")){
            String[] taskTypes = {"todo", "event", "deadline"};
            String taskName = "";
            Task task;
            // no task name
            // if deadline -> no due time
            // if event -> no sdt or no edt


            if (cmdArr.length == 1){
                throw new NoTaskTypeException();
            } else if (cmdArr.length == 2){
                if (!Arrays.asList(taskTypes).contains(cmdArr[1])){
                    throw new NoTaskTypeException();
                } else {
                    throw new NoTaskNameException();
                }


            } else {
                if (cmdArr[1].equals("todo")){
                    for (int i = 2; i < cmdArr.length; i++){
                        taskName += cmdArr[i];
                    }
                    task = new Todo(taskName);


                } else if (cmdArr[1].equals("deadline")) {
                    // >add deadline name /by dueDate
                    if (!Arrays.asList(cmdArr).contains("/by")) {
                        throw new NoDueTimeException();
                    } else if(cmdArr[2].equals("/by")){
                        throw new NoTaskNameException();
                    } else {
                        int by = Arrays.asList(cmdArr).indexOf("/by");
                        for (int i = 2; i < by; i++) {
                            taskName += cmdArr[i];
                        }
                        String dt = "";
                        for (int i = by + 1; i < cmdArr.length; i++){
                            dt += cmdArr[i] + " ";
                        }
                        task = new Deadline(taskName,dt.substring(0,dt.length()-1));
                    }


                } else if (cmdArr[1].equals("event")){
                    // >add event name /from sdt /to edt
                    if (!Arrays.asList(cmdArr).contains("/from")) {
                        throw new NoStartTimeException();
                    } else if(!Arrays.asList(cmdArr).contains("/to")){
                        throw new NoEndTimeException();
                    } else if(cmdArr[2].equals("/from")){
                        throw new NoTaskNameException();
                    } else {
                        int start = Arrays.asList(cmdArr).indexOf("/from");
                        int end = Arrays.asList(cmdArr).indexOf("/to");
                        for (int i = 2; i < start; i++) {
                            taskName += cmdArr[i];
                        }
                        String sdt = "";
                        for (int i = start + 1; i < end; i++){
                            sdt += cmdArr[i] + " ";
                        }
                        String edt = "";
                        for (int i = end + 1; i < cmdArr.length; i++){
                            edt += cmdArr[i];
                        }
                        task = new Event(taskName, sdt.substring(0,sdt.length()-1), edt.substring(0,edt.length()-1));
                    }
                } else {
                    throw new NoTaskTypeException();
                }
            }
            cmd = new AddCommand(task);


        } else if (cmdArr[0].equals("delete")){
            int taskNum;
            try {
                taskNum = Integer.parseInt(cmdArr[1]);
            } catch (NumberFormatException e) {
                throw new NoSuchTaskException();
            }
            cmd = new DeleteCommand(taskNum);


        } else if (cmdArr[0].equals("mark")){
            boolean isMark = true;
            int taskNum;
            try {
                taskNum = Integer.parseInt(cmdArr[1]);
            } catch (NumberFormatException e) {
                throw new NoSuchTaskException();
            }
            cmd = new MarkCommand(isMark, taskNum);
        } else if (cmdArr[0].equals("unmark")){
            boolean isMark = false;
            int taskNum;
            try {
                taskNum = Integer.parseInt(cmdArr[1]);
            } catch (NumberFormatException e) {
                throw new NoSuchTaskException();
            }
            cmd = new MarkCommand(isMark, taskNum);
            // mark command in tasklist need to change from unmark and mark to this boolean

        } else {
            System.out.println("Sorry, I don't understand.\nBut don't worry, help is here!\n");
        }
        return cmd;
    }


}
