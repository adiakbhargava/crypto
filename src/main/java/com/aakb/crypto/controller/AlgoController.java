package com.aakb.crypto.controller;

import com.aakb.crypto.domain.*;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.*;

@RestController
class AlgoController {
    @GetMapping("/algos")
    AlgoResponse getAllAlgos(@RequestParam String primitive) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        StringBuilder sb = new StringBuilder();
        AlgoResponse algoResponse = new AlgoResponse();

        for (Provider provider : Security.getProviders()) {
            for (Provider.Service service : provider.getServices()) {
                String type = service.getType();
                if (type.equals(primitive) || primitive.equalsIgnoreCase("all")) {
                    if (!algoResponse.getAlgorithms().containsKey(type)) {
                        algoResponse.getAlgorithms().put(type, new ArrayList<AlgoDetails>());
                    }
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

        for (Map.Entry<String, List<AlgoDetails>> entry : algoResponse.getAlgorithms().entrySet()) {
            List<AlgoDetails> sortedList = entry.getValue().stream().filter(Objects::nonNull)
                    .sorted(Comparator.comparing(AlgoDetails::getAlgorithm))
                    .toList();

            algoResponse.getAlgorithms().put(entry.getKey(), sortedList);
        }

        return algoResponse;
    }
}