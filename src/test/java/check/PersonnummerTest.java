package check;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eara02 on 2016-10-31.
 */
public class PersonnummerTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void returneraPersonnummer() throws Exception {

    }

    @Test
    public void returneraalder() throws Exception {
        check.Personnummer p = new check.Personnummer();
        p.returneraalder("194501010212");
    }

}