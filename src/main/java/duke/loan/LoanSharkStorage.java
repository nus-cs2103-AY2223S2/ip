package duke.loan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * LoanSharkStorage handles all writing and reading of loans to persistent data.
 */
public class LoanSharkStorage {
    private static final String SAVE_DIR = "data/loans";
    private static final String LEDGER_NAME = "ledger.txt";
    private static final String ACTIVE_LOANS_NAME_TEMPLATE = "active-loans-%s.txt";
    private static final String RECORD_NAME_TEMPLATE = "record-%s.txt";
    private static final String SAVE_ERROR_MESSAGE = "Loans not saved!";

    /**
     * Saves a collection of LoanAccount objects to external storage.
     * @param loanAccounts collection of LoanAccount objects to save
     * @throws DukeException
     */
    public static void saveAccounts(Collection<LoanAccount> loanAccounts) throws DukeException {
        try {
            Files.createDirectories(Paths.get(SAVE_DIR));
            StringBuilder ledger = new StringBuilder();

            for (LoanAccount loanAccount : loanAccounts) {
                saveLoanAccount(ledger, loanAccount);
            }

            Path ledgerPath = Paths.get(SAVE_DIR, LEDGER_NAME);
            writeDataToFile(ledger.toString(), ledgerPath);
        } catch (IOException e) {
            throw new DukeException(SAVE_ERROR_MESSAGE);
        }
    }

    private static void saveLoanAccount(StringBuilder ledger, LoanAccount loanAccount) throws IOException {
        String holder = loanAccount.getHolder();
        ledger.append(holder).append("\n");
        saveRecord(loanAccount, holder);
    }

    private static Path getActiveLoanFilePath(String holder) {
        return Paths.get(SAVE_DIR, String.format(ACTIVE_LOANS_NAME_TEMPLATE, holder));
    }

    private static void saveRecord(LoanAccount loanAccount, String holder) throws IOException {
        Path recordFilePath = getRecordFilePath(holder);
        writeDataToFile(loanAccount.serializeRecord(), recordFilePath);
    }

    private static Path getRecordFilePath(String holder) {
        return Paths.get(SAVE_DIR, String.format(RECORD_NAME_TEMPLATE, holder));
    }

    private static void writeDataToFile(String data, Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        FileWriter fw = new FileWriter(path.toFile(), false);
        fw.write(data);
        fw.close();
    }

    /**
     * Loads a collection of LoanAccount objects from external storage.
     * @param loanShark LoanShark object that receives loaded LoanAccount objects
     * @throws DukeException
     */
    public static void loadAccounts(LoanShark loanShark) throws DukeException {
        Path ledgerPath = Paths.get(SAVE_DIR, LEDGER_NAME);
        ArrayList<Task> tasks = new ArrayList<Task>();

        if (!Files.exists(ledgerPath)) {
            return;
        }

        try {
            File ledgerFile = ledgerPath.toFile();
            Scanner ledgerScanner = new Scanner(ledgerFile);
            while (ledgerScanner.hasNext()) {
                String holder = ledgerScanner.nextLine();
                File recordFile = getRecordFilePath(holder).toFile();
                Scanner recordScanner = new Scanner(recordFile);
                while (recordScanner.hasNext()) {
                    String loanData = recordScanner.nextLine();
                    if (loanData.isBlank()) {
                        continue;
                    }
                    String[] splitLoanData = loanData.split("[|]");
                    int balance = Integer.parseInt(splitLoanData[1]);
                    int original = Integer.parseInt(splitLoanData[2]);
                    String description = splitLoanData[3];
                    loanShark.addLoan(balance, original, description, holder);
                }
            }
            ledgerScanner.close();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
            throw new DukeException("Unable to load existing loans! Loans save file(s) may be corrupted!\");");
        }
    }
}
