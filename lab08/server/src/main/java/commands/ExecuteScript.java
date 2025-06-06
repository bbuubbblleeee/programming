package commands;

import transfer.Request;
import transfer.Response;


/**
 * The class implements the execute_script command.
 * The command reads and executes a script from the specified file.
 */
public class ExecuteScript extends Command {
    //реализовано на клиенте, здесь осталось только ради команды help

    public ExecuteScript() {
        super("execute_script", "считывает и исполняет скрипт из указанного файла.", 1);
    }

    @Override
    public Response execute(Request request) {
        return null;
//        try {
//            ReadData readData = new ReadData(request.args()[0]);
//            while (readData.myReader.hasNextLine()){
//                String command = readData.myReader.readLine();
//                System.out.println("Текущая команда: " + command + ".");
//                new Console(command, readData, getCollectionManager());
//            }
//            return new Response("Скрипт был исполнен.");
//        }
//        catch (Exception e){
//            return new Response(e.getMessage());
//        }
    }
}
