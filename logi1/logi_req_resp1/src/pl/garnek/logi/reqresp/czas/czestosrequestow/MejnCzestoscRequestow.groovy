package pl.garnek.logi.reqresp.czas.czestosrequestow

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.garnek.logi.reqresp.czas.plik.SzukaczPlikow;

class MejnCzestoscRequestow {
	
	static main(args) {
		try {
			
			ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
			
			StarterCzestoscRequestow starter = ctx.getBean(StarterCzestoscRequestow.class)
			assert starter != null;
			
			starter.startuj()
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
