package be.caly;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.caly.conf.EnvelopeProperties;



@RunWith(SpringRunner.class)
@SpringBootTest
public class CalyApplicationTests {
	
	@Autowired
	public EnvelopeProperties  envProperties;
	
	@Test
	public void contextLoads() {
	}
	
    @Test
	public void testLoadingOfEnvelopeProperties() {
		assertThat(envProperties.getBrcCtl(), hasItems("ET-TECH", "ET-FUNC"));
		assertThat(envProperties.getInput(), allOf(hasEntry("input1", "C:Users:input1"),
	                hasEntry("input2", "C:Users:input2")));
	}
    
    

}
