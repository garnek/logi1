package pl.garnek.logi.reqresp.czas

import javax.annotation.PostConstruct

import org.apache.commons.logging.Log
import org.apache.commons.logging.impl.LogFactoryImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SzukaczLinii {
	
	Log LOGGER = LogFactoryImpl.getLog(SzukaczLinii.class)
	
	@Value('${maxWierszy}')
	int maxWierszy;
	
	
	public szukaj(File [] pliki, def produkt, Closure filtrLinii) {
		pliki.each {
			try {
				LOGGER.info "SZUKAM W: " + it.absolutePath
				int i=0
				it.eachLine {
					filtrLinii(produkt, it)
					if (i++>maxWierszy) throw new IllegalStateException();
				}
			}
			catch (IllegalStateException e) {
				//ehh
			}
		}
	}
	
}
