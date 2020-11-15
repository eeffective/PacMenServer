package SharedWebsocket;

public class WebsocketMessage {
    private MessageType operation;
    private WebsocketDTO dto;

    public WebsocketMessage(MessageType operation) {
        this.operation = operation;
    }

    public MessageType getOperation() {
        return operation;
    }

    public void setOperation(MessageType operation) {
        this.operation = operation;
    }

    public WebsocketMessage() {
    }

    public WebsocketMessage(MessageType operation, WebsocketDTO dto){
        this.operation = operation;
        this.dto = dto;
    }

    public WebsocketDTO getDto() {
        return dto;
    }
}


