package wtd.command;

import java.util.ArrayList;
import java.util.Arrays;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;
import wtd.task.Todo;

public class CreateTodo extends Command {
    protected static ArrayList<String> aliases = new ArrayList<>(Arrays.asList("todo", "t"));
    private String desc;
   
   public CreateTodo(String desc) {
      this.desc = desc;
   }

    @Override
    public String getCommand() {
        return "todo";
    }

   @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException {
        Integer size = tasks.size();
        tasks.add(new Todo(desc, false));
        assert size + 1 == tasks.size();
        return ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }

    public static boolean checkAlias(String alias) {
       return aliases.contains(alias);
    } 
}
