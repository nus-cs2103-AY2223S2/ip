package commands;

import data.MyData;
public class Add extends Commands{
    private final String description;

    public Add(String description) {
        this.description = description;
    }

    public void execute(MyData data) {
        System.out.print(getLine() +
                "    added: " + description + "\n" +
                getLine());
    }
}
