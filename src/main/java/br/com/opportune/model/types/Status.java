package br.com.opportune.model.types;

public enum Status {

    ABERTA("aberta"),
    FECHADA("fechada"),
    PERDIDA("perdida");

    private final String descricao;

    Status(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

