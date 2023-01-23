package UserCommands;

import java.util.Scanner;

import Features.DukeException;
import Features.TaskList;

abstract class Command {
    public abstract TaskList handle(Scanner userScan, TaskList taskList) throws DukeException;
}
