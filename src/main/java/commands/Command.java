package commands;

import java.util.Scanner;

import features.DukeException;
import features.TaskList;

abstract class Command {
    public abstract TaskList handle(Scanner userScan, TaskList taskList) throws DukeException;
}
