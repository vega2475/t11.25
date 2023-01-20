public class Test {
    public static void main(String[] args) {
        String text = "- Прошу садиться.\n" +
                "Мари Лоран опустилась в глубокое кожаное кресло.\n" +
                "Пока профессор Керн вскрывал конверт и читал письмо, она бегло осмотрела кабинет.\n" +
                "Какая мрачная комната! Но заниматься здесь хорошо: ничто не отвлекает внимания. Лампа с глухим абажуром освещает только письменный стол, заваленный книгами, рукописями, корректурными оттисками. Глаз едва различает солидную мебель черного дуба. Темные обои, темные драпри. В полумраке поблескивает только золото тисненых переплетов в тяжелых шкафах. Длинный маятник старинных стенных часов движется размеренно и плавно.\n" +
                "Переведя взгляд на Керна, Лоран невольно улыбнулась: сам профессор целиком соответствовал стилю кабинета. Будто вырубленная из дуба, тяжеловесная, суровая фигура Керна казалась частью меблировки. Большие очки в черепаховой оправе напоминали два циферблата часов. Как маятники, двигались его глаза серо-пепельного цвета, переходя со строки на строку письма. Прямоугольный нос, прямой разрез глаз, рта и квадратный, выдающийся вперед подбородок придавали лицу вид стилизованной декоративной маски, вылепленной скульптором-кубистом.\n" +
                "\"Камин украшать такой маской\", - подумала Лоран.";
        int weight = 54;

        solve(text, weight);
    }

    public static StringBuilder solve(String text, int weight) {
        String[] arrOfParagraph = text.split("\n");
        //String[] arrOfWords = text.split("[. ]");
        System.out.println(("--------------------------------------------------------------------------"));//это для того чтобы смотреть в консоли ширину текста и ориентироваться по этой штуку (тут 74 "-")
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arrOfParagraph.length; i++) {
            String[] arrOfWords1 = arrOfParagraph[i].split(" ");
            int quantityOfSymbols = 6;
            int quantityOfWords = 0;
            int quantityOfSymbolsOfWordsInRow = 6;
            int quantityOfSymbolsInEachParagraph = arrOfParagraph[i].length();//for debug
            int quantityOfSpacesInOneRow = 0;
            if(arrOfParagraph[i].length() + 6 > weight) {
                sb.append("      ");
                for (int j = 0; quantityOfSymbols < weight && j < arrOfWords1.length; j++) {//проблема здесь от квантити до вейт (должно быть меньше из-за последнего слова проблемы)
                    //if(quantityOfSymbols + arrOfWords1[arrOfWords1.length - 1].length() > weight)break;
                    quantityOfSymbols += arrOfWords1[j].length() + 1; //подсчет кол-ва символов для каждой строки включая красную строку (ее пишем как 6 пробелов)
                    quantityOfSymbolsOfWordsInRow += arrOfWords1[j].length();
                    quantityOfWords++;
                    quantityOfSpacesInOneRow = weight - quantityOfSymbolsOfWordsInRow;

                    if(j + 1 < arrOfWords1.length) {
                        if (quantityOfSymbols + arrOfWords1[j + 1].length() > weight) {
                            int m = j - quantityOfWords;//индекс слова из массива слов параграфа
                            if (quantityOfWords == 1 && m > -1) {
                                sb.append(arrOfWords1[m + 1]);
                            } else if(quantityOfWords == 1 && m <= -1){
                                sb.append(arrOfWords1[0]);
                            }
                            else {
                                int counter = 0;
                                for (int k = 0; k < quantityOfWords; k++) {
                                    counter++;
                                    m++;
                                    sb.append(arrOfWords1[m]);
                                    int indents = quantityOfWords - 1;//количество промежутков между словами
                                    int spaces = weight - quantityOfSymbolsOfWordsInRow;
                                    int spacesBetweenWords = spaces / indents;
                                    int lastIndentsWithMoreSpaces = spaces % indents;


                                    for (int q = 0; q < spacesBetweenWords && counter <= quantityOfWords - (lastIndentsWithMoreSpaces + 1); q++) {
                                        sb.append(" ");
                                    }
                                    for (int e = 0; e < spacesBetweenWords + 1 && counter > quantityOfWords - (lastIndentsWithMoreSpaces + 1); e++) {
                                        sb.append(" ");
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
        return sb;
    }
}

