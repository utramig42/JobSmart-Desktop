/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dominio.Estoque;
import dominio.Fornecedor;
import dominio.Funcionario;
import dominio.dados.EstoqueJpaController;
import dominio.dados.FornecedorJpaController;
import dominio.dados.ProdutoJpaController;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import util.Util;

/**
 *
 * @author 277065
 */
public class TelaCadastroEstoque extends javax.swing.JFrame {

    /**
     * Creates new form Tela
     * 
     */
    Funcionario funcionarioLogado;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JobSmart-DesktopPU");
    public TelaCadastroEstoque() {
        initComponents();
        FornecedorJpaController fjc = new FornecedorJpaController(emf);
        List<Fornecedor> fornecedores = fjc.findFornecedorEntities();
        DefaultComboBoxModel modelFornecedor = new DefaultComboBoxModel(fornecedores.toArray());
        comboFornecedor.setModel(modelFornecedor);
    }
    
    public TelaCadastroEstoque(Funcionario funcionario) {
        initComponents();
        funcionarioLogado = funcionario;
        FornecedorJpaController fjc = new FornecedorJpaController(emf);
        List<Fornecedor> fornecedores = fjc.findFornecedorEntities();
        DefaultComboBoxModel modelFornecedor = new DefaultComboBoxModel(fornecedores.toArray());
        comboFornecedor.setModel(modelFornecedor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoObservacao = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        btnCadastrarEstoque = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campoQuantidade = new javax.swing.JSpinner();
        campoDataFab = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        campoDataVal = new com.toedter.calendar.JDateChooser();
        comboFornecedor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        campoLote = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuLogo = new javax.swing.JMenu();
        menuVendas = new javax.swing.JMenu();
        menuCadastro = new javax.swing.JMenu();
        menuCadastroProdutos = new javax.swing.JMenuItem();
        menuCadastroEstoque = new javax.swing.JMenuItem();
        menuConsulta = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 768));

        jLabel1.setText("Código");

        jLabel2.setText("Quantidade de Produtos");

        jLabel4.setText("Fornecedor");

        jLabel5.setText("Valor");

        campoObservacao.setColumns(20);
        campoObservacao.setRows(5);
        jScrollPane1.setViewportView(campoObservacao);

        jLabel6.setText("Observações");

        btnCadastrarEstoque.setBackground(new java.awt.Color(153, 255, 102));
        btnCadastrarEstoque.setText("Cadastrar");
        btnCadastrarEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEstoqueActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-contatos-50 (1).png"))); // NOI18N

        jLabel9.setText("JOBSMASTER");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Webp.net-resizeimage.png"))); // NOI18N

