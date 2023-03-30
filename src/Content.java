public class Content {
    
    // protengendo os dados do conteúdo 
    private final String title;
    private final String imageUrl;

    public Content(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    // encapsulamento dos dados e protegendo
    public String getTitle() {
        return title;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }


    

    
}
