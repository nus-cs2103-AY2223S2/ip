package skittles.instruction;

import skittles.ListOfStuff;

import java.util.List;

public class ListInstruction extends Instruction {

    private final ListOfStuff skittlesList;

    public ListInstruction(ListOfStuff skittlesList) {
        this.skittlesList = skittlesList;
    }

    @Override
    public String getResponse(String input) {
        return skittlesList.displayLst();
    }
}
