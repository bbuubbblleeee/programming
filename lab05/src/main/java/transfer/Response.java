package transfer;

public record Response (String message, String... args) {
    @Override
    public String toString(){
        return message;
    }
}
