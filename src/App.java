import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {

    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP para buscar o dados, no caso os top 250 filmes
        
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // ContentExtractor imdbExtractor = new ImDbContentExtractor();

        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-02-01&end_date=2023-02-03";
        ContentExtractor nasaExtractor = new NasaContentExtractor();
        
        var http = new ClientHttp();
        String json = http.buscaDados(url);
        
        // Exibir e manipular os dados
        
        List<Content> contents = nasaExtractor.extractContent(json);

        // iniciando o generator
        StickersGenerator generator = new StickersGenerator();

        for (Content content : contents) {

            // uilizando link da img no inputStream
            InputStream inputStream = new URL(content.getImageUrl()).openStream();
            // Nome do arquivo recebe o nome do filme mais o .png
            String fileName = content.getTitle() + ".png";

            generator.create(inputStream, fileName);

            // double rating = Double.parseDouble(content.get("imDbRating"));
            // int ratingStars = (int)rating; 

            System.out.println("\u001b[1mTítulo: \u001b[m\u001b[5m" + content.getTitle() + "\u001b[m");
            // System.out.println("\u001b[1m\u001b[35mPoster: \u001b[m\u001b[1m" + urlImg + "\u001b[m");
            // System.out.println("\u001b[42;1mClassificação: " +  rating + "\u001b[m");

            // for (int i = 0; i < ratingStars; i++) {
            //     System.out.print("⭐");
            // }
            System.out.println("\n");
        }
        
        
        // Consumindo endpoint de filmes mais populares e séries mais populares
        // Filmes Populares -> https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json
        // Series Populares -> https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json

        /* 

        String url2 = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco2 = URI.create(url2);
        var client2 = HttpClient.newHttpClient();
        var request2 = HttpRequest.newBuilder(endereco2).GET().build();
        HttpResponse<String> response2 = client2.send(request2, BodyHandlers.ofString());
        String body2 = response2.body();

        // Pegar somente os dados que são interessantes (titulo, poster, classificação)
        var parser2 = new JsonParser();
        List<Map<String, String>> filmesPopulares = parser2.parse(body2);
        
        */

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
