package be.caly.conf;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "envelope")
//@Data
//@EnableConfigurationProperties -- Not Needed - Spring boot take care of it
public class EnvelopeProperties {
	
	private String  outputFolder;
	private List<String> brcCtl; 
	private Map<String, String> input;
	
	public String getOutputFolder() {
		return outputFolder;
	}
	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}
	public List<String> getBrcCtl() {
		return brcCtl;
	}
	public void setBrcCtl(List<String> brcCtl) {
		this.brcCtl = brcCtl;
	}
	public Map<String, String> getInput() {
		return input;
	}
	public void setInput(Map<String, String> input) {
		this.input = input;
	}

}
