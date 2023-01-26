package domain.entities.core;

import core.exceptions.DisposableException;
import core.singletons.Singletons;
import core.utils.TokenUtilities;

import java.util.*;

/**
 * This class shall contain several executable objects, hence it shall be
 * considered 'nestable' executable.
 */
public class NestableExecutableObject implements Executable, Disposable {
    /**
     * Instantiates a new TokensManager.
     *
     * @param preExecutables        the executables to be executed first in an
     *                              iteration.
     * @param postExecutables       the executables to be executed last in an
     *                              iteration.
     * @param identifiedExecutables the commands to be executed in the middle in
     *                              an iteration, with a mapping from tokens to
     *                              the commands themselves.
     * @param errorWriter           the writer to which the error messages shall
     *                              be written.
     */
    public NestableExecutableObject(
            ArrayList<Executable> preExecutables,
            ArrayList<Executable> postExecutables,
            HashMap<String, IdentifiableExecutable> identifiedExecutables,
            Writable errorWriter
    ) {
        this.preExecutables = preExecutables;
        this.postExecutables = postExecutables;
        this.identifiedExecutables = identifiedExecutables;
        this.errorWriter = errorWriter;
    }

    /**
     * Instantiates a new TokensManager with all the values defaulting to empty
     * collections.
     */
    public NestableExecutableObject(Writable errorWriter) {
        this(new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
                errorWriter);
    }

    /**
     * The commands that are executed in sequence before executing the
     * tokenedCommands in an execution iteration.
     * <p>It shall be noted that this
     * iteration may not continue after executing a certain command, depending
     * on the command's return value upon execution.</p>
     */
    private final List<Executable> preExecutables;

    /**
     * The commands that are executed in the sequence after executing the
     * keywordCommands.
     * <p>It shall be noted that this
     * iteration may not continue after executing a certain command, depending
     * on the command's return value upon execution.</p>
     */
    private final List<Executable> postExecutables;

    /**
     * The commands that are executed in the middle. They will be matched
     * against the keyword to decide whether if they wil be executed.
     * <p>It shall be noted that this
     * iteration may not continue after executing a certain command, depending
     * on the command's return value upon execution.</p>
     */
    private final Map<String, IdentifiableExecutable> identifiedExecutables;

    /**
     * The list of disposables that will be disposed when this object is
     * disposed.
     */
    private final List<Disposable> disposables = new ArrayList<>();

    /**
     * The writer to which the error messages will be written.
     */
    private final Writable errorWriter;

    /**
     * Registers a executable to be executed before the tokened commands.
     *
     * @param executable the executable to be put into the preCommand loop.
     */
    public void registerPreExecutable(Executable executable) {
        this.preExecutables.add(executable);
    }

    /**
     * Registers a executable to be exe
     *
     * @param executable the executable to be put into the postCommand loop.
     */
    public void registerPostExecutable(Executable executable) {
        this.postExecutables.add(executable);
    }

    /**
     * Registers a keyword executable. A keyword executable is a KeywordCommand that
     * will be activated if the line inputted has the keyword corresponding
     * to that executable.
     *
     * @param executable the executable to be put into the keyword executable loop.
     */
    public void registerIdentifiableExecutable(IdentifiableExecutable executable) {
        final String key = executable.getId();
        identifiedExecutables.put(key, executable);
    }

    /**
     * Runs a list of executables.
     *
     * @param executables the list of executables to be run.
     * @param tokens      the array of tokens to be passed to the list of executables
     *                    as argument.
     * @return the ExitStatus of executing the executables.
     */
    private ExitStatus runExecutablesList(List<Executable> executables,
                                          String[] tokens) {
        ExitStatus status = ExitStatus.continueExecute;
        for (Executable executable : executables) {
            status = executable.execute(tokens);
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
        ExitStatus status = runExecutablesList(preExecutables, tokens);
        if (status != ExitStatus.continueExecute) {
            return status;
        }
        if (!identifiedExecutables.isEmpty()) {
            final Executable executable = identifiedExecutables.get(tokens[0]);
            if (executable != null) {
                final String[] newTokens =
                        Singletons.get(TokenUtilities.class).removeFirst(tokens);
                status = executable.execute(newTokens);
                if (status != ExitStatus.continueExecute) {
                    return status;
                }
            }
        }
        return runExecutablesList(postExecutables, tokens);
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
