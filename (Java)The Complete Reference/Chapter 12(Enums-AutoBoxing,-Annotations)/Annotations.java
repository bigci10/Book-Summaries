import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.security.DomainCombiner;


public class Annotations {}

/*-----------------------------------------------------------------------------------------------------------------------
  		Java, ek bilgileri bir kaynak dosyaya gömmenizi sağlayan bir özellik sağlar. 
  		Anotosyan adı verilen bu bilgiler, programın eylemlerini değiştirmez. 
  		Böylece, bir anotosyon bir programın semantiğini değiştirmeden bırakır. 
  		Ancak, bu bilgiler hem geliştirme hem de dağıtım sırasında çeşitli araçlar tarafından kullanılabilir. 
  		Örneğin, bir anotasyon bir kaynak kodu oluşturucu tarafından işlenebilir. 
  		Meta veri terimi de bu özelliğe başvurmak için kullanılır, ancak ek açıklama terimi en açıklayıcı ve daha yaygın olarak kullanılanıdır.
-----------------------------------------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------------------------------------
			Annotation Basics
			
	Anotasyon, interface i temel alan bir mekanizma aracılığıyla oluşturulur. 
	Bir örnekle başlayalım. 
	İşte MyAnno adlı bir ek açıklama için beyan:

-----------------------------------------------------------------------------------------------------------------------*/

//@interface MyAnno
//{
//	String str();
//	int val();
//}

/*-----------------------------------------------------------------------------------------------------------------------
		İlk olarak, anahtar kelime arayüzünden önce gelen @ işaretine dikkat edin.
		Bu, derleyiciye bir ek açıklama türünün bildirildiğini bildirir.
		Ardından, str( ) ve val( ) adlı iki üyeye dikkat edin.
		Tüm ek açıklamalar yalnızca yöntem bildirimlerinden oluşur. 
		Ancak, bu yöntemler için body sağlamıyorsunuz.
		
		Bunun yerine, Java bu yöntemleri uygular. 
		Dahası, yöntemler göreceğiniz gibi alanlar gibi davranır.
		
		
		Ek açıklama extends yan tümcesi içeremez.
		Ancak, tüm anotasyon türleri Anotasyon arabirimini otomatik olarak genişletir.
		Bu nedenle, Ek Açıklama, tüm ek açıklamaların süper arayüzüdür. 
		java.lang.annotation paketi içinde bildirilir.
		Object tarafından tanımlanan hashCode( ), equals( ) ve toString( ) öğelerini geçersiz kılar.
		Ayrıca, çağıran ek açıklamayı temsil eden bir Class nesnesi döndüren annotationType( ) öğesini de belirtir.
		
		Bir ek açıklama bildirdikten sonra, bunu bir şeye açıklama eklemek için kullanabilirsiniz.
		Başlangıçta, ek açıklamalar yalnızca bildirimlerde kullanılabilir ve başlayacağımız yer burasıdır. 
		(JDK 8, tür kullanımına açıklama ekleme özelliğini ekledi ve bu, bu bölümde daha sonra açıklanmıştır. 
		Ancak, aynı temel teknikler her iki ek açıklama türü için de geçerlidir.)
		Herhangi bir bildirim türü, kendisiyle ilişkilendirilmiş bir ek açıklamaya sahip olabilir. 
		Örneğin, sınıflar, yöntemler, alanlar, parametreler ve enum sabitlerine açıklama eklenebilir. 
		Bir ek açıklama bile eklenebilir. 
		Her durumda, ek açıklama bildirimin geri kalanından önce gelir.
		
		Bir ek açıklama uyguladığınızda, üyelerine değerler verirsiniz. 
		Örneğin, MyAnno'nun bir yöntem bildirimine uygulanmasına bir örnek aşağıda verilmiştir:
			
-----------------------------------------------------------------------------------------------------------------------*/

//@MyAnno(str = "annotation example",val = 100)
//public static void myMeth() {//....}




