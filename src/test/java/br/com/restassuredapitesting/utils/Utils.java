package br.com.restassuredapitesting.utils;

import org.json.simple.JSONObject;

public class Utils {

//método para pegar o local dos testes de contrato
    public static  String getContractsBasePath(String pack, String contract) {
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitesting/tests/"
                + pack
                + "/contracts/"
                + contract
                + ".json";
    }

    public static JSONObject validPayloadBooking(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin",  "2013-02-23");
        bookingDates.put("checkout", "2014-10-23" );

        payload.put("firstname", "Sally");
        payload.put("lastname", "Brown");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return payload;
    }

    public static JSONObject payloadBookingWithMoreParameters(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin",  "2013-02-23");
        bookingDates.put("checkout", "2014-10-23" );

        payload.put("firstname", "Sally");
        payload.put("lastname", "Brown");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");
        payload.put("additionalneeds", "Baby crib");
        payload.put("freeCancellation", false);

        return payload;


    }
}
