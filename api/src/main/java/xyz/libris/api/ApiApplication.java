package xyz.libris.api;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {

	@Bean
	public EmbeddedServletContainerFactory servletContainer(@Value("${libris.http.port}") Integer httpPort) {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(createStandardConnector(httpPort));
		return tomcat;
	}

	private Connector createStandardConnector(int httpPort) {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(httpPort);
		return connector;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
