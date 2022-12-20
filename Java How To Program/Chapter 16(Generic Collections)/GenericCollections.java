

public class GenericCollections { }

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
	Bölüm 7.16'da, ArrayList oluştururken belirttiğiniz türden nesnelere başvuruları depolayan, dinamik olarak yeniden boyutlandırılabilir dizi benzeri bir veri yapısı olan genel ArrayList koleksiyonunu tanıttık.
	Bu bölümde, önceden oluşturulmuş diğer birçok genel veri yapısını içeren Java koleksiyonları çerçevesi hakkındaki tartışmamıza devam edeceğiz.

	Bazı koleksiyon örnekleri, akıllı telefonunuzda veya medya oynatıcınızda saklanan en sevdiğiniz şarkılar, kişi listeniz, bir kart oyununda tuttuğunuz kartlar, en sevdiğiniz spor takımının üyeleri ve okulda aldığınız derslerdir.

	Her koleksiyon türünün yeteneklerini bildiren koleksiyonlar-çerçeve arabirimlerini, bu arabirimleri uygulayan çeşitli sınıfları, koleksiyon nesnelerini işleyen yöntemleri ve koleksiyonlar arasında \"yürüyen\" yineleyicileri tartışıyoruz.

----------------------------------------------------------------------------------------------------------------------------------------------------------*/

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
 		Collections Overview

 	Koleksiyon, diğer nesnelere başvuruları tutabilen bir veri yapısıdır (aslında bir nesnedir).
 	Genellikle, koleksiyonlar, koleksiyonun öğe türüyle is-a ilişkisi olan herhangi bir türdeki nesnelere başvurular içerir.

 	Koleksiyonlar-çerçeve arabirimleri, çeşitli koleksiyon türlerinde genel olarak gerçekleştirilecek işlemleri bildirir.
 	Şekil 16.1'de koleksiyonlar-çerçeve arabirimlerinden bazıları listelenmiştir.
 	Bu arayüzlerin çeşitli uygulamaları çerçeve içinde sağlanmaktadır.
 	Kendi uygulamalarınızı da sağlayabilirsiniz.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
  	Collection : Koleksiyon hiyerarşisindeki Set, Queue ve List arabirimlerinin türetildiği kök arabirim.
  	Set : A collection that does not contain duplicates.
  	List : An ordered collection that can contain duplicate elements.
  	Map:Anahtarları değerlerle ilişkilendiren ve yinelenen anahtarlar içeremeyen bir koleksiyon. Map, Koleksiyon'dan türetilmemiştir.
  	Queue:Genellikle bir bekleme hattını modelleyen ilk giren ilk çıkar koleksiyonu; diğer siparişler belirtilebilir.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
		Object-Based Collections

	Collections-framework sınıfları ve arabirimleri java.util paketinin üyeleridir.
	İlk Java sürümlerinde, koleksiyonlar yalnızca Nesne başvurularını depolayan ve işleyen sınıfları çerçeveleyerek, bir koleksiyondaki herhangi bir nesneyi depolamanıza olanak tanır, çünkü tüm sınıflar doğrudan veya dolaylı olarak Object sınıfından türetilir.
	Programların normalde belirli nesne türlerini işlemesi gerekir.
	Sonuç olarak, bir koleksiyondan elde edilen Object başvurularının, programın nesneleri doğru şekilde işlemesine izin vermek için uygun bir türe indirgenmesi gerekiyordu.
	Bölüm 10'da tartıştığımız gibi, genellikle downcasting'den kaçınılmalıdır.


		Generic Collections

	Bu sorunu ortadan kaldırmak için, koleksiyonlar çerçevesi, Bölüm 7'de genel ArrayLists ile tanıttığımız ve Bölüm 20, Genel Sınıflar ve Yöntemler: Daha Derin Bir Bakış'ta daha ayrıntılı olarak tartıştığımız jenerik yeteneklerle geliştirilmiştir.
	Genel türler, bir koleksiyonda depolanacak tam türü belirtmenize olanak tanır ve derleme zamanı tür denetiminin avantajlarını sunar; koleksiyonlarınızda uygunsuz türler kullanırsanız derleyici hata iletileri verir.
	Genel bir koleksiyonda depolanan türü belirttikten sonra, koleksiyondan aldığınız tüm başvurular bu türe sahip olur.
	Bu, başvurulan nesne uygun türde değilse ClassCastExceptions oluşturabilecek açık tür dökümlerine olan ihtiyacı ortadan kaldırır.
	Ayrıca, genel koleksiyonlar, jenerikler kullanılmaya başlanmadan önce yazılmış Java koduyla geriye dönük olarak uyumludur.


		Choosing a Collection

	Her koleksiyonun belgeleri, bellek gereksinimlerini ve öğelerinin eklenmesi ve kaldırılması, öğe arama, öğeleri sıralama ve daha fazlası gibi işlemler için yöntemlerinin performans özelliklerini tartışır.
	Bir koleksiyon seçmeden önce, düşündüğünüz koleksiyon kategorisinin (Set, List, Map, Queue vb.) çevrimiçi belgelerini gözden geçirin, ardından uygulamanızın gereksinimlerini en iyi karşılayan uygulamayı seçin.
	Bölüm 19, Arama, Sıralama ve Büyük O, işlenecek veri öğelerinin sayısına bağlı olarak bir algoritmanın görevini yerine getirmek için ne kadar zor çalıştığını açıklamanın bir yolunu tartışır.
	Bölüm 19'u okuduktan sonra, çevrimiçi belgelerde açıklandığı gibi her koleksiyonun performans özelliklerini daha iyi anlayacaksınız.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
		Type-Wrapper Classes

	Her ilkel türün (Ek D'de listelenmiştir) karşılık gelen bir tür sarmalayıcı sınıfı vardır (java.lang paketinde).
	Bu sınıflara Boolean, Byte, Character, Double, Float, Integer, Long ve Short denir.
	Bunlar, ilkel tür değerleri nesneler olarak işlemenizi sağlar.
	Bu önemlidir, çünkü Bölüm 16-21'de yeniden kullandığımız veya geliştirdiğimiz veri yapıları nesneleri manipüle eder ve paylaşır - ilkel türlerdeki değişkenleri manipüle edemezler.
	Ancak, tür sarmalayıcı sınıflarının nesnelerini işleyebilirler, çünkü her sınıf sonuçta Object'ten türetilir.



---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
		Interface Collection and Class Collection

	Interface Collection, bir koleksiyona nesne (veya öğe) add, clear ve compare gibi işlemler (yani, tüm koleksiyonda gerçekleştirilen işlemleri) içerir.
	Bir Koleksiyon ayrıca bir arraye dönüştürülebilir.
	Buna ek olarak, interface Collection, bir programın koleksiyonda dolaşmasına ve yineleme sırasında koleksiyondan öğeleri kaldırmasına olanak tanıyan bir Iterator nesnesi döndüren bir yöntem sağlar.
	Bölüm 16.6.1'de Yineleyici sınıfını tartışıyoruz.
	Diğer arabirim Koleksiyonu yöntemleri, bir programın koleksiyonun boyutunu ve koleksiyonun boş olup olmadığını belirlemesini sağlar.

    Sınıf Koleksiyonları, koleksiyonlarda arama yapan, sıralayan ve koleksiyonlardaki diğer işlemleri gerçekleştiren statik kolaylık yöntemleri sağlar.
	Koleksiyon, interface koleksiyonunu uygulayan tüm nesnelerin polimorfik üretimine izin vermek için yöntemlerde yaygın olarak bir parametre türü olarak kullanılır.
	Çoğu koleksiyon uygulaması, Collection bağımsız değişkenini alan bir oluşturucu sağlar ve böylece belirtilen koleksiyonun öğelerini içeren yeni bir koleksiyonun oluşturulmasına izin verir.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
		List

	Liste (bazen dizi olarak da adlandırılır), yinelenen öğeler içerebilen sıralı öğeler koleksiyonudur.
	Dizi endeksleri gibi, Liste endeksleri de sıfır tabanlıdır (yani, ilk öğenin endeksi sıfırdır).
	Collection'dan devralınan yöntemlere ek olarak, List öğeleri dizinleri aracılığıyla işlemek, belirli bir öğe aralığını değiştirmek, öğeleri aramak ve öğelere erişmek için bir ListIterator edinmek için yöntemler sağlar.

	Interface list ArrayList ve LinkedList dahil olmak üzere çeşitli sınıflar tarafından uygulanır.
	Autoboxing, bu sınıfların nesnelerine ilkel tip değerler eklediğinizde gerçekleşir, çünkü yalnızca nesnelere referanslar saklarlar.
	Bir Arraylist'in mevcut öğeleri arasında bir eleman eklemek, yeni bir öğenin yoldan çekilmesinden sonra tüm öğelerin verimsiz bir işlemidir, bu da çok sayıda öğeye sahip bir koleksiyonda pahalı bir işlem olabilir.
	Bir Linked list, bir koleksiyonun ortasındaki elemanların verimli yerleştirilmesini (veya çıkarılmasını) sağlar, ancak koleksiyondaki belirli bir öğeye atlamak için çok daha az verimlidir. Bölüm 21'deki bağlantılı listelerin mimarisini tartışıyoruz.


---------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*---------------------------------------------------------------------------------------------------------------------------------------------------------
		ArrayList and Iterator
---------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class CollectionTest
//{
//    public static void main(String[] args)
//    {
//        String[] colors = {"MAGENTA","RED","WHITE","BLUE","CYAN"};
//        List<String> list = new ArrayList<>();
//
//        for(String color:removeColors)
//        {
//            removeList.add(color);
//        }
//
//        System.out.println("ArrayList: ");
//
//        for(int count = 0; count < list.size(); count++)
//        {
//            System.out.printf("%s",list.get(count));
//        }
//
//        removeColors(list,removeList);
//
//        System.out.printf("%nArrayList after calling removeColors:%n");
//
//        for(String color : list)
//        {
//            System.out.printf("%s",color);
//        }
//
//        private static void removeColors(Collection<String> collection1, Collection<String collection2>)
//        {
//            Iterator<String> iterator = collection1.iterator();
//
//            while (iterator.hasNext())
//            {
//                if (collection2.contains(iterator.next()))
//                {
//                    iterator.remove();
//                }
//            }
//        }
//
//    }
//}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Kod açıklaması

    11 ve 19. satırlar, String dizilerinin renklerini ve removeColors'ı bildirir ve başlatır.
    12. ve 20. satırlar ArrayList nesneleri oluşturur ve<String> bunların başvurularını<String> sırasıyla List variables list ve removeList öğelerine atar.

    ArrayList'in genel bir sınıf olduğunu hatırlayın, böylece her listedeki öğelerin türünü belirtmek için bir tür bağımsız değişkeni (bu durumda dize) belirleyebiliriz.
    Derleme zamanında bir koleksiyonda saklanacak türü belirttiğiniz için, jenerik koleksiyonlar derleyicinin geçersiz türleri kullanma girişimlerini yakalamasını sağlayan derleme zamanı türü güvenliği sağlar.
    Örneğin, çalışanları bir tel koleksiyonunda saklayamazsınız.

    14-16 arası satırlar listeyi dizi renklerinde depolanan Dizelerle doldurur ve 22-24 arası satırlar, öğeleri Listenin sonuna ekleyen List method add'i kullanarak removeList'i array removeColors içinde depolanan Dizelerle doldurur.
    29-31 satırları listenin her bir öğesinin çıktısını verir.
    Satır 29, ArrayList'teki öğelerin sayısını almak için List yöntem boyutunu çağırır.
    Satır 30, tek tek öğe değerlerini almak için List yöntemini kullanır.
    29-31. satırlar da ifade için geliştirilmiş ifadeyi kullanabilirdi.

    Satır 34, removeColors (satır 45–56) yöntemini çağırır, listeyi ve removeList'i bağımsız değişken olarak geçirir.
    removeColors yöntemi, removeList içindeki Strings'i Strings in listesinden siler.
    39–41 satırları, removeColors görevini tamamladıktan sonra listenin öğelerini yazdırır.

    removeColors yöntemi iki Collection<String> parametresi (satır 45-46) bildirir ve Strings içeren herhangi iki Koleksiyon bağımsız değişken olarak geçirilebilir.
    Yöntem\nilk Koleksiyonun (koleksiyon1) öğelerine bir Yineleyici aracılığıyla erişir.
    Satır 48, Koleksiyon için bir Yineleyici almak üzere Koleksiyon yöntemi yineleyicisini çağırır.
    Arabirim Koleksiyonu ve Yineleyici genel türlerdir.
    Döngü devam koşulu (satır 51), yineleme yapılacak daha fazla öğe olup olmadığını belirlemek için Iterator yöntemi hasNext'i çağırır.
    Yöntem hasNext, başka bir öğe varsa true değerini ve aksi takdirde false değerini döndürür.

    52. satırdaki if koşulu, sonraki öğeye başvuru almak için yanındaki Yineleyici yöntemini çağırır, ardından koleksiyon2'nin next tarafından döndürülen öğeyi içerip içermediğini belirlemek için ikinci Koleksiyon'un (koleksiyon2) yöntem içeriğini kullanır.
    Bu durumda, satır 53, öğeyi Collection collection1'den kaldırmak için Iterator yöntemi remove öğesini çağırır.

    Bir koleksiyon, o koleksiyon için bir yineleyici oluşturulduktan sonra yöntemlerinden biri tarafından değiştirilirse, yineleyici hemen geçersiz hale gelir ve yineleyiciyle gerçekleştirilen herhangi bir işlem hemen başarısız olur ve bir ConcurrentModificationException atar.
    Bu nedenle, yineleyicilerin \"hızlı başarısız\" olduğu söylenir.
    Hızlı hata yapan yineleyiciler, değiştirilebilir bir koleksiyonun aynı anda iki veya daha fazla iş parçacığı tarafından manipüle edilmemesini sağlamaya yardımcı olur ve bu da koleksiyonu bozabilir.
    Bölüm 23, Eşzamanlılık'ta, birden çok eşzamanlı iş parçacığı tarafından güvenli bir şekilde manipüle edilebilen eşzamanlı koleksiyonlar (paket java.util.concurrent) hakkında bilgi edineceksiniz.

    Bu örnekte List değişkenleri aracılığıyla ArrayLists'e başvururuz.
    Bu, daha sonra LinkedLists'in daha uygun olacağını belirlersek, kodumuzu daha esnek ve değiştirilmesi daha kolay hale getirir, yalnızca ArrayList nesnelerini oluşturduğumuz satırların (satır 12 ve 20) değiştirilmesi gerekir.
    Genel olarak, bir koleksiyon nesnesi oluşturduğunuzda, karşılık gelen koleksiyon arabirimi türünde bir değişkenle bu nesneye başvurun.
    Benzer şekilde, Koleksiyon başvurularını almak için removeColors yönteminin uygulanması, yöntemin Koleksiyon arabirimini uygulayan herhangi bir koleksiyonla kullanılmasını sağlar.
---------------------------------------------------------------------------------------------------------------------------------------------------------*/

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
            Şekil 16.3, LinkedLists üzerindeki çeşitli işlemleri göstermektedir.
            Program iki LinkedLists dizeleri oluşturur.
            Bir Listenin öğeleri diğerine eklenir.
            Daha sonra tüm Dizeler büyük harfe dönüştürülür ve bir dizi öğe silinir.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/


//class ListTest
//{
//    public static void main(String[] args)
//    {
//        String[] colors = {"black","yellow","green","blue","violet"};
//
//        List<String> list1 = new LinkedList<>();
//
//        for(String color : colors)
//            list1.add(color);
//
//        String[] colors2 = {"gold","white","brown","blue","gray"};
//
//        List<String> list2 = new LinkedList<>();
//
//        for(String color:colors)
//            list2.add(color);
//
//        list1.addAll(list2);
//        list2 = null;
//        printList(list1);
//
//        convertToUpparcaseString(list1);
//        printList(list1);
//
//        System.out.printf("%nDeleting elements 4 to 6...");
//        removeItems(list1,4,7);
//        printList(list1);
//        printReversedList(list1);
//    }
//
//    private static void printList(List<String> list)
//    {
//        System.out.printf("%nlist:%n");
//
//        for(String color : list)
//            System.out.printf("%s",color);
//
//        System.out.println();
//    }
//
//    private static void convertToUpparcaseString(List<String> list)
//    {
//        ListIterator<String> iterator = list.listIterator();
//
//        while (iterator.hasNext())
//        {
//            String color = iterator.next();
//            iterator.set(color.toUpperCase());
//
//        }
//    }
//
//    private static void removeItems(List<String> list,int start,int end)
//    {
//        list.subList(start,end).clear();
//    }
//
//    private static void printReversedList(List<String> list)
//    {
//        ListIterator<String> iterator = list.listIterator(list.size());
//
//        System.out.printf("%nReversed List:%n");
//
//        while (iterator.hasPrevious())
//        {
//            System.out.printf("%s",iterator.previous());
//        }
//    }
//}

//class UsingToArray
//{
//    public static void main(String[] args)
//    {
//        String[] colors = {"black","blue","yellow"};
//
//        LinkedList<String> links = new LinkedList<>(Arrays.asList(colors));
//
//        links.addLast("red");
//        links.add("pink");
//        links.add(3,"green");
//        links.addFirst("cyan");
//
//        colors = links.toArray(new String[]);
//
//        System.out.println("colors: ");
//
//        for(String color : colors)
//            System.out.println(color);
//
//    }
//}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
    Satır 17, bağlantılardan bir String dizisi almak için interface List'in toArray yöntemini çağırır.
    Dizi, listenin öğelerinin bir kopyasıdır ve dizinin içeriğini değiştirerek listeyi değiştirmez.
    method toArray öğesine geçirilen dizi, method toArray öğesinin döndürmesini istediğiniz türdendir.
    Bu dizideki öğelerin sayısı LinkedList'teki öğe sayısından büyük veya ona eşitse, toArray listenin öğelerini dizi bağımsız değişkenine kopyalar ve bu diziyi döndürür.
    LinkedList, dizide toArray'e geçirilen öğe sayısından daha fazla öğeye sahipse, toArray bağımsız değişken olarak aldığı aynı türde yeni bir dizi ayırır, listenin öğelerini yeni diziye kopyalar ve yeni diziyi döndürür.

    ToArray’ın bağımsız değişkeni olarak verileri içeren bir diziyi geçmek mantık hatalarına neden olabilir.
    Dizinin öğe sayısı, listedeki toArray'ın çağrıldığı öğelerin sayısından daha küçükse, dizi argümanının öğelerini korumadan listenin öğelerini depolamak için yeni bir dizi tahsis edilir.
    Dizinin öğe sayısı listedeki öğelerin sayısından daha büyükse, dizinin öğelerinin (Dizin Dizininden başlayarak) listenin öğeleriyle birlikte yazılır.
    Dizinin geri kalanının ilk öğesi, listenin sonunu belirlemek için null olarak ayarlanmıştır.


---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Collection Methods

   Koleksiyon çerçeve yöntemleri polimorfiktir.
   Yani, her biri temel uygulamalardan bağımsız olarak belirli arayüzleri uygulayan nesneler üzerinde çalışabilir.

   sort: Sorts the elements of a List.
   binarysearch = Bölüm 7.15'te tanıttığımız ve Bölüm 19.4'te ayrıntılı olarak tartıştığımız etkili ikili arama algoritmasını kullanarak bir listedeki bir nesneyi bulur.
   reverse Reverses the elements of a List.
   shuffle Randomly orders a List’s elements.
   fill : her liste öğesini belirtilen bir nesneye başvuracak şekilde ayarlar.
   copy Copies references from one List into another.
   min Returns the smallest element in a Collection.
   max Returns the largest element in a Collection.
   addAll Appends all elements in an array to a Collection.
   frequency Calculates how many collection elements are equal to the specified element.
   disjoint Determines whether two collections have no elements in common.
---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
            Method Sort

    Yöntem sıralama, bir Listenin öğelerini sıralar.
    Öğelerin türü, karşılaştırılabilir arabirimi uygulamalıdır.
    Sıra, bir compareTo yöntemiyle uygulanan öğelerin türünün doğal sırasına göre belirlenir.
    Örneğin, sayısal değerler için doğal sıra cending sırası gibidir ve Dizeler için doğal sıralama sözlüksel sıralamalarına dayanır (Bölüm 14.3).
    Yöntem compareTo, Karşılaştırılabilir arabiriminde bildirilir ve bazen doğal karşılaştırma yöntemi olarak adlandırılır.
    Sıralama çağrısı, ikinci bir bağımsız değişken olarak, öğelerin alternatif bir sıralamasını belirleyen bir Comparator nesnesi belirtebilir.

    Azalan Düzende Sıralama Şekil 16.7, Şekil 16.6'da kullanılan dizelerin aynı listesini azalan düzende sıralar.
    Örnek, bir Koleksiyonun öğelerini farklı bir sırada sıralamak için kullanılan Karşılaştırıcı arabirimini tanıtır.
    Satır 16, Listeyi azalan düzende sıralamak için Koleksiyonlar'ın yöntem sıralamasını çağırır.
    Statik Collections yöntemi reverseOrder, koleksiyonun öğelerini ters sırada sıralayan bir Comparator nesnesi döndürür.
    Sıralanan koleksiyon bir List olduğundan<String>, reverseOrder bir Karşılaştırıcı döndürür<String>.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/


//class Sort2
//{
//    public static void main(String[] args)
//    {
//        String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
//
//        List<String> list = Arrays.asList(suits);
//        System.out.printf("Unsorted array elements: %s%n",list);
//
//        Collections.sort(list,Collections.reverseOrder());
//        System.out.printf("Sorted list elements: %s%n",list);
//
//    }
//}

//Unsorted array elements: [Hearts, Diamonds, Clubs, Spades]
//Sorted list elements: [Spades, Hearts, Diamonds, Clubs]


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
                Method shuffle
    Yöntem karıştırma, bir Listenin öğelerini rastgele sıralar.
    Bölüm 7, bir kart destesini bir döngü ile karıştıran bir kart karıştırma ve dağıtma simülasyonu sundu.
    Şekil 16.10, bir kart oyunu simülatöründe kullanılabilecek bir Kart nesneleri destesini karıştırmak için yöntem karıştırmayı kullanır.
    Sınıf Kartı (satır 8-32), bir kart destesindeki bir kartı temsil eder. Her Kartın bir yüzü ve bir takım elbisesi vardır.
    9-11 arası satırlar, sırasıyla kartın yüzünü ve takımını temsil eden iki enum türünü (Yüz ve Takım Elbise) bildirir.
    Yöntem toString (satır 29-31), ' dizesiyle ayrılmış Kartın yüzünü ve takımını içeren bir String döndürür.
    Bir enum sabiti bir String'e dönüştürüldüğünde, sabitin tanımlayıcısı String gösterimi olarak kullanılır.
    Normalde enum sabitleri için tüm büyük harfleri kullanırdık.
    Bu örnekte, kartın yüz ve takım elbise için ilk büyük harflerle görüntülenmesini istediğimiz için her enum sabitinin yalnızca ilk harfi için büyük harf kullanmayı seçtik (örneğin, 'Maça Ası').


---------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Card
//{
//    enum Face{Ace,Deuce,Three,Four,Five,Six,Seven,Eight,Nine,Ten,Jack
//        ,Queen,King}
//
//    enum Suit{Clubs,Diamonds,Hearts,Spades}
//
//    private final Face face;
//    private final  Suit suit;
//
//    public Card(Face face,Suit suit)
//    {
//        this.face = face;
//        this.suit = suit;
//    }
//
//    public Face getFace() {return face;}
//
//    public Suit getSuit() {return suit;}
//
//    public String toString()
//    {
//        return String.format("%s of %s", face,suit);
//    }
//}
//
//class DeckOfCards
//{
//    private List<Card> list;
//
//    DeckOfCards()
//    {
//        Card[] deck = new Card[52];
//        int count = 0;
//
//        for(Card.Suit suit : Card.Suit.values())
//        {
//            for(Card.Face face: Card.Face.values())
//            {
//                deck[count] = new Card(face,suit);
//                ++count;
//            }
//        }
//
//        list = Arrays.asList(deck);
//        Collections.shuffle(list);
//    }
//
//    public void printCards()
//    {
//        for(int i = 0; i < list.size();i++)
//        {
//            System.out.printf("%-19s%s",list.get(i),((i+1) % 4 == 0) ? System.lineSeparator():"");
//        }
//    }
//
//    public static void main(String[] args) {
//        DeckOfCards cards = new DeckOfCards();
//        cards.printCards();
//    }
//
//}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Kod Açıklaması

   44-49. satırlar, deste dizisini benzersiz face ve suit kombinasyonlarına sahip kartlarla doldurur.
   Hem Yüz hem de suit, sınıf Kartının genel statik enum türleridir.
   Bu enum türlerini sınıf Kartı dışında kullanmak için, her bir enum'un tür adını, içinde bulunduğu sınıfın adıyla (yani, Kart) ve bir nokta (.) ayırıcısıyla nitelemeniz gerekir.
   Bu nedenle, satır 44 ve 45, for deyimlerinin kontrol değişkenlerini bildirmek için Card.Suit ve Card.Face kullanır.

   Bir enum türünün yöntem değerlerinin, enum türünün tüm sabitlerini içeren bir dizi döndürdüğünü hatırlayın.
   44-49 satırları, 52 yeni Kart oluşturmak için ifadeler için geliştirilmiş olarak kullanılır.

   Karıştırma, Kartları karıştırmak için statik Koleksiyonlar yöntemini karıştırmayı çağıran 52. satırda gerçekleşir.
   Yöntem karıştırma bir List bağımsız değişkeni gerektirir, bu nedenle karıştırmadan önce dizinin bir Listview'unu elde etmeliyiz.
   Satır 51, deste dizisinin List görünümünü almak için statik yöntemi List of class Arrays olarak çağırır.

   printCards yöntemi (satır 56-62) kart destesini dört sütunda görüntüler.
   Döngünün her yinelemesinde, 59-60 satırları, 19 karakterlik bir alanda sola dayalı bir kart çıkışı ve ardından şimdiye kadar çıkarılan kart sayısına bağlı olarak yeni bir satır veya boş bir dize çıkarır.
   Kart sayısı 4'e bölünebiliyorsa, yeni bir satır çıkarılır; aksi takdirde, boş dize çıktısı alınır.
   60. satırın, platformdan bağımsız yeni satır karakterinin her dört karttan sonra çıktı almasını sağlamak için Sistem yöntemi lineSeparator'ı kullandığını unutmayın.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Methods Reverse,Fill,copy,max and min

    Sınıf koleksiyonları, listeleri tersine çevirmek, doldurma ve kopyalama için yöntemler sağlar.
    Koleksiyonlar Yöntemi, bir listedeki öğelerin sırasını tersine çevirir ve Yöntem Dolgu, bir listedeki öğeleri belirli bir değere sahip üzerine yazar.
    Dolgu işlemi bir listenin yeniden başlatılması için kullanışlıdır.
    Yöntem kopyası iki argümanı bir hedef listesi ve bir kaynak listesi alır.
    Kaynak listesindeki her öğe hedef listeye kopyalanır.
    Hedef listesi en azından kaynak listesi kadar uzun olmalıdır; Aksi takdirde, bir indexoutofboundSException oluşur.
    Hedef listesi daha uzunsa, üzerine yazılmayan öğeler değişmez.

    Şimdiye kadar gördüğümüz her yöntem listelerde çalışır.
    Yöntemler Min ve Max her biri herhangi bir koleksiyonda çalışır.
    Yöntem Min, bir koleksiyondaki en küçük öğeyi döndürür ve Metod Max, bir koleksiyondaki en büyük öğeyi döndürür.
---------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Algorithms1
//{
//    public static void main(String[] args)
//    {
//        Character[] letters = {'P','C','M'};
//        List<Character> list = Arrays.asList(letters);
//        System.out.println("list contains:");
//        output(list);
//
//        Collections.reverse(list);
//        System.out.printf("%nAfter calling reverse, list contains:%n");
//        output(list);
//
//        Character[] lettersCopy = new Character[3];
//        List<Character> copyList = Arrays.asList(lettersCopy);
//
//        Collections.copy(copyList,list);
//        System.out.printf("%nAfter copying, copyList contains:%n");
//        output(copyList);
//
//        Collections.fill(list,'R');
//        System.out.printf("%nAfter calling fill, list contains:%n");
//        output(list);
//    }
//
//    private static void output(List<Character> listRef)
//    {
//        System.out.print("The list is :");
//
//        for(Character element:listRef)
//            System.out.printf("%s ",element);
//
//        System.out.printf("%nMax: %s", Collections.max(listRef));
//        System.out.printf(" Min: %s%n",Collections.min(listRef));
//    }
//}


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Method binarySearch

    Bu yöntem, bir listedeki bir nesneyi bulur (örneğin, bir bağlantı listesi veya bir ArrayList). Nesne bulunursa, dizini döndürülür.
    Nesne bulunmazsa, BinarySearch negatif bir değer döndürür.
    Yöntem BinarySearch, önce ekleme noktasını hesaplayarak ve işaretini negatif hale getirerek bu negatif değeri belirler.
    Daha sonra, ikili arhear, dönüş değerini elde etmek için 1 ekleme noktasından 1'i çıkarır, bu da yöntem ikili aramanın yalnızca nesne bulunursa pozitif sayıları (> = 0) döndürdüğünü garanti eder.
    Listedeki birden fazla öğe arama anahtarıyla eşleşiyorsa, önce hangisinin bulunacağının garantisi yoktur.
    Şekil 16.12, bir dizi dizisinde bir dizi dizeyi aramak için yöntem ikili aracı kullanır.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/
//class BinarySearchTest
//{
//    public static void main(String[] args)
//    {
//        String[] colors = {"red","white","blue","black"};
//
//        List<String> list = new ArrayList<>(Arrays.asList(colors));
//
//        Collections.sort(list);
//        System.out.printf("Sorted ArrayList: %s%n", list);
//
//        printSearchResults(list,"black");
//        printSearchResults(list,"red");
//        printSearchResults(list,"pink");
//        printSearchResults(list,"aqua");
//
//
//    }
//    private static void printSearchResults(List<String> list, String key)
//    {
//        System.out.printf("%nSearching for: %s%n",key);
//        int result = Collections.binarySearch(list,key);
//
//        if(result >= 0)
//            System.out.printf("Found at index %d%n",result);
//        else
//            System.out.printf("Not found (%d)%n",result);
//    }
//}


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Methods addAll, frequency and disjoint

    Sınıf koleksiyonları ayrıca addAll, frequency ve disjoint yöntemleri sağlar.
    Koleksiyonlar Yöntemi AddAll, yeni öğeleri eklemek için bir koleksiyon ve öğelerin Seri'de olmasını sağlayan bir diziyi (veya değişken uzunlukta argüman listesi) iki argüman alır.
    Koleksiyon Yöntemi frequeny iki argüman alır Bir koleksiyon ve koleksiyonda aranacak bir nesne.
    Yöntem Frekansı, ikinci argümanın koleksiyonda kaç kez göründüğünü döndürür.
    Koleksiyonlar Yöntemi Disjoint iki koleksiyon alır ve ortak öğeleri yoksa doğru döner.
    Şekil 16.13, yöntemlerin, frekans ve ayrık yöntemlerin kullanımını göstermektedir.
---------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class Algorithms2
//{
//    public static void main(String[] args)
//    {
//        String[] colors = {"Red","white","yellow","blue"};
//        List<String> list1 = Arrays.asList(colors);
//        ArrayList<String> list2 = new ArrayList<>();
//
//        list2.add("black");
//        list2.add("red");
//        list2.add("green");
//
//        System.out.printf("Before addAll, list2 contains");
//
//        for(String s: list2)
//            System.out.printf("%s", s);
//
//        Collections.addAll(list2,colors);
//
//        System.out.printf("%nAfter addAll, list2 contains: ");
//
//        for(String s: list2)
//            System.out.printf("%s", s);
//
//        int frequency = Collections.frequency(list2,"red");
//        System.out.printf("%nFrequency of red in list2: %d%n",frequency);
//
//        boolean disjoint = Collections.disjoint(list1,list2);
//
//        System.out.printf("List1 and List2 %s elements in common%n",(disjoint ? "do not have":"have"));
//
//    }
//}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Class PriorityQueue and Interface Queue

    Bir kuyruğun genellikle bir bekleme hattını temsil eden bir koleksiyon olduğunu, eklemelerin kuyruğun arkasına yapıldığını ve silmelerin önden yapıldığını unutmayın.
    Bölüm 21.6'da, bir kuyruk veri yapısını tartışacağız ve uygulayacağız. Bölüm 23, Eşzamanlılık'ta, eşzamanlı kuyruklar kullanacağız.
    Bu bölümde, java.util paketinden Java'nın Kuyruk arabirimini ve PriorityQueue sınıfını araştırıyoruz.
    Arabirim Kuyruğu, arabirim Koleksiyonu'nu genişletir ve bir kuyruğa öğe eklemek, kaldırmak ve denetlemek için ek işlemler sağlar.
    Kuyruk arabirimini uygulayan PriorityQueue, öğeleri Comparable elements'in compareTo yöntemi veya oluşturucuya sağlanan bir Comparator nesnesi tarafından belirtilen doğal sıralamalarına göre sıralar.

    Class PriorityQueue, temel alınan veri yapısına sıralanmış sırayla eklemelere ve temel alınan veri yapısının önünden silmelere olanak tanıyan işlevler sağlar.
    Bir PriorityQueue'ya öğe eklerken, öğeler öncelik sırasına göre eklenir, böylece en yüksek öncelikli öğe (yani, en büyük değer) PriorityQueue'dan kaldırılan ilk öğe olur.

    Ortak PriorityQueue işlemleri, öncelik sırasına göre uygun konuma bir öğe eklemek için offer().
    öncelik kuyruğunun en yüksek öncelikli öğesini (yani, kuyruğun başı) kaldırmak için pool(),
    öncelik kuyruğunun en yüksek öncelikli öğesine bir başvuru almak için peek() (bu öğeyi kaldırmadan),
    öncelik kuyruğundaki öğelerin sayısını almak için size() öncelik kuyruğundaki tüm öğeleri ve boyutu kaldırmak için clear().

---------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class PriorityQueueTest
//{
//    public static void main(String[] args)
//    {
//        PriorityQueue<Double> queue = new PriorityQueue<>();
//
//        queue.offer(3.2);
//        queue.offer(9.8);
//        queue.offer(5.4);
//
//        System.out.print(queue);
//
//        while (queue.size() > 0)
//        {
//            System.out.printf("%.1f ",queue.peek());
//            queue.poll();
//        }
//
//    }
//}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
            Sets

   Set, benzersiz öğelerden oluşan bir koleksiyondur (yani, kopya yok). Koleksiyonlar çerçevesi, HashSet ve TreeSet dahil olmak üzere çeşitli Set uygulamaları içerir.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class SetTest
//{
//    public static void main(String[] args)
//    {
//        String[] colors = {"red","white","blue","green","gray",
//                            "orange","tan","white","cyan","peach","gray","orange"};
//        List<String> list = Arrays.asList(colors);
//        System.out.printf("List: %s%n",list);
//
//        printNonDuplicates(list);
//
//    }
//
//    private static void printNonDuplicates(Collection<String> values)
//    {
//        Set<String> set = new HashSet<>(values);
//
//        System.out.printf("%nNonduplicates are :");
//
//        for(String value : set)
//            System.out.printf("%s ",value);
//
//        System.out.println();
//    }
//}


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Sorted Sets


   Koleksiyonlar çerçevesi ayrıca, öğelerini öğelerin doğal düzeninde (örneğin, sayılar artan sıradadır) veya bir Karşılaştırıcı tarafından belirtilen bir sırada tutan kümeler için SortedSet arabirimini (Set'i genişletir) içerir.
   Sınıf TreeSet SortedSet uygular.
   Şekil 16.16'daki program Strings'i bir TreeSet'e yerleştirir. Dizeler, TreeSet'e eklendikçe sıralanır.
   Bu örnekte, bir programın koleksiyonun bir bölümünü görüntülemesini sağlayan range view yöntemleri de gösterilmektedir.
---------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class SortedSetTest
//{
//    public static void main(String[] args)
//    {
//        String[] colors = {"yellow","green","black","tan","grey","white","orange","red","green"};
//
//        SortedSet<String> tree = new TreeSet<>(Arrays.asList(colors));
//
//        System.out.print("sorted set: ");
//        printSet(tree);
//
//        System.out.print("headSet(\"orange\"): ");
//        printSet(tree.headSet("orange"));
//
//        System.out.print("tailSet (\"orange\"): ");
//        printSet(tree.tailSet("orange"));
//
//        System.out.printf("first: %s%n",tree.first());
//        System.out.printf("last : %s%n",tree.last());
//    }
//
//    private static void printSet(SortedSet<String> set)
//    {
//        for(String s : set)
//        {
//            System.out.printf("%s ",s);
//        }
//
//        System.out.println();
//    }
//}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
            Maps

     Map anahtarları değerlerle ilişkilendirir.
     Bir Mapteki anahtarların benzersiz olması gerekir, ancak ilişkili değerlerin benzersiz olması gerekmez.
     Bir Map hem benzersiz anahtarlar hem de benzersiz değerler içeriyorsa, bire bir eşleme uyguladığı söylenir.
     Yalnızca anahtarlar benzersizse, Eşleme'nin çoka bir eşleme uyguladığı söylenir, birçok anahtar tek bir değere eşlenebilir.

     Map, Maplerin anahtarlar ve değerler içermesi nedeniyle Setler'den farklıdır, oysa Setler yalnızca değerleri içerir.
     Arabirim Eşlemesi'ni uygulayan iki sınıf HashMap ve TreeMap'tir.
     HashMaps öğeleri karma tablolarda(hash tables), TreeMaps ise ağaçlarda depolar.
     Bu bölümde karma tablolar ele alınmakta ve anahtar-değer çiftlerini depolamak için HashMap kullanan bir örnek sağlanmaktadır.
     Interface SortedMap, Map'i genişletir ve anahtarlarını sıralanmış sırada tutar
     - öğelerin doğal sırası veya bir Karşılaştırıcı tarafından belirtilen bir sıra.
     Sınıf TreeMap SortedMap uygular.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*---------------------------------------------------------------------------------------------------------------------------------------------------------
            Map Implementation with Hash Tables

    Bir program nesneler oluşturduğunda, bunları verimli bir şekilde depolaması ve alması gerekebilir.
    Verilerinizin bazı yönleri sayısal bir anahtar değeriyle doğrudan eşleşiyorsa ve anahtarlar benzersiz ve sıkı bir şekilde paketlenmişse, bilgilerin arraylerde depolanması ve alınması, etkilidir.
    Dokuz basamaklı sosyal güvenlik numaralarına sahip 100 çalışanınız varsa ve sosyal güvenlik numarasını anahtar olarak kullanarak çalışan verilerini depolamak ve almak istiyorsanız, görev, Sosyal Güvenlik İdaresi'nin web sitesine göre dokuz basamaklı Sosyal Güvenlik numaralarının 001-899 (666 hariç) ile başlaması gerektiğinden, 800 milyondan fazla öğeye sahip bir dizi gerektirecektir.

    Bu, sosyal güvenlik numaralarını anahtar olarak kullanan hemen hemen tüm uygulamalar için pratik değildir.
    Bu kadar büyük bir diziye sahip bir program, yalnızca sosyal güvenlik numarasını dizi indexi olarak kullanarak çalışan kayıtlarını depolamak ve almak için yüksek performans elde edebilir.

    Çok sayıda uygulamada bu sorun vardır, yani anahtarlar yanlış türde (örneğin, dizi alt simgelerine karşılık gelen pozitif tamsayılar değil) ya da doğru türdedir, ancak seyrek olarak geniş bir aralığa yayılmıştır.
    İhtiyaç duyulan şey, sosyal güvenlik numaraları, envanter parça numaraları ve benzerleri gibi anahtarları benzersiz dizi endekslerine dönüştürmek için yüksek hızlı bir şemadır.
    Ardından, bir uygulamanın bir şey depolaması gerektiğinde, şema uygulamanın anahtarını hızla bir dizine dönüştürebilir ve kayıt dizideki bu yuvada depolanabilir.
    Alma işlemi aynı şekilde gerçekleştirilir: Uygulama, veri kaydı almak istediği bir anahtara sahip olduğunda, uygulama dönüştürmeyi yalnızca anahtara uygular, bu da verilerin depolandığı ve alındığı dizi dizinini üretir.

    Burada tanımladığımız şema, hashing adı verilen bir tekniğin temelidir.
    Neden isim?
    Bir anahtarı bir dizi indexine dönüştürdüğümüzde, bitleri kelimenin tam anlamıyla karıştırırız, bir tür \"karmakarışık\" veya karma sayı oluştururuz.
    Sayının aslında belirli bir veri kaydının depolanması ve alınmasındaki kullanışlılığının ötesinde gerçek bir önemi yoktur.

    Şemadaki bir aksaklığa çarpışma denir – bu, iki farklı anahtar dizideki aynı hücreye (veya öğeye) \"hash\" girdiğinde meydana gelir.
    İki değeri aynı alanda depolayamayız, bu nedenle belirli bir dizi dizinine karma oluşturan ilk değerin ötesindeki tüm değerler için alternatif bir ev bulmamız gerekir.
    Bunu yapmak için birçok şema var.
    Bunlardan biri \"tekrar hash\" etmektir (yani, dizideki bir sonraki aday hücreyi sağlamak için anahtara başka bir karma dönüşümü uygulamak).

    Karma işlemi, değerleri tablo boyunca dağıtmak için tasarlanmıştır, bu nedenle varsayım, kullanılabilir bir hücrenin yalnızca birkaç karma ile bulunacağıdır.

    Karma işlemi, değerleri diğer bir şemaya dağıtmak için tasarlanmıştır ve ilk aday hücreyi bulmak için bir karma kullanır.
    Bu hücre işgal edilmişse, kullanılabilir bir hücre bulunana kadar ardışık hücreler sırayla aranır.
    Alma işlemi aynı şekilde çalışır: İlk konumu belirlemek ve istenen verileri içerip içermediğini kontrol etmek için anahtara bir kez karma oluşturma işlemi uygulanır.
    Varsa, arama tamamlanır. Aksi takdirde, ardışık hücreler istenen veriler bulunana kadar doğrusal olarak aranır.

    Karma tablo çakışmalarının en popüler çözümü, tablonun her hücresinin bir karma \"demet\", tipik olarak o hücreye karma oluşturan tüm anahtar-değer çiftlerinin bağlantılı bir listesi olmasını sağlamaktır.
    Bu, Java'nın HashMap sınıfının (java.util paketinden) kullandığı çözümdür.
    HashMap, Map arayüzünü uygular.

    Karma tablonun yük faktörü, karma şemalarının performansını etkiler.
    Yük faktörü, karma tablosundaki işgal edilen hücre sayısının karma tablodaki toplam hücre sayısına oranıdır.
    Bu oran 1.0'a ne kadar yaklaşırsa, çarpışma olasılığı da o kadar artar.

    Bir karma tablodaki yük faktörü, bellek alanı\/yürütme zamanı ödünleşiminin klasik bir örneğidir: Yük faktörünü artırarak daha iyi bellek kullanımı elde ederiz, ancak artan karma çakışmaları nedeniyle program daha yavaş çalışır.
    Yük faktörünü azaltarak, karma çakışmalarının azalması nedeniyle daha iyi program hızı elde ederiz, ancak karma tablosunun daha büyük bir kısmı boş kaldığı için daha zayıf bellek kullanımı elde ederiz.

    Bilgisayar bilimi öğrencileri, \"Veri Yapıları\" ve \"Algoritmalar\" adlı derslerde karma şemaları inceler.
    Class HashMap, klasik bir yeniden kullanım örneği olan karma tablo mekanizmalarını uygulamak zorunda kalmadan karma kullanımı kullanmanıza olanak tanır. Bu kavram, nesne yönelimli programlama çalışmamızda son derece önemlidir.
    Önceki bölümlerde tartışıldığı gibi, sınıflar karmaşıklığı (yani uygulama ayrıntılarını) kapsüller ve gizler ve kullanıcı dostu arayüzler sunar. Bu tür davranışları sergilemek için sınıfları uygun şekilde hazırlamak, nesne yönelimli programlama alanındaki en değerli becerilerden biridir.
    Şekil 16.17, bir dizedeki her kelimenin oluşum sayısını saymak için bir HashMap kullanır.

    Satır 12, varsayılan başlangıç kapasitesine (16 öğe) ve varsayılan yük faktörüne (0,75) sahip boş bir HashMap oluşturur
    - bu varsayılanlar HashMap uygulamasında yerleşiktir.
    HashMap'teki işgal edilen yuvaların sayısı, yük faktörünün kapasite katlarından daha fazla olduğunda, kapasite otomatik olarak iki katına çıkar.
    HashMap, iki tür bağımsız değişken alan genel bir sınıftır: anahtar türü (örneğin, String) ve değer türü (örneğin, Tamsayı). Genel bir sınıfa geçirilen type bağımsız değişkenlerinin başvuru türleri olması gerektiğini, bu nedenle ikinci tür bağımsız değişkeninin int değil Integer olduğunu unutmayın.
---------------------------------------------------------------------------------------------------------------------------------------------------------*/

//class WordTypeCount
//{
//    public static void main(String[] args)
//    {
//        Map<String,Integer> myMap = new HashMap<>();
//
//        createMap(myMap);
//        displayMap(myMap);
//    }
//
//    private static void createMap(Map<String,Integer> map)
//    {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter a string: ");
//        String input = scanner.nextLine();
//
//        String[] tokens = input.split(" ");
//
//        for(String token : tokens)
//        {
//            String word = token.toLowerCase();
//
//            if(map.containsKey(word))
//            {
//                int count = map.get(word);
//                map.put(word,count+1);
//            }
//            else
//                map.put(word,1);
//        }
//    }
//
//    private static void displayMap(Map<String, Integer> map)
//    {
//        Set<String> keys = map.keySet();
//
//        TreeSet<String> sortedKeys = new TreeSet<>(keys);
//
//        System.out.printf("%nMap contains: %nKey \t\tValue%n");
//
//        for(String key : sortedKeys)
//        {
//            System.out.printf("%-10s%10s%n", key,map.get(key));
//        }
//
//        System.out.printf("%nsize: %d%nisEmpty: %b%n",map.size(),map.isEmpty());
//    }
//}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------
    Her zaman bir Map ile değişmez anahtarlar kullanın.
    Anahtar, karşılık gelen değerin nereye yerleştirileceğini belirler.
    Anahtar ekleme işleminden bu yana değiştiyse, daha sonra bu değeri almaya çalıştığınızda bulunamayabilir.
    Bu bölümün örneklerinde, anahtar olarak stringleri kullanıyoruz ve stringler değişmez.
---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
            Synchronized Collections

    Bölüm 23'te, çoklu iş parçacığını tartışıyoruz.
    Koleksiyonlar çerçevesindeki koleksiyonlar varsayılan olarak eşitlenmediğinden, çoklu iş parçacığı gerekmediğinde verimli bir şekilde çalışabilirler.
    Bununla birlikte, senkronize edilmedikleri için, birden fazla iş parçacığı tarafından bir Koleksiyona eşzamanlı erişim, Bölüm 23'te gösterdiğimiz gibi, belirsiz sonuçlara veya önemli hatalara neden olabilir.
    Olası iş parçacığı sorunlarını önlemek için, birden çok iş parçacığı tarafından erişilebilen koleksiyonlar için eşitleme sarmalayıcıları sağlanır.

    List<String> list1 = new ArrayList<>();
    List<String> list2 = Collections.synchronizedList(list1);

    <T> Collection<T> synchronizedCollection(Collection<T> c)

    <T> List<T> synchronizedList(List<T> aList)
    <T> Set<T> synchronizedSet(Set<T> s)
    <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s)
    <K, V> Map<K, V> synchronizedMap(Map<K, V> m)
    <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m)
