package aqua.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.exception.LoadException;
import aqua.exception.ProcedureExecutionException;
import aqua.logic.parser.ArgumentParser;
import aqua.logic.parser.CommandLineInputParser;
import aqua.manager.AppManager;


public class Loader {
    public static void load(Path path, AppManager manager) throws LoadException {
        try (Scanner scanner = new Scanner(path.toFile())) {
            CommandLineInputParser parser = new CommandLineInputParser(new ArgumentParser());
            while (scanner.hasNextLine()) {
                parser.parse(scanner.nextLine()).getDispatcher(manager).dispatch();
            }
        } catch (IllegalSyntaxException syntaxEx) {
            throw new LoadException(syntaxEx);
        } catch (ProcedureExecutionException proEx) {
            throw new LoadException(proEx);
        } catch (FileNotFoundException fnfEx) {
            return;
        }
    }


    public static void load(String pathString, AppManager manager) throws LoadException {
        load(Paths.get(pathString), manager);
    }
}