/*-----------------------------------------------------------------------------------------------------------------------
		Bu ek açıklama myMeth( ) yöntemiyle bağlantılıdır.
		Ek açıklama sözdizimine yakından bakın. 
		@ işaretinden önce gelen ek açıklamanın adını, parantez içinde üye başlatmalarının bir listesi izler.
		Bir üyeye değer vermek için, o üyenin adına bir değer atanır. 
		Bu nedenle, örnekte, MyAnno'nun str üyesine 'Ek Açıklama Örneği' dizesi atanır. 
		Bu atamada hiçbir parantez str'yi takip etmediğine dikkat edin. 
		Bir ek açıklama üyesine bir değer verildiğinde, yalnızca adı kullanılır. 
		Bu nedenle, ek açıklama üyeleri bu bağlamda alanlara benzer.
		
-----------------------------------------------------------------------------------------------------------------------*/



/*-----------------------------------------------------------------------------------------------------------------------
			Specifying a Retention Policy
	
	Ek açıklamaları daha fazla incelemeden önce, ek açıklama saklama politikalarını tartışmak gerekir. 
	Bekletme ilkesi, ek açıklamanın hangi noktada atılacağını belirler. 
	Java, java.lang.annotation.RetentionPolicy numaralandırmasında kapsüllenen bu tür üç ilkeyi tanımlar. 
	Bunlar SOURCE, CLASS ve RUNTIME'dır.
	
	SOURCE bekletme ilkesine sahip bir ek açıklama yalnızca kaynak dosyada tutulur ve derleme sırasında atılır.
	
	CLASS bekletme ilkesine sahip bir ek açıklama, derleme sırasında .class dosyasında depolanır. 
		Ancak, çalışma süresi boyunca JVM aracılığıyla kullanılamaz.
		
	RUNTIME bekletme ilkesine sahip bir ek açıklama, derleme sırasında .class dosyasında depolanır ve çalışma zamanı boyunca JVM aracılığıyla kullanılabilir. 
		Böylece, RUNTIME saklama en büyük ek açıklama kalıcılığını sunar.
	NOT Yerel değişken bildirimindeki ek açıklama, .class dosyasında tutulmaz.
	
	Bir ek açıklama için bekletme politikası, Java'nın yerleşik ek açıklamalarından biri kullanılarak belirtilir: @Retention. Genel formu burada gösterilmiştir:
	@Retention(retention-policy)
	
	Burada, bekletme ilkesi daha önce tartışılan ENUM sabitlerinden biri olmalıdır. 
	Ek açıklama için herhangi bir bekletme ilkesi belirtilmezse varsayılan CLASS ilkesi kullanılır. 
	MyAnno'nun aşağıdaki sürümü, RUNTIME bekletme ilkesini belirtmek için @Retention kullanır. 
	Böylece, MyAnno program yürütme sırasında JVM için kullanılabilir olacaktır.
	
-----------------------------------------------------------------------------------------------------------------------*/

//@Retention(RetentionPolicy.RUNTIME)
//@interface MyAnno
//{
//	String str();
//	int val();
//}


