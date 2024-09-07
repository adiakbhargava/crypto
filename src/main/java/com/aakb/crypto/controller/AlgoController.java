package com.aakb.crypto.controller;

import com.aakb.crypto.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.*;

@RestController
/**
 * Retrieves all usable algorithms for these REST APIs
 *
 * @author Adi Bhargava
 */
class AlgoController {
    /**
     * All algorithms will be displayed based off of what is passed as the primitive (represents service type)
     *
     * if "all" is passed into the primitive, all the algorithms will be displayed
     *
     * @return AlgoResponse holding the sorted hashmap containing all algorithms organized by service type
     */
    @GetMapping("/algos")
    AlgoResponse getAllAlgos(@RequestParam String primitive) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // set up a StringBuilder and AlgoResponse instance
        StringBuilder sb = new StringBuilder();
        AlgoResponse algoResponse = new AlgoResponse();

        // iterate through the given providers in Security
        for (Provider provider : Security.getProviders()) {
            // get and iterate through the services of each provider
            for (Provider.Service service : provider.getServices()) {
                // if service type equals that of the passed in primitive, we will add it to the AlgoResponse map
                String type = service.getType();
                if (type.equals(primitive) || primitive.equalsIgnoreCase("all")) {
                    if (!algoResponse.getAlgorithms().containsKey(type)) {
                        algoResponse.getAlgorithms().put(type, new ArrayList<AlgoDetails>());
                    }
                    // get algoList to add new algorithm with AlgoDetails
                    List<AlgoDetails> algoList = algoResponse.getAlgorithms().get(type);
                    algoList.add(AlgoDetails.builder()
                            .algorithm(service.getAlgorithm())
                            .className(service.getClassName())
                            .provider(provider.toString())
                            .service(service.toString())
                            .build());
                }
            }
        }

        // sort AlgoRespone hashmap
        for (Map.Entry<String, List<AlgoDetails>> entry : algoResponse.getAlgorithms().entrySet()) {
            List<AlgoDetails> sortedList = entry.getValue().stream().filter(Objects::nonNull)
                    .sorted(Comparator.comparing(AlgoDetails::getAlgorithm))
                    .toList();

            algoResponse.getAlgorithms().put(entry.getKey(), sortedList);
        }

        return algoResponse;
    }
}