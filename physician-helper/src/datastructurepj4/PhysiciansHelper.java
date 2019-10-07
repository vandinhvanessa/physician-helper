import java.util.*;
import java.io.*;

public class PhysiciansHelper
{
	// symptom to illnesses map 
	private Map<String, List<String>> symptomChecker;


	/* Constructor symptomChecker map using TreeMap */
	public PhysiciansHelper()
	{ 
		// use TreeMap, i.e. sorted order keys
		symptomChecker = new TreeMap<String,List<String>>();
	} // end default constructor


	/* Reads a text file of illnesses and their symptoms.
	   Each line in the file has the form
		Illness: Symptom, Symptom, Symptom, ...  
	   Store data into symptomChecker map */

	public void processDatafile()
	{
		// Step 1: read in a data filename from keybaord
		//         create a scanner for the file

		// Step 2: process data lines in file scanner
		// 2.1 for each line, split the line into a disease and symptoms
		//     make sure to trim() spaces and use toLowercase()
		// 2.2 for symptoms, split into individual symptom
		//     create a scanner for symptoms 
		//     useDelimeter(",") to split into individual symptoms 
		//     make sure to trim() spaces and use toLowercase()
		//     for each symptom
		//        if it is already in the map, insert disease into related list
		//        if it is not already in the map, create a new list with the disease
		// Step 3: display symptomChecker map

		// implement here.....
        System.out.println("Enter the data file name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        try {
            File file = new File(name);
            FileReader x = new FileReader(file);
            BufferedReader y = new BufferedReader (x);
            String ln;
            while ((ln = y.readLine()) != null){
                String[] list = ln.split(":");
                String illness = list[0].trim().toLowerCase();
                input = new Scanner(list[1]).useDelimiter(",");
                String symptom;
                while (input.hasNext()){
                    symptom = input.next().trim().toLowerCase();
                    if (!symptomChecker.containsKey(symptom)){
                        symptomChecker.put(symptom, new ArrayList<String>());
                    }
                    symptomChecker.get(symptom).add(illness);
                }
            }
                x.close();
            }
            catch (Exception ex){ }
            displaySymptomChecker();
        }

	 // end processDatafile



	/*  Read patient's symptoms in a line and adds them to the list.
		Input format: Symptom, Symptom, Symptom,...
	    Shows diseases that match a given number of the symptoms. */

	public void processSymptoms()
	{

		// Step 1: get all possible symptoms from symptomChecker map
		//         and display them
		// Step 2: process patient symptoms, add to patientSymptoms list 
		//         read patient's symptoms in a line, separated by ','
		//         create a scanner for symptoms 
		//         UseDelimeter(",") to split into individual symptoms 
		//         make sure to trim() spaces and use toLowercase()
		//         display invalid/duplicate symptoms
		//         add valid symptoms to patientSymptoms list
		// Step 3: display patientSymptoms list
   	        // Step 4: process illnesses to frequency count
		//         create a map of disease name and frequency count
		//         for each symptom in patientSymptoms list
		//              get list of illnesses from symptomChecker map
		//              for each illness in the list
		// 	            if it is already in the map, increase counter by 1
	        //	            if it is not already in the map, create a new counter 1
		//         ** need to keep track of maximum counter numbers
		// Step 5: display result
		//         for count i = 1 to maximum counter number
		//             display illness that has count i
		 

		// implement here.....
            displaySymptoms();
            System.out.println("Enter symptoms: ");
            Scanner input = new Scanner (System.in);
            input = new Scanner(input.nextLine()).useDelimiter(",");
            
            List<String> patient;
            patient = new ArrayList<>();
            while (input.hasNext()){
                patient.add(input.next().trim().toLowerCase());
            }
            for (int i =0; i < patient.size(); i++){
                boolean same = false;
                for (int j = 0; j < i; j++){
                    if (patient.get(i).equals(patient.get(j))){
                        same = true;
                    }
                }
                if (same){
                    System.out.println("duplicate symptom: " + patient.get(i));
                    patient.remove(i);
                    i -= 1;
                }
                else {
                    if (!symptomChecker.containsKey(patient.get(i))){
                        System.out.println("invalid symptom: "+ patient.get(i));
                        patient.remove(i);
                        i -= 1;
                    }
                }
            }
            System.out.println("\n======================\n");
            System.out.println("Patient Symptom list: "+ patient);
            System.out.println("Possible illnesses that match:");
            Map<String, Integer> frequency; 
            frequency = new TreeMap<>();
            Set<String> set = symptomChecker.keySet();
            int match = 0;
            for(String symptom : patient){
                List<String> illnessList = symptomChecker.get(symptom);
                for (String illness : illnessList){
                if (!frequency.containsKey(illness)){
                    frequency.put(illness, 0);
                }
                frequency.put(illness, frequency.get(illness) + 1);
                if (frequency.get(illness) > match){
                    match = frequency.get(illness);
                }
                }
            }
            for (int a = 1; a <= match; a++){
                System.out.println("==> Diseases match: " + a + "symptom(s)");
                for (Map.Entry<String,Integer> entry : frequency.entrySet()){
                    if (entry.getValue() == a){
                        System.out.println(entry.getKey());
                    }
                }
                System.out.println();
            }
	} // end processSymptoms 

        private void displaySymptomChecker(){
            System.out.println("\n=========================");
            System.out.println("symptomChecker map:");
            Iterator<Map.Entry<String, List<String>>> iterator = 
                    symptomChecker.entrySet().iterator();
            while (iterator.hasNext()){
            Map.Entry<String, List<String>> entry = iterator.next();
            System.out.println(entry.getKey()+ "="+ entry.getValue());
        }
            System.out.println("===========================\n");
        }
        
        private void displaySymptoms(){
            System.out.println("Possible symptoms are: ");
            Iterator<String> iterator = symptomChecker.keySet().iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
            System.out.println("\n=========================\n");
        }

	public static void main(String[] args)
	{

		PhysiciansHelper lookup = new PhysiciansHelper();
		lookup.processDatafile();
		lookup.processSymptoms();
	} // end main
} // end PhysiciansHelper
