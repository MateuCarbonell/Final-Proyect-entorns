import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Xip {
    private int id;
    private Medicine medicine;
    private Patient patient;
    private Date date;

    public Xip() {
   
    }

    public Xip(int id, Medicine medicine, Patient patient, Date date) {
        this.id = id;
        this.medicine = medicine;
        this.patient = patient;
        this.date = date;
    }

    public void load(int id) {
        String query = "SELECT * FROM xip WHERE id=" + id + ";";
        BBDD bd = new BBDD();
        bd.conectar();
        ResultSet rs = bd.loadSelect(query);
        try {
            if (rs != null && rs.next()) {
                this.id = rs.getInt("id");
                // Cargar el objeto Medicine según su id en la base de datos
                int medicineId = rs.getInt("id");
                Medicine medicine = new Medicine();
                medicine.load(medicineId);
                this.medicine = medicine;
                // Cargar el objeto Patient según su correo electrónico en la base de datos
                String patientMail = rs.getString("mail");
                Patient patient = new Patient();
                patient.load(patientMail);
                this.patient = patient;
                // Cargar la fecha de finalización del uso del xip
                this.date = rs.getDate("date");
            }
        } catch (SQLException e) {
            System.out.println("Error in Xip.load: " + e.getMessage());
        }
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	@Override
	public String toString() {
		return "Xip [id=" + id + ", medicine=" + medicine + ", patient=" + patient + ", date=" + date + "]";
	}
    
}
