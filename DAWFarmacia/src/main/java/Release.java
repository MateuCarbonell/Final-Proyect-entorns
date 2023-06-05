

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Servlet implementation class Release
 */
@WebServlet("/DAWFarmacia/Release")

public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Release() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String session = request.getParameter("session");
        String idXip = request.getParameter("idXip");
        String nombreMedicine = request.getParameter("medicineSelect");
        String nombrePaciente = request.getParameter("patientSelect");
        String date = request.getParameter("expiryDate");

        BBDD bd = new BBDD();
        bd.conectar();

        try {
            String queryPac = "SELECT mail from patient where name = '" + nombrePaciente + "'";
            System.out.println("primer select release");
            ResultSet rs = bd.loadSelect(queryPac);
            rs.next();
            String mailP = rs.getString("mail");

            String queryMed = "SELECT id from medicine where name = '" + nombreMedicine + "'";
            ResultSet rs2 = bd.loadSelect(queryMed);
            rs2.next();
            String idMed = rs2.getString("id");
            System.out.println("e");

            String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES (" + idXip + ", '" + mail + "', " + idMed + ", '" + mailP + "', '" + date + "')";
            System.out.println("a");
            bd.update(query);
            System.out.println(query);
        } catch (Exception e) {
            // Manejar la excepción de manera apropiadaS
            e.printStackTrace(); // Imprimir el mensaje de la excepción en la consola
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el servidor"); // Enviar una respuesta de error al cliente
        }
    }

}


