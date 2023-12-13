package vn7xcw;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONRead {
	
	public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try (Reader reader = new FileReader("VN7XCW_1206/JSONParseVN7XCW/orarendVn7xcw.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            jsonObject = (JSONObject) jsonObject.get("orarendVn7xcw");

            JSONArray jsonArray = (JSONArray) jsonObject.get("ora");

            for (int i = 0; i < jsonArray.size(); i++) {
            	
                JSONObject localObject = (JSONObject) jsonArray.get(i);
                
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
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
