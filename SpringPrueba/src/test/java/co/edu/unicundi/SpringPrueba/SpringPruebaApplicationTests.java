package co.edu.unicundi.SpringPrueba;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringPruebaApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;	
	
	@Test
	void verificarClave() {
		System.out.println("Resultado:--------------------  " + bcrypt.encode("admin"));
		assertTrue(true);
	}

}
