package pl.retsuz.shell.specs;

import pl.retsuz.context.IContext;
import pl.retsuz.shell.gen.Command;
import pl.retsuz.shell.gen.ICommand;

import java.util.regex.Pattern;

public class Diff extends Command {

    public Diff(IContext ctx, ICommand next)
    {
        super("diff", ctx, next, null, "Uzycie diff <plik> <plik>");
        super.generalPattern = Pattern.compile("diff" + " ([a-zA-Z0-9.l\\/_]+ [a-zA-Z0-9.l\\/_]+)");
    }
}
