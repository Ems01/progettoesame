package exam.EnricOmar;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import project.service.connection;

@SpringBootTest(classes=connection.class)
class EnricOmarApplicationTests {
	
	private connection conn;
    private String data;
    
    @BeforeEach
    void setUp() {
    	conn = new connection();
    }

	@Test
	void TestDataOK() {
		data = "13.11.2020";
		equals(conn.getToday(data));
	}
	
	@Test
	void TestDataNoOK() {
		data = "13.11.2021";
		assertEquals("1", conn.getToday(data));
	}

}
