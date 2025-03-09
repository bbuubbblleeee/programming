package transfer;

import collection.Dragon;
import commands.Command;

import java.util.ArrayList;

public record Request(Command command, String[] args, ArrayList<Dragon> dragons) {
}