/*-----------------------------------------------------------------------------------------------------------------------
				Obtaining Annotations at Run Time by Use of Reflection
	
	Ek açıklamalar çoğunlukla diğer geliştirme veya dağıtım araçları tarafından kullanılmak üzere tasarlanmış olsa da, RUNTIME'ın bir bekletme ilkesini belirtirlerse, reflection kullanılarak herhangi bir Java programı tarafından çalışma zamanında sorgulanabilirler. 
	Reflection, bir sınıf hakkındaki bilgilerin çalışma zamanında elde edilmesini sağlayan özelliktir. 
	Reflection API'si java.lang.reflect paketinde bulunur. 
	Reflection kullanmanın birkaç yolu vardır ve hepsini incelemeyeceğiz. 
	Bununla birlikte, ek açıklamalar için geçerli olan birkaç örneği inceleyeceğiz.

	Reflection kullanmanın ilk adımı, ek açıklamalarını elde etmek istediğiniz sınıfı temsil eden bir Class nesnesi elde etmektir.
	Sınıf, Java'nın yerleşik sınıflarından biridir ve java.lang dosyasında tanımlanır. 
	Bölüm II'de ayrıntılı olarak açıklanmıştır. 
	Bir Class nesnesi elde etmenin çeşitli yolları vardır. 
	En kolaylarından biri, Object tarafından tanımlanan bir yöntem olan getClass( ) öğesini çağırmaktır. Genel formu burada gösterilmiştir:
	
	final Class<?> getClass( )
	It returns the Class object that represents the invoking object
	
	Bir Class nesnesi edindikten sonra, ek açıklamaları da dahil olmak üzere sınıf tarafından bildirilen çeşitli öğeler hakkında bilgi edinmek için bu nesnenin yöntemlerini kullanabilirsiniz.
	Bir sınıf içinde bildirilen belirli bir öğeyle ilişkili ek açıklamaları edinmek istiyorsanız, önce o öğeyi temsil eden bir nesne edinmeniz gerekir.
	Örneğin, Class (diğerlerinin yanı sıra) sırasıyla bir yöntem, alan ve oluşturucu hakkında bilgi edinen getMethod( ), getField( ) ve getConstructor( ) yöntemlerini sağlar. 
	Bu yöntemler Method, Field ve Constructor türündeki nesneleri döndürür.
	
	İşlemi anlamak için, bir yöntemle ilişkili ek açıklamaları alan bir örnek üzerinde çalışalım. 
	Bunu yapmak için, önce sınıfı temsil eden bir Class nesnesi edinirsiniz ve ardından bu Class nesnesinde getMethod( ) öğesini çağırarak yöntemin adını belirtirsiniz. 
	getMethod( ) şu genel biçime sahiptir:
	
	Method getMethod(String methName, Class<?> ... paramTypes)
	
	
	Yöntemin adı methName içinde geçirilir. 
	Yöntemin bağımsız değişkenleri varsa, bu türleri temsil eden Class nesnelerinin de paramTypes tarafından belirtilmesi gerekir.
	ParamType'ın bir varargs parametresi olduğuna dikkat edin. 
	Bu, sıfır dahil olmak üzere gerektiği kadar parametre türü belirtebileceğiniz anlamına gelir. 
	getMethod( ), yöntemi temsil eden bir Method nesnesi döndürür. 
	Yöntem bulunamazsa, NoSuchMethodException atılır.
	
	Bir Class, Method, Field veya Constructor nesnesinden, getAnnotation( ) öğesini çağırarak bu nesneyle ilişkilendirilmiş belirli bir ek açıklama elde edebilir. 
	Genel formu burada gösterilmiştir:
	<A extends Annotation> getAnnotation(Class<A> annoType)
	
	Burada, annoType, ilgilendiğiniz ek açıklamayı temsil eden bir Class nesnesidir. 
	Yöntem, ek açıklamaya bir başvuru döndürür.
	
	Bu başvuruyu kullanarak, ek açıklamanın üyeleriyle ilişkili değerleri elde edebilirsiniz. 
	Yöntem, ek açıklama bulunamazsa null değerini döndürür, bu da ek açıklamanın RUNTIME bekletme özelliğine sahip olmaması durumunda geçerli olur. 
	Aşağıda, daha önce gösterilen tüm parçaları bir araya getiren ve bir yöntemle ilişkili ek açıklamayı görüntülemek için reflection kullanan bir program verilmiştir:
-----------------------------------------------------------------------------------------------------------------------*/

