package songplaylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	private static ArrayList<Album> albums = new ArrayList<>(); //This is an ArrayList of Album class. Also, it throws error in psvm, hence made property of class. 

	public static void main(String[] args) {
				
		Album album = new Album("Album1","BTS");
		album.addSong("Euphoria", 4.5);
		album.addSong("Idol", 4.2);
		album.addSong("Not Today", 3.5);
		album.addSong("Fire", 5.5);
		albums.add(album); //add method of ArrayList is used to add songs through the album object of class Album
//		album.sizeofSongs(); //iterate through the first Songs ArrayList to view the songs added in the list. 
		
		album = new Album("Album2", "Linkin Park");
		album.addSong("Papercut", 3.55);
		album.addSong("Somewhere I Belong", 4.5);
		album.addSong("In the End", 4);
		album.addSong("In the End", 4);
		albums.add(album);
//		album.sizeofSongs(); //iterate through the second Songs ArrayList to view the songs added in the list.

		LinkedList<Song> playList_1 = new LinkedList<>(); //LinkedList of playlist contains songs from the two albums
		
		albums.get(0).addToPlayList("Euphoria", playList_1); //get(0) because 0th index contains album of BTS (1st object of Album class)
		albums.get(0).addToPlayList("Idol", playList_1);
		albums.get(0).addToPlaylist(3, playList_1); //3 is 3rd song (Not Today) in Album1 
		albums.get(0).addToPlaylist(5, playList_1); //trackNumber not present in Album1 - throws exception
		albums.get(1).addToPlayList("Papercut", playList_1);
		albums.get(1).addToPlayList("In the End", playList_1);
		albums.get(1).addToPlayList("Meri Gully Mein", playList_1); //trying to add a song to the playlist which does not exist in Album2
		
		play(playList_1);

	}
	
	private static void play(LinkedList<Song> playList) //play our playList
	{
		Scanner scan= new Scanner(System.in); 
		boolean quit = false; //to check whether the user has quit or not, i.e., 0 - to quit option. False means user has quit.
		boolean forward = true; 
		ListIterator<Song> listIterator = playList.listIterator(); //listIterator object will iterate through the playlist
		
		if(playList.size() == 0) //check if the playlist is empty
		{
			System.out.println("This playlist has no songs.");
		}
		else
		{
			System.out.println("\nNow playing : " + listIterator.next()); 
			printMenu(); 
		}
		
		while(!quit) //while user has not quit yet. (quit = true)
		{
			int action = scan.nextInt();
			//scan.nextLine();
			
			switch(action)
			{
			case 0: //0 - to quit
				System.out.println("Playlist complete");
				quit = true; //will quit the app
				break;
				
			case 1: //1 - to play next song
				if(!forward) //forward = false
				{
					//if the next song exist then shift listIterator to next (setting the cursor)
					if(listIterator.hasNext()) //check whether the next song exists or not
					{
						//if the next song exists then shift the listIterator
						listIterator.next(); //if we have a next song, then set listIterator to next song (listIterator points to the next song)
					}
					forward = true;
				}
				//check if the next song exists to be sure, then print current position of listIterator
				if(listIterator.hasNext())
				{
					System.out.println("Now playing : "+ listIterator.next().toString()); 
				}
				else
				{
					System.out.println("No songs available. We have reached the end of the list. "); //reached the end of the list
					forward = false; 
				}
				break;
				
			case 2: //2 - to play previous song
				if(forward) //forward = true
				{
					//if the previous song exist then shift listIterator to previous (setting the cursor)
					if(listIterator.hasPrevious()) //if we are on the first song then we don't have any previous song, so we have to check whether there is a previous song or not
					{
						listIterator.previous(); //if we have a previous song, then set listIterator to previous song (listIterator points to the previous song)
					}
					forward = false;
				}
				if(listIterator.hasPrevious())
				{
					System.out.println("Now playing : "+listIterator.previous().toString());
				}
				else 
				{
					System.out.println("We are at the first song. So no previous song.");
					forward = false;
				}
				break;
				
			case 3: //3 - to replay the current song
				if(forward) //forward = true
				{
					if(listIterator.hasPrevious()) //only checking for previous song and not setting the cursor to previous song.
					{
						//Following statement will return the current song, as it is pointing to the current song. We have not set it to point to the previous song as in case 2.
						System.out.println("Now replaying : "+listIterator.previous().toString()); 
						forward = false;
					}
					else
					{
						System.out.println("No previous song. We are at the start of the list. ");
					}
				}
				else
				{
					if(listIterator.hasNext())
					{
						System.out.println("Now replaying : "+ listIterator.next().toString());
						forward = true; 
					}
					else
					{
						System.out.println("No next song. We have reached to the end of the list.");
					}
				}
				break;
				
			case 4: //4 - list of all songs
				printList(playList);
				break;
				
			case 5: //5 - print all available options
				printMenu();
				break;
				
			case 6: //6 - delete current song
				//when the song exists in our playlist
				if(playList.size() > 0) //if size of playlist > 0, then only we can delete songs from the playlist
				{
					listIterator.remove(); //remove song where the current iterator is. 
					//if we have deleted the current song, then we have to play the next song.
					if(listIterator.hasNext()) //to play the next song, check if there exists a next song
					{
						System.out.println("Now playing : "+listIterator.next().toString()); 
						//Since we have removed the current song, by default it will either play the next song or previous song 
					}
					//if there is no next song (means we have removed the last song from the playlist), check for previous song.
					else
					{
						if(listIterator.hasPrevious()) //check if we have a previous song
							System.out.println("Now playing : "+ listIterator.previous().toString());
					}
				}
				break;				
			}
		}
	}
	
	//methods for user interaction
	private static void printMenu()
	{
		System.out.println("\n Menu - Available options \n press");
		System.out.println("0 - to quit \n"+ 
		"1 - to play next song \n"+
		"2 - to play previous song \n"+
		"3 - to replay the current song \n"+
		"4 - list of all songs \n"+ 
		"5 - print all available options \n"+
		"6 - delete current song \n"); 
	}
	
	private static void printList(LinkedList<Song> playList) 
	{
		Iterator<Song> iterator = playList.iterator(); //Iterator to iterate through songs in LinkedList of Song.
		System.out.println("--------------------------");
		
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
		System.out.println("--------------------------");
	}

}
