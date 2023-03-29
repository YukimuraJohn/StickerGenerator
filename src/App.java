import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {

    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP para buscar o dados no caso os top 250 filmes
        // case test "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build(); 
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // Pegar somente os dados que são interessantes (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);
        
        // Exibir e manipular os dados
        // iniciando o generator
        StickersGenerator generator = new StickersGenerator();
        
        for (Map<String,String> filme : listaFilmes) {

            // capturando as chaves image e title nas variaveis String
            String urlImg = filme.get("image");
            String titleMovie = filme.get("title");
            // uilizando link da img no inputStream
            InputStream inputStream = new URL(urlImg).openStream();
            // Nome do arquivo recebe o nome do filme mais o .png
            String fileName = titleMovie + ".png";

            generator.create(inputStream, fileName);

            double rating = Double.parseDouble(filme.get("imDbRating"));
            int ratingStars = (int)rating; 

            System.out.println("\u001b[1mTítulo: \u001b[m\u001b[5m" + titleMovie + "\u001b[m");
            System.out.println("\u001b[1m\u001b[35mPoster: \u001b[m\u001b[1m" + urlImg + "\u001b[m");
            System.out.println("\u001b[42;1mClassificação: " +  rating + "\u001b[m");

            for (int i = 0; i < ratingStars; i++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
        
        
        // Consumindo endpoint de filmes mais populares e séries mais populares
        // Filmes Populares -> https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
        // Series Populares -> https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json

        String url2 = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco2 = URI.create(url2);
        var client2 = HttpClient.newHttpClient();
        var request2 = HttpRequest.newBuilder(endereco2).GET().build();
        HttpResponse<String> response2 = client2.send(request2, BodyHandlers.ofString());
        String body2 = response2.body();

        // Pegar somente os dados que são interessantes (titulo, poster, classificação)
        var parser2 = new JsonParser();
        List<Map<String, String>> filmesPopulares = parser2.parse(body2);
        
        // Exibir e manipular os dados
        /*
        System.out.println("\u001b[1;1mFilmes Populares\n");
        for (Map<String,String> filme : filmesPopulares) {

            double rating = Double.parseDouble(filme.get("imDbRating"));
            int ratingStars = (int)rating;

            System.out.println("\u001b[1mTítulo: \u001b[m\u001b[5m" + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[1m\u001b[36mPoster: \u001b[m\u001b[1m" + filme.get("image") + "\u001b[m");
            System.out.println("\u001b[37;1m\u001b[41mClassificação: " + rating + "\u001b[m");
            for (int i = 0; i < ratingStars; i++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
        */
        
    }
}
