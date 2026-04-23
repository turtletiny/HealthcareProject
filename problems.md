- thinking of a way to incorporate settings for mri machine and ultrasound with only 1 unique class attribute
- researching mrimachines and ultrasounds
- have to think of a way to edit an attribute that fits naturally (editing probe type doesnt make sense because its physical, whereas this is software)



- index out of bounds when adding new ultrasound error - due to selectedUltrasound
problenm: Ultrasound selectedUltrasound = ultrasounds.get(selection - 1); was outside of while loop, so if the user chose back or add name, the array was out of bounds
solution: initialise selectedUltrasound inside the while loop


- method getSafetyProtocol seems unsuitable, because an object is required to use it, which is unrealistic
solution - 



problem: index out of bounds becaues printing name of just removed item

solution: get the name as a string first
String removedMRIName = mris.get(removeMRIChoice - 1).getName();
                            mris.remove(removeMRIChoice - 1);
                            System.out.println("Removed " + removedMRIName);
                            break;

NVM its still an issue



problem - no way i have to add this much code just to edit the usecase :sob: 



- if you dont add any usecases, it still says STOP - i dont care though 