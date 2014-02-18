package com.hvn.velocity.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class encapsulates cityRegion to reduce the database burden.
 * 
 * See com.hvn.velocity.domain.Customer's cityRegion field.
 */
public class RegionHashMap {

	private Map<String, String> cityRegion;
	
	public RegionHashMap() {
		cityRegion = new LinkedHashMap<String, String>();
		cityRegion.put("PG", "Prague");
		cityRegion.put("BN", "Benarl");
		cityRegion.put("HS", "Hassen");
	}

	public Map<String, String> getCityRegion() {
		return cityRegion;
	}
	
}
