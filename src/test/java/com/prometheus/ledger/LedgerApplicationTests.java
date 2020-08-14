package com.prometheus.ledger;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LedgerApplication.class)
@WebAppConfiguration
@ActiveProfiles("test001")
public abstract class LedgerApplicationTests {

	@Test
	void contextLoads() {
	}

}
