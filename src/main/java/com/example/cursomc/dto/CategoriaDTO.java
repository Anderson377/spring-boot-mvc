package com.example.cursomc.dto;

import com.example.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min=5, max=8, message = "O tamanho deve ser entre 5 e 8 caracteres")
    private String nome;

    public CategoriaDTO(){

    }

    //Para receber os dados de Categoria
    public CategoriaDTO (Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
}
