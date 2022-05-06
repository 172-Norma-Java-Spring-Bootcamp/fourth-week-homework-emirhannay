package org.patikadev.orderexample.converter;


import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateCampaignRequestDTO;
import org.patikadev.orderexample.model.Campaign;
import org.springframework.stereotype.Component;




@RequiredArgsConstructor
@Component
public class CampaignConverterImpl implements CampaignConverter{
    @Override
    public Campaign toCampaign(CreateCampaignRequestDTO createCampaignRequestDTO) {
        Campaign campaign = new Campaign();
        campaign.setDescription(createCampaignRequestDTO.description());
        campaign.setDiscountPrice(createCampaignRequestDTO.discountPrice());
        campaign.setDiscountRate(createCampaignRequestDTO.discountRate());
        campaign.setName(createCampaignRequestDTO.name());
        campaign.setStartDate(createCampaignRequestDTO.startDate());
        campaign.setEndDate(createCampaignRequestDTO.endDate());
        return campaign;
    }
}
