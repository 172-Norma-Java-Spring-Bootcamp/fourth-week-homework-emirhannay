package org.patikadev.orderexample.controller;

import lombok.RequiredArgsConstructor;
import org.patikadev.orderexample.dto.request.CreateCampaignRequestDTO;
import org.patikadev.orderexample.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCampaignRequestDTO createCampaignRequestDTO){
        campaignService.create(createCampaignRequestDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{campaignId}")
    public ResponseEntity<?> delete(@PathVariable  Long campaignId){
        campaignService.delete(campaignId);
        return ResponseEntity.ok().build();
    }
}
