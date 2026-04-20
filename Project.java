
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Project {

    public static void main(String[] args) {
        

        //
        ArrayList<Radiologist> radiologists = new ArrayList<>();
        Radiologist drYagami = new Radiologist("Light Yagami");
        radiologists.add(drYagami);

        ArrayList<Ultrasound> ultrasounds = new ArrayList<>();
        Ultrasound ultrasound1 = new Ultrasound("Ultrasound-1", true);
        ultrasounds.add(ultrasound1);

        ArrayList<MRIMachine> mris = new ArrayList<>();
        MRIMachine mri1 = new MRIMachine("MRI-1", true);
        mris.add(mri1);


        //Maps probe types for ultrasound to an array of their use cases
        HashMap<String, ArrayList<String>> probeDatabase = new HashMap<>();
        probeDatabase.put("Linear", new ArrayList<>(Arrays.asList("Vascular", "Breast")));
        probeDatabase.put("Convex", new ArrayList<>(Arrays.asList("Deeper Organ Imaging", "Breast")));
        probeDatabase.put("Pencil", new ArrayList<>(Arrays.asList("Measure Blood Flow", "Measure Blood Sound Speed")));

        int menuChoice;
        Boolean running = true;
        while (running) {
            System.out.println("\n~Radiology Management Software~");
            System.out.println("-------------------------------");
            System.out.println("\n Menu\n");
            System.out.println("1. Configure MRI Machines ");
            System.out.println("2. Configure Ultrasounds ");
            System.out.println("3. Manage Radiologists ");
            System.out.println("4. Use Diagnostic Tool ");
            System.out.println("5. User Manuals ");

            System.out.println(". Exit [5, x, exit] ");
            menuChoice = In.nextInt();

            if (menuChoice == 1) {
                System.out.println("~MRI Machine Configuration~");
                System.out.println("---------------------------");
                System.out.println("Available MRI Machines: ");

                //loop through an array list of mri machines and print them with index as a count for input
            }

        }else{
                    System.out.println("Choose options 1-5 !");

                }

    }

}



}



class DiagnosticTool {

    //2 attributes
    protected String name;
    protected boolean isOperational;

    //constructor
    public DiagnosticTool(String name, boolean isOperational) {
        this.name = name;
        this.isOperational = isOperational;
    }

    //accessor methods
    public String getName() {
        return this.name;

    }

    public boolean getIsOperational() {
        return this.isOperational;
    }

    //mutator methods
    public void setName(String name) {
        this.name = name;
    }

    public void setOperationalStatus(boolean isOperational) {
        this.isOperational = isOperational;
    }

    public void operationalStatusToggle() {
        this.isOperational = !this.isOperational;
    }

    //2 methods to be overriden (at least 1 must be non void)
    public void activate() {
        System.out.println("Starting ...");
    }

    public String getSafetyProtocol() {
        return "Only authorised persons may use this tool ";
    }
}

class MRIMachine extends DiagnosticTool {

    //1 attribute
    private HashMap<String, String> systemSettings;

    //constructor
    public MRIMachine(String name, boolean isOperational) {
        super(name, isOperational);
        this.systemSettings = new HashMap<>();

        //initialise default settings with no values yet
        this.systemSettings.put("MagneticFieldStrength", "");
        this.systemSettings.put("CoilType", "");

    }

    //accessor methods
    public HashMap<String, String> getSystemSettings() {
        return this.systemSettings;

    }

    //mutator methods
    public void setSystemSetting(String key, String value) {
        if (this.systemSettings.containsKey(key)) {
            String oldValue = this.systemSettings.get(key);
            this.systemSettings.put(key, value);
            System.out.println("Updated MRI setting: " + key + " from:  " + oldValue + " to -> " + value);
        } else {
            this.systemSettings.put(key, value);
            System.out.println("Added new MRI setting: " + key + ", Set to: " + value);

        }

    }

    public void removeSystemSetting(String key) {
        this.systemSettings.remove(key);
        System.out.println("Removed MRI setting: " + key);

    }

