package com.example.ibge_nomes_soa.service;


import com.example.ibge_nomes_soa.client.IbgeClient;
import com.example.ibge_nomes_soa.model.NomeResponse;
import com.example.ibge_nomes_soa.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NomeService {

    @Autowired
    private IbgeClient client;

    public List<Serie> obterEvolucao(String nome) {
        List<NomeResponse> resposta = client.buscarNome(nome);
        return resposta.get(0).getRes();
    }

    public Map<Integer, Long> compararNomes(String nome1, String nome2) {
        List<Serie> serie1 = obterEvolucao(nome1);
        List<Serie> serie2 = obterEvolucao(nome2);

        return serie1.stream().collect(Collectors.toMap(
                Serie::getDecada,
                s -> s.getFrequencia() - serie2.stream()
                        .filter(x -> x.getDecada() == s.getDecada())
                        .mapToLong(Serie::getFrequencia).findFirst().orElse(0)
        ));
    }
}