---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Abstract Implementations

   Koleksiyonlar çerçevesi, eksiksiz özelleştirilmiş uygulamaları hızlı bir şekilde \"ayrıntılandırabileceğiniz\" Koleksiyon arayüzlerinin çeşitli soyut uygulamalarını sağlar.
   Bu soyut uygulamalar, AbstractCollection adı verilen ince bir Koleksiyon uygulamasını, AbstractList adlı öğelerine dizi benzeri erişime izin veren bir List uygulamasını, AbstractMap adlı bir Harita uygulamasını, AbstractSequentialList adı verilen öğelerine sıralı erişime (baştan sona) izin veren bir List uygulamasını, AbstractSet adlı bir Set uygulamasını ve AbstractQueue adlı bir Queue uygulamasını içerir.
   http:\/\/ docs.oracle.com\/javase\/8\/docs\/api\/java\/util\/package-summary.html'da bu sınıflar hakkında daha fazla bilgi edinebilirsiniz. Özel bir uygulama yazmak için, gereksinimlerinizi en iyi karşılayan soyut uygulamayı genişletebilir, sınıfın soyut yöntemlerinin her birini uygulayabilir ve sınıfın somut yöntemlerini gerektiği gibi geçersiz kılabilirsiniz.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
        Java SE9: Convenience Factory Methods For Immutable Collections!

    Java SE 9, Liste, Set ve Eşleme arabirimlerine küçük değişmez koleksiyonlar oluşturmanıza olanak tanıyan yeni statik kolaylık fabrika yöntemleri ekler; oluşturulduktan sonra değiştirilemezler (JEP 269).
    Bölüm 10.12'de fabrika yöntemlerini tanıttık - fabrika kelimesi bu yöntemlerin nesneler oluşturduğunu gösterir.
    Kullanışlıdırlar, çünkü öğeleri bağımsız değişkenler olarak koleksiyonu oluşturan ve öğeleri sizin için koleksiyona ekleyen bir kolaylık fabrikası yöntemine geçirirsiniz.

    Bölüm 16.12'de tartıştığımız değiştirilemez sarmalayıcılar tarafından döndürülen koleksiyonlar, değiştirilebilir koleksiyonların değişmez görünümlerini oluşturur - orijinal değiştirilebilir koleksiyona yapılan başvuru, koleksiyonu değiştirmek için hala kullanılabilir.
    Bunun yerine, kolaylık fabrikası yöntemleri, gerçekten değişmez ve küçük koleksiyonları depolamak için optimize edilmiş özel koleksiyon nesneleri döndürür.
    Bölüm 17 ve 23'te, lambda'ları ve akışları değişmez varlıklarla kullanmanın, günümüzün çok çekirdekli mimarilerinde daha verimli çalışacak \"paralelleştirilebilir\" kod oluşturmanıza nasıl yardımcı olabileceğini açıklıyoruz.
    Şekil 16.20, bir Liste, bir Set ve iki Map için bu kullanışlı fabrika yöntemlerini göstermektedir.


    List, Set veya Map convenience factory yöntemleri tarafından döndürülen bir koleksiyonu değiştirmeye çalışan herhangi bir yöntemi çağırmak UnsupportedOperationException ile sonuçlanır.

    Java'da, koleksiyon öğeleri her zaman nesnelere başvurur. Değişmez bir koleksiyon tarafından başvurulan nesneler yine de değiştirilebilir olabilir.
