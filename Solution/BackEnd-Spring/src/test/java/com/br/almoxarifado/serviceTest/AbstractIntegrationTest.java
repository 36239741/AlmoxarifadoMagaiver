package com.br.almoxarifado.serviceTest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.almoxarifado.AlmoxarifadoMagaiverApplication;
import com.br.almoxarifado.AlmoxarifadoMagaiverApplicationTests;

//Usuario
@ActiveProfiles("henrique")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AlmoxarifadoMagaiverApplicationTests.class, AlmoxarifadoMagaiverApplication.class})
public abstract class AbstractIntegrationTest {
	@Before
	public void beforeTest() {
		
	}
}
