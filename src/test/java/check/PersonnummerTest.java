package check;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eara02 on 2016-10-31.
 */
public class PersonnummerTest {
    @Test
    public void returneraPersonnummer() throws Exception {

    }

    @Test
    public void returneraalder() throws Exception {
        check.Personnummer p = new check.Personnummer();
        p.returneraalder("201201010212");
    }

}