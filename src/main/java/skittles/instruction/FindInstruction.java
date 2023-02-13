package skittles.instruction;

import java.util.ArrayList;

import skittles.Task;
import skittles.ListOfStuff;


public class FindInstruction extends Instruction {

    private final ListOfStuff skittlesList;

    public FindInstruction(ListOfStuff dukeList) {
        this.skittlesList = dukeList;
    }
    @Override
    public String getResponse(String input) {
        if (input.split(" ", 2).length == 1) {
            return "Oops! Looks like you are missing the keyword you wish to search! Try again :-)";
        }
        if (input.split(" ", 2).length > 2) {
            return "Oops! Looks like you are searching for multiple keywords! Try again with one keyword:-)";
        }
        String[] information = input.split(" ", 2);
        String output = "Here are the matching tasks in your list:\n";
        String keyword = information[1];
        ArrayList<Task> results = skittlesList.findInList(keyword);
        for (int i = 1; i <= results.size(); i++) {
            output = output.concat(i + "." + results.get(i - 1).toString() + "\n");
        }
        if (!results.isEmpty()) {
            return output;
        } else {
            return "There are no such matches in your list!";
        }
    }
}
