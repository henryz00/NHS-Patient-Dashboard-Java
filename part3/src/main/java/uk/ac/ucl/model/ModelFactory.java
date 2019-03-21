package uk.ac.ucl.model;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ModelFactory {
    private static Model model;
    private static Statistics statistics;
//    private static List<Patient> searchResultCache = null;

    public static Model getModel() {
      if (model == null) {
        model = new Model();
        model.readFromCSV("patients/patients100000.csv");
        statistics = new Statistics(model.getPatients());
      }
      return model;
    }

    public static int getPatientAge(Patient p) {
      if (model == null) {
        getModel();
      }
      return statistics.getAge(p);
    }

    public static List<String> getStatistics(){
      if (model == null) {
        getModel();
      }
      return statistics.getStatisticInfo();
    }


    public static void pageDivider(HttpServletRequest request, List<Patient> patients){
      String pagePara = request.getParameter("page");
      if(pagePara == null || pagePara.equals("")){
        pagePara = "1";
      }
      int page = Integer.parseInt(pagePara);
      int patientPerPage = 30;
      int startIndex = (page-1)*patientPerPage;
      int endIndex = (startIndex + patientPerPage > patients.size()) ? patients.size(): startIndex + patientPerPage ;
      if(endIndex > patients.size()){
          endIndex = patients.size();
      }

      List<Patient> currentPagePatients = patients.subList(startIndex, endIndex);
      request.setAttribute("list", currentPagePatients);
      request.setAttribute("numberIndicator","Showing " + (startIndex + 1) + " - " + endIndex + " of " + patients.size() +" patients");
      request.setAttribute("pageCurrent", page);
      request.setAttribute("pageTotal", (patients.size() % patientPerPage == 0) ? (patients.size() / patientPerPage) : (patients.size() / patientPerPage +1));
    }

//    public static void setSearchResultCache(List<Patient> patients){
//        searchResultCache = patients;
//    }
//
//    public static List<Patient> getSearchResultCache(){
//       return searchResultCache;
//    }
}
