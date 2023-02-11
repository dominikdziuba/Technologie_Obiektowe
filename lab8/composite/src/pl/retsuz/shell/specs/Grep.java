package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;

import java.util.regex.Pattern;

public class Grep extends Command {

    public Grep(IContext ctx, ICommand next)
    {
        super("grep", ctx, next, null, "Uzycie grep <wzorzec> <sciezka>");
        super.generalPattern = Pattern.compile("grep" + " ([a-zA-Z0-9.l\\/_]+ [a-zA-Z0-9.l\\/_]+)");
    }
}
