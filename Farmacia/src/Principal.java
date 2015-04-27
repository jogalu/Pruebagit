import java.sql.*;
import java.io.*;
//Hola
/*
 * Realizar una aplicación java que permita gestionar la bd llamada farmacia. Esta bd almacena todos los laboratorios
 * con los que trabaja la farmacia y los medicamentos que oferta cada uno de ellos. Por lo tanto disponemos de
 * dos tablas:
 *  -Laboratorios: codigo-lab, nombre-lab, telefono, direccion y población.
 *  -Medicamentos: codigo-med, nombre-med, unidades disponibles, precio, tipo de medicamento, receta(booleano),
 *  codigo-lab al que pertenece.
 * Hasta el momento en la farmacia han trabajado con un fichero de texto en el que tienen almacenada la info de
 * todos los medicamentos con los que comercializa. Este fichero se llama medicamentos.txt.
 * La aplicación lo primero que hará será realizar la conexión con la bd con la que va a trabajar y a continuación
 * mediante un menu permitirá las siguientes opciones:
 *  1. Rellenar la tabla de medicamentos de la bd con los datos que se tienen almacenados en el fichero medicamentos.txt
 *  Debes realizar todas las comprobaciones oportunas antes de realizar la inserción, mostrando todos los posibles
 *  mensajes de error.
 *  2. Generar una única estructura estática de datos que almacene las distintas poblaciones y el nº total de laboratorios
 *  existentes en cada población. Mostrar dicha estructura mostrando en pantalla algo similar a lo siguiente:
 *  Localidad:xxx Laboratorios:y
 *  3. Generar un fichero binario con los nombres y los precios(dos datos independientes, una cadena y dato numerico)
 *  de aquellos medicamentos para los que es necesaria receta. El campo receta de la tabla medicamentos contendrá
 *  un 1 si se requiere receta y un 0 en caso contrario. Visualizar dicho fichero.
 *  4. Generar un fichero de objetos que contenga por cada medicamento el nombre del medicamento, el nombre del
 *  laboratorio al que pertenece y el total de dinero que cobraría la farmacia si vendiese todas las unidades
 *  existentes del mismo. Visualizar dicho fichero.
 *  5. Nos ha llegado un cominicado de que el laboratorio con codigo 3 cerrará sus instalaciones, por lo que
 *  se desea eliminar dicho laboratorio de la bd junto con todos sus medicamentos.
 *  6. Generar un listado en pantalla en el que aparezcan por cada laboratorio todos sus medicamentos, mostrando
 *  el nombre del laboratorio(una única vez) y debajo el nombre de todos los medicamentos de dicho laboratorio.(0.5)
 *  7. Salir.
 */
public class Principal {
	public static BufferedReader leer=new BufferedReader (new InputStreamReader(System.in));

	public static int menu() throws NumberFormatException, IOException{
		int opc;
		do{
			System.out.println("1.- Rellenar la tabla medicamentos.");
			System.out.println("2.- Laboratorios por localidad.");
			System.out.println("3.- Precio de los medicamentos con receta.");
			System.out.println("4.- Precio total que se cobraría con la venta de todas las unidades de cada medicamento.");
			System.out.println("5.- Eliminar laboratorio.");
			System.out.println("6.- Medicamentos de cada laboratorio.");
			System.out.println("7.- Salir.");
			opc=Integer.parseInt(leer.readLine());
		}while(opc<1 || opc>7);
		return opc;
	}
	public static void opcion1(Statement stm) throws IOException, SQLException{
		File f=new File("Medicamentos.txt");
		if(f.exists()){
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String[] v=new String[7];
			v[0]=br.readLine();
			while(v[0]!=null){
				for (int i = 1; i < v.length; i++) {
					v[i]=br.readLine();
				}
				ResultSet rs = stm.executeQuery("select count(*), Unidades from medicamentos where CodMed="+v[0]);
				while(rs.next()){
					if(rs.getInt(1)==1){
						System.out.println("El medicamento con código "+v[0]+" ya está en la base de datos");
						stm.executeUpdate("update medicamentos set Unidades="+(Integer.parseInt(v[2])+rs.getInt(2)));
						System.out.println("Se ha aumentado el stock");
					}else{
						ResultSet rs2 = stm.executeQuery("select count(*) from laboratorios where CodLab="+v[6]);
						rs2.next();
						if(rs.getInt(1)==0){
							System.out.println("No se puede insertar el medicamento con código "+v[0]+" porque el laboratorio con código "+v[6]+" no existe");
						}else{
							stm.executeUpdate("INSERT INTO medicamentos VALUES ("+Integer.parseInt(v[0])+",[value-2],[value-3],[value-4],[value-5],[value-6],[value-7])");
						}
					}
				}
				
			}
		}
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/agenciatributaria","root","12345");
		System.out.println("Coneción realizada");
		Statement stm=conexion.createStatement();
		int opc;
		
		do{
			opc=menu();
			switch (opc) {
			case 1:
				
				break;

			default:
				break;
			}
		}while(opc!=7);
	}
}
