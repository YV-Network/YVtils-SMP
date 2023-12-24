package yv.tils.smp.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @version CH2-1.0.0
 * @since CH2-1.0.0
 */
public class MojangAPI {
    public static UUID Name2UUID(String playerName) {
        try {
            String url = "https://api.mojang.com/users/profiles/minecraft/" + playerName;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder content = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();

                String websiteContent = content.toString();
                List<String> list = new ArrayList<>(List.of(websiteContent.split(",")));
                Map<String, String> map = new HashMap<>();

                for (String s : list) {
                    s = s.replace("{", "");
                    s = s.replace("}", "");
                    s = s.replace("\"", "");
                    s = s.replace(" ", "");
                    map.put(s.split(":")[0], s.split(":")[1]);
                }

                map.put("id", map.get("id").substring(0, 8) + "-" + map.get("id").substring(8, 12) + "-" + map.get("id").substring(12, 16) + "-" + map.get("id").substring(16, 20) + "-" + map.get("id").substring(20));

                return UUID.fromString(map.get("id"));
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String UUID2Name(UUID uuid) {
        try {
            String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString().replace("-", "");
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder content = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();

                String websiteContent = content.toString();
                List<String> list = new ArrayList<>(List.of(websiteContent.split(",")));
                Map<String, String> map = new HashMap<>();

                for (String s : list) {
                    s = s.replace("{", "");
                    s = s.replace("}", "");
                    s = s.replace("\"", "");
                    s = s.replace(" ", "");
                    map.put(s.split(":")[0], s.split(":")[1]);
                }

                map.put("name", map.get("name"));

                return map.get("name");
            } else {
                System.out.println("HTTP request failed with response code: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}