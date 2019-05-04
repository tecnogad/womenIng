package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Card;
import aiss.model.Song;
import aiss.model.repository.MapCardRepository;
import aiss.model.repository.CardRepository;


@Path("v1/cards")
public class CardResource {
	
	/* Singleton */
	private static CardResource _instance=null;
	CardRepository repository;
	
	private CardResource() {
		repository=MapCardRepository.getInstance();

	}
	
	public static CardResource getInstance()
	{
		if(_instance==null)
				_instance=new CardResource();
		return _instance;
	}
	

	@GET
	@Produces("application/json")
	public List<Card> getAll(@QueryParam("name") String name)
	{
		List<Card> all=new ArrayList<>(repository.getAllCards());	
		Collections.sort(all);
		List<Card> byName=new ArrayList<>();
		if(name!=null && name!="") {
			for(Card c: all) {
				if(c.getName().contains(name)) {
					byName.add(c);
				}
			}
			return byName;
		}
		else {
			return all;
		}
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Card get(@PathParam("id") String id)
	{
		Card list = repository.getCard(id);
		
		if (list == null) {
			throw new NotFoundException("The Card wit id="+ id +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addCard(@Context UriInfo uriInfo, Card Card) {
		if (Card.getName() == null || "".equals(Card.getName()))
			throw new BadRequestException("The name of the Card must not be null");
		
//		if (Card.getSongs()!=null)
//			throw new BadRequestException("The songs property is not editable.");

		repository.addCard(Card);

		// Builds the response. Returns the Card the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(Card.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(Card);			
		return resp.build();
	}

	
	@PUT
	@Consumes("application/json")
	public Response updateCard(Card card) {
		Card oldCard = repository.getCard(card.getId());
		if (oldCard == null) {
			throw new NotFoundException("The Card with id="+ card.getId() +" was not found");			
		}
		// Update name
		if (card.getName() == null || card.getName().equals("")) {
			return Response.status(418).build();
		}
		else {
			oldCard.setName(card.getName());
		}
//		if (Card.getSongs()!=null)
//			throw new BadRequestException("The songs property is not editable.");
		
		
		// Update description
		if (card.getIntroduction()!=null)
			oldCard.setIntroduction(card.getIntroduction());
		// Update image
		if (card.getImage()!=null)
			oldCard.setImage(card.getImage());
		// Update qr
		if (card.getQr()!=null)
			oldCard.setQr(card.getQr());
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeCard(@PathParam("id") String id) {
		Card toberemoved=repository.getCard(id);
		if (toberemoved == null)
			throw new NotFoundException("The Card with id="+ id +" was not found");
		else
			repository.deleteCard(id);
		
		return Response.noContent().build();
	}
	
	
//	@POST	
//	@Path("/{CardId}/{songId}")
//	@Consumes("text/plain")
//	@Produces("application/json")
//	public Response addSong(@Context UriInfo uriInfo,@PathParam("CardId") String CardId, @PathParam("songId") String songId)
//	{				
//		
//		Card Card = repository.getCard(CardId);
//		Song song = repository.getSong(songId);
//		
//		if (Card==null)
//			throw new NotFoundException("The Card with id=" + CardId + " was not found");
//		
//		if (song == null)
//			throw new NotFoundException("The song with id=" + songId + " was not found");
//		
//		if (Card.getSong(songId)!=null)
//			throw new BadRequestException("The song is already included in the Card.");
//			
//		repository.addSong(CardId, songId);		
//
//		// Builds the response
//		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
//		URI uri = ub.build(CardId);
//		ResponseBuilder resp = Response.created(uri);
//		resp.entity(Card);			
//		return resp.build();
//	}
//	
//	
//	@DELETE
//	@Path("/{CardId}/{songId}")
//	public Response removeSong(@PathParam("CardId") String CardId, @PathParam("songId") String songId) {
//		Card Card = repository.getCard(CardId);
//		Song song = repository.getSong(songId);
//		
//		if (Card==null)
//			throw new NotFoundException("The Card with id=" + CardId + " was not found");
//		
//		if (song == null)
//			throw new NotFoundException("The song with id=" + songId + " was not found");
//		
//		
//		repository.removeSong(CardId, songId);		
//		
//		return Response.noContent().build();
//	}
}
