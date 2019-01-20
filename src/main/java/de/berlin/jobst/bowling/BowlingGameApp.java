package de.berlin.jobst.bowling;

import java.io.Console;
import java.util.ArrayList;

class BowlingGameApp {

    public static void main(String[] args) {

        Console con = System.console();

        if (con == null) {
            System.err.print("Keine Konsole für die Ein-/Ausgabe vorhanden.\n");
            System.exit(1);
        }

        con.printf("\n*** Starte neues Bowling Spiel. ***\n\n");

        Game game = new Game();
        game.setSilent();

        ArrayList<Integer> validRolls = new ArrayList<>();

        do {
            String str = con.readLine("Bitte die geworfenen Pins eingeben (q - Ende): ");

            if (str.equals("q")) System.exit(0);

            try {
                int roll = Integer.parseInt(str);
                int frameNumber = game.getFrame();
                int rollNumber = game.getRoll();
                game.rollBall(roll);
                validRolls.add(roll);
                if (game.isUnfinished()) {
                    con.printf("\nDas Zwischenergebnis im %d. Frame nach dem %d. Wurf ist %d Punkte.\n\n"
                            , frameNumber, rollNumber, game.getScore());
                }
            } catch (NumberFormatException | UnsupportedOperationException ex) {
                con.printf("%s ist eine ungültige Eingabe.\n\n", str);
            }

        } while (game.isUnfinished());

        con.printf("\nDas Spielergebnis für die Würfe %s ist %d Punkte.\n\n", validRolls.toString(), game.getScore());

        System.exit(0);
    }
}
