package POO5;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JOptionPane;

public class uso_empleado_con_comparacion_de_llegada {

	public static void main(String[] args) {
			boolean salir = false;
			int cantidad = 10;
			Empleado[] Empleados = new Empleado[cantidad];
			LocalDateTime fecha = LocalDateTime.now();
			LocalTime horaActual = LocalTime.now();
			int contador = 0;
			Empleados[contador] = new Empleado();
			Empleados[contador].dime_nombre("Ibai");
			Empleados[contador].dime_hora(Empleados, 12, 30, 25, 1, 2021);
			contador++;
			while (!salir) {
				String[] opciones = { "Añadir Empleado (max. " + cantidad + " )", "Lista de empleado", "Salir" };
				int seleccion = JOptionPane.showInternalOptionDialog(null, "Selecciona una opción:", "Menú",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

				switch (seleccion) {
				case 0:
					if (contador < cantidad) {
						Empleados[contador] = new Empleado();

						Empleados[contador].dime_nombre(JOptionPane.showInputDialog("Nombre:"));
						JOptionPane.showMessageDialog(null, "Hora del registro del empleado guardada");

						int año = fecha.getYear();
						int dia = fecha.getDayOfMonth();
						int mes = fecha.getMonthValue();
						int horañadida = horaActual.getHour();
						int minutoañadido = horaActual.getMinute();
						Empleados[contador].dime_hora(Empleados, horañadida, minutoañadido, dia, mes, año);
						contador++;
					} else {
						JOptionPane.showMessageDialog(null, "No hay más espacio para nuevos empleados.", null,
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 1:
					Empleado.mostrarLista(Empleados);
					String[] opcionesEliminar = { "Eliminar Empleado", "Aumentar sueldo", "Comparar empleados",
							"Cancelar" };
					int opcionEliminar = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Opciones",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesEliminar,
							opcionesEliminar[0]);
					if (opcionEliminar == 0) {
						String indiceEliminarInput = JOptionPane
								.showInputDialog("Ingrese el índice del empleado a eliminar (1-" + (contador) + "):");
						if (indiceEliminarInput == null) {
							JOptionPane.showMessageDialog(null, "Eliminación de contacto cancelada.");
							break;
						}

						try {
							int indiceEliminar = Integer.parseInt(indiceEliminarInput);
							if (indiceEliminar > 0 && indiceEliminar <= (10 - contador)) {
								contador = Empleado.eliminarEmpleado(Empleados, indiceEliminar, contador);
								JOptionPane.showMessageDialog(null, "Empleado eliminado.");
							} else {
								JOptionPane.showMessageDialog(null, "Índice inválido.");
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Error: Ingresa un número válido. Inténtalo de nuevo.");
						}
					} else if (opcionEliminar == 1) {
						String indiceaumentoInput = JOptionPane
								.showInputDialog("Ingrese el índice del empleado a eliminar (1-" + (contador) + "):");
						if (indiceaumentoInput == null) {
							JOptionPane.showMessageDialog(null, "Eliminación de Empleado cancelada.");
							break;
						}
						try {
							int indiceaumento = Integer.parseInt(indiceaumentoInput);
							if (indiceaumento > 0 && indiceaumento <= (10 - contador)) {
								contador = Empleado.aumentarsueldoEmpleado(Empleados, indiceaumento, contador);
							} else {
								JOptionPane.showMessageDialog(null, "Índice inválido.");
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Error: Ingresa un número válido. Inténtalo de nuevo.");
						}
					} else if (opcionEliminar == 2) {
						String indiceComparar1Input = JOptionPane
								.showInputDialog("Ingrese el índice del primer Empleado (1-" + contador + "):");
						String indiceComparar2Input = JOptionPane
								.showInputDialog("Ingrese el índice del segundo Empleado (1-" + contador + "):");

						try {
							int indiceComparar1 = Integer.parseInt(indiceComparar1Input);
							int indiceComparar2 = Integer.parseInt(indiceComparar2Input);

							if (indiceComparar1 > 0 && indiceComparar1 <= contador && indiceComparar2 > 0
									&& indiceComparar2 <= contador) {
								Empleado empleado1 = Empleados[indiceComparar1 - 1];
								Empleado empleado2 = Empleados[indiceComparar2 - 1];

								if (empleado1.dame_año() == empleado2.dame_año()
										&& empleado1.dame_mes() == empleado2.dame_mes()
										&& empleado1.dame_dia() == empleado2.dame_dia()) {
									JOptionPane.showMessageDialog(null, "Los empleados llegaron el mismo día.");
								} else {
									JOptionPane.showMessageDialog(null, "Los empleados NO llegaron el mismo día.");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Índice inválido.");
							}
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Error: Ingresa un número válido. Inténtalo de nuevo.");
						}
					}

					break;
				case 2:
					salir = true;
					break;
				default:
					break;
				}
			}
		}
	}

	class Empleado {
		LocalTime horaActual = LocalTime.now();
		private int horaActualInt[];
		private int minutoActualInt[];
		private int año[];
		private int mes[];
		private int dia[];
		private String[] nombre;
		private double[] sueldo;
		private int contador;

		public Empleado() {
			horaActualInt = new int[10];
			minutoActualInt = new int[10];
			año = new int[10];
			dia = new int[10];
			mes = new int[10];
			nombre = new String[10];
			sueldo = new double[10];
			contador = 0;
			this.sueldo[contador] = 3000;
		}

		public void dime_nombre(String nombre) {
			this.nombre[contador] = nombre;

		}

		public double dame_sueldo() {
			return this.sueldo[contador - 1];
		}

		public void dime_hora(Empleado[] empleados, int hora, int minutos, int dia, int mes, int año) {
			this.horaActualInt[contador] = hora;
			this.minutoActualInt[contador] = minutos;
			this.mes[contador] = mes;
			this.dia[contador] = dia;
			this.año[contador] = año;
			contador++;

			if (this.año[contador - 1] <= 2021) {
				double sueldo = this.dame_sueldo();
				double porcentaje = 0.05;
				int nsueldo = (int) (sueldo * (1 + porcentaje));
				double nuevoSueldo = nsueldo;
				int numero = contador - 1;
				this.dime_sueldo(nuevoSueldo);
			}

		}

		public String dame_nombre() {
			return nombre[contador - 1];
		}

		public int dame_hora() {
			return horaActualInt[contador - 1];
		}

		public int dame_min() {
			return minutoActualInt[contador - 1];
		}

		public int dame_dia() {
			return dia[contador - 1];
		}

		public int dame_mes() {
			return mes[contador - 1];
		}

		public int dame_año() {
			return año[contador - 1];
		}

		public static int eliminarEmpleado(Empleado[] empleados, int indice, int contador) {
			for (int i = indice - 1; i < contador - 1; i++) {
				empleados[i] = empleados[i + 1];
			}

			empleados[contador - 1] = null;
			contador--;

			return contador;
		}

		public static int aumentarsueldoEmpleado(Empleado[] empleados, int indice, int contador) {
			double porcentaje = 0.1;

			if (indice > 0 && indice <= contador) {
		        double sueldo = empleados[indice - 1].dame_sueldo();
		        int nsueldo = (int) (sueldo * (1 + porcentaje));
		        double nuevoSueldo = nsueldo;
		        empleados[indice - 1].dime_sueldo(nuevoSueldo);  
		        JOptionPane.showMessageDialog(null, "Sueldo del empleado " + indice + " aumentado");
		    } else {
		        
		    }

			return contador;
		}

		private void dime_sueldo(double nuevoSueldo) {
			this.sueldo[contador-1] = nuevoSueldo;

		}

		public static void mostrarLista(Empleado[] empleados) {

			StringBuilder Lista = new StringBuilder("Empleados :\n");
			for (int i = 0; i < empleados.length; i++) {
				if (empleados[i] != null) {
					Lista.append((i + 1) + "\n Nombre : ").append(empleados[i].dame_nombre()).append("\n Comienzo: ")
							.append(empleados[i].dame_dia() + "/" + empleados[i].dame_mes() + "/" + empleados[i].dame_año()
									+ " a las " + empleados[i].dame_hora() + ":" + empleados[i].dame_min())
							.append("\n Sueldo: ").append(empleados[i].dame_sueldo()).append("\n----------------------\n");
				}
			}
			JOptionPane.showMessageDialog(null, Lista.toString(), "Empleados", JOptionPane.INFORMATION_MESSAGE);
		}
	}