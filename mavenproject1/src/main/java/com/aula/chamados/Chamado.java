package com.aula.chamados;

public class Chamado {
    private int id; 
    private String solicitante;
    private String equipamento;
    private String descricao;
    private String dataAbertura;
    private int prioridade;
    private String status;

    public Chamado() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getSolicitante() { return solicitante; }
    public void setSolicitante(String s) { this.solicitante = s; }
    public String getEquipamento() { return equipamento; }
    public void setEquipamento(String e) { this.equipamento = e; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String d) { this.descricao = d; }
    public String getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(String da) { this.dataAbertura = da; }
    public int getPrioridade() { return prioridade; }
    public void setPrioridade(int p) { this.prioridade = p; }
    public String getStatus() { return status; }
    public void setStatus(String st) { this.status = st; }
}