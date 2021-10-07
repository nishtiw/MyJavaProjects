package songplaylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Album {
	private String name;
	private String artist;
	private ArrayList<Song> songs; 
	
	public Album(String name, String artist) {
		//super();
		this.name = name;
		this.artist = artist;
		this.songs = new ArrayList<Song>();
	}
	
	public Album() {}
	
	public Song findSong(String title) //Check if the entered song exists in the ArrayList<Song>. 
	{
		for(Song checkedSong : songs) //forEach loop
		{
			if(checkedSong.getTitle().equals(title)) //if the song already exists then return song.
				return checkedSong;
		}
		return null; 
	}
	
	public void addSong(String title, double duration) {
		//if the song does not exist in the ArrayList, then add the song
		if(findSong(title) == null) 
		{
			songs.add(new Song(title, duration));
			System.out.println(title+" - successfully added to the album - " + name);
		}
		//if the song exists in the ArrayList then we cannot add the song
		else
		{
			System.out.println("Song with name '"+ title + "' already exists in the album - " + name);
		}
	}
	
	//add song to the playlist, such a song must already exist in the ArrayList<Album>. 
	public boolean addToPlaylist(int trackNumber, LinkedList<Song> PlayList) 
	{
		try
		{
			int index = trackNumber - 1; //trackNumber starts from 1 but index starts from 0, so check if the trackNumber is valid or not
			if(index > 0 && index <= this.songs.size()) //if index lies between 0 and the size of the ArrayList<Song>, then only we can add the song to the playlist
			{
				PlayList.add(this.songs.get(index)); //add the song which is already present in the ArrayList<Song> to the LinkedList/PlayList.
				return true;
			}
		}catch(Exception e) //catches IndexOutOfBoundsException
		{
			System.out.println("This album does not have the song with track number " + trackNumber); //song does not exist in the album
		}
		return false;
	}
	
	public boolean addToPlayList(String title, LinkedList<Song> PlayList)
	{
			for(Song checkedSong : this.songs)
			{
				if(checkedSong.getTitle().equals(title)) //if the song exists in the ArrayList<Song> then add it to the PlayList
				{
					PlayList.add(checkedSong);
					return true; 
				}
			}
			System.out.println(title + " - there is no such song in the album"); //song does not exist in the album
			return false;
	}
	
	/*public void sizeofSongs()
	{
		System.out.println("Size of Songs arraylist in Album class: "+ songs.size());
		Iterator<Song> iterator = songs.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}*/
	
}
