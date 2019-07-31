package com.huynguyen.musiclibrary.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huynguyen.musiclibrary.comparator.SongComparator;
import com.huynguyen.musiclibrary.dao.Album;
import com.huynguyen.musiclibrary.dao.Artist;
import com.huynguyen.musiclibrary.dao.Country;
import com.huynguyen.musiclibrary.dao.Genre;
import com.huynguyen.musiclibrary.dao.Song;
import com.huynguyen.musiclibrary.dao.Type;
import com.huynguyen.musiclibrary.dao.User;
import com.huynguyen.musiclibrary.dao.Userandlike;
import com.huynguyen.musiclibrary.mail.EncryptionAndDecryption;
import com.huynguyen.musiclibrary.mail.MailService;
import com.huynguyen.musiclibrary.service.DataTransformation;
import com.huynguyen.musiclibrary.service.ServerValidation;



/**
 * Handles requests for the application home page.
 */


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private ServerValidation svValidation = new ServerValidation();
	private  DataTransformation datatransformation = new DataTransformation();
	
	private EncryptionAndDecryption ed = new EncryptionAndDecryption();
	
	/* upload properties*/

	private final String UPLOAD_DIRECTORY = "upload";
	private final int THRESHOLD_SIZE = 1024 * 1024 * 3; //3MB
	private final int MAX_FILE_SIZE = 1024 * 1024 * 40; //40MB
	private final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; //50MB
	
	private Pattern pattern;
	private Matcher matcher;
	private final String MUSIC_PATTERN = "([^\\s]+(\\.(?i)(mp3))$)";
	
	
	@Autowired private ApplicationContext appContext;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	 public HomeController(){
		 pattern = Pattern.compile(MUSIC_PATTERN);
		
	 } 
	 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) throws MessagingException, EmailException {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model){
		return "login";
	}
	
	
	
	@RequestMapping(value="/loginhandler", method = RequestMethod.POST)
	public String loginHandler(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ratts){
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println(email);
		System.out.println(password);
		
		if(!svValidation.isThisEmailAndPasswordRegistered(email, password)){
			ratts.addFlashAttribute("error", "notregistered");
			return "redirect:/login";
		}
		
		User user = datatransformation.getUserByEmail(email);
		
		if(!svValidation.isThisAccountActivated(user)){
			ratts.addFlashAttribute("error", "notactivated");
			return "redirect:/login";
		}
		
		Cookie loginName = new Cookie("name", user.getFirstname());
		Cookie loginUserId = new Cookie("id", Integer.toString(user.getIduser()));
		loginName.setMaxAge(30 * 60);
	
		loginUserId.setMaxAge(30 * 60);
		response.addCookie(loginName);	
		
		response.addCookie(loginUserId);
				
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ratts){
		
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie: cookies){
			if(cookie.getName().equals("name")){
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		
			if(cookie.getName().equals("id")){
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
		}
		
		return "redirect:/";
		
		
	}
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Locale locale, Model model){
		
		return "register";
	}
	
	
	
	@RequestMapping(value = "/registervalidation", method = RequestMethod.POST)
	public String registerController(HttpServletRequest request, RedirectAttributes ratts) throws EmailException{
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(svValidation.isThisEmailRegistered(email)){
			ratts.addFlashAttribute("error", "The email is already registered");
			return "redirect:/register";
		}
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		
		String gender = request.getParameter("gender");
		
		int day = Integer.parseInt(request.getParameter("day"));
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		
		Calendar cal=Calendar.getInstance();
		cal.set(year, month, day);
		java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
		
		User user = new User();
		Type type = new Type();
		type.setIdtype(1);
		type.setType("user");
		
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setGender(gender);
		user.setPassword(password);
		user.setDob(date);
		user.setType(type);
		user.setEmail(email);
		user.setActive(0);
	
		if(!datatransformation.insertInforToDatabase(user)){
			ratts.addFlashAttribute("Error", "Sorry! We can not process to create a new account. Please try agian!");
			return "redirect:/register";
		
		}
		
		String encode = ed.encrypt(email);
		
		System.out.println(encode);
		
		//send email to active account
		String message = "Please click on this link to activate your account: "
				+ "http://localhost:8080/musiclibrary/confirmemail?email=" + encode;
		String subject = "Musiclibrary: Email confirmation";
		String from = "huymusiclibrary@gmail.com";
		String to = email;
		
		MailService mailService = new MailService();
		
		mailService.sendMail(message, subject, from, to);
		
		ratts.addFlashAttribute("sentemailnotice", "An email has sent to you");
		
		return "redirect:/";
			
	}
	
	@RequestMapping(value="/confirmemail", method = RequestMethod.GET)
	public String confirmEmail(HttpServletRequest request, Locale locale, Model model, RedirectAttributes ratts){
		
		String emailEncrypted = request.getParameter("email");
		
		String eEncrypted = emailEncrypted.replaceAll(" ", "+");
		
		String email = ed.decrypt("ThisIsASecretKey", eEncrypted);
		
		if(!datatransformation.updateActiveStatus(email)){
			ratts.addFlashAttribute("error", "Can not update active status");
			return "redirect:/register";
		}
		
		ratts.addFlashAttribute("sucess", "Your account has been activated");
		return "confirmemail";
	}
	
	@RequestMapping(value="/success", method = RequestMethod.GET)
	public String success(Locale locale, Model model){
		return "success";
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changePassword(HttpServletRequest request, Model model){
		
		return "changepassword";
	}
	
	

	@RequestMapping(value = "/changepasswordprocess", method = RequestMethod.POST)
	public String changePasswordProcess(HttpServletRequest request, RedirectAttributes ratts){
		
		String email = request.getParameter("email");
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		
		if(!svValidation.isThisEmailRegistered(email)){
			ratts.addFlashAttribute("error", "The email you entered is not registered");
			return "redirect:/changepassword";
		}
		
		if(!svValidation.isThisEmailAndPasswordRegistered(email, oldPassword)){
			ratts.addFlashAttribute("error", "The Old password is not correct");
			return "redirect:/changepassword";
		}
		
		
		if(oldPassword.equals(newPassword)){
			ratts.addFlashAttribute("error", "The new password is same as the old password");
			return "redirect:/changepassword";
		}
		
		if(!datatransformation.updatePassword(email, newPassword)){
			ratts.addFlashAttribute("error", "Error in updating new password");
			return "redirect:/changepassword";
		}
		
		return "redirect:/success";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String test(Locale locale, Model model){
		
		return "upload";
	}
	
	
	
	
	@RequestMapping(value="/uploadhandler", method= RequestMethod.POST)
	
	public String uploadHandler(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ratts)
			throws ServletException, IOException{
		
		//get information from upload page
		String title = " ";
		String artist = " ";
		String album = " ";
		String country = " ";
		String duration = " ";
		String date = " ";
		String genre = " ";
		
		String fileNameNoWhiteSpace = "";
		
		//checks if request actually contains upload file
		if(!ServletFileUpload.isMultipartContent(request)){
			ratts.addFlashAttribute("message", "Request does not contain upload data");
			return "redirect:/upload";
		}
		
		//configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//constructs the directory path to store upload file
		
		ServletContext context = request.getSession().getServletContext();
		String uploadPath = context.getRealPath("") + File.separator + UPLOAD_DIRECTORY;;
	    
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()){
			uploadDir.mkdir();
		}
		try{
			//parses the request's content to extract file data
			List formItems = upload.parseRequest(request);
			Iterator iter = formItems.iterator();
			//iterates over form's fields
			while(iter.hasNext()){
				FileItem item = (FileItem) iter.next();
				//processes only fields that are not form fields
				if(!item.isFormField()){
					String fileName = new File(item.getName()).getName();
					
					String fileName1 = fileName.replaceAll(" - ", "_");
					
					fileNameNoWhiteSpace = fileName1.replaceAll("\\s", "_");
					
					System.out.println(fileNameNoWhiteSpace);
					
					matcher = pattern.matcher(fileNameNoWhiteSpace);
					
						
					if(matcher.matches()){
						
						String filePath = uploadPath + File.separator + fileNameNoWhiteSpace;
						String command = "mv " + filePath + " /home/huynguyen/git/Musiclibrary/MusicLibrary/src/main/webapp/resources/songs";
						
						File storeFile = new File(filePath);
						item.write(storeFile);
						
						try
						{
						    Process process = Runtime.getRuntime().exec(command);
						    
						} catch (IOException e)
						{
						    e.printStackTrace();
							ratts.addFlashAttribute("message", "There was an error in coppying the mp3 file");
						    return "redirect:/upload";
						}
						
						System.out.println(filePath);
					}
					else{
						ratts.addFlashAttribute("message", "This is not an valid file");
						return "redirect:/upload";
					}	
					
				}
				else{
					if(item.getFieldName().equals("title"))
						title = item.getString();
					if(item.getFieldName().equals("artist"))
						artist = item.getString();
					if(item.getFieldName().equals("album"))
						album = item.getString();
					if(item.getFieldName().equals("country")){
						country = item.getString();
					}
					if(item.getFieldName().equals("duration"))
						duration = item.getString();
					if(item.getFieldName().equals("date"))
						date = item.getString();
					if(item.getFieldName().equals("genre"))
						genre = item.getString();
						
				}
			}
			
			if(svValidation.isThisSongExisted(title)){
				ratts.addFlashAttribute("message", "This song is already exited, Please upload other song!");
				return "redirect:/upload";
			}
			
				
			if(!datatransformation.uploadSongAndAlbum(title, album, country, date, duration, genre, fileNameNoWhiteSpace)){
				ratts.addFlashAttribute("message", "Upload Song and album. Can not upload this song and album, try again!");
				return "redirect:/upload";
			}
			
			if(!datatransformation.uploadSongAndArtist(title, artist, country)){
				ratts.addFlashAttribute("message", "Upload Song and Artist. Can not upload this song and artist, try again!");
				return "redirect:/upload";
			}
			
			if(!datatransformation.uploadAlbumAndArtist(album, artist)){
				ratts.addFlashAttribute("message", "Upload album and Artist. Can not upload this album and artist, try again!");
				return "redirect:/upload";
			}

			System.out.println(title + " " + artist + " " + album + " " + country + " " + duration + " " + date + " " + genre);	

			ratts.addFlashAttribute("message", "Upload have been done successfully!");
		}catch(Exception ex){
			ratts.addFlashAttribute("message", "There was an error");
			return "redirect:/upload";
		}
		
		return "redirect:/success";
	}
	
	//albums 
	@RequestMapping(value = "/albums/{country}", method = RequestMethod.GET)
	public String albums(@PathVariable("country") String country, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes ratts){
		

		HttpSession session = request.getSession();
		Country thisCountry = new Country();
		List<Album> albums = new ArrayList<Album>();
		
		int page = 1;
		int recordsPerPage = 4;
		
		int numberOfRecords = 0;
		int numberOfPage = 0;
		
		if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
		
		
		if(country.equals("album_nhacviet")){
			thisCountry.setIdcountry(1);
			thisCountry.setName("Viet Nam");
			session.setAttribute("country", "Vietnamese");
			albums = datatransformation.getAlbumsByCountry(thisCountry);
		}
		if(country.equals("album_usa")){
			thisCountry.setIdcountry(2);
			thisCountry.setName("USA");
			session.setAttribute("country", "American");
			albums = datatransformation.getAlbumsByCountry(thisCountry);
		}
		if(country.equals("album_korea")){
			thisCountry.setIdcountry(3);
			thisCountry.setName("Korea");
			session.setAttribute("country", "Korea");
			albums = datatransformation.getAlbumsByCountry(thisCountry);
		}
		if(country.equals("album_china")){
			thisCountry.setIdcountry(4);
			thisCountry.setName("China");
			session.setAttribute("country", "China");
			albums = datatransformation.getAlbumsByCountry(thisCountry);
		}
		
		if(country.equals("all")){
			albums = datatransformation.getAlbums();
			request.setAttribute("country", "All");
		}
		
		numberOfRecords = albums.size();
		numberOfPage = numberOfRecords / recordsPerPage + 1;
		
		session.setAttribute("albums", albums);
        session.setAttribute("numberOfPage", numberOfPage);
        session.setAttribute("currentPage", page);
		
		
		
		//session.setAttribute("albums", albums);
	
		return "redirect:/albumslist";	
		
	}
	
	@RequestMapping(value = "/albumslist", method = RequestMethod.GET)
	public String representAlbums(Locale locale, Model model){
		
		return "albumslist";
	}
	
	
	@RequestMapping(value = "/albumsongslist", method = RequestMethod.GET)
	public String representSongsOfAlbum(Locale locale, Model model){
		
		return "albumsongslist";
	}
	
	
	@RequestMapping(value = "/albums/album/{album}", method = RequestMethod.GET)
	public String albumSongs(@PathVariable("album") String album, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes ratts){
		
		String albumName = album.replaceAll("_", " ");
		
		List<Song> songs = datatransformation.getSongsByAlbum(albumName);
	
		
		HttpSession session = request.getSession();
		session.setAttribute("songs", songs);
		session.setAttribute("albumName", albumName);
		
		
		return "redirect:/albumsongslist";
	}
	
	
	//artist
	@RequestMapping(value = "/artistslist", method = RequestMethod.GET)
	public String representArtists(Locale locale, Model model){
		
		return "artistslist";
	}
	
	@RequestMapping(value = "/artists/{country}", method = RequestMethod.GET)
	public String artists(@PathVariable("country") String country, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes ratts){
		
		HttpSession session = request.getSession();
		
		Country thisCountry = new Country();
		
		if(country.equals("artist_viet")){
			thisCountry.setIdcountry(1);
			thisCountry.setName("VietName");
			session.setAttribute("country", "Vietnamese");
		
		}
		if(country.equals("artist_usa")){
			thisCountry.setIdcountry(2);
			thisCountry.setName("USA");
			session.setAttribute("country", "American");
		
		}
		if(country.equals("artist_korea")){
			thisCountry.setIdcountry(3);
			thisCountry.setName("Korea");
			session.setAttribute("country", "Korean");
		
		}
		if(country.equals("artist_china")){
			thisCountry.setIdcountry(4);
			thisCountry.setName("China");
			session.setAttribute("country", "China");
		
		}
		
		if(country.equals("all")){
			List<Artist> artists = new ArrayList<Artist>();
			artists = datatransformation.getArtists();
			session.setAttribute("country", "All");
			session.setAttribute("artists", artists);
			return "redirect:/artistslist";
			
		}
		
		List<Artist> artists = datatransformation.getArtistsByCountry(thisCountry);
		session.setAttribute("artists", artists);
		
		return "redirect:/artistslist";
	}
	
	@RequestMapping(value = "/artists/artist/{artist}", method = RequestMethod.GET)
	public String artistSongs(@PathVariable("artist") String artist, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes ratts){
		
		String artistName = artist.replaceAll("_", " ");
	
		List<Song> songs = datatransformation.getSongsByArtist(artistName);
		
		HttpSession session = request.getSession();
		session.setAttribute("songs", songs);
		session.setAttribute("artistName", artistName);
		
		
		return "redirect:/artistsongslist";
	}
	
	@RequestMapping(value = "/artistsongslist", method = RequestMethod.GET)
	public String representSongOfArtist(Locale locale, Model model){
		
		return "artistsongslist";
	}
	
	
	@RequestMapping(value = "/genreslist", method = RequestMethod.GET)
	public String representGenre(Locale locale, Model model){
		
		return "genreslist";
	}
	
	@RequestMapping(value = "/genres/{genre}", method = RequestMethod.GET)
	public String genres(@PathVariable("genre") String genre, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes ratts){
		
		HttpSession session = request.getSession();
		
		String genreName = " ";
		
		if(genre.equals("rock")){
			genreName = "Rock";
			session.setAttribute("type", "Rock");
		}
		if(genre.equals("country")){
			genreName = "Country";
			session.setAttribute("type", "Country");
		}
	
		if(genre.equals("blue")){
			genreName = "Blue";
			session.setAttribute("type", "Blue");
		}
	
		if(genre.equals("pop")){
			genreName = "Pop";
			session.setAttribute("type", "Pop");
		}
	
		if(genre.equals("r&b")){
			genreName = "R&B";
			session.setAttribute("type", "R&B");
		}
		
		if(genre.equals("other")){
			genreName = "Other";
			session.setAttribute("type", "Other");
		}
		
		if(genre.equals("reggaefusion")){
			genreName = "Reggae Fusion";
			session.setAttribute("type", "Reggae Fusion");
		}
	
	
		
		
		if(genre.equals("all")){
			
			List<Genre> genres = new ArrayList<Genre>();
			List<Song> songs = datatransformation.getSongs();
			
			genres = datatransformation.getGenres();
			session.setAttribute("type", "All");
			session.setAttribute("genres", genres);
			session.setAttribute("songs", songs);
			
			
			return "redirect:/genreslist";
			
		}
		
		Genre thisGenre = datatransformation.getGenreByName(genreName);
	
		session.setAttribute("genre", thisGenre);
		
		return "redirect:/genreslist";
	}
	
	
	@RequestMapping(value = "/tops/{country}", method = RequestMethod.GET)
	public String tops(@PathVariable("country") String country, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes ratts){
		
		HttpSession session = request.getSession();
		Country thisCountry = new Country();
		List<Song> songs = new ArrayList<Song>();
		
		if(country.equals("top_viet")){
			thisCountry.setIdcountry(1);
			thisCountry.setName("Viet Nam");
			session.setAttribute("country", "Viet Nam");
			session.setAttribute("top_country", "top_viet");
		}
		
		if(country.equals("top_usa")){
			thisCountry.setIdcountry(2);
			thisCountry.setName("USA");
			session.setAttribute("country", "USA");
			session.setAttribute("top_country", "top_usa");
	
		}
		
		if(country.equals("top_korea")){
			thisCountry.setIdcountry(3);
			thisCountry.setName("Korea");
			session.setAttribute("country", "Korea");
			session.setAttribute("top_country", "top_usa");
	
		}
		if(country.equals("top_china")){
			thisCountry.setIdcountry(4);
			thisCountry.setName("China");
			session.setAttribute("country", "China");
			session.setAttribute("top_country", "top_usa");
	
		}
		
		if(country.equals("all")){
			
			songs = datatransformation.getSongs();
			
			session.setAttribute("songs", songs);
			session.setAttribute("country", "All");
			session.setAttribute("top_country", "all");
			
			
			return "redirect:/top";
			
		}
		
		songs = datatransformation.getSongsByCountry(thisCountry);
		
		SongComparator comparator = new SongComparator();
		Collections.sort(songs, comparator);

		
		List<Song> topTens = new ArrayList<Song>();
		
		if(songs.size() > 10)
			topTens = songs.subList(0, 10);
		else
			topTens = songs;
		
		session.setAttribute("songs", topTens);
		
		return "redirect:/top";
		
		
	}
	
	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public String representtop(Locale locale, Model model){
		
		return "top";
	}
	
	@RequestMapping(value = "/playsong", method = RequestMethod.GET)
	public String playSong(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model,
			RedirectAttributes ratts){
		
		Cookie[] cookies = request.getCookies();
		cookies = request.getCookies();
		
		String like = request.getParameter("like");
			
		HttpSession session = request.getSession();	
	
		
		String idStr = "";
		int id = 0;
		
		
		for(Cookie cookie: cookies){
			if(cookie.getName().equals("id")){
				idStr = cookie.getValue();
				id = Integer.parseInt(idStr);
			}
			
		}
		
		
	
		Song song = (Song)session.getAttribute("songToPlay");
		String songName = song.getName();
		
	
		if(like != null){
			Userandlike userAndLike = new Userandlike();
			userAndLike.setSongIdsong(song.getIdsong());
			userAndLike.setUserIduser(id);
					
			if(!datatransformation.insertInforToDatabase(userAndLike)){
					System.out.println("Can not insert userAndLike object");
			}					
				
			if(!datatransformation.updateLikeForSong(songName, Integer.parseInt(like))){
				System.out.println("Cat not update like for this song");
			}
				
		}
		
	
		
		return "playsong";
	}
	
	@RequestMapping(value = "/songs/song/{song}", method = RequestMethod.GET)
	public String playSongController(@PathVariable("song") String song, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes ratts){
		
		String songName = song.replaceAll("_", " ");
		
		HttpSession session = request.getSession();
		
		List<Song> songs = (List<Song>)session.getAttribute("songs");
		
		for(Song s: songs){
			if(s.getName().equals(songName)){
				session.setAttribute("songToPlay", s);
				break;
			}
		}
		
		return "redirect:/playsong";
	}
	
	@RequestMapping(value="/searchhandler", method = RequestMethod.POST)
	public String searchHandler(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ratts){
		
		String keySearch = request.getParameter("search");
		HttpSession session = request.getSession();
		
		System.out.println(keySearch);
		
		keySearch = "%" + keySearch + "%";
		
		System.out.println(keySearch);
		
		List<Song> songs = datatransformation.searchSongs(keySearch);
		List<Album> albums = datatransformation.searchAlbums(keySearch);
		List<Artist> artists = datatransformation.searchArtists(keySearch);

		session.setAttribute("songs", songs);
		session.setAttribute("albums", albums);
		session.setAttribute("artists", artists);
		
		
		return "redirect:/search";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model){
		return "result";
	}
		

}
