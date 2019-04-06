package co.edu.materia.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;


import co.edu.materia.conexion.TransaccionMYSQL;
import co.edu.materia.conexion.TransaccionMYSQL2;
import co.edu.materia.repositorio.Itransaccion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PantallaMateria {

	private JFrame frame;
	private JTextField txtcodigo;
	private JTextField txtnombre;
	private JTextField txtcredito;

	/**
	 * Launch the application.
	 *
	 */
	
	PreparedStatement ps = null;
	ResultSet rs= null;
	int retorno = 0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaMateria window = new PantallaMateria();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaMateria() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 517, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCodigoDeMateria = new JLabel("Codigo de Materia");
		lblCodigoDeMateria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoDeMateria.setBounds(26, 29, 118, 23);
		frame.getContentPane().add(lblCodigoDeMateria);
		
		JLabel lblNombreDeMateria = new JLabel("Nombre de Materia");
		lblNombreDeMateria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDeMateria.setBounds(26, 84, 118, 23);
		frame.getContentPane().add(lblNombreDeMateria);
		
		JLabel lblNumeroDeCreditos = new JLabel("Numero de Creditos");
		lblNumeroDeCreditos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroDeCreditos.setBounds(26, 150, 134, 23);
		frame.getContentPane().add(lblNumeroDeCreditos);
		
		txtcodigo = new JTextField();
		txtcodigo.setBounds(212, 32, 142, 20);
		frame.getContentPane().add(txtcodigo);
		txtcodigo.setColumns(10);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(212, 87, 142, 20);
		frame.getContentPane().add(txtnombre);
		txtnombre.setColumns(10);
		
		txtcredito = new JTextField();
		txtcredito.setBounds(212, 153, 142, 20);
		frame.getContentPane().add(txtcredito);
		txtcredito.setColumns(10);
		
		JButton buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Itransaccion transaccionMySQL = new TransaccionMYSQL();
					Connection con = transaccionMySQL.conectar();
					String sql = "SELECT nmateria,creditos FROM materia where idMateria = ?";
					ps = con.prepareStatement(sql);
					ps.setString(1, txtcodigo.getText());
					 ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						txtnombre.setText(rs.getString ("nmateria"));
						txtcredito.setText(rs.getString ("creditos"));
						
					}else {
						rs.close();
						ps.close();
						con.close();
						Itransaccion transaccionMySQL2 = new TransaccionMYSQL2();
						 con = transaccionMySQL2.conectar();
						
						
						sql = "SELECT nmateria,creditos FROM materia2 where idmateria = ?";
						ps = con.prepareStatement(sql);
						ps.setString(1, txtcodigo.getText());
						  rs = ps.executeQuery();
						if(rs.next()) {
						
							txtnombre.setText(rs.getString ("nmateria"));
							txtcredito.setText(rs.getString ("creditos"));
								  
							
						}else {
							
							JOptionPane.showMessageDialog(null, " materia no Encontrado");
						}
						rs.close();
						ps.close();
						con.close();
					
					}
					con.close();
			}catch (SQLException e1){
				JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
				e1.printStackTrace();
			}
			}
		});
		buscar.setBounds(387, 31, 89, 23);
		frame.getContentPane().add(buscar);
		

		JButton agregar = new JButton("Agregar");
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Itransaccion transaccionMySQL = new TransaccionMYSQL();
					Connection con = transaccionMySQL.conectar();
					String sql = "insert into materia (idMateria ,nmateria,creditos)values(?,?,?)";
					
					ps = con.prepareStatement(sql);
					ps.setString(1, txtcodigo.getText());
					ps.setString(2, txtnombre.getText());
					ps.setString(3, txtcredito.getText());
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						Itransaccion transaccionMySQL2 = new TransaccionMYSQL2();
						 con = transaccionMySQL2.conectar();
						 String sql2 = "insert into materia2 (idmateria ,nmateria,creditos)values(?,?,?)";
						 ps = con.prepareStatement(sql2);
							ps.setString(1, txtcodigo.getText());
							ps.setString(2, txtnombre.getText());
							ps.setString(3, txtcredito.getText());
							retorno = ps.executeUpdate();
						
						if(retorno >0 ) {
							
							
							JOptionPane.showMessageDialog(null,"Registro Agregado Correctamente  #2" );
							limpiarcuadrotxt();
						
							ps.close();
							con.close();
						
						} else {
							JOptionPane.showMessageDialog(null,"No se Pudo Agregar el Registro #2" );
							
							
							 
						}
						
						JOptionPane.showMessageDialog(null,"Registro Agregado Correctamente" );
						limpiarcuadrotxt();
					
						ps.close();
						con.close();
					
					} else {
						JOptionPane.showMessageDialog(null,"No se Pudo Agregar el Registro" );
						
						
						 
					}
				}
				catch (SQLException e1){
					JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
					e1.printStackTrace();
				}
			}

			private void limpiarcuadrotxt() {
				// TODO Auto-generated method stub
				txtcodigo.setText(null);
				txtnombre.setText(null);
				txtcredito.setText(null);
				
			}
		});
		agregar.setBounds(49, 214, 89, 23);
		frame.getContentPane().add(agregar);
		

		JButton modificar = new JButton("Modificar");
		modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Itransaccion transaccionMySQL = new TransaccionMYSQL();
					Connection con = transaccionMySQL.conectar();
					String sql = ("update materia set nmateria = ? , creditos = ?  where idMateria = ?");
					ps = con.prepareStatement(sql);
					ps.setString(3, txtcodigo.getText());
					ps.setString(1, txtnombre.getText());
					ps.setString(2, txtcredito.getText());
					
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						Itransaccion transaccionMySQL2 = new TransaccionMYSQL2();
						 con = transaccionMySQL2.conectar();
						String sql2 = ("update materia2 set nmateria = ? , creditos = ?  where idmateria = ?");
						ps = con.prepareStatement(sql2);
						ps.setString(3, txtcodigo.getText());
						ps.setString(1, txtnombre.getText());
						ps.setString(2, txtcredito.getText());
						retorno = ps.executeUpdate();
						if(retorno >0 ) {
							JOptionPane.showMessageDialog(null,"Registro Actualizado Correctamente" );
							limpiarcuadrotxt();
						ps.close();
						con.close();
						} else {
							

							
					}
						JOptionPane.showMessageDialog(null,"Registro Actualizado Correctamente" );
						limpiarcuadrotxt();
					ps.close();
					con.close();
					} else {
						Itransaccion transaccionMySQL2 = new TransaccionMYSQL2();
						 con = transaccionMySQL2.conectar();
						String sql2 = ("update materia2 set nmateria = ? , creditos = ?  where idmateria = ?");
						ps = con.prepareStatement(sql2);
						ps.setString(3, txtcodigo.getText());
						ps.setString(1, txtnombre.getText());
						ps.setString(2, txtcredito.getText());
						retorno = ps.executeUpdate();
						if(retorno >0 ) {
							JOptionPane.showMessageDialog(null,"Registro Actualizado Correctamente" );
							limpiarcuadrotxt();
						ps.close();
						con.close();
						} else {
							

							
					}
					}
				}
				catch (SQLException e1){
					JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
					e1.printStackTrace();
				}
				
			}

			private void limpiarcuadrotxt() {
				// TODO Auto-generated method stub
				txtcodigo.setText(null);
				txtnombre.setText(null);
				txtcredito.setText(null);
				
			}
		});
		modificar.setBounds(181, 214, 89, 23);
		frame.getContentPane().add(modificar);
		
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Itransaccion transaccionMySQL = new TransaccionMYSQL();
					Connection con = transaccionMySQL.conectar();
					String sql = ("delete from materia where idMateria = ?");
					ps = con.prepareStatement(sql);
					ps.setString(1, txtcodigo.getText());
					
					retorno = ps.executeUpdate();
					if(retorno >0 ) {
						Itransaccion transaccionMySQL2 = new TransaccionMYSQL2();
						 con = transaccionMySQL2.conectar();
						String sql2 = ("delete from materia2 where idmateria = ?");
						ps = con.prepareStatement(sql2);
						ps.setString(1, txtcodigo.getText());
						retorno = ps.executeUpdate();
						if(retorno >0 ) {
							JOptionPane.showMessageDialog(null,"Registro Borrado Correctamente #2" );
							limpiarcuadrotxt();
						ps.close();
						con.close();
						} else {
							JOptionPane.showMessageDialog(null,"No se Pudo Borrar el Registro" );
					}
						JOptionPane.showMessageDialog(null,"Registro Borrado Correctamente" );
						limpiarcuadrotxt();
					ps.close();
					con.close();
					} else {
						Itransaccion transaccionMySQL2 = new TransaccionMYSQL2();
						 con = transaccionMySQL2.conectar();
						String sql2 = ("delete from materia2 where idmateria = ?");
						ps = con.prepareStatement(sql2);
						ps.setString(1, txtcodigo.getText());
						
						retorno = ps.executeUpdate();
						if(retorno >0 ) {
							JOptionPane.showMessageDialog(null,"Registro Borrado Correctamente" );
							limpiarcuadrotxt();
						ps.close();
						con.close();
						} else {
							JOptionPane.showMessageDialog(null,"No se Pudo Borrar el Registro" );
					}
					}	
				}
				catch (SQLException e1){
					JOptionPane.showMessageDialog(null,"Error al Acceder a BD");
					e1.printStackTrace();
				}
			}

			private void limpiarcuadrotxt() {
				// TODO Auto-generated method stub
				txtcodigo.setText(null);
				txtnombre.setText(null);
				txtcredito.setText(null);
			}
		});
		eliminar.setBounds(324, 214, 89, 23);
		frame.getContentPane().add(eliminar);
	}
}
