package ScoreBoard;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.util.LinkedList;

public class ScoreSaver {

    public static void saveScores(ScoreBoardData data, String fileName) {
        //Create XML writer object
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        XMLStreamWriter xsw = null;
        try {
            xsw = xof.createXMLStreamWriter(new FileWriter(fileName));
            //Write file
            xsw.writeStartDocument();
            xsw.writeStartElement("players");

            LinkedList<Player> players = data.getPlayers();
            for (Player player : players) {
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
