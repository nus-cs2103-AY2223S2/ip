package skittles.instruction;

import skittles.Data;
import skittles.ListOfStuff;
import skittles.SkittlesException;
import skittles.SortTasksByName;

import java.util.ArrayList;
import java.util.Collections;
import skittles.Task;

public class SortInstruction extends Instruction {
    private static ListOfStuff skittlesList;

    public SortInstruction(ListOfStuff skittlesList) {
        this.skittlesList = skittlesList;
    }

    @Override
    public String getResponse(String input) {
        try {
            ArrayList<Task> sortSkittlesList = Data.loadUpInfo();
            Collections.sort(sortSkittlesList, new SortTasksByName());
            skittlesList = new ListOfStuff(sortSkittlesList, new Data("./data/data.txt"));
            skittlesList.refresh();
            return "Sorted!";
        } catch (SkittlesException e) {
            return "Oops we can't update your list man! Try again!";
        }
    }
}

