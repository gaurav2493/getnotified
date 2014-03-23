package com.jarvit.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class URLHtml {

	public ArrayList<Album> al;
	public Album result;

	public String getHTML(String urlToRead) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public URLHtml(String query) {
		String s = getHTML("http://s.staging.api.gaana.com/index.php?type=album&subtype=new_release");

		JSONParser parser = new JSONParser();
		al = new ArrayList<Album>();

		try {
			Object obj = parser.parse(s);
			JSONObject obj2 = (JSONObject) obj;
			JSONArray array = (JSONArray) obj2.get("album");
			JSONObject o3 = null;
			JSONObject o4 = null;
			String seokey, albumid, albumtitle, releasedate, artistname, artistid;
			JSONArray array2 = null;

			for (int i = 0; i < array.size(); i++) {
				o3 = (JSONObject) array.get(i);
				System.out.print(i + 1 + " :\n");

				seokey = o3.get("seokey") + "";
				albumid = o3.get("album_id") + "";
				albumtitle = o3.get("title") + "";
				releasedate = o3.get("release_date") + "";

				Album album = new Album();
				al.add(album);
				album.setArtists(new ArrayList<Artist>());
				album.setAlbumTitle(albumtitle);
				album.setSeoKey(seokey);
				album.setAlbumId(Integer.parseInt(albumid));
				album.setReleaseDate(releasedate);

				System.out.println("Seokey : " + seokey + "\nAlbumid: "
						+ albumid + "\nAlbumtitle: " + albumtitle
						+ "\nReleasedate: " + releasedate);
				array2 = (JSONArray) o3.get("artist");
				System.out.print("Artist : ");
				for (int j = 0; j < array2.size(); j++) {
					o4 = (JSONObject) array2.get(j);
					artistname = o4.get("name") + "";
					artistid = o4.get("artist_id") + "";
					System.out.print(artistname + ", ");

					Artist a = new Artist();
					a.artistId = artistid;
					a.artistName = artistname;
					album.getArtists().add(a);
				}
				System.out.println("\n");
			}
		}

		catch (ParseException pe) {
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
		System.out.println("\n\n\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n\n"
				+ al.size());
		Iterator<Album> i1 = al.iterator();
		int k = 1;
		while (i1.hasNext()) {
			Album alb = (Album) i1.next();
			Iterator<Artist> i2 = alb.getArtists().iterator();
			System.out.println("--Album " + k++ + "\n");
			while (i2.hasNext()) {
				Artist art = (Artist) i2.next();
				if (art.artistName.equalsIgnoreCase(query))
					/*System.out.println("\nAlbumTitle- " + alb.getAlbumTitle()
							+ "\nAlbumId- " + alb.getAlbumId() + "\nSeoKeys- "
							+ alb.getSeoKey() + "\n");*/
					result=alb;

			}

		}

	}
}