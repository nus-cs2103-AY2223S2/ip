package Ava.Commands;

import Ava.Storage;
import Ava.TaskList;

public class Exit implements AvaCommand {

    @Override
    public boolean run(TaskList t, Storage s){
        return false;
    }

    @Override
    public String output(String formatSpace){
        return "Will Never reach here";
    }
}
