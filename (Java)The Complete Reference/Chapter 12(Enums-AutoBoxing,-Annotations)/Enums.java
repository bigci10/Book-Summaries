import java.util.Random;

public class EnumsAutoBoxingAnnotations {}

/*-----------------------------------------------------------------------------------------------------------------------
 
 	Bu bölüm, başlangıçta Java'nın bir parçası olmayan, ancak zamanla her biri Java programlamanın neredeyse vazgeçilmez bir yönü haline gelen üç özelliği incelemektedir: enums, autoboxing ve annotations. 
 	Başlangıçta JDK 5 tarafından eklenen, her biri Java programcılarının güvendiği bir özelliktir, çünkü her biri ortak programlama görevlerini yerine getirmek için kolaylaştırılmış bir yaklaşım sunar. 
 	Bu bölümde ayrıca Java'nın tür sarmalayıcıları tartışılmakta ve yansıma tanıtılmaktadır.
 	
-----------------------------------------------------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------------------------------------------------------
 				Enumerations
 	
 	En basit haliyle numaralandırma, yeni bir veri türünü ve yasal değerlerini tanımlayan adlandırılmış sabitlerin bir listesidir. 
 	Bu nedenle, bir numaralandırma nesnesi yalnızca listede bildirilen bir değeri tutabilir. Diğer değerlere izin verilmez. Başka bir deyişle, numaralandırma, bir veri türünün yasal olarak sahip olabileceği tek değerleri açıkça belirtmenin bir yolunu sunar. 
 	enums, bir öğe koleksiyonunu temsil eden bir değerler kümesini tanımlamak için yaygın olarak kullanılır. Örneğin, başarılı, başarısız veya beklemede gibi bazı işlemlerden kaynaklanabilecek hata kodlarını temsil etmek için bir numaralandırma kullanabilirsiniz; veya bir cihazın çalışıyor, durdurulmuş veya duraklatılmış gibi içinde bulunabileceği durumların bir listesi. 
 	Java'nın ilk sürümlerinde, bu değerler son değişkenler kullanılarak tanımlanmıştır, ancak numaralandırmalar çok daha üstün bir yaklaşım sunmaktadır.
  

	Java numaralandırmaları, ilk bakışta, diğer dillerdeki enumlara benzer görünse de, bu benzerlik yalnızca anlamsal olabilir, çünkü Java'da bir enum bir sınıf türünü tanımlar. 
	Sınıflar halinde enumlar yaparak, enumların yetenekleri büyük ölçüde genişletilir. 
	Örneğin, Java'da bir enum sınıfın oluşturucuları, yöntemleri ve örnek değişkenleri olabilir. 
	Güçleri ve esneklikleri nedeniyle, enumlar Java API kitaplığında yaygın olarak kullanılmaktadır.
-----------------------------------------------------------------------------------------------------------------------*/  




/*-----------------------------------------------------------------------------------------------------------------------
				Enumeration Fundamentals
	
	Enum anahtar kelimesi kullanılarak enumeration oluşturulur. 
	Örneğin, çeşitli Apple çeşitlerini listeleyen basit bir enumaration:

-----------------------------------------------------------------------------------------------------------------------*/

//enum Apple
//{
//	Jonathon,GoldenDel,RedDel,Winesap,Cortland
//}

/*-----------------------------------------------------------------------------------------------------------------------

	Jonathan, Goldendel vb. Tanımlayıcılara numaralandırma sabitleri denir. 
	Her biri dolaylı olarak Apple'ın public static final üyesi olarak ilan edilir. 
	Ayrıca, türleri ilan edildikleri numaralandırma türüdür, bu da bu durumda Apple'dır.

	
	Bir numaralandırma tanımladıktan sonra, bu türde bir değişken oluşturabilirsiniz. 
	Ancak, numaralandırmalar bir sınıf türünü tanımlasa da, new kullanarak bir enum örneği oluşturmazsınız. 
	Bunun yerine, bir numaralandırma değişkenini, ilkel türlerden birini yaptığınız gibi bildirir ve kullanırsınız. 
	Örneğin, bu, ap'yi Apple numaralandırma türünde bir değişken olarak bildirir:
	
	Apple ap;
	
	ap Apple türünde olduğundan, atanabileceği (veya içerebileceği) değerler yalnızca numaralandırma tarafından tanımlanan değerlerdir. 
	Örneğin, bu, ap'ye RedDel değerini atar:
	
	ap = Apple.RedDel;
	
	RedDel sembolünün Apple'dan sonra geldiğine dikkat edin.
	İki numaralandırma sabiti, == ilişkisel işleci kullanılarak eşitlik için karşılaştırılabilir. 
	Örneğin, bu deyim ap içindeki değeri GoldenDel sabitiyle karşılaştırır:
	
	if(ap == Apple.GoldenDel) //...
	
	Bir numaralandırma değeri, bir switch deyimini denetlemek için de kullanılabilir. 
	Tabii ki, tüm vaka ifadeleri, switch ifadesi tarafından kullanılanla aynı enumdan sabitler kullanmalıdır. 
	Örneğin, bu anahtar mükemmel bir şekilde geçerlidir:

	switch(ap)
	{
		case Jonathan:
		//..
		
		case Winesap:
		//..
	
	switch içinde ki caselerde, numaralandırma sabitlerinin adlarının, numaralandırma türü adlarıyla nitelenmeden kullanıldığına dikkat edin. 
	Yani, Apple.Winesap değil, Winesap kullanılır. 
	Bunun nedeni, anahtar ifadesindeki numaralandırma türünün, büyük\/küçük harf sabitlerinin enum türünü zaten örtülü olarak belirtmiş olmasıdır. 
	Case deyimlerindeki sabitleri enum türü adlarıyla nitelemeye gerek yoktur. 
	Aslında, bunu yapmaya çalışmak bir derleme hatasına neden olur.
-----------------------------------------------------------------------------------------------------------------------*/


