package api.base;


import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {
    // Létrehozzuk a Dotenv példányt.
    private static final Dotenv dotenv = Dotenv.load();

    public static String getApiKey() {
        // Itt hívjuk meg a kulcsot a fájlból.
    	String apiKey = dotenv.get("API_KEY");
    	
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("HIBA: Az API_KEY nem található a .env fájlban!");
        } else {
            //System.out.println("Sikeresen beolvasva: " + apiKey);
            System.out.println("Turn your dreams into reality.");
        }
        return apiKey;
    }
}