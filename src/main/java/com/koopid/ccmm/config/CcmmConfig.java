package com.koopid.ccmm.config;

import static com.koopid.ccmm.utility.Constants.SCAN_PACKAGES;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.koopid.ccmm.entity.KoopidProvider;
import com.koopid.ccmm.repo.KoopidConfigRepository;
import com.koopid.ccmm.service.KoopidService;
import com.koopid.ccmm.service.SessionKeyClient;

@Configuration
public class CcmmConfig {

	private static final Logger log = LoggerFactory.getLogger(CcmmConfig.class);

	public static List<KoopidProvider> koopidProviderList;
	
	@Value("${version}")
	private String appVersion;

	@Value("${aacc_version}")
	private String aaccVersion;

	@Autowired
	private KoopidService koopidService;
	
	@Autowired
	private KoopidConfigRepository koopidConfigRepository;

	@Bean
	public void initApp() {
		log.info("**********************************************************************");
		log.info("*                Koopid AACC Gateway (Version: {})                 *", appVersion);
		log.info("*             Avaya Aura Contact Center (Version: {})             *", aaccVersion);
		log.info("**********************************************************************");
	}

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan(SCAN_PACKAGES);
		return marshaller;
	}

	@Bean
	public SessionKeyClient sessionKeyClient(Jaxb2Marshaller marshaller) {
		SessionKeyClient client = new SessionKeyClient();
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

	@Bean
	public void callbackReg() {

		// Note dont take this object from cache it execute initially keep it as it is
		koopidProviderList = koopidConfigRepository.findByIsActiveTrue();

		for (KoopidProvider koopidProvider : koopidProviderList) {
			koopidService.callbackRegistration(koopidProvider);
			koopidService.messageWhitelisting(koopidProvider);
		}
	}

}
