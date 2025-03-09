package commands;

import collectionManager.CollectionManager;
import collectionManager.InMemoryCollection;
import transfer.Request;
import transfer.Response;

public abstract class Command {
    private final String commandName;
    private final String purpose;
    private final int requiredDragon;
    private final int requiredArgs;

    protected static CollectionManager collectionManager = new InMemoryCollection();

    public Command(String commandName, String purpose, int requiredArgs, int requiredDragon){
        this.commandName = commandName;
        this.purpose = purpose;
        this.requiredArgs = requiredArgs;
        this.requiredDragon = requiredDragon;
    }


    abstract public Response execute(Request request);

    public String getName(){
        return commandName;
    }

    public String getPurpose(){
        return purpose;
    }

    public int getRequiredArgs(){
        return requiredArgs;
    }
    public int getRequiredDragon(){
        return requiredDragon;
    }
}
