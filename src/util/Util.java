/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dominio.Funcionario;
import javax.swing.JFrame;
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
        
        new TelaVendas(funcionario).setVisible(true);
        obj.dispose();
    }
    
    public static void instanciaCadastroProduto( JFrame obj, Funcionario funcionario){
        new TelaCadastroProduto(funcionario).setVisible(true);
        obj.dispose();
    }
    
    public static void instanciaCadastroEstoque( JFrame obj, Funcionario funcionario){
        new TelaCadastroEstoque(funcionario).setVisible(true);
        obj.dispose();
    }
    
    public static void instanciaConsultaProduto( JFrame obj, Funcionario funcionario){
        new TelaDetalheProduto(funcionario).setVisible(true);
        obj.dispose();
    }
    
        public static void instanciaAtualizaEstoque( JFrame obj, Funcionario funcionario){
        new Atualizacaodeestoque(funcionario).setVisible(true);
        obj.dispose();
    }
}
