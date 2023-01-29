package ui;
import tasks.TaskList;
import exception.DukeException;

public class StringParser {
    public boolean parse(String answer, TaskList taskList) throws DukeException {
        String[] wordArr = answer.split(" ", 2);
        if (wordArr[0].equals("mark")) {
            int index = Integer.parseInt(wordArr[1]);
            System.out.println(taskList.markTask(index - 1));
            //submit index to hardrive object
        } else if (wordArr[0].equals("unmark")) {
            int index = Integer.parseInt(wordArr[1]);
            System.out.println(taskList.unmarkTask(index - 1));
        } else if (answer.equals("bye")) {
            System.out.println("Goodbye");
            return true;
        } else if (wordArr[0].equals("delete")) {
            String index = wordArr[1];
            System.out.println(taskList.deleteTask(index));
        } else if (answer.equals("list")) { //return list of tasks
            System.out.println(taskList.listTasks());
        } else if (wordArr[0].equals("todo")) {
            this.parseTodo(answer);
            String desc = wordArr[1];
            System.out.println(taskList.addTask(desc));
        } else if (wordArr[0].equals("deadline")) {
            String desc = wordArr[1].split(" /by ")[0];
            String by = wordArr[1].split(" /by ")[1];
            System.out.println(taskList.addTask(desc, by));

        } else if (wordArr[0].equals("event")) {
            String desc = wordArr[1].split(" ", 2)[0];
            String from = wordArr[1].split(" /from ")[1].split(" /to ")[0];
            String to = wordArr[1].split(" /to ", 2)[1];
            System.out.println(taskList.addTask(desc, from, to));
        } else{
            throw new DukeException("Sorry, I don't know that command");
        }
        return false;
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
