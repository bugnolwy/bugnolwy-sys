package cn.bugnolwy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class BugnoLwyApplicationTests {
	@Autowired
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
	
	@Test
	public void getPassword() {
		System.out.println(passwordEncoder().encode("123456"));
	}

}
