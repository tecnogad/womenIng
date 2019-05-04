package aiss.model.repository;

import java.util.Collection;

import aiss.model.Card;
import aiss.model.Song;

public interface CardRepository {
	
	
	// Songs
//	public void addSong(Song s);
//	public Collection<Song> getAllSongs();
//	public Song getSong(String songId);
//	public void updateSong(Song s);
//	public void deleteSong(String songId);
	
	// Cards
	public void addCard(Card p);
	public Collection<Card> getAllCards();
	public Card getCard(String CardId);
	public void updateCard(Card p);
	public void deleteCard(String CardId);
//	public Collection<Song> getAll(String CardId);
//	public void addSong(String CardId, String songId);
//	public void removeSong(String CardId, String songId); 

	
	
	
	

}
