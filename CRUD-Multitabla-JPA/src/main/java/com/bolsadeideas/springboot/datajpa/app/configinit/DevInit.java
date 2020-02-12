package com.bolsadeideas.springboot.datajpa.app.configinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.datajpa.app.entity.Rol;
import com.bolsadeideas.springboot.datajpa.app.repository.IRolRepository;

@Component
public class DevInit implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	IRolRepository Rolrepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Rolrepository.save(new Rol("Administrador"));
		Rolrepository.save(new Rol("Contable"));
		Rolrepository.save(new Rol("Regular"));
	}
	
	

}
