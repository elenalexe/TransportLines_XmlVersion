package Model_Package;

public class Lines  extends java.util.Observable {
    private int id;
    private String number;
    private String startStation;
    private String finalStation;
    private String stations;


    public Lines() {
    }


    public Lines(int id, String title, String startStation, String finalStation, String stations) {
        this.id = id;
        this.number = title;
        this.startStation = startStation;
        this.finalStation = finalStation;
        this.stations = stations;
    }

    public String getFinalStation() {
        return finalStation;
    }

    public void setFinalStation(String finalStation) {
        this.finalStation = finalStation;
        setChanged();
        notifyObservers();
    }

    public void updateLine(int id, String number, String startStation, String finalStation, String stations) {
        this.id = id;
        this.number = number;
        this.startStation = startStation;
        this.stations = finalStation;
        this.stations = stations;
        setChanged();
        notifyObservers();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
        setChanged();
        notifyObservers();
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
        setChanged();
        notifyObservers();
    }
    public String getStartStation() {
        return startStation;
    }
    public void setStartStation(String startStation) {
        this.startStation = startStation;
        setChanged();
        notifyObservers();
    }
    public String getStations() {
        return stations;
    }

    public void setStations(String stations) {
        this.stations = stations;
    }

}