//enum Apple
//{
//	Jonathan,GoldenDel,RedDel,Winesap,Cortland
//}
//
//class EnumDemo
//{
//	public static void main(String[] args) 
//	{
//		Apple ap ;
//		
//		ap = Apple.Cortland;
//		
//		System.out.println(ap);
//		
//		ap = Apple.GoldenDel;
//		
//		if(ap == Apple.GoldenDel)
//			System.out.println("ap contains GoldenDel.");
//		
//		//use switchcase
//		
//		switch(ap)
//		{
//			case Jonathan:
//				System.out.println("Jonathan is red");
//			
//			case GoldenDel:
//				System.out.println("Golden Delicious is red.");
//			
//			case RedDel:
//				System.out.println("Red Delicious is red.");
//				break;
//		}
//	}
//}




/*-----------------------------------------------------------------------------------------------------------------------
				The values( ) and valueOf( ) Methods
				
	Tüm numaralandırmalar otomatik olarak önceden tanımlanmış iki yöntem içerir: values( ) ve valueOf( ). 
	Genel formları burada gösterilmiştir:
	
	public static enum-type [ ] values( )
	public static enum-type valueOf(String str )
	
	values( ) yöntemi, numaralandırma sabitlerinin listesini içeren bir dizi döndürür. 
	valueOf( ) yöntemi, değeri str içinde geçirilen dizeye karşılık gelen numaralandırma sabitini döndürür. 
	Her iki durumda da, enum tipi numaralandırmanın türüdür. 
	Örneğin, daha önce gösterilen Apple numaralandırması söz konusu olduğunda, Apple.valueOf('Winesap') dönüş türü Winesap'tır.
	
	bir örnek..
	
	
-----------------------------------------------------------------------------------------------------------------------*/


//enum Apple
//{
//	Jonathan,GoldenDel,RedDel,Winesap,Cortlant
//}
//
//class EnumDemo2
//{
//	public static void main(String[] args) 
//	{
//		Apple ap;
//		
//		System.out.println("Here are all Apple constants:");
//		
//		Apple[] allApples = Apple.values();
//		
//		for(Apple a: allApples)
//			System.out.println(a);
//		
//		System.out.println();
//		
//		//use valueOf()
//		ap = Apple.valueOf("Winesap");
//		System.out.println("ap contains "+ ap);
//	}
//}


/*-----------------------------------------------------------------------------------------------------------------------
			Java Enumerations Are Class Types
	
	Daha önce de belirtildiği gibi, Java numaralandırması bir sınıf türüdür. 
	Yeni kullanarak bir enum örneği oluşturmasanız da, aksi takdirde diğer sınıflarla aynı yeteneklere sahiptir. 
	Enum'un bir sınıfı tanımlaması, Java numaralandırmasına olağanüstü bir güç verir. 
	Örneğin, onlara oluşturucular verebilir, örnek değişkenleri ve yöntemleri ekleyebilir ve hatta arabirimler uygulayabilirsiniz.
	
	Her numaralandırma sabitinin kendi numaralandırma türünde bir nesne olduğunu anlamak önemlidir. 
	Bu nedenle, bir enum için bir oluşturucu tanımladığınızda, her numaralandırma sabiti oluşturulduğunda oluşturucu çağrılır. 
	Ayrıca, her numaralandırma sabitinin, numaralandırma tarafından tanımlanan herhangi bir örnek değişkeninin kendi kopyası vardır. 
	Örneğin, aşağıdaki Apple sürümünü göz önünde bulundurun:
	
-----------------------------------------------------------------------------------------------------------------------*/

