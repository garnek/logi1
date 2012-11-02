package pl.garnek.logi.reqresp.czas.deltaodpowiedzi

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.garnek.logi.reqresp.czas.plik.SzukaczPlikow;

class MejnDeltaOdpowiedzi {
	
	static main(args) {
		try {
			
			ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
			
			StarterDeltaOdpowiedzi starter = ctx.getBean(StarterDeltaOdpowiedzi.class)
			assert starter != null;
			
			starter.startuj()
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
