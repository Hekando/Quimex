package Vista;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import Controller.UsuarioController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario extends javax.swing.JFrame {

    public Usuario() {
        initComponents();
        this.setLocationRelativeTo(null); 
        this.getContentPane().setBackground(new java.awt.Color(245, 247, 250));
        jPanel1.setBackground(new java.awt.Color(59, 126, 70));
        cargar_lista();
        bloqueo_componentes();
        limpiar_campos();
    }
    
    private void cargar_lista(){
        UsuarioController u = new UsuarioController();
        
        ArrayList<String> listaDatos = u.listaUsuarios();
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String registro : listaDatos) {
            modelo.addElement(registro);
        }
        lis_usuario.setModel(modelo);
    }
    
    private void bloqueo_componentes(){
        txt_nombre.setEnabled(false);
        txt_correo.setEnabled(false);
        txt_pass1.setEnabled(false);
        txt_pass2.setEnabled(false);
        jList_rol.setEnabled(false);
        jList_estado.setEnabled(false);
    }
    
    private void habilitar_componentes(){
        txt_nombre.setEnabled(true);
        txt_correo.setEnabled(true);
        txt_pass1.setEnabled(true);
        txt_pass2.setEnabled(true);
        jList_rol.setEnabled(true);
        jList_estado.setEnabled(true);
    }
    
    private void limpiar_campos(){
        txt_nombre.setText("");
        txt_correo.setText("");
        txt_pass1.setText("");
        txt_pass2.setText("");
        jList_rol.clearSelection();
        jList_estado.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_user = new javax.swing.JLabel();
        lb_rol = new javax.swing.JLabel();
        btn_volver = new javax.swing.JPanel();
        lb_volver = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lis_usuario = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_rol = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_estado = new javax.swing.JList<>();
        txt_nombre = new javax.swing.JTextField();
        txt_correo = new javax.swing.JTextField();
        txt_pass1 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txt_pass2 = new javax.swing.JPasswordField();
        btn_editar = new javax.swing.JPanel();
        lb_editar = new javax.swing.JLabel();
        btn_nuevo = new javax.swing.JPanel();
        lb_nuevo = new javax.swing.JLabel();
        btn_eliminar = new javax.swing.JPanel();
        lb_eliminar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lb_nota = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setForeground(new java.awt.Color(255, 102, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quimex");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sistema de Asistencia");

        lb_user.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_user.setForeground(new java.awt.Color(255, 255, 255));
        lb_user.setText("marcelo@gmail.com");

        lb_rol.setForeground(new java.awt.Color(255, 255, 255));
        lb_rol.setText("Administrador");

        btn_volver.setBackground(new java.awt.Color(255, 255, 255));
        btn_volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_volverMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_volverMousePressed(evt);
            }
        });

        lb_volver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_volver.setForeground(new java.awt.Color(0, 0, 0));
        lb_volver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_volver.setText(" Volver al Menu");
        lb_volver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_volverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_volverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_volverMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_volverMousePressed(evt);
            }
        });

        javax.swing.GroupLayout btn_volverLayout = new javax.swing.GroupLayout(btn_volver);
        btn_volver.setLayout(btn_volverLayout);
        btn_volverLayout.setHorizontalGroup(
            btn_volverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_volver, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
        );
        btn_volverLayout.setVerticalGroup(
            btn_volverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_volver, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_rol, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_user, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(37, 37, 37)
                .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lb_user))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lb_rol)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        lis_usuario.setBorder(null);
        lis_usuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lis_usuario.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lis_usuario);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nombre Completo");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Correo");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Contraseña");

        jList_rol.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jList_rol.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Administrador", "Empleado" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList_rol);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Rol");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Estado");

        jList_estado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jList_estado.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Activo", "Inactivo" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList_estado);

        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(null);

        txt_correo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_correo.setBorder(null);

        txt_pass1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pass1.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Repita Contraseña");

        txt_pass2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pass2.setBorder(null);

        btn_editar.setBackground(new java.awt.Color(0, 153, 255));

        lb_editar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_editar.setForeground(new java.awt.Color(255, 255, 255));
        lb_editar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_editar.setText("Editar");
        lb_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_editarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_editarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btn_editarLayout = new javax.swing.GroupLayout(btn_editar);
        btn_editar.setLayout(btn_editarLayout);
        btn_editarLayout.setHorizontalGroup(
            btn_editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_editar, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );
        btn_editarLayout.setVerticalGroup(
            btn_editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_editar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        btn_nuevo.setBackground(new java.awt.Color(0, 153, 255));

        lb_nuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_nuevo.setForeground(new java.awt.Color(255, 255, 255));
        lb_nuevo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_nuevo.setText("Nuevo");
        lb_nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_nuevoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_nuevoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btn_nuevoLayout = new javax.swing.GroupLayout(btn_nuevo);
        btn_nuevo.setLayout(btn_nuevoLayout);
        btn_nuevoLayout.setHorizontalGroup(
            btn_nuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_nuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );
        btn_nuevoLayout.setVerticalGroup(
            btn_nuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        btn_eliminar.setBackground(new java.awt.Color(0, 153, 255));

        lb_eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        lb_eliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_eliminar.setText("Eliminar");
        lb_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lb_eliminarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lb_eliminarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout btn_eliminarLayout = new javax.swing.GroupLayout(btn_eliminar);
        btn_eliminar.setLayout(btn_eliminarLayout);
        btn_eliminarLayout.setHorizontalGroup(
            btn_eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_eliminarLayout.createSequentialGroup()
                .addComponent(lb_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );
        btn_eliminarLayout.setVerticalGroup(
            btn_eliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setText("Control de Usuarios");

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Agrega - Modifica - Elimina Usuarios");

        lb_nota.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_pass1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel6))
                                    .addComponent(txt_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(30, 30, 30)
                                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(jLabel13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(lb_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_pass1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(txt_pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_editar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_nota)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lb_volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_volverMouseClicked
        System.exit(0);
        // aqui falta codigo para volver al menu principal
    }//GEN-LAST:event_lb_volverMouseClicked

    private void lb_volverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_volverMouseEntered
        btn_volver.setBackground(new java.awt.Color(20, 150, 230));
        lb_volver.setForeground(Color.WHITE);
    }//GEN-LAST:event_lb_volverMouseEntered

    private void btn_volverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_volverMouseEntered
        //btn_volver.setBackground(Color.BLUE);
    }//GEN-LAST:event_btn_volverMouseEntered

    private void lb_volverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_volverMousePressed
        btn_volver.setBackground(new java.awt.Color(20, 160, 240));
    }//GEN-LAST:event_lb_volverMousePressed

    private void btn_volverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_volverMousePressed
        //btn_volver.setBackground(Color.BLUE);
    }//GEN-LAST:event_btn_volverMousePressed

    private void lb_volverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_volverMouseExited
        btn_volver.setBackground(Color.WHITE);
        lb_volver.setForeground(Color.BLACK);
    }//GEN-LAST:event_lb_volverMouseExited

    private void lb_nuevoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nuevoMousePressed
        btn_nuevo.setBackground(new java.awt.Color(20, 180, 250));
    }//GEN-LAST:event_lb_nuevoMousePressed

    private void lb_nuevoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nuevoMouseReleased
        btn_nuevo.setBackground(new java.awt.Color(20, 160, 240));
        
        if (lb_nuevo.getText().equals("Nuevo")) {
            lb_nuevo.setText("Guardar");
            lb_editar.setEnabled(false);
            lb_eliminar.setEnabled(false);
            lb_nota.setText("si no desea guardar nada deje los campos vacios y presione GUARDAR");
            lis_usuario.setEnabled(false);
            habilitar_componentes();
            limpiar_campos();
        }else if(lb_nuevo.getText().equals("Guardar")){
            String pin1 = new String(txt_pass1.getPassword()).trim();
            String pin2 = new String(txt_pass2.getPassword()).trim();
            
            if (txt_nombre.getText().trim().isEmpty() && txt_correo.getText().trim().isEmpty() && pin1.isEmpty() && pin2.isEmpty()) {
                //System.out.println("El campo está vacío. Por favor, completa este dato");
                lb_nuevo.setText("Nuevo");
                lb_editar.setEnabled(true);
                lb_eliminar.setEnabled(true);
                lb_nota.setText("");
                lis_usuario.setEnabled(true);
                limpiar_campos();
                bloqueo_componentes();
            } else if(txt_nombre.getText().trim().isEmpty() || txt_correo.getText().trim().isEmpty() || pin1.isEmpty() || pin2.isEmpty()){
                if(!txt_nombre.getText().trim().isEmpty() && !txt_correo.getText().trim().isEmpty() && !pin1.isEmpty() && !pin2.isEmpty()){
                    if (lis_usuario.getSelectedIndex() != -1) {
                        String regexCorreo = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
                        Pattern pattern = Pattern.compile(regexCorreo);
                        String textoValidar = txt_correo.getText().trim();
                        Matcher matcher = pattern.matcher(textoValidar);
                        
                        if (matcher.matches()) {
                            if(pin1.equals(pin2)){
                                
                                int rol = 0;
                                if(lis_usuario.getSelectedValue().toString().equals("Administrador")){
                                    rol = 1;
                                }else if(lis_usuario.getSelectedValue().toString().equals("Empleado")){
                                    rol = 2;
                                }
                                Boolean state = true;
                                if(lis_usuario.getSelectedValue().toString().equals("Activo")){
                                    state = true;
                                }else if(lis_usuario.getSelectedValue().toString().equals("Inactivo")){
                                state = false;
                                }
            
                                UsuarioController u = new UsuarioController();
                                u.insertar(txt_nombre.getText(), txt_correo.getText(), pin1, rol, state);
            
                                lb_nuevo.setText("Nuevo");
                                lb_editar.setEnabled(true);
                                lb_eliminar.setEnabled(true);
                                lb_nota.setText("");
                                lis_usuario.setEnabled(true);
                                limpiar_campos();
                                bloqueo_componentes();
                            }else{
                                lb_nota.setText("las contraseñas no coinciden");
                            }
                            //System.out.println("El correo electrónico es válido.");
                        } else {
                            lb_nota.setText("El formato de correo electrónico no es válido.");
                            txt_correo.requestFocus();
                        }
                    } else {
                        lb_nota.setText("Seleccione los Item Rol y Estado");
                    }
                }else{
                    lb_nota.setText("Debe llenar los campos solicitados");
                }
            }
        }
    }//GEN-LAST:event_lb_nuevoMouseReleased

    private void lb_editarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_editarMouseReleased
        btn_editar.setBackground(new java.awt.Color(20, 160, 240));
        
        if (!lis_usuario.isSelectionEmpty()) {
            if (lb_editar.getText().equals("Editar")) {
                lb_editar.setText("Guardar");
                lb_nuevo.setEnabled(false);
                lb_eliminar.setEnabled(false);
                lb_nota.setText("");
                habilitar_componentes();
                lis_usuario.setEnabled(false);
                //limpiar_campos();
            }else if(lb_editar.getText().equals("Guardar")){
                //
                lb_editar.setText("Editar");
                lb_nuevo.setEnabled(true);
                lb_eliminar.setEnabled(true);
                lb_nota.setText("");
                limpiar_campos();
                bloqueo_componentes();
                lis_usuario.setEnabled(true);
            }
            //System.out.println("Hay elementos seleccionados.");
        }
    }//GEN-LAST:event_lb_editarMouseReleased

    private void lb_editarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_editarMousePressed
        btn_editar.setBackground(new java.awt.Color(20, 180, 250));
    }//GEN-LAST:event_lb_editarMousePressed

    private void lb_eliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_eliminarMousePressed
        btn_eliminar.setBackground(new java.awt.Color(20, 180, 250));
    }//GEN-LAST:event_lb_eliminarMousePressed

    private void lb_eliminarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_eliminarMouseReleased
        btn_eliminar.setBackground(new java.awt.Color(20, 160, 240));
        
        if (!lis_usuario.isSelectionEmpty()) {
            //
            limpiar_campos();
        }
    }//GEN-LAST:event_lb_eliminarMouseReleased

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_editar;
    private javax.swing.JPanel btn_eliminar;
    private javax.swing.JPanel btn_nuevo;
    private javax.swing.JPanel btn_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList_estado;
    private javax.swing.JList<String> jList_rol;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb_editar;
    private javax.swing.JLabel lb_eliminar;
    private javax.swing.JLabel lb_nota;
    private javax.swing.JLabel lb_nuevo;
    private javax.swing.JLabel lb_rol;
    private javax.swing.JLabel lb_user;
    private javax.swing.JLabel lb_volver;
    private javax.swing.JList<String> lis_usuario;
    private javax.swing.JTextField txt_correo;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JPasswordField txt_pass1;
    private javax.swing.JPasswordField txt_pass2;
    // End of variables declaration//GEN-END:variables
}
