package pl.garnek.logi.reqresp.czas.deltaodpowiedzi

import java.text.DateFormat
import java.text.SimpleDateFormat

import pl.garnek.logi.reqresp.czas.FiltrAbs;

class FiltrLinii extends FiltrAbs {
	String tekstWLinii
	
	
	public Closure getDomkniecieFiltrujace() {
		def filtrLinii = { mapa, linia ->
			
			if (linia ==~ tekstWLinii) {
				
				try {
					mapa.put(
							wyciagnijIDPytania(linia)
							, 
							wyciagnijDate(linia)
							);					
				}
				catch (IllegalStateException e) {
					//ehh
				}
				
			}
		}
		return filtrLinii;
	}
	
	
	static DateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
	
	private wyciagnijDate(String linia) {
		SDF.parse(linia.substring(0, 23));
	}
	
	static String START_REQ_ID = 'mustUnderstand="0">urn:uuid:';
	static String START_RESP_ID = '"wsa:Reply">urn:uuid:';
	static String KONIEC = '</wsa';
	
	private wyciagnijIDPytania(String linia) {
		int start = linia.lastIndexOf(START_RESP_ID);
		if ( start != -1 ) {
			start += START_RESP_ID.length() 
		}
		else {
			start = linia.lastIndexOf(START_REQ_ID);
			if (start != -1) {
				start += START_REQ_ID.length()
			}
			else {
				throw new IllegalStateException()
			}
		}
		
		String id = linia.subSequence(start, linia.indexOf(KONIEC, start));
		
		return id;
	}
}