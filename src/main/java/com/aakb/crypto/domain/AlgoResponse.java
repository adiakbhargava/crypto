package com.aakb.crypto.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
public class AlgoResponse {
    private HashMap<String, List<AlgoDetails>> algorithms = new HashMap<String, List<AlgoDetails>>();
}
