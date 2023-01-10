import java.util.Arrays;

/**
 made in Russia
 Плагин для Microsoft Word 2023 "Выравнивание текста".
 */

public class Main {
    public static void main(String[] args) {
        String text = "- Прошу садиться.\n" +
                "Мари Лоран опустилась в глубокое кожаное кресло.\n" +
                "Пока профессор Керн вскрывал конверт и читал письмо, она бегло осмотрела кабинет.\n" +
                "Какая мрачная комната! Но заниматься здесь хорошо: ничто не отвлекает внимания. Лампа с глухим абажуром освещает только письменный стол, заваленный книгами, рукописями, корректурными оттисками. Глаз едва различает солидную мебель черного дуба. Темные обои, темные драпри. В полумраке поблескивает только золото тисненых переплетов в тяжелых шкафах. Длинный маятник старинных стенных часов движется размеренно и плавно.\n" +
                "Переведя взгляд на Керна, Лоран невольно улыбнулась: сам профессор целиком соответствовал стилю кабинета. Будто вырубленная из дуба, тяжеловесная, суровая фигура Керна казалась частью меблировки. Большие очки в черепаховой оправе напоминали два циферблата часов. Как маятники, двигались его глаза серо-пепельного цвета, переходя со строки на строку письма. Прямоугольный нос, прямой разрез глаз, рта и квадратный, выдающийся вперед подбородок придавали лицу вид стилизованной декоративной маски, вылепленной скульптором-кубистом.\n" +
                "\"Камин украшать такой маской\", - подумала Лоран.";
        int weight = 74;

        solve(text, weight);
    }

    public static void solve(String text, int weight) {
        String[] arrOfParagraph = text.split("\n");
        //String[] arrOfWords = text.split("[. ]");
        System.out.println(("--------------------------------------------------------------------------"));//это для того чтобы смотреть в консоли ширину текста и ориентироваться по этой штуку (тут 74 "-")
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arrOfParagraph.length; i++) {
            String[] arrOfWords1 = arrOfParagraph[i].split(" ");
            int quantityOfSymbols = 6;
            int quantityOfWords = 0;
            int quantityOfSymbolsOfWordsInRow = 0;
            int quantityOfSymbolsInEachParagraph = arrOfParagraph[i].length();//for debug
            int quantityOfSpacesInOneRow = 0;
            if(arrOfParagraph[i].length() > weight) {
                sb.append("      ");
                for (int j = 0; quantityOfSymbols < weight && j < arrOfWords1.length; j++) {//проблема здесь от квантити до вейт (должно быть меньше из-за последнего слова проблемы)
                    //if(quantityOfSymbols + arrOfWords1[arrOfWords1.length - 1].length() > weight)break;
                    quantityOfSymbols += arrOfWords1[j].length() + 1; //подсчет кол-ва символов для каждой строки включая красную строку (ее пишем как 6 пробелов)
                    quantityOfSymbolsOfWordsInRow += arrOfWords1[j].length();
                    quantityOfWords++;
                    quantityOfSpacesInOneRow = weight - quantityOfSymbolsOfWordsInRow;

                    if(j + 1 < arrOfWords1.length) {
                        if (quantityOfSymbols + arrOfWords1[j + 1].length() > weight) {
                            int m = j  - quantityOfWords;//индекс слова из массива слов параграфа
                            int counter = 0;
                            for(int k = 0; k < quantityOfWords; k++){
                                counter++;
                                m++;
                                sb.append(arrOfWords1[m]);
                                int indents = quantityOfWords - 1;//количество промежутков между словами
                                int spacesBetweenWords = quantityOfSpacesInOneRow / indents;//количество пробелов между словами
                                int indentsWithAVGCountOfSpaces = quantityOfSpacesInOneRow % indents;//колво промежутков с средним колвом пробелов
                                int indentsWithMinOrMaxCountOfSpaces = indents - indentsWithAVGCountOfSpaces;//кол-во промежутков которых меньшинство
                                if(indentsWithAVGCountOfSpaces < indentsWithMinOrMaxCountOfSpaces && counter >= quantityOfWords - indentsWithMinOrMaxCountOfSpaces){
                                    for(int a = 0; a < spacesBetweenWords; a++){
                                        sb.append(" ");
                                    }
                                }
                                else if(indentsWithAVGCountOfSpaces < indentsWithMinOrMaxCountOfSpaces){//большинство максималок
                                    for(int b = 0; b < spacesBetweenWords - 1; b++){
                                        sb.append(" ");
                                    }
                                }



                                if(indentsWithAVGCountOfSpaces > indentsWithMinOrMaxCountOfSpaces && counter < quantityOfWords - indentsWithMinOrMaxCountOfSpaces){
                                    for(int a = 0; a < spacesBetweenWords; a++){
                                        sb.append(" ");
                                    }
                                }
                                else if(indentsWithAVGCountOfSpaces > indentsWithMinOrMaxCountOfSpaces){//большинство минималок
                                    for(int c = 0; c < spacesBetweenWords + 1;c++){
                                        sb.append(" ");
                                    }
                                }

                                if(indentsWithAVGCountOfSpaces == indentsWithMinOrMaxCountOfSpaces){
                                    if(counter * 2 < quantityOfWords){
                                        for(int a = 0; a < spacesBetweenWords; a++){
                                            sb.append(" ");
                                        }
                                    }
                                    else{
                                        for(int b = 0; b < spacesBetweenWords + 1; b++){
                                            sb.append(" ");
                                        }
                                    }
                                }


                            }
                            quantityOfSpacesInOneRow = 0;
                            quantityOfSymbolsOfWordsInRow = 0;
                            quantityOfSymbols = 0;
                            quantityOfWords = 0;
                            sb.append("\n");
                        }
                    }
                    else if(j == arrOfWords1.length - 1){//для допечатки длинных абзацев строки которых переносятся из-за их размеры
                        for(int s = quantityOfWords - 1; s >= 0; s--){
                            sb.append(arrOfWords1[j - s]).append(" ");
                            int indents = quantityOfWords - 1;//количество промежутков между словами
                            int spacesBetweenWords = quantityOfSpacesInOneRow / indents;//количество пробелов между словами
                            int lastWordsWithMoreSpaces = quantityOfSpacesInOneRow % indents;//количество последних слов между которыми кол-во пробелов должо быть на 1 больше чем между остальными в этой строке

                        }
                    }


                }

                sb.append("\n");
            }
            else{
                sb.append("      ").append(arrOfParagraph[i]).append("\n");
            }
        }
        System.out.println(sb);
    }
}