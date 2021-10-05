package gestorDeEspectaculos;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
public class main {
	String start_date="01-02-2022 15:36:44";
	String end_date="01-04-2022 15:00:00";
	SimpleDateFormat sdf
    = new SimpleDateFormat(
        "dd-MM-yyyy HH:mm:ss");

// Try Block
try {

    // parse method is used to parse
    // the text from a string to
    // produce the date
    Date d1 = sdf.parse(start_date);
    Date d2 = sdf.parse(end_date);
}