---------------------------------------------------------------------------------------------------------------------------------------------------------*/


//class FactoryMethods
//{
//    public static void main(String[] args) {
//        List<String> colorList = List.of("red", "orange", "yellow", "green", "blue", "indigo", "violet");
//        System.out.printf("colorSet: %s%n%n", colorList);
//
//        Set<String> colorSet = Set.of("red", "orange", "yellow",
//                "green", "blue", "indigo", "violet");
//
//        System.out.printf("colorSet: %s%n%n", colorSet);
//
//        Map<String, Integer> dayMap = Map.of("Monday", 1, "Tuesday", 2,
//                "Wednesday", 3, "Thursday", 4, "Friday", 5, "Saturday", 6,
//                "Sunday", 7);
//
//        Map<String, Integer> daysPerMonthMap = Map.ofEntries(
//                Map.entry("January", 31),
//                Map.entry("February", 28),
//                Map.entry("March", 31),
//                Map.entry("April", 30),
//                Map.entry("May", 31),
//                Map.entry("June", 30),
//                Map.entry("July", 31),
//                Map.entry("August", 31),
//                Map.entry("September", 30),
//                Map.entry("October", 31),
//                Map.entry("November", 30),
//                Map.entry("December", 31)
//        );
//        System.out.printf("monthMap: %s%n", daysPerMonthMap);
//
//    }
//
//}


