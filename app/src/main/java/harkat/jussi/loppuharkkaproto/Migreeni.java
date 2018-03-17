package harkat.jussi.loppuharkkaproto;


/**
 * Created by Jussi on 17.3.2018.
 */

public class Migreeni {

    public Migreeni (String date, float duration, int painIntensity, String type, String presymptoms,
                     String painLocation, String otherSymptoms, String medicineTaken, String location,
                     String triggers, String menstruation, int iconID){
        super();
        this.date=date;
        this.duration=duration;
        this.painIntensity=painIntensity;
        this.type=type;
        this.presymptoms=presymptoms;
        this.painLocation=painLocation;
        this.otherSymptoms=otherSymptoms;
        this.medicineTaken=medicineTaken;
        this.location=location;
        this.triggers=triggers;
        this.menstruation=menstruation;
        this.iconID = painIntensity;
    }

    private String date;
    private float duration;
    private int painIntensity;
    private String type;
    private String presymptoms;
    private String painLocation;
    private String otherSymptoms;
    private String medicineTaken;
    private String location;
    private String triggers;
    private String menstruation;
    private int iconID;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPainIntensity() {
        return painIntensity;
    }

    public void setPainIntensity(int painIntensity) {
        this.painIntensity = painIntensity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPresymptoms() {
        return presymptoms;
    }

    public void setPresymptoms(String presymptoms) {
        this.presymptoms = presymptoms;
    }

    public String getPainLocation() {
        return painLocation;
    }

    public void setPainLocation(String painLocation) {
        this.painLocation = painLocation;
    }

    public String getOtherSymptoms() {
        return otherSymptoms;
    }

    public void setOtherSymptoms(String otherSymptoms) {
        this.otherSymptoms = otherSymptoms;
    }

    public String getMedicineTaken() {
        return medicineTaken;
    }

    public void setMedicineTaken(String medicineTaken) {
        this.medicineTaken = medicineTaken;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTriggers() {
        return triggers;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }

    public String getMenstruation() {
        return menstruation;
    }

    public void setMenstruation(String menstruation) {
        this.menstruation = menstruation;
    }

    public int getIconID(){
        return iconID;
    }

}
