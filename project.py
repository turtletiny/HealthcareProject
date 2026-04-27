class DiagnosticTool:
    def __init__(self, name, isOperational):
        self.name = name
        self.isOperational = isOperational


    def activate(self, radiologist):
        print("SAFETY WARNING: {}")



    def getSafetyWarning(self):
        base = "Only authorised persons may use this tool."
        if not self.isOperational:
            base += "\n Warning: Tool Not Operational"
        return base
        
    
    
    

class MRIMachine(DiagnosticTool):
    def __init__(self, name, isOperational):
        super(name, isOperational)
        system_settings = {"MagneticFieldStrength" : 0, "CoilType" : None}


    #setSystemSetting()
    def setSystemSetting(self, key, value):
        if key in self.system_settings:
            print(f"Updated MRI setting: {key} from {self.system_settings[key]} to -> {value}")
            
        else:
            print(f"Added new MRI setting: {key}, Set to: value")

        self.system_settings[key] = value


    #activate()
    def activate(self, radiologist):
        if not self.isOperational:
            print(f"MRI Machine {self.name} is not operational. Activation failed. ")
            print(f"Please reactivate once MRI Machine {self.name} is operational")
            return
        
        for key, value in self.systemSettings.items():
            if value is None:
                print(f"Value not selected for parameter: {key}. System safety protocol activated:")
                print("Is Operational set to: false")
                self.isOperational = False
                print(f"Please reactivate once MRI Machine {self.name} has all settings initialised")
                return
            
        super.activate(radiologist)
        print("Starting MRI Machine: {self.name}")
        print("Emitting Magnetic Fields...")
        radiologist.logScan("MRIMachine")
        print(f"Scan logged for: {radiologist.getFullName()}")
            


class Ultrasound(DiagnosticTool):
    def __init__(self, name, isOperational):
        super(name, isOperational)
        currentProbeType = None


    

class Radiologist():
    def __init__(self, name, experienceCount):
        self.name = name
        self.experienceCount = experienceCount