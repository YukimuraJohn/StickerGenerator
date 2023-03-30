import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersGenerator {
    

    public void create(InputStream inputStream, String fileName) throws Exception {
        // leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("entry/filme.jpg"));
        // InputStream inputStreamUrl = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_2.jpg").openStream();

        BufferedImage originalImage = ImageIO.read(inputStream);

        // criar nova imagem em memória com transparência e com tamanho novo
        int widthImg = originalImage.getWidth();
        int heightImg = originalImage.getHeight();
        int newHeightImg = heightImg + 320;
        BufferedImage newImage = new BufferedImage(widthImg, newHeightImg, BufferedImage.TRANSLUCENT);
        
        // Extra read my Stick
        InputStream inputMySticker = new FileInputStream("entry/myImage.png");
        BufferedImage mySticker = ImageIO.read(inputMySticker);

        // copiar a imagem original pra nova imagem(em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // configurar a fonte e setando a fonte
        Font font = new Font("Impact", Font.BOLD, 72);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // escrever uma frase iconica na nova imagem
        String text = "Brabo Demais";
        // pegar o tamanho do texto
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(text, graphics);
        int textWidth = (int) rectangle.getWidth();
        // posição para inserir o texto
        int textPositionX = (widthImg*20)/100; // para pegar centro da imagem (widthImg - textWidth)/2
        graphics.drawString(text, textPositionX, newHeightImg-100);
        // adicionando sticker personalizado
        graphics.drawImage(mySticker, widthImg-320,newHeightImg-320 , null);

        // escrever a nova imagem em um arquivo
        // var pathName = "leave/sticker.png";

        // Fazendo a criação do diretório caso o mesmo não exista
        String dirName = "leave/";
        if (!new File(dirName).exists()){
            // System.out.println("diretório não existe e será criado");
            new File(dirName).mkdirs();
            ImageIO.write(newImage, "png", new File(dirName + fileName));
        }
        // System.out.println("diretório existe e então será criado o arquivo");
        ImageIO.write(newImage, "png", new File(dirName + fileName));
        

        
    };

}
