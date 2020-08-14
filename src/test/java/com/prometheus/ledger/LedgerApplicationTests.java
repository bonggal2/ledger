package com.prometheus.ledger;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(locations = {"classpath:/beans.xml","classpath:/service-beans.xml"})
public abstract class LedgerApplicationTests {

	@Test
	void contextLoads() {
	}

}
