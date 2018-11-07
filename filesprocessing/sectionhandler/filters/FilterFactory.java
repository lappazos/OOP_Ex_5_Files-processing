package filesprocessing.sectionhandler.filters;

import filesprocessing.sectionhandler.InvalidSubSectionLineException;
import filesprocessing.sectionhandler.filters.filenamefilters.*;
import filesprocessing.sectionhandler.filters.propertyfilters.*;
import filesprocessing.sectionhandler.filters.sizefilters.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Factory singleton for creating filters
 *
 * @author alontron lioraryepaz
 */
public class FilterFactory {

    /**
     * the singleton instance
     */
    private static final FilterFactory instance = new FilterFactory();

    private static final String ALL = "all";
    private static final String GREATER_THAN = "greater_than";
    private static final String BETWEEN = "between";
    private static final String SMALLER_THAN = "smaller_than";
    private static final String FILE = "file";
    private static final String CONTAINS = "contains";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";
    private static final String WRITABLE = "writable";
    private static final String EXECUTABLE = "executable";
    private static final String HIDDEN = "hidden";
    private static final String NOT = "NOT";

    private static final String SEPARATION_KEY = "#";

    private static final int NUMBER_OF_ARGS_DEFAULT_FILTER = 1;
    private static final int NUMBER_OF_ARGS_BETWEEN_FILTER = 2;

    /**
     * the defult all filter
     */
    private static final Filter DEFAULT_FILTER = new AllFilter();

    /**
     * singleton private constructor
     */
    private FilterFactory() {
    }

    /**
     * @return singleton instance
     */
    public static FilterFactory getInstance() {
        return instance;
    }

    /**
     * The factory method
     *
     * @param input the line of the filter sub-section
     * @return the created filter
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    public Filter createFilter(String input) throws InvalidSubSectionLineException {
        String[] parameters = input.split(SEPARATION_KEY);
        //we can assume that every index we'll approach in the parameters list will be exist, according
        //to the filter type
        Filter filter = filterChooser(parameters);

        if (parameters[parameters.length - 1].equals(NOT)) {
            return new NotFilter(filter);
        }

        return filter;
    }

    /**
     * choose the correct filter
     *
     * @param parameters line divided into its different parts
     * @return the created filter
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    private Filter filterChooser(String[] parameters) throws InvalidSubSectionLineException {
        String filterName = parameters[0];

        switch (filterName) {
            case ALL:
                return DEFAULT_FILTER;

            case GREATER_THAN:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                return new GreaterThanFilter(DEFAULT_FILTER, parameters[1], false);

            case BETWEEN:
                validateNArguments(parameters, NUMBER_OF_ARGS_BETWEEN_FILTER);
                return new BetweenFilter(DEFAULT_FILTER, parameters[1], parameters[2]);

            case SMALLER_THAN:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                Filter biggerThan = new GreaterThanFilter(DEFAULT_FILTER, parameters[1], true);
                return new NotFilter(biggerThan);

            case FILE:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                return new FileFilter(DEFAULT_FILTER, parameters[1]);

            case CONTAINS:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                return new ContainsFilter(DEFAULT_FILTER, parameters[1]);

            case PREFIX:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                return new PrefixFilter(DEFAULT_FILTER, parameters[1]);

            case SUFFIX:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                return new SuffixFilter(DEFAULT_FILTER, parameters[1]);

            case WRITABLE:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                return new WritableFilter(DEFAULT_FILTER, parameters[1]);

            case EXECUTABLE:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                return new ExecutableFilter(DEFAULT_FILTER, parameters[1]);

            case HIDDEN:
                validateNArguments(parameters, NUMBER_OF_ARGS_DEFAULT_FILTER);
                return new HiddenFilter(DEFAULT_FILTER, parameters[1]);

            default:
                throw new InvalidSubSectionLineException();
        }
    }

    /**
     * @return default all filter
     */
    public Filter getDefault() {
        return DEFAULT_FILTER;
    }

    /**
     * check if the number of args is correct
     *
     * @param parameters the args
     * @param n          desired number of args
     * @throws InvalidSubSectionLineException case - indicates wrong parameters per sub-section
     */
    private void validateNArguments(String[] parameters, int n) throws InvalidSubSectionLineException {

        List<String> params = new LinkedList<String>(Arrays.asList(parameters));
        params.remove(NOT);

        if (params.size() - 1 != n) {
            throw new InvalidSubSectionLineException();
        }
    }
}
