class DiagnosticTool:
    def __init__(self, name, is_operational):
        self.name = name
        self.is_operational = is_operational

    def activate(self, radiologist):
        print(f"SAFETY WARNING: use of {self.name} must follow site protocols")

    def get_safety_warning(self):
        base = "Only authorised persons may use this tool."
        if not self.is_operational:
            base += "\nWarning: Tool not operational"
        return base


class MRIMachine(DiagnosticTool):
    def __init__(self, name, is_operational):
        super().__init__(name, is_operational)
        self.system_settings = {"magnetic_field_strength": 0, "coil_type": None}

    def set_system_setting(self, key, value):
        if key in self.system_settings:
            print(f"Updated MRI setting: {key} from {self.system_settings[key]} to -> {value}")
        else:
            print(f"Added new MRI setting: {key}, set to: {value}")
        self.system_settings[key] = value

    def activate(self, radiologist):
        if not self.is_operational:
            print(f"MRI machine {self.name} is not operational. Activation failed.")
            print(f"Please reactivate once MRI machine {self.name} is operational")
            return

        for key, value in self.system_settings.items():
            if value is None:
                print(f"Value not selected for parameter: {key}. System safety protocol activated:")
                print("is_operational set to: False")
                self.is_operational = False
                print(f"Please reactivate once MRI machine {self.name} has all settings initialised")
                return

        super().activate(radiologist)
        print(f"Starting MRI machine: {self.name}")
        print("Emitting magnetic fields...")
        radiologist.log_scan("mri_machine")
        print(f"Scan logged for: {radiologist.get_full_name()}")

    def get_safety_warning(self):
        message = super().get_safety_warning()
        message += "\n- ALL METALLIC OBJECTS MUST BE REMOVED BEFORE ENTRY."
        return message
    

    @staticmethod
    def is_duplicate_name(name, database):
        for i in database:
            if i.name == name:
                return True
        return False




    def __str__(self):
        report = f"MRI Machine: {self.name} \n"
        report += f"Is Operational: {self.is_operational}\n"
        report += f"Coil Type: {self.system_settings['coil_type']}\n"
        report += f"Magnetic Field Strength: {self.system_settings['magnetic_field_strength']}\n"
        return report


class Ultrasound(DiagnosticTool):
    def __init__(self, name, is_operational):
        super().__init__(name, is_operational)
        self.current_probe_type = None

    def activate(self, radiologist):
        if not self.is_operational:
            print(f"Ultrasound {self.name} is not operational. Activation failed")
            return

        if self.current_probe_type is None:
            print("No probe selected. System safety protocol activated:")
            print("is_operational set to: False")
            self.is_operational = False
            return

        super().activate(radiologist)
        print(f"Current probe equipped: {self.current_probe_type}")
        print("Emitting sound waves")
        radiologist.log_scan("ultrasound")
        print(f"Scan logged for {radiologist.full_name}")

    def get_safety_warning(self):
        message = super().get_safety_warning()
        if self.current_probe_type == "Pencil":
            message += "\n- WARNING: Pencil probe in use. Do not apply excessive force"
        else:
            message += f"\n- Ensure sufficient gel is applied for {self.current_probe_type} probe"

        message += "\nThermal index must be monitored during use."
        return message

    def __str__(self):
        report = f"Ultrasound: {self.name} \n"
        report += f"Is Operational: {self.is_operational}\n"
        report += f"Current Probe Type: {self.current_probe_type}"
        return report


class Radiologist:
    def __init__(self, full_name):
        self.full_name = full_name
        self.tool_experience = {"mri_machine": 0, "ultrasound": 0}

    def log_scan(self, tool):
        if tool not in self.tool_experience:
            self.tool_experience[tool] = 1
        else:
            self.tool_experience[tool] += 1

    def print_experience_report(self):
        print("- Experience Report -\n")
        print(self + "\n")
        for key, value in self.tool_experience.items():
            print(f"{key}: {value} uses")
        print(25 * "-")

    def get_full_name(self):
        return self.full_name

    def __str__(self):
        return f"Radiologist Name: {self.full_name}"



#Program


