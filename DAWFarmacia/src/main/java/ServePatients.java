import jakarta.servlet.ServletException;

import java.util.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import org.json.JSONArray;
/**
 * Servlet implementation class ServePatients*/
 @WebServlet("/DAWFarmacia/ServePatients")

public class ServePatients extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServePatients() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        ArrayList<String> listaPacientes = new ArrayList<>();
        BBDD bd = new BBDD();
        bd.conectar();
        try {
        	System.out.println("Se están cargando los pacientes");
        	String query = "SELECT mail,name FROM patient";
        	ResultSet rs = bd.loadSelect(query);
        	
        	while(rs.next()) {
        		String paciente = rs.getString("mail");
        		String nombrePaciente = rs.getString("name");
        		
        		listaPacientes.add(nombrePaciente);
        		listaPacientes.add(paciente);
        	}
        	
        	}catch (Exception e){
        	System.out.println("Error en lista de pacientes"+ e.getMessage());
        }
	
        JSONArray arrayJSONPatients = new JSONArray(listaPacientes);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(arrayJSONPatients.toString());
        response.getWriter().flush();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
}