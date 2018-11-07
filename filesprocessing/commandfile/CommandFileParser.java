package filesprocessing.commandfile;

import filesprocessing.sectionhandler.Section;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class handles parsing the command file into its different sections, plus handling potential errors
 *
 * @author alontron lioraryepaz
 */
public class CommandFileParser {

    /**
     * The single instance
     */
    private static final CommandFileParser instance = new CommandFileParser();

    //Headline names
    private final static String FILTER = "FILTER";
    private final static String ORDER = "ORDER";

    //constants represents relations between file indexes
    private static final int LINES_TO_POTENTIAL_END = 2;
    private static final int LINES_FROM_ORDER_HEADLINE_ARG_TO_START = 2;
    private static final int LINES_FROM_ORDER_ARG_TO_START = 3;

    /**
     * counter of the section lines
     */
    private int frameSectionIndex;

    /**
     * command line section list
     */
    private ArrayList<Section> sectionList;

    private String tempFilterInput;
    private String tempOrderInput;

    /**
     * the singleton constructor
     */
    private CommandFileParser() {
        frameSectionIndex = 1;
        sectionList = new ArrayList<Section>();
    }

    /**
     * @return singleton instance
     */
    public static CommandFileParser getInstance() {
        return instance;
    }

    /**
     * Creates a Command File parser
     *
     * @param path The path to the new file
     * @throws IOException          In the case of an error occurred while reading the file
     * @throws CommandFileException If command file is not valid
     */
    public List<Section> parse(String path) throws IOException, CommandFileException {

        List<String> lines = Files.readAllLines(Paths.get(path));

        for (int i = 0; i < lines.size(); i++) {

            String currentLine = lines.get(i);

            switch (frameSectionIndex) {
                case 1:
                    parseFirstSectionLine(lines, i, currentLine);
                    break;
                case 2:
                    tempFilterInput = currentLine;
                    frameSectionIndex++;
                    break;
                case 3:
                    parseThirdSectionLine(lines, i, currentLine);
                    break;
                case 4:
                    tempOrderInput = currentLine;
                    resetSectionReading(i - LINES_FROM_ORDER_ARG_TO_START);
                    break;
            }
        }

        ArrayList<Section> tempSectionList = new ArrayList<>(sectionList);
        resetParser();

        return tempSectionList;
    }

    /**
     * Reset parser data members
     */
    private void resetParser() {
        sectionList.clear();
        frameSectionIndex = 1;
        tempFilterInput = null;
        tempOrderInput = null;
    }

    /**
     * in charge of parsing the first line of each section
     *
     * @param lines       the content of the command file
     * @param i           the line number
     * @param currentLine the current i line content
     * @throws BadFormatException case - indicates the command file structure is broken
     */
    private void parseFirstSectionLine(List<String> lines, int i, String currentLine) throws BadFormatException {
        if (!currentLine.equals(FILTER)) {
            resetParser();
            throw new BadFormatException();
        }
        if (i + LINES_TO_POTENTIAL_END >= lines.size()) {
            resetParser();
            throw new BadFormatException();
        }
        frameSectionIndex++;
    }

    /**
     * in charge of parsing the third line of each section
     *
     * @param lines       the content of the command file
     * @param i           the line number
     * @param currentLine the current i line content
     * @throws BadSubsectionException case - indicates the command file subsections headlines are wrong
     */
    private void parseThirdSectionLine(List<String> lines, int i, String currentLine) throws BadSubsectionException {
        if (!currentLine.equals(ORDER)) {
            resetParser();
            throw new BadSubsectionException();
        }
        if (i + 1 < lines.size()) {
            if (lines.get(i + 1).equals(FILTER)) {
                resetSectionReading(i - LINES_FROM_ORDER_HEADLINE_ARG_TO_START);
            } else {
                frameSectionIndex++;
            }
        } else {
            resetSectionReading(i - LINES_FROM_ORDER_HEADLINE_ARG_TO_START);
        }
    }

    /**
     * Creates new section and set to start read the next section
     *
     * @param sectionLineIndex current line index
     */
    private void resetSectionReading(int sectionLineIndex) {
        frameSectionIndex = 1;
        sectionList.add(new Section(tempFilterInput, tempOrderInput, sectionLineIndex + 1));

        tempOrderInput = null;
    }
}
