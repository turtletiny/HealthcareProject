
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Project {

    public static void main(String[] args) {

        //Initialise ArrayLists to store objects
        ArrayList<MRIMachine> mris = new ArrayList<>();
        ArrayList<Ultrasound> ultrasounds = new ArrayList<>();
        ArrayList<Radiologist> radiologists = new ArrayList<>();

        //Initialise Objects and add to Databases
        Radiologist drYagami = new Radiologist("Light Yagami");
        Radiologist drSparda = new Radiologist("Dante Sparda");
        radiologists.add(drYagami);
        radiologists.add(drSparda);

        Ultrasound ultrasound1 = new Ultrasound("Ultrasound-1", true);
        Ultrasound ultrasound2 = new Ultrasound("Ultrasound-2", true);
        ultrasounds.add(ultrasound1);
        ultrasounds.add(ultrasound2);

        MRIMachine mri1 = new MRIMachine("MRI-1", true);
        MRIMachine mri2 = new MRIMachine("MRI-2", true);
        mris.add(mri1);
        mris.add(mri2);

        //Maps probe types for ultrasound to an array of their use cases as a "guide" on how to use them in the manual
        HashMap<String, ArrayList<String>> probeDatabase = new HashMap<>();
        probeDatabase.put("Linear", new ArrayList<>(Arrays.asList("Vascular", "Breast", "Lung")));
        probeDatabase.put("Convex", new ArrayList<>(Arrays.asList("Deeper Organ Imaging", "Breast")));
        probeDatabase.put("Pencil", new ArrayList<>(Arrays.asList("Measure Blood Flow", "Measure Blood Sound Speed", "Cardiac")));

        //Main Menu
        boolean mainMenuRunning = true;
        while (mainMenuRunning) {
            System.out.println("  ____           _ _       _                     __  __                                                   _   \r\n"
                    + //
                    " |  _ \\ __ _  __| (_) ___ | | ___   __ _ _   _  |  \\/  | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_ \r\n"
                    + //
                    " | |_) / _` |/ _` | |/ _ \\| |/ _ \\ / _` | | | | | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|\r\n"
                    + //
                    " |  _ < (_| | (_| | | (_) | | (_) | (_| | |_| | | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_ \r\n"
                    + //
                    " |_| \\_\\__,_|\\__,_|_|\\___/|_|\\___/ \\__, |\\__, | |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__|\r\n"
                    + //
                    "                                   |___/ |___/                            |___/                               ");
            System.out.println("-------------------------------------------------------");
            System.out.println("~Home~");
            System.out.println("[1] Configure MRI Machines ");
            System.out.println("[2] Configure Ultrasounds ");
            System.out.println("[3] Manage Radiologists ");
            System.out.println("[4] Use Diagnostic Tool ");
            System.out.println("[5] View/Edit User Manual ");
            System.out.println("[6] Exit ");
            System.out.println("-------------------------------------------------------");
            int mainMenuChoice = In.nextInt();
            System.out.println("-------------------------------------------------------");

            //[1] Configure MRI Machines
            if (mainMenuChoice == 1) {
                //Select MRI Machine
                boolean mriMenuRunning = true;
                while (mriMenuRunning) {
                    System.out.println("~MRI Machine Configuration~");
                    System.out.println("Select option: ");
                    for (int i = 0; i < mris.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + mris.get(i).getName());
                    }
                    System.out.println("[" + (mris.size() + 1) + "] Add new MRI Machine");
                    System.out.println("[" + (mris.size() + 2) + "] Back");
                    System.out.println("-------------------------------------------------------");
                    int mriMenuChoice = In.nextInt();
                    System.out.println("-------------------------------------------------------");
                    if (mriMenuChoice < 1 || mriMenuChoice > mris.size() + 2) {
                        System.out.println("Invalid option! ");
                        continue; //restarts while loop

                        //Back button
                    } else if (mriMenuChoice == mris.size() + 2) {
                        break;

                        //Add new MRI Machine
                    } else if (mriMenuChoice == mris.size() + 1) {
                        boolean addMriRunning = true;
                        while (addMriRunning) {
                            boolean duplicateName = false; //Flag to check if new MRI name already exists
                            System.out.println("Enter new MRI Machine name: ");
                            String newMRI = In.nextLine();
                            System.out.println("-------------------------------------------------------");

                            //Loop through mri database - if it finds a name equal to input name, set flag to true
                            for (MRIMachine mri : mris) {
                                if (mri.getName().equals(newMRI)) {
                                    System.out.println("Name already exists!");
                                    duplicateName = true;
                                    break; //stop searching through database once found to save memory
                                }
                            }
                            if (!duplicateName) {
                                mris.add(new MRIMachine(newMRI, true));
                                System.out.println("New MRI Machine: " + newMRI + " added. ");
                                addMriRunning = false;

                            }

                        }
                        //Choose Settings for selected MRI
                    } else {
                        boolean mriSettingsMenuRunning = true;
                        while (mriSettingsMenuRunning) {
                            MRIMachine selectedMRI = mris.get(mriMenuChoice - 1);
                            System.out.println(selectedMRI);
                            System.out.println("Select setting to edit: ");
                            System.out.println("[1] Set Name");
                            System.out.println("[2] Toggle Operational");
                            System.out.println("[3] Set Coil Type");
                            System.out.println("[4] Set Magnetic Field Strength");
                            System.out.println("[5] Back");
                            System.out.println("-------------------------------------------------------");
                            int mriSettingChoice = In.nextInt();
                            System.out.println("-------------------------------------------------------");

                            //[1] Set name
                            if (mriSettingChoice == 1) {
                                while (true) {
                                    boolean duplicateName = false; //Flag to check if new MRI name already exists
                                    System.out.println("Enter new MRI Machine name: ");
                                    String newName = In.nextLine();
                                    System.out.println("-------------------------------------------------------");
                                    for (MRIMachine mri : mris) {
                                        if (mri.getName().equals(newName)) {
                                            System.out.println("Name already exists!");
                                            duplicateName = true;
                                            break; //stop searching through database once found to save memory
                                        }
                                    }
                                    //if duplicate name is false, set the name and exit loop
                                    if (!duplicateName) {
                                        System.out.println("Updated MRI name from: " + selectedMRI.getName() + " to -> " + newName);
                                        selectedMRI.setName(newName);
                                        break;

                                    }

                                }
                                //[2] Toggle Operational
                            } else if (mriSettingChoice == 2) {
                                selectedMRI.isOperationalToggle();
                                System.out.println("Updated MRI Operational state from: " + !selectedMRI.IsOperational() + " to -> " + selectedMRI.IsOperational());

                                //[3] Set Coil Type
                            } else if (mriSettingChoice == 3) {
                                while (true) {
                                    System.out.println("Enter coil type:");
                                    System.out.println("[1] Head");
                                    System.out.println("[2] Body");
                                    System.out.println("[3] Extremity");
                                    System.out.println("[4] Back");
                                    System.out.println("-------------------------------------------------------");
                                    int coilChoice = In.nextInt();
                                    System.out.println("-------------------------------------------------------");

                                    if (coilChoice == 1) {
                                        selectedMRI.setSystemSetting("CoilType", "Head");
                                        break;

                                    } else if (coilChoice == 2) {
                                        selectedMRI.setSystemSetting("CoilType", "Body");
                                        break;

                                    } else if (coilChoice == 3) {
                                        selectedMRI.setSystemSetting("CoilType", "Extremity");
                                        break;

                                    } else if (coilChoice == 4) {
                                        break;

                                    } else {
                                        System.out.println("Invalid option!");

                                    }

                                }
                                //[4] Set Magnetic Field Strength
                            } else if (mriSettingChoice == 4) {
                                while (true) {
                                    System.out.println("Enter Magnetic Field Strength (max 8): ");
                                    System.out.println("-------------------------------------------------------");
                                    double magneticFieldStrength = In.nextDouble();
                                    System.out.println("-------------------------------------------------------");
                                    if (magneticFieldStrength > 8 || magneticFieldStrength < 0) {
                                        System.out.println("Must be between 0 and 8.0!");

                                    } else {
                                        selectedMRI.setSystemSetting("MagneticFieldStrength", magneticFieldStrength + "T");
                                        break;
                                    }

                                }

                            } else if (mriSettingChoice == 5) {
                                mriSettingsMenuRunning = false;

                            } else {
                                System.out.println("Enter a valid option.");
                            }
                        }
                    }
                }
                //[2] Configure Ultrasounds
            } else if (mainMenuChoice == 2) {

                //Choose Ultrasound
                boolean ultrasoundMenuRunning = true;
                while (ultrasoundMenuRunning) {
                    System.out.println("~Ultrasound Configuration~");
                    System.out.println("Select Ultrasound to configure: ");
                    for (int i = 0; i < ultrasounds.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + ultrasounds.get(i).getName());
                    }
                    System.out.println("[" + (ultrasounds.size() + 1) + "] Add new Ultrasound");
                    System.out.println("[" + (ultrasounds.size() + 2) + "] Back");
                    System.out.println("-------------------------------------------------------");
                    int ultrasoundMenuChoice = In.nextInt();
                    System.out.println("-------------------------------------------------------");

                    if (ultrasoundMenuChoice < 1 || ultrasoundMenuChoice > (ultrasounds.size() + 2)) {
                        System.out.println("Invalid option! ");
                        continue; //restarts while loop
                    } //Back button
                    else if (ultrasoundMenuChoice == ultrasounds.size() + 2) {
                        ultrasoundMenuRunning = false;
                    } //Add new Ultrasound
                    else if (ultrasoundMenuChoice == (ultrasounds.size() + 1)) {
                        while (true) {
                            boolean duplicateName = false; //Flag to check if new Ultrasound name already exists in ultrasound Database

                            System.out.println("Enter new Ultrasound name: ");
                            String newUltrasoundName = In.nextLine();
                            System.out.println("-------------------------------------------------------");
                            for (Ultrasound ultrasound : ultrasounds) {
                                if (ultrasound.getName().equals(newUltrasoundName)) {
                                    System.out.println("Name already exists! ");
                                    duplicateName = true;
                                    break;

                                }

                            }
                            if (!duplicateName) {
                                ultrasounds.add(new Ultrasound(newUltrasoundName, true));
                                System.out.println("New Ultrasound: " + newUltrasoundName + " added. ");
                                break;
                            }

                        }
                        //Configure specific ultrasound object
                    } else {
                        boolean ultrasoundSettingsMenuRunning = true;
                        while (ultrasoundSettingsMenuRunning) {
                            Ultrasound selectedUltrasound = ultrasounds.get(ultrasoundMenuChoice - 1);
                            System.out.println(selectedUltrasound);
                            System.out.println("Select setting to edit: ");
                            System.out.println("[1] Set Name");
                            System.out.println("[2] Toggle Operational");
                            System.out.println("[3] Set Probe Type");
                            System.out.println("[4] Back");
                            System.out.println("-------------------------------------------------------");
                            int ultrasoundSettingChoice = In.nextInt();
                            System.out.println("-------------------------------------------------------");

                            //[1] Set Name
                            if (ultrasoundSettingChoice == 1) {
                                System.out.println("Enter new name: ");
                                String newName = In.nextLine();
                                System.out.println("Updated Ultrasound name from: " + selectedUltrasound.getName() + " to -> " + newName);
                                selectedUltrasound.setName(newName);

                                //[2] Toggle Operational
                            } else if (ultrasoundSettingChoice == 2) {
                                selectedUltrasound.isOperationalToggle();
                                System.out.println("Updated Ultrasound state from: " + !selectedUltrasound.IsOperational() + " to -> " + selectedUltrasound.IsOperational());

                                //[3] Set Probe Type
                            } else if (ultrasoundSettingChoice == 3) {

                                while (true) {
                                    //Print out options
                                    System.out.println("Select probe type: ");
                                    int probeNum = 1;
                                    for (Map.Entry<String, ArrayList<String>> entry : probeDatabase.entrySet()) {
                                        String key = entry.getKey();
                                        System.out.println("[" + probeNum + "] " + key);
                                        probeNum++;
                                    }
                                    System.out.println("[" + (probeDatabase.size() + 1) + "] Back");
                                    System.out.println("-------------------------------------------------------");
                                    int probeTypeChoice = In.nextInt();
                                    System.out.println("-------------------------------------------------------");

                                    //Error
                                    if (probeTypeChoice > probeDatabase.size() + 1) {
                                        System.out.println("Invalid option!");
                                        continue;

                                    //Back Button
                                    } else if (probeTypeChoice == probeDatabase.size() + 1) { 
                                        break;

                                    } else {
                                        //loop through probe Database, incrementing n every loop until n == user selection
                                        String newProbeType = "";
                                        int n = 1;
                                        for (Map.Entry<String, ArrayList<String>> entry : probeDatabase.entrySet()) { 
                                            if (n == probeTypeChoice) { 
                                                newProbeType += entry.getKey(); //get the name of the probe
                                                break; //stop looping though probe database
                                            }
                                            n++;
                                        }
                                        System.out.println("Updated Ultrasound probe type from: " + selectedUltrasound.getCurrentProbeType() + " to -> " + newProbeType);
                                        selectedUltrasound.setCurrentProbeType(newProbeType);
                                        break;
                                    }

                                }
                                //[4] Back button
                            } else if (ultrasoundSettingChoice == 4) {
                                ultrasoundSettingsMenuRunning = false;

                            } else {
                                System.out.println("Invalid option!");
                            }

                        }
                    }
                }

                //[3] Manage Radiologists
            } else if (mainMenuChoice == 3) {
                boolean manageRadiologistsMenuRunning = true;
                while (manageRadiologistsMenuRunning) {
                    System.out.println("~Radiologist Configuration~");
                    System.out.println("Select Radiologist to configure: ");
                    for (int i = 0; i < radiologists.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + radiologists.get(i).getFullName());
                    }
                    System.out.println("[" + (radiologists.size() + 1) + "] Add new Radiologist");
                    System.out.println("[" + (radiologists.size() + 2) + "] Back");
                    System.out.println("-------------------------------------------------------");
                    int radiologistMenuChoice = In.nextInt();
                    System.out.println("-------------------------------------------------------");
                    if (radiologistMenuChoice < 1 || radiologistMenuChoice > radiologists.size() + 2) {
                        System.out.println("Invalid option! ");
                        continue; //restarts while loop
                    } else if (radiologistMenuChoice == radiologists.size() + 2) { //Back Button
                        break;
                    } else if (radiologistMenuChoice == radiologists.size() + 1) { //Add new Radiologist
                        while (true) {
                            boolean duplicateName = false; //Flag to check if new Radiologist name already exists
                            System.out.println("Enter new Radiologist Full Name: ");
                            String newRadiologistName = In.nextLine();
                            System.out.println("-------------------------------------------------------");
                            for (Radiologist radiologist : radiologists) {
                                if (radiologist.getFullName().equals(newRadiologistName)) {
                                    System.out.println("Name already exists!");
                                    duplicateName = true;
                                    break;
                                }
                            }
                            if (duplicateName) {
                                continue;
                            }
                            radiologists.add(new Radiologist(newRadiologistName));
                            System.out.println("New Radiologist: " + newRadiologistName + " added. ");
                            break;

                        }

                    }
                    while (true) {
                        Radiologist selectedRadiologist = radiologists.get(radiologistMenuChoice - 1);
                        System.out.println("Select option: ");
                        System.out.println("[1] Set Name");
                        System.out.println("[2] Print Experience Report");
                        System.out.println("[3] Edit Experience ");
                        System.out.println("[4] Back");
                        System.out.println("-------------------------------------------------------");
                        int radiologistFunctionChoice = In.nextInt();
                        System.out.println("-------------------------------------------------------");

                        if (radiologistFunctionChoice == 1) {
                            while (true) {
                                boolean duplicateName = false; //Flag to check if new Radiologist name already exists in ultrasoundDatabase

                                System.out.println("Enter new Radiologist Full Name: ");
                                String newRadiologistName = In.nextLine();
                                System.out.println("-------------------------------------------------------");
                                for (Radiologist radiologist : radiologists) {
                                    if (radiologist.getFullName().equals(newRadiologistName)) {
                                        System.out.println("Name already exists! ");
                                        duplicateName = true;
                                        break;

                                    }

                                }
                                if (duplicateName) {
                                    continue;
                                }
                                System.out.println("Updated Radiologist name from  " + selectedRadiologist.getFullName() + " to -> " + newRadiologistName);
                                selectedRadiologist.setFullName(newRadiologistName);
                                break;
                            }
                        } else if (radiologistFunctionChoice == 2) {
                            selectedRadiologist.printExperienceReport();
                            System.out.println("Press [ENTER] to stop reading");
                            In.nextLine();

                        } else if (radiologistFunctionChoice == 3) {
                            while (true) {
                                int mriMachineExperience = selectedRadiologist.getToolExperience().get("MRIMachine");
                                int ultrasoundExperience = selectedRadiologist.getToolExperience().get("Ultrasound");
                                System.out.println("Select tool experience to edit: ");
                                System.out.println("[1] MRI Machine: " + mriMachineExperience + " uses");
                                System.out.println("[2] Ultrasound: " + ultrasoundExperience + " uses");
                                System.out.println("[3] Back");
                                System.out.println("-------------------------------------------------------");
                                int toolExperienceChoice = In.nextInt();
                                System.out.println("-------------------------------------------------------");

                                if (toolExperienceChoice == 1) {
                                    while (true) {
                                        int currentMRIExperience = selectedRadiologist.getToolExperience().get("MRIMachine");
                                        System.out.println("Enter new MRI Machine experience: ");
                                        System.out.println("-------------------------------------------------------");
                                        int newExperience = In.nextInt();
                                        System.out.println("-------------------------------------------------------");
                                        if (newExperience < 0) {
                                            System.out.println("Must be a positive number! ");
                                        } else {
                                            selectedRadiologist.setToolExperience("MRIMachine", newExperience);
                                            System.out.println("Updated MRI Machine experience from: " + currentMRIExperience + "to -> " + newExperience);
                                            break;

                                        }

                                    }

                                } else if (toolExperienceChoice == 2) {
                                    while (true) {
                                        int currentUltrasoundExperience = selectedRadiologist.getToolExperience().get("Ultrasound");
                                        System.out.println("Enter new Ultrasound experience: ");
                                        System.out.println("-------------------------------------------------------");
                                        int newExperience = In.nextInt();
                                        System.out.println("-------------------------------------------------------");
                                        if (newExperience < 0) {
                                            System.out.println("Must be a positive number! ");
                                        } else {
                                            selectedRadiologist.setToolExperience("Ultrasound", newExperience);
                                            System.out.println("Updated Ultrasound experience from: " + currentUltrasoundExperience + "to -> " + newExperience);
                                            break;

                                        }

                                    }

                                } else if (toolExperienceChoice == 3) {
                                    break;

                                } else {
                                    System.out.println("Invalid option!");
                                    continue;
                                }

                            }

                        } else if (radiologistFunctionChoice == 4) {
                            break;
                        } else {
                            System.out.println("Invalid option!");
                        }

                    }
                }

                //[4] Use Diagnostic Tool
            } else if (mainMenuChoice == 4) {
                while (true) {
                    System.out.println("Enter Tool to use: ");
                    System.out.println("[1] MRI Machine");
                    System.out.println("[2] Ultrasound");
                    System.out.println("[3] Back");
                    System.out.println("-------------------------------------------------------");
                    int useToolChoice = In.nextInt();
                    System.out.println("-------------------------------------------------------");
                    if (useToolChoice == 1) {
                        while (true) {
                            System.out.println("Select MRI Machine to activate: ");
                            int i = 1;
                            for (MRIMachine mri : mris) {
                                System.out.println("[" + i + "] " + mri.getName());
                                i++;
                            }
                            System.out.println("[" + i + "] Back");
                            System.out.println("-------------------------------------------------------");
                            int useMRIChoice = In.nextInt();
                            System.out.println("-------------------------------------------------------");
                            if (useMRIChoice > i || useMRIChoice < 1) {
                                System.out.println("Invalid option!");
                            } else if (useMRIChoice == i) {
                                break;
                            } else {
                                while (true) {
                                    System.out.println("Select Radiologist to operate MRI Machine: ");
                                    int j;
                                    for (j = 0; j < radiologists.size(); j++) {
                                        System.out.println("[" + (j + 1) + "] " + radiologists.get(j).getFullName());
                                    }
                                    System.out.println("[" + (j + 1) + "] Back");
                                    System.out.println("-------------------------------------------------------");
                                    int radiologistOperatorChoice = In.nextInt();
                                    System.out.println("-------------------------------------------------------");
                                    if (radiologistOperatorChoice > (j + 1) || radiologistOperatorChoice < 1) {
                                        System.out.println("Invalid option! ");
                                        continue;
                                    } else if (radiologistOperatorChoice == (j + 1)) {
                                        break;

                                    } else {
                                        MRIMachine selectedMRIToUse = mris.get(useMRIChoice - 1);
                                        Radiologist selectedRadiologist = radiologists.get(radiologistOperatorChoice - 1);
                                        selectedMRIToUse.activate(selectedRadiologist);
                                        System.out.println("-------------------------------------------------------");
                                        break;

                                    }

                                }

                            }

                        }

                    } else if (useToolChoice == 2) {

                        System.out.println("Select Ultrasound to activate: ");
                        int i = 1;
                        for (Ultrasound ultrasound : ultrasounds) {
                            System.out.println("[" + i + "] " + ultrasound.getName());
                            i++;
                        }
                        System.out.println("[" + i + "] Back");
                        System.out.println("-------------------------------------------------------");
                        int useUltrasoundChoice = In.nextInt();
                        System.out.println("-------------------------------------------------------");
                        if (useUltrasoundChoice > i || useUltrasoundChoice < 1) {
                            System.out.println("Invalid option!");
                        } else if (useUltrasoundChoice == i) {
                            break;
                        } else {
                            while (true) {
                                System.out.println("Select Radiologist to operate Ultrasound: ");
                                int j;
                                for (j = 0; j < radiologists.size(); j++) {
                                    System.out.println("[" + (j + 1) + "] " + radiologists.get(j).getFullName());
                                }
                                System.out.println("[" + (j + 1) + "] Back");
                                System.out.println("-------------------------------------------------------");
                                int radiologistOperatorChoice = In.nextInt();
                                System.out.println("-------------------------------------------------------");
                                if (radiologistOperatorChoice > (j + 1) || radiologistOperatorChoice < 1) {
                                    System.out.println("Invalid option! ");
                                    continue;
                                } else if (radiologistOperatorChoice == (j + 1)) {
                                    break;

                                } else {
                                    Ultrasound selectedUltrasoundToUse = ultrasounds.get(useUltrasoundChoice - 1);
                                    Radiologist selecteRadiologist = radiologists.get(radiologistOperatorChoice - 1);
                                    selectedUltrasoundToUse.activate(selecteRadiologist);
                                    System.out.println("-------------------------------------------------------");
                                    break;

                                }

                            }

                        }

                    } else if (useToolChoice == 3) {
                        break;

                    } else {
                        System.out.println("Invalid option!");
                    }

                }

                //[5] User Manuals
            } else if (mainMenuChoice == 5) {
                while (true) {
                    System.out.println("[1] View Manual");
                    System.out.println("[2] Edit Probe Types");
                    System.out.println("[3] Back");
                    System.out.println("-------------------------------------------------------");

                    int manualChoice = In.nextInt();
                    System.out.println("-------------------------------------------------------");

                    if (manualChoice == 1) {
                        System.out.println("~Ultrasound Manual~");
                        System.out.println("Probe Types are not initialised by default. Probes must be set in 'Configure Ultrasounds' to activate ultrasounds");
                        System.out.println("Recommended Probe Type Uses: ");
                        for (Map.Entry<String, ArrayList<String>> entry : probeDatabase.entrySet()) {
                            String key = entry.getKey();
                            ArrayList<String> value = entry.getValue();
                            System.out.println(key + ": " + value);

                        }
                        System.out.println("~MRI Machine Manual~");
                        System.out.println("All Metallic objects must be removed");
                        System.out.println("");
                        System.out.println("~General~");
                        System.out.println("Only enter text when prompted for text, Otherwise program will crash");
                        System.out.println("-------------------------------------------------------");
                        System.out.println("Press [ENTER] to stop reading ");
                        In.nextLine();

                    } else if (manualChoice == 2) {
                        while (true) {
                            System.out.println("Select probe to add use cases or add new probe type: ");
                            int probeNum = 1;
                            for (Map.Entry<String, ArrayList<String>> entry : probeDatabase.entrySet()) {
                                String key = entry.getKey();
                                System.out.println("[" + probeNum + "] " + key);
                                probeNum++;
                            }
                            System.out.println("[" + probeNum + "] " + "Add new probe type");
                            System.out.println("[" + (probeNum + 1) + "] " + "Back");
                            System.out.println("-------------------------------------------------------");
                            int editProbeChoice = In.nextInt();
                            System.out.println("-------------------------------------------------------");
                            if (editProbeChoice > (probeDatabase.size() + 2) || editProbeChoice < 1) {
                                System.out.println("Invalid option! ");
                                continue;
                            } //Back
                            else if (editProbeChoice == probeDatabase.size() + 2) {
                                break;
                            } //Add new probetype
                            else if (editProbeChoice == (probeDatabase.size() + 1)) {
                                while (true) {
                                    System.out.println("Enter new probe type name: ");
                                    System.out.println("-------------------------------------------------------");
                                    String newProbeTypeName = In.nextLine();
                                    System.out.println("-------------------------------------------------------");
                                    if (probeDatabase.containsKey(newProbeTypeName)) {
                                        System.out.println("Probe already exists! ");
                                        continue;

                                    } else {
                                        ArrayList<String> newUseCases = new ArrayList<>();
                                        int useCaseCount = 1;
                                        while (true) {
                                            System.out.println("Enter use case " + useCaseCount + " for " + newProbeTypeName + " (type: [STOP] to stop adding usecases)");
                                            String newUseCase = In.nextLine();
                                            useCaseCount++;
                                            System.out.println("-------------------------------------------------------");
                                            if (newUseCase.equals("STOP")) {
                                                break;
                                            } else {
                                                newUseCases.add(newUseCase);
                                            }

                                        }
                                        probeDatabase.put(newProbeTypeName, newUseCases);
                                        System.out.println("Added use cases");
                                        break;

                                    }
                                }

                            } else {
                                int count = 1;
                                String selectedProbe = "";
                                ArrayList<String> selectedProbeUseCases = new ArrayList<>();

                                for (Map.Entry<String, ArrayList<String>> entry : probeDatabase.entrySet()) {
                                    String key = entry.getKey();

                                    if (count == editProbeChoice) {
                                        selectedProbe = key;
                                        selectedProbeUseCases = entry.getValue();
                                        System.out.println("Current use cases for: " + selectedProbe);
                                        System.out.println(selectedProbeUseCases);
                                    }
                                    count++;

                                }
                                while (true) {
                                    System.out.println("Enter new use case for: " + selectedProbe + " (type: [STOP] to stop adding usecases)");
                                    System.out.println("-------------------------------------------------------");
                                    String newUseCase = In.nextLine();
                                    System.out.println("-------------------------------------------------------");
                                    if (!newUseCase.equals("STOP")) {
                                        selectedProbeUseCases.add(newUseCase);

                                    } else {
                                        probeDatabase.put(selectedProbe, selectedProbeUseCases);
                                        System.out.println("Added use cases");
                                        break;

                                    }
                                    probeDatabase.put(selectedProbe, selectedProbeUseCases);

                                }

                            }
                        }

                    } else if (manualChoice == 3) {
                        break;
                    } else {
                        System.out.println("Invalid option! ");
                        continue;
                    }
                }

                //[6] Exit Main Menu
            } else if (mainMenuChoice == 6) {
                mainMenuRunning = false;

                //[Invalid option]
            } else {
                System.out.println("Invalid option!");
                continue;

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

    public boolean IsOperational() {
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
    public void activate(Radiologist radiologist) {
        System.out.println("SAFETY WARNING: " + getSafetyWarning());
        System.out.println("Welcome Dr " + radiologist.getFullName());
        System.out.println("Starting ...");
    }

    public String getSafetyWarning() {
        String base = "Only authorised persons may use this tool.";
        if (!this.isOperational) {
            base += "\n Warning: Tool Not Operational";
        }
        return base;
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
        this.systemSettings.put("MagneticFieldStrength", "none");
        this.systemSettings.put("CoilType", "none");

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
            System.out.println("Updated MRI setting: " + key + " from: " + oldValue + " to -> " + value);
        } else {
            this.systemSettings.put(key, value);
            System.out.println("Added new MRI setting: " + key + ", Set to: " + value);

        }

    }

    //overrides of 2 methods
    //at least 1 shuold modify value of a class attribute
    //both should reference an attribute or method of parent class
    @Override
    public void activate(Radiologist radiologist) {
        if (!this.IsOperational()) {
            System.out.println("MRI Machine " + this.name + " is not operational. Activation failed. ");
            System.out.println("Please reactivate once MRI Machine " + this.name + " is operational");
            return;

        }

        for (Map.Entry<String, String> entry : this.systemSettings.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.equals("") || value.equals("none")) {
                System.out.println("Value not selected for parameter: " + key + ". System safety protocol activated: ");
                System.out.println("Is Operational set to: false");
                this.setIsOperational(false);
                System.out.println("Please reactivate once Ultrasound " + this.name + " has all settings initialised");
                return;

            }

        }
        //No issues: activate MRI Machine
        super.activate(radiologist);
        System.out.println("Starting MRI Machine: " + this.name);
        System.out.println("Emitting Magnetic Fields...");
        radiologist.logScan("MRIMachine");
        System.out.println("Scan logged for: " + radiologist.getFullName());

    }

    @Override
    public String getSafetyWarning() {
        String message = super.getSafetyWarning();
        message += "\n- ALL METALLIC OBJECTS MUST BE REMOVED BEFORE ENTRY.";
        return message;
    }

    //toString 
    @Override
    public String toString() {
        String report = "MRI Machine: " + this.getName() + "\n";
        report += "Is Operational: " + this.IsOperational() + "\n";
        report += "CoilType: " + this.systemSettings.get("CoilType") + "\n";
        report += "MagneticFieldStrength: " + this.systemSettings.get("MagneticFieldStrength") + "\n";
        return report;
    }
}

