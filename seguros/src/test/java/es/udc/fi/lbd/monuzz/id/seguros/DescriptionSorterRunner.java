package es.udc.fi.lbd.monuzz.id.seguros;

import java.util.Comparator;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Sorter;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class DescriptionSorterRunner extends SpringJUnit4ClassRunner {
    private static final Comparator<Description> comparator = new Comparator<Description>() {
    	 
        public int compare(Description o1, Description o2) {
            return o1.getDisplayName().compareTo(o2.getDisplayName());
        }
    };

    public DescriptionSorterRunner(Class<?> klass) throws InitializationError {
        super(klass);
        sort(new Sorter(comparator));
}
}
