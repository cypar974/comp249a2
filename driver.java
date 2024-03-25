import java.io.Serializable;

public class Movie implements Serializable {

	// Declaring all instances variable needed
	private int year;
	private String title;
	private int duration;
	private String genre;
	/*{ musical, comedy, animation, adventure, drama, crime, biography, horror, action, documentary, fantasy, mystery,
	sci-fi, family, western, romance, and thriller} */
	
	private String rating;
	private double score;
	private String director;
	private String actor1;
	private String actor2;
	private String actor3;
	
	
	//Default Constructor
	public Movie() {
		this (0, null, 0, null, null, 0.0, null, null, null, null);
	}
	
	//Custom constructor
	public Movie (int year, String title, int duration, String genre, String rating,
			double score, String director, String actor1, String actor2, String actor3) {
		
		this.year = year;
		this.title = title;
		this.duration = duration;
		this.genre= genre;
		this.rating = rating;
		this.score= score;
		this.director = director;
		this.actor1 =actor1;
		this.actor2= actor2;
		this.actor3= actor3;
		
	}
	
	//Copy constructor
	public Movie (Movie objMovie) {
		this.year = objMovie.year;
		this.title = objMovie.title;
		this.duration = objMovie.duration;
		this.genre= objMovie.genre;
		this.rating = objMovie.rating;
		this.score= objMovie.score;
		this.director = objMovie.director;
		this.actor1 = objMovie.actor1;
		this.actor2= objMovie.actor2;
		this.actor3= objMovie.actor3;
	}

	//Accessor and mutator methods for all instance variables
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenres(String genre) {
		this.genre = genre;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}


	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor1() {
		return actor1;
	}

	public void setActor1(String actor1) {
		this.actor1 = actor1;
	}

	public String getActor2() {
		return actor2;
	}

	public void setActor2(String actor2) {
		this.actor2 = actor2;
	}

	public String getActor3() {
		return actor3;
	}

	public void setActor3(String actor3) {
		this.actor3 = actor3;
	}
	
	//@Override
	public boolean equals (Movie objMovie) {
		
		if (objMovie== null) {
			return false;
		}
		
		else {
			return ((this.year == objMovie.year)&&
					 this.title.equals(objMovie.title) &&
					 this.duration == objMovie.duration &&
					 this.genre.equals(objMovie.genre) &&
					 this.rating.equals(objMovie.rating)&&
					 this.score == objMovie.score &&
					 this.director.equals(objMovie.director) &&
					 this.actor1.equals(objMovie.actor1) && 
					 this.actor2.equals(objMovie.actor2)&&
					 this.actor3.equals(objMovie.actor3))
					 ;	
		}
		
	}
	
	@Override
	public String toString() {
		
		return ("Year: " + year + ", " +
				"Title: " + title + ", " +
				"Duration: " + duration + ", " + 
				"Rating: " + rating + ", " +
				"Score: " + score + ", "+
				"Director: " + director + ", " +
				"Actor1: " + actor1 + ", " +
				"Actor2: " + actor2 + ", " +
				"Actor3: " + actor3 + ".");
		
	}	
	
}
