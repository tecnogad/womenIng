package aiss.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.model.Playlist;
import aiss.model.Song;
import aiss.model.repository.MapPlaylistRepository;
import aiss.model.repository.PlaylistRepository;

import java.net.URI;
import java.util.Collection;
import java.util.List;


@Path("/songs")
public class SongResource {

	public static SongResource _instance=null;
	PlaylistRepository repository;
	
	private SongResource(){
		repository=MapPlaylistRepository.getInstance();
	}

	public static SongResource getInstance()
	{
		if(_instance==null)
			_instance=new SongResource();
		return _instance; 
	}
	
	//GET		/songs				Devuelve todas las canciones de la aplicación.
	@GET
	@Produces("application/json")
	public Collection<Song> getAll()
	{
		return repository.getAllSongs();
	}
	
	//GET		/songs/{songId}		Devuelve la canción con id=songId. Si la canción no existe devuelve un “404 Not Found”.
	//TODO como devolvemos aquí una tetera?418
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Song get(@PathParam("id") String songId)
	{
		Song list = repository.getSong(songId);
		
		if (list == null) {
			throw new NotFoundException("The song wit id="+ songId +" was not found");			
		}
		
		return list;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addSong(@Context UriInfo uriInfo, Song song) {
		if (song.getTitle() == null || "".equals(song.getTitle()))
			throw new BadRequestException("The title of the song must not be null");//lanza 400
		
		
		repository.addSong(song);
//si paso song s1 >> estamos reconstruyendo redirección ..../api/songs/s1
		// Builds the response. Returns the song the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");//hasta songs
		URI uri = ub.build(song.getId());//el id
		ResponseBuilder resp = Response.created(uri);//response.status(418)
		resp.entity(song);			
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateSong(Song song) {
		Song old = repository.getSong(song.getId());
		if (old == null) {
			throw new NotFoundException("The song with id="+ song.getId() +" was not found");			
		}
		
		// Update
//		private String id;
//		private String title;
//		private String artist;
//		private String album;
//		private String year;
		if (song.getTitle()!=null)
			old.setTitle(song.getTitle());

		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeSong(@PathParam("id") String songId) {
		Song toberemoved=repository.getSong(songId);
		if (toberemoved == null)
			throw new NotFoundException("The song with id="+ songId +" was not found");
		else
			repository.deleteSong(songId);
		
		return Response.noContent().build();
	}
	
}
