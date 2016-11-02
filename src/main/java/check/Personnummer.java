package check;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by eara02 on 2016-10-31.
 */
@Path("/kontrollera")
public class Personnummer {

    @Path("/koll/{personNummer}")
    @GET
    @Produces("application/json")
    public Response returneraPersonnummer(@PathParam("personNummer") String personNummer) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        Response resp;
        jsonObject.put("Fick in Value", personNummer);
        String result = "@Produces(\"application/json\") Output: \n\n" + jsonObject;
        if(personNummer.length() != 10 && personNummer.length() != 12) {
           resp  = Response.status(500).entity(result).build();
            return resp;
        }

        resp = Response.status(200).entity(result).build();
        return resp;
    }

    @Path("/alder/{personNummer/json}")
    @GET
    @Produces("application/json")
    public Response returneraalder(@PathParam("personNummer") String personNummer) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        Response resp;
        Date d = new Date();
        if(personNummer.length() != 10 && personNummer.length() != 12) {
            jsonObject.put("Meep in Value", personNummer);
            String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;

            resp  = Response.status(500).entity(result).build();
            return resp;
        }


        if(personNummer.length() == 10) {
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            try {
                d = sdf.parse(personNummer.substring(0,1) + "-" + personNummer.substring(2,3) +"-" + personNummer.substring(4,5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(personNummer.length() == 12) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                d = sdf.parse(personNummer.substring(0,4) + "-" + personNummer.substring(4,6) + "-" + personNummer.substring(6,8));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        LocalDate birthDate = new LocalDate(d);
        LocalDate now = new LocalDate(LocalDate.now());
        Years age = Years.yearsBetween(birthDate,now);

        jsonObject.put("Ålder", age.getYears());
        String result2 = "@Produces(\"application/json\") Output: \n\n" + jsonObject;

        resp = Response.status(200).entity(result2).build();
        return resp;
    }
    @Path("/alder/{personNummer/xml}")
    @GET
    @Produces("application/xml")
    public Response returneraalderXml(@PathParam("personNummer") String personNummer) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        Response resp;
        Date d = new Date();
        if(personNummer.length() != 10 && personNummer.length() != 12) {
            jsonObject.put("Meep in Value", personNummer);
            String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;

            resp  = Response.status(500).entity(result).build();
            return resp;
        }


        if(personNummer.length() == 10) {
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            try {
                d = sdf.parse(personNummer.substring(0,1) + "-" + personNummer.substring(2,3) +"-" + personNummer.substring(4,5));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(personNummer.length() == 12) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String s = personNummer.substring(0,4) + "-" + personNummer.substring(4,6) +"-" + personNummer.substring(6,8);
                d = sdf.parse(personNummer.substring(0,4) + "-" + personNummer.substring(4,6) + "-" + personNummer.substring(6,8));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        LocalDate birthDate = new LocalDate(d);
        LocalDate now = new LocalDate(LocalDate.now());
        Years age = Years.yearsBetween(birthDate,now);

        jsonObject.put("Ålder", age.getYears());
        String result2 = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
        System.out.println("sfsdfdf");

        resp = Response.status(200).entity(result2).build();
        return resp;
    }
}
