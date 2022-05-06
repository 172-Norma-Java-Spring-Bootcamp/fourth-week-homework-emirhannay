package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateCampaignRequestDTO;
import org.patikadev.orderexample.model.Campaign;

public interface CampaignService {
    void create(CreateCampaignRequestDTO createCampaignRequestDTO);
    Campaign getCampaign(Long campaignId);
    void delete(Long campaignId);
}
