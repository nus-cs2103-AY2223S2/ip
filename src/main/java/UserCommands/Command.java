package UserCommands;

import Features.DukeException;
import Features.TaskList;

import java.util.Scanner;

abstract class Command {
    public abstract TaskList handle(Scanner userScan, TaskList taskList) throws DukeException;
}
