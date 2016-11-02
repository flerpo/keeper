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
        String result = "Output: \n\n" + jsonObject;
        if (checkLenght(personNummer) == false) {
           resp  = Response.status(500).entity(result).build();
            return resp;
        }
        if (checkNumber(personNummer) == false) {
            result = result + "\n FEL!";
            resp  = Response.status(500).entity(result).build();
            return resp;
        }

        result = result + "\n OK!";
        resp = Response.status(200).entity(result).build();
        return resp;
    }

    @Path("/alder/{personNummer}/json")
    @GET
    @Produces("application/json")
    public Response returneraalder(@PathParam("personNummer") String personNummer) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        Response resp;
        Date d = new Date();
        if (checkLenght(personNummer) == false) {
            jsonObject.put("Meep in Value", personNummer);
            String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;

            resp  = Response.status(500).entity(result).build();
            return resp;
        }

        if(personNummer.length() == 10) {
            d = getDate10(personNummer);
        }

        if(personNummer.length() == 12) {
            d = getDate12(personNummer);
        }

        Integer alder = getAge(d);

        jsonObject.put("Ålder", alder);
        String result2 = "@Produces(\"application/json\") Output: \n\n" + jsonObject;

        resp = Response.status(200).entity(result2).build();
        return resp;
    }

    private Integer getAge(Date d) {
        LocalDate birthDate = new LocalDate(d);
        LocalDate now = new LocalDate(LocalDate.now());
        Years age = Years.yearsBetween(birthDate,now);
        return age.getYears();
    }

    private Date getDate10(String personNummer) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        try {
            d = sdf.parse(personNummer.substring(0,2) + "-" + personNummer.substring(3,4) +"-" + personNummer.substring(5,6));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    private Date getDate12(String personNummer) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = sdf.parse(personNummer.substring(0,4) + "-" + personNummer.substring(4,6) + "-" + personNummer.substring(6,8));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Path("/alder/{personNummer}/xml")
    @GET
    @Produces("application/xml")
    public Response returneraalderXml(@PathParam("personNummer") String personNummer) {
        try {
            Response resp;
            Date d = new Date();
            if (checkLenght(personNummer) == false) {

                String result = "Error";

                resp  = Response.status(500).entity(result).build();
                return resp;
            }

            if(personNummer.length() == 10) {
                d = getDate10(personNummer);
            }

            if(personNummer.length() == 12) {
                d = getDate12(personNummer);
            }

            Integer alder = getAge(d);

            String xmlString = "<data><alder>" + alder + "</alder></data>";
            String result2 = xmlString;

            resp = Response.status(200).entity(result2).build();
            return resp;

        }  catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }

    private boolean checkLenght(String pNr){
        if(pNr.length() != 10 && pNr.length() != 12){
            return false;
        }
        return true;
    }

    private boolean checkNumber(String pn){

            if (pn.length() == 12){
               pn = pn.substring(2);
            }


            int lastNumber = Integer.parseInt(pn.substring(9, 10).toString());
            pn = pn.substring(0, 9).toString();

            int summa = 0;
            int summa2 = 0;
            int[] teste = new int[10];


            for (int i = 0; i < 9; i++) {
                int number = Integer.parseInt(pn.substring(i, i +1));

                if((i % 2)!= 0){
                    summa2 = number;

                    if(summa2 > 9){
                        teste[i]= (1 + (summa2 % 10));
                    }else {
                        teste[i] = summa2;
                    }

                }else {
                    summa = number *2;

                    if(summa > 9){
                        teste[i] = (1+ (summa %10));
                    }else{
                        teste[i] = summa;
                    }
                }

            }

//		for (int j = 0; j < (teste.length) - 1; j++) {
//			System.out.println(teste[j]);
//		}

            int total = teste[0] + teste[1] + teste[2] + teste[3] + teste[4] + teste[5] + teste[6] + teste[7] +
                    teste[8] + teste[9];

            total = total % 10;

            if((10 - total) == lastNumber){
                System.out.println("> Personnummer är giltigt!");
                return true;
            }else {
                System.out.println("> Personnummer är ogiltigt!");
                return false;
            }





    }
}
