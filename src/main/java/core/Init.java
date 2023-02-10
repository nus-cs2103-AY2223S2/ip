package core;

import javafx.application.Application;
import javafx.application.Platform;
import shigure.Cli;
import task.Parser;

public class Init {
    public static void main(String[] args) {
        boolean hasAsciiOnly = false;
        boolean hasNoAutoload = false;
        boolean hasCli = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--ascii-only")) {
                hasAsciiOnly = true;
            }
            if (args[i].equals("--no-autoload")) {
                hasNoAutoload = true;
            }
            if (args[i].equals("--cli")) {
                hasCli = true;
            }
        }

        if (!hasCli) {
            Application.launch(MikiApp.class, args);
        } else {
            Cli cli = new Cli(hasAsciiOnly);
            Miki miki = new Miki(cli, hasNoAutoload);
            String cmdLine = "";
            while (!Parser.isExitCommand(cmdLine)) {
                cmdLine = cli.readLine();
                miki.respond(cmdLine);
            }
        }
        Platform.exit();
    }
}
