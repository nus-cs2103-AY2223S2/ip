package ui;
import command.*;
import tasks.TaskList;
import exception.DukeException;

public class StringParser {
    public Command parse(String answer, TaskList taskList) throws DukeException {
        String[] wordArr = answer.split(" ", 2);
        if (wordArr[0].equals("mark")) {
            int index = Integer.parseInt(wordArr[1]);
            return new MarkCommand(index - 1);
        } else if (wordArr[0].equals("unmark")) {
            int index = Integer.parseInt(wordArr[1]);
            return new UnmarkCommand(index - 1);
        } else if (answer.equals("bye")) {
            return new ByeCommand();
        } else if (wordArr[0].equals("delete")) {
            int index = Integer.parseInt(wordArr[1]);
            return new DeleteCommand(index - 1);
        } else if (answer.equals("list")) { //return list of tasks
            return new ListCommand();
        } else if (wordArr[0].equals("todo")) {
            this.parseTodo(answer);
            String desc = wordArr[1];
            return new TodoCommand(desc);
        } else if (wordArr[0].equals("deadline")) {
            String desc = wordArr[1].split(" /by ")[0];
            String by = wordArr[1].split(" /by ")[1];
            return new DeadlineCommand(desc, by);
        } else if (wordArr[0].equals("event")) {
            String desc = wordArr[1].split(" ", 2)[0];
            String from = wordArr[1].split(" /from ")[1].split(" /to ")[0];
            String to = wordArr[1].split(" /to ", 2)[1];
            return new EventCommand(desc, from, to);
        } else if (wordArr[0].equals("find")){
            String keyWord = wordArr[1];
            return new FindCommand(keyWord);
        } else{
            throw new DukeException("Sorry, I don't know that command");
        }
    }
    public void parseTodo(String input) throws DukeException{
        try{
            String[] wordArr = input.split(" ", 2);
            String desc = wordArr[1];
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Sorry, todo command needs description");
        }
    }
}
