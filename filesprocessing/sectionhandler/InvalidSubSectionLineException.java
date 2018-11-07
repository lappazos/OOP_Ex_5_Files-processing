package filesprocessing.sectionhandler;

/**
 * Exception indicates wrong parameters per sub-section
 */
public class InvalidSubSectionLineException extends Exception {

    /**
     * the default constructor
     */
    public InvalidSubSectionLineException() {
    }

    /**
     * Constructor - is here for modularity purposes in case someone would like to override the default message
     *
     * @param innerException cause to be sent to the the exception
     */
    public InvalidSubSectionLineException(Exception innerException) {
        super(innerException);
    }
}
