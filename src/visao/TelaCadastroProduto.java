/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dominio.Categoria;
import dominio.Marca;
import dominio.Produto;
import dominio.dados.CategoriaJpaController;
import dominio.dados.MarcaJpaController;
import dominio.dados.ProdutoJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import util.Util;

/**
 *
 * @author 277065
 */
public class TelaCadastroProduto extends javax.swing.JFrame {

    /**
     * Creates new form Tela
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JobSmart-DesktopPU");
    public TelaCadastroProduto() {
        initComponents();
        
        MarcaJpaController mjc = new MarcaJpaController(emf);
        CategoriaJpaController cjc = new CategoriaJpaController(emf);
        List<Marca> marcas = mjc.findMarcaEntities();
        
        List<Categoria> categorias = cjc.findCategoriaEntities();
        DefaultComboBoxModel modelMarca = new DefaultComboBoxModel(marcas.toArray());
        comboMarca.setModel(modelMarca);
        
        DefaultComboBoxModel modelCategoria = new DefaultComboBoxModel(categorias.toArray());
        comboCategoria.setModel(modelCategoria);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboCategoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoObs = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        checkAtivo = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        campoQtdMin = new javax.swing.JSpinner();
        comboMarca = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuLogo = new javax.swing.JMenu();
        menuVendas = new javax.swing.JMenu();
        menuCadastro = new javax.swing.JMenu();
        menuCadastroProdutos = new javax.swing.JMenuItem();
        menuCadastroEstoque = new javax.swing.JMenuItem();
        menuConsulta = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 768));

        campoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeActionPerformed(evt);
            }
        });

        jLabel2.setText("Nome");

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Categoria");

        jLabel4.setText("Quantidade Miníma");

        campoObs.setColumns(20);
        campoObs.setRows(5);
        jScrollPane1.setViewportView(campoObs);

        jLabel6.setText("Observações");

        checkAtivo.setText("Ativo/Inativo");

        jButton1.setBackground(new java.awt.Color(153, 255, 102));
        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-contatos-50 (1).png"))); // NOI18N

        jLabel9.setText("JOBSMASTER");

        jLabel11.setText("Marca");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Webp.net-resizeimage.png"))); // NOI18N

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
        menuCadastroEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadastroEstoqueActionPerformed(evt);
            }
        });
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addComponent(jLabel12))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(5, 5, 5)
                                            .addComponent(jLabel3)
                                            .addGap(257, 257, 257)
                                            .addComponent(jLabel11))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(60, 60, 60)
                                            .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(campoQtdMin)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(153, 153, 153))))
                                .addComponent(campoNome))
                            .addGap(26, 26, 26))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(checkAtivo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE))))
                .addComponent(jLabel8)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap(56, Short.MAX_VALUE)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel9))
                                            .addComponent(jLabel12))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoQtdMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkAtivo)
                    .addComponent(jButton1))
                .addContainerGap(283, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeActionPerformed

    private void menuLogoMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuLogoMenuSelected
     this.dispose();
    }//GEN-LAST:event_menuLogoMenuSelected

    private void menuVendasMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuVendasMenuSelected
      Util.instanciaVenda(this);
    }//GEN-LAST:event_menuVendasMenuSelected

    private void menuCadastroProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroProdutosActionPerformed
      
    }//GEN-LAST:event_menuCadastroProdutosActionPerformed

    private void menuCadastroEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadastroEstoqueActionPerformed
      Util.instanciaCadastroEstoque(this);
    }//GEN-LAST:event_menuCadastroEstoqueActionPerformed

    private void menuConsultaMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_menuConsultaMenuSelected
       Util.instanciaConsultaProduto(this);
    }//GEN-LAST:event_menuConsultaMenuSelected

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        //Instancia dos controladores de entidade e operações com banco
       
        ProdutoJpaController pjc = new ProdutoJpaController(emf);
        //Instancia de produto para receber os valores
        Produto produto = new Produto();
        //Setagem de valores, com base nos campos 
        produto.setIdProd(null); //Id Null por ser autoIncrement
        produto.setNmProd(campoNome.getText());
        Marca marca = (Marca) comboMarca.getSelectedItem(); //Pegando a Marca selecionada, puxando do banco
        produto.setIdMarca(marca);//Setando o Id a partir da Marca
        Categoria categoria = (Categoria)comboCategoria.getSelectedItem(); //Mesmo da marca
        produto.setIdCat(categoria); //Porém com categoria
        produto.setQtdMinProd((int) campoQtdMin.getValue());
        produto.setObsProd(campoObs.getText());
        produto.setAtivoProd(checkAtivo.isSelected()); //Pegando valor boolean do checkbox
        pjc.create(produto); //Inserir objeto Produto no banco
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextArea campoObs;
    private javax.swing.JSpinner campoQtdMin;
    private javax.swing.JCheckBox checkAtivo;
    private javax.swing.JComboBox<String> comboCategoria;
    private javax.swing.JComboBox<String> comboMarca;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
