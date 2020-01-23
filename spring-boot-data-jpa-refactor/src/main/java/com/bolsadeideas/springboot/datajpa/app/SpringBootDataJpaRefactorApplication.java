package com.bolsadeideas.springboot.datajpa.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bolsadeideas.springboot.datajpa.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringBootDataJpaRefactorApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUploadFileService fileService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaRefactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileService.deleteAll();
		fileService.init();
		
		String password ="12345";

		for (int i = 0; i < 2; i++) {
			String bcryptPass = passwordEncoder.encode(password);
			System.out.println(bcryptPass);
		}
	}

}
