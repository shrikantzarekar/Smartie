package com.smartie.shrikant.smartie;



// categories are stories, jokes, math, game

import smarty.shrikant.com.smarty.Constants;

public class parsemyString {


public parsemyString() {
	
}

public String understand(String s){
	//String s = "I want to walk my dog";
	//String returnstring=Constants.Joke1;
	String returnstring="";
	 String[] arr = s.split(" ");    

	 for ( String ss : arr) {

	       if(ss.equals("joke")|| ss.equals("jokes") || ss.equals("joke's")){
	    	   returnstring = Constants.Joke2;
	       }else if(ss.equals("friend")|| ss.equals("Friend")){
	    	   returnstring = Constants.Friend2;
	       }
	       else if(ss.equals("Name")|| ss.equals("name")){
	    	   returnstring = Constants.Name;
	       }
	       else if(ss.equals("Colour")|| ss.equals("colour")){
	    	   returnstring = Constants.Color;
	       }
	       else if(ss.equals("smile")|| ss.equals("Smile")){
	    	   returnstring = Constants.Smile;
	       }
	       else if(ss.equals("hi")|| ss.equals("Hi")|| ss.equals("Hello")|| ss.equals("hello")){
	    	   returnstring = Constants.Introduction;
	       }
	       else if(ss.equals("error")|| ss.equals("Error")){
	    	   returnstring = Constants.Howareyou;
	       }
	       else if(ss.equals("")){
	    	   returnstring = Constants.GoodAnswer;
	       }
	       else if(ss.equals("day")|| ss.equals("Day")){
	    	   returnstring = Constants.Day;
	       }
	       else if(ss.equals("mother")|| ss.equals("Mother")){
	    	   returnstring = Constants.Mother;
	       }
	       else if(ss.equals("day")|| ss.equals("Day")){
	    	   returnstring = Constants.Day;
	       }
	       else if(ss.equals("school")|| ss.equals("School")){
	    	   returnstring = Constants.School;
	       }
	       else if(ss.equals("exam")|| ss.equals("Exam") || ss.equals("exams")|| ss.equals("Exams")|| ss.equals("exam's")|| ss.equals("Exam's")){
	    	   returnstring = Constants.Exam;
	       }
	       else if(ss.equals("best")|| ss.equals("Best")|| ss.equals("best of")|| ss.equals("Best of")){
	    	   returnstring = Constants.BestofLuck;
	       }
	    /*   else if(ss.equals("song")|| ss.equals("Song") || ss.equals("songs")|| ss.equals("Songs")|| ss.equals("song's")|| ss.equals("Song's")){
	    	   returnstring = Constants.Song;
	       } */
	  }
	return returnstring;
}

}
