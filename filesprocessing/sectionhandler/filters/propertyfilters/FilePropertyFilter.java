package filesprocessing.sectionhandler.filters.propertyfilters;

import filesprocessing.sectionhandler.InvalidSubSectionLineException;
import filesprocessing.sectionhandler.filters.Filter;
import filesprocessing.sectionhandler.filters.FilterDecorator;

import java.io.File;

/**
 * abstract Filter class of type file properties
 */
abstract class FilePropertyFilter extends FilterDecorator {

    // according to the filter parameters
    private boolean shouldPropertyBeOn;

    private static final String TRUE = "YES";
    private static final String FALSE = "NO";

    /**
     * Constructor
     *
     * @param decoratedFilter    the filter that is being wrapped by this filter
     * @param shouldPropertyBeOn the filter parameter
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    FilePropertyFilter(Filter decoratedFilter, String shouldPropertyBeOn) throws InvalidSubSectionLineException {
        super(decoratedFilter);

        switch (shouldPropertyBeOn) {
            case TRUE:
                this.shouldPropertyBeOn = true;
                break;
            case FALSE:
                this.shouldPropertyBeOn = false;
                break;
            default:
                throw new InvalidSubSectionLineException();
        }
    }

    @Override
    public boolean filter(File file) {
        return decoratedFilter.filter(file);
    }

    /**
     * determine the filter result - according to the performed check and to the parameter
     *
     * @param property the specific property check
     * @return boolean
     */
    boolean filterLogic(boolean property) {

        //not-xor between both boolean because should be the same
        return shouldPropertyBeOn ^ !property;
    }
}
