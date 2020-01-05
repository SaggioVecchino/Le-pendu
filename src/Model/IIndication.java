package Model;

public enum IIndication {
    Definition,
    Synonyme,
    Antonyme;

    public final static int coeffDefinition = 3;
    public final static int coeffSynonyme = 2;
    public final static int coeffAntonyme = 1;

    public static IIndication getIndication(String ind) {
        switch (ind) {
            case "DEFINITION":
                return Definition;
            case "SYNONYME":
                return Synonyme;
            case "ANTONYME":
                return Antonyme;
            default:
                return null;

        }

    }

    public static String indicationString(IIndication indication) {
        if (indication == Definition) return "Definition";
        if (indication == Synonyme) return "Synonyme";
        return "Antonyme";
    }
}
