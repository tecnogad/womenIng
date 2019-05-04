package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.ls.LSOutput;

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
		// Create cards
		Card agcard=new Card();
		agcard.setName("Adele Goldberg");
		agcard.setIntroduction("Empresaria e informática que participó en el desarrollo del lenguaje de programación Smaltalk-80,"
				+ "el cual se utilizó como prototipo de la interfaz WIMP(ventanas, iconos, menús y apuntadores),"
				+ "los inicios de las interfaces gráficas modernas.");
		agcard.setImage("https://i.imgur.com/AzftjHt");
		agcard.setQr("https://i.imgur.com/Frb7mtP");
		addCard(agcard);
		
		Card lsCard = new Card();
		lsCard.setName("Lucy Sanders");
		lsCard.setIntroduction("Es la CEO actual del National Center for Women & Information Technology (NCWIT). \r\n" + 
				"Lucy recibió el Bob Newman Lifetime Achievement Award por su tecnología innovadora");
		addCard(lsCard);
		
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
		String id = "c" + index++;	
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
