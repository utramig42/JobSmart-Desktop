/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
    
    public static void instanciaVenda( JFrame obj){
        new TelaVendas().setVisible(true);
        obj.dispose();
    }
    
    public static void instanciaCadastroProduto( JFrame obj){
        new TelaCadastroProduto().setVisible(true);
        obj.dispose();
    }
    
    public static void instanciaCadastroEstoque( JFrame obj){
        new TelaCadastroEstoque().setVisible(true);
        obj.dispose();
    }
    
    public static void instanciaConsultaProduto( JFrame obj){
        new TelaDetalheProduto().setVisible(true);
        obj.dispose();
    }
    
        public static void instanciaAtualizaEstoque( JFrame obj){
        new Atualizacaodeestoque().setVisible(true);
        obj.dispose();
    }
}
