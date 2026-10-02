package api.base;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String getApiKey() {
        String apiKey = System.getenv("API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            apiKey = dotenv.get("API_KEY");
        }

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "Az API_KEY nincs beállítva. Add meg környezeti változóként vagy a projekt gyökerében lévő .env fájlban.");
        }

        return apiKey;
    }

    public static String getProjectId() {
        String projectId = System.getenv("PROJECT_ID");
        if (projectId == null || projectId.isBlank()) {
            projectId = dotenv.get("PROJECT_ID");
        }
        return projectId;
    }
}
