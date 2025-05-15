package com.example.ibge_nomes_soa.controller;

import com.example.ibge_nomes_soa.model.Serie;
import com.example.ibge_nomes_soa.service.NomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class NomeController {

    @Autowired
    private NomeService nomeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/nome")
    public String mostrarRanking(@RequestParam String nome, Model model) {
        List<Serie> dados = nomeService.obterEvolucao(nome);
        model.addAttribute("nome", nome);
        model.addAttribute("dados", dados);
        return "nome";
    }

    @GetMapping("/comparar")
    public String compararNomes(@RequestParam String nome1, @RequestParam String nome2, Model model) {
        Map<Integer, Long> comparacao = nomeService.compararNomes(nome1, nome2);
        model.addAttribute("nome1", nome1);
        model.addAttribute("nome2", nome2);
        model.addAttribute("dados", comparacao);
        return "comparacao";
    }
}