//An annotation type declaration.
//@Retention(RetentionPolicy.RUNTIME)
//@interface MyAnno
//{
//	String str();
//	int val();
//}
//
//class Meta
//{
//	
//	// Annotate a method.
//	@MyAnno(str = "Annotation example",val = 100)
//	public static void myMeth()
//	{
//		Meta ob = new Meta();
//		
//		// Obtain the annotation for this method
//		// and display the values of the members.
//		try
//		{
//			// First, get a Class object that represents
//			// this class.
//			Class<?> c = ob.getClass();
//			
//			// Now, get a Method object that represents
//			// this method.
//			Method m = c.getMethod("myMeth");
//			
//			// Next, get the annotation for this class.
//			MyAnno anno = m.getAnnotation(MyAnno.class);
//			
//			System.out.println(anno.str());
//		}catch (NoSuchMethodException exe)
//		{
//			System.out.println("Method Not Found");
//		}
//	}
//	
//	public static void main(String[] args) 
//	{
//		myMeth();
//		
//	}
//}



/*-----------------------------------------------------------------------------------------------------------------------
	Bu program, Meta sınıfındaki myMeth( ) ile ilişkili MyAnno ek açıklamasındaki str ve val değerlerini elde etmek ve görüntülemek için açıklandığı gibi reflection kullanır. 
	Özellikle dikkat edilmesi gereken iki şey var. İlk olarak, bu satırda
	MyAnno anno = m.getAnnotation(MyAnno.class);
	
	MyAnno.class ifadesine dikkat edin. 
	Bu ifade, ek açıklama olan MyAnno türündeki bir Class nesnesini değerlendirir.
	Bu yapıya sınıf değişmez değeri denir. 
	Bu ifade türünü, bilinen bir sınıfın Class nesnesine ihtiyaç duyulduğunda kullanabilirsiniz. 
	Örneğin, bu deyim Meta için Class nesnesini elde etmek için kullanılmış olabilir
	
	Class<?> c = Meta.class;
	
	Elbette, bu yaklaşım yalnızca bir nesnenin sınıf adını önceden bildiğinizde işe yarar, ki bu her zaman böyle olmayabilir. 
	Genel olarak, sınıflar, arabirimler, ilkel türler ve diziler için bir sınıf değişmez değeri elde edebilirsiniz.
	
	İkinci ilgi çekici nokta, str ve val ile ilişkili değerlerin aşağıdaki satırla çıktılarında elde edilme şeklidir:
	System.out.println(anno.str() + " " + anno.val());
	
	Yöntem çağrısı sözdizimi kullanılarak çağrıldıklarına dikkat edin. 
	Aynı yaklaşım, bir ek açıklama üyesinin değeri gerektiğinde de kullanılır.
-----------------------------------------------------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------------------------------------------------------
				A Second Reflection Example
	
	Önceki örnekte, myMeth( ) parametresi yoktur. 
	Böylece, getMethod( ) çağrıldığında, yalnızca myMeth adı geçti. 
	Ancak, parametreleri olan bir yöntem elde etmek için, bu parametrelerin türlerini temsil eden sınıf nesnelerini getMethod( ) bağımsız değişkenleri olarak belirtmeniz gerekir. 
	Örneğin, önceki programın biraz farklı bir sürümü aşağıda verilmiştir:

-----------------------------------------------------------------------------------------------------------------------*/

//@Retention(RetentionPolicy.RUNTIME)
//@interface MyAnno
//{
//	String str();
//	int val();
//}
//
//class Meta 
//{
//	@MyAnno(str = "Two parameters",val = 100)
//	public static void myMeth(String str,int val)
//	{
//		Meta ob = new Meta();
//		
//		try
//		{
//			Class<?> c = ob.getClass();
//			
//			Method m = c.getMethod("myMeth", String.class, int.class);
//			
//			MyAnno anno = m.getAnnotation(MyAnno.class);
//			System.out.println(anno.str() + " " + anno.val());
//		}catch(NoSuchMethodException exc)
//		{
//			System.out.println("Method Not Found.");
//		}
//	}
//	
//	public static void main(String[] args) 
//	{
//		myMeth("test",10);
//	}
//}


