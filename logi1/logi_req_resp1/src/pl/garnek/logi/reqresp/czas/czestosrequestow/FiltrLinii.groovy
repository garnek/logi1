package pl.garnek.logi.reqresp.czas.czestosrequestow

import java.text.DateFormat
import java.text.SimpleDateFormat

import pl.garnek.logi.reqresp.czas.FiltrAbs;

class FiltrLinii extends FiltrAbs {
	String tekstWLinii
	
	
	public Closure getDomkniecieFiltrujace() {
		def filtrLinii = { lista, linia ->
			
			if (linia ==~ tekstWLinii) {
				
				lista.add(wyciagnijDate(linia))
				//println linia 
				//println lista
			}
		}
		return filtrLinii;
	}
	
	
	static DateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
	
	private wyciagnijDate(String linia) {
		SDF.parse(linia.substring(0, 23));
	}
	
}