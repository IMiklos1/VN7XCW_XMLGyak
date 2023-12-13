package vn7xcw;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

public class JSONWrite {
	 public static void main(String[] args) {
    	 JSONParser JSONparser = new JSONParser();

         try (Reader reader = new FileReader("VN7XCW_1206/JSONParseVN7XCW/orarendVn7xcw.json")) {
            JSONObject JSONObject = new JSONObject();

            JSONArray oraArray = new JSONArray();
            oraArray.add(createLesson(new String[]{"Szoftvertesztel�s", "Dr. Horny�k Oliv�r","M�rn�kinformatikus","H�tf�","10:00","12:00","Inf/103"}));
            oraArray.add(createLesson(new String[]{"Szoftvertesztel�s", "Dr. Horny�k Oliv�r","H�tf�","12:00","14:00","Inf/103."}));
            oraArray.add(createLesson(new String[]{"WEB technol�gi�k 1", "Ag�rdi Anita", "M�rn�kinformatikus","H�tf�","14:00","16:00","XXX. el�ad�"}));
            oraArray.add(createLesson(new String[]{"WEB technol�gi�k 1", "Ag�rdi Anita", "M�rn�kinformatikus","H�tf�","16:00","18:00","Inf/202"}));
            oraArray.add(createLesson(new String[]{"Mesters�ges intelligencia", "Dr. Fazekas Levente", "M�rn�kinformatikus","Kedd","10:00","12:00","XXXII.el�ad�"}));
            oraArray.add(createLesson(new String[]{"Adatkezel�s XML-ben", "Dr. Bednarik L�szl�", "M�rn�kinformatikus","Kedd","12:00","14:00","XXXII.el�ad�"}));
            oraArray.add(createLesson(new String[]{"WEB-es alkalmaz�sok (C#)", "�rvai L�szl� Lajos", "M�rn�kinformatikus","Kedd","14:00","16:00","Inf/101"}));
            oraArray.add(createLesson(new String[]{"WEB-es alkalmaz�sok (C#)", "Dr. Mileff P�ter", "M�rn�kinformatikus","Kedd","16:00","18:00","Inf/101"}));
            oraArray.add(createLesson(new String[]{"Adatkezel�s XML-ben", "Dr. Bednarik L�szl�", "M�rn�kinformatikus","Szerda","10:00","12:00","Inf/101"}));
            oraArray.add(createLesson(new String[]{"Integr�lt v�llalati rendszerek", "Dr. Samad Dadvandipour", "M�rn�kinformatikus","Szerda","12:00","14:00","X. A1/218."}));
            oraArray.add(createLesson(new String[]{"Integr�lt v�llalati rendszerek", "Dr. Kulcs�rn� Dr. Forrai M�nika", "M�rn�kinformatikus","Szerda","14:00","16:00","Inf/15"}));
            oraArray.add(createLesson(new String[]{"Mesters�ges intelligencia", "Dr. Fazekas Levente", "M�rn�kinformatikus","Cs�t�rt�k","10:00","12:00","I.el�ad�"}));

            for (int i = 0; i < oraArray.size() ;i++) {
                JSONObject localObject = (JSONObject) oraArray.get(i);
                System.out.println("\n�ra");
                System.out.println("  T�rgy: " + localObject.get("targy"));
                System.out.println("  Oktat�: " + localObject.get("oktato"));
                System.out.println("  Szak: " + localObject.get("szak"));
                System.out.println("  Id�pont: ");

                JSONObject time = (JSONObject) localObject.get("idopont");
                System.out.println("    Nap: " + time.get("nap"));
                System.out.println("    T�l: " + time.get("tol"));
                System.out.println("    Ig: " + time.get("ig"));
                System.out.println("  Helysz�n: " + localObject.get("helyszin"));
             }

            JSONObject oraObject = new JSONObject();
            oraObject.put("ora", oraArray);
            JSONObject.put("orarendVn7xcw", oraObject);

            FileWriter file = new FileWriter("orarendVn7xcw.json");
            file.write(JSONObject.toString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static JSONObject createLesson(String[] data) {
        JSONObject localObject = new JSONObject();

        localObject.put("targy", data[0]);
        localObject.put("oktato", data[1]);
        localObject.put("szak", data[2]);
        
        
        JSONObject timeObject = new JSONObject();
        
        timeObject.put("nap",data[3]);
        timeObject.put("tol",data[4]);
        timeObject.put("ig",data[5]);
        localObject.put("idopont",timeObject);
        localObject.put("helyszin", data[6]);

        return localObject;
    }

}
