package check;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by eara02 on 2016-10-31.
 */
public class PersonnummerTest {

    @Test
    public void returneraKorrektPersonnummer10() throws Exception {
        check.Personnummer p = new check.Personnummer();
        Response r = p.returneraPersonnummer("8008090212");
        assertEquals(200,r.getStatus());
    }

    @Test
    public void returneraFelaktigtPersonnummer10() throws Exception {
        check.Personnummer p = new check.Personnummer();
        Response r = p.returneraPersonnummer("8008090211");
        assertEquals(200,r.getStatus());
    }

    @Test
    public void returneraalderKorrektPersonNummer10() throws Exception {
        check.Personnummer p = new check.Personnummer();
        Response r = p.returneraalderXml("4501010212");
        assertEquals(200,r.getStatus());
    }

    @Test
    public void returneraalderFelaktigtPersonNummer10() throws Exception {
        check.Personnummer p = new check.Personnummer();
        Response r = p.returneraalderXml("45010102121");
        assertEquals(200,r.getStatus());
    }

    @Test
    public void returneraKorrektPersonnummer12() throws Exception {
        check.Personnummer p = new check.Personnummer();
        Response r = p.returneraPersonnummer("198008090212");
        assertEquals(200,r.getStatus());
    }

    @Test
    public void returneraFelaktigtPersonnummer12() throws Exception {
        check.Personnummer p = new check.Personnummer();
        Response r = p.returneraPersonnummer("198008090211");
        assertEquals(200,r.getStatus());
    }

    @Test
    public void returneraalderKorrektPersonNummer12() throws Exception {
        check.Personnummer p = new check.Personnummer();
        Response r = p.returneraalderXml("194501010212");
        assertEquals(200,r.getStatus());
    }

    @Test
    public void returneraalderFelaktigtPersonNummer12() throws Exception {
        check.Personnummer p = new check.Personnummer();
        Response r = p.returneraalderXml("1945010102121");
        assertEquals(200,r.getStatus());
    }

}