/*-----------------------------------------------------------------------------------------------------------------------
	Bu sürümde, myMeth( ) bir String ve bir int parametresi alır. 
	Bu yöntem hakkında bilgi edinmek için, getMethod( ) burada gösterildiği gibi çağrılmalıdır:
	
	Method m = c.getMethod("myMeth", String.class, int.class);
	Burada, String ve int'yi temsil eden Class nesneleri ek bağımsız değişkenler olarak geçirilir.
	

-----------------------------------------------------------------------------------------------------------------------*/




/*-----------------------------------------------------------------------------------------------------------------------
 				Obtaining All Annotations
 	
 	Bir öğeyle ilişkili RUNTIME bekletme özelliğine sahip tüm ek açıklamaları, o öğede getAnnotations( ) öğesini çağırarak elde edebilirsiniz. 
 	Bu genel forma sahiptir:
 	
 	Annotation[ ] getAnnotations( )
 	
 	Ek açıklamaların bir dizisini döndürür. getAnnotations( ), diğerlerinin yanı sıra Class, Method, Constructor ve Field türündeki nesnelere çağrılabilir. 
 	Burada, bir sınıfla ve bir yöntemle ilişkili tüm ek açıklamaların nasıl elde edileceğini gösteren başka bir yansıma örneği verilmiştir. 
 	İki ek açıklama bildirir. 
 	Daha sonra bu ek açıklamaları bir sınıfa ve yönteme açıklama eklemek için kullanır.
-----------------------------------------------------------------------------------------------------------------------*/


//@Retention(RetentionPolicy.RUNTIME)
//@interface MyAnno
//{
//	String str();
//	int val();
//}
//
//@Retention(RetentionPolicy.RUNTIME)
//@interface What
//{
//	String description();
//}
//
//@What(description = "an anotations")
//@MyAnno(str = "meta2" , val = 99)
//class Meta2
//{
//	@What(description = "an anno")
//	@MyAnno(str = "testing",val = 100)
//	public static void myMeth()
//	{
//		Meta2 ob = new Meta2();
//		
//		try
//		{
//			Annotation[] annos = ob.getClass().getAnnotations();
//			
//			for(Annotation a : annos)
//				System.out.println(a);
//			
//			Method m = ob.getClass().getMethod("myMeth");
//			annos = m.getAnnotations();
//		}catch (Exception e) {
//			System.out.println(e);
//		}
//	}
//	public static void main(String[] args) 
//	{
//		myMeth();
//		
//	}
//}

/*-----------------------------------------------------------------------------------------------------------------------
 
  	Program, Meta2 sınıfı ve myMeth( ) yöntemiyle ilişkili tüm ek açıklamaların bir dizisini elde etmek için getAnnotations( ) kullanır. 
  	Açıklandığı gibi, getAnnotations( ) bir dizi Annotation nesnesi döndürür. 
  	Ek Açıklama'nın tüm ek açıklama arabirimlerinin süper arabirimi olduğunu ve Nesne'deki toString( ) öğesini geçersiz kıldığını hatırlayın. 
  	Bu nedenle, bir Ek Açıklamaya başvuru çıktı alındığında, önceki çıktının gösterdiği gibi, ek açıklamayı açıklayan bir dize oluşturmak için toString( ) yöntemi çağrılır.

-----------------------------------------------------------------------------------------------------------------------*/


