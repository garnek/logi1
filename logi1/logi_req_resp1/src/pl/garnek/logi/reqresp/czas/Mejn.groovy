package pl.garnek.logi.reqresp.czas

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.garnek.logi.reqresp.czas.plik.Czytacz;

class Mejn {
	
	static main(args) {
		try {
			
			ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
			
			Starter starter = ctx.getBean(Starter.class)
			assert starter != null;
			
			starter.startuj()
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
