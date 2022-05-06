package org.patikadev.orderexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.CampaignConverter;
import org.patikadev.orderexample.dto.request.CreateCampaignRequestDTO;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Campaign;
import org.patikadev.orderexample.repository.CampaignRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CampaignServiceImpl implements CampaignService{
    private final CampaignRepository campaignRepository;
    private final CampaignConverter campaignConverter;
    @Override
    public void create(CreateCampaignRequestDTO createCampaignRequestDTO) {
        Campaign campaign = campaignConverter.toCampaign(createCampaignRequestDTO);
        campaignRepository.save(campaign);
        log.info("Campaign created was successfully -> {}",campaign.getId());
    }
    public Campaign getCampaign(Long campaignId){
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(
                () -> new BusinessServiceOperationException.CampaignNotFoundException("Campaign not found")
        );
        log.info("Getting campaign was successfully -> {}",campaign.getId());
        return campaign;
    }

    @Override
    public void delete(Long campaignId) {
        Campaign campaign = getCampaign(campaignId);
        campaignRepository.delete(campaign);
        log.info("Campaign deleted was successfully -> {}",campaign.getId());
    }
}
