package ScoreBoard;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class ScoreLoader extends org.xml.sax.helpers.DefaultHandler {

    ScoreBoardData data;
    String mainElement;

    public ScoreLoader(ScoreBoardData data) {
        this.data = data;
    }

    public static void loadScores(ScoreBoardData data, String fileName) {
        //Create XML reader object
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(fileName, new ScoreLoader(data));
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        } catch (IOException ignored) {
        }
    }

    public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) throws SAXException {
        System.out.println("Start Element :" + qName);
        if (qName.equals("players")) {
            mainElement = "players";
        }
        if (qName.equals("player") && mainElement.equals("players")) {
            String name = attrs.getValue("name");
            String score = attrs.getValue("score");
            data.addPlayer(new Player(name, Integer.parseInt(score)));
        }
    }

    public void endElement(String namespaceURI, String sName, String qName) throws SAXException {
        if (qName.equals("players")) {
            mainElement = "";
        }
    }
}