/*-----------------------------------------------------------------------------------------------------------------------
				The AnnotatedElement Interface
				
	Önceki örnekler tarafından kullanılan getAnnotation( ) ve getAnnotations( ) yöntemleri, java.lang.reflect içinde tanımlanan AnnotatedElement arabirimi tarafından tanımlanır. 
	Bu arabirim, ek açıklamalar için reflection destekler ve diğerlerinin yanı sıra Method, Field, Constructor, Class ve Package sınıfları tarafından uygulanır.
	
	getAnnotation( ) ve getAnnotations( )AnnotatedElement öğelerine ek olarak diğer birçok yöntemi tanımlar. 
	Ek açıklamalar başlangıçta Java'ya eklendiğinden beri iki tane mevcuttur. 
	Birincisi, bu genel forma sahip getDeclaredAnnotations( ), 'dir:

	Annotation[ ] getDeclaredAnnotations( )
	
	Çağıran nesnede bulunan devralınmamış tüm ek açıklamaları döndürür. 
	İkincisi, bu genel forma sahip olan isAnnotationPresent( )'dır:
	
	default boolean isAnnotationPresent(Class<? extends Annotation> annoType)
	
	annoType tarafından belirtilen ek açıklama çağıran nesneyle ilişkiliyse true değerini döndürür. 
	Aksi takdirde false değerini döndürür. 
	JDK 8 bunlara getDeclaredAnnotation( ), getAnnotationsByType( ) ve getDeclaredAnnotationsByType( ) değerlerini ekledi. 
	Bunlardan son ikisi otomatik olarak tekrarlanan bir ek açıklama ile çalışır. 
	(Tekrarlanan ek açıklamalar bu bölümün sonunda tartışılmaktadır.)
-----------------------------------------------------------------------------------------------------------------------*/





/*-----------------------------------------------------------------------------------------------------------------------
					Using Default Values

	Ek açıklama üyelerine, detaylandırma uygulandığında hiçbir değer belirtilmediğinde kullanılacak varsayılan değerleri verebilirsiniz. 
	Varsayılan değer, bir üyenin bildirimine varsayılan bir yan tümce eklenerek belirtilir. 
	Bu genel forma sahiptir:
	
	type member( ) default value ;
	
	Burada, değer, türle uyumlu bir türde olmalıdır. 
	Varsayılan değerleri içerecek şekilde yeniden yazılan @MyAnno aşağıda verilmiştir:
	
-----------------------------------------------------------------------------------------------------------------------*/

//@Retention(RetentionPolicy.RUNTIME)
//@interface MyAnno
//{
//	String str() default "Testing";
//	int val()  default 9000;
//}

/*-----------------------------------------------------------------------------------------------------------------------
 	Bu bildirim, str için varsayılan bir 'Test' değeri ve val için 9000 değerini verir. 
 	Bu, @MyAnno kullanıldığında her iki değerin de belirtilmesi gerekmediği anlamına gelir. 
 	Ancak, istenirse her ikisinden birine veya her ikisine de değer verilebilir. 
 	Bu nedenle, @MyAnno kullanılabileceği dört yol şunlardır:
 	
 	@MyAnno() // both str and val default
	@MyAnno(str = "some string") // val defaults
	@MyAnno(val = 100) // str defaults
	@MyAnno(str = "Testing", val = 100) // no defaults
 -----------------------------------------------------------------------------------------------------------------------*/


//@Retention(RetentionPolicy.RUNTIME)
//@interface MyAnno
//{
//	String str() default "Testing";
//	int val() default 9000;
//}
//
//class Meta3
//{
//	public static void myMeth()
//	{
//		Meta3 ob = new Meta3();
//		
//		try
//		{
//			Class<?> c = ob.getClass();
//			
//			Method m = c.getMethod("myMeth");
//			
//			MyAnno anno = m.getAnnotation(MyAnno.class);
//			
//			System.out.println(anno.str());
//		}catch(NoSuchMethodException ex)
//		{
//			System.out.println(ex);
//		}
//	}
//	
//	public static void main(String[] args)
//	{
//		myMeth();
//	}
//}


