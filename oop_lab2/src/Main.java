import Dance.Dance;
import Utility.DOMParser;
import Utility.XMLValidator;

import javax.xml.transform.dom.DOMSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        DOMParser parser=new DOMParser();
        ArrayList<Dance> dances=null;
        try {
            dances=parser.parse();
        }catch(Exception e){
            e.printStackTrace();
        }

        for (Dance dance : dances)
            System.out.println(dance.toString());

        XMLValidator validator=new XMLValidator("resources\\Dance.xml","resources\\Dance.xsd");
        if (validator.validateXML())
            System.out.println("Fine");
        else System.out.println("Not fine... Sadge");

        Collections.sort(dances);

        for (Dance dance : dances)
            System.out.println(dance.toString());
        parser.createXML(dances);
    }
}
/*
Создать файл XML и соответствующую ему схему XSD.

При разработке XSD использовать простые и комплексные типы, перечисления, шаблоны и предельные значения, обязательно использование атрибутов и типа ID.

Сгенерировать (создать) Java-класс, соответствующий данному описанию.

Создать Java-приложение для разбора XML-документа и инициализации коллекции объектов информацией из XML-файла. Для разбора использовать SAX, DOM и StAX парсеры. Для сортировки объектов использовать интерфейс Comparator.

Произвести проверку XML-документа с привлечением XSD.

Определить метод, производящий преобразование разработанного XML-документа в документ, указанный в каждом задании.
Избегать copy-past кода.
Весь код должен быть покрыт юнит тестами.



14.	Концерты танцевального коллектива.
Танцевальный номер, представленный на концерте, имеют следующие характеристики:
	Type – направление танца (бальный, народный, эстрадный, восточный и т.д.). ballroom national pop east
	Scene – место выступления (актовый зал, уличная площадка, телестудия и т.д.). Assembly hall, street playground television studio
	Number of dancers – массовый, сольный, парный.
	Music – вид музыкального сопровождения (фонограмма, “живая” музыка).
	Dancers (должно быть несколько) – имя или название коллектива, возраст, сколько лет занимается и т.д.
	Number – каким номером в программе.
Корневой элемент назвать Dance.

10.	Пиво.
•	Name – название пива.
•	Type – тип пива (темное, светлое, лагерное, живое).
•	Al – алкогольное либо нет.
•	Manufacturer – фирма-производитель.
•	Ingredients (должно быть несколько) – ингредиенты: вода, солод, хмель, сахар и т.д.
•	Chars (должно быть несколько) – характеристики: кол-во оборотов (если алкогольное), прозрачность (в процентах), фильтрованное либо нет, пищевая ценность (ккал), способ разлива (объем и материал емкостей)
•	Корневой элемент назвать Beer.

 */