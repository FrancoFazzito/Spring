package com.bolsadeideas.springboot.id.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsadeideas.springboot.id.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.id.app.models.domain.Producto;
import com.bolsadeideas.springboot.id.app.models.service.IServiciable;
import com.bolsadeideas.springboot.id.app.models.service.ServicioIntermedio;

@Configuration
public class AppConfig {
	
	//agregar a traves de config y bean
	@Bean("servicioIntermedio")
	public IServiciable registrarServicio() {
		return new ServicioIntermedio();
	}

	@Bean("itemsFactura")
	@Primary
	public List<ItemFactura> registrarItems(){
		Producto pro1 = new Producto("camara sony", 100);
		Producto pro2 = new Producto("celular sony", 200);
		
		ItemFactura linea1 = new ItemFactura(pro1, 5);
		ItemFactura linea2 = new ItemFactura(pro2,10);
		
		return Arrays.asList(linea1,linea2);
	}
	
	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina(){ //2 beans del tipo list<itemFactura>
		Producto pro1 = new Producto("PC asus", 1000);
		Producto pro2 = new Producto("lapicera bic", 1);
		Producto pro3 = new Producto("impresora", 10);
		
		ItemFactura linea1 = new ItemFactura(pro1, 5);
		ItemFactura linea2 = new ItemFactura(pro2,100);
		ItemFactura linea3 = new ItemFactura(pro3,20);
		
		return Arrays.asList(linea1,linea2,linea3);
	}
}
