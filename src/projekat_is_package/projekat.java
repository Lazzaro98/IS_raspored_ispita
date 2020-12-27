package projekat_is_package;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class projekat {
	    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
	    	Kalk k = new Kalk();
			ArrayList<ArrayList<termin>> res = k.resi();
			k.ispis(res);

	    }
}
