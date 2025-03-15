package collection.checkers;

import exceptions.InvalidFileException;
import exceptions.WrongNumberOfArguments;
import io.MyReader;

/*
    Класс отвечает за проверку количества аргументов, введённых вместе с командой.
 */
public class NumberOfArgumentChecker {
    public static void numberOfArgumentsChecker(String[] arguments, int requiredArgs, boolean inScript, MyReader myReader){
        while (true) {
            try{
                if (arguments != null && arguments.length == requiredArgs) {
                    return;
                }
                throw new WrongNumberOfArguments();
            }
            catch (WrongNumberOfArguments e){
                if (inScript){
                    throw new InvalidFileException("Invalid number of arguments with the command in the file.");
                }
                System.out.print(e.getMessage());
                arguments = (myReader.readLine()).trim().split("\\s+");
            }
        }
    }
}
