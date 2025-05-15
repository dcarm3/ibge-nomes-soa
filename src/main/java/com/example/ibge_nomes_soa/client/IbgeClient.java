package com.example.ibge_nomes_soa.client;


import com.example.ibge_nomes_soa.model.NomeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
public class IbgeClient {

    private static final String API_URL = "https://servicodados.ibge.gov.br/api/v2/censos/nomes/";

    @Autowired
    private RestTemplate restTemplate;

    public List<NomeResponse> buscarNome(String nome) {
        NomeResponse[] resposta = restTemplate.getForObject(API_URL + nome, NomeResponse[].class);
        return Arrays.asList(resposta);
    }
}
