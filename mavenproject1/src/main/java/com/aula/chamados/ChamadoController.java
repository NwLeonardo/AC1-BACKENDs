package com.aula.chamados;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private List<Chamado> listaChamados = new ArrayList<>();
    private int proximoId = 1;

    @PostMapping("/cadastrar")
    public Chamado cadastrar(@RequestBody Chamado chamado) {
        chamado.setId(proximoId++);
        chamado.setStatus("ABERTO");
        listaChamados.add(chamado);
        return chamado;
    }

    @GetMapping("/listar")
    public List<Chamado> listar() {
        return listaChamados;
    }

    @GetMapping("/buscar/{id}")
    public Chamado buscar(@PathVariable int id) {
        return listaChamados.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/atualizar/{id}")
    public Chamado atualizar(@PathVariable int id, @RequestBody Chamado novosDados) {
        for (Chamado c : listaChamados) {
            if (c.getId() == id) {
                c.setSolicitante(novosDados.getSolicitante());
                c.setEquipamento(novosDados.getEquipamento());
                c.setDescricao(novosDados.getDescricao());
                c.setDataAbertura(novosDados.getDataAbertura());
                c.setPrioridade(novosDados.getPrioridade());
                c.setStatus(novosDados.getStatus());
                return c;
            }
        }
        return null;
    }

    @DeleteMapping("/remover/{id}")
    public String remover(@PathVariable int id) {
        boolean removido = listaChamados.removeIf(c -> c.getId() == id);
        return removido ? "Chamado removido com sucesso!" : "Erro: Chamado não encontrado!";
    }

    @PatchMapping("/cancelar/{id}")
    public String cancelar(@PathVariable int id) {
        for (Chamado c : listaChamados) {
            if (c.getId() == id) {
                c.setStatus("FECHADO");
                return "Chamado cancelado (status alterado para FECHADO).";
            }
        }
        return "Erro: Chamado não encontrado!";
    }

    @GetMapping("/abertos")
    public List<Chamado> filtrarAbertos() {
        return listaChamados.stream()
                .filter(c -> "ABERTO".equalsIgnoreCase(c.getStatus()))
                .collect(Collectors.toList());
    }

    @GetMapping("/solicitante/{solicitante}")
    public List<Chamado> filtrarPorSolicitante(@PathVariable String solicitante) {
        return listaChamados.stream()
                .filter(c -> c.getSolicitante().equalsIgnoreCase(solicitante))
                .collect(Collectors.toList());
    }

    @GetMapping("/equipamento/{equipamento}")
    public List<Chamado> filtrarPorEquipamento(@PathVariable String equipamento) {
        return listaChamados.stream()
                .filter(c -> c.getEquipamento().equalsIgnoreCase(equipamento))
                .collect(Collectors.toList());
    }

    @GetMapping("/prioridade")
    public List<Chamado> filtrarPorPrioridade() {
        return listaChamados.stream()
                .filter(c -> "ABERTO".equalsIgnoreCase(c.getStatus()))
                .sorted(Comparator.comparingInt(Chamado::getPrioridade).reversed())
                .collect(Collectors.toList());
    }
}