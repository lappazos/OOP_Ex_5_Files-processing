package filesprocessing.commandfile;

/**
 * General exception to indicate error in command file
 */
public abstract class CommandFileException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param errorType message to be sent to the the exception
     */
    CommandFileException(String errorType) {
        super(errorType);
    }

}
