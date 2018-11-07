package filesprocessing.commandfile;

/**
 * Exception indicates the command file subsections headlines are wrong
 */
class BadSubsectionException extends CommandFileException {

    private static final long serialVersionUID = 1L;
    private final static String BAD_SUBSECTION_ERROR = "Bad Subsection name";

    /**
     * Default Constructor
     */
    BadSubsectionException() {
        super(BAD_SUBSECTION_ERROR);
    }

    /**
     * Constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param message message to be sent to the the exception
     */
    BadSubsectionException(String message) {
        super(message);
    }
}
