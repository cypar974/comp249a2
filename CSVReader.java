import java.util.Arrays;


public class CSVReader {

	String record_line =  "2004, \"I, Robot \",115,Action,PG-13,7.1,Alex Proyas,Will Smith,Bruce Greenwood,Chi McBride";
	
	
	//This method returns the fields without syntax error
	public static String[] fields (String record_line) throws Exception {
		
		String[] fields = null;
		int nbFields = 0;
		boolean missingQuote = false;
		boolean hasQuotes = false;
		
		for (int i =0; i < record_line.length(); i++ ) {
			
			if (record_line.charAt(i) == '"') {
				missingQuote = !missingQuote;
				hasQuotes = true;
				continue;
			}
			
			if (record_line.charAt(i)== ',' && !missingQuote) {
				nbFields ++;
			}
		}
		
		if (missingQuote) {
			throw new MissingQuotesException();
		}
		
		if (nbFields < 10) {
			throw new MissingFieldsException();
		}
		else if (nbFields >10) {
			throw new ExcessFieldsException();
		}
		
		
		
		fields = new String [10];
		
		
		int currentField=0;
		fields[0] = "";
		
		if (hasQuotes) {
			String[] midPoint = record_line.split("\"");
			fields[0] = midPoint[0].substring(0, midPoint.length-1);
			fields[1] = midPoint[1];
			String[] finalArray = midPoint[2].substring(1, midPoint.length).split(",");
		}
		
		System.out.print(fields.toString());
		
		for( int i = 0; i< record_line.length(); i++) {
			if (record_line.charAt(i)== '\"') {
				hasQuotes = !hasQuotes;
				continue;
			}
			
			if (record_line.charAt(i) == ',' && !hasQuotes) {
				fields[currentField] = fields [currentField].trim();
				currentField++;
				
				fields[currentField] = "";
				continue;
				
			}
			
			fields[currentField] += record_line.charAt(i);
			
		}
		
		System.out.println(fields);
		return fields;
	
	}
	
	
	
}
