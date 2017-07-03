package es.sidelab.urjc;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

@EnableCaching
@SpringBootApplication
@EnableHazelcastHttpSession
public class BasketGoApplication {

	@Value("${hazelcast.member}")
	private String HAZELCAST_MEMBER;
	
	public static void main(String[] args) {
		SpringApplication.run(BasketGoApplication.class, args);
	}
	
	@Bean
	public CacheManager cacheManager(){
		return new ConcurrentMapCacheManager("cacheJugadores");
	}

	@Bean
	public Config config() {
		
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(false);
		//joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList(HAZELCAST_MEMBER));
		joinConfig.getTcpIpConfig().setEnabled(true).addMember( "192.168.0.20" ).addMember( "192.168.0.6" );
		return config;
		
	}
	
}
