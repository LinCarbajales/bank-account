package dev.lin;

import dev.lin.views.Menu;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciar();
    }
}
