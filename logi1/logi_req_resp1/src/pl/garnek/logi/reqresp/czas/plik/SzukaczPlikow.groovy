package pl.garnek.logi.reqresp.czas.plik

import javax.annotation.PostConstruct

import org.apache.commons.logging.Log
import org.apache.commons.logging.impl.LogFactoryImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SzukaczPlikow {
	
	Log LOGGER = LogFactoryImpl.getLog(SzukaczPlikow.class)
	
	@Value('${sciezka.baza}')
	String sciezka;
	
	public szukaj(Closure filtrPlikow) {
		return znajdzPlikiWgPattern(filtrPlikow)
	}
	
	private znajdzPlikiWgPattern(Closure filtrPlikow) {
		
		def pliki = []; 
		
		znajdzPliki(sciezka).each {filtrPlikow(pliki,it)}
		
		return pliki
	}
	
	private znajdzPliki(String sciezka) {
		
		def baza = new File(sciezka);
		baza.listFiles();
	}
	
}
