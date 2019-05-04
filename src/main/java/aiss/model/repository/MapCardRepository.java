package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.ls.LSOutput;

import aiss.model.Card;
import aiss.model.Card; 
import aiss.model.Song;


public class MapCardRepository implements CardRepository{

	Map<String, Card> cardMap;
	Map<String, Song> songMap;
	private static MapCardRepository instance=null;
	private int index=0;			
	
	
	public static MapCardRepository getInstance() {
		
		if (instance==null) {
			instance = new MapCardRepository();
			instance.init();
		}
		
		return instance;
	}
	
	public void init() {
		
		cardMap = new HashMap<String,Card>();
		//songMap = new HashMap<String,Song>();
		
//		// Create songs
//		Song rollingInTheDeep=new Song();
//		rollingInTheDeep.setTitle("Rolling in the Deep");
//		rollingInTheDeep.setArtist("Adele");
//		rollingInTheDeep.setYear("2011");
//		rollingInTheDeep.setAlbum("21");
//		addSong(rollingInTheDeep);
//		
//		Song one=new Song();
//		one.setTitle("One");
//		one.setArtist("U2");
//		one.setYear("1992");
//		one.setAlbum("Achtung Baby");
//		addSong(one);
//		
//		Song losingMyReligion=new Song();
//		losingMyReligion.setTitle("Losing my Religion");
//		losingMyReligion.setArtist("REM");
//		losingMyReligion.setYear("1991");
//		losingMyReligion.setAlbum("Out of Time");
//		addSong(losingMyReligion);
//		
//		Song smellLikeTeenSpirit=new Song();
//		smellLikeTeenSpirit.setTitle("Smell Like Teen Spirit");
//		smellLikeTeenSpirit.setArtist("Nirvana");
//		smellLikeTeenSpirit.setAlbum("Nevermind");
//		smellLikeTeenSpirit.setYear("1991");
//		addSong(smellLikeTeenSpirit);
//		
//		Song gotye=new Song();
//		gotye.setTitle("Someone that I used to know");
//		gotye.setArtist("Gotye");
//		gotye.setYear("2011");
//		gotye.setAlbum("Making Mirrors");
//		addSong(gotye);
//		
//		// Create cards
//		Card jacard=new Card();
//		jacard.setName("AISSPlayList");
//		jacard.setDescription("AISS PlayList");
//		addCard(jacard);
//		
//		Card card = new Card();
//		card.setName("Favourites");
//		card.setDescription("A sample card");
//		addCard(card);
//		
//		// Add songs to cards
//		addSong(jacard.getId(), rollingInTheDeep.getId());
//		addSong(jacard.getId(), one.getId());
//		addSong(jacard.getId(), smellLikeTeenSpirit.getId());
//		addSong(jacard.getId(), losingMyReligion.getId());
//		
//		addSong(card.getId(), losingMyReligion.getId());
//		addSong(card.getId(), gotye.getId());
	}
	
	// Card related operations
	@Override
	public void addCard(Card p) {
		String id = "p" + index++;	
		p.setId(id);
		cardMap.put(id,p);
	}
	
	@Override
	public Collection<Card> getAllCards() {
			return cardMap.values();
	}

	@Override
	public Card getCard(String id) {
		return cardMap.get(id);
	}
	
	@Override
	public void updateCard(Card p) {
		cardMap.put(p.getId(),p);
	}

	@Override
	public void deleteCard(String id) {	
		cardMap.remove(id);
	}
	


	
}
