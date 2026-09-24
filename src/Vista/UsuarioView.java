package Vista;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import Controller.UsuarioController;
import Model.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class UsuarioView extends javax.swing.JFrame {
    int id=0;
    Usuario u = new Usuario();
    
    
    /**
     * Crea una nueva ventana para la gestión y control de usuarios.
     * Configura el diseño visual, inicializa los componentes de la interfaz, 
     * establece los colores corporativos y muestra la información del usuario en sesión.
     * 
     * @param user Objeto {@link Model.Usuario} que representa al usuario autenticado actual.
     */
    public UsuarioView(Usuario user) {
        initComponents();
        this.setLocationRelativeTo(null); 
        this.getContentPane().setBackground(new java.awt.Color(245, 247, 250));
        jPanel1.setBackground(new java.awt.Color(59, 126, 70));
        btn_nuevo.setBackground(new java.awt.Color(30, 120, 200));//azul
        btn_editar.setBackground(new java.awt.Color(30, 120, 200));//azul
        btn_eliminar.setBackground(new java.awt.Color(30, 120, 200));//azul
        
        cargar_lista();
        bloqueo_componentes();
        limpiar_campos();
        u = user;
        lb_user.setText(u.getNombre());
        if(u.getId_rol() == 1){
            lb_rol.setText("Adminitrador");
        }else{
            lb_rol.setText("Empleado");
        }
    }
    
    
    /**
     * Consulta la lista completa de usuarios registrados mediante el controlador
     * y la renderiza en el componente visual JList.
     * Limpia cualquier selección previa de forma segura.
     */
    private void cargar_lista(){
        UsuarioController u = new UsuarioController();
        ArrayList<String> listaDatos = u.listaUsuarios();
        
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String registro : listaDatos) {
            modelo.addElement(registro);
        }
        lis_usuario.setModel(modelo);
        lis_usuario.clearSelection();
    }
    
    private void re_cargar_lista(){
        DefaultListModel modelo1 = (DefaultListModel) lis_usuario.getModel();
        modelo1.removeAllElements();
        lis_usuario.clearSelection();
    }
    
    
    /**
     * Cambia el estado de los componentes del formulario a deshabilitados (solo lectura)
     * y restablece el fondo a color blanco para denotar bloqueo de edición.
     */
    private void bloqueo_componentes(){
        txt_nombre.setEnabled(false);
        txt_correo.setEnabled(false);
        txt_pass1.setEnabled(false);
        txt_pass2.setEnabled(false);
        jList_rol.setEnabled(false);
        jList_estado.setEnabled(false);
        
        txt_nombre.setBackground(Color.white);
        txt_correo.setBackground(Color.white);
        txt_pass1.setBackground(Color.white);
        txt_pass2.setBackground(Color.white);
        jList_rol.setBackground(Color.white);
        jList_estado.setBackground(Color.white);
    }
    
    
    /**
     * Habilita todos los campos de entrada de texto y listas de selección del formulario,
     * cambia su color de fondo para indicar un estado activo de edición o inserción,
     * y asigna el foco del teclado al campo de nombre completo.
     */
    private void habilitar_componentes(){
        txt_nombre.setEnabled(true);
        txt_correo.setEnabled(true);
        txt_pass1.setEnabled(true);
        txt_pass2.setEnabled(true);
        jList_rol.setEnabled(true);
        jList_estado.setEnabled(true);
        
        txt_nombre.setBackground(new java.awt.Color(130, 255, 80));
        txt_correo.setBackground(new java.awt.Color(130, 255, 80));
        txt_pass1.setBackground(new java.awt.Color(130, 255, 80));
        txt_pass2.setBackground(new java.awt.Color(130, 255, 80));
        jList_rol.setBackground(new java.awt.Color(130, 255, 80));
        jList_estado.setBackground(new java.awt.Color(130, 255, 80));
        
        txt_nombre.requestFocusInWindow();
    }
    
    private void limpiar_campos(){
        txt_nombre.setText("");
        txt_correo.setText("");
        txt_pass1.setText("");
        txt_pass2.setText("");
        jList_rol.clearSelection();
        jList_estado.clearSelection();
    }
    
    
    /**
     * Despliega un cuadro de diálogo de confirmación para eliminar el usuario seleccionado.
     * Si el usuario confirma la acción, se comunica con el controlador para borrar el registro
     * de la base de datos, restablece los componentes de la interfaz, vacía los campos 
     * y refresca la lista visual de registros.
     */
    private void panelEliminar(){
        int respuesta = JOptionPane.showConfirmDialog(
        this, 
            "¿Estás seguro de que deseas eliminar este registro?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );
        if (respuesta == JOptionPane.YES_OPTION) {
            System.out.println("Registro eliminado.");
                UsuarioController u = new UsuarioController();
                u.eliminar(id);
                lb_nota.setText("");
                lb_nota.setForeground(Color.black);
                lis_usuario.setEnabled(true);
                limpiar_campos();
                //bloqueo_componentes();
                re_cargar_lista();
                cargar_lista();
                System.out.println("eliminado exito");
                btn_eliminar.setBackground(new java.awt.Color(30, 120, 200));//azul
                lb_eliminar.setText("Eliminar Usuario");
                JOptionPane.showMessageDialog(null,"Usuario eliminado con exito","Información",JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Operación cancelada.");
            btn_eliminar.setBackground(new java.awt.Color(30, 120, 200));//azul
        }
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
        lis_usuario.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lis_usuarioValueChanged(evt);
            }
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

        txt_nombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(null);

        txt_correo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_correo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_correo.setBorder(null);

        txt_pass1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_pass1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pass1.setBorder(null);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Repita Contraseña");

        txt_pass2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_pass2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pass2.setBorder(null);

        btn_editar.setBackground(new java.awt.Color(0, 153, 255));

        lb_editar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_editar.setForeground(new java.awt.Color(255, 255, 255));
        lb_editar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_editar.setText("Editar Usuario");
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
        lb_nuevo.setText("Nuevo Usuario");
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
        lb_eliminar.setText("Eliminar Usuario");
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

        lb_nota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                        .addGap(152, 152, 152)
                        .addComponent(lb_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lb_volverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_volverMouseClicked
        this.dispose(); // Cierra y destruye la ventana actual
        MenuView v = new MenuView(u);
        if (v != null) {
            v.setVisible(true); // Hace visible la ventana anterior
        }
        //System.exit(0);
    }//GEN-LAST:event_lb_volverMouseClicked

    private void lb_volverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_volverMouseEntered
        btn_volver.setBackground(new java.awt.Color(20, 150, 230));
        lb_volver.setForeground(Color.WHITE);
    }//GEN-LAST:event_lb_volverMouseEntered

    private void btn_volverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_volverMouseEntered
        
    }//GEN-LAST:event_btn_volverMouseEntered

    private void lb_volverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_volverMousePressed
        btn_volver.setBackground(new java.awt.Color(20, 160, 240));
    }//GEN-LAST:event_lb_volverMousePressed

    private void btn_volverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_volverMousePressed
        
    }//GEN-LAST:event_btn_volverMousePressed

    private void lb_volverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_volverMouseExited
        btn_volver.setBackground(Color.WHITE);
        lb_volver.setForeground(Color.BLACK);
    }//GEN-LAST:event_lb_volverMouseExited

    private void lb_nuevoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nuevoMousePressed
        if (lb_nuevo.isEnabled() && lb_nuevo.getText().equals("Nuevo Usuario")){
            btn_nuevo.setBackground(new java.awt.Color(30, 150, 220));//celeste claro
        }else if(lb_nuevo.isEnabled() && lb_nuevo.getText().equals("Guardar")){
            btn_nuevo.setBackground(new java.awt.Color(40, 220, 140));//verde claro
        }
    }//GEN-LAST:event_lb_nuevoMousePressed

    
    /**
     * Maneja el ciclo de vida de la inserción de un nuevo usuario en el sistema.
     * Funciona como una máquina de estados basada en el texto de la etiqueta:
     * <ul>
     *   <li>Si el texto es "Nuevo Usuario": Limpia y habilita los componentes visuales para la captura de datos.</li>
     *   <li>Si el texto es "Guardar": Valida campos obligatorios, aplica una expresión regular (Regex) 
     *       para verificar el formato de correo electrónico, valida la coincidencia de contraseñas 
     *       y procede a insertar el registro en la base de datos a través del controlador.</li>
     * </ul>
     * 
     * @param evt Evento del ratón que dispara la acción al soltar el botón.
     */
    private void lb_nuevoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_nuevoMouseReleased
        if (lb_nuevo.isEnabled() && lb_nuevo.getText().equals("Nuevo Usuario")) {
            lb_nuevo.setText("Guardar");
            lb_editar.setEnabled(false);
            lb_eliminar.setEnabled(false);
            //btn_editar.setEnabled(false);
            //btn_eliminar.setEnabled(false);
            lb_nota.setText("si no desea agregar un nuevo Usuario deje los campos vacios y presione GUARDAR");
            lis_usuario.setEnabled(false);
            habilitar_componentes();
            limpiar_campos();
            btn_nuevo.setBackground(new java.awt.Color(40, 180, 120));//verde
            System.out.println("se habilito los campos para ingresar datos");  //------------------------------------>>
            
        }else if(lb_nuevo.isEnabled() && lb_nuevo.getText().equals("Guardar")){
            String pin1 = new String(txt_pass1.getPassword()).trim();
            String pin2 = new String(txt_pass2.getPassword()).trim();
            
            if (!txt_nombre.getText().trim().isEmpty() && !txt_correo.getText().trim().isEmpty() && !pin1.isEmpty() && !pin2.isEmpty()){
                System.out.println("todos los campos estan llenos");
                String regexCorreo = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$";
                Pattern pattern = Pattern.compile(regexCorreo);
                String textoValidar = txt_correo.getText().trim();
                Matcher matcher = pattern.matcher(textoValidar);
                
                if(matcher.matches() && pin1.equals(pin2) && jList_rol.getSelectedIndex() != -1 && jList_estado.getSelectedIndex() != -1){
                    //se procede a guardar a la base de datos
                    System.out.println("se pre-guarda en base de datos");
                                int rol = 0;
                                if(jList_rol.getSelectedValue().toString().equals("Administrador")){
                                    rol = 1;
                                }else if(jList_rol.getSelectedValue().toString().equals("Empleado")){
                                    rol = 2;
                                }
                                Boolean state = true;
                                if(jList_estado.getSelectedValue().toString().equals("Activo")){
                                    state = true;
                                }else if(jList_estado.getSelectedValue().toString().equals("Inactivo")){
                                state = false;
                                }
            
                                UsuarioController u = new UsuarioController();
                                u.insertar(txt_nombre.getText(), txt_correo.getText(), pin1, rol, state);
            
                                lb_nuevo.setText("Nuevo Usuario");
                                lb_editar.setEnabled(true);
                                lb_eliminar.setEnabled(true);
                                //btn_editar.setEnabled(true);
                                //btn_eliminar.setEnabled(true);
                                lb_nota.setText("");
                                lis_usuario.setEnabled(true);
                                limpiar_campos();
                                bloqueo_componentes();
                                re_cargar_lista();
                                cargar_lista();
                                JOptionPane.showMessageDialog(null,"Usuario agregado con exito","Información",JOptionPane.INFORMATION_MESSAGE);
                                
                    System.out.println("guardardo exito");
                    btn_nuevo.setBackground(new java.awt.Color(30, 120, 200));//azul
                }else{
                    if(!matcher.matches()){
                        System.out.println("correo erroneo");
                        lb_nota.setText("Debe escribir un correo valido");
                        btn_nuevo.setBackground(new java.awt.Color(40, 180, 120));//verde
                    }else if(!pin1.equals(pin2)){
                        System.out.println("contraseñas no coinciden");
                        lb_nota.setText("las contraseñas no coinciden");
                        btn_nuevo.setBackground(new java.awt.Color(40, 180, 120));//verde
                    }else if(jList_rol.getSelectedIndex() == -1 || jList_estado.getSelectedIndex() == -1){
                        System.out.println("rol o estado no seleccionado");
                        lb_nota.setText("debe selecionar un Rol y Estado del Usuario");
                        btn_nuevo.setBackground(new java.awt.Color(40, 180, 120));//verde
                    }
                }
            }else if(!txt_nombre.getText().trim().isEmpty() || !txt_correo.getText().trim().isEmpty() || !pin1.isEmpty() || !pin2.isEmpty()){
                //algunos de los campos estan vacios
                System.out.println("algunos campos vacios");
                lb_nota.setText("Debe llenar los campos solicitados");
                btn_nuevo.setBackground(new java.awt.Color(40, 180, 120));//verde
            }else{
                //todo los campos estan vacios
                System.out.println("todos vacios");
                lb_nuevo.setText("Nuevo Usuario");
                lb_editar.setEnabled(true);
                lb_eliminar.setEnabled(true);
                btn_editar.setEnabled(true);
                btn_eliminar.setEnabled(true);
                lb_nota.setText("");
                lis_usuario.setEnabled(true);
                limpiar_campos();
                bloqueo_componentes();
                re_cargar_lista();
                cargar_lista();
                btn_nuevo.setBackground(new java.awt.Color(30, 120, 200));//azul
            }
        }
        return;
        //}
    }//GEN-LAST:event_lb_nuevoMouseReleased

    
    /**
     * Maneja el ciclo de vida de la actualización de datos de un usuario existente.
     * Funciona como una máquina de estados basada en el texto de la etiqueta:
     * <ul>
     *   <li>Si el texto es "Editar Usuario": Requiere una selección previa en la lista, habilita los 
     *       campos del formulario y notifica al usuario las condiciones para conservar la clave actual.</li>
     *   <li>Si el texto es "Guardar": Ejecuta la validación de formato de correo. Admite dos flujos de persistencia:
     *     <ul>
     *       <li>Si las cajas de contraseña contienen datos, actualiza la cuenta modificando la clave.</li>
     *       <li>Si las cajas de contraseña están vacías, invoca un método especializado para actualizar 
     *           los datos del perfil conservando la contraseña intacta en la base de datos.</li>
     *     </ul>
     *   </li>
     * </ul>
     * 
     * @param evt Evento del ratón que dispara la acción al soltar el botón.
     */
    private void lb_editarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_editarMouseReleased
        if (lb_editar.getText().equals("Editar Usuario") && !lis_usuario.isSelectionEmpty() && lb_nuevo.isEnabled()) {
            lb_editar.setText("Guardar");
            lb_nuevo.setEnabled(false);
            lb_eliminar.setEnabled(false);
            //btn_nuevo.setEnabled(false);
            //btn_eliminar.setEnabled(false);
            lb_nota.setText("Si quiere conservar su CONTRASEÑA deje vacio esos campos y modifique los demas campos");
            lis_usuario.setEnabled(false);
            habilitar_componentes();
            btn_editar.setBackground(new java.awt.Color(40, 180, 120));//verde
            System.out.println("se habilito los campos para modificar datos");  //------------------------------------>>
        }else if(lb_editar.getText().equals("Guardar") && !lis_usuario.isSelectionEmpty() && lb_editar.isEnabled()){
            String pin1 = new String(txt_pass1.getPassword()).trim();
            String pin2 = new String(txt_pass2.getPassword()).trim();
            
            if (!txt_nombre.getText().trim().isEmpty() && !txt_correo.getText().trim().isEmpty() && !pin1.isEmpty() && !pin2.isEmpty()){
                //todos los campos estan llenos
                System.out.println("todos los campos estan llenos");
                String regexCorreo = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$";
                Pattern pattern = Pattern.compile(regexCorreo);
                String textoValidar = txt_correo.getText().trim();
                Matcher matcher = pattern.matcher(textoValidar);
                
                if(matcher.matches() && pin1.equals(pin2) && jList_rol.getSelectedIndex() != -1 && jList_estado.getSelectedIndex() != -1){
                    //se procede a guardar a la base de datos
                    System.out.println("se pre-guarda en base de datos");
                                int rol = 0;
                                if(jList_rol.getSelectedValue().toString().equals("Administrador")){
                                    rol = 1;
                                }else if(jList_rol.getSelectedValue().toString().equals("Empleado")){
                                    rol = 2;
                                }
                                Boolean state = true;
                                if(jList_estado.getSelectedValue().toString().equals("Activo")){
                                    state = true;
                                }else if(jList_estado.getSelectedValue().toString().equals("Inactivo")){
                                state = false;
                                }
            
                                UsuarioController u = new UsuarioController();
                                u.actualizar(id, txt_nombre.getText(), txt_correo.getText(), pin1, rol, state);
            
                                lb_editar.setText("Editar Usuario");
                                lb_nuevo.setEnabled(true);
                                lb_eliminar.setEnabled(true);
                                btn_nuevo.setEnabled(true);
                                btn_eliminar.setEnabled(true);
                                lb_nota.setText("");
                                lis_usuario.setEnabled(true);
                                limpiar_campos();
                                bloqueo_componentes();
                                re_cargar_lista();
                                cargar_lista();
                    System.out.println("guardardo exito");
                    btn_editar.setBackground(new java.awt.Color(30, 120, 200));//azul
                    JOptionPane.showMessageDialog(null,"Usuario modificado con exito","Información",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }else{
                    btn_editar.setBackground(new java.awt.Color(40, 180, 120));//verde
                    if(!matcher.matches()){
                        System.out.println("correo erroneo");
                        lb_nota.setText("Debe escribir un correo valido");
                    }else if(!pin1.equals(pin2)){
                        System.out.println("contraseñas no coinciden");
                        lb_nota.setText("las contraseñas no coinciden");
                    }else if(jList_rol.getSelectedIndex() == -1 || jList_estado.getSelectedIndex() == -1){
                        System.out.println("rol o estado no seleccionado");
                        lb_nota.setText("debe selecionar un Rol y Estado del Usuario");
                    }
                }
            }else if(!txt_nombre.getText().trim().isEmpty() || !txt_correo.getText().trim().isEmpty() || !pin1.isEmpty() || !pin2.isEmpty()){
                    //algunos de los campos estan vacios pero guardable depende
                    System.out.println("algunos de los campos estan vacios pero guardable depende");
                    btn_editar.setBackground(new java.awt.Color(40, 180, 120));//verde
                    
                if(!txt_nombre.getText().trim().isEmpty() && !txt_correo.getText().trim().isEmpty() && pin1.isEmpty() && pin2.isEmpty()){
                    //campos llenos y contraseñas vacios - se guarda sin modificar pass
                    System.out.println("se pre actualiza conservando los pass");
                    
                    String regexCorreo = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$";
                    Pattern pattern = Pattern.compile(regexCorreo);
                    String textoValidar = txt_correo.getText().trim();
                    Matcher matcher = pattern.matcher(textoValidar);
                
                    if(matcher.matches()){
                        //se procede a guardar a la base de datos
                                int rol = 0;
                                if(jList_rol.getSelectedValue().toString().equals("Administrador")){
                                    rol = 1;
                                }else if(jList_rol.getSelectedValue().toString().equals("Empleado")){
                                    rol = 2;
                                }
                                Boolean state = true;
                                if(jList_estado.getSelectedValue().toString().equals("Activo")){
                                    state = true;
                                }else if(jList_estado.getSelectedValue().toString().equals("Inactivo")){
                                state = false;
                                }
            
                                UsuarioController u = new UsuarioController();
                                u.actualizar_sin(id, txt_nombre.getText(), txt_correo.getText(), rol, state);
            
                                lb_editar.setText("Editar Usuario");
                                lb_nuevo.setEnabled(true);
                                lb_eliminar.setEnabled(true);
                                btn_nuevo.setEnabled(true);
                                btn_eliminar.setEnabled(true);
                                lb_nota.setText("");
                                lis_usuario.setEnabled(true);
                                limpiar_campos();
                                bloqueo_componentes();
                                re_cargar_lista();
                                cargar_lista();
                        System.out.println("guardardo update exito");
                        //b=true;
                        //btn_editar.setBackground(new java.awt.Color(20, 160, 240));
                        btn_editar.setBackground(new java.awt.Color(30, 120, 200));//azul
                        return;
                    }else{
                        System.out.println("correo erroneo");
                        lb_nota.setText("Debe escribir un correo valido");
                        btn_nuevo.setBackground(new java.awt.Color(40, 180, 120));//verde
                    }
                    return;
                }
                lb_nota.setText("Debe llenar los campos solicitados excepto las contraseñas si lo desea");
                btn_editar.setBackground(new java.awt.Color(40, 180, 120));//verde// ??????????????
            }else{
                //todo los campos estan vacios
                System.out.println("todos vacios");
                lb_nota.setText("No puede dejar los campos vacios excepto las contraseñas");
                btn_nuevo.setBackground(new java.awt.Color(40, 180, 120));//verde
            }
        }else{
            btn_editar.setBackground(new java.awt.Color(30, 120, 200));//azul
        }
    }//GEN-LAST:event_lb_editarMouseReleased

    private void lb_editarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_editarMousePressed
        if (lb_editar.isEnabled() && lb_editar.getText().equals("Editar Usuario")){
            btn_editar.setBackground(new java.awt.Color(30, 150, 220));//celeste claro
        }else if(lb_editar.isEnabled() && lb_editar.getText().equals("Guardar")){
            btn_editar.setBackground(new java.awt.Color(40, 220, 140));//verde claro
        }
    }//GEN-LAST:event_lb_editarMousePressed

    private void lb_eliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_eliminarMousePressed
        if (lb_eliminar.isEnabled() && lb_eliminar.getText().equals("Eliminar Usuario")){
            btn_eliminar.setBackground(new java.awt.Color(30, 150, 220));//celeste claro
        }else if(lb_eliminar.isEnabled() && lb_eliminar.getText().equals("Confirmar")){
            btn_eliminar.setBackground(new java.awt.Color(255, 90, 90));//rojo claro
        }
    }//GEN-LAST:event_lb_eliminarMousePressed

    private void lb_eliminarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_eliminarMouseReleased
        if (!lis_usuario.isSelectionEmpty() && lb_eliminar.isEnabled()){
                panelEliminar();
        }else{
            btn_eliminar.setBackground(new java.awt.Color(30, 120, 200));//azul
        }
    }//GEN-LAST:event_lb_eliminarMouseReleased

    
    /**
     * Escucha los cambios de selección dentro de la lista visual de usuarios.
     * Cuando un registro es seleccionado, extrae su identificador, consulta sus datos 
     * detallados mediante el controlador y puebla de manera automática todos los campos 
     * del formulario (nombre, correo, rol y estado) mapeando los valores numéricos correspondientes.
     * 
     * @param evt Evento de cambio de estado en la selección de la lista.
     */
    private void lis_usuarioValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lis_usuarioValueChanged
        if (!evt.getValueIsAdjusting()) {
            if (lis_usuario.getSelectedValue() != null) {
                String seleccionado = lis_usuario.getSelectedValue().toString();
                UsuarioController u = new UsuarioController();
                ArrayList<String> user = new ArrayList<>();
                user = u.IdxNombre(seleccionado);
                txt_nombre.setText(user.get(1));
                txt_correo.setText(user.get(2));
                String r = "";
                String e = "";
                if(user.get(4).equals("1")){
                    r = "Administrador";
                }else if (user.get(4).equals("2")){
                    r = "Empleado";
                }
                if(user.get(5).equals("1")){
                    e = "Activo";
                }else if (user.get(5).equals("0")){
                    e = "Inactivo";
                }
                for (int i = 0; i < jList_rol.getModel().getSize(); i++) {
                    String elementoLista = jList_rol.getModel().getElementAt(i).toString();
                    if (elementoLista.equalsIgnoreCase(r)) {
                        jList_rol.setSelectedIndex(i);
                        /*jList1.ensureIndexIsVisible(i);       Hace scroll automático si la lista es muy larga*/
                        break;
                    }
                }
                for (int i = 0; i < jList_estado.getModel().getSize(); i++) {
                    String elementoLista = jList_estado.getModel().getElementAt(i).toString();
                    if (elementoLista.equalsIgnoreCase(e)) {
                        jList_estado.setSelectedIndex(i);
                        /*jList1.ensureIndexIsVisible(i);       Hace scroll automático si la lista es muy larga*/
                        break;
                    }
                }
                id = Integer.parseInt(user.get(0));
                System.out.println(id);
            }
        }
    }//GEN-LAST:event_lis_usuarioValueChanged

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
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuario uss = new Usuario();
                new UsuarioView(uss).setVisible(true);
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
