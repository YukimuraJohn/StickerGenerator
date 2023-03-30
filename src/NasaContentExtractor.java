import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {
    
    public List<Content> extractContent(String json) {

        // Pegar somente os dados que são interessantes (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json); // Lista de Atributos

        // criando um lista vazia para ser populada
        List<Content> contents = new ArrayList<>();

        // Popular a lista de conteúdos
        for (Map<String, String> attributes : attributesList) {

            // capturando as chaves title e url nas variaveis String
            String title = attributes.get("title");
            String urlImg = attributes.get("url");
            
            var content = new Content(title, urlImg);
            
            // adicionando o conteudo a lista de conteúdos que estava inicialmente vazia
            contents.add(content);
        }

        return contents;
    }

}
