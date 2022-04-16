package tgc.edu.ljc.stu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class StuApplicationTests {
	@Test
	@Transactional
	@Rollback(value = false)
	void contextLoads() {
	}

}
