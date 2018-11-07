package filesprocessing.sectionhandler.filters;

import java.io.File;

/**
 * Filter class of type not - return the opposite of the decorated filter
 */
public class NotFilter extends FilterDecorator {

    /**
     * Constructor
     *
     * @param decoratedFilter the decorated filter
     */
    public NotFilter(Filter decoratedFilter) {
        super(decoratedFilter);
    }

    @Override
    public boolean filter(File file) {
        return !super.filter(file);
    }
}
