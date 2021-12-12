/*
 * OrganizationActivationEventConsumer.java
 *
 * (c) Copyright AUDI AG, 2021
 * All Rights reserved.
 *
 * AUDI AG
 * 85057 Ingolstadt
 * Germany
 */
package de.apnmt.organization.common.async.controller;

import de.apnmt.common.controller.ApnmtEventConsumer;
import de.apnmt.common.event.ApnmtEvent;
import de.apnmt.common.event.ApnmtEventType;
import de.apnmt.common.event.value.OrganizationActivationEventDTO;
import de.apnmt.organization.common.service.OrganizationService;

public class OrganizationActivationEventConsumer implements ApnmtEventConsumer<OrganizationActivationEventDTO> {

    private final OrganizationService organizationService;

    public OrganizationActivationEventConsumer(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    public void receiveEvent(ApnmtEvent<OrganizationActivationEventDTO> event) {
        if (event.getType().equals(ApnmtEventType.organizationActivationChanged)) {
            this.organizationService.handleOrganizationActivation(event.getValue());
        }
    }

}
