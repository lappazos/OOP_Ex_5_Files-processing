package filesprocessing.sectionhandler.filters.sizefilters;

import filesprocessing.sectionhandler.InvalidSubSectionLineException;
import filesprocessing.sectionhandler.filters.Filter;
import filesprocessing.sectionhandler.filters.FilterDecorator;
import filesprocessing.sectionhandler.filters.NotFilter;

import java.io.File;

/**
 * Filter class of type size - check if a certain file is between X & Y KB.
 */
public class BetweenFilter extends FilterDecorator {

    private Filter betweenFilter;

    /**
     * Constructor
     *
     * @param decoratedFilter the filter that is being wrapped by this filter
     * @param minValue        minimum size factor
     * @param maxValue        maximum size factor
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    public BetweenFilter(Filter decoratedFilter, String minValue, String maxValue)
            throws InvalidSubSectionLineException {
        super(decoratedFilter);

        Filter biggerThanMax = new GreaterThanFilter(decoratedFilter, maxValue, false);
        Filter notBiggerThanMax = new NotFilter(biggerThanMax);
        this.betweenFilter = new GreaterThanFilter(notBiggerThanMax, minValue, true);

        if (Double.parseDouble(minValue) > Double.parseDouble(maxValue)) {
            throw new InvalidSubSectionLineException();
        }
    }

    @Override
    public boolean filter(File file) {
        return betweenFilter.filter(file);
    }
}
