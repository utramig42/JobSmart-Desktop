/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author 277093
 */
public class TelaInicial extends javax.swing.JFrame {

    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() {
        initComponents();
    }
    
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize();

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logo = new javax.swing.JLabel();
        btnAbrirCadastroProduto = new javax.swing.JButton();
        btnAbrirVenda = new javax.swing.JButton();
        btnAbrirConsulta = new javax.swing.JButton();
        btnAbrirCadastroEstoque = new javax.swing.JButton();
        iconVendas = new javax.swing.JLabel();
        iconEstoque = new javax.swing.JLabel();
        iconProduto = new javax.swing.JLabel();
        iconConsulta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        setSize((int)d.getWidth(),(int) d.getHeight());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/JOBSMART™.png"))); // NOI18N

        btnAbrirCadastroProduto.setText("CADASTRO DE PRODUTO");
        btnAbrirCadastroProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCadastroProdutoActionPerformed(evt);
            }
        });

        btnAbrirVenda.setBackground(new java.awt.Color(0, 204, 0));
        btnAbrirVenda.setForeground(new java.awt.Color(255, 255, 255));
        btnAbrirVenda.setText("VENDAS");
        btnAbrirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirVendaActionPerformed(evt);
            }
        });

        btnAbrirConsulta.setText("CONSULTA");
        btnAbrirConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirConsultaActionPerformed(evt);
            }
        });

        btnAbrirCadastroEstoque.setText("CADASTRO DE ESTOQUE");
        btnAbrirCadastroEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCadastroEstoqueActionPerformed(evt);
            }
        });

        iconVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/venda_1.png"))); // NOI18N

        iconEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/estoque.png"))); // NOI18N

        iconProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cadastro.png"))); // NOI18N

        iconConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/binoculos.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(433, 433, 433)
                .addComponent(logo))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(iconVendas)
                .addGap(8, 8, 8)
                .addComponent(btnAbrirVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(iconProduto)
                .addGap(9, 9, 9)
                .addComponent(btnAbrirCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(iconEstoque)
                .addGap(9, 9, 9)
                .addComponent(btnAbrirCadastroEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(iconConsulta)
                .addGap(9, 9, 9)
                .addComponent(btnAbrirConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(logo)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconVendas)
                    .addComponent(btnAbrirVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconProduto)
                    .addComponent(btnAbrirCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconEstoque)
                    .addComponent(btnAbrirCadastroEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconConsulta)
                    .addComponent(btnAbrirConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        iconConsulta.getAccessibleContext().setAccessibleParent(iconVendas);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirVendaActionPerformed
        new TelaVendas().setVisible(true);
    }//GEN-LAST:event_btnAbrirVendaActionPerformed

    private void btnAbrirCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCadastroProdutoActionPerformed
        new TelaCadastroProduto().setVisible(true);
    }//GEN-LAST:event_btnAbrirCadastroProdutoActionPerformed

    private void btnAbrirCadastroEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCadastroEstoqueActionPerformed
       new TelaCadastroEstoque().setVisible(true);
    }//GEN-LAST:event_btnAbrirCadastroEstoqueActionPerformed

    private void btnAbrirConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirConsultaActionPerformed
        new TelaDetalheProduto().setVisible(true);
    }//GEN-LAST:event_btnAbrirConsultaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirCadastroEstoque;
    private javax.swing.JButton btnAbrirCadastroProduto;
    private javax.swing.JButton btnAbrirConsulta;
    private javax.swing.JButton btnAbrirVenda;
    private javax.swing.JLabel iconConsulta;
    private javax.swing.JLabel iconEstoque;
    private javax.swing.JLabel iconProduto;
    private javax.swing.JLabel iconVendas;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