/*---------------------------------------------------------------------------------------------------------------------------------------------------------
    factory metot yöntemleri tarafından döndürülen koleksiyonlar,
    en fazla 10 öğe (List ve Setler için) veya anahtar-değer çiftleri (Map için) için optimize edilmiştir.
    Yöntem, sıfır ila 10 element için aşırı yüklenmiştir, çünkü araştırmalar, bunların değişmez koleksiyonlara ihtiyaç duyulan vakaların büyük çoğunluğunu ele aldığını göstermiştir.
    Sıfır ila 10 öğe için aşırı yüklenme yöntemi, değişken uzunluktaki bağımsız değişken listelerinin işlenmesinin ekstra yükünü ortadan kaldırır.
    Bu, küçük değişmez koleksiyonlar oluşturan uygulamaların performansını artırır.

    Factory metot yöntemleri tarafından döndürülen koleksiyonların null değerler içermesine izin verilmez—bu yöntemler, herhangi bir bağımsız değişken null ise bir NullPointerException atar.


    Set'in bağımsız değişkenlerinden herhangi biri duplicate ediliyorsa IllegalArgumentException atar.

    Map'in ve ofEntries yöntemlerinin her biri, anahtarlardan herhangi biri duplicate oluyorsa bir IllegalArgumentException atar
---------------------------------------------------------------------------------------------------------------------------------------------------------*/



