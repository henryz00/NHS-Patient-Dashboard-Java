import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Patient> patients;
    private ReadCSV csv;
    private JSONFormatter json;

    Model(){
        patients = new ArrayList<>();
    }

    public void readFile(String path){
        patients = csv.readCSV(path);

    }

    public String getAllPatients(){
        return json.getAllPatientJson(patients);
    }


    public String getPatient(Patient p){
        return json.getSinglePatientJson(p);
    }

    public String getName(Patient p){
        return p.get("FIRST") + " " + p.get("LAST");
    }

    public String getId(Patient p){
        return p.get("ID");
    }

}
