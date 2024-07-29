package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controller.UserController;
import model.User;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Users extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel main;
	private JTextField inputCedula;
	private JTextField inputNombres;
	private JTextField inputCorreo;
	private JTextField inputDireccion;
	private JTextField inputTelefono;
	private JTable table;
	DefaultTableModel datatable = new DefaultTableModel();
	ArrayList<User> listUsers;
	int row = -1;
	User user = new User();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users frame = new Users();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Funcion para actualizar los datos de la tabla.
	 */
	public void syncTable() {
		
		// Instancia del controlador del usuario.
	    UserController userController = new UserController();
	    
	    // Limpiar el modelo de la tabla.
	    DefaultTableModel datatable = (DefaultTableModel) table.getModel();
	    datatable.setRowCount(0); // Elimina todas las filas existentes
	    
	    // Obtener la lista de usuarios
	    listUsers = userController.datatable();
	    for (User user : listUsers) {
	        Object[] row = new Object[4];
	        row[0] = user.getDni();
	        row[1] = user.getName();
	        row[2] = user.getRol();
	        row[3] = user.getEmail();
	        
	        // Añadir la fila al modelo de la tabla
	        datatable.addRow(row);
	    }
	}
	
	/**
	 * Funcion para limpiar el formulario
	 */
	public void clearForm() {
		inputCedula.setText("");
        inputNombres.setText("");
        inputCorreo.setText("");
        inputDireccion.setText("");
        inputTelefono.setText("");
	}
	
	/**
	 * Create the frame.
	 */
	public Users() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 453);
		main = new JPanel();
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setTitle("Uniminuto - Especialización Desarrollo De Software - Raul Maurcio Uñate Castro");
		setContentPane(main);
		main.setLayout(null);
		
		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setBounds(20, 72, 100, 20);
		main.add(lblCedula);
		
		inputCedula = new JTextField();
		inputCedula.setBounds(86, 73, 100, 20);
		main.add(inputCedula);
		inputCedula.setColumns(10);
		
		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(207, 72, 100, 20);
		main.add(lblNombres);
		
		inputNombres = new JTextField();
		inputNombres.setColumns(10);
		inputNombres.setBounds(266, 73, 312, 20);
		main.add(inputNombres);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(21, 100, 100, 20);
		main.add(lblCorreo);
		
		inputCorreo = new JTextField();
		inputCorreo.setColumns(10);
		inputCorreo.setBounds(86, 102, 294, 20);
		main.add(inputCorreo);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(588, 72, 46, 20);
		main.add(lblCargo);
		
		JComboBox<String> selectCargo = new JComboBox<>();
        selectCargo.setModel(new DefaultComboBoxModel<>(new String[] {"Cliente", "Cajero", "Contador", "Propietario"}));
        selectCargo.setBounds(627, 72, 146, 20);
        main.add(selectCargo);
		
		JLabel lbl = new JLabel("Dirección");
		lbl.setBounds(390, 100, 100, 20);
		main.add(lbl);
		
		inputDireccion = new JTextField();
		inputDireccion.setColumns(10);
		inputDireccion.setBounds(448, 101, 325, 20);
		main.add(inputDireccion);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(21, 130, 100, 20);
		main.add(lblTelefono);
		
		inputTelefono = new JTextField();
		inputTelefono.setColumns(10);
		inputTelefono.setBounds(86, 130, 120, 20);
		main.add(inputTelefono);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Variables con los datos del Formulario
                String cedula = inputCedula.getText().trim();
                String nombres = inputNombres.getText().trim();
                String correo = inputCorreo.getText().trim();
                String cargo = (String) selectCargo.getSelectedItem();
                String direccion = inputDireccion.getText().trim();
                String telefono = inputTelefono.getText().trim();

                // Validación de que ningun dato este vacio.
                if (cedula.isEmpty() || nombres.isEmpty() || correo.isEmpty() || cargo == null || direccion.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                	// Instancia del controlador del usuario.
                    UserController userController = new UserController();
                    //Valor por defecto de creacion de la data.
					try {
						String execute_result = userController.insertUser(cedula, nombres, correo, cargo, direccion, telefono);
						if (execute_result == "Se creó el usuario en la base de datos.") {
							// Cargar los datos de la tabla.
							syncTable();
							//Limpiar el Formulario
							clearForm();
							// Lanzar mensaje de Exito
							JOptionPane.showMessageDialog(null, "Se creó correctamente el usuario.", "Usuario Guardado", JOptionPane.INFORMATION_MESSAGE);					
						} else {
							// Lanzar mensaje con error especifico.
							JOptionPane.showMessageDialog(null, "No se logró crear el registro en la base de datos. " + execute_result, "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						
						// Error generico.
						JOptionPane.showMessageDialog(null, "No se logró crear el registro en la base de datos. " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
                }

			}
		});
		
		btnRegistrar.setBounds(658, 131, 115, 21);
		main.add(btnRegistrar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Obetner los datos del usuario que se busca actualizar.
                String cedula = inputCedula.getText().trim();
                String nombres = inputNombres.getText().trim();
                String correo = inputCorreo.getText().trim();
                String cargo = (String) selectCargo.getSelectedItem();
                String direccion = inputDireccion.getText().trim();
                String telefono = inputTelefono.getText().trim();
                
                // Validar que la cedula que se desea actualizar ya este en la base de datos.
                String i_dni = "";
                boolean dni_validate = false;
                for (User user : listUsers) {
                	i_dni = user.getDni().trim();
					if (i_dni.equals(cedula)) {
						dni_validate = true;
						break;
					}
				}
                
                // Determinar si se puede editar.
                if (dni_validate == false) {
                	JOptionPane.showMessageDialog(null, "El número de cedula a editar no existe en la base de datos. Por favor use el boton de registrar.", "Error", JOptionPane.ERROR_MESSAGE);
                	return;
                }
                
                // Lanzar actualizacion.
                // Validación de que ningun dato este vacio.
                if (cedula.isEmpty() || nombres.isEmpty() || correo.isEmpty() || cargo == null || direccion.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                	
                	// Instancia del controlador del usuario.
                    UserController userController = new UserController();
                    
                    //Valor por defecto de creacion de la data.
					try {
						String execute_result = userController.updateUser(cedula, nombres, correo, cargo, direccion, telefono);
						if (execute_result == "Se actualizó el usuario en la base de datos.") {
							
							// Cargar los datos de la tabla.
							syncTable();
							
							//Limpiar el Formulario
							clearForm();

							// Lanzar mensaje de Exito
							JOptionPane.showMessageDialog(null, execute_result, "Usuario Actualizado", JOptionPane.INFORMATION_MESSAGE);
														
						} else {
							
							// Lanzar mensaje con error especifico.
							JOptionPane.showMessageDialog(null, "No se logró actualizar el registro en la base de datos. " + execute_result, "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						
						// Error generico.
						JOptionPane.showMessageDialog(null, "No se logró actualizar el registro en la base de datos. " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
                    
                }
			}
		});
		btnActualizar.setBounds(533, 131, 115, 21);
		main.add(btnActualizar);
		
		JSeparator Separador = new JSeparator();
		Separador.setBounds(10, 60, 783, 2);
		main.add(Separador);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 184, 783, 188);
		main.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Obetener el Id de la tabla seleccionada.
				row = table.getSelectedRow(); 
				
				// De los datos actuales de la lista obetner los datos de la seleccion
				user = listUsers.get(row);
				
				// Llenar los datos en los labels.
				inputCedula.setText(user.getDni());
		        inputNombres.setText(user.getName());
		        inputCorreo.setText(user.getEmail());
		        inputDireccion.setText(user.getAddress());
		        inputTelefono.setText(user.getPhone());
		        selectCargo.setSelectedItem(user.getRol());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {},
		    new String[] {"Cedula", "Nombre", "Cargo", "Correo"}
		));
		scrollPane.setViewportView(table);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirm = JOptionPane.showConfirmDialog(btnEliminar, "¿Esta seguro(a) de eliminar el registro?", "Accion No Reversable", JOptionPane.YES_NO_OPTION);
				if (confirm == 0) {
					
					// Obetner los datos del usuario que se busca actualizar.
					String cedula = inputCedula.getText().trim();
					
					// Validar que la cedula que se desea eliminar ya este en la base de datos.
					String i_dni = "";
					boolean dni_validate = false;
					for (User user : listUsers) {
						i_dni = user.getDni().trim();
						if (i_dni.equals(cedula)) {
							dni_validate = true;
							break;
						}
					}
					
					// Determinar si se puede editar.
					if (cedula.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Se requiere minimo el número de cedula a eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					// Validación de que por lo menos este la cedula.
					if (dni_validate == false) {
						JOptionPane.showMessageDialog(null, "El número de cedula a eliminar no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						
						// Instancia del controlador del usuario.
						UserController userController = new UserController();
						
						//Valor por defecto de creacion de la data.
						try {
							String execute_result = userController.deleteUser(cedula);
							if (execute_result == "Se eliminó el usuario en la base de datos.") {
								
								// Cargar los datos de la tabla.
								syncTable();
								
								//Limpiar el Formulario
								clearForm();
								
								// Lanzar mensaje de Exito
								JOptionPane.showMessageDialog(null, execute_result, "Usuario Eliminado", JOptionPane.INFORMATION_MESSAGE);
								
							} else {
								
								// Lanzar mensaje con error especifico.
								JOptionPane.showMessageDialog(null, "No se logró eliminar el registro en la base de datos. " + execute_result, "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e1) {
							
							// Error generico.
							JOptionPane.showMessageDialog(null, "No se logró eliminar el registro en la base de datos. " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				}
			}
		});
		btnEliminar.setBounds(408, 131, 115, 21);
		main.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cierra la Ventana de la Vista.
				dispose();
			}
		});
		btnSalir.setBounds(678, 382, 115, 21);
		main.add(btnSalir);
		
		JLabel lblControlDeUsuarios = new JLabel("CONTROL DE USUARIOS");
		lblControlDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblControlDeUsuarios.setFont(new Font("Cambria", Font.BOLD, 20));
		lblControlDeUsuarios.setBounds(10, 16, 784, 27);
		main.add(lblControlDeUsuarios);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obetner los datos del usuario que se busca actualizar.
                String cargo = (String) selectCargo.getSelectedItem();
                
                // Validación de que exista un rol seleccionado.
                if (cargo == null) {
                    JOptionPane.showMessageDialog(null, "El campo de rol es requerido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                	
                	// Instancia del controlador del usuario.
                    UserController userController = new UserController();
                    
                    // Limpiar el modelo de la tabla.
					DefaultTableModel datatable = (DefaultTableModel) table.getModel();
					datatable.setRowCount(0); // Elimina todas las filas existentes
					
					// Obtener la lista de usuarios
					listUsers = userController.search(cargo);
					for (User user : listUsers) {
					    Object[] row = new Object[4];
					    row[0] = user.getDni();
					    row[1] = user.getName();
					    row[2] = user.getRol();
					    row[3] = user.getEmail();
					    
					    // Añadir la fila al modelo de la tabla
					    datatable.addRow(row);
					}
                    
                }
			}
		});
		btnBuscar.setBounds(284, 132, 115, 21);
		main.add(btnBuscar);
		
		JButton btnRestaurar = new JButton("Restaurar");
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cargar el formulario limpio.
				clearForm();
				
				// Cargar los datos de la tabla.
				syncTable();
			}
		});
		btnRestaurar.setBounds(553, 382, 115, 21);
		main.add(btnRestaurar);
		
		// Cargar el formulario limpio.
		clearForm();
		
		// Cargar los datos de la tabla.
		syncTable();
	}
}
