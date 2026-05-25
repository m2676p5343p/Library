package org.jboss.as.quickstarts.library;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.List;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Reader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class Library implements Serializable {
	/**
	 * Fields store the data that are associated with the newest item being added
	 */
	private String newTitle;
	private String newAuthor;
	private String newReleaseYear;
	private String newGenre;
	private float newLength;
	
	/**
	 * The path for the csv file containing the library items data
	 */
	private final Path CSV_PATH = FileSystems.getDefault()
								  .getPath("library", "src", "main", "resources", "items.csv");
	
	
	/**
	 * Stores LibraryItem objects (Books / Dvds)
	 */
	private LibraryList<LibraryItem> items;
	
	/**
	 * Instantiates the items ArrayList. Reads the CSV file and instantiates Book and Dvd objects
	 * from the CSV to store in the ArrayList.
	 */
	@PostConstruct
	public void init() {
		items = new LibraryList<LibraryItem>();
		// lines stores the input from the csv file
		List<String[]> lines;

		try (InputStream is = Library.class.getResourceAsStream("/items.csv")) {
			try (Reader reader = new BufferedReader(new InputStreamReader(is))) {
				CSVParser parser = new CSVParserBuilder().withSeparator(',')
									.withIgnoreQuotations(true).build();
				try (CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1)
											.withCSVParser(parser).build()) {
					// reads all lines from the csv file, excluding the header line
					lines = csvReader.readAll();
					// iterates through the lines from the csv file, instantiating Book and Dvd objects
					// for each one and adding them to the List
					for (String[] line : lines) {
						if (line[0].equals("Book")) {
							items.add(new Book(
								line[1], //title
								line[3], //author
								line[2], //releaseYear
								line[4], //genre
								line[6].equals("y") //available
							));
						} else if (line[0].equals("DVD")) {
							items.add(new Dvd(
								line[1], //title
								line[2], //releaseYear
								Float.parseFloat(line[5]), //length
								line[6].equals("y") //available
							));
						}
					}
				} catch (CsvException e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
				}
			}
		} catch (FileNotFoundException e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
		} catch (IOException e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
		} catch (NumberFormatException e ){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
		}
		/**
		 * catch clauses catch different types of exceptions and sends a message to the
		 * <h:messages/> tag in the home page
		 */
	}
	
	/**
	 * Getters and setters
	 */
	public String getNewTitle() {
		return this.newTitle;
	}
	
	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}
	
	public String getNewAuthor() {
		return this.newAuthor;
	}
	
	public void setNewAuthor(String newAuthor) {
		this.newAuthor = newAuthor;
	}
	
	public String getNewReleaseYear() {
		return this.newReleaseYear;
	}
	
	public void setNewReleaseYear(String newPublicationYear) {
		this.newReleaseYear = newPublicationYear;
	}
	
	public String getNewGenre() {
		return this.newGenre;
	}
	
	public void setNewGenre(String newGenre) {
		this.newGenre = newGenre;
	}
	
	public float getNewLength() {
		return this.newLength;
	}
	
	public void setNewLength(float newLength) {
		this.newLength = newLength;
	}
	
	public List<LibraryItem> getItems() {
		return this.items;
	}
	
	/**
	 * Adds a new Book object to the items List
	 */
	public void addBook() {
		if (items.add(new Book(newTitle, newAuthor, newReleaseYear, newGenre, true))) {
			appendBookToCsv(newTitle, newAuthor, newReleaseYear, newGenre);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Book already exists!"));
		}
	}
	
	/**
	 * Appends a line to the CSV to represent a new book
	 */
	public void appendBookToCsv(String title, String author, String releaseYear, String genre) {
		String[] line = {"Book", title, releaseYear, author, genre, ""};
		try(CSVWriter writer = new CSVWriter(new FileWriter(CSV_PATH.toString(), true))) {
			writer.writeNext(line);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Done"));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
		}
	}
	
	/**
	 * Adds a new Dvd object to the items List
	 */
	public void addDvd() {
		if (items.add(new Dvd(newTitle, newReleaseYear, newLength, true))) {
			appendDvdToCsv(newTitle, newReleaseYear, newLength);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DVD already exists!"));
		}
	}
	
	/**
	 * Appends a line to the CSV to represent a new Dvd
	 */
	public void appendDvdToCsv(String title, String releaseYear, float length) {
		String[] line = {"DVD", title, releaseYear, "", "", length + ""};
		try(CSVWriter writer = new CSVWriter(new FileWriter(CSV_PATH.toString(), true))) {
			writer.writeNext(line);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Done"));
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
		}
	}
}