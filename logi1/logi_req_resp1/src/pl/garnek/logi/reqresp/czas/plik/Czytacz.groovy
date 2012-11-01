package pl.garnek.logi.reqresp.czas.plik

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class Czytacz {
	
	Log LOGGER = LogFactoryImpl.getLog(Czytacz.class)
	
	
	Czytacz(){
		LOGGER.info "Czytacz init";
	}
	
	public czytaj(Konfiguracja konfiguracja) {
		println "konfig: " + konfiguracja.sciezkaBaza + "\\" + konfiguracja.getSciezkaRegexp();
		
		def mapa = Map;
		
		znajdzPkikiWgPattern(konfiguracja.getSciezkaBaza(), konfiguracja.getSciezkaRegexp()).each {
			println it.absolutePath;
			it.eachLine {
				mapa.put(klucz, wa)
			}
		}
	}
	
	private znajdzPkikiWgPattern(String sciezka, String wyrazeniePliku) {
		
		def pliki = []; 
		
		def pattern = ~wyrazeniePliku
		
		znajdzPliki(sciezka).each {
			def nazwa = it.absolutePath - "\\" + sciezka
			if (nazwa ==~ pattern) {
				pliki.add(it)
			}
		}
		
		return pliki
	}
	
	private znajdzPliki(String sciezka) {
		
		def baza = new File(sciezka);
		baza.listFiles();
	}
	
	@PostConstruct
	post (){
		LOGGER.debug "post conddtruct"
	}
	
	static class Konfiguracja {
		String sciezkaBaza
		String sciezkaRegexp
		
	}
}
