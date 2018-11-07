package filesprocessing;

import filesprocessing.commandfile.CommandFileParser;
import filesprocessing.commandfile.CommandFileException;
import filesprocessing.sectionhandler.Section;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class - handles the run
 *
 * @author alontron lioraryepaz
 */
public class DirectoryProcessor {

    private static final String ERROR_PREFIX = "ERROR: ";
    private static final String WARNING_PREFIX = "Warning in line ";
    private static final String BAD_COMMAND_FILE_ERROR = "Problem with command file";

    private static final int NUMBER_OF_ARGS = 2;

    /**
     * Program entry point
     *
     * @param args Gets command file path and directory to process
     */
    public static void main(String args[]) {

        List<File> files;

        try {
            files = validateArguments(args);

        } catch (InvalidUsageException e) {
            System.err.println(ERROR_PREFIX + e.getMessage());
            return;
        }

        List<Section> sections;

        try {
            sections = CommandFileParser.getInstance().parse(args[1]);
        } catch (CommandFileException e) {
            System.err.println(ERROR_PREFIX + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println(ERROR_PREFIX + BAD_COMMAND_FILE_ERROR);
            return;
        }

        for (Section section : sections) {

            List<Integer> warnings = section.initialize();
            for (int warning : warnings) {
                System.err.println(WARNING_PREFIX + warning);
            }
            List<File> results = section.perform(files);
            for (File file : results) {
                System.out.println(file.getName());
            }
        }
    }

    /**
     * Check that the argument are valid
     *
     * @param args both arguments
     * @return The files in directory
     * @throws InvalidUsageException If The user called the with invalid parameters
     */
    private static ArrayList<File> validateArguments(String args[]) throws InvalidUsageException {

        if (args.length != NUMBER_OF_ARGS) {
            throw new InvalidNumberOfArgsException();
        }

        final String sourceDirPath = args[0];
        final String commandFilePath = args[1];

        File sourceDir = new File(sourceDirPath);

        if (!sourceDir.isDirectory()) {
            throw new InvalidSourceDirectoryException();
        }

        ArrayList<File> files = readDirectoryFiles(sourceDirPath);

        if (files.isEmpty()) {
            throw new EmptySourceDirectoryException();
        }

        File commandFile = new File(commandFilePath);

        if (!commandFile.isFile()) {
            throw new InvalidCommandFileException();
        }

        return files;
    }

    /**
     * Stores all file names in path in a list.
     *
     * @param dir Path ti directory with files
     * @return List with files
     */
    private static ArrayList<File> readDirectoryFiles(String dir) throws InvalidSourceDirectoryException {
        File folder = new File(dir);
        File[] allFiles = folder.listFiles();

        ArrayList<File> results = new ArrayList<File>();
        if (allFiles != null) {
            for (File file : allFiles) {
                if (file.isFile()) {
                    results.add(file);
                }
            }
        } else {
            throw new InvalidSourceDirectoryException();
        }

        return results;
    }
}
