package SharedWebsocket;

import Enums.Type;
import models.Player;

import java.util.ArrayList;

public class WebsocketDTO {
    private String name;
    private Type type;
    private Player player;

    public WebsocketDTO(Player player) {
        this.player = player;
    }

//    public ArrayList<Player> PlayerList() {}

    public Player getPlayer(){
        return player;
    }
}
