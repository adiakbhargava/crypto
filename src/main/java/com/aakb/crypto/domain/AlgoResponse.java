package com.aakb.crypto.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
/**
 * Response for algo controller
 *
 * @author Adi Bhargava
 */
public class AlgoResponse {
    // A hashmap with the name of the algorithm type as the key, and a list of algo details for the value
    private HashMap<String, List<AlgoDetails>> algorithms = new HashMap<String, List<AlgoDetails>>();
}
