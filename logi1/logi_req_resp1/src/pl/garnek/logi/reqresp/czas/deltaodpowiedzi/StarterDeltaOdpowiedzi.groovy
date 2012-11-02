package pl.garnek.logi.reqresp.czas.deltaodpowiedzi

import java.util.Map.Entry;

import javax.annotation.PostConstruct
import javax.annotation.Resource;

import org.apache.commons.logging.Log
import org.apache.commons.logging.impl.LogFactoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

import pl.garnek.logi.reqresp.czas.SzukaczLinii;
import pl.garnek.logi.reqresp.czas.plik.SzukaczPlikow
import pl.garnek.logi.reqresp.czas.plik.FiltrPlikow

@Component
class StarterDeltaOdpowiedzi {
	
	@Autowired
	SzukaczPlikow szukaczPlikow
	@Autowired
	SzukaczLinii szukaczLinii
	
	@Resource(name="filtrPlikowReq")
	FiltrPlikow filtrReq;
	@Resource(name="filtrPlikowResp")
	FiltrPlikow filtrResp;
	@Resource(name="filtrLiniiReq")
	FiltrLinii filtrLiniiReq;
	@Resource(name="filtrLiniiResp")
	FiltrLinii filtrLiniiResp;
	
	Log LOGGER = LogFactoryImpl.getLog(StarterDeltaOdpowiedzi.class)
	
	public startuj() {
//		Map<String, Date> mapaPyt = szukaczPlikow.szukaj(filtrReq.domkniecieFiltrujace, filtrLiniiReq.domkniecieFiltrujace);
//		Map<String, Date> mapaOdp = szukaczPlikow.szukaj(filtrResp.domkniecieFiltrujace, filtrLiniiResp.domkniecieFiltrujace);
		
		File [] plikiReq = szukaczPlikow.szukaj(filtrReq.domkniecieFiltrujace)
		Map<String, Date> mapaPyt = new HashMap<String, Date>()
		szukaczLinii.szukaj(plikiReq, mapaPyt, filtrLiniiReq.domkniecieFiltrujace)
		
		File [] plikiResp = szukaczPlikow.szukaj(filtrResp.domkniecieFiltrujace)
		Map<String, Date> mapaOdp = new HashMap<String, Date>()
		szukaczLinii.szukaj(plikiResp, mapaOdp, filtrLiniiResp.domkniecieFiltrujace)
		
		int i = 0;
		long suma = 0;
		for ( Entry<String, Date> e : mapaPyt ) {
			Date czasPyt = e.value
			Date czasOdp = mapaOdp.get(e.key)
			if (czasOdp==null) {continue;}
			long d = czasOdp.time - czasPyt.time
			
			if (d>100) {
				println e.key + ": " + czasPyt + " - " + czasOdp + " = " + d				
			}
			suma+=d;
			i++;
		}
		
		println "suma=" + suma + " (" + suma/60 + " sek)" + "   ilosc=" + i + "   srednia=" + suma/i
	}
	
}