/*-----------------------------------------------------------------------------------------------------------------------
 				Marker Annotations
 				
 	İşaretçi ek açıklaması, üye içermeyen özel bir ek açıklama türüdür. 
 	Tek amacı bir öğeyi işaretlemektir. 
 	Bu nedenle, ek açıklama olarak varlığı yeterlidir. 
 	Bir işaretçi detaylandırmasının mevcut olup olmadığını belirlemenin en iyi yolu, AnnotatedElement arabirimi tarafından tanımlanan isAnnotationPresent( ) yöntemini kullanmaktır. 
 	Aşağıda, işaretleyici ek açıklaması kullanan bir örnek verilmiştir.
-----------------------------------------------------------------------------------------------------------------------*/

//@Retention(RetentionPolicy.RUNTIME)
//@interface MyMarker {}
//
//class Marker
//{
//	@MyMarker
//	public static void myMeth()
//	{
//		Marker ob = new Marker();
//	
//	
//	try
//	{
//		Method m = ob.getClass().getMethod("MyMarker");
//		
//		if(m.isAnnotationPresent(MyMarker.class))
//			System.out.println("MyMarker is present");
//	}catch(NoSuchMethodException ex) 
//	{
//		System.out.println(ex);
//	}
//	}
//	public static void main(String args)
//	{
//		myMeth();
//	}
//}


/*-----------------------------------------------------------------------------------------------------------------------
				Single-Member Annotations
	
	Tek üyeli ek açıklama yalnızca bir üye içerir. 
	Normal bir ek açıklama gibi çalışır, ancak üyenin değerini belirtmenin kısa bir biçimine izin verir. 
	Yalnızca bir üye varsa, ek açıklama uygulandığında o üyenin değerini belirtebilirsiniz, üyenin adını belirtmenize gerek yoktur. 
	Ancak, bu kısaltmayı kullanmak için, üyenin adı value olmalıdır.

-----------------------------------------------------------------------------------------------------------------------*/

 @Retention(RetentionPolicy.RUNTIME)
 @interface MySingle
 {
	 int value(); // this variable name must be value
 }
 
 class Single
 {
	 @MySingle(100)
	 public static void myMeth()
	 {
		 Single ob = new Single();
	 
	 
	 try {
		 Method m = ob.getClass().getMethod("myMeth");
		 
		 MySingle anno = m.getAnnotation(MySingle.class);
		 
		 System.out.println(anno.value());
	 }catch(NoSuchMethodException ex)
	 {
		 System.out.println(ex);
	 }
	 }
	 public static void main(String[] args)
	 {
		 myMeth();
	 }
 }
 
 /*-----------------------------------------------------------------------------------------------------------------------
 		Beklendiği gibi, bu program 100 değerini görüntüler. 
 		Programda, burada gösterildiği gibi myMeth( ) açıklamasına açıklama eklemek için @MySingle kullanılır:
 		@MySingle(100)
 		
 		Başka üyeleri olan bir ek açıklama uygularken tek değerli sözdizimini kullanabilirsiniz, ancak bu diğer üyelerin hepsinin varsayılan değerleri olmalıdır. 
 		Örneğin, burada xyz değeri, varsayılan değeri sıfır olacak şekilde eklenir:
 		
 		@interface SomeAnno {
 			int value();
 			int xyz() default 0;
		}
		
		xyz için varsayılanı kullanmak istediğiniz durumlarda, tek üyeli sözdizimini kullanarak value değerini belirterek @SomeAnno daha sonra gösterildiği gibi uygulayabilirsiniz.
 
 		@SomeAnno(88)
 		
 		Bu durumda, xyz varsayılan olarak sıfırdır ve değer 88 değerini alır. 
 		Elbette, xyz için farklı bir değer belirtmek için, burada gösterildiği gibi her iki üyenin de açıkça adlandırılması gerekir:
 		
 		@SomeAnno(value = 88, xyz = 99)
 		
 		Tek üyeli bir ek açıklama kullandığınızda, bu üyenin adının value olması gerektiğini unutmayın.
 -----------------------------------------------------------------------------------------------------------------------*/
