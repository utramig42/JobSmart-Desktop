/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dominio.FormaPagamento;
import dominio.ItensVenda;
import dominio.Pagamento;
import dominio.Venda;
import dominio.dados.FormaPagamentoJpaController;
import dominio.dados.ItensVendaJpaController;
import dominio.dados.PagamentoJpaController;
import dominio.dados.VendaJpaController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author 275043
 */
public class TelaPagamento extends javax.swing.JFrame {

    Venda venda;
    Pagamento pagamento;
    double valorPendente;
    List<ItensVenda> itens = new ArrayList<>();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JobSmart-DesktopPU");
    FormaPagamentoJpaController fpc = new FormaPagamentoJpaController(emf);
    ItensVendaJpaController ivc = new ItensVendaJpaController(emf);
    PagamentoJpaController pjc = new PagamentoJpaController(emf);

    List<FormaPagamento> formas = fpc.findFormaPagamentoEntities();
    List<Pagamento> pagamentos = new ArrayList<>();
    DefaultComboBoxModel modelFormas = new DefaultComboBoxModel(formas.toArray());

    /**
     * Creates new form TelaPagamento
     */
    public TelaPagamento() {
        initComponents();

        campoFormaPagamento.setModel(modelFormas);

    }

    public TelaPagamento(Venda venda) {
        initComponents();
        this.venda = venda;
        valorPendente = venda.getVlrVenda();

        campoValorTotal.setText(Double.toString(valorPendente));
        itens = venda.getItensVendaList();
        instanciaItens();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoValorRecebido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        campoValorTotal = new javax.swing.JLabel();
        finalizarCompra = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        campoFormaPagamento = new javax.swing.JComboBox<>();
        valorTroco = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 768));

        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        label1.setText("Opção de Pagamento");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/JOBSMART.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("TROCO");
        jLabel2.setMaximumSize(new java.awt.Dimension(40, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(40, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 20));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Valor Recebido");

        campoValorRecebido.setPreferredSize(new java.awt.Dimension(255, 30));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setText("TOTAL");
        jLabel5.setMaximumSize(new java.awt.Dimension(40, 20));
        jLabel5.setMinimumSize(new java.awt.Dimension(40, 20));
        jLabel5.setPreferredSize(new java.awt.Dimension(40, 20));

        campoValorTotal.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        campoValorTotal.setText("R$ 0.00");
        campoValorTotal.setMaximumSize(new java.awt.Dimension(130, 50));
        campoValorTotal.setMinimumSize(new java.awt.Dimension(130, 50));
        campoValorTotal.setPreferredSize(new java.awt.Dimension(130, 50));

        finalizarCompra.setBackground(new java.awt.Color(0, 204, 0));
        finalizarCompra.setForeground(new java.awt.Color(255, 255, 255));
        finalizarCompra.setText("Finalizar Compra");
        finalizarCompra.setMaximumSize(new java.awt.Dimension(255, 30));
        finalizarCompra.setMinimumSize(new java.awt.Dimension(255, 30));
        finalizarCompra.setPreferredSize(new java.awt.Dimension(255, 30));
        finalizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarCompraActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.setPreferredSize(new java.awt.Dimension(255, 30));

        campoFormaPagamento.setModel(modelFormas);
        campoFormaPagamento.setMinimumSize(new java.awt.Dimension(255, 30));
        campoFormaPagamento.setPreferredSize(new java.awt.Dimension(255, 30));
        campoFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFormaPagamentoActionPerformed(evt);
            }
        });

        valorTroco.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        valorTroco.setText("R$ 0.00");
        valorTroco.setMaximumSize(new java.awt.Dimension(130, 50));
        valorTroco.setMinimumSize(new java.awt.Dimension(130, 50));
        valorTroco.setPreferredSize(new java.awt.Dimension(130, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(330, 330, 330)
                                .addComponent(finalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valorTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campoFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(campoValorRecebido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(180, 180, 180))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170))))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(campoFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valorTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFormaPagamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFormaPagamentoActionPerformed

    private void finalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarCompraActionPerformed

        pagamento = new Pagamento(pjc.getPagamentoCount() + 1);
        pagamento.setIdVenda(venda);
        pagamento.setIdForma((FormaPagamento) campoFormaPagamento.getSelectedItem());
        pagamento.setVlrPag(Double.parseDouble(campoValorRecebido.getText()));
        pagamento.setVlrTrocoPag(0);
        double valorAPagar = valorPendente;
        valorPendente -= Double.parseDouble(campoValorRecebido.getText());
        pagamentos.add(pagamento);

        if (validaPagamento(valorAPagar)) {
            try {
                VendaJpaController vjc = new VendaJpaController(emf);
                vjc.create(venda);
                pjc.createWithList(pagamentos);
                venda.setPagamentoList(pagamentos);
                ivc.createWithList(itens); //Itens e pagamento estão estourando NullPointer

                JOptionPane.showMessageDialog(this, "Venda Finalizada!");
                this.dispose();

            } catch (Exception ex) {
                Logger.getLogger(TelaPagamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_finalizarCompraActionPerformed

    public boolean validaPagamento(double valorAPagar) {
        if (valorPendente <= 0) {
            System.out.println("Valor pago");
            campoValorTotal.setText("R$ 0");
            valorPendente = 0;
            defineTroco(Double.parseDouble(campoValorRecebido.getText()), valorAPagar);
            return true;
        }
        campoValorTotal.setText(Double.toString(valorPendente));

        return false;
    }

    public double defineTroco(double valorPago, double valorAPagar) {

        double troco = valorPago - valorAPagar;
        valorTroco.setText(Double.toString(troco));
        pagamento.setVlrTrocoPag(troco);
        return troco;
    }

    public void instanciaItens() {
        for (ItensVenda item : itens) {
            item.setIditensvenda(ivc.getItensVendaCount());
            item.setIdVenda(venda);

        }
    }

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
            java.util.logging.Logger.getLogger(TelaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPagamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPagamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> campoFormaPagamento;
    private javax.swing.JTextField campoValorRecebido;
    private javax.swing.JLabel campoValorTotal;
    private javax.swing.JButton finalizarCompra;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private java.awt.Label label1;
    private javax.swing.JLabel valorTroco;
    // End of variables declaration//GEN-END:variables
}
