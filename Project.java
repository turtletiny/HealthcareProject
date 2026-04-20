
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
        MRIMachine mri2 = new MRIMachine("MRI-2", true);

        mris.add(mri1);
        mris.add(mri2);

        //Maps probe types for ultrasound to an array of their use cases
        HashMap<String, ArrayList<String>> probeDatabase = new HashMap<>();
        probeDatabase.put("Linear", new ArrayList<>(Arrays.asList("Vascular", "Breast")));
        probeDatabase.put("Convex", new ArrayList<>(Arrays.asList("Deeper Organ Imaging", "Breast")));
        probeDatabase.put("Pencil", new ArrayList<>(Arrays.asList("Measure Blood Flow", "Measure Blood Sound Speed")));

        Boolean running = true;
        while (running) {
            System.out.println("\n~Radiology Management Software~");
            System.out.println("-------------------------------");
            System.out.println("\n Menu\n");
            System.out.println("[1] Configure MRI Machines ");
            System.out.println("[2] Configure Ultrasounds ");
            System.out.println("[3] Manage Radiologists ");
            System.out.println("[4] Use Diagnostic Tool ");
            System.out.println("[5] User Manuals ");

            System.out.println("[6] Exit ");
            int menuChoice = In.nextInt();
            if (menuChoice == 1) {
                while (true) {
                    System.out.println("~MRI Machine Configuration~");
                    System.out.println("---------------------------");
                    System.out.println("Select MRI Machine to configure: ");
                    for (int i = 0; i < mris.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + mris.get(i).getName());
                    }
                    System.out.println("[" + (mris.size() + 1) + "] Back");
                    int selection = In.nextInt();
                    if (selection < 1 || selection > mris.size() + 1) {
                        System.out.println("Enter a valid MRI Machine! ");
                        continue;
                    }
                    if (selection == mris.size() + 1) {
                        break; 
                    }

                    MRIMachine selectedMRI = mris.get(selection - 1);

                    while (true) {
                        System.out.println(selectedMRI);
                        System.out.println("[1] Toggle Operational");
                        System.out.println("[2] Set Coil Type");
                        System.out.println("[3] Set Magnetic Field Strength");
                        System.out.println("[4] Back");
                        System.out.println("Select setting to edit: ");
                        int settingChoice = In.nextInt();

                        if (settingChoice == 1) {
                            selectedMRI.isOperationalToggle();
                            System.out.println("Updated MRI Operational state from: " + !selectedMRI.getIsOperational() + " to -> " + selectedMRI.getIsOperational());

                        } else if (settingChoice == 2) {
                            System.out.println("Available Coils: ");
                            System.out.println("[1] Head");
                            System.out.println("[2] Body");
                            System.out.println("[3] Extremity");
                            System.out.println("");
                            System.out.println("Enter coil type:");

                            int coilChoice = In.nextInt();
                            if (coilChoice == 1) {
                                selectedMRI.setSystemSetting("CoilType", "Head");

                            } else if (coilChoice == 2) {
                                selectedMRI.setSystemSetting("CoilType", "Body");

                            } else if (coilChoice == 3) {
                                selectedMRI.setSystemSetting("CoilType", "Extremity");

                            } else {
                                System.out.println("Please enter a valid option. ");
                            }

                        } else if (settingChoice == 3) {
                            System.out.println("Enter value (max 8): ");
                            double magneticFieldStrength = In.nextDouble();
                            if (magneticFieldStrength > 8 || magneticFieldStrength < 0) {
                                System.out.println("Must be between 0 and 8.0");

                            } else {
                                selectedMRI.setSystemSetting("MagneticFieldStrength", magneticFieldStrength + "T");
                            }

                        } else if (settingChoice == 4) {
                            break; 
                        } else {
                            System.out.println("Enter a valid option.");
                        }
                    }
                }

            } else {
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

    public void setIsOperational(boolean isOperational) {
        this.isOperational = isOperational;
    }

    public void isOperationalToggle() {
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
    @Override
    public String toString() {
        String report = "-MRI Machine: " + this.getName() + "-\n";
        report += "Is Operational: " + this.getIsOperational() + "\n";
        report += "CoilType: " + this.systemSettings.get("CoilType") + "\n";
        report += "MagneticFieldStrength: " + this.systemSettings.get("MagneticFieldStrength") + "\n";
        return report;
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
            this.setIsOperational(false);
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
