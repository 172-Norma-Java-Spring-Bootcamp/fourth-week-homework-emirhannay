package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateCampaignRequestDTO;
import org.patikadev.orderexample.model.Campaign;

public interface CampaignConverter {
    Campaign toCampaign(CreateCampaignRequestDTO createCampaignRequestDTO);
}
