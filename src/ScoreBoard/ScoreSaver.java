package ScoreBoard;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.util.LinkedList;

/**
 * Saves the scores to an XML file.
 */
public class ScoreSaver {

    /**
     * Saves the scores to an XML file.
     *
     * @param data     The scores to save.
     * @param fileName The name of the file to save to.
     */
    public static void saveScores(ScoreBoardData data, String fileName) {
        //Create XML writer object
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = null;
        try {
            xsw = xof.createXMLStreamWriter(new FileWriter(fileName));
            xsw.writeStartDocument();
            xsw.writeStartElement("players");

            LinkedList<Player> players = data.getPlayers();
            for (Player player : players) {
                //Write each player
                xsw.writeStartElement("player");
                xsw.writeAttribute("name", player.getName());
                xsw.writeAttribute("score", String.valueOf(player.getScore()));
                xsw.writeEndElement();
            }

            xsw.writeEndElement();
            xsw.writeEndDocument();
            xsw.flush();
        } catch (Exception e) {
            System.err.println("Unable to write the file: " + e.getMessage());
        } finally {
            try {
                if (xsw != null) {
                    xsw.close();
                }
            } catch (Exception e) {
                System.err.println("Unable to close the file: " + e.getMessage());
            }
        }
    }

}
