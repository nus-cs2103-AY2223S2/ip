package domain.entities.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.exceptions.DisposableException;
import core.singletons.Singletons;
import core.utils.TokenUtilities;

/**
 * This class shall contain several executable objects, hence it shall be
 * considered 'nestable' executable.
 */
public class NestedCommandableObject implements Commandable, Disposable {
    /**
     * The commands that are executed in sequence before executing the
     * tokenedCommands in an execution iteration.
     * <p>It shall be noted that this
     * iteration may not continue after executing a certain command, depending
     * on the command's return value upon execution.</p>
     */
    private final List<Commandable> preCommandables;
    /**
     * The commands that are executed in the sequence after executing the
     * keywordCommands.
     * <p>It shall be noted that this
     * iteration may not continue after executing a certain command, depending
     * on the command's return value upon execution.</p>
     */
    private final List<Commandable> postCommandables;
    /**
     * The commands that are executed in the middle. They will be matched
     * against the keyword to decide whether if they wil be executed.
     * <p>It shall be noted that this
     * iteration may not continue after executing a certain command, depending
     * on the command's return value upon execution.</p>
     */
    private final Map<String, IdentifiedCommandable> identifiedExecutables;
    /**
     * The list of disposables that will be disposed when this object is
     * disposed. This can be used to do some bookkeeping such as saving the
     * data to the disk.
     */
    private final List<Disposable> disposables = new ArrayList<>();
    /**
     * The writer to which the error messages will be written.
     */
    private final Writable errorWriter;

    /**
     * Instantiates a new TokensManager.
     *
     * @param preCommandables       the executables to be executed first in an
     *                              iteration.
     * @param postCommandables      the executables to be executed last in an
     *                              iteration.
     * @param identifiedExecutables the commands to be executed in the middle in
     *                              an iteration, with a mapping from tokens to
     *                              the commands themselves.
     * @param errorWriter           the writer to which the error messages shall
     *                              be written.
     */
    public NestedCommandableObject(
            ArrayList<Commandable> preCommandables,
            ArrayList<Commandable> postCommandables,
            HashMap<String, IdentifiedCommandable> identifiedExecutables,
            Writable errorWriter
    ) {
        this.preCommandables = preCommandables;
        this.postCommandables = postCommandables;
        this.identifiedExecutables = identifiedExecutables;
        this.errorWriter = errorWriter;
    }

    /**
     * Instantiates a new TokensManager with all the values defaulting to empty
     * collections.
     *
     * @param errorWriter the writer to which the error messages shall be
     *                    written.
     */
    public NestedCommandableObject(Writable errorWriter) {
        this(new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
                errorWriter);
    }

    /**
     * Registers a commandable to be executed before the tokened commands.
     *
     * @param commandable the commandable to be put into the preCommand loop.
     */
    public void registerPreExecutable(Commandable commandable) {
        this.preCommandables.add(commandable);
    }

    /**
     * Registers a commandable to be exe
     *
     * @param commandable the commandable to be put into the postCommand loop.
     */
    public void registerPostExecutable(Commandable commandable) {
        this.postCommandables.add(commandable);
    }

    /**
     * Registers a keyword executable. A keyword executable is a KeywordCommand that
     * will be activated if the line inputted has the keyword corresponding
     * to that executable.
     *
     * @param executable the executable to be put into the keyword executable loop.
     */
    public void registerIdentifiableExecutable(IdentifiedCommandable executable) {
        final String key = executable.getId();
        identifiedExecutables.put(key, executable);
    }

    /**
     * Runs a list of commandables.
     *
     * @param commandables the list of commandables to be run.
     * @param tokens       the array of tokens to be passed to the list of commandables
     *                     as argument.
     * @return the ExitStatus of executing the commandables.
     */
    private ExitStatus runExecutablesList(List<Commandable> commandables,
                                          String[] tokens) {
        ExitStatus status = ExitStatus.continueExecute;
        for (Commandable commandable : commandables) {
            status = commandable.execute(tokens);
            if (status != ExitStatus.continueExecute) {
                return status;
            }
        }
        return status;
    }


    /**
     * Registers a disposable to be disposed when this object is disposed.
     *
     * @param disposable the disposable to be registered.
     */
    public void registerDisposable(Disposable disposable) {
        disposables.add(disposable);
    }


    @Override
    public ExitStatus execute(String[] tokens) {
        ExitStatus status = runExecutablesList(preCommandables, tokens);
        if (status != ExitStatus.continueExecute) {
            return status;
        }
        if (!identifiedExecutables.isEmpty()) {
            final Commandable commandable = identifiedExecutables.get(tokens[0]);
            if (commandable != null) {
                final String[] newTokens =
                        Singletons.get(TokenUtilities.class).removeFirst(tokens);
                status = commandable.execute(newTokens);
                if (status != ExitStatus.continueExecute) {
                    return status;
                }
            }
        }
        return runExecutablesList(postCommandables, tokens);
    }

    @Override
    public void dispose() {
        for (Disposable disposable : disposables) {
            try {
                disposable.dispose();
            } catch (DisposableException e) {
                errorWriter.write(e.getMessage());
            }
        }
    }
}
