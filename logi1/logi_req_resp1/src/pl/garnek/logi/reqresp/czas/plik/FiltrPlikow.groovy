package pl.garnek.logi.reqresp.czas.plik

import java.text.DateFormat
import java.text.SimpleDateFormat

import pl.garnek.logi.reqresp.czas.FiltrAbs;

class FiltrPlikow extends FiltrAbs {
	String sciezkaRegexp
	
	public Closure getDomkniecieFiltrujace() {
		def filtrPlikow = { lista, plik ->
			
			def nazwa = plik.absolutePath
			if (nazwa ==~ sciezkaRegexp) {
				lista.add(plik)
			}
		}
		return filtrPlikow;
	}
	
}