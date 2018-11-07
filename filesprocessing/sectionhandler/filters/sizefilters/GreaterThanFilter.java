package filesprocessing.sectionhandler.filters.sizefilters;

import filesprocessing.sectionhandler.InvalidSubSectionLineException;
import filesprocessing.sectionhandler.filters.Filter;
import filesprocessing.sectionhandler.filters.FilterDecorator;

import java.io.File;

/**
 * Filter class of type size - check if a certain file is bigger than X KB.
 */
public class GreaterThanFilter extends FilterDecorator {

    //The number of bytes in a kilobyte.
    private static final int ONE_KB = 1024;
    private static final double MIN_SIZE = 0;

    private double minSize;
    // whether the filter is < or =<
    private boolean isInclusive;

    /**
     * Constructor
     *
     * @param decoratedFilter the filter that is being wrapped by this filter
     * @param minSize         the size factor
     * @param isInclusive     whether the filter is < or =<
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    public GreaterThanFilter(Filter decoratedFilter, String minSize, boolean isInclusive) throws InvalidSubSectionLineException {
        super(decoratedFilter);
        this.isInclusive = isInclusive;

        try {
            this.minSize = Double.parseDouble(minSize);
        } catch (NumberFormatException e) {
            throw new InvalidSubSectionLineException(e);
        }

        if (this.minSize < MIN_SIZE) {
            throw new InvalidSubSectionLineException();
        }

    }

    @Override
    public boolean filter(File file) {
        if (!super.filter(file)) {
            return false;
        }

        float fileSizeInKB = ((float) file.length() / ONE_KB);
        if (!isInclusive) {
            return fileSizeInKB > minSize;
        }
        return fileSizeInKB >= minSize;
    }
}
