package org.patikadev.orderexample.repository;

import org.patikadev.orderexample.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
}