        campoDataFab.setMaxSelectableDate(new Date());
        campoDataFab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoDataFabFocusLost(evt);
            }
        });

        jLabel11.setText("Data de Fabricação");

        jLabel12.setText("Data de Validade");

        campoDataVal.setMinSelectableDate(new Date());

        comboFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Lote");

        jButton1.setText("Atualizar Cadastro exitente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        menuLogo.setText("Home");
        menuLogo.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuLogoMenuSelected(evt);
            }
        });
        jMenuBar1.add(menuLogo);

        menuVendas.setText("Vendas");
        menuVendas.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuVendasMenuSelected(evt);
            }
        });
        jMenuBar1.add(menuVendas);

        menuCadastro.setText("Cadastro");

        menuCadastroProdutos.setText("Produtos");
        menuCadastroProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroProdutosActionPerformed(evt);
            }
        });
        menuCadastro.add(menuCadastroProdutos);

        menuCadastroEstoque.setText("Estoque");
        menuCadastro.add(menuCadastroEstoque);

        jMenuBar1.add(menuCadastro);

        menuConsulta.setText("Consulta");
        menuConsulta.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                menuConsultaMenuSelected(evt);
            }
        });
        jMenuBar1.add(menuConsulta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1)
                                    .addComponent(campoDataFab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addComponent(comboFornecedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(167, 167, 167)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(campoDataVal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                        .addComponent(campoQuantidade, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addComponent(jLabel3)
                            .addComponent(campoLote, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(272, 272, 272)
                                        .addComponent(btnCadastrarEstoque))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel8)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(campoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoDataFab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoDataVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarEstoque)
                    .addComponent(jButton1))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuLogoMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuLogoMenuSelected
      this.dispose();
    }//GEN-LAST:event_menuLogoMenuSelected

    private void menuVendasMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuVendasMenuSelected
       Util.instanciaVenda(this,funcionarioLogado);
    }//GEN-LAST:event_menuVendasMenuSelected

    private void menuCadastroProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroProdutosActionPerformed
       Util.instanciaCadastroProduto(this,funcionarioLogado);
    }//GEN-LAST:event_menuCadastroProdutosActionPerformed

    private void menuConsultaMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuConsultaMenuSelected
       Util.instanciaConsultaProduto(this,funcionarioLogado);
    }//GEN-LAST:event_menuConsultaMenuSelected

    private void btnCadastrarEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEstoqueActionPerformed
        EstoqueJpaController ejc = new EstoqueJpaController(emf);
        Estoque estoque = new Estoque();
        ProdutoJpaController pjc = new ProdutoJpaController(emf);
       /* int i = (int) ejc.getLastIdEstoque();
        System.out.println(i); //PENDENTE*/
        
        //estoque = new Estoque((int) ejc.getLastIdEstoque(),estoque.getFornecedor().getIdFor(),estoque.getProduto().getIdProd());
        try {
            //estoque.setEstoquePK(pk); //Pendente definir qual será a EstoquePK de um novo registro de Estoque
            
            //Instanciando e setando dados de estoque
            estoque.setIdFor((Fornecedor) comboFornecedor.getSelectedItem());
            estoque.setIdProd(pjc.findProduto(Integer.parseInt(campoCodigo.getText())));
            estoque.setQtdProdEst((int) campoQuantidade.getValue());
            estoque.setVlrCustoEst( Double.parseDouble(campoValor.getText()));
            estoque.setVlrVendaEst(estoque.getVlrCustoEst() * 2);
            estoque.setLoteEst(campoLote.getText());
            estoque.setObsEst(campoObservacao.getText());
            if(validaDataFab()){
                estoque.setDtFabEst(campoDataFab.getDate());
            }
            estoque.setDtValEst(campoDataVal.getDate());
            estoque.setDtCadEst(new Date());
            //Inserindo no banco
            ejc.create(estoque);
        } catch (Exception ex) {
            Logger.getLogger(TelaCadastroEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCadastrarEstoqueActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     Util.instanciaAtualizaEstoque(this,funcionarioLogado);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void campoDataFabFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoDataFabFocusLost
        validaDataFab();
    }//GEN-LAST:event_campoDataFabFocusLost
    
    public boolean validaDataFab(){
        if(campoDataFab.getDate().after(new Date()) || campoDataFab.getDate().after(campoDataVal.getDate())){
            JOptionPane.showMessageDialog(this, "Data de fabricação não pode ser maior que hoje ou data de validade");
            return false;
        }
        return true;
    }
    
     public boolean validaDataVal(){
        if(campoDataVal.getDate().before(new Date()) || campoDataVal.getDate().before(campoDataFab.getDate())){
            JOptionPane.showMessageDialog(this, "Data de Validade não pode ser menor que hoje ou data de fabricação");
            return false;
        }
        return true;
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
            java.util.logging.Logger.getLogger(TelaCadastroEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroEstoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrarEstoque;
    private javax.swing.JTextField campoCodigo;
    private com.toedter.calendar.JDateChooser campoDataFab;
    private com.toedter.calendar.JDateChooser campoDataVal;
    private javax.swing.JTextField campoLote;
    private javax.swing.JTextArea campoObservacao;
    private javax.swing.JSpinner campoQuantidade;
    private javax.swing.JTextField campoValor;
    private javax.swing.JComboBox<String> comboFornecedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenuItem menuCadastroEstoque;
    private javax.swing.JMenuItem menuCadastroProdutos;
    private javax.swing.JMenu menuConsulta;
    private javax.swing.JMenu menuLogo;
    private javax.swing.JMenu menuVendas;
    // End of variables declaration//GEN-END:variables
}
