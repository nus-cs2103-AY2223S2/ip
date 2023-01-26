import exception.TreeBotException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TreeBot treeBot = new TreeBot("data/treebot.txt");
        treeBot.run();

    }
}
