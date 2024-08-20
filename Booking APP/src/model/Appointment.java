package model;

public class Appointment {

    // Fields
    private String topLength;
    private String buzzCut;
    private String thinOut;
    private String fadeType;
    private String taperType;
    private String sideType;
    private String design;
    private String beard;
    private String lineUp;
    private String appointmentDate;
    private String time;
    private String cost;
    private String uniqueID;

    // Constructor
    public Appointment(String topLength, String buzzCut, String thinOut, String fadeType,
                       String taperType, String sideType, String design, String beard, String lineUp,
                       String appointmentDate, String time, String cost, String uniqueID) {
        this.topLength = topLength;
        this.buzzCut = buzzCut;
        this.thinOut = thinOut;
        this.fadeType = fadeType;
        this.taperType = taperType;
        this.sideType = sideType;
        this.design = design;
        this.beard = beard;
        this.lineUp = lineUp;
        this.appointmentDate = appointmentDate;
        this.time = time;
        this.cost = cost;
        this.uniqueID = uniqueID;
    }

    // Getters
    public String getTopLength() { return topLength; }

    public String getBuzzCut() { return buzzCut; }

    public String getThinOut() { return thinOut; }

    public String getFadeType() { return fadeType; }

    public String getTaperType() { return taperType; }

    public String getSideType() { return sideType; }

    public String getDesign() { return design; }

    public String getBeard() { return beard; }

    public String getLineUp() { return lineUp; }

    public String getAppointmentDate() { return appointmentDate; }

    public String getTime() { return time; }

    public String getCost() { return cost; }

    public String getUniqueID() { return uniqueID; }

    // Getting only relevant data for the appointment (all null is ignored)
    private String getAppointment() {
        StringBuilder appointments = new StringBuilder();

        if (this.topLength != null && !this.topLength.contains("null")) {
            appointments.append("Μέγεθος Κουρέματος: ").append(this.topLength).append(" cm, ");
        }
        if (this.buzzCut != null && !this.buzzCut.contains("null")) {
            appointments.append(this.buzzCut).append(", ");
        }
        if (this.thinOut != null && !this.thinOut.contains("null")) {
            appointments.append(this.thinOut).append(", ");
        }
        if (this.fadeType != null && !this.fadeType.contains("null")) {
            appointments.append(this.fadeType).append(", ");
        }
        if (this.taperType != null && !this.taperType.contains("null")) {
            appointments.append(this.taperType).append(", ");
        }
        if (this.sideType != null && !this.sideType.contains("null")) {
            appointments.append(this.sideType).append(", ");
        }
        if (this.design != null && !this.design.contains("null")) {
            appointments.append(this.design).append(", ");
        }
        if (this.beard != null && !this.beard.contains("null")) {
            appointments.append(this.beard).append(", ");
        }
        if (this.lineUp != null && !this.lineUp.contains("null")) {
            appointments.append(this.lineUp).append(", ");
        }
        if (this.appointmentDate != null && !this.appointmentDate.contains("null")) {
            appointments.append("Ημερομηνία: ").append(this.appointmentDate).append(", ");
        }
        if (this.time != null && !this.time.contains("null")){
            appointments.append("Χρόνος: ").append(this.time).append(", ");
        }
        if (this.cost != null && !this.cost.contains("null")) {
            appointments.append("Κόστος: ").append(this.cost).append(" ");
        }
        return appointments.toString();
    }

    // toString method
    @Override
    public String toString() {
        String appointments = getAppointment();
        return appointments;
    }
}
