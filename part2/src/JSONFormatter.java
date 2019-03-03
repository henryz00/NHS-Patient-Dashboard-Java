import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONFormatter {

    JSONFormatter(){

    }

    public String getSinglePatientJson(Patient patient){
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        String[] fields = patient.getFields();

        for(String field : fields){
            builder.append('"');
            builder.append(field);
            builder.append("\" : \"");
            builder.append(patient.get(field));
            builder.append("\",\n");
        }

        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("}");

        return builder.toString();

    }


    private String getJsonArray(String[] jsons){
        StringBuilder builder = new StringBuilder();
        builder.append("[");

        for(String json : jsons){
            builder.append(json);
            builder.append(",\n");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");

        return builder.toString();
    }


    public String getAllPatientJson(List<Patient> patients){

        ArrayList<String> patientsArray = new ArrayList<>();
        for(Patient p : patients){
            patientsArray.add(getSinglePatientJson(p));
        }

        String patientsJson = getJsonArray(patientsArray.toArray(new String[0]));

        return "{\n\"patients\" : " + patientsJson + "\n}";


    }



    private String[] parseOneStringEntry(String entry){
        Pattern p = Pattern.compile("\"([^:]*)\"");
        Matcher m = p.matcher(entry);

        String[] res = {"",""};
        if(!m.find()){return null;}
        res[0] = m.group(1);           //entry name
        if(!m.find()){return null;}
        res[1] = m.group(1);          //entry content

        return res;
    }

//    public static void main(String[] args){
//        new JSONFormatter().parseOneEntry("{\"patient\":\"adfwefwe{a\"fwefew\",}fwefewfew\"}");
//    }

}