#Initialising Objects and Databases
mri_database = [MRIMachine("MRI-1", True), MRIMachine("MRI-2", True)]
ultrasound_database = [Ultrasound("Ultrasound-1", True), Ultrasound("Ultrasound-2", True)]
radiologist_database = [Radiologist("Light Yagami"), Radiologist("Dante Sparda")]
probe_database = {"Linear" : ["Vascular", "Breast", "Lung"], "Convex" : ["Deeper Organ Imaging", "Breast"], "Pencil" : ["Measure Blood Flow", "Measure Bloud Sound Speed", "Cardiac"]}



#Main Menu
main_menu_running = True
while main_menu_running:
    print("""
                                                                                                                            
                                                                                                                            
█████▄   ▄▄▄  ▄▄▄▄  ▄▄  ▄▄▄  ▄▄     ▄▄▄   ▄▄▄▄ ▄▄ ▄▄   ██▄  ▄██  ▄▄▄  ▄▄  ▄▄  ▄▄▄   ▄▄▄▄ ▄▄▄▄▄ ▄▄   ▄▄ ▄▄▄▄▄ ▄▄  ▄▄ ▄▄▄▄▄▄ 
██▄▄██▄ ██▀██ ██▀██ ██ ██▀██ ██    ██▀██ ██ ▄▄ ▀███▀   ██ ▀▀ ██ ██▀██ ███▄██ ██▀██ ██ ▄▄ ██▄▄  ██▀▄▀██ ██▄▄  ███▄██   ██   
██   ██ ██▀██ ████▀ ██ ▀███▀ ██▄▄▄ ▀███▀ ▀███▀   █     ██    ██ ██▀██ ██ ▀██ ██▀██ ▀███▀ ██▄▄▄ ██   ██ ██▄▄▄ ██ ▀██   ██   
                                                                                                                            
""")
    print(25*"-")
    print("-Home-")
    print("[1] Configure MRI Machines")
    print("[2] Configure Ultrasounds")
    print("[3] Manage Radiologists")
    print("[4] Use Diagnostic Tool")
    print("[5] View/Edit User Manual")
    print("[6] Exit")
    print(25*"-")
    main_menu_choice = int(input("Select option: "))
    print(25*"-")

    #[1] Configure MRI Machines
    if main_menu_choice == 1:
        mri_menu_running = True
        while mri_menu_running:
            print("~MRI Machine Configuration~")
            for i in range(len(mri_database)):
                print(f"[{i+1}] {mri_database[i].name}")
            print(f"[{i+2}] Add new MRI Machine")
            print(f"[{i+3}] Back")
            print(25*"-")
            mri_menu_choice = int(input("Select option: "))

            #Out of range
            if mri_menu_choice < 1 or mri_menu_choice > i+3:
                print("Invalid option! ")
                continue

            #Back Button
            elif mri_menu_choice == (i+3):
                mri_menu_running = False
            
            #Add New MRI Machine
            elif mri_menu_choice == (i+2):
                add_mri_running = True
                while add_mri_running:
                    print(25*"-")
                    new_mri_name = input("Enter new MRI Machine name: ")
                    print(25*"-")
                    if MRIMachine.is_duplicate_name(new_mri_name, mri_database):
                        print("Name already exists! ")
                    else:
                        mri_database.append(MRIMachine(new_mri_name, True))
                        print(f"New MRI Machine: {new_mri_name} added.")
                        print(25*"-")
                        add_mri_running = False

            #Choose Settings for selected MRI
            else:
                mri_settings_menu_running = True
                while mri_settings_menu_running:
                    selected_mri = mri_database[mri_menu_choice-1]
                    print(selected_mri)
                    print("[1] Set Name")
                    print("[2] Toggle Operational")
                    print("[3] Set Coil Type")
                    print("[4] Set Magnetic Field Strength")
                    print("[5] Back")
                    print(25*"-")
                    mri_settings_choice = int(input("Select option: "))
                    print(25*"-")

                    if mri_settings_choice == 1:
                        while True:
                            print("Enter new MRI Machine name: ")
                            print(25*"-")
                            new_mri_name = input("")
                            MRIMachine.is_duplicate_name(new_mri_name, mri_database)


                    #


    
