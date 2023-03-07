package com.sg.dvdlibrary.dto;

public class DVD {

    //Our 6 variables for a DVD object
    private String Title;
    private String Release;
    private String Rating;
    private String Director;
    private String Studio;
    private String Notes;

    //following that, our methods to set each variable:
    //Our first is our key, which is the title
    public DVD(String Title){
        this.Title = Title;
    }
    public String getTitle(){return Title;}

    public String getRelease(){
        return Release;
    }
    public void setRelease(String Release){this.Release = Release;}

    public String getRating(){
        return Rating;
    }
    public void setRating(String Rating){this.Rating = Rating;}

    public String getDirector(){
        return Director;
    }
    public void setDirector(String Director){this.Director = Director;}

    public String getStudio(){
        return Studio;
    }
    public void setStudio(String Studio){this.Studio = Studio;}

    public String getNotes(){
        return Notes;
    }
    public void setNotes(String Notes){this.Notes = Notes;}

}
