package Model_Package;

import Default_Package.XmlDatabase;

import java.util.ArrayList;

public class LinesMethod extends java.util.Observable {

    private static ArrayList<Lines> linesDatabase=new ArrayList<>();


    public LinesMethod() {
        if(linesDatabase.size()==0) {
            linesDatabase =  XmlDatabase.getLinesFromXml();
        }
    }

    public boolean insertInDatabase(Lines l){
        linesDatabase.add(l);
        return true;
    }

    public boolean deleteInDatabase(Lines l){
        int index=0;
        for (Lines lines : linesDatabase) {
            if(lines.getId()==(l.getId())){
                linesDatabase.remove(index);
                return true;
            }
            index++;
        }
        return false;
    }

    public boolean updateInDatabase(Lines l){
        for (Lines lines : linesDatabase) {
            if(lines.getId()==l.getId()){
                updateLine(l, lines);
                return true;
            }
        }
        return false;
    }

    private void updateLine(Lines l, Lines lines) {
        lines.setNumber(l.getNumber());
        lines.setFinalStation(l.getFinalStation());
        lines.setStartStation(l.getStartStation());
        lines.setStations(l.getStations());

    }


    public ArrayList<Lines> searchLine(String searchString, String searchTerm) {
        ArrayList<Lines> matches = new ArrayList<Lines>();
        System.out.println("in search line ");

        if (searchString == null || searchString.equals("") || searchString.equals(" ")) {
            System.out.println("in search line searchString is null/empty");
            return linesDatabase;
        }

        for (Lines lines : linesDatabase) {
            switch(searchTerm){
                case "number":
                    if (lines.getNumber().equals(searchString) || lines.getNumber().contains(searchString)) {
                        matches.add(lines);
                        System.out.println("found line by number");
                    }
                    break;
                case "finalStation":
                    if(lines.getFinalStation().equals(searchString) || lines.getFinalStation().contains(searchString)){
                        matches.add(lines);
                        System.out.println("found line by finalStation");
                    }
                    break;
                case "startStation":
                    if (lines.getStartStation().equals(searchString) || lines.getStartStation().contains(searchString)) {
                        matches.add(lines);
                        System.out.println("found line by startStation");
                    }
                    break;
            }
        }
        return matches;
    }

    public ArrayList <Lines> getLinesDatabase() {
        return linesDatabase;
    }

}
