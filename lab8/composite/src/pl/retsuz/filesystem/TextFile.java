package pl.retsuz.filesystem;

import java.util.ArrayList;
import java.util.List;

public class TextFile extends GeneralComposite {
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String grep(String wanted) {
        String[] fileLines = content.split("\n");
        String results = "";
        for (String line : fileLines) {
            if (line.contains(wanted)) {
                results += "> " + line + "\n";
            }
        }
        if (results == "") {
            return "Nie znaleziono wzorca";
        }
        return results;
    }

    public String lcsAlgorithm(IComposite file)
    {
        String[] firstFile = this.content.split("\n");
        String[] secFile = ((TextFile) file).content.split("\n");
        List<String> difference = new ArrayList<>();
        int a = firstFile.length;
        int b = secFile.length;
        int[][] lcs = new int[a + 1][b + 1];
        for (int i = 0; i <= a; i++)
        {
            for (int j = 0; j <= b; j++)
            {
                if (i == 0 || j == 0)
                {
                    lcs[i][j] = 0;
                }
                else if (firstFile[i - 1].equals(secFile[j - 1]))
                {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                }
                else
                {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }

        int i = a;
        int j = b;
        while (i > 0 && j > 0)
        {
            if (firstFile[i - 1].equals(secFile[j - 1]))
            {
                difference.add(0, firstFile[i - 1]+ "\n");
                i--;
                j--;
            }
            else if (lcs[i - 1][j] > lcs[i][j - 1])
            {
                difference.add(0, " < " + firstFile[i - 1] + "\n");
                i--;
            }
            else
            {
                difference.add(0, "> " + secFile[j - 1]+ "\n");
                j--;
            }
        }

        while (i > 0)
        {
            difference.add(0, "< " + firstFile[i - 1]+ "\n");
            i--;
        }
        while (j > 0)
        {
            difference.add(0, "> " + secFile[j - 1]);
            j--;
        }

        return difference.toString();
    }

}
