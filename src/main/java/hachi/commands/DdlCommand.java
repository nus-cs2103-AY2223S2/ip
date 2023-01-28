package hachi.commands;
import hachi.main.HachiExceptions;
import hachi.main.TaskList;
import hachi.tasks.Ddl;
import hachi.main.Ui;
import hachi.main.Storage;
import java.time.format.DateTimeParseException;

public class DdlCommand extends Command {
    private String input;

    public DdlCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            int index_ddl = input.indexOf("/");
            if (input.length() > 9 && !input.contains("/")) {
                throw new HachiExceptions("☹ Ohno! seems like you forgot to put a deadline.");
            }
            if (index_ddl - 1 < 9) {
                throw new HachiExceptions("☹ Ohno! The description cannot be empty.");
            }
            Ddl ddlTask = new Ddl(input.substring(9, index_ddl - 1), input.substring(index_ddl + 4, input.length()));
            System.out.println("   okie dokie. I've added this task:" + "\n" + ddlTask);
            toDoList.add(ddlTask);
            storage.saveTaskList(toDoList);
            System.out.println("   Now you have " + toDoList.size() + " tasks in the list.");
        } catch (HachiExceptions e) {
            System.out.println(e.getMessage());
        }
        catch (DateTimeParseException e1) {
            System.out.println("   Key in deadline in the format of yyyy-mm-dd");
        }
    }

}

