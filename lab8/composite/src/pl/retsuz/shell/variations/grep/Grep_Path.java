package pl.retsuz.shell.variations.grep;

import pl.retsuz.filesystem.Composite;
import pl.retsuz.filesystem.IComposite;
import pl.retsuz.filesystem.TextFile;
import pl.retsuz.shell.gen.ICommand;
import pl.retsuz.shell.variations.gen.CommandVariation;
import pl.retsuz.shell.variations.gen.ICommandVariation;

public class Grep_Path extends CommandVariation {

    public Grep_Path (ICommandVariation next, ICommand parent){
        super(next, parent, "[a-zA-Z0-9.l\\/_]+ [a-zA-Z0-9.l\\/_]+");

    }

    @Override
    public void make(String params)
    {
        Composite c = (Composite) (this.getParent().getContext().getCurrent());
        try
        {
            String[] split_params = params.split(" ");
            IComposite el = c.findElementByPath(split_params[1]);
            if (TextFile.class.isInstance(el))
            {
                System.out.println(((TextFile) el).grep(split_params[0]));
            }
            else System.err.println("Niepoprawny wybór. Żądany element nie jest plikiem.");

        }
        catch (Exception e)
        {
            System.err.println("Docelowy element nie jest plikiem lub nie istnieje.");
        }
    }
}
