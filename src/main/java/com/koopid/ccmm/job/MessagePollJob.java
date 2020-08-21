package com.koopid.ccmm.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.koopid.ccmm.config.CollectionConfig;
import com.koopid.ccmm.entity.CcmmMeta;
import com.koopid.ccmm.entity.Route;
import com.koopid.ccmm.repo.CcmmMetaRepository;
import com.koopid.ccmm.repo.RouteRepository;
import com.koopid.ccmm.utility.CcmmUtility;

public class MessagePollJob extends QuartzJobBean {

	private static final Logger log = LoggerFactory.getLogger(MessagePollJob.class);

	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private CcmmUtility ccmmUtility;
	@Autowired
	private CcmmMetaRepository ccmmMetaRepository;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		List<Route> routesRecord = routeRepository.findByIsActiveAndType(true, "ccmm");

		if (!routesRecord.isEmpty()) {
			routesRecord.parallelStream().forEach(route -> {
				if (!CollectionConfig.getRouteList().contains(route.getContext())) {
					CollectionConfig.getRouteList().add(route.getContext());
					log.debug("[{}] Started polling messages  ", route.getContext());

					CcmmMeta ccmmMeta = ccmmMetaRepository.findByContext(route.getContext());
					ccmmUtility.pollForMessage(route.getContext(), ccmmMeta.getContactID(), ccmmMeta.getSessionkey(),
							route.getProviderId(), route.getPartnerId());
				} else if (Boolean.FALSE.equals(route.isActive())
						&& CollectionConfig.getRouteList().contains(route.getContext())) {
					CollectionConfig.getRouteList().remove(route.getContext());
				}
			});
		}
	}

}
