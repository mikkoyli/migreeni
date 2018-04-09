package harkat.jussi.loppuharkkaproto;


import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Jussi on 17.3.2018.
 */

public class Migreeni implements Serializable {

    public Migreeni() {

    }

    public Migreeni(String id, String date, String duration, String start_time, String end_time, String painIntensity, String type, String type2, String presymptoms, String presymptoms2,
                    String painLocation, String otherSymptoms, String medicineTaken, String medicineEffect, String location,
                    String triggers, String triggers2, String menstruation, int iconID) {
        super();
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.start_time = start_time;
        this.end_time = end_time;
        this.painIntensity = painIntensity;
        this.type = type;
        this.type2 = type2;
        this.presymptoms = presymptoms;
        this.presymptoms2 = presymptoms2;
        this.painLocation = painLocation;
        this.otherSymptoms = otherSymptoms;
        this.medicineTaken = medicineTaken;
        this.medicineEffect = medicineEffect;
        this.location = location;
        this.triggers = triggers;
        this.triggers2 = triggers2;
        this.menstruation = menstruation;
        this.iconID = iconID;
    }

    private String id;
    private String date;
    private String duration;
    private String start_time;
    private String end_time;
    private String painIntensity;
    private String type;
    private String type2;
    private String presymptoms;
    private String presymptoms2;
    private String painLocation;
    private String otherSymptoms;
    private String medicineTaken;
    private String medicineEffect;
    private String location;
    private String triggers;
    private String triggers2;
    private String menstruation;
    private int iconID;

    @Override
    public String toString() {
        return
                regguls(this.getDate(), "Päivämäärä") +
                        regguls(this.getStartTime(), "Kohtauksen alku") +
                        regguls(this.getEndTime(), "Kohtauksen loppu") +
                        regguls(this.getPainIntensity(), "Kivun taso") +
                        regguls(this.getType(), "Kohtauksen tyyppi") +
                        regguls(this.getPainLocation(), "Kivun sijainti") +
                        regguls(this.getType2(), "Kivun tyyppi") +
                        regguls(this.getOtherSymptoms(), "Muut oireet") +
                        regguls(this.getMedicineTaken(), "Lääkkeet") +
                        regguls(this.getMedicineEffect(), "Lääkkeen vaikutus") +
                        regguls(this.getLocation(), "Sijainti") +
                        regguls(this.getPresymptoms(), "Ennakko-oireet") +
                        regguls(this.getPresymptoms2(), "Muut ennakko-oireet") +
                        regguls(this.getTriggers(), "Laukaisevat tekijät") +
                        regguls(this.getTriggers2(), "Muut laukaisevat tekijät")
                ;
    }


    public String regguls(String regguls, String rogguls) {
        if (regguls == null || regguls.trim().equals("")) return "\n";
        return rogguls + ": " + regguls + "\n";
    }

    public String getID() {
        return this.id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return start_time;
    }

    public void setStartTime(String start_time) {
        this.start_time = start_time;
    }

    public String getEndTime() {
        return end_time;
    }

    public void setEndTime(String end_time) {
        this.end_time = end_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPainIntensity() {
        return painIntensity;
    }

    public void setPainIntensity(String painIntensity) {
        this.painIntensity = painIntensity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getPresymptoms() {
        return presymptoms;
    }

    public void setPresymptoms(String presymptoms) {
        this.presymptoms = presymptoms;
    }

    public String getPresymptoms2() {
        return presymptoms2;
    }

    public void setPresymptoms2(String presymptoms2) {
        this.presymptoms2 = presymptoms2;
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

    public String getMedicineEffect() {
        return medicineEffect;
    }

    public void setMedicineEffect(String medicineEffect) {
        this.medicineEffect = medicineEffect;
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

    public String getTriggers2() {
        return triggers2;
    }

    public void setTriggers2(String triggers2) {
        this.triggers2 = triggers2;
    }

    public String getMenstruation() {
        return menstruation;
    }

    public void setMenstruation(String menstruation) {
        this.menstruation = menstruation;
    }

    public int getIconID() {
        return iconID;
    }
}
