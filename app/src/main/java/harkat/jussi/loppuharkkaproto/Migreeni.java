package harkat.jussi.loppuharkkaproto;


import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Jussi on 17.3.2018.
 */

public class Migreeni implements Serializable {

    public Migreeni(){

    }
    public Migreeni(String date, String duration, String start_time, String end_time, String painIntensity, String type, String presymptoms,
                    String painLocation, String otherSymptoms, String medicineTaken, String location,
                    String triggers, String menstruation, int iconID) {
        super();
        this.date = date;
        this.duration = duration;
        this.start_time = start_time;
        this.end_time = end_time;
        this.painIntensity = painIntensity;
        this.type = type;
        this.presymptoms = presymptoms;
        this.painLocation = painLocation;
        this.otherSymptoms = otherSymptoms;
        this.medicineTaken = medicineTaken;
        this.location = location;
        this.triggers = triggers;
        this.menstruation = menstruation;
        this.iconID = iconID;
    }

    private String date;
    private String duration;
    private String start_time;
    private String end_time;
    private String painIntensity;
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

    public int getIconID() {
        return iconID;
    }

}
