package core;

import javafx.application.Application;
import javafx.application.Platform;
import shigure.Cli;
import task.Parser;

/**
 * Initializer & execution entry-point of the Miki project.
 */
public class Init {
    /**
     * Main function of the Miki project.
     *
     * @param args command-line arguments from the initial program call.
     */
    public static void main(String[] args) {
        assert args != null : "Program arguments should be non-null";
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
            while (!Parser.isExitString(cmdLine)) {
                cmdLine = cli.readLine();
                miki.respond(cmdLine);
            }
        }
        Platform.exit();
    }
}
