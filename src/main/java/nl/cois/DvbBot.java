package nl.cois;

import start.Bot;

import java.io.IOException;

public class DvbBot extends Bot {
    public static void main(String[] args) throws IOException {
        new DvbBot().readSystemInput();
    }
    @Override
    public void doTurn() {
        /* do nothing */
    }
}
