package filesprocessing.sectionhandler;

import filesprocessing.sectionhandler.filters.Filter;
import filesprocessing.sectionhandler.filters.FilterFactory;
import filesprocessing.sectionhandler.orders.Order;
import filesprocessing.sectionhandler.orders.OrderFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * class represent section of commands
 *
 * @author alontron lioraryepaz
 */
public class Section {

    private static final int FILTER_SECTION_LINE = 1;
    private static final int ORDER_SECTION_LINE = 3;
    private final String filterLine;
    private final String orderLine;
    private final int sectionLineIndex;

    private Filter filter;
    private Order order;

    /**
     * Constructor
     *
     * @param filter           filter line
     * @param order            order line
     * @param sectionLineIndex index of first line of the section
     */
    public Section(String filter, String order, int sectionLineIndex) {
        this.sectionLineIndex = sectionLineIndex;
        filterLine = filter;
        orderLine = order;
    }

    /**
     * creates instances of the filters & orders plus check for exceptions
     *
     * @return list of warnings
     */
    public List<Integer> initialize() {

        ArrayList<Integer> warnings = new ArrayList<Integer>();

        try {

            filter = FilterFactory.getInstance().createFilter(filterLine);
        } catch (InvalidSubSectionLineException e) {
            filter = FilterFactory.getInstance().getDefault();
            warnings.add(sectionLineIndex + FILTER_SECTION_LINE);
        }


        if (orderLine == null) {
            order = OrderFactory.getInstance().getDefault();
        } else {
            try {
                order = OrderFactory.getInstance().createOrder(orderLine);
            } catch (InvalidSubSectionLineException e) {
                order = OrderFactory.getInstance().getDefault();
                warnings.add(sectionLineIndex + ORDER_SECTION_LINE);
            }
        }

        return warnings;
    }

    /**
     * perform the actual filtration and order sort of the files
     *
     * @param files files list to perform on
     * @return filtered & sorted files list
     */
    public List<File> perform(List<File> files) {
        List<File> filteredFilesList = new ArrayList<>();
        for (File file : files) {
            if (filter.filter(file)) {
                filteredFilesList.add(file);
            }
        }

        order.orderFiles(filteredFilesList);

        return filteredFilesList;
    }

}