    //overrides of 2 methods
    //at least 1 shuold modify value of a class attribute
    //both should reference an attribute or method of parent class
    @Override
    public void activate() { //Plan: loop through systemSettings to see if any values are blank. If yes, set default values and then ask the user to reactivate
        if (!this.getIsOperational()) {
            System.out.println("MRI Machine " + this.name + " is not operational. Activation failed. ");
            System.out.println("Please reactivate once MRI Machine " + this.name + " is operational");
            return;

        }

        for (Map.Entry<String, String> entry : this.systemSettings.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.equals("")) {
                System.out.println("Value not selected for parameter: " + key + ". Activation failed. ");
                System.out.println("Initialising default settings values for " + this.name);
                System.out.println("Please reactivate once MRI Machine " + this.name + " has all settings initialised");

                return;
            }

        }
        //No issues: activate MRI Machine
        super.activate();
        System.out.println("Starting MRI Machine: " + this.name);
        System.out.println("Emitting Magnetic Fields...");

    }

    @Override
    public String getSafetyProtocol() {
        String defaultMessage = super.getSafetyProtocol();
        return defaultMessage + "\n All personal belongings and metallic objects must be removed ";

    }

    //toString 
    public String toString() {
        return "MRI Machine name: " + this.name + "\n" + this.systemSettings;
    }
}

class Ultrasound extends DiagnosticTool {

    private String currentProbeType; //type of probe used (narrow, round, etc)

    public Ultrasound(String name, boolean isOperational) {
        super(name, isOperational);
        this.currentProbeType = ""; //initialise currentProbeType to blank by default

    }

    //accessor methods
    public String getCurrentProbeType() {
        return this.currentProbeType;
    }

    //mutator methods
    public void setCurrentProbeType(String probeType) {
        this.currentProbeType = probeType;
    }

    //overrides of 2 methods
    //at least 1 shuold modify value of a class attribute
    //both should reference an attribute or method of parent class
    @Override
    public void activate() {
        if (!this.getIsOperational()) {
            System.out.println("Ultrasound " + this.getName() + " is not operational. Activation failed.");
            return;
        }

        //if probeType is empty, set to 'not operational'
        if (this.currentProbeType.equals("")) {
            System.out.println("No probe selected. System safety protocol activated. ");
            this.setOperationalStatus(false);
            return;
        }

        //No issues: activate Ultrasound
        super.activate();
        System.out.println("Emitting sound waves");
        System.out.println("Current Probe Equipped: " + this.getCurrentProbeType());
    }

    @Override
    public String getSafetyProtocol() {
        String defaultMessage = super.getSafetyProtocol();
        return defaultMessage + "Thermal index must be monitored during use. ";

    }

    //toString 
    @Override
    public String toString() {
        return "Ultrasound name: " + this.getName() + "', operational=" + this.getIsOperational() + ", currentProbeType='" + this.currentProbeType;
    }
}

class Radiologist {

    private String fullName;
    private HashMap<String, Integer> toolExperience;

    public Radiologist(String fullName) {
        this.fullName = fullName;
        this.toolExperience = new HashMap<>(); //Maps tools to how many times they've been used

        toolExperience.put("MRIMachine", 0);
        toolExperience.put("Ultrasound", 0);
    }

    //accessor methods
    public String getFullName() {
        return this.fullName;
    }

    public HashMap<String, Integer> getToolExperience() {
        return this.toolExperience;
    }

    //mutator methods
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setToolExperience(String tool, Integer experience) {
        this.toolExperience.put(tool, experience);
    }

    //2 methods
    public void logScan(String tool) {
        if (!this.toolExperience.containsKey(tool)) { //if the tool is not in the experience log, create the key and set value to 1
            this.toolExperience.put(tool, 1);

        } else {
            int currentExperience = this.toolExperience.get(tool); //gets current experience count for the tool
            this.toolExperience.put(tool, currentExperience + 1); //increments it by 1
        }

    }

    public void printExperienceReport() {

        System.out.println("-Experience Report-");
        System.out.println("");
        System.out.println(this); //uses toString method
        System.out.println("");
        for (Map.Entry<String, Integer> entry : this.toolExperience.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + ": " + value);

        }
        System.out.println("-------------");

    }

    public String toString() {
        return "Radiologist Name: " + this.fullName;
    }

}
