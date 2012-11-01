package pl.garnek.logi.reqresp.czas

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import pl.garnek.logi.reqresp.czas.plik.Czytacz;

@Component
class Starter {
	
	@Autowired
	Czytacz czytacz
	@Autowired
	@Qualifier("czytaczCfgReq")
	Czytacz.Konfiguracja cfgReq;
	@Autowired
	@Qualifier("czytaczCfgResp")
	Czytacz.Konfiguracja cfgResp;
	
	public startuj() {
		czytacz.czytaj(cfgReq);
		czytacz.czytaj(cfgResp);
	}
	
	@PostConstruct
	post (){
		println "post conddtruct"
	}
}
