/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dominio.Funcionario;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import visao.Atualizacaodeestoque;
import visao.TelaCadastroEstoque;
import visao.TelaCadastroProduto;
import visao.TelaDetalheProduto;
import visao.TelaVendas;

/**
 *
 * @author 277017
 */
public class Util {
    
    
    public static void instanciaVenda( JFrame obj, Funcionario funcionario){
        if(!(funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 4 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 5 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 6 )){
        new TelaVendas(funcionario).setVisible(true);
        obj.dispose();
        }else {
            JOptionPane.showMessageDialog(obj, "Você não tem acesso");
        }
    }
    
    public static void instanciaCadastroProduto( JFrame obj, Funcionario funcionario){
        if(!(funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 4 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 5 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 6 )){
        new TelaCadastroProduto(funcionario).setVisible(true);
        obj.dispose();
        }else {
            JOptionPane.showMessageDialog(obj, "Você não tem acesso");
        }
    }
    
    public static void instanciaCadastroEstoque( JFrame obj, Funcionario funcionario){
        if(!(funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 4 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 5 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 6 )){
        new TelaCadastroEstoque(funcionario).setVisible(true);
        obj.dispose();
        }else {
            JOptionPane.showMessageDialog(obj, "Você não tem acesso");
        }
    }
    
    public static void instanciaConsultaProduto( JFrame obj, Funcionario funcionario){
        if(!(funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 4 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 5 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 6 )){
        new TelaDetalheProduto(funcionario).setVisible(true);
        obj.dispose();
        }else {
            JOptionPane.showMessageDialog(obj, "Você não tem acesso");
        }
    }
    
        public static void instanciaAtualizaEstoque( JFrame obj, Funcionario funcionario){
        if(!(funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 4 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 5 || 
                funcionario.getIdCargo().getIdPerfil().getIdPerfil() == 6 )){
        new Atualizacaodeestoque(funcionario).setVisible(true);
        obj.dispose();
        }else {
            JOptionPane.showMessageDialog(obj, "Você não tem acesso");
        }
    }
        
        
    public static String nomeUserLogado(Funcionario funcionario){
        return "Olá "+funcionario.getNmFun()+"!";
    }
}
