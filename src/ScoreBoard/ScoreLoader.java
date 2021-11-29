package ScoreBoard;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * This class is used to load the score from the xml file.
 */
public class ScoreLoader extends org.xml.sax.helpers.DefaultHandler {

    ScoreBoardData data;
    String mainElement;

    /**
     * Constructor, sets the load destination.
     *
     * @param data Where the scores will be loaded
     */
    public ScoreLoader(ScoreBoardData data) {
        this.data = data;
    }

    /**
     * Load the scores from the xml file.
     *
     * @param data     Where the scores will be loaded
     * @param fileName The file name
     */
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

    /**
     * This method is called when the parser encounters a start element.
     * We check if we are in the players element, if so we add the player to the list.
     *
     * @param namespaceURI The namespace URI
     * @param sName        The local name
     * @param qName        The qualified name
     * @param attrs        The attributes
     * @throws SAXException
     */
    public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) throws SAXException {
        if (qName.equals("players")) {
            mainElement = "players";
        }
        if (qName.equals("player") && mainElement.equals("players")) {
            String name = attrs.getValue("name");
            String score = attrs.getValue("score");
            data.addPlayer(new Player(name, Integer.parseInt(score)));
        }
    }

    /**
     * This method is called when the parser encounters end of the element.
     * If this is the end of the players element we reset the main element.
     *
     * @param namespaceURI The namespace URI
     * @param sName        The local name
     * @param qName        The qualified name
     * @throws SAXException
     */
    public void endElement(String namespaceURI, String sName, String qName) throws SAXException {
        if (qName.equals("players")) {
            mainElement = "";
        }
    }
}
