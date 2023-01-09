/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.web.controller;

import br.com.unincor.sistemacomanda.model.dao.ClienteDao;
import br.com.unincor.sistemacomanda.model.domain.Cliente;
import br.com.unincor.sistemacomanda.model.exception.ClienteException;
import br.com.unincor.sistemacomanda.model.servicos.ClienteService;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

/**
 * @author otavi
 */
@Getter
@Setter
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private Cliente cliente;
    private List<Cliente> clientes;
    private List<Cliente> clientesFilter;

    public ClienteBean() {
        pesquisar();
    }

    public void salvar() {
        try {
            ClienteService clienteService = new ClienteService();
            clienteService.salvarCliente(cliente);
            /* Gera uma mensagem de sucesso */
            FacesContext.getCurrentInstance().addMessage("",
                    new FacesMessage("Salvo com sucesso!"));
            /* Chamamos o cancelar para limpar o nosso objeto após salvar */
            cancelar();
            pesquisar();
        } catch (ClienteException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }
    }

    /* Ao cancelar criamos um novo produto para que a tela atualize */
    public void cancelar() {
        this.cliente = null;
    }

    public void novoCliente() {
        this.cliente = new Cliente();
    }

    public void pesquisar() {
        this.clientes = new ClienteDao().findAll();
    }

    public void editar(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public static void main(String[] args) {
        new ClienteDao().findAll();
    }
    
}
