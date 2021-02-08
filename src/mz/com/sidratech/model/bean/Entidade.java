/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.sidratech.model.bean;

import java.util.List;

/**
 *
 * @author celso
 */
public abstract class Entidade {

    private String actividade;
    private String tipo;
    private String categoria;
    private String designacao;
    private String enderecoFisico;
    private String email;
    private int telefone;
    private String url;
    private int numeroTrabalhadores;
    private int homens;
    private int mulheres;
    private String documentacao;
    private String estadoLogin;
    private List<Usuario> usuario;

}
