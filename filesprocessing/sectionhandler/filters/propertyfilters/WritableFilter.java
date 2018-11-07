package filesprocessing.sectionhandler.filters.propertyfilters;

import filesprocessing.sectionhandler.InvalidSubSectionLineException;
import filesprocessing.sectionhandler.filters.Filter;

import java.io.File;

/**
 * Filter class of type Writable - checks if a certain file has writing permissions.
 */
public class WritableFilter extends FilePropertyFilter {

    /**
     * Constructor
     *
     * @param decoratedFilter    the filter that is being wrapped by this filter
     * @param shouldPropertyBeOn the filter parameter
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    public WritableFilter(Filter decoratedFilter, String shouldPropertyBeOn) throws InvalidSubSectionLineException {
        super(decoratedFilter, shouldPropertyBeOn);
    }

    @Override
    public boolean filter(File file) {
        return super.filter(file) && filterLogic(file.canWrite());
    }
}
