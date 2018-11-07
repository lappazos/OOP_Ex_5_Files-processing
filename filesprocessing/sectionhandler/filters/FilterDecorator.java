package filesprocessing.sectionhandler.filters;

import java.io.File;

/**
 * abstract filter decorator class
 */
public abstract class FilterDecorator implements Filter {

    //the decorated filter
    protected Filter decoratedFilter;

    /**
     * Constructor
     *
     * @param decoratedFilter the decorated filter
     */
    public FilterDecorator(Filter decoratedFilter) {
        this.decoratedFilter = decoratedFilter;
    }

    @Override
    public boolean filter(File file) {
        return decoratedFilter.filter(file);
    }
}
