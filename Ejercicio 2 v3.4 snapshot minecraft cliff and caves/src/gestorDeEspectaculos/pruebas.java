package gestorDeEspectaculos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date fechaActual = new Date();
		System.out.println(fechaActual);
		
		String start_date="01-02-2022 15:36:44";
		String end_date="01-04-2022 15:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	// Try Block
	try {

	    // parse method is used to parse
	    // the text from a string to
	    // produce the date
	    Date d1 = sdf.parse(start_date);
	    Date d2 = sdf.parse(end_date);
	    long difference=d2.getTime()-d1.getTime();
	    long difference_In_Days
        = (difference
           / (1000 * 60 * 60 * 24))
          % 365;
	    System.out.println("Diferencia es: "+difference_In_Days+" días");
	}catch (ParseException e) {
	    e.printStackTrace();
	}
	}

}
