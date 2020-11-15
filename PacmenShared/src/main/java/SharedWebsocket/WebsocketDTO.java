package SharedWebsocket;

import Enums.Type;
import models.Player;

public class WebsocketDTO {
    private String name;
    private Type type;
    private Player player;

    public WebsocketDTO(Player player) {
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }
}
