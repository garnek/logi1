package pl.garnek.logi.reqresp.czas.czestosrequestow

import javax.annotation.Resource

import org.apache.commons.logging.Log
import org.apache.commons.logging.impl.LogFactoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import pl.garnek.logi.reqresp.czas.SzukaczLinii
import pl.garnek.logi.reqresp.czas.plik.FiltrPlikow
import pl.garnek.logi.reqresp.czas.plik.SzukaczPlikow

@Component
class StarterCzestoscRequestow {
	
	Log LOGGER = LogFactoryImpl.getLog(StarterCzestoscRequestow.class)
	
	@Autowired
	SzukaczPlikow szukaczPlikow
	@Autowired
	SzukaczLinii szukaczLinii
	
	@Resource(name="filtrPlikowReq")
	FiltrPlikow filtrReq;
	@Resource(name="filtrLiniiCzasyZapytan")
	FiltrLinii filtrLiniiCzasyZapytan;
	
	
	public startuj() {
		
		File [] plikiReq = szukaczPlikow.szukaj(filtrReq.domkniecieFiltrujace)
		List<Date> listaCzasow = new LinkedList<Date>()
		szukaczLinii.szukaj(plikiReq, listaCzasow, filtrLiniiCzasyZapytan.domkniecieFiltrujace)
		
		int i = 0;
		long suma = 0;
		Date dataPoprzednia
		for (Date data : listaCzasow) {
			if (dataPoprzednia!=null) {
				
				long d = data.time - dataPoprzednia.time
				suma += d
				
				i++;
				
				if (d>10000) {
					println d + ": " + dataPoprzednia + " --- " + data
				}
			}
			
			dataPoprzednia = data
		}
		
		println "suma=" + suma + " (" + suma/(60*60) + " min)" + "   ilosc=" + i + "   srednia=" + suma/i + " (" + (suma/i)/(60) + " sek)"
		
//		Map<String, Date> mapaPyt = czytacz.czytaj(filtrReq.filtrPlikow, filtrReq.filtrLinii);
//		Map<String, Date> mapaOdp = czytacz.czytaj(filtrResp.filtrPlikow, filtrResp.filtrLinii);
//		
//		int i = 0;
//		int suma = 0;
//		for ( Entry<String, Date> e : mapaPyt ) {
//			Date czasPyt = e.value
//			Date czasOdp = mapaOdp.get(e.key)
//			if (czasOdp==null) {continue;}
//			long d = czasOdp.time - czasPyt.time
//			
//			if (d>100) {
//				println e.key + ": " + czasPyt + " - " + czasOdp + " = " + d				
//			}
//			suma+=d;
//			i++;
//		}
//		
//		println "suma=" + suma + " (" + suma/60 + " sek)" + "   ilosc=" + i + "   srednia=" + suma/i
	}
	
}