//enum Apple
//{
//	Jonathan(10),GoldenDel(9),RedDel(12),Winesap(15),Cortland(8);
//	
//	private int price;
//	
//	Apple(int p)
//	{
//		price = p;
//	}
//	
//	public int getPrice() {
//		return price;
//	}
//}
//
//class EnumDemo3
//{
//	public static void main(String[] args) 
//	{
//		Apple ap;
//		
//		System.out.println("Winesap costs "+ Apple.Winesap.getPrice()+" cents");
//		
//	}
//}



/*-----------------------------------------------------------------------------------------------------------------------

	Apple'ın bu sürümü üç şey ekliyor. Birincisi, her bir elma çeşidinin fiyatını tutmak için kullanılan örnek değişken price. 
	İkincisi, bir elmanın fiyatını geçen Apple constructor. 
	Üçüncüsü, fiyatın değerini döndüren getPrice( ) yöntemidir.
	
	ap değişkeni main( içinde bildirildiğinde), Apple için constructor belirtilen her sabit için bir kez çağrılır. 
	Burada gösterildiği gibi, her sabitten sonra parantez içine koyarak oluşturucuya yönelik bağımsız değişkenlerin nasıl belirtildiğine dikkat edin:
	
	Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland(8);
	
	Bu değerler Apple( ) öğesinin p parametresine geçirilir ve bu parametre daha sonra bu değeri price a atar. 
	Yine, constructor her sabit için bir kez çağrılır.
	
	Her numaralandırma sabitinin kendi price kopyası olduğundan, getPrice( ) öğesini çağırarak belirli bir elma türünün fiyatını elde edebilirsiniz. 
	Örneğin, main( ) de bir Winesap'ın fiyatı aşağıdaki çağrı ile elde edilir:
	
	Apple.Winesap.getPrice( )
	
	Önceki örnek yalnızca bir oluşturucu içermesine rağmen, bir enum, diğer sınıflarda olduğu gibi iki veya daha fazla aşırı yüklenmiş form sunabilir. 
	Örneğin, Apple'ın bu sürümü, hiçbir fiyat verisinin mevcut olmadığını belirtmek için fiyatı –1 olarak başlatan varsayılan bir oluşturucu sağlar:

-----------------------------------------------------------------------------------------------------------------------*/


//enum Apple
//{
//	Jonathan(10),GoldenDel(9),RedDel,Winesap(15),Cortland(8);
//	
//	private int price;
//	
//	Apple(int p) {price = p;}
//	
//	Apple()
//	{
//		price = -1;
//	}
//	
//	public int getPrice() {
//		return price;
//	}
//}


/*-----------------------------------------------------------------------------------------------------------------------
	Bu sürümde RedDel'e bir argüman verilmediğine dikkat edin. 
	Bu, varsayılan oluşturucunun çağrıldığı ve RedDel'in fiyat değişkenine –1 değerinin verildiği anlamına gelir.

	
	Aşağıda, numaralandırmalar için geçerli olan iki kısıtlama verilmiştir. 
	İlk olarak, bir numaralandırma başka bir sınıfı devralamaz. 
	İkincisi, bir enum bir üst sınıf olamaz. Bu, bir enumun extend olamayacağı anlamına gelir. 
	Aksi takdirde, enum diğer sınıf tipleri gibi davranır. 
	Önemli olan, numaralandırma sabitlerinin her birinin, tanımlandığı sınıfın bir nesnesi olduğunu hatırlamaktır.
-----------------------------------------------------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------------------------------------------------------
		Enumerations Inherit Enum
		
	Bir enum bildirirken bir üst sınıfı devralamasanız da, tüm numaralandırmalar otomatik olarak birini devralır: java.lang.Enum. 
	Bu sınıf, tüm numaralandırmalar tarafından kullanılabilen çeşitli yöntemleri tanımlar. 
	Enum sınıfı Bölüm II'de ayrıntılı olarak açıklanmıştır, ancak yöntemlerinden üçü şu anda bir tartışmayı gerektirmektedir.
	
	Bir numaralandırma sabitinin sabitler listesindeki konumunu gösteren bir değer elde edebilirsiniz. Buna sıra değeri denir ve burada gösterilen ordinal( ) yöntemi çağrılarak alınır:
	
	final int ordinal()	
	
	Çağırma sabitinin sıra değerini döndürür. 
	Sıra değerleri sıfırdan başlar. 
	Böylece, Apple numaralandırmasında, Jonathan'ın sıra değeri sıfırdır, GoldenDel'in sıra değeri 1'dir, 
	RedDel'in sıra değeri 2'dir, vb.
	
	Aynı numaralandırmanın iki sabitinin sıra değerini, compareTo( ) yöntemini kullanarak karşılaştırabilirsiniz. 
	Bu genel forma sahiptir:
		final int compareTo(enum-type e)
	
	Burada, enum-tipi numaralandırmanın türüdür ve e, çağırma sabitiyle karşılaştırılan sabit varlıktır. 
	Unutmayın, hem çağıran sabit hem de e aynı numaralandırmada olmalıdır. 
	Çağırma sabiti e'ninkinden daha küçük bir sıra değerine sahipse, compareTo( ) negatif bir değer döndürür.
	
	İki sıra değeri aynıysa, sıfır döndürülür. 
	Çağırma sabiti e'ninkinden daha büyük bir sıra değerine sahipse, pozitif bir değer döndürülür.
	
	Eşitlik için bir numaralandırma sabitini, nesne tarafından tanımlanan eşit () yöntemini geçersiz kılan Equals () kullanarak başka bir nesne ile karşılaştırabilirsiniz
	equals( ) bir numaralandırma sabitini başka herhangi bir nesneyle karşılaştırabilse de, bu iki nesne yalnızca her ikisi de aynı numaralandırma içinde aynı sabite atıfta bulunuyorsa eşit olacaktır.
	Sadece ortak sıra değerlerine sahip olmak, iki sabit farklı numaralandırmalardan geliyorsa, equals( ) değerinin true değerini döndürmesine neden olmaz.
	
	Remember, you can compare two enumeration references for equality by using ==.
	The following program demonstrates the ordinal( ), compareTo( ), and equals( ) methods:
-----------------------------------------------------------------------------------------------------------------------*/

