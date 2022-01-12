package de.apnmt.organization.common.async.controller;

import de.apnmt.common.controller.ApnmtEventConsumer;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.ApnmtEventType;
import de.apnmt.common.event.value.OrganizationActivationEventDTO;
import de.apnmt.organization.common.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrganizationActivationEventConsumer implements ApnmtEventConsumer<OrganizationActivationEventDTO> {

    private final Logger log = LoggerFactory.getLogger(OrganizationActivationEventConsumer.class);

    private final OrganizationService organizationService;

    public OrganizationActivationEventConsumer(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    public void receiveEvent(ApnmtEvent<OrganizationActivationEventDTO> event) {
        if (event.getType().equals(ApnmtEventType.organizationActivationChanged)) {
            this.organizationService.handleOrganizationActivation(event.getValue());
        } else {
            log.error("Received wrong event type {}", event.getType());
        }
    }

}
