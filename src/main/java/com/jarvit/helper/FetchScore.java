package com.jarvit.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class FetchScore {

	public ArrayList<Match> al;
	public Match result;
	
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

	public FetchScore(String query) {
		String s = getHTML("http://synd.cricbuzz.com/j2me/1.0/livematches.xml");
		al = new ArrayList<Match>();
		query=query.toUpperCase();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(s));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("match");
			for (int temp = 0; temp < nodes.getLength(); temp++) {
				Node node = nodes.item(temp);
				Element student = (Element) node;
				Match m = new Match();
				System.out.print("Match desc : "
						+ student.getAttribute("mchDesc"));
				m.setDesc(student.getAttribute("mchDesc"));

				if (student.getAttribute("grnd") != "")
					System.out.print(" Match grnd : "
							+ student.getAttribute("grnd"));
				m.setGround(student.getAttribute("grnd"));
				NodeList node1 = student.getElementsByTagName("Tme");
				Node n2 = node1.item(0);
				Element s2 = (Element) n2;
				System.out.print(" date " + s2.getAttribute("Dt"));
				System.out.print(" time " + s2.getAttribute("stTme"));
				m.setDate(s2.getAttribute("Dt"));
				m.setTime(s2.getAttribute("stTme"));
				al.add(m);

				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<Match> i = al.iterator();
		while (i.hasNext()) {
			Match m=i.next();
			if(m.getDesc().contains(query))
				//System.out.println(m.toString());
				result=m;
		}

	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
	}
}