class Ultrasound extends DiagnosticTool {

    private String currentProbeType; //type of probe used (narrow, round, etc)

    public Ultrasound(String name, boolean isOperational) {
        super(name, isOperational);
        this.currentProbeType = "none"; //initialise currentProbeType to blank by default

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
    public void activate(Radiologist radiologist) {
        if (!this.IsOperational()) {
            System.out.println("Ultrasound " + this.name + " is not operational. Activation failed.");
            return;
        }

        //if probeType is empty, set to 'not operational'
        if (this.currentProbeType.equals("") || this.currentProbeType.equals("none")) {
            System.out.println("No probe selected. System safety protocol activated: ");
            System.out.println("Is Operational set to: false");
            this.setIsOperational(false);
            return;
        }

        //No issues: activate Ultrasound
        super.activate(radiologist);
        System.out.println("Current Probe Equipped: " + this.getCurrentProbeType());
        System.out.println("Emitting sound waves");
        radiologist.logScan("Ultrasound");
        System.out.println("Scan logged for: " + radiologist.getFullName());
    }

    @Override
    public String getSafetyWarning() {
        String message = super.getSafetyWarning();
        if (this.currentProbeType.equals("Pencil")) {
            message += "\n- WARNING: Pencil probe in use. Do not apply excessive force";
        } else {
            message += "\n- Ensure sufficient gel is applied for " + (this.currentProbeType) + " probe.";
        }
        message += "\n- Thermal index must be monitored during use.";
        return message;
    }

    //toString 
    @Override
    public String toString() {
        String report = "Ultrasound: " + this.getName() + "\n";
        report += "Is Operational: " + this.IsOperational() + "\n";
        report += "Current Probe Type: " + this.currentProbeType;
        return report;
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
            System.out.println(key + ": " + value + " uses");

        }
        System.out.println("-------------------------------------------------------");

    }

    public String toString() {
        return "Radiologist Name: " + this.fullName;
    }

}