/*---------------------------------------------------------------------------------------------------------------------------------------------------------
                Wrap-up


   • Java koleksiyonları çerçevesi, önceden oluşturulmuş veri yapıları ve bunları işlemek için yöntemler sağlar.

   • Koleksiyon, diğer nesnelere referanslar tutabilen bir nesnedir.

   • Koleksiyonlar çerçevesinin sınıfları ve arayüzleri java.util paketindedir.

   • Interface Set ve List, bir koleksiyondaki nesneleri ekleme, temizleme ve karşılaştırma işlemlerini içeren Koleksiyonu genişletir (s. 706).

   • Tür sarmalayıcı sınıfları (örneğin, Integer, Double, Boolean), programcıların ilkel tip değerleri nesneler olarak işlemelerini sağlar (s. 707).
        Bu sınıfların nesneleri koleksiyonlarda kullanılabilir.

   • Kutulama (s. 707) ilkel bir değeri karşılık gelen tür sarmalayıcı sınıfının bir nesnesine dönüştürür.
   • Java kutu dönüştürme ve kutudan çıkarma dönüşümlerini otomatik olarak gerçekleştirir.

   • Koleksiyon yöntemi Iterator (s. 710), bir koleksiyonun iteratorunu (s. 708) elde eder.
   • Sınıf Koleksiyonları (s. 708), koleksiyonları işlemek için statik yöntemler sağlar.

   • Interface List, ArrayList ve LinkedList sınıfları tarafından uygulanır.
        ArrayList (s. 708) yeniden boyutlandırılabilir bir dizi uygulamasıdır.
        LinkedList (s. 708), bir List'in linkedlist uygulamasıdır.

   • Java, genel tür değişkenlerini ve nesnelerini bildiren ve oluşturan ifadelerdeki <> gösterimiyle tür çıkarımını destekler.
   • Iterator yöntemi hasNext (s. 710) bir Koleksiyonun başka bir öğe içerip içermediğini belirler.
        Sonraki yöntem Koleksiyon'daki bir sonraki nesneye bir başvuru döndürür ve Yineleyici'yi ilerletir.
   • Bir Liste (s. 714), yinelenen öğeler içerebilen sırayla bir öğeler koleksiyonudur.

   • Algoritmalar sıralama (s. 716), binarySearch, reverse (s. 722), shuffle (s. 720), fill (s. 722), kopyalama Listeler üzerinde çalışır.
        Minimum ve maksimum algoritmalar (s. 722) Koleksiyonlar üzerinde çalışır.

   • Algoritma addAll bir dizideki tüm öğeleri bir koleksiyona ekler (s. 725), frekans (s. 726) koleksiyondaki kaç öğenin belirtilen öğeye eşit olduğunu hesaplar ve disjoint (s. 726) iki koleksiyonun ortak öğeleri olup olmadığını belirler.
   • Minimum ve maksimum algoritmalar bir koleksiyondaki en küçük ve en büyük öğeleri bulur.
   • Comparator interface (s. 717), bir Koleksiyonun öğelerini doğal düzenlerinden farklı bir düzende sıralamak için bir araç sağlar.
   • Collections yöntemi reverseOrder (s. 717), bir koleksiyonun öğelerini ters sırada sıralamak için sort ile birlikte kullanılabilen bir Comparator nesnesi döndürür.
   • Algoritma shuffle (s. 720) bir Listenin öğelerini rastgele sıralar.
   • Algoritma binary-search (s. 724) sıralanmış bir Listede bir anahtar bulur.


   • Interface Queue (s. 727), interface collection genişletir ve bir queue'ya öğe eklemek, kaldırmak ve incelemek için ek işlemler sağlar.
   • PriorityQueue (s. 727) interface queue uygular ve öğeleri doğal sıralamalarına veya oluşturucuya sağlanan bir Karşılaştırıcı nesnesine göre sıralar.
   • PriorityQueue yöntem offer (s. 727), öncelik sırasına göre uygun konuma bir öğe ekler.
        Yöntem poll (s. 727),  kuyruğun en yüksek öncelikli öğesini kaldırır.
        Yöntem peek (peek),  kuyruğun en yüksek öncelikli öğesine bir başvuru alır.
        Yöntem clear (s. 727)  kuyruğun tüm öğeleri kaldırır.
        Yöntem size (s. 727) kuyruğun öğelerin sayısını alır.

   • Bir Set (s. 728), duplicate öğeler içermeyen sırasız bir Koleksiyondur.
        HashSet (s. 728) öğelerini bir karma tabloda depolar.
        TreeSet (s. 728) elemanlarını bir ağaçta saklar.
   • Interface SortedSet (s. 729) Set'i genişletir ve öğelerini sıralanmış düzende tutan bir set temsil eder. Sınıf TreeSet SortedSet uygular.
   • TreeSet yöntemi headSet (s. 730), belirtilen bir öğeden daha az olan öğeleri içeren bir TreeSet görünümü alır.
        TailSet yöntemi (s. 730), belirtilen bir öğeden büyük veya ona eşit öğeler içeren bir TreeSet görünümü alır.
        Bu görünümlerde yapılan tüm değişiklikler özgün TreeSet'te yapılır.

   • Mapler (s. 731) anahtar-değer çiftlerini depolar ve yinelenen anahtarlar içeremez.
        HashMaps (s. 731) öğeleri karma tabloda, TreeMaps (s. 731) ise öğeleri bir ağaçta depolar.
   • HashMap iki tür bağımsız değişken alır: anahtar türü ve değer türü.
   • HashMap method put (s. 734) bir HashMap'e anahtar-değer çifti ekler.
        Yöntem get (s. 734) belirtilen anahtarla ilişkili değeri bulur.
        isEmpty yöntemi (s. 734) haritanın boş olup olmadığını belirler.

   • HashMap yöntemi keySet (s. 734) anahtarların bir kümesini döndürür. Map yöntemi size (s. 734), Mapde ki anahtar-değer çiftlerinin sayısını döndürür.
   • Interface SortedMap (s. 731) Map'i genişletir ve anahtarlarını sıralanmış sırada tutan bir map temsil eder. Sınıf TreeMap SortedMap uygular.
   • Koleksiyonlar frameworkündeki koleksiyonlar senkronize edilmez. Senkronizasyon sarmalayıcıları (s. 735), aynı anda birden fazla iş parçacığı tarafından erişilebilen koleksiyonlar için sağlanır.
   • Değiştirilemeyen koleksiyon sarmalayıcıları (s. 735), koleksiyonu değiştirme girişiminde bulunulursa UnsupportedOperationExceptions (s. 714) öğesini atar.

   • Java SE 9, Liste, Set ve Map arabirimlerine küçük değişmez koleksiyonlar oluşturmanıza olanak tanıyan yeni statik factory yöntemleri ekler; oluşturulduktan sonra değiştirilemezler.
   • factory yöntemleri, gerçekten değişmez ve küçük koleksiyonları depolamak için optimize edilmiş özel koleksiyon nesneleri döndürür.
   • Liste  factory yöntemi "of" değişmez bir Liste oluşturur.
   • Set convenience factory method of creates an immutable Set.
   • Yineleme sırası,  factory yöntemleri tarafından döndürülen set için belirtilmemiştir.
   • Map convenience factory method of creates an immutable Map.
   •  factory yöntemleriyle döndürülen Maplerde ki anahtarlar için yineleme sırası belirtilmemiş.

---------------------------------------------------------------------------------------------------------------------------------------------------------*/
