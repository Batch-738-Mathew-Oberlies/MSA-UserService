package com.revature.rideshare.services;

import com.revature.rideshare.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "distance-service", url = "http://distance-service:8080")
public interface DistanceService {
	@GetMapping("/?api-key=true")
	String getGoogleMAPKey();

	@GetMapping("/{address}?driver=true")
	List<User> distanceMatrix(@PathVariable String address);
}
