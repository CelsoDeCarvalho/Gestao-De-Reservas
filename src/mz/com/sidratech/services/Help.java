
package mz.com.sidratech.services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 *
 * @author doroteia
 */
public class Help {

    public static Date date_from_string(String date) throws ParseException {

        SimpleDateFormat parse = new SimpleDateFormat("yyyy.MM.dd");
        date = date.replaceAll("-", ".");
        java.util.Date d = parse.parse(date);
        Date df = new Date(d.getTime());

        return df;

    }
    
    public static Date StringToDate(String date) throws ParseException {

        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd-hh");
      
        java.util.Date d = parse.parse(date);
        Date df = new Date(d.getTime());

        return df;

    }

}