//enum Apple
//{
//	Jonathan,GoldenDel,RedDel,Winesap,Cortland
//}
//
//class EnumDemo4
//{
//	public static void main(String[] args) 
//	{
//		Apple ap, ap2, ap3;
//		
//		System.out.println("Here are all apple constants" + "and their ordinal values :");
//		
//		for(Apple a : Apple.values())
//			System.out.println(a + " " + a.ordinal());
//		
//		ap = Apple.RedDel;
//		ap2 = Apple.GoldenDel;
//		ap3 = Apple.RedDel;
//		
//		System.out.println();
//		
//		if(ap.compareTo(ap2) < 0)
//			System.out.println(ap + " comes before " + ap2);
//		
//		if(ap.compareTo(ap2) > 0)
//			System.out.println(ap2 + " comes before " + ap2);
//		
//		if(ap.equals(ap2))
//			System.out.println("Error!");
//		
//		if(ap == ap3)
//			System.out.println(ap + " == " + ap3);
//		
//	}
//}


/*-----------------------------------------------------------------------------------------------------------------------
 				Another Enumeration Example
 	
 	Devam etmeden önce, bir enum kullanan farklı bir örneğe bakacağız. 
 	Bölüm 9'da, otomatik bir \"karar verici\" programı oluşturuldu. 
 	Bu sürümde, HAYIR, EVET, BELKI, DAHA SONRA, YAKINDA ve ASLA olarak adlandırılan değişkenler bir arayüz içinde bildirilmiştir ve olası cevapları temsil eder. 
 	Bu yaklaşımda teknik olarak yanlış bir şey olmasa da, numaralandırma daha iyi bir seçimdir. 
 	İşte bu programın cevapları tanımlamak için Answers adlı bir enum kullanan geliştirilmiş bir sürümü. 
 	Bu sürümü Bölüm 9'daki orijinalle karşılaştırmalısınız.
 
-----------------------------------------------------------------------------------------------------------------------*/ 

//enum Answers
//{
//	NO,YES,MAYBE,LATER,SOON,NEVER
//}
//
//class Question
//{
//	Random rand = new Random();
//	
//	
//	Answers ask()
//	{
//		int prob = (int) (100 * rand.nextDouble());
//		
//		if(prob < 15)
//			return Answers.MAYBE;
//		else if (prob < 30)
//			return Answers.NO;
//		else if (prob < 60)
//			return Answers.YES;
//		else if (prob < 75)
//			return Answers.LATER;
//		else
//			return Answers.NEVER;
//	}
//
//}

//class AskMe
//{
//	static void answer(Answers result)
//	{
//		switch(result) {
//		
//		case NO:
//			System.out.println("NO");
//			break;
//		
//		
//		case YES:
//			System.out.println("Yes");
//			break;
//		
//		case MAYBE:
//			System.out.println("MAYBE");
//			break;
//		
//		}
//	}
//	
//	public static void main(String[] args) 
//	{
//		Question q = new Question();
//		answer(q.ask());
//		
//	}
//}
