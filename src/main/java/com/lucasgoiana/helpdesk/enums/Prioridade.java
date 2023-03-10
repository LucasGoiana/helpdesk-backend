package com.lucasgoiana.helpdesk.enums;

public enum Prioridade {
    BAIXA(1, "BAIXA"),
    MEDIA(2, "MEDIA"),
    ALTA(3, "ALTA");

    private Integer codigo;
    private String descricao;

    Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Prioridade x : Prioridade.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade inválid1!");
    }
}
