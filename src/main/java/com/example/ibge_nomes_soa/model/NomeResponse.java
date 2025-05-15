package com.example.ibge_nomes_soa.model;

import lombok.Data;
import java.util.List;

@Data
public class NomeResponse {
    private String nome;
    private List<Serie> res;
}

