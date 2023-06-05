import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.*;
public class Doctor extends Person{
	
	private String pass;
	private LocalDate lastLog;
	private int session;
	private ArrayList<Xip> releaseList;
	
	
	public Doctor(){
		
		this.releaseList = new ArrayList<>();
	}
	
	public Doctor(String name,String mail,String pass, LocalDate lastLog, int session) {
		super(name,mail);
		this.pass = pass;
		this.lastLog = lastLog;
		this.session = session;
		this.releaseList = new ArrayList<>();
	}
	
	
	public void load(String id) {
		String query = "SELECT * FROM doctor WHERE mail='"+ id +"';";
		BBDD bd = new BBDD();
		bd.conectar();
		ResultSet rs = bd.loadSelect(query);
		try {
			if(rs!= null && rs.next()) {
				this.setName(rs.getString("name"));
				this.setMail(rs.getString("mail"));
				this.setPass(rs.getString("pass"));
			}
			
		}catch(SQLException e){
			System.out.println("Error a Doctor.load.Class" + e.getMessage());
		}
		
		
	}
	
	public void login(String mail, String pass) {
		String query = "SELECT * FROM doctor WHERE mail='"+ mail + "' AND pass='"+pass+"';";
		BBDD bd = new BBDD();
		bd.conectar();

		try {
			ResultSet rs = bd.loadSelect(query);
			while(!rs.isClosed() && rs.next()) {
				this.setLastLog(LocalDate.now());
				Random random = new Random();
				String code = "";
				for(int i=0;i<9;i++) {
					code += random.nextInt(10);
				
				int session = Integer.parseInt(code);
				this.setSession(session);
				
				query = "UPDATE doctor SET last_log='"+this.getLastLog()+"',session='"+this.getSession()+"' WHERE mail='"+this.getMail()+"';";
				bd.update(query);
				this.load(mail);
				
			}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 public boolean isLogged(String mail, String session) {
	        String query = "SELECT * FROM doctor WHERE mail = '" + mail + "' AND session = '" + session + "';";
	        BBDD bd = new BBDD();
	        bd.conectar();
	        try {
	            ResultSet rs = bd.loadSelect(query);
	            if (rs != null && rs.next()) {
	                return true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	 
	 public ArrayList<Xip> loadReleaseList() {
		    releaseList = new ArrayList<>();

		    String query = "SELECT * FROM xip WHERE doctor_mail='" + getMail() + "';";
		    BBDD bd = new BBDD();
		    bd.conectar();
		    ResultSet rs = bd.loadSelect(query);

		    try {
		        while (rs != null && rs.next()) {
		            int id = rs.getInt("id");
		            int medicineId = rs.getInt("id_medicine");
		            String patientMail = rs.getString("id_patient");
		            Date date = rs.getDate("date");

		            // Cargar el objeto Medicine según su id en la base de datos
		            Medicine medicine = new Medicine();
		            medicine.load(medicineId);

		            // Cargar el objeto Patient según su correo electrónico en la base de datos
		            Patient patient = new Patient();
		            patient.load(patientMail); // Modificar para cargar el objeto completo del paciente
		            
		            // Crear el objeto Xip con los datos cargados
		            Xip xip = new Xip(id, medicine, patient, date);
		            System.out.println(xip.toString());
		            // Agregar el objeto Xip a la lista de releaseList
		            releaseList.add(xip);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error in Doctor.loadReleaseList: " + e.getMessage());
		    }

		    return releaseList; // Devolver la lista completa de xips
		}



	 
	 
	 public String getTable() {
		    StringBuilder table = new StringBuilder();
		    table.append("<table>");
		    table.append("<tr><th>ID</th><th>Patient</th><th>Medicine</th><th>Data de Finalització</th></tr>");
		    ArrayList<Xip> xips = loadReleaseList();
		    for (int i = 0 ; i < xips.size();i++) {
		    	Xip xip = xips.get(i);
		    	int id= xip.getId();
		    	String paciente = xip.getPatient().getMail();
		    	String medicina = xip.getMedicine().getName();
		    	String fecha = xip.getDate().toString();
		    	
		    	
		        table.append("<tr>");
		        table.append("<td>").append(id).append("</td>");
		        table.append("<td>").append(paciente).append("</td>");
		        table.append("<td>").append(medicina).append("</td>");
		        table.append("<td>").append(fecha).append("</td>");
		        table.append("</tr>");
		    }
		    table.append("</table>");
		    return table.toString();
	 }
	 
	 // CLASES EXTRA NECESITADAS POR LOS SERVLETS
	 
	

	  

	
	

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDate getLastLog() {
		return lastLog;
	}

	public void setLastLog(LocalDate lastLog) {
		this.lastLog = lastLog;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	
}
