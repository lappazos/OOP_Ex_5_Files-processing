package filesprocessing.sectionhandler.filters.filenamefilters;

import filesprocessing.sectionhandler.filters.Filter;
import filesprocessing.sectionhandler.filters.FilterDecorator;

import java.io.File;

/**
 * Filter class of type File - checks if a certain string is the file name
 */
public class FileFilter extends FilterDecorator {

    private String comparisionValue;

    /**
     * Constructor
     *
     * @param decoratedFilter  the filter that is being wrapped by this filter
     * @param comparisionValue the string we are looking for
     */
    public FileFilter(Filter decoratedFilter, String comparisionValue) {
        super(decoratedFilter);
        this.comparisionValue = comparisionValue;
    }

    @Override
    public boolean filter(File file) {
        return super.filter(file) && file.getName().equals(comparisionValue);
    }
}
